package com.company.datastructure.exceptions;

public class ValueNotFoundException extends RuntimeException {

  public ValueNotFoundException() {}

  public ValueNotFoundException(String message) {
    super(message);
  }
}
