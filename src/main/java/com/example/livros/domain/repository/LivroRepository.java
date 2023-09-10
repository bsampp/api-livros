package com.example.livros.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.livros.domain.model.Livro;
import com.example.livros.domain.model.Usuario;

public interface LivroRepository extends JpaRepository<Livro, Long>{
    List<Livro> findByUsuario(Usuario usuario);
} 