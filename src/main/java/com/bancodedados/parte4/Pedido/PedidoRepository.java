package com.bancodedados.parte4.Pedido;

import java.util.List;

public interface PedidoRepository {
    Pedido adicionarPedido(Pedido Pedido);
    List<Pedido> getPedidos();
    List<Pedido> getPedidoPorId(Long id);
    Pedido alterarPedidoPorId(Long id, Pedido dadosPedido);
    String removerPedidoPorId(Long id);
}
