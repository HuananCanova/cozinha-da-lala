package com.ufsm.csi.cozinhaapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cardapio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private double preco;
    @Column(unique = true)
    private LocalDate data;
    private String diaSemana;

    public String obterNomeDiaDaSemana() {
        return this.data.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault());
    }

    public Cardapio(Long id, String descricao, double preco, LocalDate data) {
        this.id = id;
        this.descricao = descricao;
        this.preco = preco;
        this.data = data;
    }
}
