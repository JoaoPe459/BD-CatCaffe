package br.edu.ufersa.CatCaffe.models.services;

import br.edu.ufersa.CatCaffe.models.dtos.request.ReservarEspacoRequestDto;
import br.edu.ufersa.CatCaffe.models.dtos.response.ReservarEspacoResponseDto;
import br.edu.ufersa.CatCaffe.models.entities.Cliente;
import br.edu.ufersa.CatCaffe.models.entities.Reservar_Espaco;
import br.edu.ufersa.CatCaffe.models.repositories.ClienteRepository;
import br.edu.ufersa.CatCaffe.models.repositories.ReservarEspacoRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ReservarEspacoServices {

    private final ReservarEspacoRepository reservarEspacoRepository;
    private final ClienteRepository clienteRepository;

    public ReservarEspacoServices(ReservarEspacoRepository reservarEspacoRepository, ClienteRepository clienteRepository) {
        this.reservarEspacoRepository = reservarEspacoRepository;
        this.clienteRepository = clienteRepository;
    }

    @Transactional
    public ReservarEspacoResponseDto saveReserva(ReservarEspacoRequestDto dto) {
        Reservar_Espaco reserva = new Reservar_Espaco();
        reserva.setData(dto.getData());
        reserva.setHorario(dto.getHorario());

        Set<Cliente> clientes = new HashSet<>();
        for (Long id : dto.getId_cliente()) {
            Cliente cliente = clienteRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente com id " + id + " não encontrado"));
            clientes.add(cliente);
        }
        reserva.setCliente(clientes);

        Reservar_Espaco saved = reservarEspacoRepository.save(reserva);

        return toResponseDto(saved);
    }

    public List<ReservarEspacoResponseDto> getAllReservas() {
        return reservarEspacoRepository.findAll().stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteReserva(Long id) {
        if (!reservarEspacoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reserva não encontrada");
        }
        reservarEspacoRepository.deleteById(id);
    }

    @Transactional
    public ReservarEspacoResponseDto editReserva(Long id, ReservarEspacoRequestDto dto) {
        Reservar_Espaco reserva = reservarEspacoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reserva não encontrada"));

        reserva.setData(dto.getData());
        reserva.setHorario(dto.getHorario());

        Set<Cliente> clientes = new HashSet<>();
        for (Long clienteId : dto.getId_cliente()) {
            Cliente cliente = clienteRepository.findById(clienteId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente com id " + clienteId + " não encontrado"));
            clientes.add(cliente);
        }
        reserva.setCliente(clientes);

        Reservar_Espaco updated = reservarEspacoRepository.save(reserva);

        return toResponseDto(updated);
    }

    private ReservarEspacoResponseDto toResponseDto(Reservar_Espaco reserva) {
        Set<Long> clienteIds = reserva.getCliente() != null
                ? reserva.getCliente().stream().map(Cliente::getId_cliente).collect(Collectors.toSet())
                : new HashSet<>();

        return new ReservarEspacoResponseDto(
                reserva.getId_reserva(),
                clienteIds,
                reserva.getData(),
                reserva.getHorario()
        );
    }
}
