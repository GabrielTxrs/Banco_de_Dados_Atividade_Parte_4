package com.bancodedados.parte4.Usuario;

import com.bancodedados.parte4.JdbcManual;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("usuario")
public class UsuarioController {


    private final JdbcManual jdbcManual;

    public UsuarioController(JdbcManual jdbcManual) {
        this.jdbcManual = jdbcManual;
    }



    @GetMapping("getUsuarioLeaderboard")
    public ResponseEntity<List<Usuario>> getUsuarioLeaderboard() {
            try {
                jdbcManual.setConnection("professor.cywhulnakf35.us-east-1.rds.amazonaws.com",
                        "professor", "professor", "professor");
                System.out.println("Conexao feita");
                List<Usuario> usuarios = jdbcManual.select();
                jdbcManual.fecharConexao();
                return ResponseEntity.ok(usuarios);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;

    }
    @GetMapping("getUsuarioBancoLocal")
    public ResponseEntity<List<Usuario>> getUsuarioBancoLocal() {
        try {
            jdbcManual.setConnection("localhost:3306", "usuario", "root", "211221");
            System.out.println("Conexao feita");
            List<Usuario> usuarios = jdbcManual.select();
            jdbcManual.fecharConexao();
            return ResponseEntity.ok(usuarios);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @PostMapping("inserirUsuarioLeaderboard")
    public ResponseEntity<String> inserirUsuarioLeaderboard(@RequestBody Usuario usuario) {
        try {
            jdbcManual.setConnection("professor.cywhulnakf35.us-east-1.rds.amazonaws.com",
                    "professor", "professor", "professor");
            String sucesso = jdbcManual.insert(usuario);
            jdbcManual.fecharConexao();
            return ResponseEntity.ok(sucesso);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("Não foi possivel inserir o usuario");
    }
    @PostMapping("inserirUsuarioBancoLocal")
    public ResponseEntity<String> inserirUsuarioBancoLocal(@RequestBody Usuario usuario) {
        try {
            jdbcManual.setConnection("localhost:3306", "usuario", "root", "211221");
            String sucesso = jdbcManual.insert(usuario);
            jdbcManual.fecharConexao();
            return ResponseEntity.ok(sucesso);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("Não foi possivel inserir o usuario");
    }
}
