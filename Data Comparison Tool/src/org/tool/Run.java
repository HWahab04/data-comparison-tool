package org.tool;

public class Run {

    public static void main(String[] args) {
        IRead expectedReader;
        IRead actualReader;

        String rawExpected = "http://localhost:8080/employees/page/4";
        String rawActual = "actual.csv";
        try {
            expectedReader = ReaderFactory.getReader(rawExpected);
            actualReader = ReaderFactory.getReader(rawActual);



            System.out.println("Expected:");
            DataSet expected= expectedReader.readFile(rawExpected);
            System.out.println("**********************************************");
            System.out.println("Actual:");
            DataSet actual= actualReader.readFile(rawActual);
            System.out.println("Result:");
            Compare.compareFiles(actual,expected);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            System.exit(1);
        }


    }
}
