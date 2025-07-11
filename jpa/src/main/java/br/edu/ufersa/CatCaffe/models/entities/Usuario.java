package br.edu.ufersa.CatCaffe.models.entities;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

@Entity
public abstract class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_usuario;

    @Column(nullable = false, unique = true,length = 50)
    private String username;

    @Column(nullable = false, unique = true,length = 70)
    private String email;

    @Column(unique = true,length = 12)
    private String telefone;

    @Column(nullable = false,length = 30)
    private String senha;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setId(int id) {
        if (id>0){
        this.id_usuario = id;
    }}

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getId() {
        return id_usuario;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getSenha() {
        return senha;
    }

}
