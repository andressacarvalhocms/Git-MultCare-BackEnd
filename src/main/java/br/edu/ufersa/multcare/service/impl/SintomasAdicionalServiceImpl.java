package br.edu.ufersa.multcare.service.impl;

import static br.edu.ufersa.multcare.security.SecurityUtils.obterIdUsuarioAutenticado;

import java.util.List;

import org.springframework.stereotype.Component;

import br.edu.ufersa.multcare.persistence.entities.SintomaAdicional;
import br.edu.ufersa.multcare.persistence.entities.Usuario;
import br.edu.ufersa.multcare.persistence.repositories.SintomaAdicionalRepository;
import br.edu.ufersa.multcare.service.SintomaAdicionalService;
import br.edu.ufersa.multcare.service.UsuarioService;


@Component
public class SintomasAdicionalServiceImpl implements SintomaAdicionalService {


    private final SintomaAdicionalRepository sintomaAdicionalRepository;

    private final UsuarioService usuarioService;

    public SintomasAdicionalServiceImpl(SintomaAdicionalRepository sintomaAdicionalRepository, UsuarioService usuarioService) {
        this.sintomaAdicionalRepository = sintomaAdicionalRepository;
        this.usuarioService = usuarioService;
    }

    @Override
    public List<SintomaAdicional> listarSintomasAdicionalUsuarioLogado() {
//        return sintomaRepository.listarSintomasPorUsuario(obterIdUsuarioAutenticado());
        // TODO refazer
        return null;
    }

    @Override
    public List<SintomaAdicional> listarSintomasAdicional() {
        return sintomaAdicionalRepository.findAll();
    }

    @Override
    public SintomaAdicional cadastrarSintomasAdicional(SintomaAdicional sintomaAdicional) {
        Usuario usuario = usuarioService.obterUsuarioPorId(obterIdUsuarioAutenticado());
        // TODO relacionar com usuario
        return sintomaAdicionalRepository.save(sintomaAdicional);
    }

    @Override
    public void deletarSintomasAdicional(SintomaAdicional sintomaAdicional) {
    	sintomaAdicionalRepository.delete(sintomaAdicional);
    }

    @Override
    public SintomaAdicional atualizarSintomasAdicional(SintomaAdicional sintomaAdicional) {
        return sintomaAdicionalRepository.save(sintomaAdicional);
    }

    @Override
    public List<SintomaAdicional> obterSintomasAdicionalPorId(List<Integer> ids) {
        return sintomaAdicionalRepository.findAllByIdIn(ids);
    }
}


