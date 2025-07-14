package br.edu.ufersa.CatCaffe.models.dtos;

public record EnderecoRecordDto(Long id_endereco,
                                String rua,
                                String bairro,
                                String n_casa,
                                Long id_cliente) {
}
