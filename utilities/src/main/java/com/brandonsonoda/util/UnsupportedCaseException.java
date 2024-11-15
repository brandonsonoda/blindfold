package com.brandonsonoda.utilities;

public class UnsupportedCaseException extends IllegalStateException {
  public UnsupportedCaseException(Enum e) {
    super(String.format("Unexpected %s: %s", e.getClass().getSimpleName(), e.name()));
  }
}
