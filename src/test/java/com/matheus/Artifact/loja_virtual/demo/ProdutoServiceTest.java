package com.matheus.Artifact.loja_virtual.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProdutoServiceTest {
    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ProdutoService produtoService;

    @Test
    void deverCriarProdutoComSucesso() {
        Produto produto = new Produto();
        produto.setNome("Mouse Gamer");
        produto.setPreco(new BigDecimal("120.00"));
        produto.setEstoque(10);

        when(produtoRepository.save(produto)).thenReturn(produto);

        Produto resultado = produtoService.criar(produto);

        assertEquals("Mouse Gamer", resultado.getNome());
        verify(produtoRepository).save(produto);
    }
}

