
package dao;

import beans.Sg;
import java.sql.Connection;
import conexao.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Renato Melo - 05/Jul/26
 */

public class SgDAO {
    private Conexao conexao;
    private Connection conn;

    public SgDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.conectar();
    }

    public void inserir(Sg sg) {
        String sql = "INSERT INTO sg(data_abert_sg, cliente_id, filial_id, produto_id, nf, data_nf, data_receb_produto, "
		+ "status, fase_processo, data_inicio_fase, prazo_lim_aceite) VALUES " + "( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, sg.getData_abert_sg());
	    stmt.setInt(2, sg.getCliente_id());
            stmt.setInt(3, sg.getFilial_id());
	    stmt.setInt(4, sg.getProduto_id());
            //stmt.setInt(5, sg.getQuant());
	    stmt.setString(5, sg.getNf());
            stmt.setString(6, sg.getData_nf());
	    stmt.setString(7, sg.getData_receb_produto());
	    stmt.setString(8, sg.getStatus());
	    stmt.setString(9, sg.getFase_processo());
	    stmt.setString(10, sg.getData_inicio_fase());
	    stmt.setString(11, sg.getPrazo_lim_aceite());
 stmt.execute();

        } catch (Exception e) {
            System.out.println("Erro ao inserir a SG: " + e.getMessage());
        }
    }
    
    public Sg getSg (int id){
      String sql = "SELECT * FROM sg WHERE id = ?";
      try {
                  
          PreparedStatement stmt = this.conn.prepareStatement(sql);
          stmt.setInt(1, id);
          ResultSet rs = stmt.executeQuery();
        
          Sg sg = new Sg();
          
          rs.next(); 
          sg.setId(id);
          sg.setData_abert_sg(rs.getString("data_abert_sg"));
	  sg.setCliente_id(rs.getInt("cliente_id"));
	  sg.setFilial_id(rs.getInt("filial_id"));
	  sg.setProduto_id(rs.getInt("produto_id"));
	  //sg.setQuant(rs.getInt("quant"));
	  sg.setNf(rs.getString("nf"));
	  sg.setData_nf(rs.getString("data_nf"));
	  sg.setData_receb_produto(rs.getString("data_receb_produto"));
	  sg.setStatus(rs.getString("status"));
	  sg.setFase_processo(rs.getString("fase_processo"));
	  sg.setData_inicio_fase(rs.getString("data_inicio_fase"));
	  sg.setPrazo_lim_aceite(rs.getString("prazo_lim_aceite"));
	
          return sg;
          
          //tratando o erro, caso ele ocorra
      } catch (Exception e) {
          System.out.println("erro: " + e.getMessage());
          return null;
      }
  }
    
     public void editar (Sg sg){
                //string sql com o código de update para o banco de dados
                String sql = "UPDATE sg SET data_abert_sg=?, cliente_id=?, filial_id=?, produto_id=?,"
			+ "nf=?, data_nf=?, data_receb_produto=?, status=?, fase_processo=?, data_inicio_fase=?, prazo_lim_aceite=? WHERE id=?";
                
                try {
                    //esse trecho é igual ao método inserir
                    PreparedStatement stmt = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE);
                    //Setando os parâmetros
 		    stmt.setString(1, sg.getData_abert_sg());
		    stmt.setInt(2, sg.getCliente_id());
		    stmt.setInt(3, sg.getFilial_id());
		    stmt.setInt(4, sg.getProduto_id());
		    //stmt.setInt(5, sg.getQuant());
		    stmt.setString(5, sg.getNf());
		    stmt.setString(6, sg.getData_nf());
		    stmt.setString(7, sg.getData_receb_produto());
		    stmt.setString(8, sg.getStatus());
		    stmt.setString(9, sg.getFase_processo());
		    stmt.setString(10, sg.getData_inicio_fase());
		    stmt.setString(11, sg.getPrazo_lim_aceite());
 
                    stmt.setInt(12, sg.getId());
		    
                    //Executando a query
                    stmt.execute();
                    //tratando o erro, caso ele ocorra
                } catch (Exception e) {
                    System.out.println("Erro ao editar a SG: " + e.getMessage());
                }
            }
     
     
     public Sg getData_abert_sg (String data_abert_sg){
                String sql = "SELECT * FROM sg WHERE data_abert_sg LIKE ?";
                try {        
                    PreparedStatement stmt = this.conn.prepareStatement(sql);
                  
                    stmt.setString(1, data_abert_sg);
                
                    ResultSet rs = stmt.executeQuery();
              
                    Sg sg = new Sg();
                    rs.next(); 
                    
		    
		    sg.setId(rs.getInt("id"));
		    sg.setId(rs.getInt("id"));
		    sg.setData_abert_sg(rs.getString("data_abert_sg"));
		    sg.setCliente_id(rs.getInt("cliente_id"));
		    sg.setFilial_id(rs.getInt("filial_id"));
		    sg.setProduto_id(rs.getInt("produto_id"));
		    //sg.setQuant(rs.getInt("quant"));
		    sg.setNf(rs.getString("nf"));
		    sg.setData_nf(rs.getString("data_nf"));
		    sg.setData_receb_produto(rs.getString("data_receb_produto"));
		    sg.setStatus(rs.getString("status"));
		    sg.setFase_processo(rs.getString("fase_processo"));
		    sg.setData_inicio_fase(rs.getString("data_inicio_fase"));
		    sg.setPrazo_lim_aceite(rs.getString("prazo_limite_aceite"));
	  
                    return sg;
                  
                  //tratando o erro, caso ele ocorra
                } catch (Exception e) {
                    System.out.println("erro: " + e.getMessage());
                    return null;
                }
            }

     
      public void excluir (int id){
                
                String sql = "DELETE FROM sg WHERE id = ?";
                try {
                    //esse trecho é igual ao método editar e inserir
                    PreparedStatement stmt = this.conn.prepareStatement(sql);
                    stmt.setInt(1, id);
                    
                    //Executando a query
                    stmt.execute();
                    //tratando o erro, caso ele ocorra
                } catch (Exception e) {
                    System.out.println("Erro ao excluir a SG: " + e.getMessage());
                }
                
            }
/*
      public List<Sg> getProduto(String codigo_interno) { //parâmetro para buscar o cliente pelo nome
                String sql = "SELECT * FROM sg WHERE codigo_interno LIKE ?"; //LIKE nos permite pesquisar por partes de um nome, ao invés de pesquisarmos por todo nome
                
                try {
                    PreparedStatement stmt = this.conn.prepareStatement(sql);
                    //Como temos um parâmetro, devemos defini-lo
                    stmt.setString(1,"%" + codigo_interno + "%"); //Conforme for a palavra ou letra digitada para pesquisa, será buscada antes, no meio e no fim
                    //Método para poder executar o SELECT.
                    //Os resultados obtivos pela consulta serão armazenados na variavel ResultSet
                    ResultSet rs = stmt.executeQuery();            
                    
                    //Vamos criar um objeto do tipo List
                    //Faça a importação do ArrayList
                    List<Sg> listaSgs = new ArrayList<>();
                    //percorrer o resultSet e salvar as informações dentro de uma variável "Produto"
                    //Depois salva esse objeto dentro da lista
                    
                    //Estrutura de repetição While
                    while (rs.next()) { //.next retorna verdadeiro caso exista uma próxima posição dentro do array
                    Sg sg = new Sg();
                    //Salvar dentro do objeto empresa as informações            
                    
		    sg.setId(rs.getInt("id"));
		    
		    sg.setData_abert_sg(rs.getString("data_abert_sg"));
		    sg.setCliente_id(rs.getInt("cliente_id"));
		    sg.setFilial_id(rs.getInt("filial_id"));
		    sg.setProduto_id(rs.getInt("produto_id"));
		    //sg.setQuant(rs.getInt("quant"));
		    sg.setNf(rs.getString("nf"));
		    sg.setData_nf(rs.getString("data_nf"));
		    sg.setData_receb_produto(rs.getString("data_receb_produto"));
		    sg.setStatus(rs.getString("status"));
		    sg.setFase_processo(rs.getString("fase_processo"));
		    sg.setData_inicio_fase(rs.getString("data_inicio_fase"));
		    sg.setPrazo_lim_aceite(rs.getString("prazo_limite_aceite"));
		    
		    
                    
		    
		    //Adicionando os elementos na lista criada
                    listaSgs.add(sg);
                            
                    }
                    //Após finalizar o while, o retorno será a listaEmpresas, onde cada posição é um registro do banco de dados
                    return listaSgs;
                    
                    //Se o método entrar no "Catch" quer dizer que não encontrou nenhuma empresa, então damos um "return null"
                } catch (Exception e) {
                    return null;
                }
            }*/
}