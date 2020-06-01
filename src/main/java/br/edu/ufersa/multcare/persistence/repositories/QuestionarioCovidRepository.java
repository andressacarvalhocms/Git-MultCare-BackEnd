package br.edu.ufersa.multcare.persistence.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.ufersa.multcare.persistence.entities.QuestionarioCovid;


@Repository
public interface QuestionarioCovidRepository extends JpaRepository<QuestionarioCovid, Long>{

	@Query("select al from QuestionarioCovid al where al.usuario.id = ?1")
	List<QuestionarioCovid> listarQuestionarioCovidPorUsuario(Integer id);

	QuestionarioCovid findById(long id);
}