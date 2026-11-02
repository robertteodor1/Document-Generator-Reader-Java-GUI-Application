package ro.tuiasi.ac.proiect;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Această clasă exemplifică modul de citire a conținutului dintr-un document
 * Word utilizând biblioteca Apache POI.
 * 
 * @author Roxana
 */
public class ReadWordDocument {

	/**
	 * Metoda principală care citeste conținutul unui document Word și îl
	 * afișează în consolă.
	 * 
	 * @param args Argumentele liniei de comandă (nu sunt utilizate în acest
	 *             exemplu).
	 */
	public static void main(final String[] args) {
		// Specificați calea către fișierul Word
		String fileName = "FisaProtectiaMuncii_SIAD Pas.doc";
		String filePath = System.getProperty("user.dir")
				+ File.separator + "src" + File.separator + "main"
				+ File.separator + "java" + File.separator + "ro"
				+ File.separator + "tuiasi" + File.separator + "ac"
				+ File.separator + "proiect" + File.separator + fileName;

		StringBuilder contentBuilder = new StringBuilder(); // Creează un obiect StringBuilder pentru a
															// stoca conținutul textului

		try (FileInputStream fis = new FileInputStream(new File(filePath))) {
			HWPFDocument document = new HWPFDocument(fis);
			WordExtractor extractor = new WordExtractor(document);
			String[] paragraphs = extractor.getParagraphText();

			for (String paragraph : paragraphs) {
				contentBuilder.append(paragraph).append("\n"); // Adaugă fiecare paragraf
			}												   // la obiectul StringBuilder
			DocumentContentHolder.setDocumentContent(contentBuilder.toString()); // Setează conținutul în
																					// DocumentContentHolder
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Obțineți conținutul textului sub formă de șir de caractere
		String documentContent = contentBuilder.toString();

		// Puteți returna sau utiliza documentContent 
		// în altă parte a codului, de exemplu
		// în clasa de generare a documentului Word
		System.out.println(documentContent); // Afișează conținutul textului în consolă
	}
}