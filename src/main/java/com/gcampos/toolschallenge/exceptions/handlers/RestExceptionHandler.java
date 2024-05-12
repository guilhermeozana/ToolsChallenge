package com.gcampos.toolschallenge.exceptions.handlers;

import com.gcampos.toolschallenge.exceptions.PagamentoNaoEstornadoException;
import com.gcampos.toolschallenge.exceptions.FormaPagamentoNaoEncontradaException;
import com.gcampos.toolschallenge.exceptions.PagamentoNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({
            PagamentoNaoEstornadoException.class,
            PagamentoNaoEncontradoException.class,
            FormaPagamentoNaoEncontradaException.class
    })
    private ResponseEntity<HandlerMensagemErro> naoEncontradoExceptionHandler(RuntimeException exception){
        HandlerMensagemErro handlerMensagemErro = new HandlerMensagemErro(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(handlerMensagemErro);
    }

    @ExceptionHandler({RuntimeException.class})
    private ResponseEntity<HandlerMensagemErro> runtimeExceptionHandler(RuntimeException exception){
        HandlerMensagemErro handlerMensagemErro = new HandlerMensagemErro(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(handlerMensagemErro);
    }
}
