/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author joaosantos
 */
public class GerarNumeroPedido {

    public static String geraNumeroPedido() {

        Calendar c = Calendar.getInstance();

        Calendar dataHoje = Calendar.getInstance();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        String agora = s.format(dataHoje.getTime());

        //Converter data Anuncio
        String dataQuebrada[] = agora.split(" ");
        String dataSemHoras = dataQuebrada[0];

        String dataPre[] = dataSemHoras.split("-");
        String anoS = dataPre[0];
        String mesS = dataPre[1];
        String diaS = dataPre[2];
        
        String fim = anoS + mesS + diaS;
        
        return fim;
    }

}
