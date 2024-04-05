package com.bancodedados.parte4.Cliente;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cliente {
    private Long idCliente;
    private String nome;
    private String sobrenome;
    private String email;
    private int idade;

    @SuppressWarnings("unused")
    public Cliente() {
    }

    public Cliente(Long idCliente, String nome, String sobrenome, String email, int idade) {
        this.idCliente = idCliente;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.idade = idade;
    }
}
