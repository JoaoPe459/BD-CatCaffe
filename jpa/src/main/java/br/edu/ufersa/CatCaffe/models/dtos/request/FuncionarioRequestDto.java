package br.edu.ufersa.CatCaffe.models.dtos.request;

import java.math.BigDecimal;

public record FuncionarioRequestDto(
        String nome,
        BigDecimal salario,
        String cargo
) {
}
