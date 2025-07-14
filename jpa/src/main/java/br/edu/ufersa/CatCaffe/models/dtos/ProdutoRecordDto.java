package br.edu.ufersa.CatCaffe.models.dtos;

import java.math.BigDecimal;

public record ProdutoRecordDto(Long id_produto,
                               String nome_produto,
                               BigDecimal preco,
                               String item,
                               String adicional,
                               Long id_compra) {
}
