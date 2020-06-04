package com.backend.agendacrista.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PushFCMNotification {
    private String title;
    private String body;
    @JsonProperty("click_action")
    private String clickAction;


    public PushFCMNotification(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public PushFCMNotification(String title, String body, String clickAction) {
        this.title = title;
        this.body = body;
        this.clickAction = clickAction;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getClickAction() {
        return clickAction;
    }

    public void setClickAction(String clickAction) {
        this.clickAction = clickAction;
    }

}
