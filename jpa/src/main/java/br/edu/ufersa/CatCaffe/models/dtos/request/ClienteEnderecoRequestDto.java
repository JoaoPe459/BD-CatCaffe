package br.edu.ufersa.CatCaffe.models.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteEnderecoRequestDto {
    private String nomeCliente;
    private String bairro;
}
