package br.edu.ufersa.CatCaffe.models.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoResponseDto {
    private Long id_produto;
    private String nome_produto;
    private BigDecimal preco;
    private String item;
    private String adicional;
    private Long id_compra;
}
