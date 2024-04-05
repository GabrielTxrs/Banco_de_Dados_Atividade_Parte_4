package com.bancodedados.parte4.Cliente;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteRowMapper implements RowMapper<Cliente> {


    @Override
    public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Cliente(
                rs.getLong("ID_CLIENTE"),
                rs.getString("NOME"),
                rs.getString("SOBRENOME"),
                rs.getString("EMAIL"),
                rs.getInt("IDADE"));

    }
}
