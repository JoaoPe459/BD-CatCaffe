package br.edu.ufersa.CatCaffe.models.controllers;

import br.edu.ufersa.CatCaffe.models.dtos.request.GatoRequestDto;
import br.edu.ufersa.CatCaffe.models.dtos.response.GatoResponseDto;
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
    public ResponseEntity<GatoResponseDto> criarGato(@RequestBody GatoRequestDto dto) {
        GatoResponseDto novoGato = gatoServices.saveGato(dto);
        return new ResponseEntity<>(novoGato, HttpStatus.CREATED);
    }

    // GET - Listar todos os gatos
    @GetMapping
    public ResponseEntity<List<GatoResponseDto>> listarGatos() {
        return ResponseEntity.ok(gatoServices.getAllGatos());
    }

    // PUT - Atualizar gato
    @PutMapping("/{id}")
    public ResponseEntity<GatoResponseDto> atualizarGato(@PathVariable Long id, @RequestBody GatoRequestDto dto) {
        GatoResponseDto gatoAtualizado = gatoServices.editGato(id, dto);
        return ResponseEntity.ok(gatoAtualizado);
    }

    // DELETE - Remover gato
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarGato(@PathVariable Long id) {
        gatoServices.deleteGato(id);
        return ResponseEntity.noContent().build();
    }
}
