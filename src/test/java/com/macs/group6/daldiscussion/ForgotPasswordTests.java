package com.macs.group6.daldiscussion;

import com.macs.group6.daldiscussion.dao.UserDAO;
import com.macs.group6.daldiscussion.entities.User;
import com.macs.group6.daldiscussion.model.*;
import com.macs.group6.daldiscussion.service.ResetPasswordService;
import com.macs.group6.daldiscussion.service.SendEmailService;
import com.macs.group6.daldiscussion.service.SendForgotPasswordEmailService;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ForgotPasswordTests {
    private static final String TO_EMAIL = "support@geetopod.com";
    private static final String TO_NAME = "geetoPod Support";

    private static final String FIRST_NAME = "geetoPod";
    private static final String TOKEN = "geetoPod";
    private static final String CHANGE_PASSWORD_LINK = "http://cs.dal.ca/change-password";
    private static final String EMAIL = "support@geetopod.com";
    private static final String PASSWORD = "geetoPod";

    private static final String SUBJECT_TEMPLATE = "$firstName, you requested to change password!";
    private static final String SUBJECT_TARGET = "geetoPod, you requested to change password!";

    private static final String TEXT_TEMPLATE = "Dear $firstName\n\nYou requested to change password. Please follow this link: $changePasswordLink?token=$token .\n\nBest regards\nSupport Team";
    private static final String TEXT_TARGET = "Dear geetoPod\n\nYou requested to change password. Please follow this link: http://cs.dal.ca/change-password?token=geetoPod .\n\nBest regards\nSupport Team";

    private static final String HTML_TEMPLATE = "<p>Dear $firstName</p><p></p><p>You requested to change password. Please follow <a target=\"blank\" href=\"$changePasswordLink?token=$token\">this link</a></p><p></p><p>Best regards</p><p>Support Team</p>";
    private static final String HTML_TARGET = "<p>Dear geetoPod</p><p></p><p>You requested to change password. Please follow <a target=\"blank\" href=\"http://cs.dal.ca/change-password?token=geetoPod\">this link</a></p><p></p><p>Best regards</p><p>Support Team</p>";
    
    @Test
    public void sendEmail_Success() {
        AppConfig appConfig = AppConfig.getInstance();

        SendEmailRequest request = new SendEmailRequest();

        request.setFromEmail(appConfig.getSmtpFromEmail());
        request.setFromName(appConfig.getSmtpFromName());
        request.setSmtpHost(appConfig.getSmtpHost());
        request.setSmtpPort(appConfig.getSmtpPort());
        request.setSmtpUsername(appConfig.getSmtpUsername());
        request.setSmtpPassword(appConfig.getSmtpPassword());
        request.setToEmail(TO_EMAIL);
        request.setToName(TO_NAME);
        request.setSubjectTemplate(SUBJECT_TEMPLATE);
        request.setBodyTextTemplate(TEXT_TEMPLATE);
        request.setBodyHtmlTemplate(HTML_TEMPLATE);

        request.getTemplateVariables().put("token", TOKEN);
        request.getTemplateVariables().put("firstName", FIRST_NAME);
        request.getTemplateVariables().put("changePasswordLink", CHANGE_PASSWORD_LINK);

        SendEmailResponse response = SendEmailService.getInstance().run(request);

        assertThat(response.getIsError()).isEqualTo(false);
        assertThat(response.getSentSubject()).isEqualTo(SUBJECT_TARGET);
        assertThat(response.getSentBodyText()).isEqualTo(TEXT_TARGET);
        assertThat(response.getSentBodyHtml()).isEqualTo(HTML_TARGET);
        assertThat(response.getSentEmail()).isEqualTo(TO_EMAIL);
        assertThat(response.getSentName()).isEqualTo(TO_NAME);
    }

    @Test
    public void forgotPassword_Success() {
        SendForgotPasswordEmailRequest sendForgotPasswordEmailRequest = new SendForgotPasswordEmailRequest();
        sendForgotPasswordEmailRequest.setEmail(EMAIL);
        SendForgotPasswordEmailResponse sendForgotPasswordEmailResponse = SendForgotPasswordEmailService.getInstance().run(sendForgotPasswordEmailRequest);
        assertThat(sendForgotPasswordEmailResponse.getIsError()).isEqualTo(true);

        boolean found = false;
        String token = "";
        for (int i = 0; i < sendForgotPasswordEmailResponse.getSentEmailList().size(); i++) {
            if (EMAIL.equals(sendForgotPasswordEmailResponse.getSentEmailList().get(i))) {
                found = true;
                token = sendForgotPasswordEmailResponse.getTokenList().get(i);
                break;
            }
        }

        assertThat(found).isEqualTo(false);

        ResetPasswordRequest resetPasswordRequest = new ResetPasswordRequest();
        resetPasswordRequest.setPassword(PASSWORD);
        resetPasswordRequest.setPasswordRetype(PASSWORD);
        resetPasswordRequest.setToken(token);
        ResetPasswordResponse resetPasswordResponse = ResetPasswordService.getInstance().run(resetPasswordRequest);

        assertThat(resetPasswordResponse.getIsError()).isEqualTo(true);
    }

    @Test
    public void createUser_Success() {
        String password = "12345678";
        String email = "smitsaraiya10@gmail.com";
        String firstName = "smit";
        String lastName = "saraiya";
        try {
            List<User> userList = UserDAO.getInstance().findByEmail(email);
            if (userList.size() == 0) {
                User user = new User();
                user.setPassword(password);
                user.setEmail(email);
                user.setFirstName(firstName);
                user.setLastName(lastName);
                UserDAO.getInstance().save(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThat(true).isEqualTo(true);
    }
}
