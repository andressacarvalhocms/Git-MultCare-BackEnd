package br.edu.ufersa.multcare.web.resources;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailResource {
/*

    @Autowired private JavaMailSender mailSender;

	@Scheduled(fixedDelay = 250000)
	   public String executar() {
		
	       System.out.println("Executou o Scheduled com delay");
//    public String sendMail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText("Hora do rémedio x:");
        message.setTo("acmsq@ic.ufal.br");
        message.setFrom("andressamelocms@gmail.com");

        try {
            mailSender.send(message);
            return "Email enviado com sucesso!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao enviar email.";
        }
	   
    }  
    
    *
    *
    *
    *
    *		
	/*	if (!medicamentos.isEmpty()) {
			for (int i = 0; i < medicamentos.size(); i++) {

				Usuario user = ((Medicamento) medicamentos).getUsuario();
				SimpleMailMessage message = new SimpleMailMessage();
		        message.setText("Hora do rémedio x:");
		        message.setTo(user.getLogin());
		        message.setFrom("andressamelocms@gmail.com");
		        mailSender.send(message);
		        System.out.println("Email enviado!");
			}  
		}else {

			   System.out.println("Nenhum medicamento nesse horário!");
		} */
    
}