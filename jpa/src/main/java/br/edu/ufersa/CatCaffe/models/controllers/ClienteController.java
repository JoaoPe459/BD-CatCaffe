package br.edu.ufersa.CatCaffe.models.controllers;

import br.edu.ufersa.CatCaffe.models.dtos.request.ClienteRequestDto;
import br.edu.ufersa.CatCaffe.models.dtos.response.ClienteResponseDto;
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
    public ResponseEntity<ClienteResponseDto> criarCliente(@RequestBody ClienteRequestDto dto) {
        ClienteResponseDto novoCliente = clienteServices.saveCiente(dto);
        return new ResponseEntity<>(novoCliente, HttpStatus.CREATED);
    }

    // GET: Listar todos os clientes
    @GetMapping
    public ResponseEntity<List<ClienteResponseDto>> listarClientes() {
        return ResponseEntity.ok(clienteServices.getAllClientes());
    }

    // PUT: Atualizar cliente
    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDto> atualizarCliente(@PathVariable Long id, @RequestBody ClienteRequestDto dto) {
        ClienteResponseDto clienteEditado = clienteServices.editCliente(id, dto);
        return ResponseEntity.ok(clienteEditado);
    }

    // DELETE: Deletar cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
        clienteServices.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }

    // GET: Listar clientes com gatos (endpoint alternativo)
    @GetMapping("/com-gatos")
    public ResponseEntity<List<Cliente>> listarClientesComGatos() {
        return ResponseEntity.ok(clienteServices.getAllClientesComGatos());
    }

}
