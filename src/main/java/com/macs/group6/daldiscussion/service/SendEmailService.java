package com.macs.group6.daldiscussion.service;

import com.macs.group6.daldiscussion.model.SendEmailRequest;
import com.macs.group6.daldiscussion.model.SendEmailResponse;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

public class SendEmailService {
    private static SendEmailService __instance;

    public static SendEmailService instance() {
        if (__instance == null) {
            __instance = new SendEmailService();
        }
        return __instance;
    }

    public SendEmailResponse run(SendEmailRequest request) {
        SendEmailResponse response = new SendEmailResponse();
        try {
            String subject = merge(request.subjectTemplate, request.templateVariables);
            String text = merge(request.bodyTextTemplate, request.templateVariables);
            String html = merge(request.bodyHtmlTemplate, request.templateVariables);

            System.out.println("=== Email ===");
            System.out.println("From: " + request.fromName + " <" + request.fromEmail + ">");
            System.out.println("To: " + request.toName + " <" + request.toEmail + ">");
            System.out.println("Subject: " + subject);
            System.out.println("Text: " + text);
            System.out.println("Html: " + html);

            Properties props = new Properties();
            props.put("mail.smtp.host", request.smtpHost);
            props.put("mail.smtp.port", request.smtpPort);
            props.put("mail.smtp.auth", "true"); //enable authentication
            props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

            //create Authenticator object to pass in Session.getInstance argument
            final String smtpUsernameF = request.smtpUsername;
            final String smtpPasswordF = request.smtpPassword;
            Authenticator auth = new Authenticator() {
                //override the getPasswordAuthentication method
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(smtpUsernameF, smtpPasswordF);
                }
            };
            Session session = Session.getInstance(props, auth);

            MimeMessage msg = new MimeMessage(session);
            //set message headers
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress(request.fromEmail, request.fromName));
            msg.setReplyTo(InternetAddress.parse(request.fromEmail, false));

            msg.setSubject(subject, "UTF-8");
            msg.setText(text, "UTF-8");
            msg.setContent(html, "text/html; charset=utf-8");
            msg.setSentDate(new Date());
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(request.toEmail, false));
            Transport.send(msg);
            System.out.println("Message is ready");

            response.sentEmail = request.toEmail;
            response.sentName = request.toName;
            response.sentSubject = subject;
            response.sentBodyText = text;
            response.sentBodyHtml = html;
        } catch (Throwable t) {
            response.setError("SendEmail.Failed", t);
        }
        return response;
    }

    private String merge(String template, Map args) throws Exception {
        VelocityEngine engine = new VelocityEngine();
        engine.init();
        VelocityContext ctx = new VelocityContext();
        for (Object keyO : args.keySet()) {
            ctx.put(keyO + "", args.get(keyO));
        }
        Writer writer = new StringWriter();
        engine.evaluate(ctx, writer, "", template);
        return writer.toString();
    }
}
