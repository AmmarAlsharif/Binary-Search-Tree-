package com.company.datastructure.exceptions;

public class EmptyTreeException extends RuntimeException {
  public EmptyTreeException() {}

  public EmptyTreeException(String message) {
    super(message);
  }
}
