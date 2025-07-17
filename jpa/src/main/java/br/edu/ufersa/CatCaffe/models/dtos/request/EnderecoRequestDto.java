package br.edu.ufersa.CatCaffe.models.dtos.request;

public record EnderecoRequestDto(
        String ruaENumero,
        String cidade,
        String cep,
        Long id_cliente
) {}
