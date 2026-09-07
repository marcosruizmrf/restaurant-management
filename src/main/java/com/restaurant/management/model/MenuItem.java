package com.restaurant.management.model;

// MenuItem.java
@Entity
public class MenuItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    private Double preco;
    private Boolean disponivelNoLocal;
    private String fotoPath;

    @ManyToOne
    private Restaurant restaurant;

    // getters e setters
}