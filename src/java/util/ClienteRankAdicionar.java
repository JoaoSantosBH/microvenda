/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import util.ClienteRank;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.Carrinho;

/**
 *
 * @author joaosantos
 */
public class ClienteRankAdicionar {

    public static List<ClienteRank> adicionarProdutoCarrinho(ClienteRank c, ArrayList l) throws SQLException {
        if (l == null) {
            l = new ArrayList();
        }
        l.add(c);
        return l;
    }

    public static boolean verificarSeExisteItemNoCarrinho(ClienteRank c, ArrayList l) throws SQLException {
        boolean existe = false;
        if (l == null) {
            l = new ArrayList();
        }
        for (int i = 0; i < l.size(); i++) {
            ClienteRank crVindoLista = (ClienteRank) l.get(i);
            if (crVindoLista.getIdCliente() == c.getIdCliente()) {
                existe = true;
            }
        }
        return existe;
    }

        public static  List<ClienteRank> editarItemNoCarrinho(ClienteRank c, ArrayList l)  {      
        if (l == null) {
            l = new ArrayList();
        }
        for (int i = 0; i < l.size(); i++) {
            ClienteRank crLista = (ClienteRank) l.get(i);  
            if (crLista.getIdCliente() == c.getIdCliente()) {
                l.remove(i); 
                int valor = crLista.getTotal() + c.getTotal();
                crLista.setTotal(valor);
                l.add(i, crLista);
            }
        }
       return l;
    }

}
