package br.edu.ufersa.multcare.service.impl;

import static br.edu.ufersa.multcare.security.SecurityUtils.obterIdUsuarioAutenticado;

import java.util.List;

import org.springframework.stereotype.Component;

import br.edu.ufersa.multcare.persistence.entities.Patologia;
import br.edu.ufersa.multcare.persistence.entities.QuestionarioCovid;
import br.edu.ufersa.multcare.persistence.entities.Sintoma;
import br.edu.ufersa.multcare.persistence.entities.SintomaAdicional;
import br.edu.ufersa.multcare.persistence.entities.Usuario;
import br.edu.ufersa.multcare.persistence.repositories.QuestionarioCovidRepository;
import br.edu.ufersa.multcare.service.PatologiaService;
import br.edu.ufersa.multcare.service.QuestionarioCovidService;
import br.edu.ufersa.multcare.service.SintomaAdicionalService;
import br.edu.ufersa.multcare.service.SintomasService;
import br.edu.ufersa.multcare.service.UsuarioService;
import br.edu.ufersa.multcare.shared.builder.QuestionarioCovidBuilder;
import br.edu.ufersa.multcare.shared.dto.QuestionarioCovidDTO;


@Component
public class QuestionarioCovidServiceImpl implements QuestionarioCovidService {

    private final QuestionarioCovidRepository questionarioCovidRepository;
    private final UsuarioService usuarioService;
    private final SintomasService sintomasService;
    private final SintomaAdicionalService sintomasAdicionalService;
    private final PatologiaService patologiaService;


    public QuestionarioCovidServiceImpl(QuestionarioCovidRepository questionarioCovidRepository, UsuarioService usuarioService, SintomasService sintomasService, SintomaAdicionalService sintomasAdicionalService, PatologiaService patologiaService ) {
        this.questionarioCovidRepository = questionarioCovidRepository;
        this.usuarioService = usuarioService;
        this.sintomasService = sintomasService;
        this.sintomasAdicionalService = sintomasAdicionalService;
        this.patologiaService = patologiaService;
    }


    @Override
    public List<QuestionarioCovid> listarQuestionarioCovidPorUsuario() {
        return questionarioCovidRepository.listarQuestionarioCovidPorUsuario(obterIdUsuarioAutenticado());
    }

    @Override
    public QuestionarioCovid cadastrarQuestionarioCovid(QuestionarioCovidDTO questionarioCovid) {
        Usuario usuario = usuarioService.obterUsuarioPorId(obterIdUsuarioAutenticado());
        List<Sintoma> sintomas = sintomasService.obterSintomasPorId(questionarioCovid.getSintomas());
        List<SintomaAdicional> sintomasAdicional = sintomasAdicionalService.obterSintomasAdicionalPorId(questionarioCovid.getSintomasAdicional());
        List<Patologia> patologia = patologiaService.obterPatologiaPorId(questionarioCovid.getPatologia());

        QuestionarioCovid questionario = QuestionarioCovidBuilder.of(questionarioCovid)
                .comSintomas(sintomas)
                .comSintomaAdicional(sintomasAdicional)
                .comPatologia(patologia)
                .comUsuario(usuario)
                .build();

        return questionarioCovidRepository.save(questionario);
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
