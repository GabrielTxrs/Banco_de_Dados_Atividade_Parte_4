package com.bancodedados.parte4.Item;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("item")
public class ItemController {
    private final ItemRepository itemRepository;

    public ItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @PostMapping("adicionar")
    public Item adicionarItem(@RequestBody Item item) {
        return itemRepository.adicionarItem(item);
    }

    @GetMapping("getAll")
    public List<Item> getItems() {
        return itemRepository.getItems();
    }

    @GetMapping("get/{id}")
    public List<Item> getItemPorId(@PathVariable Long id) {
        return itemRepository.getItemPorId(id);
    }


    @PutMapping("alterar/{id}")
    public Item alterarItemPorId(@PathVariable Long id, @RequestBody Item dadosItem) {
        return itemRepository.alterarItemPorId(id, dadosItem);
    }

    @DeleteMapping("remover/{id}")
    public String removerItemPorId(@PathVariable Long id) {
        return itemRepository.removerItemPorId(id);
    }
}



