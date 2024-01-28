package com.springWebFlux.dto;

import java.io.Serializable;
import java.util.Date;

public class Response implements Serializable {

    private Date date = new Date();

    private int output;

    public Date getDate() {
        return date;
    }

    public int getOutput() {
        return output;
    }

    public Response(int output){
        this.output = output;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setOutput(int output) {
        this.output = output;
    }

    @Override
    public String toString() {
        return "Response{" +
                "date=" + date +
                ", output=" + output +
                '}';
    }
}
