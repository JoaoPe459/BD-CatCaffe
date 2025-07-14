package br.edu.ufersa.CatCaffe.models.services;

import br.edu.ufersa.CatCaffe.models.dtos.ClienteRecordDto;
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
    public Cliente saveCiente(ClienteRecordDto clienteRecordDto) {
        Cliente cliente = new Cliente();
        cliente.setNomeCliente(clienteRecordDto.nomeCliente());
        if(clienteRecordDto.id_endereco() != null) {
            cliente.setEndereco(
                    enderecoRepository.findById(clienteRecordDto.id_endereco()).orElseThrow(() -> new RuntimeException("Endereço não encontrado")));

            return clienteRepository.save(cliente);
        }else
        {cliente.setEndereco(null);}

        return clienteRepository.save(cliente);
    }

    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    @Transactional
    public void deleteCliente(Long id) {
        if (clienteRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrada");
        }
        clienteRepository.deleteById(id);
    }

    public Cliente editCliente(ClienteRecordDto clienteRecordDto) {
        Cliente cliente = clienteRepository.findById(clienteRecordDto.id_cliente())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

        cliente.setNomeCliente(clienteRecordDto.nomeCliente());

        Endereco endereco = enderecoRepository.findById(clienteRecordDto.id_endereco())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereço não encontrado"));
        cliente.setEndereco(endereco);

        return clienteRepository.save(cliente);
    }


}


