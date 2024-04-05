package com.bancodedados.parte4.Item;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemRepositoryImpl implements ItemRepository {
    private final JdbcTemplate jdbcTemplate;

    public ItemRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private static final String INSERT_ITEM =
            "INSERT INTO TB_ITEM (ID_ITEM, ID_PEDIDO, NOME, QUANTIDADE, PRECO) " +
            "VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_ITEM =
            "SELECT ID_ITEM, ID_PEDIDO, NOME, QUANTIDADE, PRECO " +
            "FROM TB_ITEM ";
    private static final String SELECT_ITEM_POR_ID =
            "SELECT ID_ITEM, ID_PEDIDO, NOME, QUANTIDADE, PRECO " +
            "FROM TB_ITEM  " +
            "WHERE ID_ITEM = ?";

    private static final String UPDATE_ITEM_POR_ID =
            "UPDATE TB_ITEM SET ID_ITEM = ?, ID_PEDIDO = ?, NOME = ?, QUANTIDADE = ?, PRECO = ? " +
            "WHERE ID_ITEM = ?";
    private static final String DELETE_ITEM_POR_ID = "DELETE FROM TB_ITEM WHERE ID_ITEM = ?";

    @Override
    public Item adicionarItem(Item Item) {
        if (jdbcTemplate.update(INSERT_ITEM, Item.getIdItem(), Item.getIdPedido(),
                Item.getNome(), Item.getQuantidade(), Item.getPreco()) == 1) {
            return Item;
        }
        return null;
    }

    @Override
    public List<Item> getItems() {
        List<Item> itens;
        try {
            itens = jdbcTemplate.query(SELECT_ITEM,new ItemRowMapper());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return itens;
    }

    @Override
    public List<Item> getItemPorId(Long id) {
        List<Item> item;
        try {
            item = jdbcTemplate.query(SELECT_ITEM_POR_ID,new ItemRowMapper(), id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return item;
    }


    @Override
    public Item alterarItemPorId(Long id, Item dadosItem) {
        if (jdbcTemplate.update(UPDATE_ITEM_POR_ID, dadosItem.getIdItem(), dadosItem.getIdPedido(),
                dadosItem.getNome(), dadosItem.getQuantidade(), dadosItem.getPreco(), id) == 1) {
            return dadosItem;
        }
        return null;
    }

    @Override
    public String removerItemPorId(Long id) {
        if (jdbcTemplate.update(DELETE_ITEM_POR_ID, id) == 1) {
            return "Item removido com sucesso!";
        }
        return null;
    }
}
