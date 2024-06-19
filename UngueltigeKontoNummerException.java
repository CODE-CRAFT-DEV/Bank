package uebung_2024_06_18_exceptions.Aufgabe_4;

public class UngueltigeKontoNummerException extends Exception {
    public UngueltigeKontoNummerException(String detailMessage) {
        super(detailMessage);
    }
}
