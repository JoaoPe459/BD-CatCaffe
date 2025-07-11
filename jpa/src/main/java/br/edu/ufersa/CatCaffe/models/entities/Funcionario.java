package br.edu.ufersa.CatCaffe.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Funcionario extends Usuario{

    @Column(nullable = false,length = 50)
    private String nome;

    @Column(nullable = false)
    private int salario;

    @Column(nullable = false,length = 50)
    private String cargo;

    @Column
    @OneToMany
    private Produto produto;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getNome() {
        return nome;
    }

    public String getCargo() {
        return cargo;
    }

    public int getSalario() {
        return salario;
    }

}
