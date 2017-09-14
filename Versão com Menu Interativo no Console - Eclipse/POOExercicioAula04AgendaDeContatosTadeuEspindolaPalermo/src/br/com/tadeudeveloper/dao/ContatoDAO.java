package br.com.tadeudeveloper.dao;
// © 2017 Tadeu Espíndola Palermo | Todos os direitos reservados
import	java.sql.*;
import java.util.ArrayList;
import java.util.List;

import br.com.tadeudeveloper.javabeans.Contato;
import br.com.tadeudeveloper.sqliteconexaobanco.ConnectionFactory;

public class ContatoDAO {
	
	private Connection conexao;
	private void abrirConexao() {
		if (this.conexao == null) {
			this.conexao = new ConnectionFactory().getConnection();
		}
	}

	private void fecharConexao() {
		try {
			if (this.conexao != null) {
				this.conexao.close();
				this.conexao = null;
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	/*
	 * A agenda deverá ter uma funcionalidade para o usuário inserir um novo contato digitando os dados desse
	 * contato.
	 */
	
	public void adicionar(Contato contato) {
		try {
			this.abrirConexao();
			String sql = "INSERT INTO contato (nome, telefone, email) VALUES (?, ?, ?)";
			PreparedStatement stmt = this.conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);		
			stmt.setString(1, contato.getNome());
			stmt.setInt(2, contato.getTelefone());
			stmt.setString(3, contato.getEmail());				
			int adicionado = stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			int lastId = rs.getInt(1);				
			if (adicionado > 0) {
				System.out.println();
				System.out.println("Contato " + lastId + " adicionado com sucesso!");
				System.out.println("----------------------------------\n");
			}			
			//stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			this.fecharConexao();
		}
	}
	
	/*
	 * Deverá ter uma funcionalidade para trazer o contato pelo nome informado.
	 */
	
	public List<Contato> buscarPorNome(String nome) {
		List<Contato> contatos = new ArrayList<Contato>();			
		try {
			this.abrirConexao();
			String sql = "SELECT * FROM contato WHERE nome LIKE ?";
			PreparedStatement stmt = this.conexao.prepareStatement(sql);
			stmt.setString(1, nome);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Contato c = new Contato();				
				c.setNome(rs.getString("nome"));
				c.setTelefone(rs.getInt("telefone"));				
				c.setEmail(rs.getString("email"));	
				contatos.add(c);
			}
			rs.close();
			stmt.close();			
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			this.fecharConexao();
		}
		return contatos;
	}
	
	/*
	 * Deverá também pemitir listar todos os contatos que comecem com uma determinada letra informada.
	 */
	
	public List<Contato> buscarPorLetra (String letra) {
		List<Contato> contatos = new ArrayList<Contato>();		
		try {
			this.abrirConexao();
			String sql = "SELECT * FROM contato WHERE nome LIKE ?";
			PreparedStatement stmt = this.conexao.prepareStatement(sql);
			stmt.setString(1, letra + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Contato c = new Contato();				
				c.setNome(rs.getString("nome"));
				c.setTelefone(rs.getInt("telefone"));				
				c.setEmail(rs.getString("email"));	
				contatos.add(c);
			}
			rs.close();
			stmt.close();			
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			this.fecharConexao();
		}
		return contatos;
	}

	/*
	 * Ela deverá ter uma outra funcionalidade para listar todos os contatos da agenda.
	 */
	
	public List<Contato> listar() {
		List<Contato> contatos = new ArrayList<Contato>();
		try {
			this.abrirConexao();
			String sql = "SELECT * FROM contato";
			PreparedStatement stmt = conexao.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Contato c = new Contato();				
				c.setNome(rs.getString("nome"));
				c.setTelefone(rs.getInt("telefone"));				
				c.setEmail(rs.getString("email"));				
				contatos.add(c);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			this.fecharConexao();
		}
		return contatos;
	}
	
	/*
	 * Funcionalidade para remover um contato buscado (BÔNUS).
	 */
	
	public Contato remover(Contato contato) {
		try {
			this.abrirConexao();
			String sql = "DELETE FROM contato WHERE nome=?";
			PreparedStatement stmt = this.conexao.prepareStatement(sql);			
			stmt.setString(1, contato.getNome());			
			int apagado = stmt.executeUpdate();
			if (apagado > 0) {
				System.out.println();
				System.out.println("Contato " + contato.getNome() +  " removido com sucesso!");
			}			
			//stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			this.fecharConexao();
		}
		return contato;	
	}
}