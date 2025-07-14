package br.edu.ufersa.CatCaffe.models.controllers;

import br.edu.ufersa.CatCaffe.models.dtos.ReservarEspacoRecordDto;
import br.edu.ufersa.CatCaffe.models.entities.Reservar_Espaco;
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
    public ResponseEntity<Reservar_Espaco> criarReserva(@RequestBody ReservarEspacoRecordDto dto) {
        Reservar_Espaco reserva = reservarEspacoServices.saveReserva(dto);
        return new ResponseEntity<>(reserva, HttpStatus.CREATED);
    }

    // GET - Listar todas as reservas
    @GetMapping
    public ResponseEntity<List<Reservar_Espaco>> listarReservas() {
        return ResponseEntity.ok(reservarEspacoServices.getAllReservas());
    }

    // PUT - Atualizar reserva
    @PutMapping("/{id}")
    public ResponseEntity<Reservar_Espaco> atualizarReserva(@PathVariable Long id, @RequestBody ReservarEspacoRecordDto dto) {
        ReservarEspacoRecordDto dtoAtualizado = new ReservarEspacoRecordDto(
                id,
                dto.id_cliente(),
                dto.data(),
                dto.horario()
        );
        Reservar_Espaco reservaAtualizada = reservarEspacoServices.editReserva(dtoAtualizado);
        return ResponseEntity.ok(reservaAtualizada);
    }

    // DELETE - Deletar reserva
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarReserva(@PathVariable Long id) {
        reservarEspacoServices.deleteReserva(id);
        return ResponseEntity.noContent().build();
    }
}
