package br.edu.ufersa.CatCaffe.models.services;

import br.edu.ufersa.CatCaffe.models.dtos.request.EnderecoRequestDto;
import br.edu.ufersa.CatCaffe.models.dtos.response.EnderecoResponseDto;
import br.edu.ufersa.CatCaffe.models.entities.Cliente;
import br.edu.ufersa.CatCaffe.models.entities.Endereco;
import br.edu.ufersa.CatCaffe.models.repositories.ClienteRepository;
import br.edu.ufersa.CatCaffe.models.repositories.EnderecoRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnderecoServices {
    private final EnderecoRepository enderecoRepository;
    private final ClienteRepository clienteRepository;

    public EnderecoServices(EnderecoRepository enderecoRepository, ClienteRepository clienteRepository) {
        this.enderecoRepository = enderecoRepository;
        this.clienteRepository = clienteRepository;
    }

    @Transactional
    public EnderecoResponseDto saveEndereco(EnderecoRequestDto dto) {
        Endereco endereco = new Endereco();
        endereco.setRuaENumero(dto.ruaENumero());
        endereco.setCidade(dto.cidade());
        endereco.setCep(dto.cep());

        if (dto.id_cliente() != null) {
            Cliente cliente = clienteRepository.findById(dto.id_cliente())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
            endereco.setCliente(cliente);
        } else {
            endereco.setCliente(null);
        }

        Endereco salvo = enderecoRepository.save(endereco);
        return toResponseDto(salvo);
    }

    public List<EnderecoResponseDto> getAllEndereco() {
        return enderecoRepository.findAll().stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteEndereco(Long id) {
        if (!enderecoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereço não encontrado");
        }
        enderecoRepository.deleteById(id);
    }

    @Transactional
    public EnderecoResponseDto editEndereco(Long id, EnderecoRequestDto dto) {
        Endereco endereco = enderecoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereço não encontrado"));

        endereco.setRuaENumero(dto.ruaENumero());
        endereco.setCidade(dto.cidade());
        endereco.setCep(dto.cep());

        if (dto.id_cliente() != null) {
            Cliente cliente = clienteRepository.findById(dto.id_cliente())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
            endereco.setCliente(cliente);
        } else {
            endereco.setCliente(null);
        }

        Endereco atualizado = enderecoRepository.save(endereco);
        return toResponseDto(atualizado);
    }

    private EnderecoResponseDto toResponseDto(Endereco endereco) {
        Long idCliente = endereco.getCliente() != null ? endereco.getCliente().getId_cliente() : null;
        return new EnderecoResponseDto(
                endereco.getId_endereco(),
                endereco.getRuaENumero(),
                endereco.getCidade(),
                endereco.getCep(),
                idCliente
        );
    }
}
