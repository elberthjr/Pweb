package br.fatec.exercicio6;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class ContatoCrudJDBC {

	public void salvar(Contato contato) {
		Connection conexao = this.geraConexao();
		PreparedStatement insereSt = null;
		// o codigo é auto incremente
		String sql = "insert into contato (nome, telefone, email, dt_cad, obs) values (?, ?, ?, ?, ?)";

		try {
			insereSt = conexao.prepareStatement(sql);
			insereSt.setString(1, contato.getNome());
			insereSt.setString(2, contato.getTelefone());
			insereSt.setString(3, contato.getEmail());
			insereSt.setDate(4, contato.getDataCadastro());
			insereSt.setString(5, contato.getObservacao());
			insereSt.executeUpdate();
			conexao.close();
		} catch (SQLException e) {
			System.out.println("Erro ao incluir contato" + e.getMessage());
		}
	}

	public void atualizar(Contato contato) {
		Connection conexao = this.geraConexao();
		PreparedStatement insereSt = null;
		// o codigo nao pode ser alterado
		String sql = "update from contato (nome, telefone, email, dt_cad, obs) values (?, ?, ?, ?, ?) where codigo=?";

		try {
			insereSt = conexao.prepareStatement(sql);
			insereSt.setString(1, contato.getNome());
			insereSt.setString(2, contato.getTelefone());
			insereSt.setString(3, contato.getEmail());
			insereSt.setDate(4, contato.getDataCadastro());
			insereSt.setString(5, contato.getObservacao());
			insereSt.setInt(6, contato.getCodigo());
			insereSt.executeUpdate();
			conexao.close();
		} catch (SQLException e) {
			System.out.println("Erro ao alterar contato" + e.getMessage());
		}
	}

	public void excluir(Contato contato) {
		Connection conexao = this.geraConexao();
		PreparedStatement insereSt = null;
		// o codigo nao pode ser alterado
		String sql = "delete from contato where codigo=?";

		try {
			insereSt = conexao.prepareStatement(sql);
			insereSt.setInt(1, contato.getCodigo());
			insereSt.executeUpdate();
			conexao.close();
		} catch (SQLException e) {
			System.out.println("Erro ao excluir contato" + e.getMessage());
		}

	}

	public List<Contato> listar() {
		Connection conexao = this.geraConexao();
		List<Contato> contatos = new ArrayList<Contato>();
		Statement consulta = null;
		ResultSet resultado = null;
		Contato contato = null;
		String sql = "select * from contato";
		try {
			consulta = conexao.createStatement(); // nao é preparada (não
													// precisa de parametros)
			resultado = consulta.executeQuery(sql); // utilizado em consultas
			while (resultado.next()) {
				contato = new Contato();
				contato.setCodigo(resultado.getInt("codigo"));
				contato.setNome(resultado.getString("nome"));
				contato.setTelefone(resultado.getString("telefone"));
				contato.setEmail(resultado.getString("email"));
				contato.setDataCadastro(resultado.getDate("dt_cad"));
				contato.setObservacao(resultado.getString("obs"));
				contatos.add(contato);
			}

			conexao.close();
		} catch (SQLException e) {
			System.out.println("Erro ao listar contato " + e.getMessage());

		} finally {
			try {
				consulta.close();
				resultado.close();
				conexao.close();
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operação consulta " + e.getMessage());

			}
		}
		return contatos; // retorna contatos
	}

	public Contato buscaContato(int valor) {
		Connection conexao = this.geraConexao();
		PreparedStatement consulta = null;
		ResultSet resultado = null;
		Contato contato = null;
		String sql = "select * from contato  where codigo=?";
		try {
			consulta = conexao.prepareStatement(sql); // é preparada (precisa de
														// parametros)
			consulta.setInt(1, valor);
			resultado = consulta.executeQuery(sql); // utilizado em consultas
			while (resultado.next()) {
				contato = new Contato();
				contato.setCodigo(resultado.getInt("codigo"));
				contato.setNome(resultado.getString("nome"));
				contato.setTelefone(resultado.getString("telefone"));
				contato.setEmail(resultado.getString("email"));
				contato.setDataCadastro(resultado.getDate("codigo"));
				contato.setObservacao(resultado.getString("codigo"));
			}
			conexao.close();

		} catch (SQLException e) {
			System.out.println("Erro ao listar contato " + e.getMessage());

		} finally {
			try {
				consulta.close();
				resultado.close();
				conexao.close();
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operação consulta " + e.getMessage());
			}
		}
		return contato;
	}

	public Connection geraConexao() {
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
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro de conexao " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("Ocorreu um erro de noa encontrou classe " + e.getMessage());
		}
		return conexao;
	}

	public static void main(String[] args) {
		ContatoCrudJDBC contatoCRUDJDBC = new ContatoCrudJDBC();

		// criando um contato
		Contato beltrano = new Contato();
		beltrano.setNome("Beltrano da Silva");
		beltrano.setTelefone("1234");
		beltrano.setEmail("beltrano@gmail.com");
		beltrano.setDataCadastro(new Date(System.currentTimeMillis()));
		beltrano.setObservacao("novo contato");
		contatoCRUDJDBC.salvar(beltrano);

		// criando outro contato
		Contato fulano = new Contato();
		fulano.setNome("Fulano de Oliveira");
		fulano.setTelefone("9999");
		fulano.setEmail("fulano@gmail.com");
		fulano.setDataCadastro(new Date(System.currentTimeMillis()));
		fulano.setObservacao("outro novo contato");
		contatoCRUDJDBC.salvar(fulano);

		System.out.println("Contatos Cadastrados " + contatoCRUDJDBC.listar().size());

	}

}
