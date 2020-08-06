package br.edu.ufersa.multcare.web.resources;

import static java.lang.Boolean.TRUE;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufersa.multcare.persistence.entities.Exame;
import br.edu.ufersa.multcare.service.ExameService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


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

/*	@PostMapping(name = "/exames/xml", consumes = { MediaType.APPLICATION_XML_VALUE,})
	public ResponseEntity cadastrarExamesPorXml(@RequestBody ExamesXmlDTO examesXmlDTO) {
		List<Exame> exames = exameService.cadastrarExamePorXml(examesXmlDTO);
		return ResponseEntity.ok(exames);
	}
	*/

	@RequestMapping(value = "/exames/xml", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_XML_VALUE,})
	public List<Exame> cadastrarExamesPorXml(@RequestBody List<Exame> examesXmlDTO) {
		List<Exame> exames = (List<Exame>) exameService.cadastrarExamePorXml(examesXmlDTO);
		return exames;
	}
	
	/*
	@RequestMapping(value = "/saveall", method = RequestMethod.POST)
	@ResponseBody
	public List<Exame> saveAllExame(@RequestBody List<Exame> exameList) {
		List<Exame> exameResponse = (List<Exame>) exameService.saveAllExame(exameList);
		return exameResponse;
	}
	
	 /* @PostMapping("/tutorials")
	  public ResponseEntity<Exame> createExame(@RequestBody Exame exame) {
		  
	    try {
	    	Exame _exame = exameRepository.save(new Exame(exame.getNome(), 
	    			exame.getResultado()));
	      return new ResponseEntity<>(_exame, HttpStatus.CREATED);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
	    }
	  }
	
	*/
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