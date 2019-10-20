package core.util;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class RequestBuilder {


    public RequestSpecification baseRequest() {

        String timestamp = HashUtils.getTimestamp();
        return given()
                .baseUri("http://gateway.marvel.com/v1/public")
                .accept(JSON)
                .queryParam("ts", timestamp)
                .queryParam("apikey", System.getProperty("public.key"))
                .queryParam("hash", HashUtils.getHash(timestamp, System.getProperty("private.key"), System.getProperty("public.key")));


    }

    public RequestSpecification baseRequestUnautorized() {
        return given()
                .baseUri("http://gateway.marvel.com/v1/public");

    }

    public Response sendGetTo(String path) {
        return baseRequest()
                .get(path)
                .prettyPeek();
    }

    public Response sendGetTo(String path, String limit, String offset) {
        return baseRequest()
                .queryParam("limit", limit)
                .queryParam("offset", offset)
                .get(path)
                .prettyPeek();
    }

    public Response sendGetToWithId(String path, String id) {
        return baseRequest()
                .basePath(path)
                .get("/"+id)
                .prettyPeek();
    }


    public Response sendGetUnautorized(String path, String limit, String offset){
        return baseRequestUnautorized()
                .queryParam("limit", limit)
                .queryParam("offset", offset)
                .get(path)
                .prettyPeek();
    }

}
