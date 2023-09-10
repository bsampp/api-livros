package com.example.livros.domain.dto.livro;

public class LivroRequestDTO {
    private Long id;
    private String titulo;
    private String autor;
    private String editora;
    private int ano;
    private String genero;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditora() {
        return editora;
    }
    public void setEditora(String editora) {
        this.editora = editora;
    }

    public int getAno() {
        return ano;
    }
    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero){
        this.genero = genero;
    }
}
