package br.edu.ufersa.CatCaffe.models.controllers;

import br.edu.ufersa.CatCaffe.models.dtos.FuncionarioRecordDto;
import br.edu.ufersa.CatCaffe.models.entities.Funcionario;
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
    public ResponseEntity<Funcionario> criarFuncionario(@RequestBody FuncionarioRecordDto dto) {
        Funcionario novoFuncionario = funcionarioServices.saveFuncionario(dto);
        return new ResponseEntity<>(novoFuncionario, HttpStatus.CREATED);
    }

    // GET - Listar todos os funcionários
    @GetMapping
    public ResponseEntity<List<Funcionario>> listarFuncionarios() {
        return ResponseEntity.ok(funcionarioServices.getAllFuncionarios());
    }

    // PUT - Atualizar funcionário
    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> atualizarFuncionario(@PathVariable Long id, @RequestBody FuncionarioRecordDto dto) {
        FuncionarioRecordDto dtoAtualizado = new FuncionarioRecordDto(
                id,
                dto.nome(),
                dto.salario(),
                dto.cargo()
        );
        Funcionario funcionarioAtualizado = funcionarioServices.editFuncionario(dtoAtualizado);
        return ResponseEntity.ok(funcionarioAtualizado);
    }

    // DELETE - Remover funcionário
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFuncionario(@PathVariable Long id) {
        funcionarioServices.deleteFuncionario(id);
        return ResponseEntity.noContent().build();
    }
}
