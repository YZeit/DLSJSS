package dlsjss.util;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xddf.usermodel.XDDFSolidFillProperties;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;

public class ExcelReader {

    public static String readStringValue(String SAMPLE_XLSX_FILE_PATH, String SHEET_NAME) throws IOException, InvalidFormatException {

        File file = new File(SAMPLE_XLSX_FILE_PATH);
        FileInputStream fis = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);

        Sheet sheet = workbook.getSheet(SHEET_NAME);

        Row row = sheet.getRow(0);  // Assuming the string value is in the first row (0-based index)
        Cell cell = row.getCell(0);  // Assuming the string value is in the first column (0-based index)

        String cellValue = cell.getStringCellValue();

        workbook.close();
        fis.close();

        return cellValue;
    }

    public static int[][] readInputDataArrayInt(String SAMPLE_XLSX_FILE_PATH, String SHEET_NAME) throws IOException, InvalidFormatException {

        // Creating a Workbook from an Excel file (.xls or .xlsx)
        //Workbook workbook = WorkbookFactory.create(new File(SAMPLE_XLSX_FILE_PATH));
        File file = new File(SAMPLE_XLSX_FILE_PATH);
        FileInputStream fis = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);

        // Getting the Sheet
        Sheet sheet = workbook.getSheet(SHEET_NAME);

        // Create a DataFormatter to format and get each cell's value as String
        //DataFormatter dataFormatter = new DataFormatter();

        int noOfColumns = sheet.getRow(0).getPhysicalNumberOfCells();
        int noOfRows = sheet.getPhysicalNumberOfRows() - 1;

        int[][] array = new int[noOfRows][noOfColumns];

        // 2. Or you can use a for-each loop to iterate over the rows and columns
        for (int r = 1; r <= noOfRows; r++) {
            Row row = sheet.getRow(r);
            for (int c = 1; c <= noOfColumns; c++) {
                Cell cell = row.getCell(c);
                double cellValue = cell.getNumericCellValue();
                int cellValueInt = (int) cellValue;
                array[r-1][c-1] = cellValueInt;
            }
        }

        // Closing the workbook
        workbook.close();
        fis.close();

        return array;
    }

    public static double[][] readInputDataArrayDouble(String SAMPLE_XLSX_FILE_PATH, String SHEET_NAME) throws IOException, InvalidFormatException {

        // Creating a Workbook from an Excel file (.xls or .xlsx)
        //Workbook workbook = WorkbookFactory.create(new File(SAMPLE_XLSX_FILE_PATH));
        File file = new File(SAMPLE_XLSX_FILE_PATH);
        FileInputStream fis = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);

        // Getting the Sheet
        Sheet sheet = workbook.getSheet(SHEET_NAME);

        // Create a DataFormatter to format and get each cell's value as String
        //DataFormatter dataFormatter = new DataFormatter();

        int noOfColumns = sheet.getRow(0).getPhysicalNumberOfCells();
        int noOfRows = sheet.getPhysicalNumberOfRows() - 1;

        double[][] array = new double[noOfRows][noOfColumns];

        // 2. Or you can use a for-each loop to iterate over the rows and columns
        for (int r = 1; r <= noOfRows; r++) {
            Row row = sheet.getRow(r);
            for (int c = 1; c <= noOfColumns; c++) {
                Cell cell = row.getCell(c);
                double cellValue = cell.getNumericCellValue();
                array[r-1][c-1] = cellValue;
            }
        }

        // Closing the workbook
        workbook.close();
        fis.close();

        return array;
    }

    public static double[] readInputDataListDouble(String SAMPLE_XLSX_FILE_PATH, String SHEET_NAME) throws IOException, InvalidFormatException {

        // Creating a Workbook from an Excel file (.xls or .xlsx)
        //Workbook workbook = WorkbookFactory.create(new File(SAMPLE_XLSX_FILE_PATH));
        File file = new File(SAMPLE_XLSX_FILE_PATH);
        FileInputStream fis = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);

        // Getting the Sheet
        Sheet sheet = workbook.getSheet(SHEET_NAME);

        // Create a DataFormatter to format and get each cell's value as String
        //DataFormatter dataFormatter = new DataFormatter();

        int noOfColumns = sheet.getRow(0).getPhysicalNumberOfCells();
        int noOfRows = sheet.getPhysicalNumberOfRows() - 1;

        double[] list = new double[noOfRows];

        // 2. Or you can use a for-each loop to iterate over the rows and columns
        for (int r = 1; r <= noOfRows; r++) {
            Row row = sheet.getRow(r);
            for (int c = 1; c <= noOfColumns; c++) {
                Cell cell = row.getCell(c);
                double cellValue = cell.getNumericCellValue();
                list[r-1] = cellValue;
            }
        }

        // Closing the workbook
        workbook.close();
        fis.close();

        return list;
    }

    public static int[] readInputDataListInt(String SAMPLE_XLSX_FILE_PATH, String SHEET_NAME) throws IOException, InvalidFormatException {

        // Creating a Workbook from an Excel file (.xls or .xlsx)
        //Workbook workbook = WorkbookFactory.create(new File(SAMPLE_XLSX_FILE_PATH));
        File file = new File(SAMPLE_XLSX_FILE_PATH);
        FileInputStream fis = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);

        // Getting the Sheet
        Sheet sheet = workbook.getSheet(SHEET_NAME);

        // Create a DataFormatter to format and get each cell's value as String
        //DataFormatter dataFormatter = new DataFormatter();

        int noOfColumns = sheet.getRow(0).getPhysicalNumberOfCells();
        int noOfRows = sheet.getPhysicalNumberOfRows() - 1;

        int[] list = new int[noOfRows];

        // 2. Or you can use a for-each loop to iterate over the rows and columns
        for (int r = 1; r <= noOfRows; r++) {
            Row row = sheet.getRow(r);
            for (int c = 1; c <= noOfColumns; c++) {
                Cell cell = row.getCell(c);
                double cellValue = cell.getNumericCellValue();
                int cellValueInt = (int) cellValue;
                list[r-1] = cellValueInt;
            }
        }

        // Closing the workbook
        workbook.close();
        fis.close();

        return list;
    }

}
