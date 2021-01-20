
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

import br.edu.ufersa.multcare.persistence.entities.MedicamentoTriagem;
import br.edu.ufersa.multcare.service.MedicamentoTriagemService;


@RestController
@RequestMapping(value="/api")
public class MedicamentoTriagemResource {
	
	@Autowired
	private MedicamentoTriagemService medicamentoTriagemService;

	@GetMapping("obterListaMedicamentoTriagem")
	public ResponseEntity<List<MedicamentoTriagem>> obterTodosMedicamentoTriagem() {
		List<MedicamentoTriagem> medicamentoTriagem = medicamentoTriagemService.listarMedicamentoTriagem();
		return ResponseEntity.ok(medicamentoTriagem);
	}

	@GetMapping("/medicamentoTriagemUsuario")
	public ResponseEntity<List<MedicamentoTriagem>> listaMedicamentoTriagemUsuario(){
		List<MedicamentoTriagem> medicamentoTriagem = medicamentoTriagemService.listarMedicamentoTriagemUsuarioLogado();
		return ResponseEntity.ok(medicamentoTriagem);
	}

	@PostMapping("/medicamentoTriagem")
	public ResponseEntity<MedicamentoTriagem> salvaMedicamentoTriagem(@RequestBody MedicamentoTriagem medicamentoTriagem) {
		MedicamentoTriagem medicamentoTriagemSalvo = medicamentoTriagemService.cadastrarMedicamentoTriagem(medicamentoTriagem);
		return ResponseEntity.ok(medicamentoTriagemSalvo);
	}
	
	@DeleteMapping("/medicamentoTriagem")
	public ResponseEntity<Boolean> deletaMedicamentoTriagem(@RequestBody MedicamentoTriagem medicamentoTriagem) {
		medicamentoTriagemService.deletarMedicamentoTriagem(medicamentoTriagem);
		return ResponseEntity.ok(TRUE);
	}
	
	@PutMapping("/medicamentoTriagem")
	public ResponseEntity<MedicamentoTriagem> atualizaMedicamentoTriagem(@RequestBody MedicamentoTriagem medicamentoTriagem) {
		MedicamentoTriagem medicamentoTriagemAtualizado = medicamentoTriagemService.atualizarMedicamentoTriagem(medicamentoTriagem);
		return ResponseEntity.ok(medicamentoTriagemAtualizado);
	}
}
