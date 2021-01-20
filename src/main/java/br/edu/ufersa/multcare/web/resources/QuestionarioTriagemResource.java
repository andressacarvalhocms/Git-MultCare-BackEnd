package br.edu.ufersa.multcare.web.resources;


import static java.lang.Boolean.TRUE;

import java.util.List;

import br.edu.ufersa.multcare.shared.dto.QuestionarioTriagemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufersa.multcare.persistence.entities.QuestionarioTriagem;
import br.edu.ufersa.multcare.service.QuestionarioTriagemService;


@RestController
@RequestMapping(value="/api")
public class QuestionarioTriagemResource {

	@Autowired
	private QuestionarioTriagemService questionarioTriagemService;

	@GetMapping("/questionarioTriagemPorUsuario")
	public ResponseEntity<List<QuestionarioTriagem>> listaQuestionarioTriagemPorUsuario(){
		List<QuestionarioTriagem> questionarioTriagem = questionarioTriagemService.listarQuestionarioTriagemPorUsuario();
		return ResponseEntity.ok(questionarioTriagem);
	}

//	@PostMapping("/questionarioTriagem")
//	public ResponseEntity<QuestionarioTriagem> salvaQuestionarioTriagem(@RequestBody QuestionarioTriagemDTO questionarioTriagem) {
//		QuestionarioTriagem questionarioTriagemSalva = questionarioTriagemService.cadastrarQuestionarioTriagem(questionarioTriagem);
//		return ResponseEntity.ok(questionarioTriagemSalva);
//	}

	@DeleteMapping("/questionarioTriagem")
	public ResponseEntity<Boolean> deletaQuestionarioTriagem(@RequestBody QuestionarioTriagem questionarioTriagem) {
		questionarioTriagemService.deletarQuestionarioTriagem(questionarioTriagem);
		return ResponseEntity.ok(TRUE);
	}

	@PutMapping("/questionarioTriagem")
	public ResponseEntity<QuestionarioTriagem> atualizaQuestionarioTriagem(@RequestBody QuestionarioTriagem questionarioTriagem) {
		QuestionarioTriagem questionarioTriagemAtualizada = questionarioTriagemService.atualizarQuestionarioTriagem(questionarioTriagem);
		return ResponseEntity.ok(questionarioTriagemAtualizada);
	}
}