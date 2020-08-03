package br.edu.ufersa.multcare.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.ufersa.multcare.persistence.entities.Exame;

import java.util.List;

@Repository
public interface ExameRepository extends JpaRepository<Exame, Long>{

	@Query("select ex from Exame ex where ex.usuario.id= ?1 and ex.status = 'A'")
	List<Exame> listarExamesUsuario(Integer id);

	Exame findDistinctTopByCodigoExameEqualsAndIdUsuarioEqualsAndStatus(Integer codExame, Integer idUsuario, String Status);

	@Query(
			nativeQuery = true,
			value = "select * from exame where id = (select MAX(id) from exame where codigo_exame = ?1 and usuario_id= ?2 and status = 'A')"
	)
	Exame obterExameMaisRecentePorUsuarioCodExame(Integer codExame, Integer usuarioId );



}