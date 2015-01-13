package com.hhw.poi;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-4-9
 * Time: 下午3:48
 * To change this template use File | Settings | File Templates.
 */
public class MyPoiTest {
    public static void main(String[] args) {
        readPoi();
        read(5, 6);
    }

    /**
     * 读取一个excel的内容
     */
    public static void readPoi() {
        try {
            POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream("F:\\工作\\1_Java资料\\5_workspace\\hhw\\poi\\src\\com\\hhw\\poi\\temp1.xls"));

            HSSFWorkbook wb = new HSSFWorkbook(fs);

            HSSFSheet sheet = wb.getSheetAt(0);

            System.out.println(sheet.getRow(4).getCell(0).getStringCellValue());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成一个excel文件
     */
    public static void writePoi()
    {

    }

    public static void read(int rowxi, int rowxj) {
        try {
            POIFSFileSystem fs;
            fs = new POIFSFileSystem(new FileInputStream("F:\\工作\\1_Java资料\\5_workspace\\hhw\\poi\\src\\com\\hhw\\poi\\temp1.xls"));

            HSSFWorkbook wb = new HSSFWorkbook(fs);

            HSSFSheet sheet = wb.getSheetAt(0);

            for (int i = rowxi - 1; i < rowxj; i++) {
                String name = sheet.getRow(i).getCell(0).getStringCellValue();
                String bankName = name.substring(0, name.indexOf("(")-1);
                String bankNo = name.substring(name.indexOf("(")+1, name.indexOf(")"));
                sheet.getRow(i).getCell(13).setCellType(Cell.CELL_TYPE_STRING);
                String bin = sheet.getRow(i).getCell(13).getStringCellValue();
                String cardType = sheet.getRow(i).getCell(15).getStringCellValue();
                System.out.println(bankName+","+bankNo+","+bin+","+cardType);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
