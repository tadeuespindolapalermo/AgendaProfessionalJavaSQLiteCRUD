package br.com.tadeudeveloper.sqliteconexaobanco;
// © 2017 Tadeu Espíndola Palermo | Todos os direitos reservados
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	/*
	 * Todos os contatos deverão ser aramazenados em um banco de dados.
	 * Banco de Dados SQLite: agenda_contato.db	  
	 */
	
	public Connection getConnection() {		
		try	{			
			return DriverManager.getConnection("jdbc:sqlite:agenda_contato.db");			
		} catch (SQLException e) {			
			throw new RuntimeException(e);
		}
	}
}