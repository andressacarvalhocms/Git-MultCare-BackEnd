package br.edu.ufersa.multcare.service.impl;


import static br.edu.ufersa.multcare.security.SecurityUtils.obterIdUsuarioAutenticado;

import java.util.List;

import org.springframework.stereotype.Component;

import br.edu.ufersa.multcare.persistence.entities.MedicamentoTriagem;
import br.edu.ufersa.multcare.persistence.entities.Usuario;
import br.edu.ufersa.multcare.persistence.repositories.MedicamentoTriagemRepository;
import br.edu.ufersa.multcare.service.MedicamentoTriagemService;
import br.edu.ufersa.multcare.service.UsuarioService;


@Component
public class MedicamentoTriagemServiceImpl implements MedicamentoTriagemService {


    private final MedicamentoTriagemRepository medicamentoTriagemRepository;

    private final UsuarioService usuarioService;

    public MedicamentoTriagemServiceImpl(MedicamentoTriagemRepository medicamentoTriagemRepository, UsuarioService usuarioService) {
        this.medicamentoTriagemRepository = medicamentoTriagemRepository;
        this.usuarioService = usuarioService;
    }

    @Override
    public List<MedicamentoTriagem> listarMedicamentoTriagemUsuarioLogado() {
//        return sintomaRepository.listarSintomasPorUsuario(obterIdUsuarioAutenticado());
        // TODO refazer
        return null;
    }

    @Override
    public List<MedicamentoTriagem> listarMedicamentoTriagem() {
        return medicamentoTriagemRepository.findAll();
    }

    @Override
    public MedicamentoTriagem cadastrarMedicamentoTriagem(MedicamentoTriagem medicamentoTriagem) {
        Usuario usuario = usuarioService.obterUsuarioPorId(obterIdUsuarioAutenticado());
        // TODO relacionar com usuario
        return medicamentoTriagemRepository.save(medicamentoTriagem);
    }

    @Override
    public void deletarMedicamentoTriagem(MedicamentoTriagem medicamentoTriagem) {
    	medicamentoTriagemRepository.delete(medicamentoTriagem);
    }

    @Override
    public MedicamentoTriagem atualizarMedicamentoTriagem(MedicamentoTriagem medicamentoTriagem) {
        return medicamentoTriagemRepository.save(medicamentoTriagem);
    }

    @Override
    public List<MedicamentoTriagem> obterMedicamentoTriagemPorId(List<Integer> ids) {
        return medicamentoTriagemRepository.findAllByIdIn(ids);
    }
}

