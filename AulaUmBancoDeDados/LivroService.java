package AulaUmBancoDeDados;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.DataFormatException;


class LivroService {
  
  private String arquivoLivro;
  
  public LivroService(String arquivo) {
    arquivoLivro = arquivo;
  }
  
  public void adicionarLivro(Livro livro, String arquivoLivro) throws IOException {
		BufferedWriter bw = new BufferedWriter( new FileWriter(arquivoLivro,true) );
		bw.write(livro.getId() + "," + 
		         livro.getTitulo() + "," + 
		         livro.getAutor() + "," + 
		         livro.getAssunto());
		bw.flush();
		bw.newLine();
		bw.close();		
  }
  
  public void adicionarLivro(Livro livro) throws IOException {
    adicionarLivro(livro, this.arquivoLivro);
  }
  
  
  public ArrayList<Livro> listarLivros() throws IOException, DataFormatException {
    ArrayList<Livro> livros = new ArrayList<>();
    String linha;
    BufferedReader br = new BufferedReader( new FileReader(arquivoLivro) );
    
    while( (linha = br.readLine() ) != null ) {
    		Livro l = new Livro(linha);
    	  livros.add(l);
    }		
    
    br.close();
    
    return livros;
  }
  
  public void removerLivroPorId(Long id) throws IOException, DataFormatException {
  	String tempDB = "livro_db_temp.txt";
  	
  	// Para cada livro, adicione-o ao arquivo temporario
  	// caso o livro tenha o ID que queiramos apagar, pule ele
  	for (Livro l : listarLivros()) {
  		if( id == l.getId() ) { continue; }
      adicionarLivro(l, tempDB);
  	}
  	
  	// A seguir, apaga o arquivo DB original e renomeia o arquivo temporario
  	// com o nome do arquivo original
    File db = new File(arquivoLivro);
    File tDb = new File(tempDB);
	
  	db.delete();
  	tDb.renameTo(db);
  }
  
  public Livro buscarLivroPorId(Long id) throws IOException, DataFormatException {
    for (Livro l : listarLivros()) {
  		if( id == l.getId() ) { return l; }
  	}
  	
  	return null;
  }
  
  public Livro atualizarLivro(Livro livro) throws IOException, DataFormatException {
    String tempDB = "livro_db_temp.txt";
  	
  	// Para cada livro, adicione-o ao arquivo temporario
  	// caso o livro tenha o ID que queiramos apagar, pule ele
  	for (Livro l : listarLivros()) {
  		if( livro.getId() == l.getId() ) { 
  		  adicionarLivro(livro, tempDB);
  	  } else {
        adicionarLivro(l, tempDB);
  	  }
  	}
  	
  	// A seguir, apaga o arquivo DB original e renomeia o arquivo temporario
  	// com o nome do arquivo original
    File db = new File(arquivoLivro);
    File tDb = new File(tempDB);
	
  	db.delete();
  	tDb.renameTo(db);
  	
  	return livro;
  }
}
