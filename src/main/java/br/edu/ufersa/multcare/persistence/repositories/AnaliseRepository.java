package br.edu.ufersa.multcare.persistence.repositories;

import br.edu.ufersa.multcare.persistence.entities.Analise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AnaliseRepository extends JpaRepository<Analise, Long>{

    @Query("select an from Analise an where an.usuario.id = ?1")
    List<Analise> obterAnalisesPorUsuario(Integer idUsuario);
}