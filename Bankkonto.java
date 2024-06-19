package uebung_2024_06_18_exceptions.Aufgabe_4;

public class Bankkonto {
    // Attribute festlegen
    private int kontonummer;
    private double kontostand;
    private String waehrung;

    /**
     * Konstruktor der ein Bankkonto erstellt und einen Anfangsbestand und Währung deklariert
     * @param kontonummer
     * @param anfangskontostand
     * @param waehrung
     */
    public Bankkonto(int kontonummer, double anfangskontostand, String waehrung) {
        this.kontonummer = kontonummer;
        this.kontostand = anfangskontostand;
        this.waehrung = waehrung;
    }

    // Getter Methoden für die Attribute
    public int getKontonummer() {
        return kontonummer;
    }

    public double getKontostand() {
        int temp = (int)kontostand * 100;
        kontostand = (double) temp / 100;
        return kontostand;
    }

    public String getWaehrung() {
        return waehrung;
    }

    /**
     * Methode zum Einzahlen auf das Konto, erwartet den Betrag als Eingabe
     * @param einzahlung
     */
    public void einzahlen(double einzahlung) {
        kontostand += einzahlung;
    }

    /**
     * Methode zum Abheben von Geld. Erwartet Betrag als Eingabe und wirft Fehler aus
     * wenn Konto nicht gedeckt ist
     * @param auszahlung
     */
    public void auszahlen(double auszahlung) throws KontoNichtGedecktException {
        if (kontostand < auszahlung) {
            throw new KontoNichtGedecktException("Zu wenig Guthaben auf dem Konto!");
        }
        kontostand -= auszahlung;
    }

    /**
     * Methode um den Kontostand anzeigen zu lassen
     */
    public void zeigeKontostand() {
        System.out.println("Kontonummer: " + getKontonummer() + " " + "Kontostand: " + kontostand + " " + waehrung);
    }
}

