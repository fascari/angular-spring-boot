package io.github.fascari.agenda.model.repository;

import io.github.fascari.agenda.model.entity.Contatos;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContatosRepository extends JpaRepository<Contatos, Integer> {
}
