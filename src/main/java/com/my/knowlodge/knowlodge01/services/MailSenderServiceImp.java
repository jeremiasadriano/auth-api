package com.my.knowlodge.knowlodge01.services;

import com.my.knowlodge.knowlodge01.models.MailModel;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Objects;

@Slf4j
@Service
@EnableAsync
@RequiredArgsConstructor
public class MailSenderServiceImp implements MailSenderService {
    private final JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String sender;

    @Async
    @Override
    public void registerConfirmation(MailModel model) {
        try {
            log.info("Sending email to the user");
            MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
            MimeMessageHelper mailSender = new MimeMessageHelper(mimeMessage, null);
            mailSender.setFrom(sender);
            mailSender.setTo(model.to());
            mailSender.setSubject(model.subject());
            mailSender.setText(model.message());
            log.info("Here Nowww!");
            if (!model.attachment().trim().isEmpty()) {
                FileSystemResource resource = new FileSystemResource(new File(model.attachment()));
                mailSender.addAttachment(Objects.requireNonNull(resource.getFilename()), resource);
                log.info("In the attachment");
            }
            log.info("Sending Now");
            this.javaMailSender.send(mimeMessage);
            log.info("Email sent successful");
        } catch (Exception e) {
            log.error("The mail can't be sent", e);
        }
    }
}
