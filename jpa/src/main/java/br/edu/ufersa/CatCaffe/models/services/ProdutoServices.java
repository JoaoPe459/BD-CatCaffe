package br.edu.ufersa.CatCaffe.models.services;

import br.edu.ufersa.CatCaffe.models.dtos.request.ProdutoRequestDto;
import br.edu.ufersa.CatCaffe.models.dtos.response.ProdutoResponseDto;
import br.edu.ufersa.CatCaffe.models.entities.Produto;
import br.edu.ufersa.CatCaffe.models.entities.Compra;
import br.edu.ufersa.CatCaffe.models.repositories.ProdutoRepository;
import br.edu.ufersa.CatCaffe.models.repositories.CompraRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoServices {

    private final ProdutoRepository produtoRepository;
    private final CompraRepository compraRepository;

    public ProdutoServices(ProdutoRepository produtoRepository, CompraRepository compraRepository) {
        this.produtoRepository = produtoRepository;
        this.compraRepository = compraRepository;
    }

    @Transactional
    public ProdutoResponseDto saveProduto(ProdutoRequestDto dto) {
        Produto produto = new Produto();

        Compra compra = compraRepository.findById(dto.getId_compra())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Compra não encontrada"));

        produto.setNome_produto(dto.getNome_produto());
        produto.setPreco(dto.getPreco());
        produto.setItem(dto.getItem());
        produto.setAdicional(dto.getAdicional());
        produto.setCompra(compra);

        Produto saved = produtoRepository.save(produto);

        return new ProdutoResponseDto(
                saved.getId_produto(),
                saved.getNome_produto(),
                saved.getPreco(),
                saved.getItem(),
                saved.getAdicional(),
                saved.getCompra().getId_compra()
        );
    }

    public List<ProdutoResponseDto> getAllProdutos() {
        return produtoRepository.findAll().stream()
                .map(produto -> new ProdutoResponseDto(
                        produto.getId_produto(),
                        produto.getNome_produto(),
                        produto.getPreco(),
                        produto.getItem(),
                        produto.getAdicional(),
                        produto.getCompra() != null ? produto.getCompra().getId_compra() : null
                ))
                .collect(Collectors.toList());
    }

    @Transactional
    public ProdutoResponseDto editProduto(Long id, ProdutoRequestDto dto) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));

        Compra compra = compraRepository.findById(dto.getId_compra())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Compra não encontrada"));

        produto.setNome_produto(dto.getNome_produto());
        produto.setPreco(dto.getPreco());
        produto.setItem(dto.getItem());
        produto.setAdicional(dto.getAdicional());
        produto.setCompra(compra);

        Produto updated = produtoRepository.save(produto);

        return new ProdutoResponseDto(
                updated.getId_produto(),
                updated.getNome_produto(),
                updated.getPreco(),
                updated.getItem(),
                updated.getAdicional(),
                updated.getCompra().getId_compra()
        );
    }

    @Transactional
    public void deleteProduto(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado");
        }
        produtoRepository.deleteById(id);
    }
}
