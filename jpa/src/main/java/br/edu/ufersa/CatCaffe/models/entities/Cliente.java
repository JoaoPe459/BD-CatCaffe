package br.edu.ufersa.CatCaffe.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    @Column(name = "id_cliente")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String nomeCliente;

    @Column
    @OneToMany(mappedBy = "id_pedido")
    private List<Pedido> pedidos;

    @Column
    @OneToMany(mappedBy = "id_gato")
    private List<Gato> gato;

    @OneToOne
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;


}
