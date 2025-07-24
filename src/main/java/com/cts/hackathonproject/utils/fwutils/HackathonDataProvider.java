package com.cts.hackathonproject.utils.fwutils;

import org.apache.poi.ss.usermodel.*;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class HackathonDataProvider {

    @DataProvider(name = "hdp")
    public static String[][] getData(Method testMethod) {
        ArrayList<String[]> allRowsTestData = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream("testdata/HackathonData.xlsx")) {
            Workbook wb = WorkbookFactory.create(fis);
            Sheet s = wb.getSheet("Sheet1");
            int rowCount = s.getPhysicalNumberOfRows();

            for (int eachrow = 1; eachrow < rowCount; eachrow++) {
                Row row = s.getRow(eachrow);
                if (row == null) continue;

                Cell testCaseCell = row.getCell(2);
                if (testCaseCell == null || testCaseCell.getCellType() != CellType.STRING) continue;

                String testcasename = testCaseCell.getStringCellValue().trim().replaceAll(" ", "");
                if (testcasename.equalsIgnoreCase(testMethod.getName().toLowerCase())) {
                    ArrayList<String> allCellsData = new ArrayList<>();

                    int maxColumns = 10; // adjust to match the expected number of data columns
                    for (int eachcell = 3; eachcell < maxColumns; eachcell++) {
                        Cell cell = row.getCell(eachcell);
                        if (cell == null) {
                            allCellsData.add("");
                        } else {
                            switch (cell.getCellType()) {
                                case STRING:
                                    allCellsData.add(cell.getStringCellValue());
                                    break;
                                case NUMERIC:
                                    allCellsData.add(String.valueOf(cell.getNumericCellValue()));
                                    break;
                                case BOOLEAN:
                                    allCellsData.add(String.valueOf(cell.getBooleanCellValue()));
                                    break;
                                case BLANK:
                                    allCellsData.add("");
                                    break;
                                default:
                                    allCellsData.add("UnsupportedType");
                                    break;
                            }
                        }
                    }

                    allRowsTestData.add(allCellsData.toArray(new String[0]));
                }
            }

        } catch (IOException ioe) {
            // Error handled silently – could add logging if needed
        }

        return allRowsTestData.toArray(new String[0][0]);
    }
}