/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import entidade.InvestimentoEntidade;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author joaosantos
 */
public class InvestimentoDAO {
    
    public static void criarInvestimento(InvestimentoEntidade i ) throws SQLException{
        String query = "INSERT into investimento (id_usuario, valor_capital, valor_inventario) values (?,?,?);";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(query);
        pst.setInt(1, i.getIdUsuario());
        pst.setFloat(2, i.getValorCapital());
        pst.setFloat(3, i.getValorInventario());
        pst.executeUpdate();
        pst.close();
        pst = null;                
    }

    public static boolean verificarSePossuiInvestimento (int i ) throws SQLException{
        boolean existe = false;
        String query = "SELECT * FROM investimento WHERE id_usuario = ?;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(query);
        pst.setInt(1, i);
        ResultSet rs = pst.executeQuery();
        if(rs.next()){
            existe = true;
        }
        pst.close();
        pst=null;
        rs.close();
        rs = null;
        return existe;
    }
    
    public static void atualizarInvestimento(InvestimentoEntidade i) throws SQLException{
        String q = "UPDATE investimento set valor_capital =? , valor_inventario =? WHERE id_usuario = ?;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(q);
        pst.setFloat(1, i.getValorCapital());
        pst.setFloat(2, i.getValorInventario());
        pst.setInt(3, i.getIdUsuario());
        pst.executeUpdate();
        pst.close();
        pst = null;
    }
    
    public static void excluirInvestimento(int idUsuario) throws SQLException{
        String q ="DELETE FROM investimento Where id_usuario = ?;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(q);
        pst.setInt(1, idUsuario);
        pst.executeUpdate();
        pst.close();
        pst = null;
    }

    public static InvestimentoEntidade pegarInvestimentoDoUsuario(int id) throws SQLException{
        InvestimentoEntidade i = new InvestimentoEntidade();
        i.setValorCapital(0f);
        i.setValorInventario(0f);
        String q = "SELECT * FROM investimento WHERE id_usuario = ?;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(q);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        if(rs.next()){
//            i = new InvestimentoEntidade();
            i.setIdInvestimento(rs.getInt("id_investimento"));
            i.setIdUsuario(rs.getInt("id_usuario"));
            i.setValorCapital(rs.getFloat("valor_capital"));
            i.setValorInventario(rs.getFloat("valor_inventario"));
        }
        return i;
    }
}
