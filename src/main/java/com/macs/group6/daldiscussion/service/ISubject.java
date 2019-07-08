package com.macs.group6.daldiscussion.service;

public interface ISubject {
    public void attach(IObserver observer);
    public void detach(IObserver observer);
    public void notifyObserver();
}
