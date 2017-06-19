/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fronteira;

/**
 *
 * @author joaosantos
 */
public class FormataDataHtml {

    public static String formatarDataParaDisplay(String data) {
        String dataFim = "";

        String dataQuebrada[] = data.split("-");
        String ano = dataQuebrada[0];
        String mes = dataQuebrada[1];
        String dia = dataQuebrada[2];
        
        dataFim = dia +"/"+ mes +"/"+ ano;

        return dataFim;
    }

}
