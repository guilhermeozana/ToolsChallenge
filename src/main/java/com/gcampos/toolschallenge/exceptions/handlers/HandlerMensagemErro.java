package com.gcampos.toolschallenge.exceptions.handlers;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class HandlerMensagemErro {
    private HttpStatus httpStatus;
    private String mensagem;
}
