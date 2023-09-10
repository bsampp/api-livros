package com.example.livros.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.livros.domain.dto.livro.LivroRequestDTO;
import com.example.livros.domain.dto.livro.LivroResponseDTO;
import com.example.livros.domain.exception.ResourceNotFoundException;
import com.example.livros.domain.model.Livro;
import com.example.livros.domain.model.Usuario;
import com.example.livros.domain.repository.LivroRepository;

@Service
public class LivroService implements ICRUDService<LivroRequestDTO, LivroResponseDTO> {

    @Autowired
    private LivroRepository livroRepository;
    @Autowired
    private ModelMapper mapper; 

    @Override
    public List<LivroResponseDTO> obterTodos() {
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       List<Livro> lista = livroRepository.findByUsuario(usuario);
       return lista.stream().map(livro -> mapper.map(livro, LivroResponseDTO.class))
       .collect(Collectors.toList());
    }

    @Override
    public LivroResponseDTO obterPorId(Long id) {
        Optional<Livro> optLivro = livroRepository.findById(id);
        if(optLivro.isEmpty()){
            throw new ResourceNotFoundException("Não foi possível encontrar o livro com o id: " + id);
        }
        return mapper.map(optLivro.get(), LivroResponseDTO.class);
    }

    @Override
    public LivroResponseDTO cadastrar(LivroRequestDTO dto) {
        Livro livro = mapper.map(dto, Livro.class);
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        livro.setUsuario(usuario);
        livro.setId(null);
        livro = livroRepository.save(livro);
        return mapper.map(livro, LivroResponseDTO.class);
    }

    @Override
    public LivroResponseDTO atualizar(Long id, LivroRequestDTO dto) {
        obterPorId(id);
        Livro livro = mapper.map(dto, Livro.class);
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        livro.setUsuario(usuario);
        livro.setId(id);
        livro = livroRepository.save(livro);
        return mapper.map(livro, LivroResponseDTO.class);
    }

    @Override
    public void deletar(Long id) {
        obterPorId(id);
        livroRepository.deleteById(id);
    }
    
}
