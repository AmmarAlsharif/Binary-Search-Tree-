package com.company.datastructure.exceptions;

public class NoSuccessorException extends RuntimeException {
  public NoSuccessorException() {}

  public NoSuccessorException(String message) {
    super(message);
  }
}
