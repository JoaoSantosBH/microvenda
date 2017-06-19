/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import controle.ControladorEstoque;
import controle.ControladorPedido;
import controle.ControladorVenda;
import entidade.AreceberEntidade;
import entidade.ItemEntidade;
import entidade.PedidoEntidade;
import entidade.VendaEntidade;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import persistencia.AreceberDAO;
import persistencia.EstoqueDAO;
import persistencia.ProdutoDAO;
import util.EmailAssincrono;

/**
 *
 * @author joaosantos
 */
public class GerarVendaDoProdutoServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            String idPedido = request.getParameter("id_pedidos");
            String idVendedor = request.getParameter("id_vendedors");
            String idCliente = request.getParameter("id_clientes");
            String numPedido = request.getParameter("num_pedidos");
            String valorEntrada = request.getParameter("valor_entrada");
            String numParcelas = request.getParameter("numero_parcelas");
            String formaPagamento = request.getParameter("forma_pagamento");
            String intervalo = request.getParameter("intervalo_parcelas");
            int a = Integer.parseInt(idPedido);
            PedidoEntidade pedido = controle.ControladorPedido.pegarPedidoPeloId(a);
            List<ItemEntidade> ls = controle.ControladorPedido.listarItensDoPedidoPeloNumPed(pedido.getNumeroPedido());
            String motivoNaoPodeVender = "";
            Boolean podeVender = true;

            //Tratar Valor de Entrada
            Integer entrada = Integer.parseInt(valorEntrada);
            if (entrada < 0) {
                entrada = entrada * -1;
                valorEntrada = entrada.toString();
            }

            //V E R I F I C A R   S E    P O D E    V E N D E R
            for (ItemEntidade it : ls) {
                ItemEntidade item = new ItemEntidade();
                item.setNome(it.getNome());
                item.setIdProduto(it.getIdProduto());
                item.setQtde(it.getQtde());
                Boolean existe = ControladorEstoque.verificarSeItemTemEstoque(item.getIdProduto(), item.getQtde());
                if (existe == false) {
                    podeVender = false;
                    motivoNaoPodeVender = it.getNome() + "com estoque insuficiente para a venda! Entre em contato com seu fornecedor";
                }
            }

            //V E R I F I C A R   S E    J A T E M  V E N D A C O M  M E S M O  N U M  PO ED I ODO   
            Boolean existeVendaParaEsteNumPedido = ControladorVenda.verificarSeJaExisteVendaComNumPedidoAtual(numPedido);
            if (existeVendaParaEsteNumPedido) {
                motivoNaoPodeVender = "Já existe uma venda gerada com este Num Pedido!";
                podeVender = false;
            }
            // V E R I F I C A R   S E  P R O D T U T O  E S T A  A T I V O 
            for (ItemEntidade it : ls) {
                ItemEntidade item = new ItemEntidade();
                item.setNome(it.getNome());
                item.setIdProduto(it.getIdProduto());
                item.setQtde(it.getQtde());
                Boolean VerificarSeProdutoEstaAtivo = ProdutoDAO.verificarSeProdutoEstaAtivo(it.getIdProduto());
                if (VerificarSeProdutoEstaAtivo == false) {
                    podeVender = false;
                    motivoNaoPodeVender = "Este Produto não pode ser vendido pois foi removido! Adquira mais com seu fornecedor!";
                }
            }

            //B A I X A R    I T E M    D O    E S T O Q U E 
            if (podeVender) {
                for (ItemEntidade it : ls) {
                    ItemEntidade item = new ItemEntidade();
                    item.setNome(it.getNome());
                    item.setIdProduto(it.getIdProduto());
                    item.setIdUsuario(it.getIdUsuario());
                    item.setQtde(it.getQtde());
                    int qtdAtual = EstoqueDAO.pegarQuantidadeDoItemNoEstoqueParaAtualizar(item.getIdProduto(), item.getQtde());
                    ControladorEstoque.atualizarEstoque(item, qtdAtual);
                }

                //B A I X A    N O    P E D I D O
                ControladorPedido.darBaixaPedido(pedido.getNumeroPedido());

                //G E R A R  V E N D A 
                VendaEntidade venda = new VendaEntidade();

                Calendar dataHoje = Calendar.getInstance();
                SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
                String agora = s.format(dataHoje.getTime());

                venda.setNumPedido(pedido.getNumeroPedido());
                venda.setIdUsuario(pedido.getIdUsuario());
                venda.setIdCliente(pedido.getIdCliente());
                venda.setData(agora);
                venda.setFormaPagamento(Short.valueOf(formaPagamento));
                venda.setValor(pedido.getTotal());
                venda.setParcelas(Integer.parseInt(numParcelas));
                venda.setEntrada(Float.valueOf(valorEntrada));

                int idVenda = ControladorVenda.criarNovaVenda(venda);

                ////C R I A R  A G E N D A M E N T O  A   R E C E B E R   se for parcelado
                if (venda.getFormaPagamento() == 2 || venda.getFormaPagamento() == 0) {

                    if (entrada == 0) {

                        for (int i = 0; i < venda.getParcelas(); i++) {

                            AreceberEntidade ar = new AreceberEntidade();
                            Float valor = venda.getValor() / venda.getParcelas();
                            ar.setIdVenda(idVenda);
                            ar.setIdUsuario(venda.getIdUsuario());
                            ar.setIdCliente(venda.getIdCliente());
                            String data = venda.getData();
                            int intervaloDias = Integer.valueOf(intervalo);
                            if (intervaloDias < 0) {
                                intervaloDias = intervaloDias * -1;
                            }
                            int param = (i + 1) * intervaloDias;
                            String dataFim = util.OperacoesComDatas.somarEneDiasNaDataAtual(data, param);
                            ar.setData(dataFim);
                            ar.setValor(valor);
                            ar.setNumeroParcela(i + 1);
                            AreceberDAO.criarNovoAreceber(ar);

                        }
                    } else {

                        for (int i = 0; i < venda.getParcelas(); i++) {

                            AreceberEntidade ar = new AreceberEntidade();
                            Float valor = venda.getValor() - entrada;
                            valor = valor / venda.getParcelas();
                            ar.setIdVenda(idVenda);
                            ar.setIdUsuario(venda.getIdUsuario());
                            ar.setIdCliente(venda.getIdCliente());
                            String data = venda.getData();
                            int intervaloDias = Integer.valueOf(intervalo);
                            if (intervaloDias < 0) {
                                intervaloDias = intervaloDias * -1;
                            }
                            int param = (i + 1) * intervaloDias;
                            String dataFim = util.OperacoesComDatas.somarEneDiasNaDataAtual(data, param);
                            ar.setData(dataFim);
                            ar.setValor(valor);

                            ar.setNumeroParcela(i + 1);

                            AreceberDAO.criarNovoAreceber(ar);

                        }
                    }
                } else {//coloquei este else para tratar erro de vendas 
                    for (int i = 0; i < venda.getParcelas(); i++) {

                        AreceberEntidade ar = new AreceberEntidade();
                        Float valor = venda.getValor() - entrada;
                        valor = valor / venda.getParcelas();
                        ar.setIdVenda(idVenda);
                        ar.setIdUsuario(venda.getIdUsuario());
                        ar.setIdCliente(venda.getIdCliente());
                        String data = venda.getData();
                            int intervaloDias = Integer.valueOf(intervalo);
                            if (intervaloDias < 0) {
                                intervaloDias = intervaloDias * -1;
                            }
                            int param = (i + 1) * intervaloDias;

                        String dataFim = util.OperacoesComDatas.somarEneDiasNaDataAtual(data, param);
                        ar.setData(dataFim);
                        ar.setValor(valor);

                        ar.setNumeroParcela(i + 1);

                        if (venda.getParcelas() != 0) { //INCLUI ESTE IF PARA CORRIGIR ERRO VENDA
                            AreceberDAO.criarNovoAreceber(ar);
                        }
                    }
                }//coloquei este else para tratar erro de vendas

                // GERAR RECIBO EMAIL CLIENTE E VENDEDOR
                EmailAssincrono ema = new EmailAssincrono();
                ema.start(venda);

                //DIRECIONAR PAGINA
                response.sendRedirect("venda_concluida.jsp?msg=1&idPedido=" + idPedido + "&idVenda=" + idVenda);

            } else {
                //DIRECIONAR PAGINA
                response.sendRedirect("venda_concluida.jsp?msg=4&e=" + motivoNaoPodeVender);
            }

        } catch (SQLException ex) {
            Logger.getLogger(GerarVendaDoProdutoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
