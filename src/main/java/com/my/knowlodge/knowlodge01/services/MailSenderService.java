package com.my.knowlodge.knowlodge01.services;

import com.my.knowlodge.knowlodge01.models.infra.MailModel;

public interface MailSenderService {
    void registerConfirmation(MailModel model);
}
