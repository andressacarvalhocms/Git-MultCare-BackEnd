package br.edu.ufersa.multcare.service;


import java.util.List;

import br.edu.ufersa.multcare.persistence.entities.SintomaTriagem;


public interface SintomaTriagemService {

   
    List<SintomaTriagem> listarSintomaTriagemUsuarioLogado();

    List<SintomaTriagem> listarSintomaTriagem();

    SintomaTriagem cadastrarSintomaTriagem(SintomaTriagem sintomaTriagem);

    void deletarSintomaTriagem(SintomaTriagem sintomaTriagem);

    SintomaTriagem atualizarSintomaTriagem(SintomaTriagem sintomaTriagem);

    List<SintomaTriagem> obterSintomaTriagemPorId(List<Integer> ids);

}
