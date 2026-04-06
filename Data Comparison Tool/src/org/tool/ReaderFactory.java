package org.tool;

import org.tool.excel.ExcelReader;

import java.io.IOException;

public class ReaderFactory {
    public static IRead getReader(String file) throws IOException {
        if(file.toLowerCase().endsWith(".csv")){
            return new CSVReader();
        } else if (file.toLowerCase().endsWith(".xls") || file.toLowerCase().endsWith(".xlsx")) {
            return new ExcelReader();
        } else if (file.toLowerCase().startsWith("http") || file.toLowerCase().endsWith("https")) {
        return new RestReader();
    }
        throw new IOException("Unsupported file type: " + file);
    }


}
