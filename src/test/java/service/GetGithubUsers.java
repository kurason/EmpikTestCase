package service;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class GetGithubUsers extends AbstractGithubUsersEndpoint {

  public static Response callGetByLogin(String login) {
    return RestAssured
        .given()
        .spec(initRequestSpecification(login))
        .get();
  }
}
