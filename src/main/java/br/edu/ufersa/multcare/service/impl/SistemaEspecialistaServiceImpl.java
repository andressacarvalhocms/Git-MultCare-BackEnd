package br.edu.ufersa.multcare.service.impl;

import static br.edu.ufersa.multcare.security.SecurityUtils.obterIdUsuarioAutenticado;

import java.util.List;

import javax.transaction.Transactional;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Component;

import br.edu.ufersa.multcare.persistence.entities.QuestionarioTriagem;
import br.edu.ufersa.multcare.persistence.entities.SistemaEspecialista;
import br.edu.ufersa.multcare.persistence.repositories.SistemaEspecialistaRepository;
import br.edu.ufersa.multcare.service.QuestionarioTriagemService;
import br.edu.ufersa.multcare.service.SistemaEspecialistaService;
import br.edu.ufersa.multcare.service.UsuarioService;
import br.edu.ufersa.multcare.shared.dto.QuestionarioTriagemDTO;

@Component
@Transactional
public class SistemaEspecialistaServiceImpl implements SistemaEspecialistaService {

    private final QuestionarioTriagemService questionarioTriagemService;
	private final KieContainer kieContainer;
	private final UsuarioService usuarioService;
	private final SistemaEspecialistaRepository sistemaEspecialistaRepository;

	public SistemaEspecialistaServiceImpl(QuestionarioTriagemService questionarioTriagemService, KieContainer kieContainer,
			SistemaEspecialistaRepository sistemaEspecialistaRepository, UsuarioService usuarioService) {
		this.questionarioTriagemService = questionarioTriagemService;
		this.kieContainer = kieContainer;
		this.sistemaEspecialistaRepository = sistemaEspecialistaRepository;
		this.usuarioService = usuarioService;
	}

	@Override
	public String realizarClassificacaoRecomendacao(QuestionarioTriagemDTO dto) {

		QuestionarioTriagem questionarioTriagem = salvarQuestionario(dto);

		SistemaEspecialista sistemaEspecialista = new SistemaEspecialista(questionarioTriagem);

		KieSession kieSession = kieContainer.newKieSession("rulesSession");
		kieSession.insert(sistemaEspecialista);
		kieSession.fireAllRules();
		kieSession.dispose();

		questionarioTriagem.setResultadoClassificacao(sistemaEspecialista.getSuspeitaDoenca());
		questionarioTriagem.setTipoSintomas(sistemaEspecialista.getTipoSintomas());
		
		return atualizarQuestionario(questionarioTriagem).getResultadoClassificacao();
	}

	private QuestionarioTriagem salvarQuestionario(QuestionarioTriagemDTO dto){
    	return questionarioTriagemService.cadastrarQuestionarioTriagem(dto);
	}

	private QuestionarioTriagem atualizarQuestionario(QuestionarioTriagem questionarioTriagem) {
		return questionarioTriagemService.atualizarQuestionarioTriagem(questionarioTriagem);
	}

	@Override
	public List<QuestionarioTriagem> obterAnalisesSEDoUsuarioAutenticado() {
        Integer idUsuario = obterIdUsuarioAutenticado();
        return sistemaEspecialistaRepository.obterAnalisesSEPorUsuario(idUsuario);
	}
	
	
}
