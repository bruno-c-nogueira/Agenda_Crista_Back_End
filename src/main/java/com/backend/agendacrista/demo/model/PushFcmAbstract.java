package com.backend.agendacrista.demo.model;


public abstract class PushFcmAbstract {

    private PushFCMNotification notification;

    public PushFcmAbstract(PushFCMNotification notification) {
        this.notification = notification;
    }

    public PushFCMNotification getNotification() {
        return notification;
    }

    public void setNotification(PushFCMNotification notification) {
        this.notification = notification;
    }

    public abstract String getDestination();

    public abstract boolean isEmptyDestination();
}
