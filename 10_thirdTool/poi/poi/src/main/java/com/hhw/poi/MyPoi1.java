package com.hhw.poi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.ss.usermodel.Workbook;

public class MyPoi1
{
	public void testPoi2() throws IOException
	{
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet s = wb.createSheet("123");
		
		HSSFRow row0= s.createRow(0);
		
		HSSFCell cell = null;
		
		cell=row0.createCell(0);
		cell.setCellValue("����");
		
		FileOutputStream fos=new FileOutputStream(new File("E://2.xls"));
		
		wb.write(fos);
	}
	
	public void testPoi1() throws IOException
	{

		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet s = wb.createSheet();
		HSSFCellStyle cellStyle00 = wb.createCellStyle();// ���ݷ��
		cellStyle00.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle00.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// cellStyle00.setBorderBottom((short)1);
		// cellStyle00.setBorderLeft((short)1);
		// cellStyle00.setBorderRight((short)1);
		// cellStyle00.setBorderTop((short)1);

		HSSFCellStyle cellStyle01 = wb.createCellStyle();// �ֶη��,��ɫ��ɫ�����
		cellStyle01.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle01.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		HSSFFont font01 = wb.createFont();
		font01.setColor(HSSFColor.WHITE.index);
		// cellStyle01.setBorderBottom((short)1);
		// cellStyle01.setBorderLeft((short)1);
		// cellStyle01.setBorderRight((short)1);
		// cellStyle01.setBorderTop((short)1);
		cellStyle01.setFillForegroundColor(HSSFColor.LIGHT_BLUE.index);
		cellStyle01.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		cellStyle01.setFont(font01);

		HSSFCellStyle cellStyle02 = wb.createCellStyle();// ����ķ��
		cellStyle02.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle02.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		HSSFFont font02 = wb.createFont();
		font02.setFontName("����");
		font02.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// �Ӵ�
		font02.setFontHeight((short) 300);
		cellStyle02.setFont(font02);

		HSSFRow rowtitle = s.createRow(0);// ����
		HSSFCell celltitle = rowtitle.createCell((short) 0);
		celltitle.setCellValue(new HSSFRichTextString("�������۱���"));
		celltitle.setCellStyle(cellStyle02);
		s.addMergedRegion(new Region(0, (short) 0, 0, (short) 22));// ָ���ϲ�����
		s.setGridsPrinted(true);

		HSSFRow rowline2 = s.createRow(1);
		HSSFCell cellline2 = rowline2.createCell((short) 0);
		cellline2.setCellValue(new HSSFRichTextString("ͳ�����ڣ�"));
		s.setGridsPrinted(true);
		HSSFCell cellline22 = rowline2.createCell((short) 1);
		s.addMergedRegion(new Region(1, (short) 1, 1, (short) 7));// ָ���ϲ�����
		s.setGridsPrinted(true);

		HSSFRow row = s.createRow(2);
		HSSFCell cell = null;

		cell = row.createCell((short) 0);
		cell.setCellValue(new HSSFRichTextString("������"));// NC
		cell.setCellStyle(cellStyle01);

		cell = row.createCell((short) 1);
		cell.setCellValue(new HSSFRichTextString("����״̬"));// F
		cell.setCellStyle(cellStyle01);

		cell = row.createCell((short) 2);
		cell.setCellValue(new HSSFRichTextString("��������"));// T
		cell.setCellStyle(cellStyle01);

		cell = row.createCell((short) 3);
		cell.setCellValue(new HSSFRichTextString("��������"));
		cell.setCellStyle(cellStyle01);

		cell = row.createCell((short) 4);
		cell.setCellValue(new HSSFRichTextString("���չ�˾"));
		cell.setCellStyle(cellStyle01);

		cell = row.createCell((short) 5);
		cell.setCellValue(new HSSFRichTextString("����������"));
		cell.setCellStyle(cellStyle01);

		cell = row.createCell((short) 6);
		cell.setCellValue(new HSSFRichTextString("�Ա�"));
		cell.setCellStyle(cellStyle01);

		cell = row.createCell((short) 7);
		cell.setCellValue(new HSSFRichTextString("֤������"));
		cell.setCellStyle(cellStyle01);

		cell = row.createCell((short) 8);
		cell.setCellValue(new HSSFRichTextString("��������"));
		cell.setCellStyle(cellStyle01);

		cell = row.createCell((short) 9);
		cell.setCellValue(new HSSFRichTextString("���շ���"));
		cell.setCellStyle(cellStyle01);

		cell = row.createCell((short) 10);
		cell.setCellValue(new HSSFRichTextString("���յ���"));
		cell.setCellStyle(cellStyle01);

		cell = row.createCell((short) 11);
		cell.setCellValue(new HSSFRichTextString("���յ���"));
		cell.setCellStyle(cellStyle01);

		cell = row.createCell((short) 12);
		cell.setCellValue(new HSSFRichTextString("�����"));
		cell.setCellStyle(cellStyle01);

		cell = row.createCell((short) 13);
		cell.setCellValue(new HSSFRichTextString("��������"));
		cell.setCellStyle(cellStyle01);

		cell = row.createCell((short) 14);
		cell.setCellValue(new HSSFRichTextString("Ͷ������"));
		cell.setCellStyle(cellStyle01);

		cell = row.createCell((short) 15);
		cell.setCellValue(new HSSFRichTextString("�����ڼ�"));
		cell.setCellStyle(cellStyle01);

		cell = row.createCell((short) 16);
		cell.setCellValue(new HSSFRichTextString("������Чʱ��"));
		cell.setCellStyle(cellStyle01);

		cell = row.createCell((short) 17);
		cell.setCellValue(new HSSFRichTextString("�ܱ���"));
		cell.setCellStyle(cellStyle01);

		cell = row.createCell((short) 18);
		cell.setCellValue(new HSSFRichTextString("֧����ʽ"));
		cell.setCellStyle(cellStyle01);

		cell = row.createCell((short) 19);
		cell.setCellValue(new HSSFRichTextString("֧������"));
		cell.setCellStyle(cellStyle01);

		cell = row.createCell((short) 20);
		cell.setCellValue(new HSSFRichTextString("֧����ˮ��"));
		cell.setCellStyle(cellStyle01);

		cell = row.createCell((short) 21);
		cell.setCellValue(new HSSFRichTextString("֧��״̬"));
		cell.setCellStyle(cellStyle01);

		cell = row.createCell((short) 22);
		cell.setCellValue(new HSSFRichTextString("��ע"));
		cell.setCellStyle(cellStyle01);

		FileOutputStream fos=new FileOutputStream(new File("E://3.xls"));
		
		wb.write(fos);
		
	}
}
