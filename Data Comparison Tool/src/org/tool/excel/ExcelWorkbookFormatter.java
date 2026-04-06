package org.tool.excel;

import org.apache.poi.ss.usermodel.*;

import java.util.*;


public class ExcelWorkbookFormatter {

    private final ExcelCellFormatter cellFormatter = new ExcelCellFormatter();

    public List<String> format(Workbook workbook) {
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();
        List<String> lines = new ArrayList<>();

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            lines.add(formatRow(row));
        }
        return lines;
    }

    private String formatRow(Row row) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < row.getLastCellNum(); i++) {
            Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            sb.append(cellFormatter.getType(cell));
            if (i < row.getLastCellNum() - 1) sb.append(",");
        }

        return sb.toString();
    }
}