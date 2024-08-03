package fon.mas.novica.micronaut.advice;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record ApiException(
        int status,
        String exception,
        String message
) {
}

