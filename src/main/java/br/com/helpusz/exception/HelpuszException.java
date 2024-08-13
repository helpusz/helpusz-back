package br.com.helpusz.exception;

import org.springframework.http.HttpStatus;

public class HelpuszException extends RuntimeException {

  private static final long serialVersionUID = 1L;
  private final HttpStatus status;

  public HelpuszException(String message, HttpStatus status) {
    super(message);
    this.status = status;
  }

  public HelpuszException(String message, Throwable cause, HttpStatus status) {
    super(message, cause);
    this.status = status;
  }

  public HttpStatus getStatus() {
    return status;
  }
}
