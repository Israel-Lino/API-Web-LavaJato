package com.lavajatoapi.projeto.repository;

import com.lavajatoapi.projeto.entities.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProduto extends JpaRepository<Produtos, Long> {
    List<Produtos> findByClienteId(Long clienteID);
}
