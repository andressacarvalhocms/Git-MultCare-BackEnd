package br.edu.ufersa.multcare.web.resources;

import static java.lang.Boolean.TRUE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufersa.multcare.persistence.entities.QuestionarioCovid;
import br.edu.ufersa.multcare.service.QuestionarioCovidService;


@RestController
@RequestMapping(value="/api")
public class QuestionarioCovidResource {

	@Autowired
	private QuestionarioCovidService questionarioCovidService;

	@GetMapping("/questionarioCovidPorUsuario")
	public ResponseEntity<List<QuestionarioCovid>> listaQuestionarioCovidPorUsuario(){
		List<QuestionarioCovid> questionarioCovid = questionarioCovidService.listarQuestionarioCovidPorUsuario();
		return ResponseEntity.ok(questionarioCovid);
	}

	@PostMapping("/questionarioCovid")
	public ResponseEntity<QuestionarioCovid> salvaQuestionarioCovid(@RequestBody QuestionarioCovid questionarioCovid) {
		QuestionarioCovid questionarioCovidSalva = questionarioCovidService.cadastrarQuestionarioCovid(questionarioCovid);
		return ResponseEntity.ok(questionarioCovidSalva);
	}

	@DeleteMapping("/questionarioCovid")
	public ResponseEntity<Boolean> deletaQuestionarioCovid(@RequestBody QuestionarioCovid questionarioCovid) {
		questionarioCovidService.deletarQuestionarioCovid(questionarioCovid);
		return ResponseEntity.ok(TRUE);
	}

	@PutMapping("/questionarioCovid")
	public ResponseEntity<QuestionarioCovid> atualizaQuestionarioCovid(@RequestBody QuestionarioCovid questionarioCovid) {
		QuestionarioCovid questionarioCovidAtualizada = questionarioCovidService.atualizarQuestionarioCovid(questionarioCovid);
		return ResponseEntity.ok(questionarioCovidAtualizada);
	}
}