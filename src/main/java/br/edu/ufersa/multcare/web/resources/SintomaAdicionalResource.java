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

import br.edu.ufersa.multcare.persistence.entities.SintomaAdicional;
import br.edu.ufersa.multcare.service.SintomaAdicionalService;


@RestController
@RequestMapping(value="/api")
public class SintomaAdicionalResource {
	
	@Autowired
	private SintomaAdicionalService sintomaAdicionalService;

	@GetMapping("obterListaSintomasAdicional")
	public ResponseEntity<List<SintomaAdicional>> obterTodosSintomasAdicional() {
		List<SintomaAdicional> sintomaAdicional = sintomaAdicionalService.listarSintomasAdicional();
		return ResponseEntity.ok(sintomaAdicional);
	}

	@GetMapping("/sintomaAdicionalUsuario")
	public ResponseEntity<List<SintomaAdicional>> listaSintomaAdicionalUsuario(){
		List<SintomaAdicional> sintomaAdicional = sintomaAdicionalService.listarSintomasAdicionalUsuarioLogado();
		return ResponseEntity.ok(sintomaAdicional);
	}

	@PostMapping("/sintomaAdicional")
	public ResponseEntity<SintomaAdicional> salvaSintomaAdicional(@RequestBody SintomaAdicional sintomaAdicional) {
		SintomaAdicional sintomaAdicionalSalvo = sintomaAdicionalService.cadastrarSintomasAdicional(sintomaAdicional);
		return ResponseEntity.ok(sintomaAdicionalSalvo);
	}
	
	@DeleteMapping("/sintomaAdicional")
	public ResponseEntity<Boolean> deletaSintomaAdicional(@RequestBody SintomaAdicional sintomaAdicional) {
		sintomaAdicionalService.deletarSintomasAdicional(sintomaAdicional);
		return ResponseEntity.ok(TRUE);
	}
	
	@PutMapping("/sintomaAdicional")
	public ResponseEntity<SintomaAdicional> atualizaSintomaAdicional(@RequestBody SintomaAdicional sintomaAdicional) {
		SintomaAdicional sintomaAdicionalAtualizado = sintomaAdicionalService.atualizarSintomasAdicional(sintomaAdicional);
		return ResponseEntity.ok(sintomaAdicionalAtualizado);
	}
}



