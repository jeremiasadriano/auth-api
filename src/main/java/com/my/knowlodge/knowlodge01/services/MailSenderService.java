package com.my.knowlodge.knowlodge01.services;

import com.my.knowlodge.knowlodge01.models.dto.EmailModel;

public interface MailSenderService {
    void registerConfirmation(EmailModel model);
}
