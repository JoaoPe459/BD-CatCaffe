package br.edu.ufersa.CatCaffe.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private int precoTotal;

    @Column(nullable = false)
    private boolean entrega;

    @OneToMany
    private Set<Produto> produto = new HashSet<>() ;

    @ManyToOne
    @JoinColumn(name = "id_pedido")
    private Pedido pedido;
}
