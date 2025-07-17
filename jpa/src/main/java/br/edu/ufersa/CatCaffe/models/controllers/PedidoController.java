package br.edu.ufersa.CatCaffe.models.controllers;

import br.edu.ufersa.CatCaffe.models.dtos.request.PedidoRequestDto;
import br.edu.ufersa.CatCaffe.models.dtos.response.PedidoResponseDto;
import br.edu.ufersa.CatCaffe.models.services.PedidoServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoServices pedidoServices;

    public PedidoController(PedidoServices pedidoServices) {
        this.pedidoServices = pedidoServices;
    }

    // POST - Criar pedido
    @PostMapping
    public ResponseEntity<PedidoResponseDto> criarPedido(@RequestBody PedidoRequestDto dto) {
        PedidoResponseDto novoPedido = pedidoServices.savePedido(dto);
        return new ResponseEntity<>(novoPedido, HttpStatus.CREATED);
    }

    // GET - Listar todos os pedidos
    @GetMapping
    public ResponseEntity<List<PedidoResponseDto>> listarPedidos() {
        return ResponseEntity.ok(pedidoServices.getAllPedidos());
    }

    // PUT - Atualizar pedido
    @PutMapping("/{id}")
    public ResponseEntity<PedidoResponseDto> atualizarPedido(@PathVariable Long id,
                                                             @RequestBody PedidoRequestDto dto) {
        PedidoResponseDto atualizado = pedidoServices.editPedido(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    // DELETE - Remover pedido
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPedido(@PathVariable Long id) {
        pedidoServices.deletePedido(id);
        return ResponseEntity.noContent().build();
    }
}
