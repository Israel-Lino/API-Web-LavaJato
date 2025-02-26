package com.lavajatoapi.projeto.factory;

import com.lavajatoapi.projeto.entities.Clientes;

public class ClientesFactory {

    public static Clientes createCliente(String nome, String email, String senha, String telefone) {
        Clientes cliente = new Clientes();
        cliente.setNome(nome);
        cliente.setEmail(email);
        cliente.setSenha(senha);
        cliente.setTelefone(telefone);
        return cliente;
    }
}
