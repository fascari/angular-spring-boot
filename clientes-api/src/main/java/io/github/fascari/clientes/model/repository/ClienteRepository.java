package io.github.fascari.clientes.model.repository;

import io.github.fascari.clientes.model.entity.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}