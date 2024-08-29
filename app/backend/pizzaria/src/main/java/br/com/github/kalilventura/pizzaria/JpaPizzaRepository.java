package br.com.github.kalilventura.pizzaria;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPizzaRepository extends JpaRepository<JpaPizza, Long> {}
