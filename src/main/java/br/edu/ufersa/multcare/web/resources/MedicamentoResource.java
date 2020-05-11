package br.edu.ufersa.multcare.web.resources;

import br.edu.ufersa.multcare.persistence.entities.Medicamento;
import br.edu.ufersa.multcare.service.MedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.lang.Boolean.TRUE;


@RestController
@RequestMapping(value="/api")
public class MedicamentoResource {
	
	@Autowired
	private MedicamentoService medicamentoService;

	@GetMapping("/medicamentoUsuario")
	public ResponseEntity<List<Medicamento>> listaMedicamentoUsuario(){
		List<Medicamento> medicamentos = medicamentoService.listarMedicamentoUsuarioLogado();
		return ResponseEntity.ok(medicamentos);
	}

	@PostMapping("/medicamento")
	public ResponseEntity<Medicamento> salvaMedicamento(@RequestBody Medicamento medicamento) {
		Medicamento medicamentoSalvo = medicamentoService.cadastrarMedicamento(medicamento);
		return ResponseEntity.ok(medicamentoSalvo);
	}
	
	@DeleteMapping("/medicamento")
	public ResponseEntity<Boolean> deletaMedicamento(@RequestBody Medicamento medicamento) {
		medicamentoService.deletarMedicamento(medicamento);
		return ResponseEntity.ok(TRUE);
	}
	
	@PutMapping("/medicamento")
	public ResponseEntity<Medicamento> atualizaMedicamento(@RequestBody Medicamento medicamento) {
		Medicamento medicamentoAtualizado = medicamentoService.atualizarMedicamento(medicamento);
		return ResponseEntity.ok(medicamentoAtualizado);
	}
}