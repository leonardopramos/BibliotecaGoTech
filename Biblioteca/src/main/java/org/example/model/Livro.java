package org.example.model;

public class Livro {
    String titulo;
    String autor;
    int paginas;
    boolean disponivel;

    public Livro(String titulo, String autor, int paginas, boolean disponivel) {
        this.titulo = titulo;
        this.autor = autor;
        this.paginas = paginas;
        this.disponivel = disponivel;
    }
    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getPaginas() {
        return paginas;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    @Override
    public String toString() {
        return "Titulo: " + titulo + '\'' +
                ", Autor: '" + autor + '\'' +
                ", Paginas: " + paginas ;
    }
}
