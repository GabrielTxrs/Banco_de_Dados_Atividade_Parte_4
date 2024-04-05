package com.bancodedados.parte4.Item;

import java.util.List;

public interface ItemRepository {
    Item adicionarItem(Item Item);
    List<Item> getItems();
    List<Item> getItemPorId(Long id);
    Item alterarItemPorId(Long id, Item dadosItem);
    String removerItemPorId(Long id);
}
