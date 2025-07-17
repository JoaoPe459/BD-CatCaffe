package br.edu.ufersa.CatCaffe.models.controllers;

import br.edu.ufersa.CatCaffe.models.dtos.request.ReservarEspacoRequestDto;
import br.edu.ufersa.CatCaffe.models.dtos.response.ReservarEspacoResponseDto;
import br.edu.ufersa.CatCaffe.models.services.ReservarEspacoServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservarEspacoController {

    private final ReservarEspacoServices reservarEspacoServices;

    public ReservarEspacoController(ReservarEspacoServices reservarEspacoServices) {
        this.reservarEspacoServices = reservarEspacoServices;
    }

    // POST - Criar reserva
    @PostMapping
    public ResponseEntity<ReservarEspacoResponseDto> criarReserva(@RequestBody ReservarEspacoRequestDto dto) {
        ReservarEspacoResponseDto reserva = reservarEspacoServices.saveReserva(dto);
        return new ResponseEntity<>(reserva, HttpStatus.CREATED);
    }

    // GET - Listar todas as reservas
    @GetMapping
    public ResponseEntity<List<ReservarEspacoResponseDto>> listarReservas() {
        return ResponseEntity.ok(reservarEspacoServices.getAllReservas());
    }

    // PUT - Atualizar reserva
    @PutMapping("/{id}")
    public ResponseEntity<ReservarEspacoResponseDto> atualizarReserva(@PathVariable Long id, @RequestBody ReservarEspacoRequestDto dto) {
        ReservarEspacoResponseDto reservaAtualizada = reservarEspacoServices.editReserva(id, dto);
        return ResponseEntity.ok(reservaAtualizada);
    }

    // DELETE - Deletar reserva
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarReserva(@PathVariable Long id) {
        reservarEspacoServices.deleteReserva(id);
        return ResponseEntity.noContent().build();
    }
}
