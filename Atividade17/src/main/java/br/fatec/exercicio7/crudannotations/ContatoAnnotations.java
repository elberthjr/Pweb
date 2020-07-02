package br.fatec.exercicio7.crudannotations;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.sql.Date;

//entity especifica o nome da entidade por padrao nao e necessaria

@Entity  
@Table(name="contato") // especifica o nome da tabela
public class ContatoAnnotations  {
	@Id // indica que é ou faz parte da chave primaria 
	@GeneratedValue(strategy = GenerationType.IDENTITY) // autonumeracao
	@Column(name = "codigo")
	private Integer codigo;
	@Column(name = "nome", length = 50, nullable = true) // define nome, tamanho, se é  nulo, etc
	private String nome;
	@Column(name = "telefone", length = 15, nullable = true)
	private String telefone;
	@Column(name = "email", length = 50, nullable = true)
	private String email;
	@Column(name = "dt_cad", nullable = true)
	private Date dataCadastro;
	@Column(name = "obs", length = 100, nullable = true)
	private String observacao;
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
}
