package com.bancodedados.parte4.Cliente;

import java.util.List;

public interface ClienteRepository {

     Cliente adicionarCliente(Cliente Cliente);
     List<Cliente> getClientes();
     List<Cliente> getClientePorId(Long id);
     Cliente alterarClientePorId(Long id, Cliente dadosCliente);
     String removerClientePorId(Long id);

}
