package br.edu.ufersa.CatCaffe.models.dtos;

import br.edu.ufersa.CatCaffe.models.entities.Cliente;

public record EnderecoRecordDto(Long id_endereco,
                                String rua,
                                String bairro,
                                String n_casa,
                                Long id_cliente) {
}
