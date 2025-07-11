package br.edu.ufersa.CatCaffe.models.entities;

import jakarta.persistence.*;

import java.sql.Time;
import java.util.Date;

@Entity
public class Reservar_Espaco {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_espaco;

    @Column(nullable = false)
    private Date date_espaco;

    @Column(nullable = false)
    private Time time_espaco;

    public int getId_espaco() {
        return id_espaco;
    }

    public void setId_espaco(int id_espaco) {
        this.id_espaco = id_espaco;
    }

    public Date getDate_espaco() {
        return date_espaco;
    }

    public void setDate_espaco(Date date_espaco) {
        this.date_espaco = date_espaco;
    }

    public Time getTime_espaco() {
        return time_espaco;
    }

    public void setTime_espaco(Time time_espaco) {
        this.time_espaco = time_espaco;
    }
}
