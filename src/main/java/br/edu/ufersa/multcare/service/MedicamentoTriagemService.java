package br.edu.ufersa.multcare.service;


import java.util.List;

import br.edu.ufersa.multcare.persistence.entities.MedicamentoTriagem;


public interface MedicamentoTriagemService {

   
    List<MedicamentoTriagem> listarMedicamentoTriagemUsuarioLogado();

    List<MedicamentoTriagem> listarMedicamentoTriagem();

    MedicamentoTriagem cadastrarMedicamentoTriagem(MedicamentoTriagem medicamentoTriagem);

    void deletarMedicamentoTriagem(MedicamentoTriagem medicamentoTriagem);

    MedicamentoTriagem atualizarMedicamentoTriagem(MedicamentoTriagem medicamentoTriagem);

    List<MedicamentoTriagem> obterMedicamentoTriagemPorId(List<Integer> ids);

}
