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
        try {
            bogies.add(new Bogie("Sleeper", 72));
            bogies.add(new Bogie("AC Chair", 60));
            bogies.add(new Bogie("First Class", 40));
        } catch (InvalidCapacityException e) {
            System.out.println("Error creating bogies: " + e.getMessage());
        }
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
        try {
            bogies.add(new Bogie("Sleeper", 72));
            bogies.add(new Bogie("AC Chair", 60));
            bogies.add(new Bogie("First Class", 40));
            bogies.add(new Bogie("Sleeper", 72));
        } catch (InvalidCapacityException e) {
            System.out.println("Error adding bogies for grouping: " + e.getMessage());
        }

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

        // UC13: Performance Comparison (Loops vs Streams)
        // Create a large collection of bogies for performance testing
        List<Bogie> performanceTestBogies = new ArrayList<>();
        try {
            performanceTestBogies.add(new Bogie("Sleeper", 72));
            performanceTestBogies.add(new Bogie("AC Chair", 60));
            performanceTestBogies.add(new Bogie("First Class", 40));
            performanceTestBogies.add(new Bogie("Sleeper", 72));
            performanceTestBogies.add(new Bogie("AC Chair", 60));
            
            // Generate a large dataset for better benchmarking
            for (int i = 0; i < 100000; i++) {
                performanceTestBogies.add(new Bogie("Sleeper", 72));
                performanceTestBogies.add(new Bogie("AC Chair", 60));
                performanceTestBogies.add(new Bogie("First Class", 40));
            }
        } catch (InvalidCapacityException e) {
            System.out.println("Error creating performance test bogies: " + e.getMessage());
        }

        System.out.println("\nPerformance Comparison (Loops vs Streams):");
        System.out.println("Dataset size: " + performanceTestBogies.size() + " bogies");

        // Measure loop-based filtering
        long startTimeLoop = System.nanoTime();
        List<Bogie> loopFilteredBogies = new ArrayList<>();
        for (Bogie bogie : performanceTestBogies) {
            if (bogie.getCapacity() > 60) {
                loopFilteredBogies.add(bogie);
            }
        }
        long endTimeLoop = System.nanoTime();
        long loopElapsedTime = endTimeLoop - startTimeLoop;

        // Measure stream-based filtering
        long startTimeStream = System.nanoTime();
        List<Bogie> streamFilteredBogies = performanceTestBogies.stream()
            .filter(bogie -> bogie.getCapacity() > 60)
            .collect(Collectors.toList());
        long endTimeStream = System.nanoTime();
        long streamElapsedTime = endTimeStream - startTimeStream;

        // Display results
        System.out.println("\nLoop-Based Filtering:");
        System.out.println("  Filtered bogies: " + loopFilteredBogies.size());
        System.out.println("  Execution time: " + loopElapsedTime + " nanoseconds");
        System.out.println("  Execution time: " + (loopElapsedTime / 1_000_000.0) + " milliseconds");

        System.out.println("\nStream-Based Filtering:");
        System.out.println("  Filtered bogies: " + streamFilteredBogies.size());
        System.out.println("  Execution time: " + streamElapsedTime + " nanoseconds");
        System.out.println("  Execution time: " + (streamElapsedTime / 1_000_000.0) + " milliseconds");

        // Verify consistency
        boolean resultsMatch = loopFilteredBogies.size() == streamFilteredBogies.size();
        System.out.println("\nConsistency Check:");
        System.out.println("  Results match: " + resultsMatch);

        // Performance comparison
        if (loopElapsedTime < streamElapsedTime) {
            double speedup = (double) streamElapsedTime / loopElapsedTime;
            System.out.println("  Loop is " + String.format("%.2f", speedup) + "x faster than Stream");
        } else {
            double speedup = (double) loopElapsedTime / streamElapsedTime;
            System.out.println("  Stream is " + String.format("%.2f", speedup) + "x faster than Loop");
        }

        // UC14: Handle Invalid Bogie Capacity (Custom Exception)
        System.out.println("\nCustom Exception Handling - Invalid Bogie Capacity:");

        // Test case 1: Valid bogie creation
        try {
            Bogie validBogie = new Bogie("Sleeper", 72);
            System.out.println("✓ Valid bogie created: " + validBogie);
        } catch (InvalidCapacityException e) {
            System.out.println("✗ Exception caught: " + e.getMessage());
        }

        // Test case 2: Negative capacity
        try {
            Bogie invalidBogie = new Bogie("AC Chair", -50);
            System.out.println("✓ Bogie created: " + invalidBogie);
        } catch (InvalidCapacityException e) {
            System.out.println("✗ Negative capacity detected - Exception: " + e.getMessage());
        }

        // Test case 3: Zero capacity
        try {
            Bogie zeroCapacityBogie = new Bogie("First Class", 0);
            System.out.println("✓ Bogie created: " + zeroCapacityBogie);
        } catch (InvalidCapacityException e) {
            System.out.println("✗ Zero capacity detected - Exception: " + e.getMessage());
        }

        // Test case 4: Multiple valid bogies
        System.out.println("Creating multiple valid bogies:");
        try {
            List<Bogie> validBogies = new ArrayList<>();
            validBogies.add(new Bogie("Sleeper", 72));
            validBogies.add(new Bogie("AC Chair", 60));
            validBogies.add(new Bogie("First Class", 40));
            System.out.println("✓ All valid bogies created successfully");
            for (Bogie bogie : validBogies) {
                System.out.println("  - " + bogie);
            }
        } catch (InvalidCapacityException e) {
            System.out.println("✗ Exception caught: " + e.getMessage());
        }

        // Test case 5: Large capacity value
        try {
            Bogie largeBogie = new Bogie("Super Sleeper", 500);
            System.out.println("✓ Large capacity bogie created: " + largeBogie);
        } catch (InvalidCapacityException e) {
            System.out.println("✗ Exception caught: " + e.getMessage());
        }

        // UC15: Safe Cargo Assignment Using try-catch-finally
        System.out.println("\nSafe Cargo Assignment Using try-catch-finally:");

        // Test case 1: Safe cargo assignment (Cylindrical bogie with Petroleum)
        System.out.println("\nTest 1: Safe assignment (Cylindrical with Petroleum)");
        GoodBogie cylindricalBogie = new GoodBogie("Cylindrical", null);
        try {
            cylindricalBogie.assignCargo("Petroleum");
            System.out.println("✓ Safe assignment succeeded: " + cylindricalBogie);
        } catch (CargoSafetyException e) {
            System.out.println("✗ Safety exception: " + e.getMessage());
        } finally {
            System.out.println("→ Finally block: Cargo assignment validation complete");
        }

        // Test case 2: Unsafe cargo assignment (Rectangular bogie with Petroleum)
        System.out.println("\nTest 2: Unsafe assignment (Rectangular with Petroleum)");
        GoodBogie rectangularBogie = new GoodBogie("Rectangular", null);
        try {
            rectangularBogie.assignCargo("Petroleum");
            System.out.println("✓ Assignment succeeded: " + rectangularBogie);
        } catch (CargoSafetyException e) {
            System.out.println("✗ Safety exception caught: " + e.getMessage());
        } finally {
            System.out.println("→ Finally block: Cargo assignment validation complete");
        }

        // Test case 3: Verify rectangular bogie still has null cargo after failed assignment
        System.out.println("\nTest 3: Verify cargo not assigned after failure");
        System.out.println("Rectangular bogie cargo after failed assignment: " + rectangularBogie.getCargo());

        // Test case 4: Safe assignment for rectangular bogie with suitable cargo
        System.out.println("\nTest 4: Safe assignment (Rectangular with Coal)");
        try {
            rectangularBogie.assignCargo("Coal");
            System.out.println("✓ Safe assignment succeeded: " + rectangularBogie);
        } catch (CargoSafetyException e) {
            System.out.println("✗ Safety exception: " + e.getMessage());
        } finally {
            System.out.println("→ Finally block: Cargo assignment validation complete");
        }

        // Test case 5: Multiple cargo assignments showing program continuation
        System.out.println("\nTest 5: Multiple cargo assignments (demonstrating program continuation)");
        GoodBogie[] goodBogies = {
            new GoodBogie("Cylindrical", null),
            new GoodBogie("Rectangular", null),
            new GoodBogie("Open", null),
            new GoodBogie("Box", null)
        };

        String[] cargoAssignments = {"Petroleum", "Coal", "Grain", "Machinery"};

        for (int i = 0; i < goodBogies.length; i++) {
            try {
                goodBogies[i].assignCargo(cargoAssignments[i]);
                System.out.println("✓ Assignment " + (i + 1) + ": " + goodBogies[i]);
            } catch (CargoSafetyException e) {
                System.out.println("✗ Assignment " + (i + 1) + " failed: " + e.getMessage());
            } finally {
                System.out.println("  → Validation complete for bogie " + (i + 1));
            }
        }

        System.out.println("\n✓ All cargo assignment tests completed. Application is stable.");

        // UC16: Sort Passenger Bogies by Capacity (Bubble Sort – Algorithm Intro)
        System.out.println("\nBubble Sort - Sorting Passenger Bogie Capacities:");

        // Test case 1: Basic unsorted array
        int[] capacities1 = {72, 56, 24, 70, 60};
        System.out.println("\nTest 1: Basic unsorted array");
        System.out.println("Original capacities: " + java.util.Arrays.toString(capacities1));
        bubbleSort(capacities1);
        System.out.println("Sorted capacities:   " + java.util.Arrays.toString(capacities1));

        // Test case 2: Already sorted array
        int[] capacities2 = {24, 56, 60, 70, 72};
        System.out.println("\nTest 2: Already sorted array");
        System.out.println("Original capacities: " + java.util.Arrays.toString(capacities2));
        bubbleSort(capacities2);
        System.out.println("Sorted capacities:   " + java.util.Arrays.toString(capacities2));

        // Test case 3: Duplicate values
        int[] capacities3 = {72, 56, 56, 24};
        System.out.println("\nTest 3: Duplicate capacity values");
        System.out.println("Original capacities: " + java.util.Arrays.toString(capacities3));
        bubbleSort(capacities3);
        System.out.println("Sorted capacities:   " + java.util.Arrays.toString(capacities3));

        // Test case 4: Single element array
        int[] capacities4 = {50};
        System.out.println("\nTest 4: Single element array");
        System.out.println("Original capacities: " + java.util.Arrays.toString(capacities4));
        bubbleSort(capacities4);
        System.out.println("Sorted capacities:   " + java.util.Arrays.toString(capacities4));

        // Test case 5: All equal values
        int[] capacities5 = {40, 40, 40};
        System.out.println("\nTest 5: All equal capacity values");
        System.out.println("Original capacities: " + java.util.Arrays.toString(capacities5));
        bubbleSort(capacities5);
        System.out.println("Sorted capacities:   " + java.util.Arrays.toString(capacities5));

        // Test case 6: Reverse sorted array (worst case for bubble sort)
        int[] capacities6 = {72, 60, 56, 40, 24};
        System.out.println("\nTest 6: Reverse sorted array (worst case)");
        System.out.println("Original capacities: " + java.util.Arrays.toString(capacities6));
        bubbleSort(capacities6);
        System.out.println("Sorted capacities:   " + java.util.Arrays.toString(capacities6));

        System.out.println("\n✓ Bubble Sort demonstration completed.");
    }

    /**
     * Bubble Sort algorithm to sort passenger bogie capacities
     * Time Complexity: O(n²)
     * Space Complexity: O(1)
     * @param capacities Array of bogie capacities to sort
     */
    public static void bubbleSort(int[] capacities) {
        int n = capacities.length;
        
        // Outer loop for multiple passes
        for (int i = 0; i < n - 1; i++) {
            // Inner loop for comparing adjacent elements
            for (int j = 0; j < n - i - 1; j++) {
                // Compare adjacent elements
                if (capacities[j] > capacities[j + 1]) {
                    // Swap if left element is greater than right element
                    int temp = capacities[j];
                    capacities[j] = capacities[j + 1];
                    capacities[j + 1] = temp;
                }
            }
        }
    }
}