/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.cliente;

import entidade.ItemEntidade;
import entidade.PedidoEntidade;
import entidade.ProdutoEntidade;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import persistencia.ItemDAO;
import persistencia.PedidoDAO;
import persistencia.PersistenciaException;
import persistencia.ProdutoDAO;
import util.Carrinho;
import util.EnviarEmailAssincNovoPedidoCliente;

/**
 *
 * @author joaosantos
 */
public class FecharPedidoClienteServlet extends HttpServlet {

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
            HttpSession session = request.getSession(true);
            List<Carrinho> lst = (ArrayList) session.getAttribute("carrinho");

            if (lst == null) {
                response.sendRedirect("fazer_pedido.jsp?msg=2");
            } else {

                PedidoEntidade p = new PedidoEntidade();
                ItemEntidade i = new ItemEntidade();
                String idUsuario = request.getParameter("id_usu");
                String idProduto = "";
                String idCliente = request.getParameter("txt_id_cliente");
                String numPedido = util.GerarNumeroPedido.geraNumeroPedido();
                Calendar c = Calendar.getInstance();
                numPedido += idUsuario;
                numPedido += c.get(Calendar.HOUR_OF_DAY);
                numPedido += c.get(Calendar.MINUTE);
                numPedido += c.get(Calendar.SECOND);
//            numPedido += "L";

                int totalPedido = 0;
                Calendar dataHoje = Calendar.getInstance();
                SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
                String agora = s.format(dataHoje.getTime());

                p.setIdUsuario(Integer.valueOf(idUsuario));
                p.setIdCliente(Integer.valueOf(idCliente));
                p.setData(agora);
                p.setNumeroPedido(Long.valueOf(numPedido));
                p.setStatus(Short.valueOf("1"));

                for (Carrinho car : lst) {
                    totalPedido += car.getValor();
                }

                p.setTotal(Float.valueOf(totalPedido));
                //Criar PEDIDO
                PedidoDAO.cadastrarPedido(p);

                for (Carrinho car : lst) {

                    ProdutoEntidade pr = new ProdutoEntidade();
                    pr = ProdutoDAO.pegarProdutoPeloId(car.getIdProd());
                    i.setIdCliente(p.getIdCliente());
                    i.setIdUsuario(p.getIdUsuario());
                    i.setIdProduto(car.getIdProd());
                    i.setNome(pr.getNome());
                    i.setNumeroPedido(Long.valueOf(numPedido));
                    i.setQtde(car.getQtd());
                    i.setValorVenda(pr.getPrecoVenda());
                    i.setValorVenda(pr.getPrecoVenda());
                    i.setPrecoCustoItem(pr.getPrecoCompra());
                    //Criar item do PEDIDO
                    ItemDAO.cadastrarItem(i);
                }

                session.setAttribute("carrinho", null);
                //mail assincrono
                EnviarEmailAssincNovoPedidoCliente e = new EnviarEmailAssincNovoPedidoCliente();
                e.start(idUsuario);
                response.sendRedirect("indexc.jsp");
            }

        } catch (SQLException ex) {
            Logger.getLogger(FecharPedidoClienteServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PersistenciaException ex) {
            Logger.getLogger(FecharPedidoClienteServlet.class.getName()).log(Level.SEVERE, null, ex);
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
