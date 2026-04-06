package org.tool.converter;

import org.tool.DataSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DataConverter {
     static void printData(Map<String, List<String>> data){

        for (Map.Entry<String, List<String>> entry : data.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
    static Map<String, List<String>> insertColumns(String [] values){
        Map<String,List<String>> structuredData=new TreeMap<>();
        for (String value : values) {
            structuredData.put(value.trim().toLowerCase(), new ArrayList<>());
        }
        return structuredData;
    }
    public static DataSet insertData(List<String> lines) {
        String[] values = lines.get(0).split(",");
        Map<String, List<String>> structuredData = insertColumns(values);

        for (int i = 1; i < lines.size(); i++) {
            String[] newValues = lines.get(i).split(",");
            for (int j = 0; j < newValues.length; j++) {
                String key = values[j].trim().toLowerCase();
                structuredData.get(key).add(newValues[j]);
            }
        }

        printData(structuredData);
        return new DataSet(structuredData);
    }


}
