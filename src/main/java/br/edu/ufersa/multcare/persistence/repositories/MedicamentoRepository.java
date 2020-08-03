package br.edu.ufersa.multcare.persistence.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.ufersa.multcare.persistence.entities.Medicamento;

@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento, Long>{

	@Query("select med from Medicamento med where med.usuario.id = ?1")
	List<Medicamento> listarMedicamentosPorUsuario(Integer id);
	

	@Query(
			nativeQuery = true,
			value = "select * from medicamento WHERE hora = TIME_FORMAT(CURTIME(), '%H:%i') "
					+ "and CURRENT_DATE BETWEEN data_inicial AND data_final "
	)
	List<Medicamento> obterTodosMedicamentosPorPeriodo();
}
