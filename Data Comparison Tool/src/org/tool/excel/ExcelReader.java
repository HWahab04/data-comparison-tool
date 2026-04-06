package org.tool.excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.tool.*;
import org.tool.converter.DataConverter;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

    public class ExcelReader implements IRead {

        private final ExcelWorkbookFormatter workbookFormatter = new ExcelWorkbookFormatter();

        @Override
        public DataSet readFile(String fileName) {
            try (FileInputStream fis = new FileInputStream(fileName);
                 Workbook workbook = new XSSFWorkbook(fis)) {

                List<String> lines = workbookFormatter.format(workbook);
                return DataConverter.insertData(lines);

            } catch (IOException e) {
                System.out.println("Error reading Excel file: " + e.getMessage());
                System.exit(1);
                return null;
            }
        }
}
