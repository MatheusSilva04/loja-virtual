package com.matheus.Artifact.loja_virtual.demo;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService){
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public List<Categoria> listar(){
        return categoriaService.listar();
    }

    @PostMapping
    public Categoria criar(@RequestBody Categoria categoria) {
        return categoriaService.criar(categoria);
    }

    @PutMapping("/{id}")
    public Categoria atualizar (@PathVariable Long id, @RequestBody Categoria categoria){
        return categoriaService.atualizar(id, categoria);
    }

    @DeleteMapping("/{id}")
    public void deletar (@PathVariable Long id) {
        categoriaService.deletar(id);
    }
}

