package br.edu.ufersa.CatCaffe.models.controllers;

import br.edu.ufersa.CatCaffe.models.dtos.request.ProdutoRequestDto;
import br.edu.ufersa.CatCaffe.models.dtos.response.ProdutoResponseDto;
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
    public ResponseEntity<ProdutoResponseDto> criarProduto(@RequestBody ProdutoRequestDto dto) {
        ProdutoResponseDto novoProduto = produtoServices.saveProduto(dto);
        return new ResponseEntity<>(novoProduto, HttpStatus.CREATED);
    }

    // GET - Listar todos os produtos
    @GetMapping
    public ResponseEntity<List<ProdutoResponseDto>> listarProdutos() {
        return ResponseEntity.ok(produtoServices.getAllProdutos());
    }

    // PUT - Atualizar produto
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDto> atualizarProduto(@PathVariable Long id, @RequestBody ProdutoRequestDto dto) {
        ProdutoResponseDto produtoAtualizado = produtoServices.editProduto(id, dto);
        return ResponseEntity.ok(produtoAtualizado);
    }

    // DELETE - Remover produto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        produtoServices.deleteProduto(id);
        return ResponseEntity.noContent().build();
    }
}
