
package dao;

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

public class ProdutoDAO {
    private Conexao conexao;
    private Connection conn;

    public ProdutoDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.conectar();
    }

    public void inserir(Produto produto) {
        String sql = "INSERT INTO produto(codigo_interno, descricao, cod_fabricante, fabricante_id, prazogarantia, observacoes) VALUES "
                + "(?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, produto.getCodigo_interno());
            stmt.setString(2, produto.getDescricao());
            stmt.setString(3, produto.getCod_fabricante());
            stmt.setInt(4, produto.getFabricante_id());
            stmt.setInt(5, produto.getPrazogarantia());
            stmt.setString(6, produto.getObservacoes());
            
            stmt.execute();

        } catch (Exception e) {
            System.out.println("Erro ao inserir o Produto: " + e.getMessage());
        }
    }
    
    public Produto getProduto (int id){
      String sql = "SELECT * FROM produto WHERE id = ?";
      try {
                  
          PreparedStatement stmt = this.conn.prepareStatement(sql);
          stmt.setInt(1, id);
          ResultSet rs = stmt.executeQuery();
        
          Produto produto = new Produto();
          
          rs.next(); 
          produto.setId(id);
          produto.setCodigo_interno(rs.getString("codigo_interno"));
          produto.setDescricao(rs.getString("descricao")); 
          produto.setCod_fabricante(rs.getString("cod_fabricante"));
          produto.setFabricante_id(rs.getInt("fabricante_id"));
          produto.setPrazogarantia(rs.getInt("prazogarantia"));
          produto.setObservacoes(rs.getString("observacoes"));
          
          return produto;
          
          //tratando o erro, caso ele ocorra
      } catch (Exception e) {
          System.out.println("erro: " + e.getMessage());
          return null;
      }
  }
    
     public void editar (Produto produto){
                //string sql com o código de update para o banco de dados
                String sql = "UPDATE produto SET codigo_interno=?, descricao=?, cod_fabricante=?, fabricante_id=?, prazogarantia=?, observacoes=? WHERE id=?";
                
                try {
                    //esse trecho é igual ao método inserir
                    PreparedStatement stmt = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE);
                    //Setando os parâmetros
                    stmt.setString(1, produto.getCodigo_interno());
                    stmt.setString(2, produto.getDescricao());
                    stmt.setString(3, produto.getCod_fabricante());
                    stmt.setInt(4, produto.getFabricante_id());
                    stmt.setInt(5, produto.getPrazogarantia());
                    stmt.setString(6, produto.getObservacoes());
                    
                    stmt.setInt(7, produto.getId());
                    //Executando a query
                    stmt.execute();
                    //tratando o erro, caso ele ocorra
                } catch (Exception e) {
                    System.out.println("Erro ao editar o Produto: " + e.getMessage());
                }
            }
     
     
     public Produto getProdutoCodigo_Interno (String codigo_interno){
                String sql = "SELECT * FROM produto WHERE codigo_interno LIKE ?";
                try {        
                    PreparedStatement stmt = this.conn.prepareStatement(sql);
                  
                    stmt.setString(1, codigo_interno);
                
                    ResultSet rs = stmt.executeQuery();
              
                    Produto produto = new Produto();
                    rs.next(); 
                    
                    produto.setId(rs.getInt("id"));
                    produto.setCodigo_interno(rs.getString("codigo_interno"));
		    produto.setDescricao(rs.getString("descricao")); 
		    produto.setCod_fabricante(rs.getString("cod_fabricante"));
		    produto.setFabricante_id(rs.getInt("fabricante_id"));
		    produto.setPrazogarantia(rs.getInt("prazogarantia"));
		    produto.setObservacoes(rs.getString("observacoes"));
                  
                    return produto;
                  
                  //tratando o erro, caso ele ocorra
                } catch (Exception e) {
                    System.out.println("erro: " + e.getMessage());
                    return null;
                }
            }
     
      public void excluir (int id){
                
                String sql = "DELETE FROM produto WHERE id = ?";
                try {
                    //esse trecho é igual ao método editar e inserir
                    PreparedStatement stmt = this.conn.prepareStatement(sql);
                    stmt.setInt(1, id);
                    
                    //Executando a query
                    stmt.execute();
                    //tratando o erro, caso ele ocorra
                } catch (Exception e) {
                    System.out.println("Erro ao excluir o Produto: " + e.getMessage());
                }
                
            }

      public List<Produto> getProduto(String codigo_interno) { //parâmetro para buscar o cliente pelo nome
                String sql = "SELECT * FROM produto WHERE codigo_interno LIKE ?"; //LIKE nos permite pesquisar por partes de um nome, ao invés de pesquisarmos por todo nome
                
                try {
                    PreparedStatement stmt = this.conn.prepareStatement(sql);
                    //Como temos um parâmetro, devemos defini-lo
                    stmt.setString(1,"%" + codigo_interno + "%"); //Conforme for a palavra ou letra digitada para pesquisa, será buscada antes, no meio e no fim
                    //Método para poder executar o SELECT.
                    //Os resultados obtivos pela consulta serão armazenados na variavel ResultSet
                    ResultSet rs = stmt.executeQuery();            
                    
                    //Vamos criar um objeto do tipo List
                    //Faça a importação do ArrayList
                    List<Produto> listaProdutos = new ArrayList<>();
                    //percorrer o resultSet e salvar as informações dentro de uma variável "Produto"
                    //Depois salva esse objeto dentro da lista
                    
                    //Estrutura de repetição While
                    while (rs.next()) { //.next retorna verdadeiro caso exista uma próxima posição dentro do array
                    Produto produto = new Produto();
                    //Salvar dentro do objeto empresa as informações            
                    
		    produto.setId(rs.getInt("id"));
                    produto.setCodigo_interno(rs.getString("codigo_interno"));
		    produto.setDescricao(rs.getString("descricao")); 
		    produto.setCod_fabricante(rs.getString("cod_fabricante"));
		    produto.setFabricante_id(rs.getInt("fabricante_id"));
		    produto.setPrazogarantia(rs.getInt("prazogarantia"));
		    produto.setObservacoes(rs.getString("observacoes"));
		    //Adicionando os elementos na lista criada
                    listaProdutos.add(produto);
                            
                    }
                    //Após finalizar o while, o retorno será a listaEmpresas, onde cada posição é um registro do banco de dados
                    return listaProdutos;
                    
                    //Se o método entrar no "Catch" quer dizer que não encontrou nenhuma empresa, então damos um "return null"
                } catch (Exception e) {
                    return null;
                }
            }
}