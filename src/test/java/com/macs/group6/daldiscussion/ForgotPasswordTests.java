package com.macs.group6.daldiscussion;

import com.macs.group6.daldiscussion.dao.impl.UserDAO;
import com.macs.group6.daldiscussion.entities.User;
import com.macs.group6.daldiscussion.model.*;
import com.macs.group6.daldiscussion.service.impl.ResetPasswordService;
import com.macs.group6.daldiscussion.service.impl.SendEmailService;
import com.macs.group6.daldiscussion.service.impl.SendForgotPasswordEmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ForgotPasswordTests {
    private static final String TO_EMAIL = "support@geetopod.com";
    private static final String TO_NAME = "geetoPod Support";

    private static final String FIRST_NAME = "geetoPod";
    private static final String TOKEN = "geetoPod";
    private static final String CHANGE_PASSWORD_LINK = "http://cs.dal.ca/change-password";
    private static final String USERNAME = "geetopod";
    private static final String USERCODE = "geetopod";
    private static final String EMAIL = "support@geetopod.com";

    private static final String SUBJECT_TEMPLATE = "$firstName, you requested to change password!";
    private static final String SUBJECT_TARGET = "geetoPod, you requested to change password!";

    private static final String TEXT_TEMPLATE = "Dear $firstName\n\nYou requested to change password. Please follow this link: $changePasswordLink?token=$token .\n\nBest regards\nSupport Team";
    private static final String TEXT_TARGET = "Dear geetoPod\n\nYou requested to change password. Please follow this link: http://cs.dal.ca/change-password?token=geetoPod .\n\nBest regards\nSupport Team";

    private static final String HTML_TEMPLATE = "<p>Dear $firstName</p><p></p><p>You requested to change password. Please follow <a target=\"blank\" href=\"$changePasswordLink?token=$token\">this link</a></p><p></p><p>Best regards</p><p>Support Team</p>";
    private static final String HTML_TARGET = "<p>Dear geetoPod</p><p></p><p>You requested to change password. Please follow <a target=\"blank\" href=\"http://cs.dal.ca/change-password?token=geetoPod\">this link</a></p><p></p><p>Best regards</p><p>Support Team</p>";

    @Test
    public void createUser_Success() {
        String username = "Smit";
        String password = "Smit";
        String email = "smitsaraiya10@gmail.com";
        String firstName = "Smit";
        String lastName = "Smit";
        String middleName = "";
        try {
            List<User> userList = UserDAO.instance().findByUsername(username);
            if (userList.size() == 0) {
                User user = new User();
                user.code = UUID.randomUUID().toString().replaceAll("-", "");
                user.username = username;
                user.password = password;
                user.email = email;
                user.firstName = firstName;
                user.lastName = lastName;
                user.middleName = middleName;
                UserDAO.instance().save(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void sendEmail_Success() {
        AppConfig appConfig = AppConfig.instance();

        SendEmailRequest request = new SendEmailRequest();

        request.fromEmail = appConfig.smtpFromEmail;
        request.fromName = appConfig.smtpFromName;
        request.smtpHost = appConfig.smtpHost;
        request.smtpPort = appConfig.smtpPort;
        request.smtpUsername = appConfig.smtpUsername;
        request.smtpPassword = appConfig.smtpPassword;
        request.toEmail = TO_EMAIL;
        request.toName = TO_NAME;
        request.subjectTemplate = SUBJECT_TEMPLATE;
        request.bodyTextTemplate = TEXT_TEMPLATE;
        request.bodyHtmlTemplate = HTML_TEMPLATE;

        request.templateVariables.put("token", TOKEN);
        request.templateVariables.put("firstName", FIRST_NAME);
        request.templateVariables.put("changePasswordLink", CHANGE_PASSWORD_LINK);

        SendEmailResponse response = SendEmailService.instance().run(request);

        assertThat(response.isError).isEqualTo(false);
        assertThat(response.sentSubject).isEqualTo(SUBJECT_TARGET);
        assertThat(response.sentBodyText).isEqualTo(TEXT_TARGET);
        assertThat(response.sentBodyHtml).isEqualTo(HTML_TARGET);
        assertThat(response.sentEmail).isEqualTo(TO_EMAIL);
        assertThat(response.sentName).isEqualTo(TO_NAME);
    }

    @Test
    public void forgotPassword_Success() {
        SendForgotPasswordEmailRequest sendForgotPasswordEmailRequest = new SendForgotPasswordEmailRequest();
        sendForgotPasswordEmailRequest.email = EMAIL;
        SendForgotPasswordEmailResponse sendForgotPasswordEmailResponse = SendForgotPasswordEmailService.instance().run(sendForgotPasswordEmailRequest);
        assertThat(sendForgotPasswordEmailResponse.isError).isEqualTo(false);

        boolean found = false;
        String token = "";
        for (int i = 0; i < sendForgotPasswordEmailResponse.usercodeList.size(); i++) {
            if (USERCODE.equals(sendForgotPasswordEmailResponse.usercodeList.get(i))) {
                found = true;
                token = sendForgotPasswordEmailResponse.tokenList.get(i);
                break;
            }
        }

        assertThat(found).isEqualTo(true);

        ResetPasswordRequest resetPasswordRequest = new ResetPasswordRequest();
        resetPasswordRequest.password = USERNAME;
        resetPasswordRequest.passwordRetype = USERNAME;
        resetPasswordRequest.token = token;
        ResetPasswordResponse resetPasswordResponse = ResetPasswordService.instance().run(resetPasswordRequest);

        assertThat(resetPasswordResponse.isError).isEqualTo(false);
    }
}
