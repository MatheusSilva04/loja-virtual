package com.matheus.Artifact.loja_virtual.demo;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> listar() {
        return produtoRepository.findAll();
    }

    public Produto criar(Produto produto) {
        return produtoRepository.save(produto);
    }
    public Produto atualizar (Long id, Produto produtoAtualizado){
        Produto produto = produtoRepository.findById(id).orElseThrow();
        produto.setNome(produtoAtualizado.getNome());
        produto.setPreco(produtoAtualizado.getPreco());
        produto.setEstoque(produtoAtualizado.getEstoque());
        return produtoRepository.save(produto);
    }

    public void deletar(Long id) {
        produtoRepository.deleteById(id);
}
}