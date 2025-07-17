package br.edu.ufersa.CatCaffe.models.services;

import br.edu.ufersa.CatCaffe.models.dtos.request.GatoRequestDto;
import br.edu.ufersa.CatCaffe.models.dtos.response.GatoResponseDto;
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
    public GatoResponseDto saveGato(GatoRequestDto dto) {
        Gato gato = new Gato();
        gato.setNome_gato(dto.nome());
        gato.setRaca(dto.raca());
        gato.setIdade(dto.idade());
        gato.setStatus_adocao(dto.status_adocao());
        gato.setStatus_saude(dto.status_saude());

        Cliente cliente = clienteRepository.findById(dto.id_cliente())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
        gato.setCliente(cliente);

        gato = gatoRepository.save(gato);
        return toResponseDto(gato);
    }

    public List<GatoResponseDto> getAllGatos() {
        return gatoRepository.findAll()
                .stream()
                .map(this::toResponseDto)
                .toList();
    }

    @Transactional
    public void deleteGato(Long id) {
        if (!gatoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Gato não encontrado");
        }
        gatoRepository.deleteById(id);
    }

    @Transactional
    public GatoResponseDto editGato(Long id, GatoRequestDto dto) {
        Gato gato = gatoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Gato não encontrado"));

        gato.setNome_gato(dto.nome());
        gato.setRaca(dto.raca());
        gato.setIdade(dto.idade());
        gato.setStatus_adocao(dto.status_adocao());
        gato.setStatus_saude(dto.status_saude());

        Cliente cliente = clienteRepository.findById(dto.id_cliente())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
        gato.setCliente(cliente);

        gato = gatoRepository.save(gato);
        return toResponseDto(gato);
    }

    private GatoResponseDto toResponseDto(Gato gato) {
        return new GatoResponseDto(
                gato.getId_gato(),
                gato.getNome_gato(),
                gato.getRaca(),
                gato.getIdade(),
                gato.getStatus_adocao(),
                gato.getStatus_saude(),
                gato.getCliente() != null ? gato.getCliente().getId_cliente() : null
        );
    }
}
