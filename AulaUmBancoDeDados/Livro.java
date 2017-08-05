package AulaUmBancoDeDados;

import java.util.StringTokenizer;
import java.util.zip.DataFormatException;

class Livro {
  private Long id;
  private String titulo;
  private String autor;
  private String assunto;
  
  public Livro(Long id, String titulo, String autor, String assunto) {
    this.id = id;
    this.titulo = titulo;
    this.autor = autor;
    this.assunto = assunto;
  }
  
  public Livro(String livro) throws DataFormatException {
    // faz um "split" do CSV
    StringTokenizer st = new StringTokenizer(livro,",");
    if (st.countTokens() != 4) {
      throw new DataFormatException("A String de Livro deve conter 4 elementos.");
    }
    this.id = Long.parseLong(st.nextToken());
    this.titulo = st.nextToken();
    this.autor = st.nextToken();
    this.assunto = st.nextToken();
  }
  
  public void setId(Long id) {
    this.id = id;
  }
  
  public Long getId() {
    return id;
  }
  
  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }
  
  public String getTitulo() {
    return titulo;
  }
  
  public void setAutor(String autor) {
    this.autor = autor;
  }
  
  public String getAutor() {
    return autor;
  }
  
  public void setAssunto(String assunto) {
    this.assunto = assunto;
  }
  
  public String getAssunto() {
    return assunto;
  }
}
