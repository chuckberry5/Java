package AulaUmBancoDeDados;


class Main {
  
  public static void main(String[] args) throws Exception {
    Biblioteca b = new Biblioteca();
    try {
      b.criaMenu();
    } catch (Exception e) {
      throw e;
    }
  }
}