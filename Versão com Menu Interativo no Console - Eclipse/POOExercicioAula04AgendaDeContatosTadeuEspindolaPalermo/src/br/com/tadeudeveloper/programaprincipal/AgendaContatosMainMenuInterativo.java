package br.com.tadeudeveloper.programaprincipal;
// © 2017 Tadeu Espíndola Palermo | Todos os direitos reservados
import java.util.List;
import java.util.Scanner;

import br.com.tadeudeveloper.dao.ContatoDAO;
import br.com.tadeudeveloper.javabeans.Contato;

public class AgendaContatosMainMenuInterativo {

	public static void main(String[] args) {	
				
		System.out.println("...............................................................");
		System.out.println("......... Exercício da Aula 04 - Banco de Dados SQLite ........");
		System.out.println("...............................................................");		
	        System.out.println("                   Professional Agenda v1.0                    ");
	        System.out.println("...............................................................");		 
		System.out.println("           Disciplina: Programação Orientada à Objetos"         );
		System.out.println("            Instituição: Faculdade JK - Santa Maria"            );
		System.out.println("             Período: 4. Semestre - Turma: SIST4A"              );
		System.out.println("              Professor: Gabriel Miranda Carvalho"              );		
		System.out.println("                Aluno: Tadeu Espíndola Palermo"                 );		
		System.out.println("...............................................................\n");
		 
		System.out.println("***************************************************************");
		System.out.println("   Bem-vindo à Agenda de Contatos Professional Agenda v1.0!"    );
		System.out.println("***************************************************************\n");
		System.out.println("*********************** MENU DE OPÇÕES ************************\n");
		System.out.println("               1 - Inserir um novo contato.                    ");
		System.out.println("               2 - Listar todos os contatos.                   ");
		System.out.println("               3 - Listar todos contatos por letra inicial.    ");
		System.out.println("               4 - Listar contatos por nome informado.         "); 
		System.out.println("               5 - Remover contato buscado.                    ");
		System.out.println("               6 - SAIR.\n                                     ");
		System.out.println("***************************************************************\n");
		
		menu();		
	}
	
	public static void menu() {	
		
		try {					
			System.out.print("Digite uma das opção do menu: ");
			Scanner teclado = new Scanner(System.in);
			 
			int opcao = teclado.nextInt();	 
			 
			ContatoDAO dao;
			Contato contato;
			dao = new ContatoDAO();
			contato = new Contato();
			List<Contato> contatos = dao.listar();					
			
			switch (opcao) {		
				
			 	case 1: // 1 - Inserir um novo contato	
			 		contato = new Contato(); 	
			 		System.out.println();	
			 		System.out.println("*****************************");
			 		System.out.println("***Inserir um novo contato***");
			 		System.out.println("*****************************\n");
			 		
					System.out.print("Digite o nome: ");
					String nome = teclado.next();				
					
					try {
						System.out.print("Digite o telefone (até 10 caracteres): ");
						int telefone = teclado.nextInt();
						Integer.toString(telefone);
						contato.setTelefone(telefone);
					} catch (Exception e) {
						System.out.println();
						System.out.println("Você não digitou um número de telefone válido!!!");
						System.out.println();
						menu();
					}											
					
					System.out.print("Digite o e-mail: ");
					String email = teclado.next();						
					
					contato.setNome(nome);					
					contato.setEmail(email);
					
					dao = new ContatoDAO();		
					dao.adicionar(contato);		
					 
					menu();
					break;
					
			 	case 2: // 2 - Listar todos os contatos.	
			 		
			 		System.out.println();	
			 		System.out.println("*******************************");
			 		System.out.println("***Listar todos os  contatos***");
			 		System.out.println("*******************************");
			 		
			 		if (!contatos.isEmpty()) {
			 			System.out.println();
						System.out.println("Listando todos os contatos da agenda...");
						System.out.println();
						for	(Contato c : contatos) {
							System.out.println("Nome: " + c.getNome());
							System.out.println("Telefone: " + c.getTelefone());
							System.out.println("E-mail: " + c.getEmail());	
							System.out.println("--------------------");
							System.out.println();
						}		 			
			 		} else {
			 			System.out.println();
			 			System.out.println("A agenda está vazia! Não há contatos para listar!\n");
			 		}		 		
					
					menu();
					break;		
					
			 	case 3: // 3 - Listar todos contatos por letra inicial.	 	 	
			 		
			 		System.out.println();	
			 		System.out.println("*********************************************");
			 		System.out.println("***Listar todos contatos por letra inicial***");
			 		System.out.println("*********************************************");
					
			 		if (!contatos.isEmpty()) {		 			
			 			System.out.println();
				 		System.out.print("Digite uma letra para pesquisar: ");		 		
				 		String letra = teclado.next();
				 		
				 		if (letra.length() > 1) {
				 			System.out.println();
				 			System.out.println("Entrada inválida! Você deve digitar apenas 1 caractere!");
				 			System.out.println();
				 			menu();
				 		} else {
				 			List<Contato> contatoLetra = dao.buscarPorLetra(letra);
					 		System.out.println();
					 		
				 			if (!contatoLetra.isEmpty()) {
								System.out.println("Listando todos os contatos que iniciam com a letra " + letra.toUpperCase() + "\n");
								
								for	(Contato c : contatoLetra) {
									System.out.println("Nome: " + c.getNome());
									System.out.println("Telefone: " + c.getTelefone());
									System.out.println("E-mail: " + c.getEmail());	
									System.out.println("--------------------");
									System.out.println();
								}								
														
							} else {						
								System.out.println("Não existe nenhum contato que inicia com a letra ou caractere informado!");
								System.out.println();
							}
				 		} 								
						
				 		} else {	
				 			System.out.println();
				 			System.out.println("A agenda está vazia! Não há contatos para listar!\n");
				 		}		
			 		
				 		menu();
						break;					 		
				
			 	case 4: // 4 - Listar contato por nome informado.	 
			 		
			 		System.out.println("****************************************");
			 		System.out.println("***Listar contatos por nome informado***");
			 		System.out.println("****************************************");
					
			 		if (!contatos.isEmpty()) {		 			
			 			System.out.println();
				 		System.out.print("Digite um nome para pesquisar: ");		 		
				 		String nomePesquisar = teclado.next();
				 		
				 		List<Contato> contatoNome = dao.buscarPorNome(nomePesquisar);
				 		System.out.println();
				 		
			 			if (!contatoNome.isEmpty()) {
							System.out.println("Listando contatos com o nome: " + nomePesquisar + "\n");
							
							for	(Contato c : contatoNome) {
								System.out.println("Nome: " + c.getNome());
								System.out.println("Telefone: " + c.getTelefone());
								System.out.println("E-mail: " + c.getEmail());	
								System.out.println("--------------------");
								System.out.println();
							}									
						} else {
							System.out.println("Não existe nenhum contato com o nome informado!");
							System.out.println();
						}
			 		} else {
			 			System.out.println();
			 			System.out.println("A agenda está vazia! Não há contatos para listar!\n");
			 		}				
					
					menu();
					break;
					
			 	case 5: // 5 - Remover contato buscado.	 	
			 		
			 		System.out.println("*****************************");
			 		System.out.println("***Remover contato buscado***");
			 		System.out.println("*****************************");
			 		
			 		if (!contatos.isEmpty()) {
			 			List<Contato> contatosRemover = dao.listar();
				 		System.out.println();
						System.out.println("Listando todos os contatos...\n");				
						for	(Contato c : contatosRemover) {	
							int index = contatosRemover.indexOf(c);						
							System.out.println("Contato: " + index);
							System.out.println("Nome:    " + c.getNome());											
							System.out.println("--------------------");
							System.out.println();
						}					
						
						System.out.print("Digite o número do contato que deseja remover: ");		 		
				 		int removerContato = teclado.nextInt();		 			 		
				 		Contato remover = dao.remover(contatosRemover.remove(removerContato));
				 		System.out.println();
						
						if (remover != null) {								
							System.out.println("Atualizando a lista de contatos...\n");				
							for	(Contato c : contatosRemover) {					
								System.out.println("Nome: " + c.getNome());	
								System.out.println("Telefone: " + c.getTelefone());
								System.out.println("E-mail: " + c.getEmail());
								System.out.println("--------------------");
								System.out.println();
							}	
							
						} else {					
							System.out.println("O contato informado não existe!!!");
						}
						
			 		} else {
			 			System.out.println();
			 			System.out.println("A agenda está vazia! Não há contatos para remover!\n");
			 		}
			 		
					menu();
					break;
					
			 	case 6: // 6 - SAIR.			 		
			 		System.out.println();
			 		System.out.println("Saindo do programa! Obrigado por utilizar!!! Até a próxima!\n");
			 		System.out.println("© 2017 Tadeu Espíndola Palermo | Todos os direitos reservados·");
			 		System.exit(0);
			 		
			 	default: // Default
			 		
			 		System.out.println();
			 		System.out.println("Você digitou uma opção inválida!!!");
			 		System.out.println();
			 		menu();
			 		
            teclado.close();	
			}         
		 
		} catch (Exception e) {
			 	System.out.println();
			 	System.out.println("Você digitou uma opção ou valor inválido!!!");
				System.out.println();
				menu();
			}			 	 
	}	
}
