package br.edu.ufersa.CatCaffe.models.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteEnderecoResponseDto {
    private String nomeCliente;
    private String bairro;
}
