package br.edu.ufersa.CatCaffe.models.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservarEspacoRequestDto {
    private Set<Long> id_cliente;
    private Date data;
    private Time horario;
}
