package br.edu.ufersa.multcare.service.impl;

import br.edu.ufersa.multcare.persistence.entities.Alergia;
import br.edu.ufersa.multcare.persistence.entities.Usuario;
import br.edu.ufersa.multcare.persistence.repositories.AlergiaRepository;
import br.edu.ufersa.multcare.service.AlergiaService;
import br.edu.ufersa.multcare.service.UsuarioService;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

import static br.edu.ufersa.multcare.security.SecurityUtils.obterIdUsuarioAutenticado;

@Component
public class AlergiaServiceImpl implements AlergiaService {

    private final AlergiaRepository alergiaRepository;

    private final UsuarioService usuarioService;

    public AlergiaServiceImpl(AlergiaRepository alergiaRepository, UsuarioService usuarioService) {
        this.alergiaRepository = alergiaRepository;
        this.usuarioService = usuarioService;
    }


    @Override
    public List<Alergia> listarAlergiasPorUsuario() {
        return alergiaRepository.listarAlergiasPorUsuario(obterIdUsuarioAutenticado());
    }

    @Override
    public Alergia cadastrarAlergia(Alergia alergia) {
        Usuario usuario = usuarioService.obterUsuarioPorId(obterIdUsuarioAutenticado());
        alergia.setUsuario(usuario);
        alergia.setDataCadastro(new Date());
        return alergiaRepository.save(alergia);
    }

    @Override
    public Alergia atualizarAlergia(Alergia alergia) {
        return alergiaRepository.save(alergia);
    }

    @Override
    public void deletarAlergia(Alergia alergia) {
        alergiaRepository.delete(alergia);
    }
}
