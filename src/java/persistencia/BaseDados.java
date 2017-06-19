package persistencia;

import java.sql.*;
import javax.naming.Context;

import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 *
 * @author Henrique
 */
public class BaseDados {

    private static BaseDados Instancia = null;
    private Connection Conexao = null;

    private BaseDados() throws SQLException {
        Conexao = connectDatabase();
    }

    public static BaseDados getInstancia() throws SQLException {
        if (Instancia == null) {
            Instancia = new BaseDados();
        }
        return Instancia;
    }

    private Connection connectDatabase() throws SQLException {

        Connection Con = null;

        try {

        InitialContext contexto = new InitialContext();  
        DataSource ds = (DataSource)contexto.lookup("java:/comp/env/jdbc/microvenda");  
        Con = ds.getConnection();  
        Con.setAutoCommit(true);// LINHA EXPERIMENTAL
        return Con;

        
        } catch (Exception e) {
            
            e.printStackTrace();
        } finally {
             

            return Con;
            
        }
    }

    public int executeUpdate(String sql) throws SQLException {
        Statement St = getConnection().createStatement();
        return St.executeUpdate(sql);
    }

    public ResultSet executeQuery(String sql) throws SQLException {
        Statement St = getConnection().createStatement();
        return St.executeQuery(sql);
    }

    public PreparedStatement prepareStatement(String sql) throws SQLException {
        if (Conexao.isClosed() || !Conexao.isValid(0) ) {
            Conexao = connectDatabase();
            
        }
        return Conexao.prepareStatement(sql);
    }

    public Connection getConnection() throws SQLException {
        if (Conexao.isClosed() || !Conexao.isValid(0) ) {
            Conexao = connectDatabase();
            
        }
        return Conexao;
    }

}
