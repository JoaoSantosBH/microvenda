/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import controle.ControladorUsuario;
import entidade.ProdutoEntidade;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import persistencia.PersistenciaException;

/**
 *
 * @author joaosantos
 */
public class InsereNovoProdutoServlet extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        try  {
            /* TODO output your page here. You may use following sample code. */
            
            String idLin = request.getParameter("linhas");
            String idUser = request.getParameter("id_usuario");
            String nomeProd = request.getParameter("txt_nome_prod");
            String desc = request.getParameter("txt_descricao");
            String preco = request.getParameter("txt_valor");
            String precoVenda = request.getParameter("txt_valor2");
            ProdutoEntidade p = new ProdutoEntidade();
            
            p.setIdUsuario(Integer.valueOf(idUser));
            p.setIdLinha(Integer.valueOf(idLin));
            p.setNome(nomeProd);
            p.setDescricao(desc);
            p.setPrecoCompra(Float.valueOf(preco));
            p.setPrecoVenda(Float.valueOf(precoVenda));
            
            ControladorUsuario.inserirProduto(p);
            response.sendRedirect("cadastra_produto.jsp?msg=1");
            
            
        }catch (PersistenciaException e) {
            response.sendRedirect("cadastra_produto.jsp?msg=2&excep=" + e.getMessage());

        } catch (SQLException e) {
            System.out.println(e);
            response.sendRedirect("cadastra_produto.jsp?msg=3");
        } catch (Exception e) {
            System.out.println(e);
            response.sendRedirect("cadastra_produto.jsp?msg=4");
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
