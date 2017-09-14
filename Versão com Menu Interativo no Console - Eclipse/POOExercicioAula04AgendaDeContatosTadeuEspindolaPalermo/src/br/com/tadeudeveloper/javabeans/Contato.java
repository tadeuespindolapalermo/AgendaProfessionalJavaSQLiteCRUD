package br.com.tadeudeveloper.javabeans;
// © 2017 Tadeu Espíndola Palermo | Todos os direitos reservados
public class Contato {
	
	/* Javabeans
	 * Cada contato deverá ter um nome, telefone e email.	
	 */	
	
	private String nome;
	private int telefone;
	private String email;		
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public int getTelefone() {
		return telefone;
	}
	
	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}	
}