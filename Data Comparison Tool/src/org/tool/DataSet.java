package org.tool;

import java.util.*;

public class DataSet {
  private Map<String, List<String>> structuredData;

    public DataSet(Map<String, List<String>> structuredData) {
        this.structuredData = structuredData;
    }

    public List<String> getColumn(String columnName) {
        return structuredData.get(columnName);
    }

    public String getValue(String columnName, int index) {
        return structuredData.get(columnName).get(index);
    }

    public boolean containsValue(String columnName, String value) {
        return structuredData.get(columnName).contains(value);
    }

    public int indexOfValue(String columnName, String value) {
        return structuredData.get(columnName).indexOf(value);
    }

    public Set<String> getColumns() {
        return structuredData.keySet();
    }

}
