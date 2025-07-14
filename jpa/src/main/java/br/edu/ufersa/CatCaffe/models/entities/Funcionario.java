package br.edu.ufersa.CatCaffe.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Funcionario extends Usuario{

    @Column(nullable = false,length = 50)
    private String nome;

    @Column(nullable = false)
    private BigDecimal salario;

    @Column(nullable = false,length = 50)
    private String cargo;

    @Column
    @OneToMany(mappedBy = "funcionario",fetch = FetchType.LAZY)
    private Set<Pedido> pedidos = new HashSet<>();


}
