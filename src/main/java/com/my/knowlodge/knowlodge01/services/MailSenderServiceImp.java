package com.my.knowlodge.knowlodge01.services;

import com.my.knowlodge.knowlodge01.models.dto.EmailModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailSenderServiceImp implements MailSenderService {
    private final JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String emailFrom;

    @Override
    public void registerConfirmation(EmailModel model) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(emailFrom);
            mailMessage.setTo(model.to());
            mailMessage.setSubject(model.subject());
            mailMessage.setText(model.message());
            this.javaMailSender.send(mailMessage);
        } catch (Exception e) {
            log.error("The mail can't be sent", e);
        }
    }
}
