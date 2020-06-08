package com.backend.agendacrista.demo.model;


public abstract class PushFcmAbstract {

    private PushFCMNotification notification;
    private Object data;

    public PushFcmAbstract(PushFCMNotification notification) {
        this.notification = notification;
    }

    public PushFCMNotification getNotification() {
        return notification;
    }

    public void setNotification(PushFCMNotification notification) {
        this.notification = notification;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public abstract String getDestination();

    public abstract boolean isEmptyDestination();
}
