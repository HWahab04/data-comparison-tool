package org.tool;

public class Compare {

    private static  String getIdSignature(DataSet dataSet) {
        for (String column : dataSet.getColumns()) {
            if (column.equalsIgnoreCase("id")) {
                return column;
            }
        }
        return "";
    }

    public static void compareFiles(DataSet actual, DataSet expected) {
        String idColumn = getIdSignature(expected);

        for (String expectedId : expected.getColumn(idColumn)) {
            if (!actual.containsValue(idColumn, expectedId)) {
                reportMissingId(expectedId);
            } else {
                int actualIndex = actual.indexOfValue(idColumn, expectedId);
                compareRow(actual, expected, idColumn, expectedId, actualIndex);
            }
        }
    }

    private static void compareRow(DataSet actual, DataSet expected, String idColumn, String expectedId, int actualIndex) {
        boolean allMatch = true;
        for (String column : expected.getColumns()) {
            if (!column.equals(idColumn)) {
                String actualVal = actual.getValue(column, actualIndex);
                String expectedVal = expected.getValue
                        (column, expected.indexOfValue(idColumn, expectedId));
                if (!actualVal.equals(expectedVal)) {
                    reportMismatch(expectedId, column, expectedVal, actualVal);
                    allMatch = false;
                }
            }
        }
        if (allMatch) {
            reportMatch(expectedId);
        }
    }

    private static void reportMissingId(String id) {
        System.out.println("ID: " + id + " : MISSING in actual");
    }

    private static void reportMismatch(String id, String column, String expectedVal, String actualVal) {
        System.out.println("ID: " + id + " : MISMATCH : " + column +
                " -> expected=" + expectedVal + ", actual=" + actualVal);
    }

    private static void reportMatch(String id) {
        System.out.println("ID: " + id + " : MATCHED");
    }


}
