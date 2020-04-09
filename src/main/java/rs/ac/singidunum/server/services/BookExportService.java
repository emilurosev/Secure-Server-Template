package rs.ac.singidunum.server.services;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.singidunum.server.entities.Book;
import rs.ac.singidunum.server.repositories.BookRepository;

@Service
public class BookExportService {

	@Autowired
	BookRepository bookRepository;

	private Workbook getWorkbook(String excelFilePath) throws IOException {
		Workbook workbook = null;

		if (excelFilePath.endsWith("xlsx")) {
			workbook = new XSSFWorkbook();
		} else if (excelFilePath.endsWith("xls")) {
			workbook = new HSSFWorkbook();
		} else {
			throw new IllegalArgumentException("The specified file is not Excel file");
		}

		return workbook;
	}

	private void writeBook(Book book, Row row) {
		Cell cell = row.createCell(1);
		cell.setCellValue(book.getTitle());

		cell = row.createCell(2);
		cell.setCellValue(book.getAuthor());

		cell = row.createCell(3);
		cell.setCellValue(book.getPrice());

		cell = row.createCell(4);
		cell.setCellValue(book.getPublicationDate());
	}

	private void createHeaderRow(Sheet sheet) {

		CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
		Font font = sheet.getWorkbook().createFont();
		font.setBold(true);
		font.setFontHeightInPoints((short) 10);
		cellStyle.setFont(font);

		Row row = sheet.createRow(0);
		Cell cellTitle = row.createCell(1);

		cellTitle.setCellStyle(cellStyle);
		cellTitle.setCellValue("Title");

		Cell cellAuthor = row.createCell(2);
		cellAuthor.setCellStyle(cellStyle);
		cellAuthor.setCellValue("Author");

		Cell cellPrice = row.createCell(3);
		cellPrice.setCellStyle(cellStyle);
		cellPrice.setCellValue("Price");

		Cell cellPublicationDate = row.createCell(4);
		cellPublicationDate.setCellStyle(cellStyle);
		cellPublicationDate.setCellValue("Publication date");
	}

	private void writeExcel(List<Book> listBook, String excelFilePath) throws IOException {
		Workbook workbook = getWorkbook(excelFilePath);

		Sheet sheet = workbook.createSheet();
		
		createHeaderRow(sheet);

		int rowCount = 0;

		for (Book aBook : listBook) {
			Row row = sheet.createRow(++rowCount);
			writeBook(aBook, row);
		}

		try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
			workbook.write(outputStream);
		}
	}

	public void export() {
		List<Book> listBook = bookRepository.findAll();
		String excelFilePath = "ExportedBooks.xls";
		try {
			writeExcel(listBook, excelFilePath);
			System.out.println("Exported");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}