import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Main class for the Train Consist Management App
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Train Consist Management App ===");

        // Initialize an empty list for the train consist
        List<String> consist = new ArrayList<>();

        // Display the initial bogie count
        System.out.println("Initial bogie count: " + consist.size());

        // Add passenger bogies
        consist.add("Sleeper");
        consist.add("AC Chair");
        consist.add("First Class");

        // Print the list after insertion
        System.out.println("Passenger bogies: " + consist);

        // Remove one bogie
        consist.remove("AC Chair");

        // Check existence
        if (consist.contains("Sleeper")) {
            System.out.println("Sleeper is present");
        }

        // Print final list state
        System.out.println("Final bogie count: " + consist.size());
        System.out.println("Final passenger bogies: " + consist);

        // Track unique bogie IDs using HashSet
        Set<String> bogieIds = new HashSet<>();
        bogieIds.add("BG101");
        bogieIds.add("BG102");
        bogieIds.add("BG101"); // duplicate
        System.out.println("Unique bogie IDs: " + bogieIds);
    }
}