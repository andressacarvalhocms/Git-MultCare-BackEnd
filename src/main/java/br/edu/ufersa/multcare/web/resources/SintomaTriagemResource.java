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

import br.edu.ufersa.multcare.persistence.entities.SintomaTriagem;
import br.edu.ufersa.multcare.service.SintomaTriagemService;


@RestController
@RequestMapping(value="/api")
public class SintomaTriagemResource {
	
	@Autowired
	private SintomaTriagemService sintomaTriagemService;

	@GetMapping("obterListaSintomaTriagem")
	public ResponseEntity<List<SintomaTriagem>> obterTodosSintomaTriagem() {
		List<SintomaTriagem> sintomaTriagem = sintomaTriagemService.listarSintomaTriagem();
		return ResponseEntity.ok(sintomaTriagem);
	}

	@GetMapping("/sintomaTriagemUsuario")
	public ResponseEntity<List<SintomaTriagem>> listaSintomaTriagemUsuario(){
		List<SintomaTriagem> sintomaTriagem = sintomaTriagemService.listarSintomaTriagemUsuarioLogado();
		return ResponseEntity.ok(sintomaTriagem);
	}

	@PostMapping("/sintomaTriagem")
	public ResponseEntity<SintomaTriagem> salvaSintomaTriagem(@RequestBody SintomaTriagem sintomaTriagem) {
		SintomaTriagem sintomaTriagemSalvo = sintomaTriagemService.cadastrarSintomaTriagem(sintomaTriagem);
		return ResponseEntity.ok(sintomaTriagemSalvo);
	}
	
	@DeleteMapping("/sintomaTriagem")
	public ResponseEntity<Boolean> deletaSintomaTriagem(@RequestBody SintomaTriagem sintomaTriagem) {
		sintomaTriagemService.deletarSintomaTriagem(sintomaTriagem);
		return ResponseEntity.ok(TRUE);
	}
	
	@PutMapping("/sintomaTriagem")
	public ResponseEntity<SintomaTriagem> atualizaSintomaTriagem(@RequestBody SintomaTriagem sintomaTriagem) {
		SintomaTriagem sintomaTriagemAtualizado = sintomaTriagemService.atualizarSintomaTriagem(sintomaTriagem);
		return ResponseEntity.ok(sintomaTriagemAtualizado);
	}
}

