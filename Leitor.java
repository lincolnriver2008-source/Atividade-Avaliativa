public class Leitor {
    private int codigo;
    private String nome;
    private String email;

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public Leitor(int codigo, String nome, String email) {
        this.codigo = codigo;
        this.email = email;
        this.nome = nome;
    }

    public void atualizarDados(String nome, String email) {
        if (nome != null && !nome.isBlank()
                && email != null && !email.isBlank()) {
            this.nome = nome;
            this.email = email;
        }
    }

    @Override
    public String toString() {
        return "Leitor " + codigo + ": " + nome + " | E-mail: " + email;
    }
}
