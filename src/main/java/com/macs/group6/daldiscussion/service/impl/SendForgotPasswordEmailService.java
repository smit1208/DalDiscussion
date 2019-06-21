package com.macs.group6.daldiscussion.service.impl;

import com.macs.group6.daldiscussion.AppConfig;
import com.macs.group6.daldiscussion.dao.impl.UserDAO;
import com.macs.group6.daldiscussion.dao.impl.VerificationDAO;
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

    public static SendForgotPasswordEmailService instance() {
        if (__instance == null) {
            __instance = new SendForgotPasswordEmailService();
        }
        return __instance;
    }

    public SendForgotPasswordEmailResponse run(SendForgotPasswordEmailRequest request) {
        SendForgotPasswordEmailResponse response = new SendForgotPasswordEmailResponse();
        try {
            if (request.email.trim().length() == 0) {
                response.setError("SendForgotPassword.EmailRequired", "Email is required!");
                return response;
            }
            List<User> userList = UserDAO.instance().findByEmail(request.email);
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
                verification.code = UUID.randomUUID().toString().replaceAll("-", "");
                verification.verifiedToken = " ";
                verification.verifyToken = UUID.randomUUID().toString().replaceAll("-", "");
                verification.jsonData = "{}";
                verification.tokenCode = user.code;
                verification.tokenText = " ";
                verification.kind = Verification.KIND_FORGOT_PASSWORD;
                VerificationDAO.instance().save(verification);
                SendEmailRequest sendEmailRequest = new SendEmailRequest();
                sendEmailRequest.bodyHtmlTemplate = bodyHtmlTemplate;
                sendEmailRequest.bodyTextTemplate = bodyTextTemplate;
                sendEmailRequest.subjectTemplate = subjectTemplate;
                sendEmailRequest.templateVariables.put("token", verification.verifyToken);
                sendEmailRequest.templateVariables.put("changePasswordLink", AppConfig.instance().resetPasswordUrl);
                sendEmailRequest.templateVariables.put("usercode", user.code);
                sendEmailRequest.templateVariables.put("username", user.username);
                sendEmailRequest.templateVariables.put("firstName", user.firstName);
                sendEmailRequest.templateVariables.put("lastName", user.lastName);
                sendEmailRequest.templateVariables.put("middleName", user.middleName);
                sendEmailRequest.templateVariables.put("email", user.email);
                sendEmailRequest.toName = user.firstName + " " + user.middleName + (user.middleName.length() > 0 ? " " : "") + user.lastName;
                sendEmailRequest.toEmail = user.email;
                sendEmailRequest.smtpHost = AppConfig.instance().smtpHost;
                sendEmailRequest.smtpPort = AppConfig.instance().smtpPort;
                sendEmailRequest.smtpUsername = AppConfig.instance().smtpUsername;
                sendEmailRequest.smtpPassword = AppConfig.instance().smtpPassword;
                sendEmailRequest.fromName = AppConfig.instance().smtpFromName;
                sendEmailRequest.fromEmail = AppConfig.instance().smtpFromEmail;
                SendEmailResponse sendEmailResponse = SendEmailService.instance().run(sendEmailRequest);
                if (sendEmailResponse.isError) {
                    response.setError(sendEmailResponse.errorCode, sendEmailResponse.errorMessage);
                    return response;
                }
                response.tokenList.add(verification.verifyToken);
                response.usercodeList.add(user.code);
                response.sentBodyHtmlList.add(sendEmailResponse.sentBodyHtml);
                response.sentBodyTextList.add(sendEmailResponse.sentBodyText);
                response.sentSubjectList.add(sendEmailResponse.sentSubject);
                response.sentEmailList.add(sendEmailResponse.sentEmail);
                response.sentNameList.add(sendEmailResponse.sentName);
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
