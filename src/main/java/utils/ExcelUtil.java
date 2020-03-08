package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//������Ҫʵ��Excel�ļ�����
public class ExcelUtil {

	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;
	private static XSSFRow Row;
	private static String Path_ExcelFile = System.getProperty("user.dir")
			+ "\\src\\main\\resources\\testdata\\testData.xlsx";
	private static FileInputStream ExcelFile;
	private static Map<String, String> dataMap;
	
	public static Map<String, String> getTestData(String SheetName) {


	
			try {
				dataMap=new HashMap<String, String>();
				// ʵ����Excel�ļ���FileInputStream�Ķ���
				ExcelFile = new FileInputStream(Path_ExcelFile);
				// ʵ����Excel�ļ���XSSFworkbook�ļ�����
				ExcelWBook = new XSSFWorkbook(ExcelFile);
				// ʵ����XSSFSheet����ָ��Excel�ļ���sheet���ƣ���������Sheet���С��к͵�Ԫ��Ĳ���
				ExcelWSheet = ExcelWBook.getSheet(SheetName);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			for (int i = 1; i < ExcelWSheet.getPhysicalNumberOfRows(); i++) {
				String key = ExcelWSheet.getRow(i).getCell(0).getStringCellValue();
				String value = ExcelWSheet.getRow(i).getCell(1).getStringCellValue();
				dataMap.put(key, value);
				
			}
	

		return dataMap;
	}
	
	// �趨Ҫ������Excel�ļ�·����Excel�ļ��е�sheet����
	// �ڶ�/д�ļ���ʱ�򣬾���Ҫ�ȵ��ô˷������趨Ҫ������Excel�ļ�·����Excel�ļ��е�sheet����
	public static void setExcelFile(String Path, String SheetName) throws Exception {

		FileInputStream ExcelFile;
		try {
			// ʵ����Excel�ļ���FileInputStream�Ķ���
			ExcelFile = new FileInputStream(Path);
			// ʵ����Excel�ļ���XSSFworkbook�ļ�����
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			// ʵ����XSSFSheet����ָ��Excel�ļ���sheet���ƣ���������Sheet���С��к͵�Ԫ��Ĳ���
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw (e);
		}

	}

	// �趨Ҫ������Excel�ļ�·�����ڶ�дexcel�ļ���ʱ����Ҫ���趨Ҫ������Excel�ļ�·��
	public static void setExcelFile(String Path) {
		FileInputStream ExcelFile;

		try {
			// ʵ����excel��FileInputStream����
			ExcelFile = new FileInputStream(Path);
			// ʵ����excel��XSSFWorkbook����
			ExcelWBook = new XSSFWorkbook(ExcelFile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("excel·���趨ʧ��");
			e.printStackTrace();
		}

	}

	
 
	// ��ȡExcel�ļ������һ�е��к�
	public static int getLastRowNum() {
		// ��������Sheet�����һ�е��к�
		return ExcelWSheet.getLastRowNum();
	}

	// ��ȡָ��sheet�е������ܵ�����
	public static int getRowCount(String SheetName) {
		ExcelWSheet = ExcelWBook.getSheet(SheetName);
		// ��ȡ�����������⣩���ݵ����һ���к�
		int number = ExcelWSheet.getLastRowNum();
		return number;

	}

	// ��ȡָ��sheet�е�ָ����Ԫ�������˺���ֻ֧����չ��Ϊ.xlsx��excel�ļ�
	public static String getCellData(String SheetName, int RowNum, int ColNum) {
		ExcelWSheet = ExcelWBook.getSheet(SheetName);
		try {
			// ͨ����������ָ����Ԫ����кź��кţ���ȡָ���ĵ�Ԫ�����
			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			/*
			 * �����Ԫ�������Ϊ�ַ������ͣ���ʹ��getStringCellValue �����Ԫ�������Ϊ���������ͣ���ʹ��getNumericCellValue
			 * ͨ��java����Ԫ�������ȡ�ĵ�Ԫ�����������Ƿ����ַ������ǡ�ֱ�ӷ����ַ�����������������ת�����ַ��������ַ�����
			 * Cell.getCellType()�� XSSFCell.CELL_TYPE_STRING��������߱�ʾ������ʹ�õķ���(������ʱ)
			 */

			String CellData = Cell.getCellType() == XSSFCell.CELL_TYPE_STRING ? Cell.getStringCellValue()
					: String.valueOf(Math.round(Cell.getNumericCellValue()));
			return CellData;
		} catch (Exception e) {
			e.printStackTrace();
			// ��ȡ����򷵻ؿ��ַ���
			return "������";
		}
	}

	// ��Excel�ļ���ִ�е�Ԫ����д�����ݣ��˺���ֻ֧����չ��Ϊxlsx��excel�ļ�д��
	public static void setCellData(String SheetName, int RowNum, int ColNum, String Result) {
		ExcelWSheet = ExcelWBook.getSheet(SheetName);
		try {
			// ��ȡexcel�ļ��е��ж���
			Row = ExcelWSheet.getRow(RowNum);
			// �����Ԫ��Ϊ�գ��򷵻�null
			Cell = Row.getCell(ColNum);
			if (Cell == null) {
				// ����Ԫ����null,������Ԫ��
				// �����Ԫ��Ϊ�գ��޷�ֱ�ӵ��õ�Ԫ������setCellValue�����趨��Ԫ���ֵ
				Cell = Row.createCell(ColNum);
				// ������Ԫ�������Ե��õ�Ԫ������setCellValue�����趨��Ԫ���ֵ
				Cell.setCellValue(Result);
			} else {
				// ��Ԫ���������ݣ�����ֱ�ӵ��õ�Ԫ�����setCellValue�����趨��Ԫ���ֵ
				Cell.setCellValue(Result);
			}
			// ʵ����д��excel�ļ����ļ����������
			FileOutputStream fileOut = new FileOutputStream(Path_ExcelFile);
			// ������д��excel�ļ���
			ExcelWBook.write(fileOut);
			// ����flsuh����ǿ��ˢ��д���ļ�
			fileOut.flush();
			// �ر��ļ����������
			fileOut.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
