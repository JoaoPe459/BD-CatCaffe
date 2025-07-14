package br.edu.ufersa.CatCaffe.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_produto;

    @Column(nullable = false,length = 50)
    private String nome_produto;

    @Column(nullable = false)
    private BigDecimal preco;

    @Column(nullable = false,length = 50)
    private String item;

    @Column(nullable = false,length = 50)
    private String adicional;

    @ManyToOne
    @JoinColumn(name = "id_compra")
    private Compra compra;
}
