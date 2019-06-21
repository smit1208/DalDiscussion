package com.macs.group6.daldiscussion.model;

import java.util.HashMap;
import java.util.Map;

public class SendEmailRequest {
    public String toEmail = "";
    public String toName = "";
    public String fromEmail = "";
    public String fromName = "";
    public String smtpHost = "";
    public int smtpPort = 0;
    public String smtpUsername = "";
    public String smtpPassword = "";
    public String subjectTemplate = "";
    public String bodyTextTemplate = "";
    public String bodyHtmlTemplate = "";
    public Map templateVariables = new HashMap();
}
