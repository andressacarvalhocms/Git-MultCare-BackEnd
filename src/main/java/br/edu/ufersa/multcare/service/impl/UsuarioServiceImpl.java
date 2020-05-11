package br.edu.ufersa.multcare.service.impl;

import br.edu.ufersa.multcare.persistence.entities.Usuario;
import br.edu.ufersa.multcare.persistence.repositories.UsuarioRepository;
import br.edu.ufersa.multcare.service.UsuarioService;
import br.edu.ufersa.multcare.shared.builder.UsuarioBuilder;
import br.edu.ufersa.multcare.shared.dto.UsuarioDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class UsuarioServiceImpl implements UsuarioService {


    private final UsuarioRepository usuariosRepository;

    private final PasswordEncoder encoder;

    public UsuarioServiceImpl(UsuarioRepository usuariosRepository, PasswordEncoder encoder) {
        this.usuariosRepository = usuariosRepository;
        this.encoder = encoder;
    }

    @Override
    public String cadastrarUsuario(UsuarioDTO usuarioDTO) {

        validarSeJaExisteUsuario(usuarioDTO);
        Usuario usuario = usuariosRepository.save(obterUsuario(usuarioDTO));

        if(usuario != null) {
            return "Usuário " + usuario.getNome() + " cadastrado com sucesso!";
        }

        return null;
    }

    @Override
    public Optional<Usuario> obterUsuarioPorLogin(String login) {
        return usuariosRepository.findUsuarioByLoginEquals(login);
    }

    @Override
    public Usuario obterUsuarioPorId(Integer id) {
        return usuariosRepository.findUsuarioById(id);
    }

    private void validarSeJaExisteUsuario(UsuarioDTO usuarioDTO) {

        if (usuariosRepository.existsUsuarioByNome(usuarioDTO.getNome())) {
            throw new RuntimeException("Usuário "+usuarioDTO.getNome() +" já Cadastrado");
        }

        if (usuariosRepository.existsUsuarioByLogin(usuarioDTO.getLogin())) {
            throw new RuntimeException("Usuário já cadastrado com esse Login");
        }
    }


    private Usuario obterUsuario(UsuarioDTO usuarioDTO) {

        return UsuarioBuilder.of()
                .comNome(usuarioDTO.getNome())
                .comLogin(usuarioDTO.getLogin())
                .comIdade(usuarioDTO.getIdade())
                .comPeso(usuarioDTO.getPeso())
                .comSenha(encoder.encode(usuarioDTO.getSenha()))
                .comSexo(usuarioDTO.getSexo())
                .comDiabetico(usuarioDTO.getDiabetico())
                .comHipertenso(usuarioDTO.getHipertenso())
                .build();
    }
}
