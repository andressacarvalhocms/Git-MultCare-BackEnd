package br.edu.ufersa.multcare.web.resources;

import java.util.List;

import br.edu.ufersa.multcare.service.AlergiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufersa.multcare.persistence.entities.Alergia;

import static java.lang.Boolean.TRUE;


@RestController
@RequestMapping(value="/api")
public class AlergiaResource {

	@Autowired
	private AlergiaService alergiaService;

	@GetMapping("/alergiaPorUsuario")
	public ResponseEntity<List<Alergia>> listaAlergiaPorUsuario(){
		List<Alergia> alergias = alergiaService.listarAlergiasPorUsuario();
		return ResponseEntity.ok(alergias);
	}

	@PostMapping("/alergia")
	public ResponseEntity<Alergia> salvaAlergia(@RequestBody Alergia alergia) {
		Alergia alergiaSalva = alergiaService.cadastrarAlergia(alergia);
		return ResponseEntity.ok(alergiaSalva);
	}

	@DeleteMapping("/alergia")
	public ResponseEntity<Boolean> deletaAlergia(@RequestBody Alergia alergia) {
		alergiaService.deletarAlergia(alergia);
		return ResponseEntity.ok(TRUE);
	}

	@PutMapping("/alergia")
	public ResponseEntity<Alergia> atualizaAlergia(@RequestBody Alergia alergia) {
		Alergia alergiaAtualizada = alergiaService.atualizarAlergia(alergia);
		return ResponseEntity.ok(alergiaAtualizada);
	}
}