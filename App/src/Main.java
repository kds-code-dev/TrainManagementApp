package com.example.trainmanagement;

/**
 * Main class for the Train Management Application
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Train Management App!");

        // Create a sample train
        Train train = new Train("Express 101", 10);
        System.out.println("Created train: " + train.getName() + " with " + train.getCapacity() + " seats");

        // Book some seats
        train.bookSeat(1);
        train.bookSeat(2);
        System.out.println("Booked seats 1 and 2. Available seats: " + train.getAvailableSeats());
    }
}