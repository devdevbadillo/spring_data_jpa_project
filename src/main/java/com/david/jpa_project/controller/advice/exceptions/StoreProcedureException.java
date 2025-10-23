package com.david.jpa_project.controller.advice.exceptions;

import lombok.Getter;

@Getter
public class StoreProcedureException extends Exception {
    private Long statusCode;

    public StoreProcedureException(String message) {
        super(message);
    }

    public  StoreProcedureException(String message, Long statusCode) {
        this(message);
        this.statusCode = statusCode;
    }

}
