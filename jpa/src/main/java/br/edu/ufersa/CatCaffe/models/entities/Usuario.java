package br.edu.ufersa.CatCaffe.models.entities;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_usuario;

    @Column(nullable = false, unique = true,length = 50)
    private String username;

    @Column(nullable = false, unique = true,length = 70)
    private String email;

    @Column(unique = true,length = 12)
    private String telefone;

    @Column(nullable = false,length = 30)
    private String senha;

    public Usuario(String username, String email, String telefone, String senha) {
        this.username = username;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
    }
}
