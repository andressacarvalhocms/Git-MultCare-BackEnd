package br.edu.ufersa.multcare.service;

import br.edu.ufersa.multcare.persistence.entities.Alergia;

import java.util.List;

public interface AlergiaService {

    List<Alergia> listarAlergiasPorUsuario();

    Alergia cadastrarAlergia(Alergia alergia);

    Alergia atualizarAlergia(Alergia alergia);

    void deletarAlergia(Alergia alergia);
}
