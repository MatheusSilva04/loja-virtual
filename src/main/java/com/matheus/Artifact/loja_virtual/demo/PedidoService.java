package com.matheus.Artifact.loja_virtual.demo;

import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ItemPedidoRepository itemPedidoRepository;
    private final ProdutoRepository produtoRepository;
    private final UsuarioRepository usuarioRepository;

    public PedidoService(PedidoRepository pedidoRepository,
                         ItemPedidoRepository itemPedidoRepository,
                         ProdutoRepository produtoRepository,
                         UsuarioRepository usuarioRepository) {
        this.pedidoRepository = pedidoRepository;
        this.itemPedidoRepository = itemPedidoRepository;
        this.produtoRepository = produtoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Pedido abrirCarrinho(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow();

        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);
        pedido.setStatus(StatusPedido.CARRINHO);
        pedido.setDataCriacao(LocalDateTime.now());

        return pedidoRepository.save(pedido);
    }

    public Pedido adicionarItem(Long pedidoId, Long produtoId, Integer quantidade){
        Pedido pedido = pedidoRepository.findById(pedidoId).orElseThrow();
        Produto produto = produtoRepository.findById(produtoId).orElseThrow();

        ItemPedido item = new ItemPedido();
        item.setPedido(pedido);
        item.setProduto(produto);
        item.setQuantidade(quantidade);
        item.setPrecoUnitario(produto.getPreco());

        itemPedidoRepository.save(item);

        return pedidoRepository.findById(pedidoId).orElseThrow();
    }

    public BigDecimal calcularTotal(Long pedidoId) {
        Pedido pedido = pedidoRepository.findById(pedidoId).orElseThrow();

        BigDecimal total = BigDecimal.ZERO;
        for (ItemPedido item : pedido.getItens()) {
            BigDecimal subtotal = item.getPrecoUnitario().multiply(BigDecimal.valueOf(item.getQuantidade()));
            total = total.add(subtotal);
        }
        return total;
    }

    public Pedido finalizar(Long pedidoId) {
        Pedido pedido = pedidoRepository.findById(pedidoId).orElseThrow();

        for (ItemPedido item : pedido.getItens()) {
            Produto produto = item.getProduto();
            int novoEstoque = produto.getEstoque() - item.getQuantidade();
            if (novoEstoque < 0) {
                throw  new RuntimeException("Estoque insuficiente para o produto:" + produto.getNome());
            }
            produto.setEstoque(novoEstoque);
            produtoRepository.save(produto);
        }

        pedido.setStatus(StatusPedido.PENDENTE);
        return pedidoRepository.save(pedido);
    }
}
