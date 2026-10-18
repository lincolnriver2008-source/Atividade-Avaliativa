public class Livro {
    private int codigo;
    private String titulo;
    private String autor;
    private boolean disponivel;

    public int getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public Livro(int codigo, String titulo, String autor) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.autor = autor;
        this.disponivel = true;
    }

    public void atualizarDados(String titulo, String autor) {
        if (titulo != null && !titulo.isBlank()
                && autor != null && !autor.isBlank()) {
            this.titulo = titulo;
            this.autor = autor;
        }
    }

    public void emprestar() {
        disponivel = false;
    }

    public void devolver() {
        disponivel = true;
    }

    @Override
    public String toString() {
        return "Livro " + codigo + ": " + titulo + " | Autor: " + autor + " | "
                + (disponivel ? "disponível" : "emprestado");
    }
}
