package br.edu.ufersa.CatCaffe.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Reservar_Espaco {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_reserva;

    @Column(nullable = false)
    private Date data;

    @Column(nullable = false)
    private Time horario;

    @ManyToMany(mappedBy = "cliente_reserva", fetch = FetchType.LAZY)
    private Set<Cliente> cliente = new HashSet<>();
}
