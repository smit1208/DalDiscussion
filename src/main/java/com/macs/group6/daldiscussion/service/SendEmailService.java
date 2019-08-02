package com.macs.group6.daldiscussion.service;
/**
 * 
 *
 * @author Kush Rao
 */
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

    public static SendEmailService getInstance() {
        if (__instance == null) {
            __instance = new SendEmailService();
        }
        return __instance;
    }

    public SendEmailResponse run(SendEmailRequest request) {
        SendEmailResponse response = new SendEmailResponse();
        try {
            String subject = merge(request.getSubjectTemplate(), request.getTemplateVariables());
            String text = merge(request.getBodyTextTemplate(), request.getTemplateVariables());
            String html = merge(request.getBodyHtmlTemplate(), request.getTemplateVariables());

            System.out.println("=== Email ===");
            System.out.println("From: " + request.getFromName() + " <" + request.getFromEmail() + ">");
            System.out.println("To: " + request.getToName() + " <" + request.getToEmail() + ">");
            System.out.println("Subject: " + subject);
            System.out.println("Text: " + text);
            System.out.println("Html: " + html);

            Properties props = new Properties();
            props.put("mail.smtp.host", request.getSmtpHost());
            props.put("mail.smtp.port", request.getSmtpPort());
            props.put("mail.smtp.auth", "true"); //enable authentication
            props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

            //createPost Authenticator object to pass in Session.getInstance argument
            final String smtpUsernameF = request.getSmtpUsername();
            final String smtpPasswordF = request.getSmtpPassword();
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

            msg.setFrom(new InternetAddress(request.getFromEmail(), request.getFromName()));
            msg.setReplyTo(InternetAddress.parse(request.getFromEmail(), false));

            msg.setSubject(subject, "UTF-8");
            msg.setText(text, "UTF-8");
            msg.setContent(html, "text/html; charset=utf-8");
            msg.setSentDate(new Date());
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(request.getToEmail(), false));
            Transport.send(msg);
            System.out.println("Message is ready");

            response.setSentEmail(request.getToEmail());
            response.setSentName(request.getToName());
            response.setSentSubject(subject);
            response.setSentBodyText(text);
            response.setSentBodyHtml(html);
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
