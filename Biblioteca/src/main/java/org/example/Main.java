package org.example;
import org.example.model.Livro;

import java.util.List;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    private static final String NOME_ARQUIVO = "biblioteca.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Livro> biblioteca = carregarBiblioteca();

        try {
            while (true) {
                System.out.println("Bem vindo(a) à biblioteca do Go Tech!");
                System.out.println("Por favor, selecione a opção desejada:");
                System.out.println("[1] Adicionar novo livro.");
                System.out.println("[2] Devolver livro");
                System.out.println("[3] Listar livros disponíveis");
                System.out.println("[4] Sair");
                System.out.print("Opção: ");
                int opcao = sc.nextInt();

                switch (opcao) {
                    case 1:
                        System.out.println("Por favor insira os dados do novo livro a ser inserido:");
                        System.out.println("Qual o título do livro?");
                        sc.nextLine();
                        String titulo = sc.nextLine();
                        System.out.println("Qual o autor do livro?");
                        String autor = sc.nextLine();
                        System.out.println("Insira o número de páginas do livro.");
                        int paginas = sc.nextInt();
                        biblioteca.add(new Livro(titulo, autor, paginas, true));
                        salvarBiblioteca(biblioteca);
                        System.out.println("Livro adicionado com sucesso! Redirecionando ao menu principal");
                        continue;
                    case 2:
                        System.out.println("Estes são os livros que estão emprestados:");
                        boolean algumLivroDisponivel = false;
                        for (int i = 0; i < biblioteca.size(); i++) {
                            Livro livro = biblioteca.get(i);
                            if (!livro.isDisponivel()) {
                                algumLivroDisponivel = true;
                                System.out.println((i + 1) + ". " + livro);
                            }
                        }
                        if (!algumLivroDisponivel) {
                            System.out.println("Não há livros emprestados no momento.");
                        } else {
                            System.out.print("Selecione o número do livro que deseja devolver: ");
                            int numeroLivro = sc.nextInt();
                            if (numeroLivro >= 1 && numeroLivro <= biblioteca.size()) {
                                Livro livroSelecionado = biblioteca.get(numeroLivro - 1);
                                if (!livroSelecionado.isDisponivel()) {
                                    livroSelecionado.setDisponivel(true);
                                    System.out.println("Livro '" + livroSelecionado.getTitulo() + "' foi devolvido com sucesso!");
                                } else {
                                    System.out.println("Este livro não está emprestado.");
                                }
                            } else {
                                System.out.println("Número de livro inválido.");
                            }
                        }
                        System.out.println("Retornando ao menu principal...");
                        continue;
                    case 3:
                        System.out.println("Estes são nossos livros disponíveis:");
                        for (int i = 0; i < biblioteca.size(); i++) {
                            Livro livro = biblioteca.get(i);
                            if (livro.isDisponivel()) {
                                System.out.println((i + 1) + ". " + livro);
                            }
                        }
                        System.out.println("Deseja pegar emprestado um livro? (S/N)");
                        String resposta = sc.next();
                        if (resposta.equalsIgnoreCase("S")) {
                            System.out.print("Selecione o número do livro que deseja pegar emprestado: ");
                            int numeroLivro = sc.nextInt();
                            if (numeroLivro >= 1 && numeroLivro <= biblioteca.size()) {
                                Livro livroSelecionado = biblioteca.get(numeroLivro - 1);
                                if (livroSelecionado.isDisponivel()) {
                                    livroSelecionado.setDisponivel(false);
                                    System.out.println("Livro '" + livroSelecionado.getTitulo() + "' foi emprestado com sucesso!");
                                } else {
                                    System.out.println("Este livro já está emprestado.");
                                }
                            } else {
                                System.out.println("Número de livro inválido.");
                            }
                        }
                        System.out.println("Retornando ao menu principal...");
                        continue;
                    case 4:
                        System.out.println("Saindo...");
                        break;
                }
                break;
            }
        } finally {
            salvarBiblioteca(biblioteca);
        }
    }

    private static void salvarBiblioteca(List<Livro> biblioteca) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NOME_ARQUIVO))) {
            for (Livro livro : biblioteca) {
                writer.write(livro.getTitulo() + "," + livro.getAutor() + "," + livro.getPaginas() + "," + livro.isDisponivel());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Livro> carregarBiblioteca() {
        List<Livro> biblioteca = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(NOME_ARQUIVO))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] partes = line.split(",");
                String titulo = partes[0];
                String autor = partes[1];
                int paginas = Integer.parseInt(partes[2]);
                boolean disponivel = Boolean.parseBoolean(partes[3]);
                biblioteca.add(new Livro(titulo, autor, paginas, disponivel));
            }
        } catch (IOException e) {
            System.out.println("Arquivo não existe ou ocorreu um erro ao ler o arquivo");
        }
        return biblioteca;
    }
}
