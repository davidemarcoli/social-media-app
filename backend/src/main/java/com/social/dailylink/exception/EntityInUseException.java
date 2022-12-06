package com.social.dailylink.exception;

public class EntityInUseException extends RuntimeException {
  public EntityInUseException(String message) {
    super(message);
  }
}
