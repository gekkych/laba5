package exception;

public class IdException extends DequeException{
    public IdException() {
        super("Неверно указаны ID в XML файле");
    }
}
