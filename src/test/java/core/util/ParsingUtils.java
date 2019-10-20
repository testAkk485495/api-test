package core.util;

import gherkin.deps.com.google.gson.JsonArray;
import gherkin.deps.com.google.gson.JsonParser;

public class ParsingUtils {


    public static int getTotalItems(String path) {
        String response = new RequestBuilder().sendGetTo(path).asString();


        return new JsonParser()
                .parse(response)
                .getAsJsonObject()
                .getAsJsonObject("data")
                .get("total").getAsInt();
    }

    public static JsonArray getResultsArray(String response) {
        return new JsonParser()
                .parse(response)
                .getAsJsonObject()
                .getAsJsonObject("data")
                .getAsJsonArray("results");
    }


    public static JsonArray getAllItemsWithPagination(String path) {
        int total = ParsingUtils.getTotalItems(path);
        int offset = 0;
        int limit = 100;
        JsonArray array = new JsonArray();
        while (total > 0) {

            if (total >= limit) {
                String response = new RequestBuilder().sendGetTo(path, limit + "", offset + "").asString();
                array.addAll(ParsingUtils.getResultsArray(response));
                offset += limit;
                total -= limit;

            } else {
                limit = total;
                String response = new RequestBuilder().sendGetTo(path, limit + "", offset + "").asString();
                array.addAll(ParsingUtils.getResultsArray(response));
                total -= limit;
                break;
            }
        }
        return array;
    }


    public static String getUrlFromCharacter(String response) {
        return new JsonParser()
                .parse(response)
                .getAsJsonObject()
                .getAsJsonObject("data")
                .getAsJsonArray("results")
                .get(0).getAsJsonObject()
                .getAsJsonArray("urls")
                .get(1).getAsJsonObject()
                .get("url").getAsString();

    }

    public static String getNumberOfComics(String response) {
        return new JsonParser()
                .parse(response)
                .getAsJsonObject()
                .getAsJsonObject("data")
                .getAsJsonArray("results")
                .get(0).getAsJsonObject()
                .getAsJsonObject("comics")
                .get("available").getAsString();

    }
}
