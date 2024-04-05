package com.bancodedados.parte4.Cliente;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cliente")
public class ClienteController {
    private final ClienteRepository clienteRepository;

    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }
    @PostMapping("adicionar")
    public Cliente adicionarCliente(@RequestBody Cliente cliente) {
        return clienteRepository.adicionarCliente(cliente);
    }
    @GetMapping("getAll")
    public List<Cliente> getClientes() {
        return clienteRepository.getClientes();
    }
    @GetMapping("get/{id}")
    public List<Cliente> getClientePorId(@PathVariable Long id) {
        return clienteRepository.getClientePorId(id);
    }
    @PutMapping("alterar/{id}")
    public Cliente alterarClientePorId(@PathVariable Long id, @RequestBody Cliente dadosCliente) {
        return clienteRepository.alterarClientePorId(id, dadosCliente);
    }
    @DeleteMapping("remover/{id}")
    public String removerClientePorId(@PathVariable Long id) {
        return clienteRepository.removerClientePorId(id);
    }
}












