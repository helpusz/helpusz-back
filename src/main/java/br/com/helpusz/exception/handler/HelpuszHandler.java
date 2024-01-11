package br.com.helpusz.exception.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.helpusz.exception.HelpuszException;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class HelpuszHandler {
  
  @ExceptionHandler(HelpuszException.class)
  public ResponseEntity<String> handleHelpuszExceptionBadRequest(HelpuszException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
  }
  
}
