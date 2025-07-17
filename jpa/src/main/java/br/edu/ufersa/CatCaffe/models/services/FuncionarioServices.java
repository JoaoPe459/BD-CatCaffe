package br.edu.ufersa.CatCaffe.models.services;

import br.edu.ufersa.CatCaffe.models.dtos.request.FuncionarioRequestDto;
import br.edu.ufersa.CatCaffe.models.dtos.response.FuncionarioResponseDto;
import br.edu.ufersa.CatCaffe.models.entities.Funcionario;
import br.edu.ufersa.CatCaffe.models.repositories.FuncionarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FuncionarioServices {

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioServices(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    @Transactional
    public FuncionarioResponseDto saveFuncionario(FuncionarioRequestDto dto) {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(dto.nome());
        funcionario.setSalario(dto.salario());
        funcionario.setCargo(dto.cargo());

        Funcionario salvo = funcionarioRepository.save(funcionario);
        return new FuncionarioResponseDto(salvo.getId_usuario(), salvo.getNome(), salvo.getSalario(), salvo.getCargo());
    }

    public List<FuncionarioResponseDto> getAllFuncionarios() {
        return funcionarioRepository.findAll().stream()
                .map(f -> new FuncionarioResponseDto(f.getId_usuario(), f.getNome(), f.getSalario(), f.getCargo()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteFuncionario(Long id) {
        if (!funcionarioRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Funcionário não encontrado");
        }
        funcionarioRepository.deleteById(id);
    }

    @Transactional
    public FuncionarioResponseDto editFuncionario(Long id, FuncionarioRequestDto dto) {
        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Funcionário não encontrado"));

        funcionario.setNome(dto.nome());
        funcionario.setSalario(dto.salario());
        funcionario.setCargo(dto.cargo());

        Funcionario atualizado = funcionarioRepository.save(funcionario);
        return new FuncionarioResponseDto(atualizado.getId_usuario(), atualizado.getNome(), atualizado.getSalario(), atualizado.getCargo());
    }
}
