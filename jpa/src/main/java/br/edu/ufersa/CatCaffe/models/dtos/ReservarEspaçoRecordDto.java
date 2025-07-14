package br.edu.ufersa.CatCaffe.models.dtos;

import java.sql.Time;
import java.util.Date;

public record ReservarEspaçoRecordDto(Long id_reserva,
                                      Long id_cliente,
                                      Date data,
                                      Time horario) {
}
