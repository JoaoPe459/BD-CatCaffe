package br.edu.ufersa.CatCaffe.models.dtos;


public record CompraRecordDto(Long id_compra,
                              Long id_pedido,
                              Boolean entrega) {
}
