package br.edu.ufersa.multcare.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.edu.ufersa.multcare.persistence.entities.Usuario;
import br.edu.ufersa.multcare.shared.dto.UsuarioDTO;

@Service
public interface UsuarioService {

    String cadastrarUsuario(UsuarioDTO usuarioDTO);

    Optional <Usuario> obterUsuarioPorLogin(String login);

    Usuario obterUsuarioPorId(Integer id);
    
  
}
