package br.edu.ufersa.CatCaffe.models.controllers;

import br.edu.ufersa.CatCaffe.models.dtos.request.EnderecoRequestDto;
import br.edu.ufersa.CatCaffe.models.dtos.response.EnderecoResponseDto;
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
    public ResponseEntity<EnderecoResponseDto> criarEndereco(@RequestBody EnderecoRequestDto dto) {
        EnderecoResponseDto novoEndereco = enderecoServices.saveEndereco(dto);
        return new ResponseEntity<>(novoEndereco, HttpStatus.CREATED);
    }

    // GET: Listar todos os endereços
    @GetMapping
    public ResponseEntity<List<EnderecoResponseDto>> listarEnderecos() {
        return ResponseEntity.ok(enderecoServices.getAllEndereco());
    }

    // PUT: Atualizar endereço por ID
    @PutMapping("/{id}")
    public ResponseEntity<EnderecoResponseDto> atualizarEndereco(
            @PathVariable Long id,
            @RequestBody EnderecoRequestDto dto
    ) {
        EnderecoResponseDto enderecoAtualizado = enderecoServices.editEndereco(id, dto);
        return ResponseEntity.ok(enderecoAtualizado);
    }

    // DELETE: Deletar endereço por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEndereco(@PathVariable Long id) {
        enderecoServices.deleteEndereco(id);
        return ResponseEntity.noContent().build();
    }
}
