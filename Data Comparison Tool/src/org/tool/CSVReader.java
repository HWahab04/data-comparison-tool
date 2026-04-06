package org.tool;
import org.tool.converter.DataConverter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

class CSVReader implements IRead {
    public DataSet readFile(String file) {

        List<String> lines=null;
        try {
            lines = Files.readAllLines(Paths.get(file));

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            System.exit(1);
        }

        return DataConverter.insertData(lines);

    }
}