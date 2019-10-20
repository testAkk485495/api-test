package core.steps;

import core.pages.CharactersPage;
import core.util.DriverBuilder;
import core.util.RequestBuilder;
import core.util.ParsingUtils;
import core.util.ScenarioScope;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.deps.com.google.gson.JsonArray;
import io.restassured.response.Response;

import java.util.logging.Logger;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CharactersSteps {

    protected static final String ITEMS = "items";
    protected static final String RESPONSE = "response";
    protected static final String URL = "url";
    protected static final String COMICS_NUMBER = "number_of_comics";
    protected ScenarioScope scope = new ScenarioScope();

    @When("^send unauthorized GET request to \"([^\"]*)\" endpoint and with limit \"([^\"]*)\" and offset \"([^\"]*)\"$")
    public void sendGetUnauthorized(String path, String limit, String offset){
        Response response = new RequestBuilder().sendGetUnautorized(path, limit, offset);
        scope.setContext(RESPONSE, response);

    }

    @When("^get all items from \"([^\"]*)\" endpoint with pagination$")
    public void getAllItemsWithPagination(String endpoint) {
        JsonArray items = ParsingUtils.getAllItemsWithPagination(endpoint);
        scope.setContext(ITEMS, items);

    }


    @When("^assert schema is correct$")
    public void assertSchemaIsCorrect() {
        JsonArray items = scope.getContext(ITEMS, JsonArray.class);
        for(Object obj : items){
            assertThat(obj.toString(), matchesJsonSchemaInClasspath("schema.json")); //matching schema for each 'results' array element
        }
    }


    @When("^send GET to \"([^\"]*)\" endpoint and with limit \"([^\"]*)\" and offset \"([^\"]*)\"$")
    public void sendGetTo(String path, String limit, String offset) {
        Response response = new RequestBuilder().sendGetTo(path, limit, offset);
        scope.setContext(RESPONSE, response);

    }


    @Then("^assert error \"([^\"]*)\" is thrown$")
    public void assertErrorIsThrown(String error) {
        Response response = scope.getContext(RESPONSE, Response.class);
        assertTrue(response.getBody().asString().contains(error));
    }

    @When("^get character by id \"([^\"]*)\"$")
    public void getCharactersById(String id){
        Response response = new RequestBuilder().sendGetToWithId("/characters", id);
        scope.setContext(RESPONSE, response);
    }


    @When("^get url from response$")
    public void getUrlFromResponse(){
        Response response = scope.getContext(RESPONSE, Response.class);
        scope.setContext(URL,  ParsingUtils.getUrlFromCharacter(response.asString()));
    }

    @When("^get number of comics from response$")
    public void getNumberOfComicsFromResponse(){
        Response response = scope.getContext(RESPONSE, Response.class);
        scope.setContext(COMICS_NUMBER,  ParsingUtils.getNumberOfComics(response.asString()));
    }

    @When("^assert number of comics on web page same as returned from api$")
    public void assertNumberOfComicsOnWebPageIsSameAsReturnedFromApi (){
        CharactersPage charactersPage = new CharactersPage(DriverBuilder.createWebDriver());

        charactersPage.openPage(scope.getContext(URL, String.class));
        assertEquals("Number of comics on web page doesn't equal number of comics returned by api",
                charactersPage.getNumberOfComicsOnPage(), scope.getContext(COMICS_NUMBER, String.class));
        charactersPage.quitDriver();

    }

}

