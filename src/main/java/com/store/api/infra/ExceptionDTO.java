package com.store.api.infra;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionDTO {

    private String message;

    public ExceptionDTO(String message) {
        this.message = message;
    }
}
