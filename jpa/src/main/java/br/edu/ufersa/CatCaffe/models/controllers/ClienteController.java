package br.edu.ufersa.CatCaffe.models.controllers;

import br.edu.ufersa.CatCaffe.models.dtos.ClienteRecordDto;
import br.edu.ufersa.CatCaffe.models.entities.Cliente;
import br.edu.ufersa.CatCaffe.models.services.ClienteServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteServices clienteServices;

    public ClienteController(ClienteServices clienteServices) {
        this.clienteServices = clienteServices;
    }

    // POST: Criar novo cliente
    @PostMapping
    public ResponseEntity<Cliente> criarCliente(@RequestBody ClienteRecordDto dto) {
        Cliente novoCliente = clienteServices.saveCiente(dto);
        return new ResponseEntity<>(novoCliente, HttpStatus.CREATED);
    }

    // Listar todos os clientes
    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        return ResponseEntity.ok(clienteServices.getAllClientes());
    }

    // Atualizar cliente
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long id, @RequestBody ClienteRecordDto dto) {
        ClienteRecordDto dtoAtualizado = new ClienteRecordDto(
                id,
                dto.nomeCliente(),
                dto.id_endereco()
        );
        Cliente clienteEditado = clienteServices.editCliente(dtoAtualizado);
        return ResponseEntity.ok(clienteEditado);
    }

    // Deletar cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
        clienteServices.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }
}
