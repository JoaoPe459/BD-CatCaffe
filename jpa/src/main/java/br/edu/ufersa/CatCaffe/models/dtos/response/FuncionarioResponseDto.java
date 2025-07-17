package br.edu.ufersa.CatCaffe.models.dtos.response;

import java.math.BigDecimal;

public record FuncionarioResponseDto(
        Long id_funcionario,
        String nome,
        BigDecimal salario,
        String cargo
) {
}
