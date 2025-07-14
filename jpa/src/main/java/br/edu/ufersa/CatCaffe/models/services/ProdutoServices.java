package br.edu.ufersa.CatCaffe.models.services;

import br.edu.ufersa.CatCaffe.models.dtos.ProdutoRecordDto;
import br.edu.ufersa.CatCaffe.models.entities.Compra;
import br.edu.ufersa.CatCaffe.models.entities.Produto;
import br.edu.ufersa.CatCaffe.models.repositories.CompraRepository;
import br.edu.ufersa.CatCaffe.models.repositories.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProdutoServices {

    private final ProdutoRepository produtoRepository;
    private final CompraRepository compraRepository;

    public ProdutoServices(ProdutoRepository produtoRepository, CompraRepository compraRepository) {
        this.produtoRepository = produtoRepository;
        this.compraRepository = compraRepository;
    }

    @Transactional
    public Produto saveProduto(ProdutoRecordDto produtoRecordDto) {
        Produto produto = new Produto();

        Compra compra = compraRepository.findById(produtoRecordDto.id_compra())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Compra não encontrada"));

        produto.setNome_produto(produtoRecordDto.nome_produto());
        produto.setPreco(produtoRecordDto.preco());
        produto.setItem(produtoRecordDto.item());
        produto.setAdicional(produtoRecordDto.adicional());
        produto.setCompra(compra);

        return produtoRepository.save(produto);
    }

    public List<Produto> getAllProdutos() {
        return produtoRepository.findAll();
    }

    @Transactional
    public void deleteProduto(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado");
        }
        produtoRepository.deleteById(id);
    }

    @Transactional
    public Produto editProduto(ProdutoRecordDto produtoRecordDto) {
        Produto produto = produtoRepository.findById(produtoRecordDto.id_produto())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));

        Compra compra = compraRepository.findById(produtoRecordDto.id_compra())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Compra não encontrada"));

        produto.setNome_produto(produtoRecordDto.nome_produto());
        produto.setPreco(produtoRecordDto.preco());
        produto.setItem(produtoRecordDto.item());
        produto.setAdicional(produtoRecordDto.adicional());
        produto.setCompra(compra);

        return produtoRepository.save(produto);
    }
}
