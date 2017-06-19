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
import persistencia.UsuarioDAO;

/**
 *
 * @author joaosantos
 */
public class EnviarEmailAssincNovoPedidoCliente {
    private final Timer timer = new Timer();
        public void start(final String idU) {

        int delay = 5000;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    
                    UsuarioEntidade u = UsuarioDAO.pegarUsuarioPorId(idU);
                    EnviarEmail env = new EnviarEmail();
                    env.enviarEmailNovoPedidoDeCliente(u);
                    
                    //Stop Timer.
                    Thread.currentThread().stop();
                } catch (SQLException ex) {
                    Logger.getLogger(EnviarEmailAssincNovoPedidoCliente.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }, delay);

    }
}
