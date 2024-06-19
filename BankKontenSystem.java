package uebung_2024_06_18_exceptions.Aufgabe_4;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class BankKontenSystem {
    private List<Bankkonto>     konten = new ArrayList<>();
    private List<Ueberweisung>  ueberweisungsVerlauf = new ArrayList<>();

    public static void main(String[] args) {
        BankKontenSystem system = new BankKontenSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.println("Willkommen bei der der JAVA - Bank");
            System.out.println("1. Konto anlegen");
            System.out.println("2. Kontostand anzeigen");
            System.out.println("3. Überweisung durchführen");
            System.out.println("4. Überweisungen anzeigen");
            System.out.println("9. Programm beenden");
            System.out.print("Ihre Wahl: ");

            int wahl = scanner.nextInt();
            switch (wahl) {
                case 1:
                    Bankkonto konto = erstelleKonto(scanner);
                    system.konten.add(konto);
                    break;
                case 2:
                    System.out.print("Gib Kontonummer an: ");
                    int kontonummer = scanner.nextInt();
                    for (Bankkonto a : system.konten) {
                        if (a.getKontonummer() == kontonummer) {
                            System.out.println("Kontostand: " + a.getKontostand() + " " + a.getWaehrung());
                            break;
                        }
                    }
                    break;
                case 3:
                    try {
                        system.ueberweiseBetrag(scanner);
                    } catch (InputMismatchException ime) {
                        System.err.println("Fehler: " + ime.getMessage());
                        scanner.next();
                    } catch (KontoNichtGedecktException knge) {
                        System.err.println("Fehler: " + knge.getMessage());
                    } catch (UngueltigeKontoNummerException ukne) {
                        System.err.println("Fehler: " + ukne.getMessage());
                    } catch (NichtUnterstuezteWaehrungException nuwe) {
                        System.err.println("Fehler: " + nuwe.getMessage());
                    }
                    break;
                case 4:
                    System.out.println("Folgende Überweisungen wurden gemacht:");
                    for (Ueberweisung ueberweisung : system.getUeberweisungsVerlauf()) {
                        System.out.println("Sender: " + ueberweisung.getKontonummerSender().getKontonummer());
                        System.out.println("Empfänger: " + ueberweisung.getKontonummerEmpfaenger().getKontonummer());
                        System.out.println("Betrag: " + ueberweisung.getBetrag());
                        System.out.println();
                    }
                    break;
                case 9:
                    System.out.println("Programm wird beendet...");
                    System.exit(0);
                    break;
                default:
                    System.err.println("Ungültige Eingabe!");
            }
        }
    }

    private static Bankkonto erstelleKonto(Scanner scanner) {
        int kontonummer;
        System.out.print("Gib Kontonummer an: ");
        try {
            kontonummer = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.err.println("Falsche Eingabe! Gib einen Integer ein.");
            scanner.next();                     // Verwerfe falsche Eingabe
            kontonummer = scanner.nextInt();    // probier´s nochmal
        }

        System.out.print("Gib Anfangsbestand an: ");
        double anfangsbestand = scanner.nextDouble();

        System.out.print("Gib Währung an (z.B. EUR, USD, CHF): ");
        String waehrung = scanner.next().toUpperCase();

        return new Bankkonto(kontonummer, anfangsbestand, waehrung);
    }

    private void ueberweiseBetrag(Scanner scanner) throws KontoNichtGedecktException, UngueltigeKontoNummerException, NichtUnterstuezteWaehrungException {
        System.out.print("Gib die Kontonummer des Absenders an: ");
        int senderKontonummer = scanner.nextInt();
        Bankkonto kontoNummerSender = getKonto(senderKontonummer);
        if (kontoNummerSender == null) {
            throw new UngueltigeKontoNummerException("Ungültige Sender-Kontonummer!");
        }

        System.out.print("Gib die Kontonummer des Empfängers an: ");
        int empfaengerKontoNummer = scanner.nextInt();
        Bankkonto kontoNummerEmpfaenger = getKonto(empfaengerKontoNummer);
        if (kontoNummerEmpfaenger == null) {
            throw new UngueltigeKontoNummerException("Ungültige Empfänger-Kontonummer!");
        }

        System.out.print("Gib den Überweisungsbetrag an: ");
        double betrag = scanner.nextDouble();
        double auszahlBetrag = 0, einzahlBetrag = 0;

        if (!kontoNummerSender.getWaehrung().equals(kontoNummerEmpfaenger.getWaehrung())) {
            if (kontoNummerSender.getWaehrung().equals("EUR") && kontoNummerEmpfaenger.getWaehrung().equals("USD")) {
                auszahlBetrag = betrag;
                einzahlBetrag = betrag * 1.0734;
            } else if (kontoNummerSender.getWaehrung().equals("USD") && kontoNummerEmpfaenger.getWaehrung().equals("EUR")) {
                auszahlBetrag = betrag * 1.0737;
                einzahlBetrag = betrag;
            } else {
                throw new NichtUnterstuezteWaehrungException("Umrechnungskurs konnte nicht gefunden werden");
            }
        }

        if (kontoNummerSender.getKontostand() < auszahlBetrag) {
            throw new KontoNichtGedecktException("Zu wenig Guthaben auf dem Konto!");
        }

        kontoNummerSender.auszahlen(auszahlBetrag);
        kontoNummerEmpfaenger.einzahlen(einzahlBetrag);

        Ueberweisung ueberweisung = new Ueberweisung(kontoNummerSender, kontoNummerEmpfaenger, betrag);
        ueberweisungsVerlauf.add(ueberweisung);

        System.out.println("Überweisung erfolgreich durchgeführt!");
    }

    private Bankkonto getKonto(int kontoNummer) {
        for (Bankkonto konto : konten) {
            if (konto.getKontonummer() == kontoNummer) {
                return konto;
            }
        }
        return null;
    }

    public List<Ueberweisung> getUeberweisungsVerlauf() {
        return ueberweisungsVerlauf;
    }
}





























/*        // Erstelle Konten
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
            ueberweisung_1.Ueberweise();
            ueberweisung_2.Ueberweise();
            ueberweisung_3.Ueberweise();
            ueberweisung_4.Ueberweise();
            neuerVerlauf.machUeberweisung(ueberweisung_1);
            neuerVerlauf.machUeberweisung(ueberweisung_2);
            neuerVerlauf.machUeberweisung(ueberweisung_3);
            neuerVerlauf.machUeberweisung(ueberweisung_4);
        } catch (InputMismatchException x) {
            System.err.println("Fehler: " + x.getMessage());
        }

        neuerVerlauf.zeigeUeberweisungsverlauf();
        konto_1.zeigeKontostand();
        konto_2.zeigeKontostand();
        konto_3.zeigeKontostand();

    }
}*/