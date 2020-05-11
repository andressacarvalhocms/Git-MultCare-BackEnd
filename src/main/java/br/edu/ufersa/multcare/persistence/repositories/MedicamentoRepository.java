package br.edu.ufersa.multcare.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.ufersa.multcare.persistence.entities.Medicamento;

import java.util.List;

@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento, Long>{

	@Query("select med from Medicamento med where med.usuario.id = ?1")
	List<Medicamento> listarMedicamentosPorUsuario(Integer id);
}
