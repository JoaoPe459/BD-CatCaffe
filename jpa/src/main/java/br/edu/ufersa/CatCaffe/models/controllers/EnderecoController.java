package br.edu.ufersa.CatCaffe.models.controllers;

import br.edu.ufersa.CatCaffe.models.dtos.EnderecoRecordDto;
import br.edu.ufersa.CatCaffe.models.entities.Endereco;
import br.edu.ufersa.CatCaffe.models.services.EnderecoServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    private final EnderecoServices enderecoServices;

    public EnderecoController(EnderecoServices enderecoServices) {
        this.enderecoServices = enderecoServices;
    }

    // POST: Criar novo endereço
    @PostMapping
    public ResponseEntity<Endereco> criarEndereco(@RequestBody EnderecoRecordDto dto) {
        Endereco novoEndereco = enderecoServices.saveEndereco(dto);
        return new ResponseEntity<>(novoEndereco, HttpStatus.CREATED);
    }

    // GET: Listar todos os endereços
    @GetMapping
    public ResponseEntity<List<Endereco>> listarEnderecos() {
        return ResponseEntity.ok(enderecoServices.getAllEndereco());
    }

    // PUT: Atualizar endereço por ID
    @PutMapping("/{id}")
    public ResponseEntity<Endereco> atualizarEndereco(@PathVariable Long id, @RequestBody EnderecoRecordDto dto) {
        EnderecoRecordDto dtoAtualizado = new EnderecoRecordDto(
                id,
                dto.rua(),
                dto.bairro(),
                dto.n_casa(),
                dto.id_cliente()
        );
        Endereco enderecoEditado = enderecoServices.editEndereco(dtoAtualizado);
        return ResponseEntity.ok(enderecoEditado);
    }

    // DELETE: Deletar endereço por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEndereco(@PathVariable Long id) {
        enderecoServices.deleteEndereco(id);
        return ResponseEntity.noContent().build();
    }
}
