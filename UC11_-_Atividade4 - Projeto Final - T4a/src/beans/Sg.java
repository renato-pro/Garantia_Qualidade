
package beans;

/**
* @author Renato Melo - 05/Jul/25
 */
public class Sg {
   private int id;
    private String data_abert_sg;
    private int cliente_id;
    private int filial_id;
    private int produto_id;
    //private int quant;
    private String nf;
    private String data_nf;
    private String data_receb_produto;
    private String status;
    private String fase_processo;
    private String data_inicio_fase;
    private String prazo_lim_aceite;

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getData_abert_sg() {
	return data_abert_sg;
    }

    public void setData_abert_sg(String data_abert_sg) {
	this.data_abert_sg = data_abert_sg;
    }

    public int getCliente_id() {
	return cliente_id;
    }

    public void setCliente_id(int cliente_id) {
	this.cliente_id = cliente_id;
    }

    public int getFilial_id() {
	return filial_id;
    }

    public void setFilial_id(int filial_id) {
	this.filial_id = filial_id;
    }

    public int getProduto_id() {
	return produto_id;
    }

    public void setProduto_id(int produto_id) {
	this.produto_id = produto_id;
    }

    //public int getQuant() {
	//return quant;
    //}

    //public void setQuant(int quant) {
	//this.quant = quant;
    //}

    public String getNf() {
	return nf;
    }

    public void setNf(String nf) {
	this.nf = nf;
    }

    public String getData_nf() {
	return data_nf;
    }

    public void setData_nf(String data_nf) {
	this.data_nf = data_nf;
    }

    public String getData_receb_produto() {
	return data_receb_produto;
    }

    public void setData_receb_produto(String data_receb_produto) {
	this.data_receb_produto = data_receb_produto;
    }

    public String getStatus() {
	return status;
    }

    public void setStatus(String status) {
	this.status = status;
    }

    public String getFase_processo() {
	return fase_processo;
    }

    public void setFase_processo(String fase_processo) {
	this.fase_processo = fase_processo;
    }

    public String getData_inicio_fase() {
	return data_inicio_fase;
    }

    public void setData_inicio_fase(String data_inicio_fase) {
	this.data_inicio_fase = data_inicio_fase;
    }

    public String getPrazo_lim_aceite() {
	return prazo_lim_aceite;
    }

    public void setPrazo_lim_aceite(String prazo_lim_aceite) {
	this.prazo_lim_aceite = prazo_lim_aceite;
    }

    
    
}