package com.backend.agendacrista.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.List;

public class PushFcmRegistrationIds extends PushFcmAbstract {
    @JsonProperty("registration_ids")
    private List<String> registrationIds;

    public PushFcmRegistrationIds(List<String> registrationIds, PushFcmNotification notification) {
        super(notification);
        this.registrationIds = registrationIds;
    }

    public List<String> getRegistrationIds() {
        return registrationIds;
    }

    public void setRegistrationIds(List<String> registrationIds) {
        this.registrationIds = registrationIds;
    }

    @Override
    public String getDestination() {
        return Arrays.toString(this.registrationIds.toArray());
    }

    @Override
    public boolean isEmptyDestination() {
        return this.registrationIds.isEmpty();
    }
}
