package com.macs.group6.daldiscussion.model;

/**
 * 
 *
 * @author Kush Rao
 */
public class SendEmailResponse extends BasicResponse {
    private String _sentEmail = "";
    private String _sentName = "";
    private String _sentSubject = "";
    private String _sentBodyText = "";
    private String _sentBodyHtml = "";

    public String getSentBodyHtml() {
        return _sentBodyHtml;
    }

    public void setSentBodyHtml(String sentBodyHtml) {
        _sentBodyHtml = sentBodyHtml;
    }

    public String getSentBodyText() {
        return _sentBodyText;
    }

    public void setSentBodyText(String sentBodyText) {
        _sentBodyText = sentBodyText;
    }

    public String getSentSubject() {
        return _sentSubject;
    }

    public void setSentSubject(String sentSubject) {
        _sentSubject = sentSubject;
    }

    public String getSentName() {
        return _sentName;
    }

    public void setSentName(String sentName) {
        _sentName = sentName;
    }

    public String getSentEmail() {
        return _sentEmail;
    }

    public void setSentEmail(String sentEmail) {
        _sentEmail = sentEmail;
    }
}
