package br.edu.ufersa.CatCaffe.models.services;

import br.edu.ufersa.CatCaffe.models.dtos.GatoRecordDto;
import br.edu.ufersa.CatCaffe.models.entities.Cliente;
import br.edu.ufersa.CatCaffe.models.entities.Gato;
import br.edu.ufersa.CatCaffe.models.repositories.ClienteRepository;
import br.edu.ufersa.CatCaffe.models.repositories.GatoRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class GatoServices {

    private final GatoRepository gatoRepository;
    private final ClienteRepository clienteRepository;

    public GatoServices(GatoRepository gatoRepository, ClienteRepository clienteRepository) {
        this.gatoRepository = gatoRepository;
        this.clienteRepository = clienteRepository;
    }

    @Transactional
    public Gato saveGato(GatoRecordDto gatoRecordDto) {
        Gato gato = new Gato();
        gato.setNome_gato(gatoRecordDto.nome());
        gato.setRaca(gatoRecordDto.raca());
        gato.setIdade(gatoRecordDto.idade());
        gato.setStatus_adocao(gatoRecordDto.status_adocao());
        gato.setStatus_saude(gatoRecordDto.status_saude());

        Cliente cliente = clienteRepository.findById(gatoRecordDto.id_cliente())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
        gato.setCliente(cliente);

        return gatoRepository.save(gato);
    }

    public List<Gato> getAllGatos() {
        return gatoRepository.findAll();
    }

    @Transactional
    public void deleteGato(Long id) {
        if (!gatoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Gato não encontrado");
        }
        gatoRepository.deleteById(id);
    }

    @Transactional
    public Gato editGato(GatoRecordDto gatoRecordDto) {
        Gato gato = gatoRepository.findById(gatoRecordDto.id_gato())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Gato não encontrado"));

        gato.setNome_gato(gatoRecordDto.nome());
        gato.setRaca(gatoRecordDto.raca());
        gato.setIdade(gatoRecordDto.idade());
        gato.setStatus_adocao(gatoRecordDto.status_adocao());
        gato.setStatus_saude(gatoRecordDto.status_saude());

        Cliente cliente = clienteRepository.findById(gatoRecordDto.id_cliente())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
        gato.setCliente(cliente);

        return gatoRepository.save(gato);
    }
}
