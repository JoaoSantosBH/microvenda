/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import controle.ControladorCliente;
import entidade.ClienteEntidade;
import entidade.EnderecoEntidade;
import entidade.TelefoneEntidade;
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
public class CadastrarClienteServlet extends HttpServlet {

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

            ClienteEntidade c = new ClienteEntidade();
            TelefoneEntidade t = new TelefoneEntidade();
            EnderecoEntidade e = new EnderecoEntidade();

            String nome = request.getParameter("txt_nome");
            String telFixo = request.getParameter("txt_tel_fixo");
            String celular = request.getParameter("txt_cel");
            String outroTel = request.getParameter("txt_tel_outro");
            String logradouro = request.getParameter("txt_logradouro");
            String numero = request.getParameter("txt_numero");
            String complemento = request.getParameter("txt_complemento");
            String bairro = request.getParameter("txt_bairro");
            String cep = request.getParameter("txt_cep");
            String estado = request.getParameter("estados");
            String cidade = request.getParameter("cidades");
            String email = request.getParameter("txt_email");
            String senha = request.getParameter("txt_password");
            String idVendedor = request.getParameter("id_usuario");

            c.setNome(nome);
            c.setEmail(email);
            c.setSenha(senha);
            c.setTipo(Short.valueOf("0"));//usuario cliente
            c.setValido(Short.valueOf("1"));
            c.setFoto("/img/usuario_default.png");
            c.setIdUsuario(idVendedor);

//            t.setIdUsuario(c.getIdUsuario());
            t.setFixo(telFixo);
            t.setCelular(celular);
            t.setOutro(outroTel);

            e.setLogradouro(logradouro);
            e.setNumero(numero);
            e.setComplemento(complemento);
            e.setBairro(bairro);
            e.setCep(cep);
            e.setCidade(0);
            e.setEstado(Short.valueOf(estado));
            e.setCoordenada("00");

            
                ControladorCliente.inserirCliente(c, t, e);
                response.sendRedirect("cadastra_cliente.jsp?msg=1");
         

        } catch (PersistenciaException e) {
            response.sendRedirect("cadastra_cliente.jsp?msg=2&excep=" + e.getMessage());

        } catch (SQLException e) {
            System.out.println(e);
            response.sendRedirect("cadastra_cliente.jsp?msg=3");
        } catch (Exception e) {
            System.out.println(e);
            response.sendRedirect("cadastra_cliente.jsp?msg=4");
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
