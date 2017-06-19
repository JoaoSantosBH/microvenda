/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import entidade.InvestimentoEntidade;
import java.sql.SQLException;
import persistencia.InvestimentoDAO;

/**
 *
 * @author joaosantos
 */
public class ControladorInvestimento {

    public static void atualizarInvestimento(InvestimentoEntidade i) throws SQLException {
    
        boolean temInvestimento = InvestimentoDAO.verificarSePossuiInvestimento(i.getIdUsuario());
        if(temInvestimento){
            InvestimentoDAO.atualizarInvestimento(i);
        }else {
            InvestimentoDAO.criarInvestimento(i);
        }                
    }
    public static InvestimentoEntidade pegarInvestimentoDoUsuario(int id) throws SQLException{
        InvestimentoEntidade i = InvestimentoDAO.pegarInvestimentoDoUsuario(id);
        return i;
    }
}
