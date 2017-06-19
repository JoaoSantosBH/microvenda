/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import entidade.FormaPagamentoEntidade;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joaosantos
 */
public class FormaPagamentoDAO {

    public static List<FormaPagamentoEntidade> pegarFormasDePagamento() throws SQLException {
        List<FormaPagamentoEntidade> l = new ArrayList<>();
        FormaPagamentoEntidade forma = null;
        String query = "Select * FROM forma_pagamento";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(query);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            forma = new FormaPagamentoEntidade();
            forma.setIdFormaPagamento(rs.getInt("id_forma_pagamento"));
            forma.setTipoPagamento(rs.getString("tipo_pagamento"));
            l.add(forma);
        }
        pst.close();
        pst = null;
        rs.close();
        rs =null;
        return l;
    }

    public static String pegarFormaDePagamento(int idForma) throws SQLException {

        String forma = "";
        String query = "Select * FROM forma_pagamento where id_forma_pagamento =" + idForma + ";";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(query);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            forma = rs.getString("tipo_pagamento");
        }
        pst.close();
        pst = null;
        rs.close();
        rs =null;
        return forma;
    }

}
