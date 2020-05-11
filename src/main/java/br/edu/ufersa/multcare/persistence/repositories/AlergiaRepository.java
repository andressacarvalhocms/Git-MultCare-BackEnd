package br.edu.ufersa.multcare.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.ufersa.multcare.persistence.entities.Alergia;

import java.util.List;


@Repository
public interface AlergiaRepository extends JpaRepository<Alergia, Long>{

	@Query("select al from Alergia al where al.usuario.id = ?1")
	List<Alergia> listarAlergiasPorUsuario(Integer id);

	Alergia findById(long id);
}