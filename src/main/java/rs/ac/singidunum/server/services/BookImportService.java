package rs.ac.singidunum.server.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.singidunum.server.entities.Book;
import rs.ac.singidunum.server.repositories.BookRepository;

@Service
public class BookImportService {

	@Autowired
	BookRepository bookRepository;

	private Object getCellValue(Cell cell) {
		switch (cell.getCellType()) {
		case STRING:
			return cell.getStringCellValue();

		case BOOLEAN:
			return cell.getBooleanCellValue();

		case NUMERIC:
			return cell.getNumericCellValue();
		}

		return null;
	}

	private Workbook getWorkbook(FileInputStream inputStream, String excelFilePath) throws IOException {
		Workbook workbook = null;

		if (excelFilePath.endsWith("xlsx")) {
			workbook = new XSSFWorkbook(inputStream);
		} else if (excelFilePath.endsWith("xls")) {
			workbook = new HSSFWorkbook(inputStream);
		} else {
			throw new IllegalArgumentException("The specified file is not Excel file");
		}

		return workbook;
	}

	public void readBooksFromExcelFile(String excelFilePath) throws IOException {

		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));

		Workbook workbook = getWorkbook(inputStream, excelFilePath);
		Sheet firstSheet = workbook.getSheetAt(0);
		Iterator<Row> iterator = firstSheet.iterator();
		iterator.next();

		while (iterator.hasNext()) {
			Row nextRow = iterator.next();
			Iterator<Cell> cellIterator = nextRow.cellIterator();
			Book book = new Book();
			while (cellIterator.hasNext()) {
				Cell nextCell = cellIterator.next();
				int columnIndex = nextCell.getColumnIndex();
				switch (columnIndex) {
				case 0:
					book.setTitle((String) getCellValue(nextCell));
					break;
				case 1:
					book.setAuthor((String) getCellValue(nextCell));
					break;
				case 2:
					book.setPrice((double) getCellValue(nextCell));
					break;
				case 3:
					Date date = DateUtil.getJavaDate((double) getCellValue(nextCell));
					book.setPublicationDate(date);
					break;

				}

			}
			bookRepository.save(book);
		}

		workbook.close();
		inputStream.close();

	}

}