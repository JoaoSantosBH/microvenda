/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import entidade.ConfigServidorEmailEntidade;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author joaosantos
 */
public class ConfigServidorEmailDAO {

    public static ConfigServidorEmailEntidade obterConfiguracaoServidorEmail(String id) throws SQLException {

        ConfigServidorEmailEntidade conf = new ConfigServidorEmailEntidade();
        String Query = "SELECT * FROM config_servidor_email where id_config =" + id + ";";

        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);

        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            conf.setHost(rs.getString("host"));
            conf.setPort(rs.getString("port"));
            conf.setAuth(rs.getString("auth"));
            conf.setStarttls(rs.getString("starttls"));
            conf.setUserEmail(rs.getString("user_email"));
            conf.setFromNameEmail(rs.getString("from_name_email"));
            conf.setPassword(rs.getString("password"));
            conf.setCharset(rs.getString("charset"));
            conf.setSubject(rs.getString("subject"));

        }
        pst.close();
        pst = null;
        rs.close();
        rs =null;
        return conf;
    }

}
