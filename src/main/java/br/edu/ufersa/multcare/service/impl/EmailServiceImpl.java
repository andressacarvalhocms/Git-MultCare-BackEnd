package br.edu.ufersa.multcare.service.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import br.edu.ufersa.multcare.service.EmailService;

//@Component
@Service
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
    


    @Autowired
    private JavaMailSender emailSender;
    @Override
    public void enviarEmailAnexado(String destinatario, String conteudo, Integer idArquivo) throws MessagingException {
    	 MimeMessage message = emailSender.createMimeMessage();
         MimeMessageHelper helper = new MimeMessageHelper(message, true);

         helper.setTo(destinatario);
         helper.setSubject( "Multcare" );
         helper.setText(conteudo, true);
         helper.setFrom("andressamelocms@gmail.com");
         FileSystemResource file = new FileSystemResource("D:\\Mestrado\\DISSERTACAO GITHUB MULTCARE\\Git-MultCare-BackEnd\\XML\\cda"+idArquivo+".xml");
 		 helper.addAttachment(file.getFilename(), file);
         emailSender.send(message);
        System.out.println("Email enviado!");
    }
    
}
