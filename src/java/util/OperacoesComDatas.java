/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author joaosantos
 */
public class OperacoesComDatas {

    public static String somarEneDiasNaDataAtual(String data, int param) {
        String dataRecebida = data;
        String dataQuebrada[] = dataRecebida.split(" ");
        String dataSemHoras = dataQuebrada[0];

        String dataPre[] = dataSemHoras.split("-");
        String anoS = dataPre[0];
        String mesS = dataPre[1];
        String diaS = dataPre[2];

        int ano = Integer.parseInt(anoS);
        int mes = Integer.parseInt(mesS);
        int dia = Integer.parseInt(diaS);

        Calendar dataAtual = new GregorianCalendar(ano, mes - 1, dia);
        Calendar dataNova = new GregorianCalendar(ano, mes - 1, dia);

        dataNova.add(Calendar.DAY_OF_YEAR, +param); // soma o valor do parametro

//        System.out.println("DATA ATUAL ==>" + dataAtual.getTime());
//        System.out.println("DATA NOVA ==>" + dataNova.getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        return sdf.format(dataNova.getTime());
    }

        public static String deccrementarEneDiasNaDataAtual(String data, int param) {
        String dataRecebida = data;
        String dataQuebrada[] = dataRecebida.split(" ");
        String dataSemHoras = dataQuebrada[0];

        String dataPre[] = dataSemHoras.split("-");
        String anoS = dataPre[0];
        String mesS = dataPre[1];
        String diaS = dataPre[2];

        int ano = Integer.parseInt(anoS);
        int mes = Integer.parseInt(mesS);
        int dia = Integer.parseInt(diaS);

        Calendar dataAtual = new GregorianCalendar(ano, mes - 1, dia);
        Calendar dataNova = new GregorianCalendar(ano, mes - 1, dia);

        dataNova.add(Calendar.DAY_OF_YEAR, - param); // soma o valor do parametro

//        System.out.println("DATA ATUAL ==>" + dataAtual.getTime());
        System.out.println("DATA NOVA ==>" + dataNova.getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        return sdf.format(dataNova.getTime());
    }
    public static Boolean verificarSeParcelaAReceberEstaVencida(String dataComparar) {
        Boolean vencida = false;

        Calendar dataAtual = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dataAtualS = sdf.format(dataAtual.getTime());

        String dataAux01[] = dataAtualS.split("-");
        String ano = dataAux01[0];
        String mes = dataAux01[1];
        String dia = dataAux01[2];

        String[] dataAux02 = dataComparar.split("-");
        String ano2 = dataAux02[0];
        String mes2 = dataAux02[1];
        String dia2 = dataAux02[2];
        int diaAux = Integer.valueOf(dia) ;
        int diaAux2=Integer.valueOf(dia2);

        if (diaAux2 < diaAux) {
//            System.out.println("DIA DIFERENTE");
            int m = Integer.valueOf(mes);
            int m2 = Integer.valueOf(mes2);
            if (m2 <= m) {
//                System.out.println("Mes menor");
                int a = Integer.valueOf(ano);
                int a2 = Integer.valueOf(ano2);
                if (a >= a2) {
                    vencida = true;
                }
            }
        } else if (diaAux == diaAux2) {
            int m = Integer.valueOf(mes);
            int m2 = Integer.valueOf(mes2);
            if (m2 < m) {
//                System.out.println("Mes menor");
                int a = Integer.valueOf(ano);
                int a2 = Integer.valueOf(ano2);
                if (a >= a2) {
                    vencida = true;
                }
            }
        } else {
            int m = Integer.valueOf(mes);
            int m2 = Integer.valueOf(mes2);
            if (m2 < m) {
                int a = Integer.valueOf(ano);
                int a2 = Integer.valueOf(ano2);
                if (a >= a2) {
                    vencida = true;
                }
            }
        }
        return vencida;
    }
}
