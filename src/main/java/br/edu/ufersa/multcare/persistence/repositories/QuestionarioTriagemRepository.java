package br.edu.ufersa.multcare.persistence.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.ufersa.multcare.persistence.entities.QuestionarioTriagem;

@Repository
public interface QuestionarioTriagemRepository extends JpaRepository<QuestionarioTriagem, Long>{

	@Query("select al from QuestionarioTriagem al where al.usuario.id = ?1")
	List<QuestionarioTriagem> listarQuestionarioTriagemPorUsuario(Integer id);

	QuestionarioTriagem findById(long id);
	
	
}