package br.edu.ufersa.multcare.service;

import java.util.List;

import br.edu.ufersa.multcare.persistence.entities.Alergia;

public interface AlergiaService {

	List<Alergia> listarAlergiaUsuarioLogado();
	
    List<Alergia> listarAlergiasPorUsuario();

    Alergia cadastrarAlergia(Alergia alergia);

    Alergia atualizarAlergia(Alergia alergia);

    void deletarAlergia(Alergia alergia);
}
