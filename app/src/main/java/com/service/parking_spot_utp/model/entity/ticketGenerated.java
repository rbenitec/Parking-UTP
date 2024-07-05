package com.service.parking_spot_utp.model.entity;

import java.io.Serializable;

public class ticketGenerated implements Serializable {
    private boolean ok;
    private String message;
    private Body body;

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public static class Body implements Serializable {
        private Integer ticketNumber;

        public Integer getTicketNumber() {
            return ticketNumber;
        }

        public void setTicketNumber(Integer ticketNumber) {
            this.ticketNumber = ticketNumber;
        }
    }
}
