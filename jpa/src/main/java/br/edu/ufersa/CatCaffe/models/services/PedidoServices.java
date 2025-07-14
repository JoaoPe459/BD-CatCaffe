package br.edu.ufersa.CatCaffe.models.services;

import br.edu.ufersa.CatCaffe.models.dtos.PedidoRecordDto;
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

    @Transactional
    public Pedido savePedido(PedidoRecordDto pedidoRecordDto) {
        Pedido pedido = new Pedido();

        Cliente cliente = clienteRepository.findById(pedidoRecordDto.id_cliente())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
        Funcionario funcionario = funcionarioRepository.findById(pedidoRecordDto.id_funcionario())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Funcionário não encontrado"));

        pedido.setCliente(cliente);
        pedido.setFuncionario(funcionario);
        pedido.setData(pedidoRecordDto.data());
        pedido.setHorario(pedidoRecordDto.hora());
        pedido.setStatus(pedidoRecordDto.status());
        pedido.setForma_pag(pedidoRecordDto.forma_pag());

        return pedidoRepository.save(pedido);
    }

    public List<Pedido> getAllPedidos() {
        return pedidoRepository.findAll();
    }

    @Transactional
    public void deletePedido(Long id) {
        if (!pedidoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado");
        }
        pedidoRepository.deleteById(id);
    }

    @Transactional
    public Pedido editPedido(PedidoRecordDto pedidoRecordDto) {
        Pedido pedido = pedidoRepository.findById(pedidoRecordDto.id_pedido())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));

        Cliente cliente = clienteRepository.findById(pedidoRecordDto.id_cliente())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
        Funcionario funcionario = funcionarioRepository.findById(pedidoRecordDto.id_funcionario())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Funcionário não encontrado"));

        pedido.setCliente(cliente);
        pedido.setFuncionario(funcionario);
        pedido.setData(pedidoRecordDto.data());
        pedido.setHorario(pedidoRecordDto.hora());
        pedido.setStatus(pedidoRecordDto.status());
        pedido.setForma_pag(pedidoRecordDto.forma_pag());

        return pedidoRepository.save(pedido);
    }
}
