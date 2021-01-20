package br.edu.ufersa.multcare.service.impl;

import static br.edu.ufersa.multcare.security.SecurityUtils.obterIdUsuarioAutenticado;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufersa.multcare.persistence.entities.Usuario;
import br.edu.ufersa.multcare.service.EmailService;
import br.edu.ufersa.multcare.service.UsuarioService;

@RestController
@RequestMapping(value="/api")
@Service
public class EmailServiceImpl implements EmailService {
	@Autowired
	private UsuarioService usuarioService;
	  

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

    @RequestMapping(value = "/enviar/email", method = RequestMethod.POST)   
    public void enviarEmailAnexado() throws MessagingException {
    	 MimeMessage message = emailSender.createMimeMessage();
         MimeMessageHelper helper = new MimeMessageHelper(message, true);
         
         Usuario usuario = usuarioService.obterUsuarioPorId(obterIdUsuarioAutenticado());
         System.out.print("teste");
         
     	String resultado = "Segue em anexo o arquivo CDA do paciente: " + usuario.getNome();
     	Integer idArquivo = obterIdUsuarioAutenticado();
         
         helper.setTo(usuario.getEmailMedico());
         helper.setSubject( "Multcare" );
         helper.setText(resultado, true);
         helper.setFrom("andressamelocms@gmail.com");
         System.out.print(idArquivo);
         FileSystemResource file = new FileSystemResource("/XML/cda"+idArquivo+".xml");
         System.out.print(file);
 		 helper.addAttachment(file.getFilename(), file);
         emailSender.send(message);
        System.out.println("Email enviado!");
    }
    
}
