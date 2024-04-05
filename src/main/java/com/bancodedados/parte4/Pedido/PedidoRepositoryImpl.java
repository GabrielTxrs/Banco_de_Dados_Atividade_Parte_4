package com.bancodedados.parte4.Pedido;

import com.bancodedados.parte4.Cliente.Cliente;
import com.bancodedados.parte4.Cliente.ClienteRepositoryImpl;
import com.bancodedados.parte4.Item.Item;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PedidoRepositoryImpl implements PedidoRepository {

    private final JdbcTemplate jdbcTemplate;
    private final ClienteRepositoryImpl clienteRepository;

    public PedidoRepositoryImpl(JdbcTemplate jdbcTemplate, ClienteRepositoryImpl clienteRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.clienteRepository = clienteRepository;
    }

    private static final String INSERT_PEDIDO =
            "INSERT INTO TB_PEDIDO (ID_PEDIDO, ID_CLIENTE, DATA_PEDIDO, PRECO_TOTAL) VALUES (?, ?, ?, ?)";
    private static final String INSERT_ITEM =
            "INSERT INTO TB_ITEM (ID_ITEM, ID_PEDIDO, NOME, QUANTIDADE, PRECO) VALUES (?, ?, ?, ?, ?)";
    private static final String INSERT_CLIENTE =
            "INSERT INTO TB_CLIENTE (ID_CLIENTE, NOME, SOBRENOME, EMAIL, IDADE) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_PEDIDOS =
            "SELECT PED.ID_PEDIDO, PED.ID_CLIENTE, PED.DATA_PEDIDO, PED.PRECO_TOTAL, CLI.NOME, CLI.SOBRENOME, CLI.EMAIL, CLI.IDADE " +
            "FROM TB_PEDIDO AS PED " +
            "JOIN TB_CLIENTE AS CLI ON (PED.ID_CLIENTE = CLI.ID_CLIENTE)";
    private static final String SELECT_PEDIDOS_POR_ID =
            "SELECT " +
            "PED.ID_PEDIDO, PED.ID_CLIENTE, PED.DATA_PEDIDO, PED.PRECO_TOTAL, CLI.NOME, CLI.SOBRENOME, CLI.EMAIL, CLI.IDADE " +
            "FROM TB_PEDIDO AS PED " +
            "JOIN TB_CLIENTE AS CLI ON (PED.ID_CLIENTE = CLI.ID_CLIENTE) " +
            "WHERE PED.ID_PEDIDO = ?";
    private static final String UPDATE_PEDIDO_POR_ID =
            "UPDATE TB_PEDIDO SET ID_PEDIDO = ?, ID_CLIENTE = ?, DATA_PEDIDO = ?, PRECO_TOTAL = ? WHERE ID_PEDIDO = ?";
    private static final String DELETE_ITEM_POR_ID = "DELETE FROM TB_ITEM WHERE ID_PEDIDO = ?";
    private static final String DELETE_PEDIDO_POR_ID = "DELETE FROM TB_PEDIDO WHERE ID_PEDIDO = ?";

    @Override
    public Pedido adicionarPedido(Pedido pedido) {
        float precoTotal = 0F;
        Cliente cliente = pedido.getCliente();
        if(clienteRepository.getClientePorId(cliente.getIdCliente()).isEmpty()) {
            jdbcTemplate.update(INSERT_CLIENTE, cliente.getIdCliente(), cliente.getNome(),
                    cliente.getSobrenome(), cliente.getEmail(), cliente.getIdade());
        }
        for (Item item : pedido.getItensPedido()) {
            precoTotal += item.getPreco()*item.getQuantidade();
        }
        if(jdbcTemplate.update(INSERT_PEDIDO, pedido.getIdPedido(), cliente.getIdCliente(),
                pedido.getDataPedido(), precoTotal) == 1) {
            for (Item item : pedido.getItensPedido()) {
                jdbcTemplate.update(INSERT_ITEM, item.getIdItem(), pedido.getIdPedido(), item.getNome(), item.getQuantidade(), item.getPreco());
            }
            return pedido;
        } else {
            return null;
        }
    }

    @Override
    public List<Pedido> getPedidos() {
        List<Pedido> pedidos;
        try {
            pedidos = jdbcTemplate.query(SELECT_PEDIDOS, new PedidoRowMapper(jdbcTemplate));
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
        return pedidos;
    }

    @Override
    public List<Pedido> getPedidoPorId(Long id) {
        List<Pedido> pedido;
        try {
            pedido = jdbcTemplate.query(SELECT_PEDIDOS_POR_ID, new PedidoRowMapper(jdbcTemplate), id);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
        return pedido;
    }

    @Override
    public Pedido alterarPedidoPorId(Long id, Pedido dadosPedido) {
        return jdbcTemplate.update(UPDATE_PEDIDO_POR_ID, dadosPedido.getIdPedido(), dadosPedido.getCliente().getIdCliente(),
                dadosPedido.getDataPedido(), dadosPedido.getPrecoTotal(), id) == 1 ? dadosPedido : null;
    }

    @Override
    public String removerPedidoPorId(Long id) {
        jdbcTemplate.update(DELETE_ITEM_POR_ID, id);
        return jdbcTemplate.update(DELETE_PEDIDO_POR_ID, id) == 1 ? "Pedido removido com sucesso" : "Erro ao remover pedido";
    }
}
