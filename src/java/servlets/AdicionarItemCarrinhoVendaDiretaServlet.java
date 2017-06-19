/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entidade.ProdutoEntidade;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import util.AdicionarItemNoCarrinho;
import util.Carrinho;

/**
 *
 * @author joaosantos
 */
public class AdicionarItemCarrinhoVendaDiretaServlet extends HttpServlet {

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
            int iqtd;
            String id = request.getParameter("id_produto");
            String qtd = request.getParameter("qtd");
            String valor = request.getParameter("preco");
            String precoCusto = request.getParameter("preco_custo");
            if (qtd.isEmpty()) {
                iqtd = -1000;
            } else {
                iqtd = Integer.valueOf(qtd);
            }

            if (qtd.equals("")) {
                response.sendRedirect("lista_orcamento_venda_direta.jsp?msg=1");
            } else if (iqtd < 0) {
                ProdutoEntidade p = persistencia.ProdutoDAO.pegarProdutoPeloId(Integer.valueOf(id));
                Carrinho car = new Carrinho();
                car.setIdProd(Integer.valueOf(id));
                car.setNome(p.getNome());
                car.setQtd(Integer.valueOf(qtd));
                car.setPrecoCusto(Float.valueOf(valor));
                car.setPrecoVenda(Float.valueOf(precoCusto));
                car.setValor(Float.valueOf(valor) * Float.valueOf(qtd));

                HttpSession session = request.getSession(true);
                ArrayList retornoLista = (ArrayList) session.getAttribute("carrinho");

                retornoLista = (ArrayList) AdicionarItemNoCarrinho.removerProdutoCarrinho(car, retornoLista);

                session.setAttribute("carrinho", retornoLista);

                response.sendRedirect("lista_orcamento_venda_direta.jsp");
            } else {
                ProdutoEntidade p = persistencia.ProdutoDAO.pegarProdutoPeloId(Integer.valueOf(id));
                Carrinho car = new Carrinho();
                car.setIdProd(Integer.valueOf(id));
                car.setNome(p.getNome());
                car.setQtd(Integer.valueOf(qtd));

                car.setValor(Float.valueOf(valor) * Float.valueOf(qtd));

                HttpSession session = request.getSession(true);
                ArrayList retornoLista = (ArrayList) session.getAttribute("carrinho");
                boolean existe = AdicionarItemNoCarrinho.verificarSeExisteItemNoCarrinho(car, retornoLista);
                if (existe) {
                    session.setAttribute("carrinho", retornoLista);

                    response.sendRedirect("lista_orcamento_venda_direta.jsp?msg=5");
                } else {
                    retornoLista = (ArrayList) AdicionarItemNoCarrinho.adicionarProdutoCarrinho(car, retornoLista);

                    session.setAttribute("carrinho", retornoLista);

                    response.sendRedirect("lista_orcamento_venda_direta.jsp");
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(AdicionarItemCarrinhoVendaDiretaServlet.class.getName()).log(Level.SEVERE, null, ex);
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
