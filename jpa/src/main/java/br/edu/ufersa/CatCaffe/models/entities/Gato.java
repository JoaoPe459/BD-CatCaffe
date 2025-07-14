package br.edu.ufersa.CatCaffe.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.lang.model.element.Name;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Gato {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_gato;

    @Column(nullable = false,length = 50)
    private String nome_gato;

    @Column(nullable = false,length = 50)
    private String raca;

    @Column(nullable = false)
    private int idade;

    @Column(nullable = false,length = 50)
    private String status_adocao;

    @Column(nullable = false,length = 50)
    private String status_saude;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

}
