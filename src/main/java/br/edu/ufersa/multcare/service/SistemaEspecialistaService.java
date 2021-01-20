package br.edu.ufersa.multcare.service;

import java.util.List;

import br.edu.ufersa.multcare.persistence.entities.QuestionarioTriagem;
import br.edu.ufersa.multcare.shared.dto.QuestionarioTriagemDTO;

public interface SistemaEspecialistaService {

    String realizarClassificacaoRecomendacao(QuestionarioTriagemDTO sistemaEspecialista);
    

    List<QuestionarioTriagem> obterAnalisesSEDoUsuarioAutenticado();

}
