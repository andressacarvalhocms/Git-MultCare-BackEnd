package br.edu.ufersa.multcare.service;

import javax.mail.MessagingException;

public interface EmailService {

  void enviarEmail(String destinatario, String conteudo) throws MessagingException;

  void enviarEmailAnexado() throws MessagingException;
}
