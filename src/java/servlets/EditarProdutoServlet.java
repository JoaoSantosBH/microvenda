/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entidade.ProdutoEntidade;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import persistencia.ProdutoDAO;

/**
 *
 * @author joaosantos
 */
public class EditarProdutoServlet extends HttpServlet {

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
            String idProd = request.getParameter("id_p");
            String nome = request.getParameter("txt_nome_prod");
            String desc = request.getParameter("txt_descricao");
            String valor = request.getParameter("txt_valor");
            String precoVenda = request.getParameter("txt_valor2");
            String idLi = request.getParameter("linhas");
            String idUs = request.getParameter("id_usuario");
            
            ProdutoEntidade p = new ProdutoEntidade();
            p.setIdProduto(Integer.valueOf(idProd));
            p.setIdLinha(Integer.valueOf(idLi));
            p.setIdUsuario(Integer.valueOf(idUs));
            p.setNome(nome);
            p.setDescricao(desc);
            p.setPrecoCompra(Float.valueOf(valor));
            p.setPrecoVenda(Float.valueOf(precoVenda));
            ProdutoDAO.editarProdutoCadastrado(p);
            response.sendRedirect("edita_produto.jsp?idProd=" + idProd + "&msg=1");

        } catch (Exception e) {
            System.out.println(e);
            response.sendRedirect("edita_produto.jsp?msg=4");
        } finally {
            out.close();
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
