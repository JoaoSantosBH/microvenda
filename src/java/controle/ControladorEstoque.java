/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import entidade.EstoqueEntidade;
import entidade.ItemEntidade;
import entidade.ProdutoEntidade;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import persistencia.EstoqueDAO;
import persistencia.PersistenciaException;
import persistencia.ProdutoDAO;

/**
 *
 * @author joaosantos
 */
public class ControladorEstoque {
    
    public static  void inserirProdutoNoEstoque(EstoqueEntidade e) throws SQLException, PersistenciaException{
        
        EstoqueDAO.cadastrarItemNoEstoque(e);
        
    }

    public static List<EstoqueEntidade> listarMeuEstoque(int id) throws SQLException {

        ProdutoEntidade p = null;
        List<EstoqueEntidade> ls = new ArrayList<EstoqueEntidade>();
       
        ls = EstoqueDAO.ListarEstoque(id);
        
//        for (EstoqueEntidade e : ls) {            
//            e =new EstoqueEntidade();
//            e.setIdProduto(id);
//            e.set ProdutoDAO.pegarProdutoPeloId(e.getIdProduto());
//            
//            ls.add();
//        }
        return ls;

    }

    public static void removerItemDoEstoque(String idProduto, String idUsuario) throws SQLException {
        
        EstoqueDAO.removerItemDoEstoque(idProduto,idUsuario);  
        
    }

    public static Boolean verificarSeItemTemEstoque(Integer idProduto, int qtd) throws SQLException {
        Boolean existeQuantidadeSuficiente = false;
        
        existeQuantidadeSuficiente = EstoqueDAO.existeQuantidadeSuficiente(idProduto, qtd);
                
        return existeQuantidadeSuficiente;
    }

    public static void atualizarEstoque(ItemEntidade item, int qtdAtual) throws SQLException {
        
        EstoqueDAO.atualizarItemEstoquePorVenda(item, qtdAtual);
        
        
    }
    
    public static void incrementarItemEstoquePorCancelamentoVenda(ItemEntidade item, int qtdAtual) throws SQLException {
        
        EstoqueDAO.incrementarItemEstoquePorCancelamentoVenda(item, qtdAtual);
        
        
    }

}
