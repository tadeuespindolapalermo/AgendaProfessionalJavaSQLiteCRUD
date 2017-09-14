package br.com.tadeudeveloper.agenda.sqlite;
import static br.com.tadeudeveloper.agenda.tela.TelaPrincipal.createNewDatabase;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	/*
	 * Todos os contatos deverão ser aramazenados em um banco de dados.
	 * Banco de Dados SQLite: agenda_contato.db	  
	 */
    
        private static final java.io.File DATABASE = new java.io.File(   
            System.getProperty("user.home")
            + System.getProperty("file.separator")
            + ".agendacontato"
            + System.getProperty("file.separator")
            + "contato.db"); 
	
	public static Connection getConnection() {		
		try	{			
			return DriverManager.getConnection("jdbc:sqlite:" + DATABASE.getPath());			
		} catch (SQLException e) {			
			throw new RuntimeException(e);
		}
	}
        
        public static void checkDatabase() throws Exception {
        if (!DATABASE.exists()) {
            createNewDatabase();
        }
    }
}