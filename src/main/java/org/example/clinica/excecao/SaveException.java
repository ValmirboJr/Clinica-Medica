package org.example.clinica.excecao;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SaveException extends RuntimeException {
    public SaveException(String message){
        super(message);
    }
}
