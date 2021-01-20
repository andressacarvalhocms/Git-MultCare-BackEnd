package br.edu.ufersa.multcare.persistence.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.ufersa.multcare.persistence.entities.QuestionarioTriagem;


@Repository
public interface SistemaEspecialistaRepository extends JpaRepository<QuestionarioTriagem, Long>{

   @Query(
			nativeQuery = true,
			value = "select * from Questionario_Triagem where id = (select MAX(id) from Questionario_Triagem where usuario_id= ?1)"
	)
    List<QuestionarioTriagem> obterAnalisesSEPorUsuario(Integer idUsuario);
}