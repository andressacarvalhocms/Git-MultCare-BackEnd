package br.edu.ufersa.multcare.service;

import java.util.List;

import br.edu.ufersa.multcare.persistence.entities.Patologia;

public interface PatologiaService {

    List<Patologia> listarPatologiaUsuarioLogado();

    List<Patologia> listarPatologia();

    Patologia cadastrarPatologia(Patologia patologia);

    void deletarPatologia(Patologia patologia);

    Patologia atualizarPatologia(Patologia patologia);

    List<Patologia> obterPatologiaPorId(List<Integer> ids);
}
