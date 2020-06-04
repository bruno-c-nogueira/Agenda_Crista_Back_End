package com.backend.agendacrista.demo.model;


public class PushFCM {

    private String to;
    private PushFCMNotification notification;

    public PushFCM(String to, PushFCMNotification notification) {
        this.to = to;
        this.notification = notification;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public PushFCMNotification getNotification() {
        return notification;
    }

    public void setNotification(PushFCMNotification notification) {
        this.notification = notification;
    }

}
