
package dao;

import beans.Cliente;
import java.sql.Connection;
import conexao.Conexao;
import java.util.List;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Renato Melo - 29/Jun/26
 */

public class ClienteDAO {
    private Conexao conexao;
    private Connection conn;

    public ClienteDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.conectar();
    }

    public void inserir(Cliente cliente) {
        String sql = "INSERT INTO cliente(nome, endereco, complemento, cidade, estado, contato, telefone, email) VALUES "
                + "(?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEndereco());
            stmt.setString(3, cliente.getComplemento());
            stmt.setString(4, cliente.getCidade());
            stmt.setString(5, cliente.getEstado());
            stmt.setString(6, cliente.getContato());
            stmt.setString(7, cliente.getTelefone());
            stmt.setString(8, cliente.getEmail());
            stmt.execute();

        } catch (Exception e) {
            System.out.println("Erro ao inserir o cliente: " + e.getMessage());
        }
    }
    
    public Cliente getCliente (int id){
      String sql = "SELECT * FROM cliente WHERE id = ?";
      try {
                  
          PreparedStatement stmt = this.conn.prepareStatement(sql);
          stmt.setInt(1, id);
          ResultSet rs = stmt.executeQuery();
        
          Cliente cliente = new Cliente();
          
          rs.next(); 
          cliente.setId(id);
          cliente.setNome(rs.getString("nome"));
          cliente.setEndereco(rs.getString("endereco")); 
          cliente.setComplemento(rs.getString("complemento"));
          cliente.setCidade(rs.getString("cidade"));
          cliente.setEstado(rs.getString("estado"));
          cliente.setContato(rs.getString("contato"));
          cliente.setTelefone(rs.getString("telefone"));
          cliente.setEmail(rs.getString("email"));
          
          return cliente;
          
          //tratando o erro, caso ele ocorra
      } catch (Exception e) {
          System.out.println("erro: " + e.getMessage());
          return null;
      }
  }
    
     public void editar (Cliente cliente){
                //string sql com o código de update para o banco de dados
                String sql = "UPDATE cliente SET nome=?, endereco=?, complemento=?, cidade=?, estado=?, contato=?, telefone=?, email=? WHERE id=?";
                
                try {
                    //esse trecho é igual ao método inserir
                    PreparedStatement stmt = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE);
                    //Setando os parâmetros
                    stmt.setString(1, cliente.getNome());
                    stmt.setString(2, cliente.getEndereco());
                    stmt.setString(3, cliente.getComplemento());
                    stmt.setString(4, cliente.getCidade());
                    stmt.setString(5, cliente.getEstado());
                    stmt.setString(6, cliente.getContato());
                    stmt.setString(7, cliente.getTelefone());
                    stmt.setString(8, cliente.getEmail());
                    stmt.setInt(9, cliente.getId());
                    //Executando a query
                    stmt.execute();
                    //tratando o erro, caso ele ocorra
                } catch (Exception e) {
                    System.out.println("Erro ao editar cliente: " + e.getMessage());
                }
            }
     
     
     public Cliente getClienteNome (String nome){
                String sql = "SELECT * FROM cliente WHERE nome LIKE ?";
                try {        
                    PreparedStatement stmt = this.conn.prepareStatement(sql);
                  
                    stmt.setString(1, nome);
                
                    ResultSet rs = stmt.executeQuery();
              
                    Cliente cliente = new Cliente();
                    rs.next(); 
                    
                    cliente.setId(rs.getInt("id"));
                    cliente.setNome(nome);
                    cliente.setNome(rs.getString("nome"));
                    cliente.setEndereco(rs.getString("endereco"));
                    cliente.setComplemento(rs.getString("complemento"));
                    cliente.setCidade(rs.getString("cidade"));
                    cliente.setEstado(rs.getString("estado"));
                    cliente.setContato(rs.getString("contato"));
                    cliente.setEmail(rs.getString("email"));
                  
                    return cliente;
                  
                  //tratando o erro, caso ele ocorra
                } catch (Exception e) {
                    System.out.println("erro: " + e.getMessage());
                    return null;
                }
            }
     
      public void excluir (int id){
                
                String sql = "DELETE FROM cliente WHERE id = ?";
                try {
                    //esse trecho é igual ao método editar e inserir
                    PreparedStatement stmt = this.conn.prepareStatement(sql);
                    stmt.setInt(1, id);
                    
                    //Executando a query
                    stmt.execute();
                    //tratando o erro, caso ele ocorra
                } catch (Exception e) {
                    System.out.println("Erro ao excluir cliente: " + e.getMessage());
                }
                
            }

      public List<Cliente> getCliente(String nome) { //parâmetro para buscar o cliente pelo nome
                String sql = "SELECT * FROM cliente WHERE nome LIKE ?"; //LIKE nos permite pesquisar por partes de um nome, ao invés de pesquisarmos por todo nome
                
                try {
                    PreparedStatement stmt = this.conn.prepareStatement(sql);
                    //Como temos um parâmetro, devemos defini-lo
                    stmt.setString(1,"%" + nome + "%"); //Conforme for a palavra ou letra digitada para pesquisa, será buscada antes, no meio e no fim
                    //Método para poder executar o SELECT.
                    //Os resultados obtivos pela consulta serão armazenados na variavel ResultSet
                    ResultSet rs = stmt.executeQuery();            
                    
                    //Vamos criar um objeto do tipo List
                    //Faça a importação do ArrayList
                    List<Cliente> listaClientes = new ArrayList<>();
                    //percorrer o resultSet e salvar as informações dentro de uma variável "Empresa"
                    //Depois salva esse objeto dentro da lista
                    
                    //Estrutura de repetição While
                    while (rs.next()) { //.next retorna verdadeiro caso exista uma próxima posição dentro do array
                    Cliente cliente = new Cliente();
                    //Salvar dentro do objeto empresa as informações            
                    cliente.setId(rs.getInt("id"));
                    cliente.setNome(rs.getString("nome"));
                    cliente.setEndereco(rs.getString("endereco"));
                    cliente.setComplemento(rs.getString("complemento"));
                    cliente.setCidade(rs.getString("cidade"));
                    cliente.setEstado(rs.getString("estado"));
                    cliente.setContato(rs.getString("contato"));
                    cliente.setTelefone(rs.getString("telefone"));
                    cliente.setEmail(rs.getString("email"));
                    //Adicionando os elementos na lista criada
                    listaClientes.add(cliente);
                            
                    }
                    //Após finalizar o while, o retorno será a listaEmpresas, onde cada posição é um registro do banco de dados
                    return listaClientes;
                    
                    //Se o método entrar no "Catch" quer dizer que não encontrou nenhuma empresa, então damos um "return null"
                } catch (Exception e) {
                    return null;
                }
            }
}