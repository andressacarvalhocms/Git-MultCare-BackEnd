package br.edu.ufersa.multcare.web.resources;

import br.edu.ufersa.multcare.persistence.entities.Exame;
import br.edu.ufersa.multcare.service.ExameService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static java.lang.Boolean.TRUE;

@RestController
@RequestMapping(value="/api")
@Api(value="API REST EXAMES")
@CrossOrigin(origins="*")
public class ExameResource {
	
	@Autowired
	private ExameService exameService;
	
	@GetMapping("/examesUsuario")
	@ApiOperation(value="Retorna uma lista de exames do usuário logado")
	public ResponseEntity<List<Exame>> listaExames(){
		List<Exame> exames = exameService.listarExamesUsuarioLogado();
		return ResponseEntity.ok(exames);
	}

	@PostMapping("/exames")
	@ApiOperation(value="salva um exame")
	public ResponseEntity<Exame> salvaExame(@RequestBody Exame exame){
		Exame exameSalvo = exameService.cadastrarExame(exame);
		return ResponseEntity.ok(exameSalvo);
	}
	
	@DeleteMapping("/exames")
	@ApiOperation(value="deleta um exame")
	public ResponseEntity<Boolean> deletaExame(@RequestBody Exame exame){
		exameService.deletarExame(exame);
		return ResponseEntity.ok(TRUE);
	}
	
	@PutMapping("/exames")
	@ApiOperation(value="altera um exame")
	public ResponseEntity<Exame> atualizaExame(@RequestBody Exame exame) {
		Exame exameAtualizado = exameService.atualizarExame(exame);
		return ResponseEntity.ok(exameAtualizado);
	}

	@GetMapping("/exames-cadastrados")
	public ResponseEntity<?> examesCadastradosUsuario() {
		Map<String, Boolean> examesCadastrados = exameService.examesCadastradosUsuarioLogado();
		return ResponseEntity.ok(examesCadastrados);
	}
}