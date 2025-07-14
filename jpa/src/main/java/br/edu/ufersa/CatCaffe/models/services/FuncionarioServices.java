package br.edu.ufersa.CatCaffe.models.services;

import br.edu.ufersa.CatCaffe.models.dtos.FuncionarioRecordDto;
import br.edu.ufersa.CatCaffe.models.entities.Funcionario;
import br.edu.ufersa.CatCaffe.models.repositories.FuncionarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class FuncionarioServices {

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioServices(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    @Transactional
    public Funcionario saveFuncionario(FuncionarioRecordDto funcionarioRecordDto) {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(funcionarioRecordDto.nome());
        funcionario.setSalario(funcionarioRecordDto.salario());
        funcionario.setCargo(funcionarioRecordDto.cargo());


        return funcionarioRepository.save(funcionario);
    }

    public List<Funcionario> getAllFuncionarios() {
        return funcionarioRepository.findAll();
    }

    @Transactional
    public void deleteFuncionario(Long id) {
        if (!funcionarioRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Funcionário não encontrado");
        }
        funcionarioRepository.deleteById(id);
    }

    @Transactional
    public Funcionario editFuncionario(FuncionarioRecordDto funcionarioRecordDto) {
        Funcionario funcionario = funcionarioRepository.findById(funcionarioRecordDto.id_usuario())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Funcionário não encontrado"));

        funcionario.setNome(funcionarioRecordDto.nome());
        funcionario.setSalario(funcionarioRecordDto.salario());
        funcionario.setCargo(funcionarioRecordDto.cargo());

        return funcionarioRepository.save(funcionario);
    }
}
