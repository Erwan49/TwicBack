package fr.eseo.twic.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String entity, String codeCommune) {
        super(entity + " with id " + codeCommune + " does not exists");
    }

}