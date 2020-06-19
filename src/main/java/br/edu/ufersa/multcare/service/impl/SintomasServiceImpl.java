package br.edu.ufersa.multcare.service.impl;

import static br.edu.ufersa.multcare.security.SecurityUtils.obterIdUsuarioAutenticado;

import java.util.List;

import org.springframework.stereotype.Component;

import br.edu.ufersa.multcare.persistence.entities.Sintoma;
import br.edu.ufersa.multcare.persistence.entities.Usuario;
import br.edu.ufersa.multcare.persistence.repositories.SintomaRepository;
import br.edu.ufersa.multcare.service.SintomasService;
import br.edu.ufersa.multcare.service.UsuarioService;


@Component
public class SintomasServiceImpl implements SintomasService {


    private final SintomaRepository sintomaRepository;

    private final UsuarioService usuarioService;

    public SintomasServiceImpl(SintomaRepository sintomaRepository, UsuarioService usuarioService) {
        this.sintomaRepository = sintomaRepository;
        this.usuarioService = usuarioService;
    }

    @Override
    public List<Sintoma> listarSintomasUsuarioLogado() {
//        return sintomaRepository.listarSintomasPorUsuario(obterIdUsuarioAutenticado());
        // TODO refazer
        return null;
    }

    @Override
    public List<Sintoma> listarSintomas() {
        return sintomaRepository.findAll();
    }

    @Override
    public Sintoma cadastrarSintomas(Sintoma sintoma) {
        Usuario usuario = usuarioService.obterUsuarioPorId(obterIdUsuarioAutenticado());
        // TODO relacionar com usuario
        return sintomaRepository.save(sintoma);
    }

    @Override
    public void deletarSintomas(Sintoma sintoma) {
    	sintomaRepository.delete(sintoma);
    }

    @Override
    public Sintoma atualizarSintomas(Sintoma sintoma) {
        return sintomaRepository.save(sintoma);
    }

    @Override
    public List<Sintoma> obterSintomasPorId(List<Integer> ids) {
        return sintomaRepository.findAllByIdIn(ids);
    }
}
