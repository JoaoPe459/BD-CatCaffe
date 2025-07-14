package br.edu.ufersa.CatCaffe.models.repositories;

import br.edu.ufersa.CatCaffe.models.entities.Gato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GatoRepository extends JpaRepository<Gato,Long> {
}
