package br.edu.ufersa.multcare.persistence.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufersa.multcare.persistence.entities.MedicamentoTriagem;


@Repository
public interface MedicamentoTriagemRepository extends JpaRepository<MedicamentoTriagem, Long>{


//	@Query("select sin from Sintoma sin where sin.usuario.id = ?1")
//	List<Sintoma> listarSintomasPorUsuario(Integer id);

    List<MedicamentoTriagem> findAllByIdIn(List<Integer> ids);
}

