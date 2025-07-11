package br.edu.ufersa.CatCaffe.models.entities;

import jakarta.persistence.*;

@Entity
public class Gato {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_gato;

    @Column(nullable = false,length = 50)
    private String nome_gato;

    @Column(nullable = false,length = 50)
    private String raca;

    @Column(nullable = false)
    private int idade;

    @Column(nullable = false,length = 50)
    private String status_adocao;

    @Column(nullable = false,length = 50)
    private String status_saude;

    public int getId() {
        return id_gato;
    }

    public void setId(int id) {
        this.id_gato = id;
    }

    public String getStatus_saude() {
        return status_saude;
    }

    public void setStatus_saude(String status_saude) {
        this.status_saude = status_saude;
    }

    public String getStatus_adocao() {
        return status_adocao;
    }

    public void setStatus_adocao(String status_adocao) {
        this.status_adocao = status_adocao;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        if (idade>-1){
        this.idade = idade;
    }}

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getNome() {
        return nome_gato;
    }

    public void setNome(String nome) {
        this.nome_gato = nome;
    }
}
