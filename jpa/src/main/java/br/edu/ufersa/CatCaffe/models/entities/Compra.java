package br.edu.ufersa.CatCaffe.models.entities;

import jakarta.persistence.*;

@Entity
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_compra;

    @Column(nullable = false)
    private int preco_total;

    @Column(nullable = false)
    private boolean entrega;

    public int getId_cliente() {
        return id_compra;
    }

    public void setId_cliente(int id_cliente) {
        this.id_compra = id_cliente;
    }

    public boolean isEntrega() {
        return entrega;
    }

    public void setEntrega(boolean entrega) {
        this.entrega = entrega;
    }

    public int getPreco_total() {
        return preco_total;
    }

    public void setPreco_total(int preco_total) {
        this.preco_total = preco_total;
    }
}
