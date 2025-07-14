package br.edu.ufersa.CatCaffe.models.controllers;

import br.edu.ufersa.CatCaffe.models.dtos.CompraRecordDto;
import br.edu.ufersa.CatCaffe.models.entities.Compra;
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
    public ResponseEntity<Compra> criarCompra(@RequestBody CompraRecordDto dto){
        Compra novaCompra = compraServices.saveCompra(dto);
        return new ResponseEntity<>(novaCompra, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity <List<Compra>> listarCompra(){
        return ResponseEntity.ok(compraServices.getAllCompras());
    }
    @PutMapping
    public ResponseEntity<Compra> atualizarCompra(@PathVariable Long id, @RequestBody CompraRecordDto dto){
        CompraRecordDto dtoAtualizado = new CompraRecordDto(
                id,
                dto.id_pedido(),
                dto.entrega()
        );
        Compra compraEditada = compraServices.editCompra(dtoAtualizado);
        return ResponseEntity.ok(compraEditada);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Compra> deletarCompra(@PathVariable Long id){
        compraServices.deleteCompra(id);
        return ResponseEntity.noContent().build();
    }
}
