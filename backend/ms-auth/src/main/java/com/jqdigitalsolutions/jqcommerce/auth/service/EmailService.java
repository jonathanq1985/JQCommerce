package com.jqdigitalsolutions.jqcommerce.auth.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

// Ing_JQC: Servicio de envío de correos
@Service
public class EmailService {

   // private final JavaMailSender mailSender;

   /* public EmailService(JavaMailSender mailSender) {

        this.mailSender = mailSender;

    }*/

    // Ing_JQC: Envía correo electrónico
    public void enviarCorreo(String destino,
                             String asunto,
                             String contenido) {

        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo(destino);
        mensaje.setSubject(asunto);
        mensaje.setText(contenido);
     //   mailSender.send(mensaje);

    }

}