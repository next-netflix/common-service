package com.nextnetflix.commonservice.exception;

import org.springframework.http.HttpStatus;

public interface ErrorCode {

    String code();

    String message();

    HttpStatus status();

    default void validate() {
        if (!code().contains("-")) {
            throw new IllegalStateException("Invalid error code format");
        }
    }

}
