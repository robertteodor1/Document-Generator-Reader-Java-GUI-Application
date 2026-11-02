package ro.tuiasi.ac.proiect;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.JComboBox;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

/**
 * Clasa GenerateWordDocument este responsabilă pentru generarea unui document
 * Word personalizat și deschiderea acestuia.
 *
 *
 * Această clasă permite înlocuirea unor marcaje predefinite dintr-un șablon de
 * document Word cu date specificate, inclusiv numele profesorului, titularului,
 * grupa, materia și laboratorul. De asemenea, clasele studenților sunt citite
 * dintr-un fișier Excel.
 *
 * 
 * Exemplu de utilizare:
 *
 * @author Stefan,Roxana,Robert
 */
public class GenerateWordDocument {
	/**
	 * JComboBox pentru selectarea profesorului.
	 * JComboBox pentru selectarea titularului.
	 * JComboBox pentru selectarea grupei.
	 * JComboBox pentru selectarea materiei.
	 * JComboBox pentru selectarea laboratorului.
	 */
	private JComboBox<String> comboBoxProfesor;
	private JComboBox<String> comboBoxTitular;
	private JComboBox<String> comboBoxGrupa;
	private JComboBox<String> comboBoxMaterii;
	private JComboBox<String> comboBoxLaborator;
	private static final int number = 0;

	/**
	 * Constructorul pentru clasa GenerateWordDocument.
	 * 
	 * @param comboBoxProfesor  ComboBox pentru selectarea profesorului.
	 * @param comboBoxTitular   ComboBox pentru selectarea titularului.
	 * @param comboBoxGrupa     ComboBox pentru selectarea grupei.
	 * @param comboBoxMaterii   ComboBox pentru selectarea materiei.
	 * @param comboBoxLaborator ComboBox pentru selectarea laboratorului.
	 */
	public GenerateWordDocument(JComboBox<String> comboBoxProfesor, JComboBox<String> comboBoxTitular,
			JComboBox<String> comboBoxGrupa, JComboBox<String> comboBoxMaterii, JComboBox<String> comboBoxLaborator) {
		this.comboBoxProfesor = comboBoxProfesor;
		this.comboBoxTitular = comboBoxTitular;
		this.comboBoxGrupa = comboBoxGrupa;
		this.comboBoxMaterii = comboBoxMaterii;
		this.comboBoxLaborator = comboBoxLaborator;
	}

	/**
	 * Metoda generateAndOpenDocument generează un document Word personalizat pe
	 * baza datelor selectate și îl deschide.
	 */
	public void generateAndOpenDocument() {
		try {
			// Obțineți directorul curent
			String currentDirectory = Paths.get("").toAbsolutePath().toString();

			// Setează calea către fișierul șablon Word
			String wordFilePath = Paths.get(currentDirectory, "src", "main", "java", "ro", "tuiasi", "ac", "proiect",
					"FisaProtectiaMuncii_SIAD Pas.docx").toString();
			// Setează calea către fișierul de ieșire Word
			String outputFilePath = Paths
					.get(currentDirectory, "src", "main", "java", "ro", "tuiasi", "ac", "proiect", "ou.docx")
					.toString();
			// Setează calea către fișierul Excel
			String excelFilePath = Paths
					.get(currentDirectory, "src", "main", "java", "ro", "tuiasi", "ac", "proiect", "Registru1.xlsx")
					.toString();

			System.out.println("Calea fișierului Word: " + wordFilePath);

			// Deschide fișierul șablon Word
			FileInputStream fis = new FileInputStream(new File(wordFilePath));
			XWPFDocument document = new XWPFDocument(fis);

			// Obține datele selectate din ComboBox-uri
			String profesor = Objects.requireNonNull(comboBoxProfesor.getSelectedItem()).toString();
			String titular = Objects.requireNonNull(comboBoxTitular.getSelectedItem()).toString();
			String grupa = Objects.requireNonNull(comboBoxGrupa.getSelectedItem()).toString();
			String materie = Objects.requireNonNull(comboBoxMaterii.getSelectedItem()).toString();
			String laborator = Objects.requireNonNull(comboBoxLaborator.getSelectedItem()).toString();

			// Citește studenții din fișierul Excel
			List<String> studenti = citireStudentiDinExcel(excelFilePath, grupa, titular);

			// Înlocuiește marcajele din paragrafe cu datele corespunzătoare
			for (XWPFParagraph paragraph : document.getParagraphs()) {
				for (XWPFRun run : paragraph.getRuns()) {
					String text = run.getText(number);
					if (text != null) {
						if (text.contains("#PROFESOR")) {
							text = text.replace("#PROFESOR", profesor);
							run.setText(text, number);
						}
						if (text.contains("#TITULAR")) {
							text = text.replace("#TITULAR", titular);
							run.setText(text, number);
						}
						if (text.contains("#GRUPA")) {
							text = text.replace("#GRUPA", grupa);
							run.setText(text, number);
						}
						if (text.contains("#MATERIE")) {
							text = text.replace("#MATERIE", materie);
							run.setText(text, number);
						}
						if (text.contains("#LABORATOR")) {
							text = text.replace("#LABORATOR", laborator);
							run.setText(text, number);
						}
					}
				}
			}

			// Înlocuiește marcajele din tabele cu datele corespunzătoare
			for (XWPFTable table : document.getTables()) {
				for (XWPFTableRow row : table.getRows()) {
					for (XWPFTableCell cell : row.getTableCells()) {
						for (XWPFParagraph paragraph : cell.getParagraphs()) {
							for (XWPFRun run : paragraph.getRuns()) {
								String text = run.getText(number);
								if (text != null && text.contains("#STUDENT")) {
									// Alegeți un singur student din lista studenti
									String student = studenti.isEmpty() ? "" : studenti.remove(number);
									// Înlocuiți doar #STUDENT cu numele studentului ales
									text = text.replace("#STUDENT", student);
									run.setText(text, number);
								}
							}
						}
					}
				}
			}

			// Scrie documentul modificat în fișierul de ieșire
			FileOutputStream fos = new FileOutputStream(new File(outputFilePath));
			document.write(fos);
			fos.close();

			// Deschide documentul de ieșire
			Desktop.getDesktop().open(new File(outputFilePath));
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metoda citireStudentiDinExcel citește numele studenților dintr-un fișier
	 * Excel pe baza grupei și a profesorului specificat.
	 * 
	 * @param excelFilePath Calea către fișierul Excel care trebuie citit.
	 * @param grupa         Grupul pentru care se filtrează studenții.
	 * @param profesor      Profesorul pentru care se filtrează studenții.
	 * @return O listă de nume de studenți care aparțin grupei și profesorului
	 *         specificat.
	 */
	private List<String> citireStudentiDinExcel(String excelFilePath, String grupa, String profesor) {
		final int indexProfesor5 = 5;
		final int indexProfesor8 = 8;
		final int indexProfesor11 = 11;
		List<String> studenti = new ArrayList<>();
		try (FileInputStream fis = new FileInputStream(new File(excelFilePath));
				Workbook workbook = new XSSFWorkbook(fis)) {
			org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(number);
			for (Row row : sheet) {
				Cell grupaCell = row.getCell(2);
				Cell studentCell = row.getCell(1);

				// Profesorii titulari sunt pe coloanele 5, 8 și 11
				Cell profesorCell5 = row.getCell(indexProfesor5);
				Cell profesorCell8 = row.getCell(indexProfesor8);
				Cell profesorCell11 = row.getCell(indexProfesor11);

				if (grupaCell != null && studentCell != null
						&& (profesorCell5 != null || profesorCell8 != null || profesorCell11 != null)) {
					String grupaValue = grupaCell.getStringCellValue();
					String studentValue = studentCell.getStringCellValue();

					// Verificați fiecare celulă a profesorului și adăugați studentul doar dacă
					// profesorul corespunde
					if (grupa.equals(grupaValue) && ((profesorCell5 != null
							&& profesor.equals(profesorCell5.getStringCellValue()))
							|| (profesorCell8 != null && profesor.equals(profesorCell8.getStringCellValue()))
							|| (profesorCell11 != null && profesor.equals(profesorCell11.getStringCellValue())))) {
						studenti.add(studentValue);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return studenti;
	}

}
