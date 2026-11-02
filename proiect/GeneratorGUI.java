package ro.tuiasi.ac.proiect;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Clasa GeneratorGUI reprezintă o interfață grafică pentru generarea fișelor
 * pentru protecția muncii.
 * 
 * @author Roxana,Robert,Stefan
 */
public class GeneratorGUI extends JFrame {
	private JComboBox<String> comboBoxProfesor, comboBoxTitular, comboBoxAsistent, comboBoxGrupa, comboBoxMaterii,
			comboBoxLaborator;
	private JButton btnGenerare;
	private static final int indexBackground1 = 30;
	private static final int indexBackground2 = 40;
	private static final int indexBorder = 60;
	private static final int indexBorder2 = 20;
	private static final int indexFont = 14;
	private static final int indexGrid = 5;
	private static final int nr = 0;
	private static final int backgroundBtn1 = 255;
	private static final int backgroundBtn2 = 69;
	private static final int btnSize = 120;
	private static final int nr1 = 1;

	/**
	 * Constructorul clasei GeneratorGUI initializează și afișează interfața
	 * grafică.
	 */
	public GeneratorGUI() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); // Setăm Look and Feel-ul sistemului
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Setările ferestrei principale
		setTitle("Generator de fișe pentru protecția muncii");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout()); // Utilizăm un BorderLayout pentru a organiza componentele
		getContentPane().setBackground(new Color(indexBackground1, indexBackground1, indexBackground1)); // Setăm o
																											// culoare
																											// de fundal
																											// întunecată

		/**
		 * Inițializează meniurile dropdown și butonul de generare. comboBoxProfesor:
		 * Meniul dropdown pentru selectarea profesorului. comboBoxTitular: Meniul
		 * dropdown pentru selectarea titularului. comboBoxGrupa: Meniul dropdown pentru
		 * selectarea grupei. comboBoxMaterii: Meniul dropdown pentru selectarea
		 * materiei. comboBoxLaborator: Meniul dropdown pentru selectarea laboratorului.
		 * btnGenerare: Butonul utilizat pentru a declanșa generarea datelor.
		 */
		comboBoxProfesor = new JComboBox<>();
		comboBoxTitular = new JComboBox<>();
		comboBoxGrupa = new JComboBox<>();
		comboBoxMaterii = new JComboBox<>();
		comboBoxLaborator = new JComboBox<>();
		btnGenerare = new JButton("Generare");

		// Stilizarea meniurilor dropdown
		Font font = new Font("Arial", Font.PLAIN, indexFont); // Stilul fontului
		comboBoxProfesor.setFont(font);
		comboBoxTitular.setFont(font);
		comboBoxGrupa.setFont(font);
		comboBoxMaterii.setFont(font);
		comboBoxLaborator.setFont(font);
		comboBoxTitular.setBackground(new Color(indexBackground2, indexBackground2, indexBackground2));
		comboBoxGrupa.setBackground(new Color(indexBackground2, indexBackground2, indexBackground2));
		comboBoxMaterii.setBackground(new Color(indexBackground2, indexBackground2, indexBackground2));
		comboBoxLaborator.setBackground(new Color(indexBackground2, indexBackground2, indexBackground2));
		comboBoxTitular.setForeground(Color.BLACK);
		comboBoxGrupa.setForeground(Color.BLACK);
		comboBoxMaterii.setForeground(Color.BLACK);
		comboBoxLaborator.setForeground(Color.BLACK);
		comboBoxTitular
				.setBorder(BorderFactory.createLineBorder(new Color(indexBorder, indexBorder, indexBorder), nr1));
		comboBoxGrupa.setBorder(BorderFactory.createLineBorder(new Color(indexBorder, indexBorder, indexBorder), nr1));
		comboBoxMaterii
				.setBorder(BorderFactory.createLineBorder(new Color(indexBorder, indexBorder, indexBorder), nr1));
		comboBoxLaborator
				.setBorder(BorderFactory.createLineBorder(new Color(indexBorder, indexBorder, indexBorder), nr1));

		// Stilizarea butonului de generare
		btnGenerare.setBackground(new Color(backgroundBtn1, backgroundBtn2, nr)); // Setăm o culoare de fundal
																					// portocalie
		btnGenerare.setForeground(Color.BLACK); // Setăm culoarea textului butonului
		btnGenerare.setFocusPainted(false); // Eliminăm conturul butonului la focus
		btnGenerare.setPreferredSize(new Dimension(btnSize, indexBackground2)); // Setăm dimensiunile butonului
		btnGenerare.setFont(new Font("Arial", Font.BOLD, indexFont)); // Setăm fontul și stilul textului butonului
		btnGenerare.setBorder(BorderFactory.createLineBorder(new Color(indexBorder, indexBorder, indexBorder), 1));

		// Crearea panourilor pentru meniurile dropdown și butonul de generare
		JPanel dropdownPanel = new JPanel(new GridLayout(indexGrid, nr1, indexGrid, indexGrid)); // Un GridLayout pentru
																									// meniurile
																									// dropdown
		dropdownPanel
				.setBorder(BorderFactory.createEmptyBorder(indexBorder2, indexBorder2, indexBorder2, indexBorder2)); // Adăugăm
																														// spațiu
																														// între
																														// margini
		dropdownPanel.setBackground(new Color(indexBackground1, indexBackground1, indexBackground1)); // Setăm culoarea
																										// de fundal

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Un FlowLayout pentru butonul de generare
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(indexBorder2, indexBorder2, indexBorder2, indexBorder2)); // Adăugăm
																														// spațiu
																														// între
																														// margini
		buttonPanel.setBackground(new Color(indexBackground1, indexBackground1, indexBackground1)); // Setăm culoarea de
																									// fundal

		// Adăugarea meniurilor dropdown la panoul corespunzător
		dropdownPanel.add(createLabeledPanel("Profesor:", comboBoxProfesor));
		dropdownPanel.add(createLabeledPanel("Titular:", comboBoxTitular));
		dropdownPanel.add(createLabeledPanel("Grupa:", comboBoxGrupa));
		dropdownPanel.add(createLabeledPanel("Materie:", comboBoxMaterii));
		dropdownPanel.add(createLabeledPanel("Laborator:", comboBoxLaborator));

		// Adăugarea butonului de generare la panoul corespunzător
		buttonPanel.add(btnGenerare);

		// Adăugarea panourilor la fereastra principală
		add(dropdownPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);

		// Citirea profesorilor din fișierul Excel

		String fileName = "Registru1.xlsx";
		String filePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
				+ File.separator + "java" + File.separator + "ro" + File.separator + "tuiasi" + File.separator + "ac"
				+ File.separator + "proiect" + File.separator + fileName;
		;
		List<String> profesori = citesteProfesoriDinExcel(filePath);
		for (String profesor : profesori) {
			comboBoxProfesor.addItem(profesor);
		}
		List<String> titulari = citesteTitular(filePath);
		for (String titular : titulari) {
			comboBoxTitular.addItem(titular);
		}
		List<String> grupa = citesteGrupa(filePath);
		for (String grupa1 : grupa) {
			comboBoxGrupa.addItem(grupa1);
		}
		List<String> materie = citesteMaterie(filePath);
		for (String materii : materie) {
			comboBoxMaterii.addItem(materii);
		}
		List<String> laborator = citesteLaborator(filePath);
		for (String lab : laborator) {
			comboBoxLaborator.addItem(lab);
		}
		pack(); // Face fereastra să aibă dimensiunea optimă în funcție de conținut
		setLocationRelativeTo(null); // Face fereastra să apară în centrul ecranului

		btnGenerare.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GenerateWordDocument generator = new GenerateWordDocument(comboBoxProfesor, comboBoxTitular,
						comboBoxGrupa, comboBoxMaterii, comboBoxLaborator);
				generator.generateAndOpenDocument();
			}
		});

	}

	/**
	 * Metoda createLabeledPanel creează un panou care conține un label și un
	 * component.
	 * 
	 * @param label     Textul label-ului
	 * @param component Componenta asociată label-ului
	 * @return Panoul creat
	 */
	private JPanel createLabeledPanel(String label, JComponent component) {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(new Color(indexBackground1, indexBackground1, indexBackground1)); // Setăm culoarea de
																								// fundal
		JLabel lbl = new JLabel(label);
		lbl.setForeground(Color.WHITE); // Setăm culoarea textului label-ului
		lbl.setFont(new Font("Arial", Font.PLAIN, indexFont)); // Setăm fontul și stilul textului label-ului
		panel.add(lbl, BorderLayout.WEST);
		panel.add(component, BorderLayout.CENTER);
		return panel;
	}

	/**
	 * Metoda citesteProfesoriDinExcel citește numele profesorilor din fișierul
	 * Excel și le returnează sub formă de listă.
	 * 
	 * @param filePath Calea către fișierul Excel
	 * @return Lista de nume de profesori
	 */
	private List<String> citesteProfesoriDinExcel(String filePath) {
		final int indexCell3 = 3;
		final int indexCell6 = 6;
		final int indexCell9 = 9;
		List<String> profesori = new ArrayList<>();
		Set<String> numeProfesoriUnice = new HashSet<>(); // Utilizăm un Set pentru a evita dublarea numelor

		try (FileInputStream fis = new FileInputStream(filePath); Workbook workbook = new XSSFWorkbook(fis)) {
			Sheet sheet = workbook.getSheetAt(nr);
			Iterator<Row> rowIterator = sheet.iterator();

			// Omiterea primei linii din coloanele 3, 6 și 9
			boolean skipFirstRow = true;

			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();

				// Presupunem că numele profesorilor se găsesc în a treia, a șasea și a noua
				// coloană (indicele 2, 5 și 8)
				Cell cell3 = row.getCell(indexCell3);
				Cell cell6 = row.getCell(indexCell6);
				Cell cell9 = row.getCell(indexCell9);

				String numeProfesor3 = cell3 != null && cell3.getCellType() == CellType.STRING
						? cell3.getStringCellValue()
						: "";
				String numeProfesor6 = cell6 != null && cell6.getCellType() == CellType.STRING
						? cell6.getStringCellValue()
						: "";
				String numeProfesor9 = cell9 != null && cell9.getCellType() == CellType.STRING
						? cell9.getStringCellValue()
						: "";

				// Verificăm dacă trebuie să omitem prima linie
				if (skipFirstRow) {
					skipFirstRow = false;
					continue; // Trecem la următoarea iterație fără a adăuga primul element în listă
				}

				// Adăugăm numele profesorilor în listă, evitând dublarea lor
				if (!numeProfesoriUnice.contains(numeProfesor3)) {
					profesori.add(numeProfesor3);
					numeProfesoriUnice.add(numeProfesor3);
				}
				if (!numeProfesoriUnice.contains(numeProfesor6)) {
					profesori.add(numeProfesor6);
					numeProfesoriUnice.add(numeProfesor6);
				}
				if (!numeProfesoriUnice.contains(numeProfesor9)) {
					profesori.add(numeProfesor9);
					numeProfesoriUnice.add(numeProfesor9);
				}
			}
		} catch (IOException e) {
			System.out.println("Eroare la citirea fișierului Excel: " + e.getMessage());
		}

		return profesori;
	}

	/**
	 * Metoda citesteTitular citește numele titularilor din fișierul Excel și le
	 * returnează sub formă de listă.
	 * 
	 * @param filePath Calea către fișierul Excel
	 * @return Lista de nume de titulari
	 */

	private List<String> citesteTitular(String filePath) {
		final int indexCell5 = 5;
		final int indexCell8 = 8;
		final int indexCell11 = 11;
		List<String> titulari = new ArrayList<>();
		Set<String> numeTitulariUnice = new HashSet<>(); // Utilizăm un Set pentru a evita dublarea numelor

		try (FileInputStream fis = new FileInputStream(filePath); Workbook workbook = new XSSFWorkbook(fis)) {
			Sheet sheet = workbook.getSheetAt(nr);
			Iterator<Row> rowIterator = sheet.iterator();

			// Omiterea primei linii din coloanele 3, 6 și 9
			boolean skipFirstRow = true;

			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();

				// Presupunem că numele profesorilor se găsesc în a treia, a șasea și a noua
				// coloană (indicele 2, 5 și 8)
				Cell cell5 = row.getCell(indexCell5);
				Cell cell8 = row.getCell(indexCell8);
				Cell cell11 = row.getCell(indexCell11);

				String numeTitular5 = cell5 != null && cell5.getCellType() == CellType.STRING
						? cell5.getStringCellValue()
						: "";
				String numeTitular8 = cell8 != null && cell8.getCellType() == CellType.STRING
						? cell8.getStringCellValue()
						: "";
				String numeTitular11 = cell11 != null && cell11.getCellType() == CellType.STRING
						? cell11.getStringCellValue()
						: "";

				// Verificăm dacă trebuie să omitem prima linie
				if (skipFirstRow) {
					skipFirstRow = false;
					continue; // Trecem la următoarea iterație fără a adăuga primul element în listă
				}

				// Adăugăm numele profesorilor în listă, evitând dublarea lor
				if (!numeTitulariUnice.contains(numeTitular5)) {
					titulari.add(numeTitular5);
					numeTitulariUnice.add(numeTitular5);
				}
				if (!numeTitulariUnice.contains(numeTitular8)) {
					titulari.add(numeTitular8);
					numeTitulariUnice.add(numeTitular8);
				}
				if (!numeTitulariUnice.contains(numeTitular11)) {
					titulari.add(numeTitular11);
					numeTitulariUnice.add(numeTitular11);
				}
			}
		} catch (IOException e) {
			System.out.println("Eroare la citirea fișierului Excel: " + e.getMessage());
		}

		return titulari;
	}

	/**
	 * Metoda citesteGrupa citește numele grupelor din fișierul Excel și le
	 * returnează sub formă de listă.
	 * 
	 * @param filePath Calea către fișierul Excel
	 * @return Lista de nume de grupuri
	 */
	private List<String> citesteGrupa(String filePath) {
		final int indexCell = 2;
		List<String> grupa = new ArrayList<>();
		Set<String> grupaUnica = new HashSet<>(); // Utilizăm un Set pentru a evita dublarea numelor

		try (FileInputStream fis = new FileInputStream(filePath); Workbook workbook = new XSSFWorkbook(fis)) {
			Sheet sheet = workbook.getSheetAt(nr);
			Iterator<Row> rowIterator = sheet.iterator();

			// Omiterea primei linii din coloanele 3, 6 și 9
			boolean skipFirstRow = true;

			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();

				// Presupunem că numele profesorilor se găsesc în a treia, a șasea și a noua
				// coloană (indicele 2, 5 și 8)
				Cell cell2 = row.getCell(indexCell);

				String numegrupa = cell2 != null && cell2.getCellType() == CellType.STRING ? cell2.getStringCellValue()
						: "";

				// Verificăm dacă trebuie să omitem prima linie
				if (skipFirstRow) {
					skipFirstRow = false;
					continue; // Trecem la următoarea iterație fără a adăuga primul element în listă
				}

				// Adăugăm numele profesorilor în listă, evitând dublarea lor
				if (!grupaUnica.contains(numegrupa)) {
					grupa.add(numegrupa);
					grupaUnica.add(numegrupa);
				}

			}
		} catch (IOException e) {
			System.out.println("Eroare la citirea fișierului Excel: " + e.getMessage());
		}

		return grupa;
	}

	/**
	 * Metoda citesteMaterie citește numele materiilor din fișierul Excel și le
	 * returnează sub formă de listă.
	 * 
	 * @param filePath Calea către fișierul Excel
	 * @return Lista de nume de materii
	 */

	private List<String> citesteMaterie(String filePath) {
		final int indexCell1 = 4;
		final int indexCell2 = 7;
		final int indexCell3 = 10;
		List<String> materie = new ArrayList<>();
		Set<String> materieUnica = new HashSet<>(); // Utilizăm un Set pentru a evita dublarea numelor

		try (FileInputStream fis = new FileInputStream(filePath); Workbook workbook = new XSSFWorkbook(fis)) {
			Sheet sheet = workbook.getSheetAt(nr);
			Iterator<Row> rowIterator = sheet.iterator();

			// Omiterea primei linii din coloanele 4, 7 și 10
			boolean skipFirstRow = true;

			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();

				// Presupunem că numele materiilor se găsesc în a patra, a șaptea și a zecea
				// coloană (indicele 4, 7 și 10)
				Cell cell4 = row.getCell(indexCell1);
				Cell cell7 = row.getCell(indexCell2);
				Cell cell10 = row.getCell(indexCell3);

				String numeMaterie1 = cell4 != null && cell4.getCellType() == CellType.STRING
						? cell4.getStringCellValue()
						: "";
				String numeMaterie2 = cell7 != null && cell7.getCellType() == CellType.STRING
						? cell7.getStringCellValue()
						: "";
				String numeMaterie3 = cell10 != null && cell10.getCellType() == CellType.STRING
						? cell10.getStringCellValue()
						: "";

				// Verificăm dacă trebuie să omitem prima linie
				if (skipFirstRow) {
					skipFirstRow = false;
					continue; // Trecem la următoarea iterație fără a adăuga primul element în listă
				}

				// Adăugăm numele materiilor în listă, evitând dublarea lor
				if (!numeMaterie1.isEmpty() && !materieUnica.contains(numeMaterie1)) {
					materie.add(numeMaterie1);
					materieUnica.add(numeMaterie1);
				}
				if (!numeMaterie2.isEmpty() && !materieUnica.contains(numeMaterie2)) {
					materie.add(numeMaterie2);
					materieUnica.add(numeMaterie2);
				}
				if (!numeMaterie3.isEmpty() && !materieUnica.contains(numeMaterie3)) {
					materie.add(numeMaterie3);
					materieUnica.add(numeMaterie3);
				}
			}
		} catch (IOException e) {
			System.out.println("Eroare la citirea fișierului Excel: " + e.getMessage());
		}

		return materie;
	}

	/**
	 * Metoda citesteLaborator citește numele laboratoarelor din fișierul Excel și
	 * le returnează sub formă de listă.
	 * 
	 * @param filePath Calea către fișierul Excel
	 * @return Lista de nume de laboratoare
	 */
	private List<String> citesteLaborator(String filePath) {
		final int indexCellMaterie = 12;
		List<String> materie = new ArrayList<>();
		Set<String> materieUnica = new HashSet<>(); // Utilizăm un Set pentru a evita dublarea numelor

		try (FileInputStream fis = new FileInputStream(filePath); Workbook workbook = new XSSFWorkbook(fis)) {
			Sheet sheet = workbook.getSheetAt(nr);
			Iterator<Row> rowIterator = sheet.iterator();

			// Omiterea primei linii din coloana ultimă pentru a găsi materiile
			boolean skipFirstRow = true;

			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();

				// Presupunem că numele materiilor se găsesc în coloana cu indexul 12 (ultima
				// coloană)
				Cell cellMaterie = row.getCell(indexCellMaterie); // Indexul coloanei cu numele materiei

				String numeMaterie = cellMaterie != null && cellMaterie.getCellType() == CellType.STRING
						? cellMaterie.getStringCellValue()
						: "";

				// Verificăm dacă trebuie să omitem prima linie
				if (skipFirstRow) {
					skipFirstRow = false;
					continue; // Trecem la următoarea iterație fără a adăuga primul element în listă
				}

				// Adăugăm numele materiei în listă, evitând dublarea lor
				if (!numeMaterie.isEmpty() && !materieUnica.contains(numeMaterie)) {
					materie.add(numeMaterie);
					materieUnica.add(numeMaterie);
				}
			}
		} catch (IOException e) {
			System.out.println("Eroare la citirea fișierului Excel: " + e.getMessage());
		}

		return materie;
	}

	/**
	 * Metoda principală care pornește interfața grafică.
	 * 
	 * @param args Argumentele liniei de comandă (nu sunt utilizate în acest
	 *             exemplu).
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				GeneratorGUI gui = new GeneratorGUI();
				gui.setVisible(true);
			}
		});
	}
}
