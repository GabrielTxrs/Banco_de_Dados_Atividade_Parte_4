package com.bancodedados.parte4.Item;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Item {
    private Long idItem;
    private Long idPedido;
    private String nome;
    private int quantidade;
    private float preco;

    @SuppressWarnings("unused")
    public Item() {
    }

    public Item(Long idItem, String nome, int quantidade, float preco) {
        this.idItem = idItem;
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
    }

}
