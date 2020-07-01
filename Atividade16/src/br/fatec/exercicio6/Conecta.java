package br.fatec.exercicio6;

import java.sql.Connection;
import java.sql.DriverManager;// classe se responsabiliza pelo processo de conexao
import java.sql.SQLException;

public class Conecta {

	public static void main(String[] args) {
		Connection conexao = null;
		try {
			// registrando a classe JDBC e os prametros de conexao em tempo de
			// execucao
			Class.forName("org.apache.derby.jdbc.ClientDriver");

			String url = "jdbc:derby://localhost:1527/agenda;create=true;update=true';";
			
			String usuario = "app";
			String senha = "app";
			conexao = DriverManager.getConnection(url, usuario, senha);
			System.out.println("Conectou");
			conexao.close();
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro de conexao " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("Ocorreu um erro de noa encontrou classe " + e.getMessage());
		}
	}
}
