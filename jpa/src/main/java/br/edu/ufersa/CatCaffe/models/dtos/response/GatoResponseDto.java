package br.edu.ufersa.CatCaffe.models.dtos.response;

public record GatoResponseDto(
        Long id_gato,
        String nome,
        String raca,
        int idade,
        String status_adocao,
        String status_saude,
        Long id_cliente
) {}
