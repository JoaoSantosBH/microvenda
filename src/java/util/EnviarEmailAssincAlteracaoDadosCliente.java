/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import entidade.ClienteEntidade;
import entidade.UsuarioEntidade;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.ClienteDAO;
import persistencia.UsuarioDAO;

/**
 *
 * @author joaosantos
 */
public class EnviarEmailAssincAlteracaoDadosCliente {
    private final Timer timer = new Timer();
        public void start(final String idU, final String idCl) {

        int delay = 5000;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    
                    UsuarioEntidade u = UsuarioDAO.pegarUsuarioPorId(idU);
                    ClienteEntidade c = ClienteDAO.pegarClientePeloId(Integer.valueOf(idCl));
                    EnviarEmail env = new EnviarEmail();
                    env.enviarEmailEdicaoCadastroDeCliente(u,c);
                    
                    //Stop Timer.
                    Thread.currentThread().stop();
                } catch (SQLException ex) {
                    Logger.getLogger(EnviarEmailAssincAlteracaoDadosCliente.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }, delay);

    }
}
