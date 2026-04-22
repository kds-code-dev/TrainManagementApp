/**
 * Custom runtime exception for cargo safety violations
 */
public class CargoSafetyException extends RuntimeException {
    public CargoSafetyException(String message) {
        super(message);
    }
}
