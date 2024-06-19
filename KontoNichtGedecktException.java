package uebung_2024_06_18_exceptions.Aufgabe_4;

public class KontoNichtGedecktException extends Exception{
    public KontoNichtGedecktException(String detailMessage) {
        super(detailMessage);
    }
}
