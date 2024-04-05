package com.bancodedados.parte4.Pedido;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pedido")
public class PedidoController {
    private final PedidoRepository pedidoRepository;

    public PedidoController(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }
     @PostMapping("adicionar")
     public Pedido adicionarPedido(@RequestBody Pedido pedido) {
         return pedidoRepository.adicionarPedido(pedido);
     }
     @GetMapping("getAll")
     public List<Pedido> getPedidos() {
         return pedidoRepository.getPedidos();
     }
     @GetMapping("get/{id}")
     public List<Pedido> getPedidoPorId(@PathVariable Long id) {
         return pedidoRepository.getPedidoPorId(id);
     }
     @PutMapping("alterar/{id}")
     public Pedido alterarPedidoPorId(@PathVariable Long id, @RequestBody Pedido dadosPedido) {
         return pedidoRepository.alterarPedidoPorId(id, dadosPedido);
     }
     @DeleteMapping("remover/{id}")
     public String removerPedidoPorId(@PathVariable Long id) {
         return pedidoRepository.removerPedidoPorId(id);
     }

}
