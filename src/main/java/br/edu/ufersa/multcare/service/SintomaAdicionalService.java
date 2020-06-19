package br.edu.ufersa.multcare.service;

import java.util.List;

import br.edu.ufersa.multcare.persistence.entities.SintomaAdicional;


public interface SintomaAdicionalService {

   
    List<SintomaAdicional> listarSintomasAdicionalUsuarioLogado();

    List<SintomaAdicional> listarSintomasAdicional();

    SintomaAdicional cadastrarSintomasAdicional(SintomaAdicional sintomaAdicional);

    void deletarSintomasAdicional(SintomaAdicional sintomaAdicional);

    SintomaAdicional atualizarSintomasAdicional(SintomaAdicional sintomaAdicional);

    List<SintomaAdicional> obterSintomasAdicionalPorId(List<Integer> ids);

}
