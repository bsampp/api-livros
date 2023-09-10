package com.example.livros.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.livros.domain.service.LivroService;
import com.example.livros.domain.dto.livro.LivroRequestDTO;
import com.example.livros.domain.dto.livro.LivroResponseDTO;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/livros")
public class LivroController {
 @Autowired
    private LivroService livroService;

    @GetMapping
    public ResponseEntity<List<LivroResponseDTO>> obterTodos(){
        return ResponseEntity.ok(livroService.obterTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroResponseDTO> obterPorId(@PathVariable Long id){
        return ResponseEntity.ok(livroService.obterPorId(id));
    }

    @PostMapping
    public ResponseEntity<LivroResponseDTO> cadastrar(@RequestBody LivroRequestDTO dto){
        LivroResponseDTO livro = livroService.cadastrar(dto);
        return new ResponseEntity<>(livro, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroResponseDTO> atualizar(@PathVariable Long id, @RequestBody LivroRequestDTO dto){
        LivroResponseDTO responseDTO = livroService.atualizar(id, dto);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id){
        livroService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

