package br.edu.ufersa.CatCaffe.models.repositories;

import br.edu.ufersa.CatCaffe.models.entities.Reservar_Espaco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservarEspacoRepository extends JpaRepository<Reservar_Espaco,Long> {
}
