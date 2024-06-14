package com.ufsm.csi.cozinhaapi.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class Cardapio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private double preco;

}
