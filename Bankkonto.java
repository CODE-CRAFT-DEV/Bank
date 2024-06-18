package uebung_2024_06_18_exceptions.Aufgabe_4;

import java.util.InputMismatchException;

public class Bankkonto {
    // Attribute festlegen
    private final int kontonummer;
    private double kontostand;
    private final String waehrung;

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
        return kontostand;
    }

    public String getWaehrung() {
        return waehrung;
    }

    /**
     * Methode zum Einzahlen auf das Konto, erwartet den Betrag als Eingabe
     * @param einzahlung
     */
    public void Einzahlen(double einzahlung) {
        kontostand += einzahlung;
    }

    /**
     * Methode zum Abheben von Geld. Erwartet Betrag als Eingabe und wirft Fehler aus
     * wenn Konto nicht gedeckt ist
     * @param auszahlung
     */
    public void Abheben(double auszahlung) {
        if (kontostand < auszahlung) {
            throw new InputMismatchException("Nicht genügend Geld auf dem Konto");
        }
        kontostand -= auszahlung;
    }

    /**
     * Methode um den Kontostand anzeigen zu lassen
     */
    public void zeigeKontostand() {
        System.out.println("Kontostand: " + kontostand + " " + waehrung);
    }
}

