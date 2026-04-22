# Train Consist Management App

A console-based Java application that simulates railway system management of train consists (collections of bogies attached to an engine). This application demonstrates various Java programming concepts through realistic railway scenarios.

## Features

The application supports:
- **Passenger bogies**: Sleeper, AC Chair, First Class with seat capacity tracking
- **Goods bogies**: Rectangular, Cylindrical with cargo type and safety constraints
- **Tracking**: Composition, capacity, cargo types, and safety compliance

## Use Cases Implemented

Each use case introduces Java concepts through railway scenarios:

1. **Basic List Operations**: Adding, removing, and checking bogies in a train consist
2. **Set Operations**: Tracking unique bogie IDs using HashSet
3. **LinkedList Operations**: Maintaining ordered train consists with insertion and removal
4. **LinkedHashSet**: Preserving insertion order for train formations
5. **HashMap Operations**: Mapping bogie types to capacities
6. **Sorting**: Sorting bogies by capacity using Comparator
7. **Stream Filtering**: Filtering high-capacity bogies using Streams
8. **Grouping**: Grouping bogies by type using Collectors.groupingBy
9. **Aggregation**: Counting total seats using Stream reduce
10. **Regex Validation**: Validating Train ID and Cargo Code formats using Pattern and Matcher

## Prerequisites

- Java 8 or higher
- Command line interface

## How to Run

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd TrainManagementApp
   ```

2. Compile the application:
   ```bash
   cd App/src
   javac *.java
   ```

3. Run the application:
   ```bash
   java Main
   ```

## Output

The application will display various railway management operations and their results in the console, demonstrating each use case with sample data.

## Project Structure

```
TrainManagementApp/
├── LICENSE
├── README.md
├── App/
│   └── src/
│       ├── Bogie.java
│       └── Main.java
```

## Concepts Demonstrated

- Collections Framework (List, Set, Map)
- Stream API (filtering, grouping, reduction)
- Regular Expressions (Pattern, Matcher)
- Object-Oriented Programming
- Functional Programming paradigms
