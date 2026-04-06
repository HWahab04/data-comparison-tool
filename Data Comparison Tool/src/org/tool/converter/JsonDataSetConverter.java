package org.tool.converter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.tool.DataSet;

import java.util.*;

public class JsonDataSetConverter {

    public DataSet convert(String json) throws Exception {
        List<String> csvLines = convertJsonToCsvLines(json);
        return DataConverter.insertData(csvLines);
    }

    private List<String> convertJsonToCsvLines(String json) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(json);

        if (!root.isArray() || root.size() == 0) {
            return Collections.singletonList("");
        }

        List<String> columns = extractColumns(root);
        return buildCsvLines(root, columns);
    }

    private List<String> extractColumns(JsonNode root) {
        List<String> columns = new ArrayList<>();
        root.get(0).fieldNames().forEachRemaining(columns::add);
        return columns;
    }

    private List<String> buildCsvLines(JsonNode root, List<String> columns) {
        List<String> lines = new ArrayList<>();
        lines.add(String.join(",", columns));
        for (JsonNode node : root) {
            List<String> values = new ArrayList<>();
            for (String col : columns) {
                JsonNode valueNode = node.get(col);
                values.add(valueNode != null ? valueNode.asText() : "");
            }
            lines.add(String.join(",", values));
        }
        return lines;
    }
}
