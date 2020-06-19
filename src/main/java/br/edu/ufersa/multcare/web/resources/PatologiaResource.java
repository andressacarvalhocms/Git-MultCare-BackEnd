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

import br.edu.ufersa.multcare.persistence.entities.Patologia;
import br.edu.ufersa.multcare.service.PatologiaService;


@RestController
@RequestMapping(value="/api")
public class PatologiaResource {
	
	@Autowired
	private PatologiaService patologiaService;

	@GetMapping("obterListaPatologia")
	public ResponseEntity<List<Patologia>> obterTodosPatologia() {
		List<Patologia> patologia = patologiaService.listarPatologia();
		return ResponseEntity.ok(patologia);
	}

	@GetMapping("/patologiaUsuario")
	public ResponseEntity<List<Patologia>> listaPatologiaUsuario(){
		List<Patologia> patologia = patologiaService.listarPatologiaUsuarioLogado();
		return ResponseEntity.ok(patologia);
	}

	@PostMapping("/patologia")
	public ResponseEntity<Patologia> salvaPatologia(@RequestBody Patologia patologia) {
		Patologia patologiaSalvo = patologiaService.cadastrarPatologia(patologia);
		return ResponseEntity.ok(patologiaSalvo);
	}
	
	@DeleteMapping("/patologia")
	public ResponseEntity<Boolean> deletaPatologia(@RequestBody Patologia patologia) {
		patologiaService.deletarPatologia(patologia);
		return ResponseEntity.ok(TRUE);
	}
	
	@PutMapping("/patologia")
	public ResponseEntity<Patologia> atualizaPatologia(@RequestBody Patologia patologia) {
		Patologia patologiaAtualizado = patologiaService.atualizarPatologia(patologia);
		return ResponseEntity.ok(patologiaAtualizado);
	}
}


