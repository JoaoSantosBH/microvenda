/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author joaosantos
 */
public class ControladorDeDatas {

   

    public static String pegarData() {
        
        DateFormat formatarData = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String data = formatarData.format(date);

       return data;

    }

}
