package com.restaurant.management.model;

// Restaurant.java
@Entity
public class Restaurant {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String endereco;
    private String tipoCozinha;
    private String horarioFuncionamento;

    @ManyToOne
    private User owner; // Dono do restaurante

    // getters e setters
}