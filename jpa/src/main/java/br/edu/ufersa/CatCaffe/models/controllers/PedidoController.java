package br.edu.ufersa.CatCaffe.models.controllers;

import br.edu.ufersa.CatCaffe.models.dtos.PedidoRecordDto;
import br.edu.ufersa.CatCaffe.models.entities.Pedido;
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
    public ResponseEntity<Pedido> criarPedido(@RequestBody PedidoRecordDto dto) {
        Pedido novoPedido = pedidoServices.savePedido(dto);
        return new ResponseEntity<>(novoPedido, HttpStatus.CREATED);
    }

    // GET - Listar todos os pedidos
    @GetMapping
    public ResponseEntity<List<Pedido>> listarPedidos() {
        return ResponseEntity.ok(pedidoServices.getAllPedidos());
    }

    // PUT - Atualizar pedido
    @PutMapping("/{id}")
    public ResponseEntity<Pedido> atualizarPedido(@PathVariable Long id, @RequestBody PedidoRecordDto dto) {
        PedidoRecordDto dtoAtualizado = new PedidoRecordDto(
                id,
                dto.id_cliente(),
                dto.id_funcionario(),
                dto.data(),
                dto.hora(),
                dto.status(),
                dto.forma_pag()
        );

        Pedido pedidoAtualizado = pedidoServices.editPedido(dtoAtualizado);
        return ResponseEntity.ok(pedidoAtualizado);
    }

    // DELETE - Remover pedido
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPedido(@PathVariable Long id) {
        pedidoServices.deletePedido(id);
        return ResponseEntity.noContent().build();
    }
}
