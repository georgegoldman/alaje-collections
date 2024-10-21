package com.hydrogenhr.service;

public interface EmailService {

    void sendValidationEmail(String to, String subject, String body);

}
