package com.backend.agendacrista.demo.model;

public class PushFcmTo extends PushFcmAbstract {

    private String to;

    public PushFcmTo(String to, PushFCMNotification notification) {
        super(notification);
        this.to = to;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public String getDestination() {
        return this.to;
    }

    @Override
    public boolean isEmptyDestination() {
        return this.to.isEmpty();
    }
}
