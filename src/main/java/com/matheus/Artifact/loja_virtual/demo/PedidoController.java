package com.matheus.Artifact.loja_virtual.demo;

import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService){
        this.pedidoService = pedidoService;
    }

    @PostMapping("/abrir/{usuarioId}")
    public Pedido abrirCarrinho(@PathVariable Long usuarioId) {
        return pedidoService.abrirCarrinho(usuarioId);
    }

    @PostMapping("/{pedidoId}/itens")
    public Pedido adicionarItem(@PathVariable Long pedidoId,
                                @RequestParam Long produtoId,
                                @RequestParam Integer quantidade){
        return pedidoService.adicionarItem(pedidoId, produtoId, quantidade);
    }

    @GetMapping("/{pedidoId}/total")
    public BigDecimal calcularTotal(@PathVariable Long pedidoId) {
        return pedidoService.calcularTotal(pedidoId);
    }

    @PostMapping("/{pedidoId}/finalizar")
    public Pedido finalizar(@PathVariable Long pedidoId) {
        return pedidoService.finalizar(pedidoId);
    }
}
