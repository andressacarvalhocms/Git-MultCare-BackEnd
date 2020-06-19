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

import br.edu.ufersa.multcare.persistence.entities.Sintoma;
import br.edu.ufersa.multcare.service.SintomasService;


@RestController
@RequestMapping(value="/api")
public class SintomasResource {
	
	@Autowired
	private SintomasService sintomasService;

	@GetMapping("obterListaSintomas")
	public ResponseEntity<List<Sintoma>> obterTodosSintomas() {
		List<Sintoma> sintomas = sintomasService.listarSintomas();
		return ResponseEntity.ok(sintomas);
	}

	@GetMapping("/sintomasUsuario")
	public ResponseEntity<List<Sintoma>> listaSintomasUsuario(){
		List<Sintoma> sintomas = sintomasService.listarSintomasUsuarioLogado();
		return ResponseEntity.ok(sintomas);
	}

	@PostMapping("/sintomas")
	public ResponseEntity<Sintoma> salvaSintomas(@RequestBody Sintoma sintoma) {
		Sintoma sintomaSalvo = sintomasService.cadastrarSintomas(sintoma);
		return ResponseEntity.ok(sintomaSalvo);
	}
	
	@DeleteMapping("/sintomas")
	public ResponseEntity<Boolean> deletaSintomas(@RequestBody Sintoma sintoma) {
		sintomasService.deletarSintomas(sintoma);
		return ResponseEntity.ok(TRUE);
	}
	
	@PutMapping("/sintomas")
	public ResponseEntity<Sintoma> atualizaSintomas(@RequestBody Sintoma sintoma) {
		Sintoma sintomasAtualizado = sintomasService.atualizarSintomas(sintoma);
		return ResponseEntity.ok(sintomasAtualizado);
	}
}

