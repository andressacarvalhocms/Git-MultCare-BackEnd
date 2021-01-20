package br.edu.ufersa.multcare.service.impl;


import static br.edu.ufersa.multcare.security.SecurityUtils.obterIdUsuarioAutenticado;

import java.util.List;

import org.springframework.stereotype.Component;

import br.edu.ufersa.multcare.persistence.entities.SintomaTriagem;
import br.edu.ufersa.multcare.persistence.entities.Usuario;
import br.edu.ufersa.multcare.persistence.repositories.SintomaTriagemRepository;
import br.edu.ufersa.multcare.service.SintomaTriagemService;
import br.edu.ufersa.multcare.service.UsuarioService;


@Component
public class SintomaTriagemServiceImpl implements SintomaTriagemService {


    private final SintomaTriagemRepository SintomaTriagemRepository;

    private final UsuarioService usuarioService;

    public SintomaTriagemServiceImpl(SintomaTriagemRepository SintomaTriagemRepository, UsuarioService usuarioService) {
        this.SintomaTriagemRepository = SintomaTriagemRepository;
        this.usuarioService = usuarioService;
    }

    @Override
    public List<SintomaTriagem> listarSintomaTriagemUsuarioLogado() {
//        return sintomaRepository.listarSintomasPorUsuario(obterIdUsuarioAutenticado());
        // TODO refazer
        return null;
    }

    @Override
    public List<SintomaTriagem> listarSintomaTriagem() {
        return SintomaTriagemRepository.findAll();
    }

    @Override
    public SintomaTriagem cadastrarSintomaTriagem(SintomaTriagem SintomaTriagem) {
        Usuario usuario = usuarioService.obterUsuarioPorId(obterIdUsuarioAutenticado());
        // TODO relacionar com usuario
        return SintomaTriagemRepository.save(SintomaTriagem);
    }

    @Override
    public void deletarSintomaTriagem(SintomaTriagem SintomaTriagem) {
    	SintomaTriagemRepository.delete(SintomaTriagem);
    }

    @Override
    public SintomaTriagem atualizarSintomaTriagem(SintomaTriagem SintomaTriagem) {
        return SintomaTriagemRepository.save(SintomaTriagem);
    }

    @Override
    public List<SintomaTriagem> obterSintomaTriagemPorId(List<Integer> ids) {
        return SintomaTriagemRepository.findAllByIdIn(ids);
    }
}

