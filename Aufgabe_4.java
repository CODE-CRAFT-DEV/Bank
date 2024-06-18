package uebung_2024_06_18_exceptions.Aufgabe_4;


import java.util.InputMismatchException;

public class Aufgabe_4 {

    public static void main(String[] args) {
        // Erstelle Konten
        Bankkonto konto_1 = new Bankkonto(1234567890, 100.0, "EUR");
        Bankkonto konto_2 = new Bankkonto(1876543210, 500.0, "EUR");
        Bankkonto konto_3 = new Bankkonto(1111111111, 2000.0, "USD");


        // Erstelle Ueberweisung
        Ueberweisung ueberweisung_1 = new Ueberweisung(konto_1, konto_2, 200.0);
        Ueberweisung ueberweisung_2 = new Ueberweisung(konto_2, konto_3, 300.0);
        Ueberweisung ueberweisung_3 = new Ueberweisung(konto_2, konto_1, 200);
        Ueberweisung ueberweisung_4 = new Ueberweisung(konto_1, konto_2, 49.95);

        // Erstelle Ueberweisungsverlauf
        Ueberweisungsverlauf neuerVerlauf = new Ueberweisungsverlauf();

        try {
          //  ueberweisung_1.Ueberweise();
          //  ueberweisung_2.Ueberweise();
            ueberweisung_3.Ueberweise();
            ueberweisung_4.Ueberweise();
            neuerVerlauf.machUeberweisung(ueberweisung_3);
            neuerVerlauf.machUeberweisung(ueberweisung_4);
        } catch (InputMismatchException x) {
            System.err.println("Fehler: " + x.getMessage());
        }

        neuerVerlauf.zeigeUeberweisungsverlauf();
        konto_1.zeigeKontostand();
        konto_2.zeigeKontostand();
    }
}