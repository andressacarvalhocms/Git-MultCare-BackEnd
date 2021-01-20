package br.edu.ufersa.multcare.web.resources;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufersa.multcare.persistence.entities.QuestionarioTriagem;
import br.edu.ufersa.multcare.service.SistemaEspecialistaService;
import br.edu.ufersa.multcare.shared.dto.QuestionarioTriagemDTO;

@RestController
@RequestMapping(value="/api")
public class SistemaEspecialistaResource {

	private SistemaEspecialistaService sistemaEspecialistaService;

	public SistemaEspecialistaResource(SistemaEspecialistaService sistemaEspecialistaService) {
		this.sistemaEspecialistaService = sistemaEspecialistaService;
	}

	@PostMapping("/realizarClassificaoRecomendacao")
	public ResponseEntity<String> salvaQuestionarioTriagem(@RequestBody QuestionarioTriagemDTO questionarioTriagem) {
		String resultado = sistemaEspecialistaService.realizarClassificacaoRecomendacao(questionarioTriagem);
		return ResponseEntity.ok(resultado);
	}
	
	@GetMapping("/obter-analisesSE")
	public ResponseEntity<?> obterAnalisesSEUsuarioAutenticado() {
		List<QuestionarioTriagem> analisesSE = sistemaEspecialistaService.obterAnalisesSEDoUsuarioAutenticado();
		return ResponseEntity.ok(analisesSE);
	}
	
}