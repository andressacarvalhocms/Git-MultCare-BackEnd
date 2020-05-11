package br.edu.ufersa.multcare.service.impl;

import br.edu.ufersa.multcare.persistence.entities.Medicamento;
import br.edu.ufersa.multcare.persistence.entities.Usuario;
import br.edu.ufersa.multcare.persistence.repositories.MedicamentoRepository;
import br.edu.ufersa.multcare.service.MedicamentoService;
import br.edu.ufersa.multcare.service.UsuarioService;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

import static br.edu.ufersa.multcare.security.SecurityUtils.obterIdUsuarioAutenticado;

@Component
public class MedicamentoServiceImpl implements MedicamentoService {

    private final MedicamentoRepository medicamentoRepository;

    private final UsuarioService usuarioService;

    public MedicamentoServiceImpl(MedicamentoRepository medicamentoRepository, UsuarioService usuarioService) {
        this.medicamentoRepository = medicamentoRepository;
        this.usuarioService = usuarioService;
    }

    @Override
    public List<Medicamento> listarMedicamentoUsuarioLogado() {
        return medicamentoRepository.listarMedicamentosPorUsuario(obterIdUsuarioAutenticado());
    }

    @Override
    public List<Medicamento> listarMedicamentos() {

        return medicamentoRepository.findAll();
    }

    @Override
    public Medicamento cadastrarMedicamento(Medicamento medicamento) {
        Usuario usuario = usuarioService.obterUsuarioPorId(obterIdUsuarioAutenticado());
        medicamento.setUsuario(usuario);
        medicamento.setDataCadastro(new Date());
        return medicamentoRepository.save(medicamento);
    }

    @Override
    public void deletarMedicamento(Medicamento medicamento) {
        medicamentoRepository.delete(medicamento);
    }

    @Override
    public Medicamento atualizarMedicamento(Medicamento medicamento) {
        return medicamentoRepository.save(medicamento);
    }
}
