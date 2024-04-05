package com.bancodedados.parte4.Usuario;

//import com.harsh.leaderboard.usuario.email.Email;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Usuario {


    private Long idUsuario;

    private String primeiroNome;

    private String sobrenome;
    private LocalDate dataNascimento;
    private int idade;
    private String telefone;
    private String senha;


    @SuppressWarnings("unused")
    public Usuario() {
    }

    public Usuario(Long idUsuario, String primeiroNome, String sobrenome, LocalDate dataNascimento, int idade,
                   String telefone,
                   String senha) {
        this.idUsuario = idUsuario;
        this.primeiroNome = primeiroNome;
        this.sobrenome = sobrenome;
        this.dataNascimento = dataNascimento;
        this.idade = idade;
        this.telefone = telefone;
        this.senha = senha;
    }


}
