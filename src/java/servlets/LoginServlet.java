package servlets;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import entidade.AcessoEntidade;
import entidade.HistoricoDeAcessoEntidade;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

import javax.servlet.http.HttpServletResponse;
import entidade.UsuarioEntidade;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import persistencia.AcessoDAO;
import persistencia.HistoricoDeAcessoDAO;
import persistencia.UsuarioDAO;

/**
 *
 * @author joaomarcelo
 */


@WebServlet(urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

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

        try {
            String login = request.getParameter("txt_email");
            String senha = request.getParameter("txt_password");
            String ip = request.getRemoteAddr();

            UsuarioEntidade Autenticado;
            Autenticado = UsuarioDAO.logar(login, senha);

            if (Autenticado != null && Autenticado.getValido() != 0) {
                Calendar dataHoje = Calendar.getInstance();
                
                SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
                String agora = s.format(dataHoje.getTime());
                
                AcessoEntidade a = new AcessoEntidade();
                a.setAcessoAtual(agora);
                a.setIdUsuario(Autenticado.getIdUsuario());
                a.setIpAcesso(ip);
                AcessoDAO.realizarRodizioDeDatasDeAcesso(a);
                HistoricoDeAcessoEntidade hist = new HistoricoDeAcessoEntidade();
                hist.setIdUsuario(Autenticado.getIdUsuario());
                hist.setIpAcesso(ip);
                hist.setNomeUsuario(Autenticado.getNome());
                SimpleDateFormat s2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
                String agora2 = s2.format(dataHoje.getTime());
                hist.setDataAcesso(agora2);
                HistoricoDeAcessoDAO.registrarLoginAcesso(hist);


                HttpSession Sessao = request.getSession();
                Sessao.setAttribute("UsuarioLogado", Autenticado);
                response.sendRedirect("/bootstrap/pages/index.jsp");

            } else {
                //Login ou senha Incorretos
                if (Autenticado != null && Autenticado.getValido() == 0) {
                    RequestDispatcher Rd = request.getRequestDispatcher("/bootstrap/pages/login.jsp?e=3");
                    Rd.include(request, response);
                }
                if (Autenticado == null) {
                    RequestDispatcher Rd = request.getRequestDispatcher("/bootstrap/pages/login.jsp?e=1");
                    Rd.include(request, response);
                }

                //out.println("<h2> login ou senha Incorretos</h2>");
            }
        } catch (SQLException e) {
            System.out.println(e);
            response.sendRedirect("login.jsp?e=2");

        } catch (Exception e) {
            System.out.println(e);
             e.printStackTrace();
            HttpSession Sessao = request.getSession();
            Sessao.invalidate();
            response.sendRedirect("erro.jsp");

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
