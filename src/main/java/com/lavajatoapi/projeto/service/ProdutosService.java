package com.lavajatoapi.projeto.service;

import com.lavajatoapi.projeto.entities.Produtos;
import com.lavajatoapi.projeto.repository.IProduto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProdutosService {

    private final IProduto repository;

    public ProdutosService(IProduto repository) {
        this.repository = repository;
    }

    public List<Produtos> findAll(){
        return repository.findAll();
    }

    public Produtos cadastrarProduto(Produtos produto){
        if(produto.getData() == null){
            produto.setData(LocalDate.now());
        }
        return repository.save(produto);
    }

    public Produtos editarProduto(Produtos produto){
        return repository.save(produto);
    }

    public boolean excluirProduto(Long id){
        repository.deleteById(id);
        return true;
    }

    public List<Produtos> listarProdutosPorCliente(Long clienteID){
        return repository.findByClienteId(clienteID);
    }
}
