import java.util.ArrayList;
import java.util.List;

/**
 * Main class for the Train Consist Management App
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Train Consist Management App ===");

        // Initialize an empty list for the train consist
        List consist = new ArrayList();

        // Display the initial bogie count
        System.out.println("Initial bogie count: " + consist.size());
    }
}