package com.brandonsonoda.app;

class UnsupportedCaseException extends IllegalStateException {
  UnsupportedCaseException(Enum e) {
    super(String.format("Unexpected %s: %s", e.getClass().getSimpleName(), e.name()));
  }
}
