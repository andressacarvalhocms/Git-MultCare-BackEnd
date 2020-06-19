package br.edu.ufersa.multcare.service;

import java.util.List;

import br.edu.ufersa.multcare.persistence.entities.QuestionarioCovid;
import br.edu.ufersa.multcare.shared.dto.QuestionarioCovidDTO;

public interface QuestionarioCovidService {

    List<QuestionarioCovid> listarQuestionarioCovidPorUsuario();

    QuestionarioCovid cadastrarQuestionarioCovid(QuestionarioCovidDTO questionarioCovid);

    QuestionarioCovid atualizarQuestionarioCovid(QuestionarioCovid questionarioCovid);

    void deletarQuestionarioCovid(QuestionarioCovid questionarioCovid);
}
