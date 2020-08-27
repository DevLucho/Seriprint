/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Entidades.Usuario;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.security.NoSuchProviderException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Huertas
 */
@Named(value = "correoControlador")
@SessionScoped
public class CorreoControlador implements Serializable {

    /**
     * Creates a new instance of CorreoControlador
     */
    
    Usuario usuario;
    
    public CorreoControlador() {
        usuario = new Usuario();
    }
    
    // notificar via email
    public void enviarEmail(String destinatario, String asunto, String descripcion) throws NoSuchProviderException, MessagingException {
        try {
            // propiedades  
            Properties propiedad = new Properties();
            propiedad.setProperty("mail.smtp.host", "smtp.gmail.com");
            propiedad.setProperty("mail.smtp.starttls.enable", "true");
            propiedad.setProperty("mail.smtp.port", "587");
            propiedad.setProperty("mail.smtp.auth", "true");
            String correoEnvia = "noresponder.conjuntoweb@gmail.com";
            String contrasena = "conjunto123";

            Session sesion = Session.getDefaultInstance(propiedad);

            // envio email
            MimeMessage mail = new MimeMessage(sesion);

            try {
                mail.setFrom(new InternetAddress(correoEnvia));
                mail.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
                mail.setSubject(asunto);
                mail.setContent(descripcion, "text/html");

                Transport transporte = sesion.getTransport("smtp");
                transporte.connect(correoEnvia, contrasena);
                transporte.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));
                transporte.close();

            } catch (AddressException ex) {
                Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);

            } catch (MessagingException ex) {
                Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (Exception e) {
            System.out.println("Error en envio email revisar: " + e.getMessage());
        }
    }
    
}
