package br.edu.ufersa.CatCaffe.models.dtos.request;

public record GatoRequestDto(
        String nome,
        String raca,
        int idade,
        String status_adocao,
        String status_saude,
        Long id_cliente
) {}
