package br.edu.ufersa.multcare.service.impl;

import static br.edu.ufersa.multcare.security.SecurityUtils.obterIdUsuarioAutenticado;

import java.util.List;

import org.springframework.stereotype.Component;

import br.edu.ufersa.multcare.persistence.entities.MedicamentoTriagem;
import br.edu.ufersa.multcare.persistence.entities.QuestionarioTriagem;
import br.edu.ufersa.multcare.persistence.entities.SintomaTriagem;
import br.edu.ufersa.multcare.persistence.entities.Usuario;
import br.edu.ufersa.multcare.persistence.repositories.QuestionarioTriagemRepository;
import br.edu.ufersa.multcare.service.MedicamentoTriagemService;
import br.edu.ufersa.multcare.service.QuestionarioTriagemService;
import br.edu.ufersa.multcare.service.SintomaTriagemService;
import br.edu.ufersa.multcare.service.UsuarioService;
import br.edu.ufersa.multcare.shared.builder.QuestionarioTriagemBuilder;
import br.edu.ufersa.multcare.shared.dto.QuestionarioTriagemDTO;


@Component
public class QuestionarioTriagemServiceImpl implements QuestionarioTriagemService {

    private final QuestionarioTriagemRepository questionarioTriagemRepository;
    private final UsuarioService usuarioService;
    private final SintomaTriagemService sintomaTriagemService;
    private final MedicamentoTriagemService medicamentoTriagemService;


    public QuestionarioTriagemServiceImpl(QuestionarioTriagemRepository questionarioTriagemRepository, UsuarioService usuarioService, SintomaTriagemService sintomaTriagemService, MedicamentoTriagemService medicamentoTriagemService ) {
        this.questionarioTriagemRepository = questionarioTriagemRepository;
        this.usuarioService = usuarioService;
        this.sintomaTriagemService = sintomaTriagemService;
        this.medicamentoTriagemService = medicamentoTriagemService;
    }


    @Override
    public List<QuestionarioTriagem> listarQuestionarioTriagemPorUsuario() {
        return questionarioTriagemRepository.listarQuestionarioTriagemPorUsuario(obterIdUsuarioAutenticado());
    }

    @Override
    public QuestionarioTriagem cadastrarQuestionarioTriagem(QuestionarioTriagemDTO questionarioTriagem) {
        Usuario usuario = usuarioService.obterUsuarioPorId(obterIdUsuarioAutenticado());
        List<SintomaTriagem> sintomaTriagem = sintomaTriagemService.obterSintomaTriagemPorId(questionarioTriagem.getSintomaTriagem());
        List<MedicamentoTriagem> medicamentoTriagem = medicamentoTriagemService.obterMedicamentoTriagemPorId(questionarioTriagem.getMedicamentoTriagem());

        QuestionarioTriagem questionario = QuestionarioTriagemBuilder.of(questionarioTriagem)
                .comSintomaTriagem(sintomaTriagem)
                .comMedicamentoTriagem(medicamentoTriagem)
                .comUsuario(usuario)
                .build();

        return questionarioTriagemRepository.save(questionario);
    }

    @Override
    public QuestionarioTriagem atualizarQuestionarioTriagem(QuestionarioTriagem questionarioTriagem) {
        return questionarioTriagemRepository.save(questionarioTriagem);
    }

    @Override
    public void deletarQuestionarioTriagem(QuestionarioTriagem questionarioTriagem) {
    	questionarioTriagemRepository.delete(questionarioTriagem);
    }
}
