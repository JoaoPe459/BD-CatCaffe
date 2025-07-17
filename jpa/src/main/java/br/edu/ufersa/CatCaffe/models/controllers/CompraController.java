package br.edu.ufersa.CatCaffe.models.controllers;

import br.edu.ufersa.CatCaffe.models.dtos.request.CompraRequestDto;
import br.edu.ufersa.CatCaffe.models.dtos.response.CompraResponseDto;
import br.edu.ufersa.CatCaffe.models.services.CompraServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/compras")
public class CompraController {

    private final CompraServices compraServices;

    public CompraController(CompraServices compraServices) {
        this.compraServices = compraServices;
    }

    // POST: Criar nova compra
    @PostMapping
    public ResponseEntity<CompraResponseDto> criarCompra(@RequestBody CompraRequestDto dto) {
        CompraResponseDto novaCompra = compraServices.saveCompra(dto);
        return new ResponseEntity<>(novaCompra, HttpStatus.CREATED);
    }

    // GET: Listar todas as compras
    @GetMapping
    public ResponseEntity<List<CompraResponseDto>> listarCompra() {
        return ResponseEntity.ok(compraServices.getAllCompras());
    }

    // PUT: Atualizar compra
    @PutMapping("/{id}")
    public ResponseEntity<CompraResponseDto> atualizarCompra(
            @PathVariable Long id,
            @RequestBody CompraRequestDto dto
    ) {
        CompraResponseDto compraAtualizada = compraServices.editCompra(id, dto);
        return ResponseEntity.ok(compraAtualizada);
    }

    // DELETE: Deletar compra
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCompra(@PathVariable Long id) {
        compraServices.deleteCompra(id);
        return ResponseEntity.noContent().build();
    }
}
