package br.edu.ufersa.multcare.service;

import java.util.List;

import br.edu.ufersa.multcare.persistence.entities.QuestionarioCovid;

public interface QuestionarioCovidService {

    List<QuestionarioCovid> listarQuestionarioCovidPorUsuario();

    QuestionarioCovid cadastrarQuestionarioCovid(QuestionarioCovid questionarioCovid);

    QuestionarioCovid atualizarQuestionarioCovid(QuestionarioCovid questionarioCovid);

    void deletarQuestionarioCovid(QuestionarioCovid questionarioCovid);
}
