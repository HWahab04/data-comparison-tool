package org.tool.excel;

import org.apache.poi.ss.usermodel .Cell;


public class ExcelCellFormatter {

    public String getType(Cell cell){
        String value = "";

        switch (cell.getCellType()) {
            case STRING -> value = cell.getStringCellValue();
            case NUMERIC -> {
                double num = cell.getNumericCellValue();
                if (num == Math.floor(num)) {
                    value = String.valueOf((int) num);
                } else {
                    value = String.valueOf(num);
                }
            }
            case BOOLEAN -> value = String.valueOf(cell.getBooleanCellValue());
            case BLANK -> value = "";
            default -> value = "";
        }
        return value;
    }
}
