package com.backend.agendacrista.demo.model;


public abstract class PushFcmAbstract {

    private PushFcmNotification notification;
    private Object data;

    public PushFcmAbstract(PushFcmNotification notification) {
        this.notification = notification;
    }

    public PushFcmNotification getNotification() {
        return notification;
    }

    public void setNotification(PushFcmNotification notification) {
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
