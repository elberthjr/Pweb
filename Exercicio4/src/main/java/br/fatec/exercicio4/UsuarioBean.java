package br.fatec.exercicio4;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name="user")
@RequestScoped
public class UsuarioBean {
	private String nome;
	private String email;
	private String senha;
	private String confirmasenha;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getConfirmasenha() {
		return confirmasenha;
	}
	public void setConfirmasenha(String confirmasenha) {
		this.confirmasenha = confirmasenha;
	}
	
	public String operacao(){
		//executa operacao
		return "resultado"; // se desejar que a p�gina que est� sendo exibida n�o mude
		//return null
		}
		public String novo() {
		return "usuario"; // esta retornando apelido da p�gina, precisa mapear em faces-config.xml
		}//inst�ncia FacesContext representa todo a informa��o contextual associada com o
		//processamento de uma solicita��o de entrada e cria��o de uma resposta
		public String Salvar() { FacesContext context = FacesContext.getCurrentInstance();
		if (!this.senha.equalsIgnoreCase(this.confirmasenha)) {
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
		"senhas diferentes",""));
		return "usuario";
		}
		//no futuro salvara em banco de dados
		return "mostrausuario";
		} 
}
