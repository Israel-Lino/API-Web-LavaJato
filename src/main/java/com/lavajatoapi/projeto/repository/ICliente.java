package com.lavajatoapi.projeto.repository;

import com.lavajatoapi.projeto.entities.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICliente extends JpaRepository<Clientes, Long> {
    Optional<Clientes> findById(Long id);
}
