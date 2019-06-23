package com.macs.group6.daldiscussion.model;

import java.util.ArrayList;
import java.util.List;

public class SendForgotPasswordEmailResponse extends BasicResponse {
    private List<String> _sentEmailList = new ArrayList<>();
    private List<String> _sentNameList = new ArrayList<>();
    private List<String> _sentSubjectList = new ArrayList<>();
    private List<String> _sentBodyTextList = new ArrayList<>();
    private List<String> _sentBodyHtmlList = new ArrayList<>();
    private List<String> _tokenList = new ArrayList<>();
    private List<Integer> _userIdList = new ArrayList<>();

    public List<Integer> getUserIdList() {
        return _userIdList;
    }

    public void setUserIdList(List<Integer> userIdList) {
        _userIdList = userIdList;
    }

    public List<String> getTokenList() {
        return _tokenList;
    }

    public void setTokenList(List<String> tokenList) {
        _tokenList = tokenList;
    }

    public List<String> getSentBodyHtmlList() {
        return _sentBodyHtmlList;
    }

    public void setSentBodyHtmlList(List<String> sentBodyHtmlList) {
        _sentBodyHtmlList = sentBodyHtmlList;
    }

    public List<String> getSentBodyTextList() {
        return _sentBodyTextList;
    }

    public void setSentBodyTextList(List<String> sentBodyTextList) {
        _sentBodyTextList = sentBodyTextList;
    }

    public List<String> getSentSubjectList() {
        return _sentSubjectList;
    }

    public void setSentSubjectList(List<String> sentSubjectList) {
        _sentSubjectList = sentSubjectList;
    }

    public List<String> getSentNameList() {
        return _sentNameList;
    }

    public void setSentNameList(List<String> sentNameList) {
        _sentNameList = sentNameList;
    }

    public List<String> getSentEmailList() {
        return _sentEmailList;
    }

    public void setSentEmailList(List<String> sentEmailList) {
        _sentEmailList = sentEmailList;
    }
}
