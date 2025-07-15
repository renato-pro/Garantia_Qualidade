
package dao;

import conexao.Conexao;
import java.sql.Connection;

/**
 *
 * @author Renato Melo - 06/Jul/25
 */
public class UsuarioDAO {
 private Conexao conexao;
    private Connection conn;

    public UsuarioDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.conectar();
    }
    
}
