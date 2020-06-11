package com.backend.agendacrista.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PushFcmData {
    @JsonProperty(value = "click_action")
    private String clickAction;
    public final String sound = "default";
    public final String status = "done";
    private String screen;
    @JsonProperty(value = "click_action_id")
    private Long clickActionId;


    public PushFcmData(String clickAction, String screen, Long clickActionId) {
        this.clickAction = clickAction;
        this.screen = screen;
        this.clickActionId = clickActionId;
    }

    public String getClickAction() {
        return clickAction;
    }

    public void setClickAction(String clickAction) {
        this.clickAction = clickAction;
    }

    public String getSound() {
        return sound;
    }

    public String getStatus() {
        return status;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public Long getClickActionData() {
        return clickActionId;
    }

    public void setClickActionData(Long clickActionData) {
        this.clickActionId = clickActionData;
    }
}
