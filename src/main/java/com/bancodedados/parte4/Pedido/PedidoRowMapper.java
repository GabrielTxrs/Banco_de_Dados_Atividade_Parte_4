package com.bancodedados.parte4.Pedido;

import com.bancodedados.parte4.Cliente.Cliente;
import com.bancodedados.parte4.Cliente.ClienteRowMapper;
import com.bancodedados.parte4.Item.Item;
import com.bancodedados.parte4.Item.ItemRowMapper;
import lombok.Setter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Setter
public class PedidoRowMapper implements RowMapper<Pedido> {
    private JdbcTemplate jdbcTemplate;
    public PedidoRowMapper(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SELECT_ITEM_POR_ID_PEDIDO =
            "SELECT ID_ITEM, ID_PEDIDO, NOME, QUANTIDADE, PRECO " +
            "FROM TB_ITEM  " +
            "WHERE ID_PEDIDO = ?";



    @Override
    public Pedido mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long idPedido = rs.getLong("ID_PEDIDO");
        Cliente cliente = new ClienteRowMapper().mapRow(rs, rowNum);
        LocalDate dataPedido = rs.getDate("DATA_PEDIDO").toLocalDate();
        int precoTotal = rs.getInt("PRECO_TOTAL");


        List<Item> items = jdbcTemplate.query(SELECT_ITEM_POR_ID_PEDIDO, new ItemRowMapper(), idPedido);

        return new Pedido(idPedido, cliente, dataPedido, precoTotal, items);
    }


}
