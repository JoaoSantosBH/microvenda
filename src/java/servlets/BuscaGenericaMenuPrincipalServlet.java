/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author joaosantos
 */
public class BuscaGenericaMenuPrincipalServlet extends HttpServlet {

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
            String query = request.getParameter("tags");
            String idUser = request.getParameter("id_usu");
            Pattern p = Pattern.compile("[a-zA-Z]");
            Pattern p2 = Pattern.compile("[0-9]");
            Matcher m = p.matcher(query);
            Matcher m2 = p2.matcher(query);

            
            if (m.find()) {
                System.out.println(" LETRAS");
                //Buscar DADOS DO Cliente
                response.sendRedirect("buscar_cliente.jsp?id="+ idUser + "&q=" + query);
            } else if (m2.find()) {

                System.out.println(" NUMEROS");
//                response.sendRedirect("hist_vendas_busca_pedido_numero.jsp?num=" + query);
                response.sendRedirect("busca_pedido.jsp?id=" + idUser + "&q=" + query);
            } else {

                System.out.println("NAO LETRA NEM NUMERO ");
                response.sendRedirect("index.jsp?msg=13");
            }


        } catch (Exception e) {
            System.out.println(e);
            response.sendRedirect("index.jsp?msg=14");
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
