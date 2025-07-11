package br.edu.ufersa.CatCaffe.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_endereco;

    @Column(nullable = false)
    private String rua;

    @Column(nullable = false)
    private String bairro;

    @Column(nullable = false)
    private String n_casa;

    @OneToOne
    private Cliente cliente;

}
