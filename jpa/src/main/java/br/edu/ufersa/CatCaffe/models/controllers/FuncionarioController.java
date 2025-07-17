package br.edu.ufersa.CatCaffe.models.controllers;

import br.edu.ufersa.CatCaffe.models.dtos.request.FuncionarioRequestDto;
import br.edu.ufersa.CatCaffe.models.dtos.response.FuncionarioResponseDto;
import br.edu.ufersa.CatCaffe.models.services.FuncionarioServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    private final FuncionarioServices funcionarioServices;

    public FuncionarioController(FuncionarioServices funcionarioServices) {
        this.funcionarioServices = funcionarioServices;
    }

    // POST - Criar funcionário
    @PostMapping
    public ResponseEntity<FuncionarioResponseDto> criarFuncionario(@RequestBody FuncionarioRequestDto dto) {
        FuncionarioResponseDto novoFuncionario = funcionarioServices.saveFuncionario(dto);
        return new ResponseEntity<>(novoFuncionario, HttpStatus.CREATED);
    }

    // GET - Listar todos os funcionários
    @GetMapping
    public ResponseEntity<List<FuncionarioResponseDto>> listarFuncionarios() {
        return ResponseEntity.ok(funcionarioServices.getAllFuncionarios());
    }

    // PUT - Atualizar funcionário
    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioResponseDto> atualizarFuncionario(@PathVariable Long id, @RequestBody FuncionarioRequestDto dto) {
        FuncionarioResponseDto funcionarioAtualizado = funcionarioServices.editFuncionario(id, dto);
        return ResponseEntity.ok(funcionarioAtualizado);
    }

    // DELETE - Remover funcionário
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFuncionario(@PathVariable Long id) {
        funcionarioServices.deleteFuncionario(id);
        return ResponseEntity.noContent().build();
    }
}
