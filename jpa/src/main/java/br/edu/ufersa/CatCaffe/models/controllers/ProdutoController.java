package br.edu.ufersa.CatCaffe.models.controllers;

import br.edu.ufersa.CatCaffe.models.dtos.ProdutoRecordDto;
import br.edu.ufersa.CatCaffe.models.entities.Produto;
import br.edu.ufersa.CatCaffe.models.services.ProdutoServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoServices produtoServices;

    public ProdutoController(ProdutoServices produtoServices) {
        this.produtoServices = produtoServices;
    }

    // POST - Criar produto
    @PostMapping
    public ResponseEntity<Produto> criarProduto(@RequestBody ProdutoRecordDto dto) {
        Produto novoProduto = produtoServices.saveProduto(dto);
        return new ResponseEntity<>(novoProduto, HttpStatus.CREATED);
    }

    // GET - Listar todos os produtos
    @GetMapping
    public ResponseEntity<List<Produto>> listarProdutos() {
        return ResponseEntity.ok(produtoServices.getAllProdutos());
    }

    // PUT - Atualizar produto
    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable Long id, @RequestBody ProdutoRecordDto dto) {
        ProdutoRecordDto dtoAtualizado = new ProdutoRecordDto(
                id,
                dto.nome_produto(),
                dto.preco(),
                dto.item(),
                dto.adicional(),
                dto.id_compra()
        );

        Produto produtoAtualizado = produtoServices.editProduto(dtoAtualizado);
        return ResponseEntity.ok(produtoAtualizado);
    }

    // DELETE - Remover produto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        produtoServices.deleteProduto(id);
        return ResponseEntity.noContent().build();
    }
}
