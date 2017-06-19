/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import entidade.ProdutoEntidade;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author joaosantos
 */
public class AdicionarItemNoCarrinho {

    public static List<Carrinho> adicionarProdutoCarrinho(Carrinho c, ArrayList l) throws SQLException {
        if (l == null) {

            l = new ArrayList();
        }

        l.add(c);

        return l;

    }

    public static boolean verificarSeExisteItemNoCarrinho(Carrinho c, ArrayList l) throws SQLException {
        boolean existe = false;
        if (l == null) {

            l = new ArrayList();
        }
        for (int i = 0; i < l.size(); i++) {
            Carrinho carro = (Carrinho) l.get(i);
            if (carro.getNome().equals(c.getNome())) {
                existe = true;
            }

        }

        return existe;

    }

    public static List<Carrinho> removerProdutoCarrinho(Carrinho c, ArrayList l) throws SQLException {
        if (l == null) {

            l = new ArrayList();
        }
        if (c.getQtd() < 0) {
            int qtd = c.getQtd();
            c.setQtd(qtd * -1);
        }
        if (c.getValor() < 0) {
            float val = c.getValor();
            c.setValor(val * -1);
        }

        for (int i = 0; i < l.size(); i++) {
            Carrinho carro = (Carrinho) l.get(i);
            if (carro.getIdProd() == c.getIdProd()) {
                l.remove(carro);
            }

        }

        return l;
    }

}
