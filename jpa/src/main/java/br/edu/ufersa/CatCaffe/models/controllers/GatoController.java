package br.edu.ufersa.CatCaffe.models.controllers;

import br.edu.ufersa.CatCaffe.models.dtos.GatoRecordDto;
import br.edu.ufersa.CatCaffe.models.entities.Gato;
import br.edu.ufersa.CatCaffe.models.services.GatoServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gatos")
public class GatoController {

    private final GatoServices gatoServices;

    public GatoController(GatoServices gatoServices) {
        this.gatoServices = gatoServices;
    }

    // POST - Criar gato
    @PostMapping
    public ResponseEntity<Gato> criarGato(@RequestBody GatoRecordDto dto) {
        Gato novoGato = gatoServices.saveGato(dto);
        return new ResponseEntity<>(novoGato, HttpStatus.CREATED);
    }

    // GET - Listar todos os gatos
    @GetMapping
    public ResponseEntity<List<Gato>> listarGatos() {
        return ResponseEntity.ok(gatoServices.getAllGatos());
    }

    // PUT - Atualizar gato
    @PutMapping("/{id}")
    public ResponseEntity<Gato> atualizarGato(@PathVariable Long id, @RequestBody GatoRecordDto dto) {
        GatoRecordDto dtoAtualizado = new GatoRecordDto(
                id,
                dto.nome(),
                dto.raca(),
                dto.idade(),
                dto.status_adocao(),
                dto.status_saude(),
                dto.id_cliente()
        );
        Gato gatoAtualizado = gatoServices.editGato(dtoAtualizado);
        return ResponseEntity.ok(gatoAtualizado);
    }

    // DELETE - Remover gato
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarGato(@PathVariable Long id) {
        gatoServices.deleteGato(id);
        return ResponseEntity.noContent().build();
    }
}
