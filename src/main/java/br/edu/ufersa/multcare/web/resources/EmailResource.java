package br.edu.ufersa.multcare.web.resources;

import static br.edu.ufersa.multcare.security.SecurityUtils.obterIdUsuarioAutenticado;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufersa.multcare.persistence.entities.Usuario;
import br.edu.ufersa.multcare.service.UsuarioService;

@RestController
@RequestMapping(value="/api")
public class EmailResource {
	 private UsuarioService usuarioService;


    @Autowired
    private JavaMailSender emailSender;
    
    @RequestMapping(value = "/email", method = RequestMethod.POST)
    public void enviarEmailAnexado(String destinatario, String conteudo, Integer idArquivo) throws MessagingException {

        Usuario usuario = usuarioService.obterUsuarioPorId(obterIdUsuarioAutenticado());

    	conteudo = "Segue em anexo o arquivo CDA do paciente: " + usuario.getNome();
        idArquivo = obterIdUsuarioAutenticado();
        
        destinatario = usuario.getEmailMedico();
        
        System.out.print(obterIdUsuarioAutenticado());    	
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
