package com.bancodedados.parte4.Item;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemRowMapper implements RowMapper<Item> {

    @Override
    public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long idItem = rs.getLong("ID_ITEM");
        String nomeItem = rs.getString("NOME");
        int quantidade = rs.getInt("QUANTIDADE");
        float preco = rs.getFloat("PRECO");

        return new Item(idItem, nomeItem, quantidade, preco);
    }

}