package br.edu.ufersa.multcare.web.resources;

import static java.lang.Boolean.TRUE;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import br.edu.ufersa.multcare.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufersa.multcare.persistence.entities.Medicamento;
import br.edu.ufersa.multcare.persistence.entities.Usuario;
import br.edu.ufersa.multcare.service.MedicamentoService;


@RestController
@RequestMapping(value="/api")
public class MedicamentoResource {
	
	@Autowired
	private MedicamentoService medicamentoService;

	@Autowired
	private EmailService emailService;
	
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

    @Autowired private JavaMailSender mailSender;
    @Scheduled(fixedDelay = 50000)
	public void obterTodosMedicamentosPorPeriodo(){
		List<Medicamento> medicamentos = medicamentoService.obterTodosMedicamentosPorPeriodo();
		medicamentos.forEach(med->{
			Usuario user = med.getUsuario();
			try {
				String conteudo = "Rémedio: "+ med.getNome()+"<br>Hora: "+ med.getHora()+"<br>Tipo: "+ med.getTipo()+"<br>Quantidade diária: "+ med.getQuantidadeDiaria();
				emailService.enviarEmail(user.getLogin(), conteudo);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} ) ;
	}
}