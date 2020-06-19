package br.edu.ufersa.multcare.service.impl;


import static br.edu.ufersa.multcare.security.SecurityUtils.obterIdUsuarioAutenticado;

import java.util.List;

import org.springframework.stereotype.Component;

import br.edu.ufersa.multcare.persistence.entities.Patologia;
import br.edu.ufersa.multcare.persistence.entities.Usuario;
import br.edu.ufersa.multcare.persistence.repositories.PatologiaRepository;
import br.edu.ufersa.multcare.service.PatologiaService;
import br.edu.ufersa.multcare.service.UsuarioService;


@Component
public class PatologiaServiceImpl implements PatologiaService {


    private final PatologiaRepository patologiaRepository;

    private final UsuarioService usuarioService;

    public PatologiaServiceImpl(PatologiaRepository patologiaRepository, UsuarioService usuarioService) {
        this.patologiaRepository = patologiaRepository;
        this.usuarioService = usuarioService;
    }

    @Override
    public List<Patologia> listarPatologiaUsuarioLogado() {
//        return sintomaRepository.listarSintomasPorUsuario(obterIdUsuarioAutenticado());
        // TODO refazer
        return null;
    }

    @Override
    public List<Patologia> listarPatologia() {
        return patologiaRepository.findAll();
    }

    @Override
    public Patologia cadastrarPatologia(Patologia patologia) {
        Usuario usuario = usuarioService.obterUsuarioPorId(obterIdUsuarioAutenticado());
        // TODO relacionar com usuario
        return patologiaRepository.save(patologia);
    }

    @Override
    public void deletarPatologia(Patologia patologia) {
    	patologiaRepository.delete(patologia);
    }

    @Override
    public Patologia atualizarPatologia(Patologia patologia) {
        return patologiaRepository.save(patologia);
    }

    @Override
    public List<Patologia> obterPatologiaPorId(List<Integer> ids) {
        return patologiaRepository.findAllByIdIn(ids);
    }
}


