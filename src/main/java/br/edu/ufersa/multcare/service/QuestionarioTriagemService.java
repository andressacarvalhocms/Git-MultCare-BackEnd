package br.edu.ufersa.multcare.service;

import java.util.List;

import br.edu.ufersa.multcare.persistence.entities.QuestionarioTriagem;
import br.edu.ufersa.multcare.shared.dto.QuestionarioTriagemDTO;

public interface QuestionarioTriagemService {

    List<QuestionarioTriagem> listarQuestionarioTriagemPorUsuario();

    QuestionarioTriagem cadastrarQuestionarioTriagem(QuestionarioTriagemDTO questionarioTriagem);

    QuestionarioTriagem atualizarQuestionarioTriagem(QuestionarioTriagem questionarioTriagem);

    void deletarQuestionarioTriagem(QuestionarioTriagem questionarioTriagem);
}
