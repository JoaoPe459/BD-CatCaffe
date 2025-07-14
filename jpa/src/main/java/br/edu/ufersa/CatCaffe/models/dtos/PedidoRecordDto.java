package br.edu.ufersa.CatCaffe.models.dtos;

import java.sql.Time;
import java.util.Date;

public record PedidoRecordDto(Long id_pedido,
                              Long id_cliente,
                              Long id_funcionario,
                              Date data,
                              Time hora,
                              String status,
                              String forma_pag) {
}
