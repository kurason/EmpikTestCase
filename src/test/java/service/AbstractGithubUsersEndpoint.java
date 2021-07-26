package service;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public abstract class AbstractGithubUsersEndpoint {
  public static final String URL = "https://api.github.com/users/";

  public static RequestSpecification initRequestSpecification(String login) {
   return new RequestSpecBuilder()
       .setContentType(ContentType.JSON)
       .setBaseUri(URL + login)
       .addFilter(new RequestLoggingFilter())
       .addFilter(new ResponseLoggingFilter())
       .build();
  }
}
