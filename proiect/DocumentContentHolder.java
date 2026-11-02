package ro.tuiasi.ac.proiect;

/**
 * Clasa DocumentContentHolder este responsabilă pentru păstrarea conținutului
 * documentului.
 *
 * @author
 */
public class DocumentContentHolder {
	/**
	 * Conținutul documentului.
	 */
	private static String documentContent;

	/**
	 * Metoda setDocumentContent este folosită pentru a seta conținutul
	 * documentului.
	 *
	 * @param content Conținutul documentului care trebuie setat.
	 */
	public static void setDocumentContent(final String content) {
		documentContent = content;
	}

	/**
	 * Metoda getDocumentContent este folosită pentru a obține conținutul
	 * documentului.
	 *
	 * @return Conținutul documentului.
	 */
	public static String getDocumentContent() {
		return documentContent;
	}
}
