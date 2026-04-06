# Data Comparison Tool

A Java tool that compares datasets from CSV, Excel, and REST API sources with detailed mismatch reporting.

## How it works

The tool reads two datasets (actual and expected), matches rows by an `id` column, and reports for each row whether it matched, mismatched, or is missing.

**Output example:**
```
ID: 1 : MATCHED
ID: 2 : MISMATCH : name -> expected=Alice, actual=Alicia
ID: 3 : MISSING in actual
```

## Supported sources

| Source | Example |
|--------|---------|
| CSV | `actual.csv` |
| Excel | `data.xlsx`, `data.xls` |
| REST API | `http://localhost:8080/employees/page/0` |

REST API support handles **pagination automatically** — it fetches all pages until the last one and combines them into a single dataset.

## Requirements

- Java 17+
- Maven

## Setup

```bash
git clone https://github.com/HWahab04/data-comparison-tool.git
cd data-comparison-tool
mvn package
```

## Usage

Configure your sources in `Run.java`:

```java
String rawExpected = "http://localhost:8080/employees/page/0";
String rawActual = "actual.csv";
```

Then run:

```bash
java -jar target/data-comparison-tool-1.0-SNAPSHOT.jar
```

## Project structure

```
src/main/java/org/tool/
├── Run.java                          # Entry point
├── IRead.java                        # Reader interface
├── ReaderFactory.java                # Picks the right reader based on file type
├── CSVReader.java                    # Reads CSV files
├── DataSet.java                      # Core data model
├── Compare.java                      # Comparison logic
├── RestReader.java                   # Fetches paginated REST API data
├── converter/
│   ├── DataConverter.java            # Converts raw lines into a DataSet
│   └── JsonDataSetConverter.java     # Converts JSON into CSV lines
└── excel/
    ├── ExcelReader.java              # Reads Excel files
    ├── ExcelWorkbookFormatter.java   # Formats workbook rows into lines
    └── ExcelCellFormatter.java       # Handles cell type formatting
```

## Dependencies

- [Apache POI](https://poi.apache.org/) — Excel file reading
- [Jackson](https://github.com/FasterXML/jackson) — JSON parsing
