package br.edu.ufersa.multcare.service.impl;

import static br.edu.ufersa.multcare.security.SecurityUtils.obterIdUsuarioAutenticado;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import br.edu.ufersa.multcare.persistence.entities.QuestionarioCovid;
import br.edu.ufersa.multcare.persistence.entities.Usuario;
import br.edu.ufersa.multcare.persistence.repositories.QuestionarioCovidRepository;
import br.edu.ufersa.multcare.service.QuestionarioCovidService;
import br.edu.ufersa.multcare.service.UsuarioService;


@Component
public class QuestionarioCovidServiceImpl implements QuestionarioCovidService {

    private final QuestionarioCovidRepository questionarioCovidRepository;

    private final UsuarioService usuarioService;

    public QuestionarioCovidServiceImpl(QuestionarioCovidRepository questionarioCovidRepository, UsuarioService usuarioService) {
        this.questionarioCovidRepository = questionarioCovidRepository;
        this.usuarioService = usuarioService;
    }


    @Override
    public List<QuestionarioCovid> listarQuestionarioCovidPorUsuario() {
        return questionarioCovidRepository.listarQuestionarioCovidPorUsuario(obterIdUsuarioAutenticado());
    }

    @Override
    public QuestionarioCovid cadastrarQuestionarioCovid(QuestionarioCovid questionarioCovid) {
        Usuario usuario = usuarioService.obterUsuarioPorId(obterIdUsuarioAutenticado());
        questionarioCovid.setUsuario(usuario);
        questionarioCovid.setDataCadastro(new Date());
        return questionarioCovidRepository.save(questionarioCovid);
    }

    @Override
    public QuestionarioCovid atualizarQuestionarioCovid(QuestionarioCovid questionarioCovid) {
        return questionarioCovidRepository.save(questionarioCovid);
    }

    @Override
    public void deletarQuestionarioCovid(QuestionarioCovid questionarioCovid) {
    	questionarioCovidRepository.delete(questionarioCovid);
    }
}
