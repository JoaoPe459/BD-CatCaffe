package br.edu.ufersa.CatCaffe.models.entities;

import jakarta.persistence.*;

import java.sql.Time;
import java.util.Date;

@Entity
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

    public void setId(int id) {
        this.id_pedido = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public int getId() {
        return id_pedido;
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }
}
