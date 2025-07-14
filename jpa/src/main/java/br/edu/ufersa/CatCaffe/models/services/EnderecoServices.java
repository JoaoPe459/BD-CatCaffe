package br.edu.ufersa.CatCaffe.models.services;

import br.edu.ufersa.CatCaffe.models.dtos.EnderecoRecordDto;
import br.edu.ufersa.CatCaffe.models.entities.Cliente;
import br.edu.ufersa.CatCaffe.models.entities.Endereco;
import br.edu.ufersa.CatCaffe.models.repositories.ClienteRepository;
import br.edu.ufersa.CatCaffe.models.repositories.EnderecoRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public class EnderecoServices {
    private final EnderecoRepository enderecoRepository;
    private final ClienteRepository clienteRepository;

    public EnderecoServices(EnderecoRepository enderecoRepository, ClienteRepository clienteRepository) {
        this.enderecoRepository = enderecoRepository;
        this.clienteRepository = clienteRepository;
    }


    @Transactional
    public Endereco saveEndereco(EnderecoRecordDto enderecoRecordDto) {
        Endereco endereco = new Endereco();
        endereco.setCep(enderecoRecordDto.cep());
        endereco.setRuaENumero(enderecoRecordDto.ruaENumero());
        endereco.setCidade(enderecoRecordDto.cidade());
        assert clienteRepository != null;
        endereco.setCliente(clienteRepository.findById(enderecoRecordDto.id_cliente()).orElseThrow(() -> new RuntimeException("Cliente não encontrado")));
        return enderecoRepository.save(endereco);
    }

    public List<Endereco> getAllEndereco() {
        return enderecoRepository.findAll();
    }

    @Transactional
    public void deleteEndereco(Long id) {
        if (enderecoRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereco não encontrada");
        }
        enderecoRepository.deleteById(id);
    }

    public Endereco editEndereco(EnderecoRecordDto enderecoRecordDto) {
        Endereco endereco = enderecoRepository.findById(enderecoRecordDto.id_endereco())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereco não encontrado"));

        endereco.setRuaENumero(enderecoRecordDto.ruaENumero());
        endereco.setCidade(enderecoRecordDto.cidade());
        endereco.setCep(enderecoRecordDto.cep());

        Cliente cliente = clienteRepository.findById(enderecoRecordDto.id_cliente())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
        endereco.setCliente(cliente);

        return enderecoRepository.save(endereco);
    }
}
