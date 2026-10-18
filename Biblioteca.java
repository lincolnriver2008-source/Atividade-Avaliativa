import java.util.ArrayList;

public class Biblioteca {

    private ArrayList<Leitor> leitores = new ArrayList<>();
    private ArrayList<Livro> livros = new ArrayList<>();
    private ArrayList<Emprestimo> emprestimos = new ArrayList<>();

    public boolean cadastrarLeitor(Leitor leitor) {
        if (leitor == null) return false;
        if (leitor.getCodigo() <= 0) return false;
        if (leitor.getNome() == null || leitor.getNome().isBlank()) return false;
        if (leitor.getEmail() == null || leitor.getEmail().isBlank()) return false;
        if (consultarLeitor(leitor.getCodigo()) != null) return false;

        leitores.add(leitor);
        return true;
    }

    public Leitor consultarLeitor(int codigo) {
        for (Leitor cadastro : leitores) {
            if (cadastro.getCodigo() == codigo) {
                return cadastro;
            }
        }
        return null;
    }

    public void listarLeitores() {
        if (leitores.isEmpty()) {
            System.out.println("A lista de leitores está vazia.");
            return;
        }
        for (Leitor leitor : leitores) {
            System.out.println(leitor);
        }
    }

    public boolean removerLeitor(Leitor leitor) {
        if (leitor == null) return false;
        if (leitorTemEmprestimoAberto(leitor)) return false;
        return leitores.remove(leitor);
    }

    public boolean cadastrarLivro(Livro livro) {
        if (livro == null) return false;
        if (livro.getCodigo() <= 0) return false;
        if (livro.getTitulo() == null || livro.getTitulo().isBlank()) return false;
        if (livro.getAutor() == null || livro.getAutor().isBlank()) return false;
        if (consultarLivro(livro.getCodigo()) != null) return false;

        livros.add(livro);
        return true;
    }

    public Livro consultarLivro(int codigo) {
        for (Livro exemplar : livros) {
            if (exemplar.getCodigo() == codigo) {
                return exemplar;
            }
        }
        return null;
    }

    public void listarLivros() {
        if (livros.isEmpty()) {
            System.out.println("A lista de livros está vazia.");
            return;
        }
        for (Livro livro : livros) {
            System.out.println(livro);
        }
    }

    public boolean removerLivro(Livro livro) {
        if (livro == null) return false;
        if (livroEmEmprestimoAberto(livro)) return false;
        return livros.remove(livro);
    }

    public boolean registrarEmprestimo(int codigo, Leitor leitor, ArrayList<Livro> livros) {
        if (codigo <= 0) return false;
        if (consultarEmprestimo(codigo) != null) return false;
        if (leitor == null || !this.leitores.contains(leitor)) return false;
        if (livros == null || livros.isEmpty()) return false;

        Emprestimo emprestimo = new Emprestimo(codigo, leitor);

        for (Livro livro : livros) {
            if (livro == null) return false;
            if (!this.livros.contains(livro)) return false;
            if (!livro.isDisponivel()) return false;
            if (!emprestimo.adicionarLivro(livro)) return false;
        }

        for (Livro livro : livros) {
            livro.emprestar();
        }

        emprestimos.add(emprestimo);
        return true;
    }

    public void listarEmprestimos() {
        if (emprestimos.isEmpty()) {
            System.out.println("Ainda não há empréstimos cadastrados.");
            return;
        }
        for (Emprestimo emprestimo : emprestimos) {
            System.out.println(emprestimo);
        }
    }

    public boolean registrarDevolucao(int codigo) {
        Emprestimo emprestimo = consultarEmprestimo(codigo);
        if (emprestimo == null) return false;
        if (!emprestimo.isAberto()) return false;

        emprestimo.finalizar();
        return true;
    }

    private Emprestimo consultarEmprestimo(int codigo) {
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getCodigo() == codigo) {
                return emprestimo;
            }
        }
        return null;
    }

    private boolean leitorTemEmprestimoAberto(Leitor leitor) {
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.isAberto() && emprestimo.getLeitor() == leitor) {
                return true;
            }
        }
        return false;
    }

    private boolean livroEmEmprestimoAberto(Livro livro) {
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.isAberto() && emprestimo.contemLivro(livro)) {
                return true;
            }
        }
        return false;
    }
}