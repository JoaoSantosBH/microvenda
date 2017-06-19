/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import controle.ControladorInvestimento;
import entidade.InvestimentoEntidade;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author joaosantos
 */
@WebServlet(name = "AtualizarInvestimentoServlet", urlPatterns = {"/bootstrap/pages/AtualizarInvestimentoServlet"})
public class AtualizarInvestimentoServlet extends HttpServlet {

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

            String valorCapital = request.getParameter("txt_capital");
            String valorInventario = request.getParameter("txt_inventario");
            String idUsuario = request.getParameter("id_usuario_txt");
            InvestimentoEntidade i = new InvestimentoEntidade();
            i.setIdUsuario(Integer.valueOf(idUsuario));
            i.setValorCapital(Float.valueOf(valorCapital));
            i.setValorInventario(Float.valueOf(valorInventario));
            ControladorInvestimento.atualizarInvestimento(i);
            response.sendRedirect("investimentos_declara.jsp?msg=1");
            
        }  catch (SQLException e) {
            System.out.println(e);
            response.sendRedirect("investimentos_declara.jsp?msg=3&e="+e);
        } catch (Exception e) {
            System.out.println(e);
            response.sendRedirect("investimentos_declara.jsp?msg=4&e="+e);
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
