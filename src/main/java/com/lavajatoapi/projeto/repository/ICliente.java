package com.lavajatoapi.projeto.repository;

import com.lavajatoapi.projeto.entities.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICliente extends JpaRepository<Clientes, Long> {
}
