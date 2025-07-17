package br.edu.ufersa.CatCaffe.models.repositories;

import br.edu.ufersa.CatCaffe.models.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    @Query("SELECT DISTINCT c FROM Cliente c JOIN c.gato g")
    List<Cliente> buscarClientesComGatos();

    @Query(value = "SELECT c.nome_cliente, e.bairro FROM cliente c JOIN endereco e ON c.id_endereco = e.id_endereco", nativeQuery = true)
    List<Object[]> buscarDados();

}
