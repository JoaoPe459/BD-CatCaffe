package br.edu.ufersa.CatCaffe.models.services;

import br.edu.ufersa.CatCaffe.models.dtos.ReservarEspaçoRecordDto;
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

@Service
public class ReservarEspacoServices {

    private final ReservarEspacoRepository reservarEspacoRepository;
    private final ClienteRepository clienteRepository;

    public ReservarEspacoServices(ReservarEspacoRepository reservarEspacoRepository, ClienteRepository clienteRepository) {
        this.reservarEspacoRepository = reservarEspacoRepository;
        this.clienteRepository = clienteRepository;
    }

    @Transactional
    public Reservar_Espaco saveReserva(ReservarEspaçoRecordDto dto) {
        Reservar_Espaco reserva = new Reservar_Espaco();
        reserva.setData(dto.data());
        reserva.setHorario(dto.horario());

        Set<Cliente> clientes = new HashSet<>();
        for (Long id:dto.id_cliente()) {
            Cliente cliente = clienteRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente com id " + id + " não encontrado"));
            clientes.add(cliente);
        }

        return reservarEspacoRepository.save(reserva);
    }

    public List<Reservar_Espaco> getAllReservas() {
        return reservarEspacoRepository.findAll();
    }

    @Transactional
    public void deleteReserva(Long id) {
        if (!reservarEspacoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reserva não encontrada");
        }
        reservarEspacoRepository.deleteById(id);
    }

    @Transactional
    public Reservar_Espaco editReserva(ReservarEspaçoRecordDto dto) {
        Reservar_Espaco reserva = reservarEspacoRepository.findById(dto.id_reserva())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reserva não encontrada"));

        reserva.setData(dto.data());
        reserva.setHorario(dto.horario());

        Set<Cliente> clientes = new HashSet<>();
        for (Long id:dto.id_cliente()) {
            Cliente cliente = clienteRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente com id " + id + " não encontrado"));
            clientes.add(cliente);
        }

        return reservarEspacoRepository.save(reserva);
    }
}
