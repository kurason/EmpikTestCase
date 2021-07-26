package enums;

import lombok.Getter;

public enum HttpStatus {
  OK(200);

  @Getter
  private int code;

  HttpStatus(final int code) {
    this.code = code;
  }
}
