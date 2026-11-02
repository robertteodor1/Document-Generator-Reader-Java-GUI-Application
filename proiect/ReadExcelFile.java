package ro.tuiasi.ac.proiect;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Această clasă exemplifică modul de citire a datelor dintr-un fișier Excel
 * utilizând biblioteca Apache POI.
 *
 * @author Robert
 */
public class ReadExcelFile {

	/**
	 * Metoda principală care citeste conținutul unui fișier Excel și îl afișează în
	 * consolă.
	 * 
	 * @param args Argumentele liniei de comandă (nu sunt utilizate în acest
	 *             exemplu).
	 */
	public static void main(final String[] args) {
		// Specificati calea către fișierul Excel
		String fileName = "Registru1.xlsx";
		String filePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
				+ File.separator + "java" + File.separator + "ro" + File.separator + "tuiasi" + File.separator + "ac"
				+ File.separator + "proiect" + File.separator + fileName;

		try (FileInputStream fis = new FileInputStream(new File(filePath));
				Workbook workbook = WorkbookFactory.create(fis)) {

			// Obțineți prima foaie de lucru din fisierul Excel
			Sheet sheet = workbook.getSheetAt(0);

			// Iterați prin rândurile foii de lucru
			for (Row row : sheet) {
				// Iterați prin celulele fiecărui rând
				for (Cell cell : row) {
					// Verificați tipul de date al celulei
					// și citiți valoarea corespunzătoare
					switch (cell.getCellType()) {
					case STRING:
						System.out.print(cell.getStringCellValue() + "\t");
						break;
					case NUMERIC:
						System.out.print(cell.getNumericCellValue() + "\t");
						break;
					case BOOLEAN:
						System.out.print(cell.getBooleanCellValue() + "\t");
						break;
					case BLANK:
						System.out.print("Blank Cell\t");
						break;
					default:
						System.out.print("Unknown Cell Type\t");
					}
				}
				System.out.println(); // Săriți la următorul rând
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
