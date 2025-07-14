package br.edu.ufersa.CatCaffe.models.dtos;

public record EnderecoRecordDto(Long id_endereco,
                                String ruaENumero,
                                String cidade,
                                String cep,
                                Long id_cliente) {
}
