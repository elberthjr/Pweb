package br.fatec.exercicio7.crudannotations;

import java.sql.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


import br.fatec.exercicio7.conexao.HibernateUtil;;

public class ContatoCrudAnnotations {

	public void salvar(ContatoAnnotations contato) {
		Session sessao = null;
		Transaction transacao = null;

		try {
			sessao = HibernateUtil.getSessionFactory().openSession();
			transacao = sessao.beginTransaction();
		    //System.out.println(contato.getNome());
			sessao.save(contato);
			transacao.commit();
			} catch (HibernateException e) {
			System.out.println("Não  foi possível inserir o contato. Erro: " + e.getMessage());
		} finally {
			try {
				sessao.close();
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operação de inserção. Mensagem: " + e.getMessage());
			}
		}
	}

	public void atualizar(ContatoAnnotations contato) {
		Session sessao = null;
		Transaction transacao = null;

		try {
			sessao = HibernateUtil.getSessionFactory().openSession();
			transacao = sessao.beginTransaction();
			sessao.update(contato);
			transacao.commit();
		} catch (HibernateException e) {
			System.out.println("Não foi possível alterar o contato. Erro: " + e.getMessage());
		} finally {
			try {
				sessao.close();
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operação de atualização. Mensagem: " + e.getMessage());
			}
		}
	}

	public void excluir(ContatoAnnotations contato) {
		Session sessao = null;
		Transaction transacao = null;

		try {
			sessao = HibernateUtil.getSessionFactory().openSession();
			transacao = sessao.beginTransaction();
			sessao.delete(contato);
			transacao.commit();
		} catch (HibernateException e) {
			System.out.println("Não foi possível excluir o contato. Erro: " + e.getMessage());
		} finally {
			try {
				sessao.close();
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operação de exclusão. Mensagem: " + e.getMessage());
			}
		}
	}

	@SuppressWarnings("unchecked")
	public List<ContatoAnnotations> listar() {
		Session sessao = null;
		Transaction transacao = null;
		Query consulta = null;
		List<ContatoAnnotations> resultado = null;

		try {
			sessao = HibernateUtil.getSessionFactory().openSession();
			transacao = sessao.beginTransaction();
			consulta = sessao.createQuery("from ContatoAnnotations");
			resultado = consulta.list();
			transacao.commit();
			return resultado;
		} catch (HibernateException e) {
			System.out.println("Não foi possível selecionar contatos. Erro: " + e.getMessage());
			throw new HibernateException(e);
		} finally {
			try {
				sessao.close();
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operação de consulta. Mensagem: " + e.getMessage());
			}
		}
	}
	
	public ContatoAnnotations buscaContato(int valor) {
		ContatoAnnotations contato = null;
		Session sessao = null;
		Transaction transacao = null;
		Query consulta = null;

		try {
			sessao = HibernateUtil.getSessionFactory().openSession();
			transacao = sessao.beginTransaction();
			consulta = sessao.createQuery("from Contato where codigo = :parametro");
			consulta.setInteger("parametro", valor);
			contato = (ContatoAnnotations) consulta.uniqueResult();
			transacao.commit();
			return contato;
		} catch (HibernateException e) {
			System.out.println("Não foi possível buscar contato. Erro: " + e.getMessage());
		} finally {
			try {
				sessao.close();
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operação de buscar. Mensagem: " + e.getMessage());
			}
		}
		return contato;
	}

	public static void main(String[] args) {
		ContatoCrudAnnotations contatoCrudAnnotations = new ContatoCrudAnnotations();
		String[] nomes = {"Sol", "Lua", "Estrela"};
		String[] fones = {"1234", "567", "99999"};
		String[] emails = {"sol@gmail.com", "lua@gmail.com", "estrela@gmail.com"};
		String[] observacoes = {"obs do sol", "obs da lua", "obs da estrela"};
		ContatoAnnotations contatoAnnotations = null;
		
		for (int i = 0; i < nomes.length; i++) {
			contatoAnnotations = new ContatoAnnotations();
			contatoAnnotations.setNome(nomes[i]);
			contatoAnnotations.setTelefone(fones[i]);
			contatoAnnotations.setEmail(emails[i]);
			contatoAnnotations.setDataCadastro(new Date(System.currentTimeMillis()));
			contatoAnnotations.setObservacao(observacoes[i]);
			contatoCrudAnnotations.salvar(contatoAnnotations);
		}
		System.out.println("Total de registros cadastrados: " + contatoCrudAnnotations.listar().size());
	}
}
