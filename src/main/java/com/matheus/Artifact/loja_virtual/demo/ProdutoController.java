package com.matheus.Artifact.loja_virtual.demo;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService){
        this.produtoService = produtoService;
    }
    @GetMapping
    public List<Produto> listar(){
        return produtoService.listar();
    }

    @PostMapping
    public Produto criar (@RequestBody Produto produto){
        return produtoService.criar(produto);
    }

    @PutMapping("/{id}")
    public Produto atualizar (@PathVariable Long id, @RequestBody Produto produto){
        return produtoService.atualizar(id, produto);
    }

    @DeleteMapping("/{id}")
    public void deletar (@PathVariable Long id) {
        produtoService.deletar(id);
    }
}
