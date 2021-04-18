package com.garymace.session.generator.service.utils;

import com.google.inject.Inject;

public class ApiPreconditions {

  @Inject
  private ApiPreconditions() {

  }

  public static <T> void checkNotNull(T object, String errorDetails) {
    if (object == null) {
      throw new RuntimeException(errorDetails);
    }
  }
}
