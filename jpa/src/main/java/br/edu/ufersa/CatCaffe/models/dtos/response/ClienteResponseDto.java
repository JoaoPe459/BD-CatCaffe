package br.edu.ufersa.CatCaffe.models.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteResponseDto {
    private Long id_cliente;
    private String nomeCliente;
    private Long id_endereco;

}
