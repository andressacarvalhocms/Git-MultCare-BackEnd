package br.edu.ufersa.multcare.service;

import java.util.List;

import br.edu.ufersa.multcare.persistence.entities.Sintoma;


public interface SintomasService {

   
    List<Sintoma> listarSintomasUsuarioLogado();

    List<Sintoma> listarSintomas();

    Sintoma cadastrarSintomas(Sintoma sintoma);

    void deletarSintomas(Sintoma sintoma);

    Sintoma atualizarSintomas(Sintoma sintoma);

    List<Sintoma> obterSintomasPorId(List<Integer> ids);

}
