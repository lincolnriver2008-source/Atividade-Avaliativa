import java.util.ArrayList;
import java.util.Scanner;

public class Aplicacao {

    private Scanner scanner;
    private Biblioteca biblioteca;

    public Aplicacao() {
        scanner = new Scanner(System.in);
        biblioteca = new Biblioteca();
    }

    public static void main(String[] args) {
        Aplicacao aplicacao = new Aplicacao();
        aplicacao.executar();
    }

    public void executar() {
        int opcao;
        do {
            mostrarMenu();
            opcao = lerInteiro("Opção: ");
            switch (opcao) {
                case 1:
                    opcaoCadastrarLeitor();
                    break;
                case 2:
                    opcaoConsultarLeitor();
                    break;
                case 3:
                    biblioteca.listarLeitores();
                    break;
                case 4:
                    opcaoAtualizarLeitor();
                    break;
                case 5:
                    opcaoRemoverLeitor();
                    break;
                case 6:
                    opcaoCadastrarLivro();
                    break;
                case 7:
                    opcaoConsultarLivro();
                    break;
                case 8:
                    biblioteca.listarLivros();
                    break;
                case 9:
                    opcaoAtualizarLivro();
                    break;
                case 10:
                    opcaoRemoverLivro();
                    break;
                case 11:
                    opcaoRegistrarEmprestimo();
                    break;
                case 12:
                    biblioteca.listarEmprestimos();
                    break;
                case 13:
                    opcaoRegistrarDevolucao();
                    break;
                case 0:
                    System.out.println("Sistema finalizado.");
                    break;
                default:
                    System.out.println("Escolha inválida. Tente novamente.");
            }
        } while (opcao != 0);
        scanner.close();
    }

    private void mostrarMenu() {
        System.out.println();
        System.out.println("1 - Adicionar leitor");
        System.out.println("2 - Buscar leitor");
        System.out.println("3 - Exibir leitores");
        System.out.println("4 - Editar leitor");
        System.out.println("5 - Excluir leitor");
        System.out.println("6 - Adicionar livro");
        System.out.println("7 - Buscar livro");
        System.out.println("8 - Exibir livros");
        System.out.println("9 - Editar livro");
        System.out.println("10 - Excluir livro");
        System.out.println("11 - Abrir empréstimo");
        System.out.println("12 - Exibir empréstimos");
        System.out.println("13 - Finalizar devolução");
        System.out.println("0 - Sair");
    }

    private int lerInteiro(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Informe um número inteiro.");
            }
        }
    }

    private String lerTexto(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String texto = scanner.nextLine().trim();
            if (!texto.isEmpty()) {
                return texto;
            }
            System.out.println("Este campo é obrigatório.");
        }
    }

    private void opcaoCadastrarLeitor() {
        int codigo = lerInteiro("Identificador do leitor: ");
        String nome = lerTexto("Nome: ");
        String email = lerTexto("E-mail: ");

        Leitor leitor = new Leitor(codigo, nome, email);
        if (biblioteca.cadastrarLeitor(leitor)) {
            System.out.println("Cadastro de leitor concluído.");
        } else {
            System.out.println("Não foi possível concluir o cadastro do leitor.");
        }
    }

    private void opcaoConsultarLeitor() {
        int codigo = lerInteiro("Identificador do leitor: ");
        Leitor leitor = biblioteca.consultarLeitor(codigo);
        if (leitor == null) {
            System.out.println("Nenhum registro localizado.");
        } else {
            System.out.println(leitor);
        }
    }

    private void opcaoAtualizarLeitor() {
        int codigo = lerInteiro("Identificador do leitor: ");
        Leitor leitor = biblioteca.consultarLeitor(codigo);
        if (leitor == null) {
            System.out.println("Nenhum registro localizado.");
            return;
        }
        String nome = lerTexto("Novo nome: ");
        String email = lerTexto("Novo e-mail: ");
        leitor.atualizarDados(nome, email);
        System.out.println("Informações do leitor atualizadas.");
    }

    private void opcaoRemoverLeitor() {
        int codigo = lerInteiro("Identificador do leitor: ");
        Leitor leitor = biblioteca.consultarLeitor(codigo);
        if (leitor == null) {
            System.out.println("Nenhum registro localizado.");
            return;
        }
        if (biblioteca.removerLeitor(leitor)) {
            System.out.println("Leitor excluído com sucesso.");
        } else {
            System.out.println("Não foi possível excluir o leitor: existe empréstimo em aberto.");
        }
    }

    private void opcaoCadastrarLivro() {
        int codigo = lerInteiro("Identificador do livro: ");
        String titulo = lerTexto("Título: ");
        String autor = lerTexto("Autor: ");

        Livro livro = new Livro(codigo, titulo, autor);
        if (biblioteca.cadastrarLivro(livro)) {
            System.out.println("Cadastro de livro concluído.");
        } else {
            System.out.println("Não foi possível concluir o cadastro do livro.");
        }
    }

    private void opcaoConsultarLivro() {
        int codigo = lerInteiro("Identificador do livro: ");
        Livro livro = biblioteca.consultarLivro(codigo);
        if (livro == null) {
            System.out.println("Nenhum registro localizado.");
        } else {
            System.out.println(livro);
        }
    }

    private void opcaoAtualizarLivro() {
        int codigo = lerInteiro("Identificador do livro: ");
        Livro livro = biblioteca.consultarLivro(codigo);
        if (livro == null) {
            System.out.println("Nenhum registro localizado.");
            return;
        }
        String titulo = lerTexto("Novo título: ");
        String autor = lerTexto("Novo autor: ");
        livro.atualizarDados(titulo, autor);
        System.out.println("Informações do livro atualizadas.");
    }

    private void opcaoRemoverLivro() {
        int codigo = lerInteiro("Identificador do livro: ");
        Livro livro = biblioteca.consultarLivro(codigo);
        if (livro == null) {
            System.out.println("Nenhum registro localizado.");
            return;
        }
        if (biblioteca.removerLivro(livro)) {
            System.out.println("Livro excluído com sucesso.");
        } else {
            System.out.println("Não foi possível excluir o livro: existe empréstimo em aberto.");
        }
    }

    private void opcaoRegistrarEmprestimo() {
        int codigoEmprestimo = lerInteiro("Identificador do empréstimo: ");
        int codigoLeitor = lerInteiro("Identificador do leitor: ");
        Leitor leitor = biblioteca.consultarLeitor(codigoLeitor);
        if (leitor == null) {
            System.out.println("Nenhum registro localizado.");
            return;
        }

        ArrayList<Livro> livrosSelecionados = new ArrayList<>();
        int codigoLivro;
        do {
            codigoLivro = lerInteiro("Identificador do livro (0 para concluir): ");
            if (codigoLivro != 0) {
                Livro livro = biblioteca.consultarLivro(codigoLivro);
                if (livro == null) {
                    System.out.println("Nenhum registro localizado.");
                } else {
                    livrosSelecionados.add(livro);
                }
            }
        } while (codigoLivro != 0);

        if (biblioteca.registrarEmprestimo(codigoEmprestimo, leitor, livrosSelecionados)) {
            System.out.println("Empréstimo cadastrado com sucesso.");
        } else {
            System.out.println("Não foi possível concluir o empréstimo.");
        }
    }

    private void opcaoRegistrarDevolucao() {
        int codigo = lerInteiro("Identificador do empréstimo: ");
        if (biblioteca.registrarDevolucao(codigo)) {
            System.out.println("Devolução concluída com sucesso.");
        } else {
            System.out.println("Não foi possível concluir a devolução.");
        }
    }
}