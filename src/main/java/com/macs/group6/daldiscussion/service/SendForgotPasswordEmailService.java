package com.macs.group6.daldiscussion.service;

import com.macs.group6.daldiscussion.AppConfig;
import com.macs.group6.daldiscussion.dao.UserDAO;
import com.macs.group6.daldiscussion.dao.VerificationDAO;
import com.macs.group6.daldiscussion.entities.User;
import com.macs.group6.daldiscussion.entities.Verification;
import com.macs.group6.daldiscussion.model.SendEmailRequest;
import com.macs.group6.daldiscussion.model.SendEmailResponse;
import com.macs.group6.daldiscussion.model.SendForgotPasswordEmailRequest;
import com.macs.group6.daldiscussion.model.SendForgotPasswordEmailResponse;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

public class SendForgotPasswordEmailService {
    private static final String RESX_SUBJECT_TPL = "/emails/forgot-password/subject.tpl";
    private static final String RESX_TEXT_TPL = "/emails/forgot-password/text.tpl";
    private static final String RESX_HTML_TPL = "/emails/forgot-password/html.tpl";

    private static SendForgotPasswordEmailService __instance;

    public static SendForgotPasswordEmailService getInstance() {
        if (__instance == null) {
            __instance = new SendForgotPasswordEmailService();
        }
        return __instance;
    }

    public SendForgotPasswordEmailResponse run(SendForgotPasswordEmailRequest request) {
        SendForgotPasswordEmailResponse response = new SendForgotPasswordEmailResponse();
        try {
            if (request.getEmail().trim().length() == 0) {
                response.setError("SendForgotPassword.EmailRequired", "Email is required!");
                return response;
            }
            List<User> userList = UserDAO.getInstance().findByEmail(request.getEmail());
            if (userList.size() == 0) {
                response.setError("SendForgotPassword.EmailNotFound", "Email is not found!");
                return response;
            }
            String subjectTemplate = readResource(RESX_SUBJECT_TPL);
            String bodyTextTemplate = readResource(RESX_TEXT_TPL);
            String bodyHtmlTemplate = readResource(RESX_HTML_TPL);
            for (int i = 0; i < userList.size(); i++) {
                User user = userList.get(i);
                Verification verification = new Verification();
                verification.setCode(UUID.randomUUID().toString().replaceAll("-", ""));
                verification.setVerifiedToken(" ");
                verification.setVerifyToken(UUID.randomUUID().toString().replaceAll("-", ""));
                verification.setJsonData("{}");
                verification.setTokenCode(user.getId() + "");
                verification.setTokenText(" ");
                verification.setKind(Verification.KIND_FORGOT_PASSWORD);
                VerificationDAO.getInstance().save(verification);
                SendEmailRequest sendEmailRequest = new SendEmailRequest();
                sendEmailRequest.setBodyHtmlTemplate(bodyHtmlTemplate);
                sendEmailRequest.setBodyTextTemplate(bodyTextTemplate);
                sendEmailRequest.setSubjectTemplate(subjectTemplate);
                sendEmailRequest.getTemplateVariables().put("token", verification.getVerifyToken());
                sendEmailRequest.getTemplateVariables().put("changePasswordLink", AppConfig.getInstance().getResetPasswordUrl());
                sendEmailRequest.getTemplateVariables().put("userid", user.getId());
                sendEmailRequest.getTemplateVariables().put("firstName", user.getFirstName());
                sendEmailRequest.getTemplateVariables().put("lastName", user.getLastName());
                sendEmailRequest.getTemplateVariables().put("email", user.getEmail());
                sendEmailRequest.setToName(user.getFirstName() + " " + user.getLastName());
                sendEmailRequest.setToEmail(user.getEmail());
                sendEmailRequest.setSmtpHost(AppConfig.getInstance().getSmtpHost());
                sendEmailRequest.setSmtpPort(AppConfig.getInstance().getSmtpPort());
                sendEmailRequest.setSmtpUsername(AppConfig.getInstance().getSmtpUsername());
                sendEmailRequest.setSmtpPassword(AppConfig.getInstance().getSmtpPassword());
                sendEmailRequest.setFromName(AppConfig.getInstance().getSmtpFromName());
                sendEmailRequest.setFromEmail(AppConfig.getInstance().getSmtpFromEmail());
                SendEmailResponse sendEmailResponse = SendEmailService.getInstance().run(sendEmailRequest);
                if (sendEmailResponse.getIsError()) {
                    response.setError(sendEmailResponse.getErrorCode(), sendEmailResponse.getErrorMessage());
                    return response;
                }
                response.getTokenList().add(verification.getVerifyToken());
                response.getUserIdList().add(user.getId());
                response.getSentBodyHtmlList().add(sendEmailResponse.getSentBodyHtml());
                response.getSentBodyTextList().add(sendEmailResponse.getSentBodyText());
                response.getSentSubjectList().add(sendEmailResponse.getSentSubject());
                response.getSentEmailList().add(sendEmailResponse.getSentEmail());
                response.getSentNameList().add(sendEmailResponse.getSentName());
            }
        } catch (Exception e) {
            response.setError("SendForgotPassword.Failed", e);
        }
        return response;
    }

    private String readResource(String resourcePath) throws Exception {
        String target;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        InputStream inputStream = SendForgotPasswordEmailService.class.getResourceAsStream(resourcePath);
        byte[] buffer = new byte[1024];
        int read = inputStream.read(buffer, 0, buffer.length);
        while (read > 0) {
            outputStream.write(buffer, 0, read);
            read = inputStream.read(buffer, 0, buffer.length);
        }
        inputStream.close();
        target = outputStream.toString();
        outputStream.close();

        return target;
    }
}
