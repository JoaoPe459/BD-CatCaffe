package br.edu.ufersa.CatCaffe.models.dtos;

public record ProdutoRecordDto(Long id_produto,
                               String nome_produto,
                               long preco,
                               String item,
                               String adicional,
                               Long id_compra) {
}
