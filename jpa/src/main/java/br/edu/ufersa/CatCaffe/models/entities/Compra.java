package br.edu.ufersa.CatCaffe.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
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
    private Long id_compra;

    @Column(nullable = false)
    private BigDecimal precoTotal;

    @Column(nullable = false)
    private boolean entrega;

    @OneToMany(mappedBy = "compra")
    private Set<Produto> produto = new HashSet<>() ;

    @ManyToOne
    @JoinColumn(name = "id_pedido")
    private Pedido pedido;
}
