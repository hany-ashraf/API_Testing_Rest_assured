package Data;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;

public class ExcelReader {
    public String[][] data_Driven() throws IOException, InvalidFormatException {
        File file = new File("C:\\Users\\ESMC\\Documents\\API_Response.xlsx");

        XSSFWorkbook wb = new XSSFWorkbook(file);
        XSSFSheet sheet = wb.getSheet("Data_APIs");
        int number_of_rows = sheet.getPhysicalNumberOfRows();
        int number_of_columns = sheet.getRow(0).getLastCellNum();
        String[][] array = new String[number_of_rows - 1][number_of_columns];

        for (int i = 0; i < number_of_rows; i++) {
            for (int j = 0; j < number_of_columns; j++) {
                XSSFRow row = sheet.getRow(i);
                array[i][j] = row.getCell(j).getStringCellValue();
            }
        }
        return array;

    }
}