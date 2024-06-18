package uebung_2024_06_18_exceptions.Aufgabe_4;

import java.util.ArrayList;
import java.util.List;

public class Ueberweisungsverlauf {
    // Attribut festlegen
    private final List<Ueberweisung> ueberweisungen;

    // Konstruktor der eine neue ArrayList erstellt
    public Ueberweisungsverlauf() {
        ueberweisungen = new ArrayList<>();
    }

    /**
     * Methode, die die Überweisung in das Array aufnimmt. Erwartet das Objekt aus der Klasse Ueberweisung
     * @param ueberweisung
     */
    public void machUeberweisung(Ueberweisung ueberweisung) {
        ueberweisungen.add(ueberweisung);
    }

    /**
     * Methode zum Anzeigen der Überweisungshistorie
     */
    public void zeigeUeberweisungsverlauf() {
        for (Ueberweisung ueberweisung : ueberweisungen) {
            System.out.println("Ueberweisung: " + ueberweisung.getKontonummerSender() +
                                         " -> " + ueberweisung.getKontonummerEmpfaenger() +
                                          " - " + ueberweisung.getBetrag() +
                                          " - " + ueberweisung.getTimestamp()
            );
        }
    }
}