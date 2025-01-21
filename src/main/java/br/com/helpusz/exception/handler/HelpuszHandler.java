package br.com.helpusz.exception.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.helpusz.exception.HelpuszException;

@RestControllerAdvice
public class HelpuszHandler {

  @ExceptionHandler(HelpuszException.class)
  public ResponseEntity<ErrorResponse> handleHelpuszException(HelpuszException ex) {
    ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), ex.getStatus().value());
    return ResponseEntity.status(ex.getStatus()).body(errorResponse);
  }

  public static class ErrorResponse {
    private String message;
    private int status;

    public ErrorResponse(String message, int status) {
      this.message = message;
      this.status = status;
    }

    public String getMessage() {
      return message;
    }

    public int getStatus() {
      return status;
    }
  }
}
