
package beans;

/**
 * @author Renato Melo - 02/Jul/25
 */
public class Produto {
    
    private int id;
    private String codigo_interno;
    private String descricao;
    private String cod_fabricante;
    private int fabricante_id;
    private int prazogarantia;
    private String observacoes;

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getCodigo_interno() {
	return codigo_interno;
    }

    public void setCodigo_interno(String codigo_interno) {
	this.codigo_interno = codigo_interno;
    }

    public String getDescricao() {
	return descricao;
    }

    public void setDescricao(String descricao) {
	this.descricao = descricao;
    }

    public String getCod_fabricante() {
	return cod_fabricante;
    }

    public void setCod_fabricante(String cod_fabricante) {
	this.cod_fabricante = cod_fabricante;
    }

    public int getFabricante_id() {
	return fabricante_id;
    }

    public void setFabricante_id(int fabricante_id) {
	this.fabricante_id = fabricante_id;
    }

    public int getPrazogarantia() {
	return prazogarantia;
    }

    public void setPrazogarantia(int prazogarantia) {
	this.prazogarantia = prazogarantia;
    }

    public String getObservacoes() {
	return observacoes;
    }

    public void setObservacoes(String observacoes) {
	this.observacoes = observacoes;
    }

}
