import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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

        // Maintain ordered bogie IDs using LinkedList
        LinkedList<String> trainConsist = new LinkedList<>();
        trainConsist.add("Engine");
        trainConsist.add("Sleeper");
        trainConsist.add("AC");
        trainConsist.add("Cargo");
        trainConsist.add("Guard");
        // Insert Pantry Car at position 2
        trainConsist.add(2, "Pantry Car");
        // Remove first and last
        trainConsist.removeFirst();
        trainConsist.removeLast();
        // Display final ordered train consist
        System.out.println("Final ordered train consist: " + trainConsist);

        // Preserve insertion order using LinkedHashSet
        LinkedHashSet<String> formation = new LinkedHashSet<>();
        formation.add("Engine");
        formation.add("Sleeper");
        formation.add("Cargo");
        formation.add("Guard");
        formation.add("Sleeper"); // duplicate
        System.out.println("Train formation: " + formation);

        // Map bogie to capacity using HashMap
        Map<String, Integer> bogieCapacity = new HashMap<>();
        bogieCapacity.put("Sleeper", 72);
        bogieCapacity.put("AC Chair", 60);
        bogieCapacity.put("First Class", 40);
        for (Map.Entry<String, Integer> entry : bogieCapacity.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " seats");
        }

        // Sort bogies by capacity using Comparator
        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 60));
        bogies.add(new Bogie("First Class", 40));
        bogies.sort(Comparator.comparingInt(Bogie::getCapacity));
        System.out.println("Bogies sorted by capacity:");
        for (Bogie b : bogies) {
            System.out.println(b);
        }

        // Filter passenger bogies using Streams
        List<Bogie> highCapacityBogies = bogies.stream()
            .filter(b -> b.getCapacity() > 60)
            .collect(Collectors.toList());
        System.out.println("High capacity bogies:");
        for (Bogie b : highCapacityBogies) {
            System.out.println(b);
        }

        // UC9: Group Bogies by Type (Collectors.groupingBy)
        // Add more bogies for grouping demonstration
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 60));
        bogies.add(new Bogie("First Class", 40));
        bogies.add(new Bogie("Sleeper", 72));

        // Group bogies by type using Collectors.groupingBy
        Map<String, List<Bogie>> groupedBogies = bogies.stream()
            .collect(Collectors.groupingBy(Bogie::getName));

        // Print the grouped bogie structure
        System.out.println("Bogies grouped by type:");
        for (Map.Entry<String, List<Bogie>> entry : groupedBogies.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // UC10: Count Total Seats in Train (reduce)
        // Calculate total seating capacity using stream reduce
        int totalSeats = bogies.stream()
            .map(b -> b.getCapacity())
            .reduce(0, Integer::sum);

        // Display the total seating capacity
        System.out.println("Total seating capacity of the train: " + totalSeats + " seats");

        // UC11: Validate Train ID & Cargo Codes (Regex)
        // Define regex patterns
        Pattern trainIdPattern = Pattern.compile("TRN-\\d{4}");
        Pattern cargoCodePattern = Pattern.compile("PET-[A-Z]{2}");

        // Sample Train IDs and Cargo Codes for validation
        String[] trainIds = {"TRN-1234", "TRAIN12", "TRN12A", "1234-TRN"};
        String[] cargoCodes = {"PET-AB", "PET-ab", "PET123", "AB-PET"};

        // Validate Train IDs
        System.out.println("Train ID Validation:");
        for (String trainId : trainIds) {
            Matcher matcher = trainIdPattern.matcher(trainId);
            boolean isValid = matcher.matches();
            System.out.println("Train ID: " + trainId + " - " + (isValid ? "Valid" : "Invalid"));
        }

        // Validate Cargo Codes
        System.out.println("Cargo Code Validation:");
        for (String cargoCode : cargoCodes) {
            Matcher matcher = cargoCodePattern.matcher(cargoCode);
            boolean isValid = matcher.matches();
            System.out.println("Cargo Code: " + cargoCode + " - " + (isValid ? "Valid" : "Invalid"));
        }

        // UC12: Safety Compliance Check for Goods Bogies
        // Create a list of goods bogies with type and cargo
        List<GoodBogie> goodsBogies = new ArrayList<>();
        goodsBogies.add(new GoodBogie("Cylindrical", "Petroleum"));
        goodsBogies.add(new GoodBogie("Rectangular", "Coal"));
        goodsBogies.add(new GoodBogie("Cylindrical", "Petroleum"));
        goodsBogies.add(new GoodBogie("Open", "Grain"));

        // Check safety compliance: Cylindrical bogies must carry only Petroleum
        boolean isSafeCompliant = goodsBogies.stream()
            .allMatch(bogie -> !bogie.getType().equals("Cylindrical") || bogie.getCargo().equals("Petroleum"));

        // Display safety compliance result
        System.out.println("Safety Compliance Check for Goods Bogies:");
        System.out.println("Goods Bogies in Formation:");
        for (GoodBogie bogie : goodsBogies) {
            System.out.println("  - " + bogie);
        }
        System.out.println("Train Formation Safety Status: " + (isSafeCompliant ? "SAFE" : "UNSAFE"));

        // Test case with violation
        System.out.println("\nTesting Safety Compliance with Violation:");
        List<GoodBogie> unsafeBogies = new ArrayList<>();
        unsafeBogies.add(new GoodBogie("Cylindrical", "Petroleum"));
        unsafeBogies.add(new GoodBogie("Cylindrical", "Coal"));  // Violation!
        unsafeBogies.add(new GoodBogie("Rectangular", "Grain"));

        boolean isUnsafeCompliant = unsafeBogies.stream()
            .allMatch(bogie -> !bogie.getType().equals("Cylindrical") || bogie.getCargo().equals("Petroleum"));

        System.out.println("Unsafe Bogies in Formation:");
        for (GoodBogie bogie : unsafeBogies) {
            System.out.println("  - " + bogie);
        }
        System.out.println("Train Formation Safety Status: " + (isUnsafeCompliant ? "SAFE" : "UNSAFE"));
    }
}