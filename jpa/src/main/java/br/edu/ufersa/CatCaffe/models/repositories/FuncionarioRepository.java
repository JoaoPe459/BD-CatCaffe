package br.edu.ufersa.CatCaffe.models.repositories;

import br.edu.ufersa.CatCaffe.models.entities.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario,Long> {
}
