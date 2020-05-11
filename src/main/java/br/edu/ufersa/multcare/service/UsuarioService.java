package br.edu.ufersa.multcare.service;

import br.edu.ufersa.multcare.persistence.entities.Usuario;
import br.edu.ufersa.multcare.shared.dto.UsuarioDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UsuarioService {

    String cadastrarUsuario(UsuarioDTO usuarioDTO);

    Optional <Usuario> obterUsuarioPorLogin(String login);

    Usuario obterUsuarioPorId(Integer id);

}
