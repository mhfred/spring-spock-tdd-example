package com.example.controller.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by deadlock on 27/1/17.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenericResponse<T> {
    @JsonProperty("status")
    private String status;
    @JsonProperty("status_code")
    private Integer statusCode;
    @JsonProperty("data")
    private T data;
    @JsonProperty("message")
    private String message;

    public GenericResponse() {
        success();
    }

    public GenericResponse(T data) {
        success();
        this.data = data;
    }

    public GenericResponse success() {
        this.status = ResponseStatus.SUCCESS.getReasonPhrase();
        this.statusCode = ResponseStatus.SUCCESS.getValue();
        return this;
    }

    public GenericResponse fail() {
        this.status = ResponseStatus.FAIL.getReasonPhrase();
        this.statusCode = ResponseStatus.FAIL.getValue();
        return this;
    }

    public GenericResponse error() {
        this.status = ResponseStatus.ERROR.getReasonPhrase();
        this.statusCode = ResponseStatus.ERROR.getValue();
        return this;
    }
}

