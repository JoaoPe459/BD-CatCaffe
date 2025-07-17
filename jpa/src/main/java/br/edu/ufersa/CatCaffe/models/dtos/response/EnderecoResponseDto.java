package br.edu.ufersa.CatCaffe.models.dtos.response;

public record EnderecoResponseDto(
        Long id_endereco,
        String ruaENumero,
        String cidade,
        String cep,
        Long id_cliente
) {}
