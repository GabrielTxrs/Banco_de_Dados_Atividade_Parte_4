package com.bancodedados.parte4.Cliente;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClienteRepositoryImpl implements ClienteRepository {

    private static final String INSERT_CLIENTE = "INSERT INTO TB_CLIENTE (ID_CLIENTE, NOME, SOBRENOME, EMAIL, IDADE) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_CLIENTES = "SELECT * FROM TB_CLIENTE";
    private static final String SELECT_CLIENTE_POR_ID = "SELECT * FROM TB_CLIENTE WHERE ID_CLIENTE = ?";
    private static final String UPDATE_CLIENTE = "UPDATE TB_CLIENTE SET NOME = ?, SOBRENOME = ?, EMAIL = ?, IDADE = ? WHERE ID_CLIENTE = ?";
    private static final String DELETE_CLIENTE_POR_ID = "DELETE FROM TB_CLIENTE WHERE ID_CLIENTE = ?";
    private final JdbcTemplate jdbcTemplate;

    public ClienteRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Cliente adicionarCliente(Cliente Cliente) {
        if (jdbcTemplate.update(INSERT_CLIENTE, Cliente.getIdCliente(), Cliente.getNome(), Cliente.getSobrenome(),
                Cliente.getEmail(), Cliente.getIdade()) == 1) {
            return Cliente;
        }
        return null;
    }

    @Override
    public List<Cliente> getClientes() {
        List<Cliente> clientes;
        try {
            clientes = jdbcTemplate.query(SELECT_CLIENTES, new ClienteRowMapper());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return clientes;
    }

    @Override
    public List<Cliente> getClientePorId(Long id) {
        List<Cliente> cliente;
        try {
            cliente = jdbcTemplate.query(SELECT_CLIENTE_POR_ID, new ClienteRowMapper(), id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return cliente;
    }

    @Override
    public Cliente alterarClientePorId(Long id, Cliente dadosCliente) {
        if (jdbcTemplate.update(UPDATE_CLIENTE, dadosCliente.getNome(), dadosCliente.getSobrenome(), dadosCliente.getEmail(),
                dadosCliente.getIdade(), id) == 1) {
            return dadosCliente;
        }
        return null;
    }

    @Override
    public String removerClientePorId(Long id) {
        if (jdbcTemplate.update(DELETE_CLIENTE_POR_ID, id) == 1) {
            return "Cliente removido com sucesso!";
        }
        return null;
    }

}
