package br.edu.ufersa.multcare.service;

import br.edu.ufersa.multcare.persistence.entities.Medicamento;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MedicamentoService {

    List<Medicamento> listarMedicamentoUsuarioLogado();

    List<Medicamento> listarMedicamentos();

    Medicamento cadastrarMedicamento(Medicamento medicamento);

    void deletarMedicamento(Medicamento medicamento);

    Medicamento atualizarMedicamento(Medicamento medicamento);
}
