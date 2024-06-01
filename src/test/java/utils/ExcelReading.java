package utils;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExcelReading {

    static Workbook book;
    static Sheet sheet;
    //открыть книгу эксель
    public static void openExcel(String filePath){
        try {
            FileInputStream fis = new FileInputStream(filePath);
             book = new XSSFWorkbook(fis);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // открыть лист
    public static void getSheet(String sheetName){
        sheet = book.getSheet(sheetName);

    }
    //получить общее колличество строк
    public static int getRowCount(){
        return sheet.getPhysicalNumberOfRows();
    }
    //получить общее колличество столбцов
    public static int getColsCount(int rowIndex){
        return sheet.getRow(rowIndex).getPhysicalNumberOfCells();
    }
    //преобразует данные в ячйке в строку
    public static String getCellData(int rowIndex, int collIndex){
        return sheet.getRow(rowIndex).getCell(collIndex).toString();
    }

    public static List<Map<String, String>> excelIntoListMap(String filePath, String sheetName){
        openExcel(filePath);
        getSheet(sheetName);

        List<Map<String,String>> listData = new ArrayList<>();
        //внешний цикл
        for (int row = 1; row <getRowCount() ; row++) {
            //создать карту для каждой строки
            Map<String,String> map = new LinkedHashMap<>();
            //просмотреть значения всех ячеек
            for (int col = 0; col < getColsCount(row); col++) {

                map.put(getCellData(0,col), getCellData(row,col));

            }
            //добавим карту в список
            listData.add(map);
        }
        return listData;
    }
}
