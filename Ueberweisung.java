package uebung_2024_06_18_exceptions.Aufgabe_4;

import java.util.Date;
import java.util.InputMismatchException;

public class Ueberweisung {
    // Attribute festlegen
    private final Bankkonto kontonummerSender;
    private final Bankkonto kontonummerEmpfaenger;
    private final double betrag;
    private final Date timestamp;

    /**
     * Konsttruktor für eine Überweisung. Erwartet die Eingabe des Absender- sowie Empfängerkontos
     * und den gewünschten Betrag. Datumsstempel wird automatisch erstellt
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

    /**
     * Methode zum Ueberweisen
     * Prüft die Waehrungspaare und wirft Exception bei Ungleichheit aus
     * Prüft den Kontostand und wirft Exception bei mangelnder Kontodeckung aus
     * Erstellt die Überweisung und trägt sie in die Historie ein
     * Verweist bei erfolgreicher Überweisung auf die Methode zeigeUeberweiungsStatus
     */
    public void Ueberweise() {
        if (!kontonummerSender.getWaehrung().equals(kontonummerEmpfaenger.getWaehrung())) {
            throw new InputMismatchException("Umrechnungskurs konnte nicht gefunden werden");
        }
        if (kontonummerSender.getKontostand() < betrag) {
            throw new InputMismatchException("Nicht genügend Geld auf dem Konto");
        } else {
            kontonummerSender.Abheben(betrag);
            kontonummerEmpfaenger.Einzahlen(betrag);
            Ueberweisung neueUeberweisung = new Ueberweisung(kontonummerSender, kontonummerEmpfaenger, betrag);
            zeigeUeberweisungsStatus();
        }
    }

    /**
     * Methode die den Überweisungsstatus zurückgibt.
     * Wird automatisch bei erfolgreicher Überweisung aufgerufen.
     */
    public void zeigeUeberweisungsStatus() {
        System.out.println("Ueberweisung erfolgreich");
    }
}

