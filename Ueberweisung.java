package uebung_2024_06_18_exceptions.Aufgabe_4;

import java.util.Date;

public class Ueberweisung {
    // Attribute festlegen
    private final Bankkonto kontonummerSender;
    private final Bankkonto kontonummerEmpfaenger;
    private final double betrag;
    private final Date timestamp;

    /**
     * Konsttruktor für eine Überweisung. Erwartet die Eingabe des Absender- sowie Empfängerkontos
     * und den gewünschten Betrag. Datumsstempel wird automatisch erstellt
     *
     * @param kontonummerSender
     * @param kontonummerEmpfaenger
     * @param betrag
     */
    public Ueberweisung(Bankkonto kontonummerSender, Bankkonto kontonummerEmpfaenger, double betrag) {
        this.kontonummerSender = kontonummerSender;
        this.kontonummerEmpfaenger = kontonummerEmpfaenger;
        this.betrag = betrag;
        this.timestamp = new Date();
    }

    // Getter Methoden für die Attribute
    public Bankkonto getKontonummerSender() {
        return kontonummerSender;
    }

    public Bankkonto getKontonummerEmpfaenger() {
        return kontonummerEmpfaenger;
    }

    public double getBetrag() {
        return betrag;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}


