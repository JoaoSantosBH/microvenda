/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entidade.AcessoEntidade;
import entidade.ClienteEntidade;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import persistencia.AcessoDAO;
import persistencia.ClienteDAO;

/**
 *
 * @author joaosantos
 */
public class ClienteLoginServlet extends HttpServlet {

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
            String login = request.getParameter("txt_email");
            String senha = request.getParameter("txt_password");
            String ip = request.getRemoteAddr();

            ClienteEntidade ClienteAutenticado;
            ClienteAutenticado = ClienteDAO.logar(login, senha);

            if (ClienteAutenticado != null && ClienteAutenticado.getValido() != 0) {
                Calendar dataHoje = Calendar.getInstance();
                
                SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
                String agora = s.format(dataHoje.getTime());
                
                AcessoEntidade a = new AcessoEntidade();
                a.setAcessoAtual(agora);
                a.setIdUsuario(ClienteAutenticado.getIdCliente());
                a.setIpAcesso(ip);
                AcessoDAO.realizarRodizioDeDatasDeAcesso(a);


                HttpSession Sessao = request.getSession();
                Sessao.setAttribute("CLienteLogado", ClienteAutenticado);
                response.sendRedirect("/bootstrap/pages/cliente/indexc.jsp");

            } else {
                //Login ou senha Incorretos
                if (ClienteAutenticado != null && ClienteAutenticado.getValido() == 0) {
                    RequestDispatcher Rd = request.getRequestDispatcher("/bootstrap/pages/cliente/login.jsp?e=3");
                    Rd.include(request, response);
                }
                if (ClienteAutenticado == null) {
                    RequestDispatcher Rd = request.getRequestDispatcher("/bootstrap/pages/cliente/login.jsp?e=1");
                    Rd.include(request, response);
                }

                //out.println("<h2> login ou senha Incorretos</h2>");
            }
        } catch (SQLException e) {
            System.out.println(e);
            response.sendRedirect("/bootstrap/pages/cliente/login.jsp?e=2");

        } catch (Exception e) {
            System.out.println(e);
             e.printStackTrace();
            HttpSession Sessao = request.getSession();
            Sessao.invalidate();
            response.sendRedirect("/bootstrap/pages/cliente/erro.jsp");

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
