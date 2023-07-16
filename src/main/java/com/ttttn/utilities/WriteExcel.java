package com.ttttn.utilities;


import java.awt.print.Book;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.ttttn.dto.DonHang;


import  org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;

@Component
public class WriteExcel {

  public static final int COLUMN_INDEX_MADH =0;
  public static final int COLUMN_INDEX_TENKH =1;
  public static final int COLUMN_INDEX_NGAYDAT =2;
//  public static final int COLUMN_INDEX_SOLUONG =3;
  public static final int COLUMN_INDEX_TRANGTHAI =3;
  public static final int COLUMN_INDEX_TONGTIEN =4;

  private static CellStyle cellStyleFormatNumber =null;
  
  final String excelFilePath ="/Users/thang/dev/projects/duantotnghiep/src/listdonhang/donhang.xlsx";
  
  //create workbook
  private static Workbook getWorkbook(String excelFilePath) throws IOException{
    Workbook workbook =null;
    if(excelFilePath.endsWith("xlsx")) {
      workbook = new XSSFWorkbook();
    }else if(excelFilePath.endsWith("xls")) {
      
    }else {
      throw new IllegalArgumentException("loi file excel truyen vo");
    }
    return workbook;
  }
  
  // create Cellstyle for header
  private static CellStyle createStyleForHeader(Sheet sheet) {
    //create font
   Font font = sheet.getWorkbook().createFont();
    font.setFontName("Times New Roman");
    font.setBold(true);
    font.setFontHeightInPoints((short) 14); // font size
    font.setColor(IndexedColors.WHITE.getIndex()); //text color
    // Create Cellstyle
    CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
    cellStyle.setFont(font);
    cellStyle.setFillForegroundColor(IndexedColors.GOLD.getIndex());
    cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    cellStyle.setBorderBottom(BorderStyle.THIN);
    return cellStyle;
    
  }
  
  //write footer
  private static void writeFooter(Sheet sheet, int rowIndex) {
    //Create row
    Row row = sheet.createRow(rowIndex);
    Cell cell = row.createCell(COLUMN_INDEX_TONGTIEN,CellType.FORMULA);
    cell.setCellFormula("SUM(E2:E"+rowIndex+")");
  }
  
  //Auto resize column width
  private static void autosizeColumn(Sheet sheet,int lastColumn) {
    for(int columnIndex = 0; columnIndex <lastColumn;columnIndex++) {
      sheet.autoSizeColumn(columnIndex);
    }
  }
  
  // create output file 
  private static void createOutputFile(Workbook workbook, String excelFilePath) throws IOException{
   try(OutputStream os = new FileOutputStream(excelFilePath)){
     workbook.write(os);
   }
  }
  
  // write header with format
  private static void writeHeader(Sheet sheet, int rowIndex) {
    //create CellStyle
    CellStyle cellStyle = createStyleForHeader(sheet);
    
    // Create row
    Row row = sheet.createRow(rowIndex);
    
    //create cells
    Cell cell = row.createCell(COLUMN_INDEX_MADH);
    cell.setCellStyle(cellStyle);
    cell.setCellValue("Ma Don Hang");
    
    cell = row.createCell(COLUMN_INDEX_TENKH);
    cell.setCellStyle(cellStyle);
    cell.setCellValue("Ten Khach Hang");
    
    cell = row.createCell(COLUMN_INDEX_NGAYDAT);
    cell.setCellStyle(cellStyle);
    cell.setCellValue("Ngay Dat");
    
//    cell = row.createCell(COLUMN_INDEX_SOLUONG);
//    cell.setCellStyle(cellStyle);
//    cell.setCellValue("So Luong  SP");
    
    cell = row.createCell(COLUMN_INDEX_TRANGTHAI);
    cell.setCellStyle(cellStyle);
    cell.setCellValue("Trang Thai");
    
    cell = row.createCell(COLUMN_INDEX_TONGTIEN);
    cell.setCellStyle(cellStyle);
    cell.setCellValue("Tong Tien");
  }
  
  //wirte data 
  private static void writeBook(DonHang donhang, Row row) {
    if(cellStyleFormatNumber == null) {
      //Format number 
      short  format =(short)BuiltinFormats.getBuiltinFormat("#,##0");
      // create CellStyle
      Workbook workbook = row.getSheet().getWorkbook();
      cellStyleFormatNumber = workbook.createCellStyle();
      cellStyleFormatNumber.setDataFormat(format);
    }
    Cell cell = row.createCell(COLUMN_INDEX_MADH);
    cell.setCellValue(donhang.getMaDonHang());
    
    cell = row.createCell(COLUMN_INDEX_TENKH);
    cell.setCellValue(donhang.getTenKhachHang());
    
    cell = row.createCell(COLUMN_INDEX_NGAYDAT);
    cell.setCellValue(donhang.getNgayDat());
    
//    cell = row.createCell(COLUMN_INDEX_SOLUONG);
//    cell.setCellValue(donhang.getSoLuongSP());
    
    cell = row.createCell(COLUMN_INDEX_TRANGTHAI);
    cell.setCellValue(donhang.getTrangThai());
    
    cell = row.createCell(COLUMN_INDEX_TONGTIEN);
    cell.setCellValue(donhang.getTongTien());
    
    //create cell formula
    //totallmoney =
  }
  public static void writeExcel(List<DonHang> donhangs,String excelFilePath) throws IOException {
    //create workbook
    Workbook workbook = getWorkbook(excelFilePath);
    
    // create sheet
    Sheet sheet = workbook.createSheet("Donhang");
    int rowIndex =0;
    //write header
    writeHeader(sheet,rowIndex);
    
    //Write data
    rowIndex++;
    for(DonHang donhang :donhangs) {
      //create row
      Row row = sheet.createRow(rowIndex);
      //write data on row
      writeBook(donhang,row);
      rowIndex++;
    }
    
    // write footer
    writeFooter(sheet, rowIndex);
    
    // Auto resize column witdth
    int numberOfColumn = sheet.getRow(0).getPhysicalNumberOfCells();
    autosizeColumn(sheet, numberOfColumn);
    
    // create file excel
    createOutputFile(workbook, excelFilePath);
    System.out.println("DOne!!!");
  }
}
