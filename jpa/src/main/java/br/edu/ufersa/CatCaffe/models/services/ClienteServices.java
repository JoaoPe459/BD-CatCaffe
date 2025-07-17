package br.edu.ufersa.CatCaffe.models.services;

import br.edu.ufersa.CatCaffe.models.dtos.request.ClienteEnderecoRequestDto;
import br.edu.ufersa.CatCaffe.models.dtos.request.ClienteRequestDto;
import br.edu.ufersa.CatCaffe.models.dtos.response.ClienteResponseDto;
import br.edu.ufersa.CatCaffe.models.entities.Cliente;
import br.edu.ufersa.CatCaffe.models.entities.Endereco;
import br.edu.ufersa.CatCaffe.models.repositories.ClienteRepository;
import br.edu.ufersa.CatCaffe.models.repositories.EnderecoRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ClienteServices {

    private final ClienteRepository clienteRepository;
    private final EnderecoRepository enderecoRepository;

    public ClienteServices(ClienteRepository clienteRepository, EnderecoRepository enderecoRepository) {
        this.clienteRepository = clienteRepository;
        this.enderecoRepository = enderecoRepository;
    }

    @Transactional
    public ClienteResponseDto saveCiente(ClienteRequestDto dto) {
        Cliente cliente = new Cliente();
        cliente.setNomeCliente(dto.getNomeCliente());

        if (dto.getId_endereco() != null) {
            Endereco endereco = enderecoRepository.findById(dto.getId_endereco())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereço não encontrado"));
            cliente.setEndereco(endereco);
        }

        Cliente saved = clienteRepository.save(cliente);

        return new ClienteResponseDto(
                saved.getId_cliente(),
                saved.getNomeCliente(),
                saved.getEndereco() != null ? saved.getEndereco().getId_endereco() : null
        );
    }

    public List<ClienteResponseDto> getAllClientes() {
        return clienteRepository.findAll().stream()
                .map(cliente -> new ClienteResponseDto(
                        cliente.getId_cliente(),
                        cliente.getNomeCliente(),
                        cliente.getEndereco() != null ? cliente.getEndereco().getId_endereco() : null
                ))
                .toList();
    }

    @Transactional
    public void deleteCliente(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado");
        }
        clienteRepository.deleteById(id);
    }

    @Transactional
    public ClienteResponseDto editCliente(Long id, ClienteRequestDto dto) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

        cliente.setNomeCliente(dto.getNomeCliente());

        if (dto.getId_endereco() != null) {
            Endereco endereco = enderecoRepository.findById(dto.getId_endereco())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereço não encontrado"));
            cliente.setEndereco(endereco);
        } else {
            cliente.setEndereco(null);
        }

        Cliente atualizado = clienteRepository.save(cliente);

        return new ClienteResponseDto(
                atualizado.getId_cliente(),
                atualizado.getNomeCliente(),
                atualizado.getEndereco() != null ? atualizado.getEndereco().getId_endereco() : null
        );
    }

    public List<Cliente> getAllClientesComGatos() {
        return clienteRepository.buscarClientesComGatos();
    }

    public List<ClienteEnderecoRequestDto> listarClienteComEndereco() {
        return clienteRepository.buscarDados().stream()
                .map(obj -> new ClienteEnderecoRequestDto((String) obj[0], (String) obj[1]))
                .toList();
    }
}
