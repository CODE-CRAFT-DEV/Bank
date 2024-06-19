package uebung_2024_06_18_exceptions.Aufgabe_4;

public class NichtUnterstuezteWaehrungException extends Exception{
    public NichtUnterstuezteWaehrungException(String detailMessage) {
        super(detailMessage);
    }
}
