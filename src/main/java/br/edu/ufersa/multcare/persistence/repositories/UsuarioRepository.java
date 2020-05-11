package br.edu.ufersa.multcare.persistence.repositories;

import br.edu.ufersa.multcare.persistence.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	Optional<Usuario> findUsuarioByLoginEquals(String login);

	Boolean existsUsuarioByLogin(String login);

	Boolean existsUsuarioByNome(String nome);

	Usuario findUsuarioById(Integer id);
	
}