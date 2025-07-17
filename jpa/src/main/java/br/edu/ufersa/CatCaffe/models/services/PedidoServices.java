package br.edu.ufersa.CatCaffe.models.services;

import br.edu.ufersa.CatCaffe.models.dtos.request.PedidoRequestDto;
import br.edu.ufersa.CatCaffe.models.dtos.response.PedidoResponseDto;
import br.edu.ufersa.CatCaffe.models.entities.Cliente;
import br.edu.ufersa.CatCaffe.models.entities.Funcionario;
import br.edu.ufersa.CatCaffe.models.entities.Pedido;
import br.edu.ufersa.CatCaffe.models.repositories.ClienteRepository;
import br.edu.ufersa.CatCaffe.models.repositories.FuncionarioRepository;
import br.edu.ufersa.CatCaffe.models.repositories.PedidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoServices {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final FuncionarioRepository funcionarioRepository;

    public PedidoServices(PedidoRepository pedidoRepository,
                          ClienteRepository clienteRepository,
                          FuncionarioRepository funcionarioRepository) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.funcionarioRepository = funcionarioRepository;
    }

    // Método auxiliar para converter entidade Pedido em DTO de resposta
    private PedidoResponseDto toResponseDto(Pedido pedido) {
        return new PedidoResponseDto(
                pedido.getId_pedido(),
                pedido.getCliente().getId_cliente(),
                pedido.getFuncionario().getId_usuario(),
                pedido.getData(),
                pedido.getHorario(),
                pedido.getStatus(),
                pedido.getForma_pag()
        );
    }

    @Transactional
    public PedidoResponseDto savePedido(PedidoRequestDto pedidoRequestDto) {
        Pedido pedido = new Pedido();

        Cliente cliente = clienteRepository.findById(pedidoRequestDto.getId_cliente())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
        Funcionario funcionario = funcionarioRepository.findById(pedidoRequestDto.getId_funcionario())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Funcionário não encontrado"));

        pedido.setCliente(cliente);
        pedido.setFuncionario(funcionario);
        pedido.setData(pedidoRequestDto.getData());
        pedido.setHorario(pedidoRequestDto.getHora());
        pedido.setStatus(pedidoRequestDto.getStatus());
        pedido.setForma_pag(pedidoRequestDto.getForma_pag());

        Pedido salvo = pedidoRepository.save(pedido);
        return toResponseDto(salvo);
    }

    public List<PedidoResponseDto> getAllPedidos() {
        return pedidoRepository.findAll()
                .stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deletePedido(Long id) {
        if (!pedidoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado");
        }
        pedidoRepository.deleteById(id);
    }

    @Transactional
    public PedidoResponseDto editPedido(Long id, PedidoRequestDto dto) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));

        Cliente cliente = clienteRepository.findById(dto.getId_cliente())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

        Funcionario funcionario = funcionarioRepository.findById(dto.getId_funcionario())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Funcionário não encontrado"));

        pedido.setCliente(cliente);
        pedido.setFuncionario(funcionario);
        pedido.setData(dto.getData());
        pedido.setHorario(dto.getHora());
        pedido.setStatus(dto.getStatus());
        pedido.setForma_pag(dto.getForma_pag());

        pedido = pedidoRepository.save(pedido);
        return toResponseDto(pedido);
    }

}
