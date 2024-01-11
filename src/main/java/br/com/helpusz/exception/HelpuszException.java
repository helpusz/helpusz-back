package br.com.helpusz.exception;

public class HelpuszException extends RuntimeException {
  
  private static final long serialVersionUID = 1L;

  public HelpuszException(String message) {
    super(message);
  }

  public HelpuszException(String message, Throwable cause) {
    super(message, cause);
  }
  
}
