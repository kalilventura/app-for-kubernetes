package br.com.github.kalilventura.pizzaria;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "pizzas")
public class JpaPizza {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column @Getter
    private String name;

    @Column @Getter
    private String description;
}
