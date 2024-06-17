package com.ufsm.csi.cozinhaapi.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    private String senha;

    private String nome;

    private String telefone;

    @Column(nullable = true)
    private String endereco;

    @Enumerated(EnumType.STRING)
    private Role role;

}