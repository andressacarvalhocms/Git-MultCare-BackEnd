package br.edu.ufersa.multcare.service.impl;

import br.edu.ufersa.multcare.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class EmailServiceImpl implements EmailService {


    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void enviarEmail(String destinatario, String conteudo) throws MessagingException {


        MimeMessage mail = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper( mail );
        helper.setTo(destinatario);
        helper.setSubject( "Multcare" );
        helper.setText(conteudo, true);
        helper.setFrom("andressamelocms@gmail.com");
        mailSender.send(mail);
        System.out.println("Email enviado!");
    }
}
