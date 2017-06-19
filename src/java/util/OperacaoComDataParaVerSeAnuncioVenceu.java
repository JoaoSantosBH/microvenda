/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
//import persistencia.ParametrosDAO;

/**
 *
 * @author joaosantos
 */
public class OperacaoComDataParaVerSeAnuncioVenceu {
    
    
    public static Boolean verificarSeDataVenceAgora(String data, int max){
        Boolean vence = false;
        
        String dataAnuncioString = data;
        Calendar dataHoje = Calendar.getInstance();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");  
        String agora = s.format(dataHoje.getTime());
        
        //Converter data Anuncio
        String dataQuebrada[] = dataAnuncioString.split(" ");
        String dataSemHoras = dataQuebrada[0];

        String dataPre[] = dataSemHoras.split("-");
        String anoS = dataPre[0];
        String mesS = dataPre[1];
        String diaS = dataPre[2];

        int ano = Integer.parseInt(anoS);
        int mes = Integer.parseInt(mesS);
        int dia = Integer.parseInt(diaS);

        mes--;
        //Converter data Atual
        String dataQuebrada1[] = agora.split(" ");
        String dataSemHoras1 = dataQuebrada1[0];

        String dataPre1[] = dataSemHoras1.split("-");
        String anoS1 = dataPre1[0];
        String mesS1 = dataPre1[1];
        String diaS1 = dataPre1[2];

        int ano1 = Integer.parseInt(anoS1);
        int mes1 = Integer.parseInt(mesS1);
        int dia1 = Integer.parseInt(diaS1);

        mes1--;
        
        //comparar as datas conforme parametro

        Calendar dataVencida = new GregorianCalendar(ano, mes, dia);
        Calendar dataAnuncio = new GregorianCalendar(ano, mes, dia);
        Calendar dataAtual = new GregorianCalendar(ano1, mes1, dia1);
        dataVencida.add(Calendar.DAY_OF_YEAR, + max); // soma o valor do parametro

        System.out.println("DATA ANUNCIO ==>" + dataAnuncio.getTime());
        System.out.println("DATA VENCIDA ==>" + dataVencida.getTime());
        System.out.println("DATA ATUAL   ==>" + dataAtual.getTime());

        int vencida = dataVencida.compareTo(dataAtual); // ZERO TRUE e 1 FASLSE
        if(vencida == 0){
            vence =true;
        }

        
        return vence;
    }
    
}
