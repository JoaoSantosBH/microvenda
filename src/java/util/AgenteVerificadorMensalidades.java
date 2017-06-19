/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import controle.ControladorUsuario;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

/**
 *
 * @author joaosantos
 */

public class AgenteVerificadorMensalidades implements Job  {

    
 public void execute(JobExecutionContext context) {

     try {
         ControladorUsuario.VerificarVencimentoDeMensalidades();
     } catch (SQLException ex) {
         Logger.getLogger(AgenteVerificadorMensalidades.class.getName()).log(Level.SEVERE, null, ex);
     }

        

    }
    
   

}
