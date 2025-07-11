package br.edu.ufersa.CatCaffe.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_pedido;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private Time time;

    @Column(nullable = false,length = 20)
    private String status;

    @ManyToOne
    @JoinColumn(name = "id_funcionario")
    private Funcionario funcionario;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @Column(nullable = false,length = 20)
    private String forma_pag;

}
