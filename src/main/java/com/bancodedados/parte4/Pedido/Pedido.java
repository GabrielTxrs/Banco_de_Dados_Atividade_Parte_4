package com.bancodedados.parte4.Pedido;

import com.bancodedados.parte4.Cliente.Cliente;
import com.bancodedados.parte4.Item.Item;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class Pedido {
    private Long idPedido;
    private Cliente cliente;
    private LocalDate dataPedido;
    private float precoTotal;
    private List<Item> itensPedido;

    @SuppressWarnings("unused")
    public Pedido() {
    }
    public Pedido(Long idPedido, Cliente cliente, LocalDate dataPedido, float precoTotal, List<Item> itensPedido) {
        this.idPedido = idPedido;
        this.cliente = cliente;
        this.dataPedido = dataPedido;
        this.precoTotal = precoTotal;
        this.itensPedido = itensPedido;
    }
}

