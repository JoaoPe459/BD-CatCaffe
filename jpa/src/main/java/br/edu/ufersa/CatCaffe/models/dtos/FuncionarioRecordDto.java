package br.edu.ufersa.CatCaffe.models.dtos;

import java.math.BigDecimal;

public record FuncionarioRecordDto(Long id_usuario,
                                   String nome,
                                   long salario,
                                   BigDecimal cargo) {
}
