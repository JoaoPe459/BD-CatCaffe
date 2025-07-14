package br.edu.ufersa.CatCaffe.models.repositories;

import br.edu.ufersa.CatCaffe.models.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto,Long> {
}
