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
    private int id_espaco;

    @Column(nullable = false)
    private Date date_espaco;

    @Column(nullable = false)
    private Time time_espaco;

    @ManyToMany(mappedBy = "reservarEspacos", fetch = FetchType.LAZY)
    private Set<Cliente> cliente = new HashSet<>();
}
