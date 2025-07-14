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
    private Long id_endereco;

    @Column(nullable = false)
    private String ruaENumero;

    @Column(nullable = false)
    private String cidade;

    @Column(nullable = false)
    private String cep;

    @OneToOne(mappedBy = "endereco")
    private Cliente cliente;

}
