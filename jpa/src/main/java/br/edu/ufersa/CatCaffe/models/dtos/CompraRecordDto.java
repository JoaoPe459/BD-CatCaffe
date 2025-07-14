package br.edu.ufersa.CatCaffe.models.dtos;

import br.edu.ufersa.CatCaffe.models.entities.Pedido;

public record CompraRecordDto(Long id_compra,
                              Long id_pedido,
                              Boolean entrega) {
}
