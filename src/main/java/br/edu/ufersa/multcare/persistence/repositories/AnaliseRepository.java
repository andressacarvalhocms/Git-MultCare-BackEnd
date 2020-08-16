package br.edu.ufersa.multcare.persistence.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.ufersa.multcare.persistence.entities.Analise;


@Repository
public interface AnaliseRepository extends JpaRepository<Analise, Long>{

    @Query("select an from Analise an where an.usuario.id = ?1")
    List<Analise> obterAnalisesPorUsuario(Integer idUsuario);
    
	@Query(
			nativeQuery = true,
			value = "select * from analise where id = (select MAX(id) from analise where usuario_id= ?1)"
	)
	List<Analise> obterUltimaAnalisesPorUsuario(Integer idUsuario);

}