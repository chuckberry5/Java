package AulaUmBancoDeDados;

import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;

class Biblioteca {
  
  private LivroService livroService;
  
  public Biblioteca() {
    livroService = new LivroService("livro_db.txt");
  }
  
  
  public void criaMenu() throws IOException{
    Scanner entrada = new Scanner(System.in);
    String opcao, cont = "s";
    
    while (cont.equalsIgnoreCase("s")) {
      System.out.println("\t\t Sistema de Cadastro de Livros\n\n");
      
      System.out.println("1 ===> Adicionar Livro ");
      System.out.println("2 ===> Listar Livros ");	
      System.out.println("3 ===> Remover Livros ");
      System.out.println("4 ===> Buscar Livro ");
      System.out.println("5 ===> Atualizar Livro ");	        
  
      System.out.print("\n\n");
      System.out.print("Escolha: ");
      opcao = entrada.nextLine();
      
      if( opcao.equals("1") ) {
      		adicionarLivro();
      } else if( opcao.equals("2") ) {
      		listarLivros();
      } else if( opcao.equals("3") ) {
      		removerLivro();
      }	else if( opcao.equals("4") ) {
      		buscarLivro();
      }	else if( opcao.equals("5") ) {
      		atualizarLivro();
      }	
        	
      System.out.print("Quer continuar? (S/[N]) ");
      cont = entrada.nextLine();
    }
  }
  
  private void imprimirTituloTabela() {
    System.out.println(" ------------------------------------------------------------- ");
  	System.out.println("|	ID\tTítulo\tAutor\tAssunto\t|");
  	System.out.println(" ------------------------------------------------------------- ");  
  }
  
  private void imprimirRodapeTabela() {
    System.out.println("|	                                            	          |");
  	System.out.println(" ------------------------------------------------------------- ");
  }
  
  private void imprimirLivro(Livro l) {
    System.out.println("| " + l.getId() + 
    	                     "\t" + l.getTitulo() + 
    	                     "\t" + l.getAutor() +
    	                     "\t" + l.getAssunto() + "\t|");
  }
  
  private void imprimirTabela(Livro l) {
    imprimirTituloTabela();
    imprimirLivro(l);
    imprimirRodapeTabela();
  }
  
  private void imprimirTabela(ArrayList<Livro> livros) {
    imprimirTituloTabela();
    		
  	for (Livro l : livros) {
  	  imprimirLivro(l);
  	}
  	
  	imprimirRodapeTabela();
  }
  
  private void adicionarLivro() {
    Livro livro;
    Long ID;
  	String titulo, autor, assunto;
    Scanner entrada = new Scanner(System.in);
		
		System.out.print("Entre o ID do livro: ");
		ID = Long.parseLong(entrada.nextLine());
		System.out.print("Entre o título do livro: ");
		titulo = entrada.nextLine();
		System.out.print("Entre o autor do livro: ");
		autor = entrada.nextLine();
		System.out.print("Entre o assunto do livro: ");
		assunto = entrada.nextLine();   
		
		livro = new Livro(ID, titulo, autor, assunto);
		try {
		  livroService.adicionarLivro(livro);
		} catch (Exception e) {
		  System.out.println("Erro: " + e);
		}
  }
  
  private void listarLivros() {
    	ArrayList<Livro> livros = new ArrayList<>();
    	
    	try {
    	  livros = livroService.listarLivros();
    	} catch (Exception e) {
		    System.out.println("Erro: " + e);
		  }
    	
    	imprimirTabela(livros);
  }
  
  private void removerLivro() {
    Scanner entrada =  new Scanner(System.in);
  	Long ID;   
  	
  	System.out.println("\t\t Apagar Registro de Livro\n");
  	
  	System.out.print("Entre o ID do Livro: ");
  	ID =  Long.parseLong(entrada.nextLine());
  	
  	try {
  	  livroService.removerLivroPorId(ID);
  	} catch (Exception e) {
		  System.out.println("Erro: " + e);
		}
  	System.out.println("\nLivro removido!\n");
  }
  
  private void buscarLivro() {
        Long ID;
    		Scanner entrada = new Scanner(System.in);
    		
    		System.out.println("\t\t Buscar Regisro de Livro\n");
    		System.out.print("Entre o ID do Livro: ");
    		ID = Long.parseLong(entrada.nextLine());
    		
    		try {
    		  Livro l = livroService.buscarLivroPorId(ID);
    		  imprimirTabela(l);
    		} catch (Exception e) {
		      System.out.println("Erro: " + e);
		    }
		    
    		
  }
  
  private void atualizarLivro() {
    Scanner entrada =  new Scanner(System.in);
  	Long ID; 
  	Livro l = null;
  	String novoTitulo, novoAutor, novoAssunto;
  	
  	System.out.println("\t\t Atualizar Registro de Livro\n");
  	
  	System.out.print("Entre o ID do Livro: ");
  	ID =  Long.parseLong(entrada.nextLine());
  	
  	try {
  	  l = livroService.buscarLivroPorId(ID);
  	} catch (Exception e) {
		  System.out.println("Erro: " + e);
		}
		
  	imprimirTabela(l);
  	
  	System.out.print("Entre o novo Título: ");
		l.setTitulo(entrada.nextLine());    		
		System.out.print("Entre o novo Autor: ");
		l.setAutor(entrada.nextLine());  
		System.out.print("Entre o novo Assunto: ");
		l.setAssunto(entrada.nextLine());  
		
		try {
		  l = livroService.atualizarLivro(l);
		} catch (Exception e) {
		  System.out.println("Erro: " + e);
		}
		
		System.out.println("\nLivro atualizado!\n");
		
  	imprimirTabela(l);
  }
}
