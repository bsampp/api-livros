package com.example.livros.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.livros.domain.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}