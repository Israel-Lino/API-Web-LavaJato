package com.lavajatoapi.projeto.service;

import com.lavajatoapi.projeto.entities.Clientes;
import com.lavajatoapi.projeto.repository.ICliente;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ICliente repository;
    private final PasswordEncoder passwordEncoder;

    public ClienteService(ICliente repository){
        this.repository = repository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public List<Clientes> listarCliente(){
        return repository.findAll();
    }

    public Clientes cadastrarCliente(Clientes clientes){
        String encoder = this.passwordEncoder.encode(clientes.getSenha());
        clientes.setSenha(encoder);
        return repository.save(clientes);
    }

    public Clientes editarCliente(Clientes clientes){
        String encoder = this.passwordEncoder.encode(clientes.getSenha());
        clientes.setSenha(encoder);
        return repository.save(clientes);
    }

    public boolean excluirCliente(Long id){
        repository.deleteById(id);
        return true;
    }

    public Clientes buscarClientePorId(Long id){
        return repository.findById(id).orElse(null);
    }

    public boolean validarSenha(Long id, String senha) {
        Clientes clientes = buscarClientePorId(id);
        return passwordEncoder.matches(senha, clientes.getSenha());
    }
}
