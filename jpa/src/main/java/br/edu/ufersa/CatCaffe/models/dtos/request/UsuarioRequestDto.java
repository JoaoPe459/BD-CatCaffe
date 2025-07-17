package br.edu.ufersa.CatCaffe.models.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequestDto {
    private String username;
    private String email;
    private String telefone;
    private String senha;
}
