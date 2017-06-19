/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.sql.SQLException;
import persistencia.ItemDAO;
import persistencia.PedidoDAO;
import persistencia.ProdutoDAO;

/**
 *
 * @author joaosantos
 */
public class ControladorProduto {

    public static Boolean verificarSeExistePedidoComProdutoASerExcluido(String idProduto, String idUsusario) throws SQLException {
        Boolean existe = false;
        existe = ItemDAO.verificarSeExistePedidoComProdutoASerExcluido(idProduto, idUsusario);
        return existe;
    }

    public static Boolean verificarSeExisteProdutoCadastradoNaLinhaASerApagada(String idLinha, String idUsuario) throws SQLException {
        Boolean existe = false;
        existe = ProdutoDAO.verificarSeExisteProdutoCadastradoNaLinhaASerExcluida(idLinha, idUsuario);
        return existe;
    }

}
