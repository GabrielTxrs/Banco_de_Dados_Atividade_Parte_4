package com.bancodedados.parte4;

import com.bancodedados.parte4.Usuario.Usuario;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class JdbcManual {
    private Connection connection;

    public JdbcManual() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setConnection(String host, String db, String user, String senha) throws SQLException {
        String url = "jdbc:mysql://" + host + "/" + db;
        connection = DriverManager.getConnection(url, user, senha);
    }

    public List<Usuario> select() throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "SELECT * FROM TB_USUARIO";
        ResultSet rs = statement.executeQuery(sql);
        List<Usuario> usuarios = new ArrayList<>();
        while (rs.next()) {
            Long idUsuario = (long) rs.getInt("ID_USUARIO");
            String primeiroNome = rs.getString("PRIMEIRO_NOME");
            String sobrenome = rs.getString("SOBRENOME");
            String telefone = rs.getString("TELEFONE");
            int idade = rs.getInt("IDADE");
            String senha = rs.getString("SENHA");
            LocalDate localDate = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(rs.getDate("DATA_NASCIMENTO")));
            usuarios.add(new Usuario(idUsuario, primeiroNome, sobrenome, localDate, idade, telefone, senha));
        }
        statement.close();
        return usuarios;
    }

    public String insert(Usuario usuario) throws SQLException {
        Statement statement = connection.createStatement();
        Long idUsuario = usuario.getIdUsuario();
        String primeiroNome = usuario.getPrimeiroNome();
        String sobrenome = usuario.getSobrenome();
        String telefone = usuario.getTelefone();
        String senha = usuario.getSenha();
        LocalDate data = usuario.getDataNascimento();
        String sql = "INSERT INTO TB_USUARIO (ID_USUARIO, PRIMEIRO_NOME, SOBRENOME, DATA_NASCIMENTO, IDADE, TELEFONE, SENHA) VALUES (" + idUsuario + ",'" + primeiroNome + "','" + sobrenome + "',DATE('" + data + "')," + "TIMESTAMPDIFF(YEAR,'" + data + "',CURDATE()),'" + telefone + "','" + senha + "');";
        System.out.println(sql);
        statement.execute(sql);
        statement.close();
        return sql;
    }

    public void fecharConexao() throws SQLException {
        connection.close();
    }
}
