package com.backend.agendacrista.demo.error;


public class ValidationErroDetails extends ErrorDetails {

    private String fields;
    private String fieldsMessage;

    private ValidationErroDetails() {

    }

    public String getFields() {
        return fields;
    }

    public void setFields(String fields) {
        this.fields = fields;
    }

    public String getFieldsMessage() {
        return fieldsMessage;
    }

    public void setFieldsMessage(String fieldsMessage) {
        this.fieldsMessage = fieldsMessage;
    }

    public static final class Builder {
        private String title;
        private int status;
        private String detail;
        private long timestamp;
        private String developerMessage;
        private String fields;
        private String fieldsMessage;

        private Builder() {
        }

        public static ValidationErroDetails.Builder newBuilder() {
            return new ValidationErroDetails.Builder();
        }

        public ValidationErroDetails.Builder title(String title) {
            this.title = title;
            return this;
        }

        public ValidationErroDetails.Builder status(int status) {
            this.status = status;
            return this;
        }

        public ValidationErroDetails.Builder detail(String detail) {
            this.detail = detail;
            return this;
        }

        public ValidationErroDetails.Builder timestamp(long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public ValidationErroDetails.Builder developerMessage(String developerMessage) {
            this.developerMessage = developerMessage;
            return this;
        }

        public ValidationErroDetails.Builder fields(String fields) {
            this.fields = fields;
            return this;
        }

        public ValidationErroDetails.Builder fieldsMessage(String fieldsMessage) {
            this.fieldsMessage = fieldsMessage;
            return this;
        }

        public ValidationErroDetails build() {
            ValidationErroDetails validationErroDetails = new ValidationErroDetails();
            validationErroDetails.setDeveloperMessage(developerMessage);
            validationErroDetails.setDetail(detail);
            validationErroDetails.setTimestamp(timestamp);
            validationErroDetails.setStatus(status);
            validationErroDetails.setTitle(title);
            validationErroDetails.setFields(fields);
            validationErroDetails.setFieldsMessage(fieldsMessage);
            return validationErroDetails;
        }
    }
}