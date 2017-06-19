/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import entidade.VendaEntidade;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joaosantos
 */
public class EmailAssincrono {

    private final Timer timer = new Timer();

    public void start(final VendaEntidade v) {

        int delay = 5000;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    EnviarEmail env = new EnviarEmail();
                    env.enviarEmailConfirmacaoDeVenda(v);
                    
                    //Stop Timer.
                    Thread.currentThread().stop();
                } catch (SQLException ex) {
                    Logger.getLogger(EmailAssincrono.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }, delay);

    }
}