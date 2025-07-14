package br.edu.ufersa.CatCaffe.models.repositories;

import br.edu.ufersa.CatCaffe.models.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
