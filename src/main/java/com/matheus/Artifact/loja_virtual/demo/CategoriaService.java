package com.matheus.Artifact.loja_virtual.demo;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> listar() {
        return categoriaRepository.findAll();
    }

    public Categoria criar(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Categoria atualizar (Long id, Categoria categoriaAtualizado){
        Categoria categoria = categoriaRepository.findById(id).orElseThrow();
        categoria.setNome(categoriaAtualizado.getNome());
        return categoriaRepository.save(categoria);
    }

    public void deletar(Long id) {
        categoriaRepository.deleteById(id);
    }
}