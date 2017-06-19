/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import controle.ControladorEstoque;
import entidade.EstoqueEntidade;
import entidade.ProdutoEntidade;
import java.io.IOException;
import java.io.PrintWriter;

import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import persistencia.PersistenciaException;
import persistencia.ProdutoDAO;

/**
 *
 * @author joaosantos
 */
public class InserirProdutoNoEstoqueServlet extends HttpServlet {

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
   
            String idUser = request.getParameter("id_usuario");
            String idProd = request.getParameter("produto");
            String qtd = request.getParameter("txt_qtd");
            String limite = request.getParameter("limite_inferior");
            ProdutoEntidade p = ProdutoDAO.pegarProdutoPeloId(Integer.valueOf(idProd));
            String nome = p.getNome();
            
            EstoqueEntidade est = new EstoqueEntidade();
            est.setIdProduto(Integer.valueOf(idProd));
            est.setIdLinha(Integer.valueOf(p.getIdLinha()));
            est.setIdUsuario(Integer.valueOf(idUser));
            est.setQuantidade(Integer.valueOf(qtd));
            est.setLimiteInferior(Integer.valueOf(limite));
            est.setNome(nome);
            
            ControladorEstoque.inserirProdutoNoEstoque(est);
                response.sendRedirect("manter_estoque.jsp?msg=1");
            //Login ou senha Incorretos
//            RequestDispatcher Rd = request.getRequestDispatcher("cadastre_se.jsp?msg=5");
//            Rd.include(request, response);

        } catch (PersistenciaException e) {
            response.sendRedirect("manter_estoque.jsp?msg=2&excep=" + e.getMessage());

        } catch (SQLException e) {
            System.out.println(e);
            response.sendRedirect("manter_estoque.jsp?msg=3");
        } catch (Exception e) {
            System.out.println(e);
            response.sendRedirect("manter_estoque.jsp?msg=4");
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
