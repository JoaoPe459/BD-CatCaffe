package br.edu.ufersa.CatCaffe.models.services;

import br.edu.ufersa.CatCaffe.models.dtos.CompraRecordDto;
import br.edu.ufersa.CatCaffe.models.entities.Compra;
import br.edu.ufersa.CatCaffe.models.entities.Pedido;
import br.edu.ufersa.CatCaffe.models.repositories.CompraRepository;
import br.edu.ufersa.CatCaffe.models.repositories.PedidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CompraServices {

    private final CompraRepository compraRepository;
    private final PedidoRepository pedidoRepository;

    public CompraServices(CompraRepository compraRepository, PedidoRepository pedidoRepository) {
        this.compraRepository = compraRepository;
        this.pedidoRepository = pedidoRepository;
    }

    @Transactional
    public Compra saveCompra(CompraRecordDto compraRecordDto) {
        Compra compra = new Compra();

        Pedido pedido = pedidoRepository.findById(compraRecordDto.id_pedido())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));

        compra.setPedido(pedido);
        compra.setEntrega(compraRecordDto.entrega());

        return compraRepository.save(compra);
    }

    public List<Compra> getAllCompras() {
        return compraRepository.findAll();
    }

    @Transactional
    public void deleteCompra(Long id) {
        if (compraRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Compra não encontrada");
        }
            compraRepository.deleteById(id);

    }

    public Compra editCompra(CompraRecordDto compraRecordDto) {
        Compra compra = compraRepository.findById(compraRecordDto.id_compra())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Compra não encontrada"));

        Pedido pedido = pedidoRepository.findById(compraRecordDto.id_pedido())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));

        compra.setPedido(pedido);
        compra.setEntrega(compraRecordDto.entrega());

        return compraRepository.save(compra);
    }
}
