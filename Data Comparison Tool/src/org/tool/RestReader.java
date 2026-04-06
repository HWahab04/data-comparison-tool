package org.tool;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.tool.converter.JsonDataSetConverter;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class RestReader implements IRead {

    private JsonDataSetConverter jsonConverter = new JsonDataSetConverter();

    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();


    public DataSet readFile(String url) {
        try {
            int startPage = extractPageFromUrl(url);
            String baseUrl = removePageFromUrl(url);

            List<JsonNode> allContent = fetchAllPages(baseUrl, startPage);
            return convertToDataSet(allContent);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<JsonNode> fetchAllPages(String baseUrl, int startPage) throws Exception {
        List<JsonNode> allContent = new ArrayList<>();
        int page = startPage;

        while (true) {
            HttpResponse<String> response = getPage(baseUrl, page);
            allContent.addAll(extractContent(response));

            if (isLastPage(response)) break;
            page++;
        }

        return allContent;
    }

    private HttpResponse<String> getPage(String baseUrl, int page) throws Exception {
        String url = buildPageUrl(baseUrl, page);
        HttpResponse<String> response = sendRequest(url);
        checkResponse(response);
        return response;
    }

    private String buildPageUrl(String baseUrl, int page) {
        return baseUrl + "/" + page;
    }

    private HttpResponse<String> sendRequest(String url) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .GET()
                .build();
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    private void checkResponse(HttpResponse<String> response) {
        if (response.statusCode() / 100 != 2) {
            System.out.println("Error: response wasn't successful. Status code: " + response.statusCode());
            System.exit(1);
        }
    }

    private List<JsonNode> extractContent(HttpResponse<String> response) throws Exception {
        JsonNode root = mapper.readTree(response.body());
        JsonNode contentNode = root.get("content");
        List<JsonNode> contentList = new ArrayList<>();
        if (contentNode != null && contentNode.isArray()) {
            contentNode.forEach(contentList::add);
        }
        return contentList;
    }

    private boolean isLastPage(HttpResponse<String> response) throws Exception {
        JsonNode root = mapper.readTree(response.body());
        return root.get("last").asBoolean();
    }

    private DataSet convertToDataSet(List<JsonNode> allContent) throws Exception {
        String combinedJson = mapper.writeValueAsString(allContent);
        return jsonConverter.convert(combinedJson);
    }

    private int extractPageFromUrl(String url) {
        String[] parts = url.split("/");
        try {
            return Integer.parseInt(parts[parts.length - 1]);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private String removePageFromUrl(String url) {
        return url.replaceAll("/\\d+$", "");
    }
}
