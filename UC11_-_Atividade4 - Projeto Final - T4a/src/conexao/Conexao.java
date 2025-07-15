package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Renato Melo - 29/Jun/2025
 */
public class Conexao {

    public com.sun.jdi.connect.spi.Connection conectar;

    public Connection conectar() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/garantia_qualidade", "adm", "Password2025@");
            System.out.println("Conectou com o banco de dados!!!!");
        } catch (SQLException ex) {
            System.out.println("Erro: Não foi possivel se conectar no banco de dados!");
        } catch (ClassNotFoundException ex) {
            System.out.println("Erro: Driver JDBC nao encontrado!.");
        }

        return conn;
    }

    public void desconectar(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Voce se desconectou do banco de dados.");
            }
        } catch (SQLException ex) {
            System.out.println("Nao foi possivel desconectar do banco dados.");
        }
    }

    public com.sun.jdi.connect.spi.Connection conecta() {
	throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}