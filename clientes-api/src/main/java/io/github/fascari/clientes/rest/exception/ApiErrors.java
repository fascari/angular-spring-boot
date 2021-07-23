package io.github.fascari.clientes.rest.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
public class ApiErrors {

    @Getter
    private final List<String> errors;

    public ApiErrors(String message) {
        this.errors = Collections.singletonList(message);
    }
}