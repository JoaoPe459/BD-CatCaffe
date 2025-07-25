package br.edu.ufersa.CatCaffe.models.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoRequestDto {
    private String nome_produto;
    private BigDecimal preco;
    private String item;
    private String adicional;
    private Long id_compra;
}
