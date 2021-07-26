import enums.HttpStatus;
import dto.UserDto;
import io.restassured.response.Response;
import org.junit.Assert;
import org.testng.annotations.Test;
import service.GetGithubUsers;

import java.time.ZonedDateTime;

public class OctocatTest {

  private static final String TEST_LOGIN = "octocat";
  private static final String FAIL_MESSAGE = "Wrong \"%s\" field value. Expected: %s, actual: %s.";

  private Response response;

  @Test
  public void testOctocatUser() {
    response = GetGithubUsers.callGetByLogin(TEST_LOGIN);
    verifyHttpStatusCode();
    verifyFieldValues();
  }

  private void verifyHttpStatusCode() {
    Assert.assertTrue(response.getStatusCode() == HttpStatus.OK.getCode());
  }

  private void verifyFieldValues() {
    UserDto actualData = response.as(UserDto.class);
    UserDto expectedData = initExpectedOctocatData();

    verifyStringFieldValue(expectedData.getLogin(), actualData.getLogin(), "login");
    verifyIntFieldValue(expectedData.getId(), actualData.getId(), "id");
    verifyStringFieldValue(expectedData.getNode_id(), actualData.getNode_id(), "node_id");
    verifyStringFieldValue(expectedData.getAvatar_url(), actualData.getAvatar_url(), "avatar_url");
    verifyStringFieldValue(expectedData.getGravatar_id(), actualData.getGravatar_id(), "gravatar_id");
    verifyStringFieldValue(expectedData.getUrl(), actualData.getUrl(), "url");
    verifyStringFieldValue(expectedData.getHtml_url(), actualData.getHtml_url(), "html_url");
    verifyStringFieldValue(expectedData.getFollowers_url(), actualData.getFollowers_url(), "followers_url");
    verifyStringFieldValue(expectedData.getFollowing_url(), actualData.getFollowing_url(), "following_url");
    verifyStringFieldValue(expectedData.getGists_url(), actualData.getGists_url(), "gists_url");
    verifyStringFieldValue(expectedData.getStarred_url(), actualData.getStarred_url(), "starred_url");
    verifyStringFieldValue(expectedData.getSubscriptions_url(), actualData.getSubscriptions_url(), "subscriptions_url");
    verifyStringFieldValue(expectedData.getRepos_url(), actualData.getRepos_url(), "repos_url");
    verifyStringFieldValue(expectedData.getEvents_url(), actualData.getEvents_url(), "events_url");
    verifyStringFieldValue(expectedData.getReceived_events_url(), actualData.getReceived_events_url(), "received_events_url");
    verifyStringFieldValue(expectedData.getType(), actualData.getType(), "type");
    verifyBooleanFieldValue(expectedData.isSite_admin(), actualData.isSite_admin(), "site_admin");
    verifyStringFieldValue(expectedData.getName(), actualData.getName(), "name");
    verifyStringFieldValue(expectedData.getCompany(), actualData.getCompany(), "company");
    verifyStringFieldValue(expectedData.getBlog(), actualData.getBlog(), "blog");
    verifyStringFieldValue(expectedData.getLocation(), actualData.getLocation(), "location");
    verifyStringFieldValue(expectedData.getEmail(), actualData.getEmail(), "email");
    verifyStringFieldValue(expectedData.getHireable(), actualData.getHireable(), "hireable");
    verifyStringFieldValue(expectedData.getBio(), actualData.getBio(), "bio");
    verifyStringFieldValue(expectedData.getTwitter_username(), actualData.getTwitter_username(), "twitter_username");
    verifyIntFieldValue(expectedData.getPublic_repos(), actualData.getPublic_repos(), "public_repos");
    verifyIntFieldValue(expectedData.getPublic_gists(), actualData.getPublic_gists(), "public_gists");
    verifyIntFieldValue(expectedData.getFollowers(), actualData.getFollowers(), "followers");
    verifyIntFieldValue(expectedData.getFollowing(), actualData.getFollowing(), "following");
    verifyDateFieldValue(expectedData.getCreated_at(), actualData.getCreated_at(), "created_at");
    verifyDateFieldValue(expectedData.getUpdated_at(), actualData.getUpdated_at(), "updated_at");
  }

  private void verifyStringFieldValue(String expectedValue, String actualValue, String verifyingField) {
    String failMessage = getFailMessage(verifyingField, expectedValue, actualValue);
    Assert.assertEquals(failMessage, expectedValue, actualValue);
  }

  private void verifyIntFieldValue(int expectedValue, int actualValue, String verifyingField) {
    String failMessage = getFailMessage(verifyingField, Integer.toString(expectedValue), Integer.toString(actualValue));
    Assert.assertTrue(failMessage, expectedValue == actualValue);
  }

  private void verifyBooleanFieldValue(boolean expectedValue, boolean actualValue, String verifyingField) {
    String failMessage = getFailMessage(verifyingField, Boolean.toString(expectedValue), Boolean.toString(actualValue));
    Assert.assertTrue(failMessage, expectedValue == actualValue);
  }

  private void verifyDateFieldValue(String expectedValue, String actualValue, String verifyingField) {
    String failMessage = getFailMessage(verifyingField, expectedValue, actualValue);
    ZonedDateTime expectedDate = ZonedDateTime.parse(expectedValue);
    ZonedDateTime actualDate = ZonedDateTime.parse(actualValue);
    Assert.assertTrue(failMessage, expectedDate.isEqual(actualDate));
  }

  private String getFailMessage(String verifyingField, String expectedValue, String actualValue) {
    return String.format(FAIL_MESSAGE, verifyingField, expectedValue, actualValue);
  }

  private static UserDto initExpectedOctocatData() {
    return UserDto.builder()
        .login("octocat")
        .id(583231)
        .node_id("MDQ6VXNlcjU4MzIzMQ==")
        .avatar_url("https://avatars.githubusercontent.com/u/583231?v=4")
        .gravatar_id("")
        .url("https://api.github.com/users/octocat")
        .html_url("https://github.com/octocat")
        .followers_url("https://api.github.com/users/octocat/followers")
        .following_url("https://api.github.com/users/octocat/following{/other_user}")
        .gists_url("https://api.github.com/users/octocat/gists{/gist_id}")
        .starred_url("https://api.github.com/users/octocat/starred{/owner}{/repo}")
        .subscriptions_url("https://api.github.com/users/octocat/subscriptions")
        .organizations_url("https://api.github.com/users/octocat/orgs")
        .repos_url("https://api.github.com/users/octocat/repos")
        .events_url("https://api.github.com/users/octocat/events{/privacy}")
        .received_events_url("https://api.github.com/users/octocat/received_events")
        .type("User")
        .site_admin(false)
        .name("The Octocat")
        .company("@github")
        .blog("https://github.blog")
        .location("San Francisco")
        .email(null)
        .hireable(null)
        .bio(null)
        .twitter_username(null)
        .public_repos(8)
        .public_gists(8)
        .followers(3881)
        .following(9)
        .created_at("2011-01-25T18:44:36Z")
        .updated_at("2021-07-22T14:27:29Z")
        .build();
  }
}
