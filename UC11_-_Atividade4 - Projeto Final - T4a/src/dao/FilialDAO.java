
package dao;

import beans.Filial;
import beans.Produto;
import java.sql.Connection;
import conexao.Conexao;
import java.util.List;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Renato Melo - 02/Jul/26
 */

public class FilialDAO {
    private Conexao conexao;
    private Connection conn;

    public FilialDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.conectar();
    }

    public void inserir(Filial filial) {
        String sql = "INSERT INTO filial(nome_filial) VALUES "
                + "(?)";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, filial.getNome_filial());
            
            
            stmt.execute();

        } catch (Exception e) {
            System.out.println("Erro ao inserir a Filial: " + e.getMessage());
        }
    }
    
    public Filial getFilial (int id){
      String sql = "SELECT * FROM filial WHERE id = ?";
      try {
                  
          PreparedStatement stmt = this.conn.prepareStatement(sql);
          stmt.setInt(1, id);
          ResultSet rs = stmt.executeQuery();
        
          Filial filial = new Filial();
          
          rs.next(); 
          filial.setId(id);
          filial.setNome_filial(rs.getString("nome_filial"));
                    
          return filial;
          
          //tratando o erro, caso ele ocorra
      } catch (Exception e) {
          System.out.println("erro: " + e.getMessage());
          return null;
      }
  }
    
     public void editar (Filial filial){
                //string sql com o código de update para o banco de dados
                String sql = "UPDATE filial SET nome_filial=? WHERE id=?";
                
                try {
                    //esse trecho é igual ao método inserir
                    PreparedStatement stmt = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE);
                    //Setando os parâmetros
                    stmt.setString(1, filial.getNome_filial());
                                        
                    stmt.setInt(2, filial.getId());
                    //Executando a query
                    stmt.execute();
                    //tratando o erro, caso ele ocorra
                } catch (Exception e) {
                    System.out.println("Erro ao editar o Produto: " + e.getMessage());
                }
            }
     
     
     public Filial getFilialNome_filial (String nome_filial){
                String sql = "SELECT * FROM filial WHERE nome_filial LIKE ?";
                try {        
                    PreparedStatement stmt = this.conn.prepareStatement(sql);
                  
                    stmt.setString(1, nome_filial);
                
                    ResultSet rs = stmt.executeQuery();
              
                    Filial filial = new Filial();
                    rs.next(); 
                    
                    filial.setId(rs.getInt("id"));
                    filial.setNome_filial(rs.getString("nome_filial"));
		    
                  
                    return filial;
                  
                  //tratando o erro, caso ele ocorra
                } catch (Exception e) {
                    System.out.println("erro: " + e.getMessage());
                    return null;
                }
            }
     
      public void excluir (int id){
                
                String sql = "DELETE FROM filial WHERE id = ?";
                try {
                    //esse trecho é igual ao método editar e inserir
                    PreparedStatement stmt = this.conn.prepareStatement(sql);
                    stmt.setInt(1, id);
                    
                    //Executando a query
                    stmt.execute();
                    //tratando o erro, caso ele ocorra
                } catch (Exception e) {
                    System.out.println("Erro ao excluir a Filial: " + e.getMessage());
                }
                
            }

      public List<Filial> getFilial(String nome_filial) { //parâmetro para buscar a filial pelo nome
                String sql = "SELECT * FROM filial WHERE nome_filial LIKE ?"; //LIKE nos permite pesquisar por partes de um nome, ao invés de pesquisarmos por todo nome
                
                try {
                    PreparedStatement stmt = this.conn.prepareStatement(sql);
                    //Como temos um parâmetro, devemos defini-lo
                    stmt.setString(1,"%" + nome_filial + "%"); //Conforme for a palavra ou letra digitada para pesquisa, será buscada antes, no meio e no fim
                    //Método para poder executar o SELECT.
                    //Os resultados obtivos pela consulta serão armazenados na variavel ResultSet
                    ResultSet rs = stmt.executeQuery();            
                    
                    //Vamos criar um objeto do tipo List
                    //Faça a importação do ArrayList
                    List<Filial> listaFiliais = new ArrayList<>();
                    //percorrer o resultSet e salvar as informações dentro de uma variável "Produto"
                    //Depois salva esse objeto dentro da lista
                    
                    //Estrutura de repetição While
                    while (rs.next()) { //.next retorna verdadeiro caso exista uma próxima posição dentro do array
                    Filial filial = new Filial();
                    //Salvar dentro do objeto empresa as informações            
                    
		    filial.setId(rs.getInt("id"));
                    filial.setNome_filial(rs.getString("nome_filial"));
		    
		    //Adicionando os elementos na lista criada
                    listaFiliais.add(filial);
                            
                    }
                    //Após finalizar o while, o retorno será a listaEmpresas, onde cada posição é um registro do banco de dados
                    return listaFiliais;
                    
                    //Se o método entrar no "Catch" quer dizer que não encontrou nenhuma empresa, então damos um "return null"
                } catch (Exception e) {
                    return null;
                }
            }
}