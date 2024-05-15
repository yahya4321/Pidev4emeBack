package com.example.pi_dev_4eme__poker_planning.Api;

import com.example.pi_dev_4eme__poker_planning.Api.MailStructure;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


@Service
public class SendInvitionMail {


    @Autowired
    private JavaMailSender mailSender;


    @Value("$(kdidifiras30@gmail.com)")
    private String fromMail;
    public void sendEmail(String mail, MailStructure mailStructure){
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setFrom(fromMail);
            helper.setTo(mail);
            helper.setSubject(mailStructure.getSubject());

            // Créer le contenu HTML
            String emailContent = "<html><body><h1 style='color:red;'>" + mailStructure.getMessage() + "</h1>" +
                    "<p style='color:blue;'><h2>Voici votre lien de session : <h2><a href=\"" + mailStructure.getLinkSession() + "\">" +
                    "<button style='background-color:green; border:none; color:white; padding:15px 32px; text-align:center; text-decoration:none; display:inline-block; font-size:16px;'>Rejoindre la session</button></a></p></body></html>";

            helper.setText(emailContent, true);

            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}

