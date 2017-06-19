/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.cliente;

import controle.ControladorCliente;
import controle.ControladorUsuario;
import entidade.ClienteEntidade;
import entidade.UsuarioEntidade;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.EnviarEmail;

/**
 *
 * @author joaosantos
 */
public class EnviarEmailClienteParaVendedorEspecificoServlet extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String titulo = request.getParameter("subject");
            String msg = request.getParameter("msg");
            String idCLiente = request.getParameter("id_cliente");
            ClienteEntidade c = ControladorCliente.pegarClientePeloId(Integer.valueOf(idCLiente));
            UsuarioEntidade u = ControladorUsuario.pegarUsuarioPeloSeuId(c.getIdUsuario());
            EnviarEmail env = new EnviarEmail();
            env.enviarEmailCLienteParaVendedorEspecifico(c, u, titulo, msg);
            response.sendRedirect("enviar_email_vendedor.jsp?msg=1");
        }catch (SQLException ex) {
            Logger.getLogger(EnviarEmailClienteParaVendedorEspecificoServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect("enviar_email_vendedor.jsp?msg=4&e="+ex);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(EnviarEmailClienteParaVendedorEspecificoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(EnviarEmailClienteParaVendedorEspecificoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
