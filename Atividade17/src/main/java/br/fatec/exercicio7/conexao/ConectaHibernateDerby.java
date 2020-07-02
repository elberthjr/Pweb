package br.fatec.exercicio7.conexao;

import org.hibernate.Session;


public class ConectaHibernateDerby {
	public static void main(String[] args) {
		Session sessao = null;
		try {
			sessao = HibernateUtil.getSessionFactory().openSession();
			System.out.println("Conectou!");
		} finally {
			sessao.close();
		}
	}
}
