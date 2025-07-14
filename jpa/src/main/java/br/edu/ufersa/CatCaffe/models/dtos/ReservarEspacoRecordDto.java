package br.edu.ufersa.CatCaffe.models.dtos;

import java.sql.Time;
import java.util.Date;
import java.util.Set;

public record ReservarEspacoRecordDto(Long id_reserva,
                                      Set<Long> id_cliente,
                                      Date data,
                                      Time horario) {
}
