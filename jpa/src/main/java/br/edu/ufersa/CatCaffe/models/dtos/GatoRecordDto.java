package br.edu.ufersa.CatCaffe.models.dtos;

public record GatoRecordDto(Long id_gato,
                            String nome,
                            String raca,
                            int idade,
                            String status_adocao,
                            String status_saude,
                            Long id_cliente) {
}
