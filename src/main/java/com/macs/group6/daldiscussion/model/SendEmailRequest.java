package com.macs.group6.daldiscussion.model;

import java.util.HashMap;
import java.util.Map;

public class SendEmailRequest {
    private String _toEmail = "";
    private String _toName = "";
    private String _fromEmail = "";
    private String _fromName = "";
    private String _smtpHost = "";
    private int _smtpPort = 0;
    private String _smtpUsername = "";
    private String _smtpPassword = "";
    private String _subjectTemplate = "";
    private String _bodyTextTemplate = "";
    private String _bodyHtmlTemplate = "";
    private Map _templateVariables = new HashMap();

    public Map getTemplateVariables() {
        return _templateVariables;
    }

    public void setTemplateVariables(Map templateVariables) {
        _templateVariables = templateVariables;
    }

    public String getBodyHtmlTemplate() {
        return _bodyHtmlTemplate;
    }

    public void setBodyHtmlTemplate(String bodyHtmlTemplate) {
        _bodyHtmlTemplate = bodyHtmlTemplate;
    }

    public String getBodyTextTemplate() {
        return _bodyTextTemplate;
    }

    public void setBodyTextTemplate(String bodyTextTemplate) {
        _bodyTextTemplate = bodyTextTemplate;
    }

    public String getSubjectTemplate() {
        return _subjectTemplate;
    }

    public void setSubjectTemplate(String subjectTemplate) {
        _subjectTemplate = subjectTemplate;
    }

    public String getSmtpPassword() {
        return _smtpPassword;
    }

    public void setSmtpPassword(String smtpPassword) {
        _smtpPassword = smtpPassword;
    }

    public String getSmtpUsername() {
        return _smtpUsername;
    }

    public void setSmtpUsername(String smtpUsername) {
        _smtpUsername = smtpUsername;
    }

    public int getSmtpPort() {
        return _smtpPort;
    }

    public void setSmtpPort(int smtpPort) {
        _smtpPort = smtpPort;
    }

    public String getSmtpHost() {
        return _smtpHost;
    }

    public void setSmtpHost(String smtpHost) {
        _smtpHost = smtpHost;
    }

    public String getFromName() {
        return _fromName;
    }

    public void setFromName(String fromName) {
        _fromName = fromName;
    }

    public String getFromEmail() {
        return _fromEmail;
    }

    public void setFromEmail(String fromEmail) {
        _fromEmail = fromEmail;
    }

    public String getToName() {
        return _toName;
    }

    public void setToName(String toName) {
        _toName = toName;
    }

    public String getToEmail() {
        return _toEmail;
    }

    public void setToEmail(String toEmail) {
        _toEmail = toEmail;
    }
}
