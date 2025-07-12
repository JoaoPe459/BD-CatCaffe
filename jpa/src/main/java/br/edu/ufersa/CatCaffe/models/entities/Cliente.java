package br.edu.ufersa.CatCaffe.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private Set<Pedido> pedidos;

    @Column
    @OneToMany(mappedBy = "id_gato")
    private Set<Gato> gato = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;

    @ManyToMany
    @JoinTable(
            name = "Cliente_reserva",
            joinColumns = @JoinColumn(name = "id_cliente"),
            inverseJoinColumns = @JoinColumn (name = "id_reserva"))
    private Set<Reservar_Espaco> reservarEspacos = new HashSet<>();

}
