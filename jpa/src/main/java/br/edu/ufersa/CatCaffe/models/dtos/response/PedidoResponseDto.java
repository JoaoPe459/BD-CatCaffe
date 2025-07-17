package br.edu.ufersa.CatCaffe.models.dtos.response;

import java.sql.Time;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoResponseDto {
    private Long id_pedido;
    private Long id_cliente;
    private Long id_funcionario;
    private Date data;
    private Time hora;
    private String status;
    private String forma_pag;
}
