
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.*;

public class Read_LoginExcelData {


    public String [][]read_Sheet ()throws InvalidFormatException, IOException

    {
        File myfile=new File(".\\TestData\\LoginTestData.xlsx");
     //   FileInputStream fis = new FileInputStream(myfile);
        XSSFWorkbook excelWorkbook = new XSSFWorkbook(OPCPackage.open(myfile));
        XSSFSheet mysheet=excelWorkbook.getSheet("Sheet1");
        int number_of_rows=mysheet.getPhysicalNumberOfRows();
        int number_of_columns=mysheet.getRow(0).getLastCellNum();
        String [][] myarrayes =new String[number_of_rows-1][number_of_columns];
        for (int x=1;x<number_of_rows;x++) {
            for (int i = 0; i <number_of_columns; i++) {

                XSSFRow row = mysheet.getRow(x);
                myarrayes[x - 1][i] = row.getCell(i).getStringCellValue();
            }
        }
        return myarrayes;

    }








}
