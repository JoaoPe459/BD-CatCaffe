package br.edu.ufersa.CatCaffe.models.services;

import br.edu.ufersa.CatCaffe.models.dtos.request.CompraRequestDto;
import br.edu.ufersa.CatCaffe.models.dtos.response.CompraResponseDto;
import br.edu.ufersa.CatCaffe.models.entities.Compra;
import br.edu.ufersa.CatCaffe.models.entities.Pedido;
import br.edu.ufersa.CatCaffe.models.repositories.CompraRepository;
import br.edu.ufersa.CatCaffe.models.repositories.PedidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompraServices {

    private final CompraRepository compraRepository;
    private final PedidoRepository pedidoRepository;

    public CompraServices(CompraRepository compraRepository, PedidoRepository pedidoRepository) {
        this.compraRepository = compraRepository;
        this.pedidoRepository = pedidoRepository;
    }

    @Transactional
    public CompraResponseDto saveCompra(CompraRequestDto dto) {
        Compra compra = new Compra();

        Pedido pedido = pedidoRepository.findById(dto.getIdPedido())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));

        compra.setPedido(pedido);
        compra.setEntrega(dto.getEntrega());

        Compra saved = compraRepository.save(compra);
        return toResponseDto(saved);
    }

    public List<CompraResponseDto> getAllCompras() {
        return compraRepository.findAll()
                .stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteCompra(Long id) {
        if (!compraRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Compra não encontrada");
        }
        compraRepository.deleteById(id);
    }

    public CompraResponseDto editCompra(Long idCompra, CompraRequestDto dto) {
        Compra compra = compraRepository.findById(idCompra)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Compra não encontrada"));

        Pedido pedido = pedidoRepository.findById(dto.getIdPedido())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));

        compra.setPedido(pedido);
        compra.setEntrega(dto.getEntrega());

        Compra updated = compraRepository.save(compra);
        return toResponseDto(updated);
    }

    private CompraResponseDto toResponseDto(Compra compra) {
        return new CompraResponseDto(
                compra.getId_compra(),
                compra.getPedido().getId_pedido(),
                compra.isEntrega()
        );
    }
}
