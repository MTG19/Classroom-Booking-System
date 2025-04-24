/*
 * Classroom Booking System
 * ------------------------
 * This Java program allows users to manage classroom bookings efficiently.
 * 
 * Features:
 * - Add classrooms with details (room number, capacity, projector availability).
 * - Display all available classrooms.
 * - Make bookings by specifying a classroom, date, and time.
 * - Cancel or modify existing bookings.
 * - View all bookings and filter them by date.
 * 
 * Concepts Used:
 * - Object-Oriented Programming (OOP) (Classes: Classroom, Booking)
 * - Data Structures (ArrayList for storing classrooms and bookings)
 * - User Input Handling (Scanner for interactive menu)
 * - String Manipulation and Formatting
 */

import java.util.*;

// Class representing a Classroom
class Classroom {
    private String roomNumber; // Room number of the classroom
    private int capacity; // Capacity of the classroom
    private boolean hasProjector; // Whether the classroom has a projector

    // Constructor to initialize a Classroom object
    public Classroom(String roomNumber, int capacity, boolean hasProjector) {
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.hasProjector = hasProjector;
    }

    // Getter method for room number
    public String getRoomNumber() {
        return roomNumber;
    }

    // Getter method for capacity
    public int getCapacity() {
        return capacity;
    }

    // Getter method for projector availability
    public boolean hasProjector() {
        return hasProjector;
    }

    // Override toString method to provide a string representation of the classroom
    @Override
    public String toString() {
        return "Room: " + roomNumber + ", Capacity: " + capacity + ", Projector: " + (hasProjector ? "Yes" : "No");
    }
}

// Class representing a Booking
class Booking {
    private String roomNumber; // Room number of the booking
    private String date; // Date of the booking
    private String time; // Time of the booking
    private String bookedBy; // Name of the person who made the booking

    // Constructor to initialize a Booking object
    public Booking(String roomNumber, String date, String time, String bookedBy) {
        this.roomNumber = roomNumber;
        this.date = date;
        this.time = time;
        this.bookedBy = bookedBy;
    }

    // Getter method for room number
    public String getRoomNumber() {
        return roomNumber;
    }

    // Getter method for date
    public String getDate() {
        return date;
    }

    // Getter method for time
    public String getTime() {
        return time;
    }

    // Getter method for the name of the person who made the booking
    public String getBookedBy() {
        return bookedBy;
    }

    // Override toString method to provide a string representation of the booking
    @Override
    public String toString() {
        return "Room: " + roomNumber + ", Date: " + date + ", Time: " + time + ", Booked by: " + bookedBy;
    }
}

public class ClassroomBookingSystem {
    private static List<Classroom> classrooms = new ArrayList<>(); // List to store classrooms
    private static List<Booking> bookings = new ArrayList<>(); // List to store bookings
    private static Scanner scanner = new Scanner(System.in); // Scanner for user input

    public static void main(String[] args) {

        System.out.println("\n_____________________________________" +
                            "\nWelcome to Classroom Booking System!" +
                            "\n_____________________________________");
        // Main loop to display the menu and process user's choice
        while (true) {
            // Display the main menu
            System.out.println("\nBooking System Menu:");
            System.out.println("1. Add Classroom");
            System.out.println("2. Show Classrooms");
            System.out.println("3. Make Booking");
            System.out.println("4. Cancel Booking");
            System.out.println("5. Edit Booking");
            System.out.println("6. Show Bookings");
            System.out.println("7. Show Bookings by Date");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt(); // Read user's choice
            scanner.nextLine(); // Consume newline

            // Execute the corresponding method based on user's choice
            switch (choice) {
                case 1:
                    addClassroom();
                    break;
                case 2:
                    showClassrooms();
                    break;
                case 3:
                    makeBooking();
                    break;
                case 4:
                    cancelBooking();
                    break;
                case 5:
                    editBooking();
                    break;
                case 6:
                    showBookings();
                    break;
                case 7:
                    showBookingsByDate();
                    break;
                case 8:
                    System.out.println("\n___________________"+
                                       "\nExiting... Goodbye!"+
                                       "\n___________________\n");
                    return; // Exit the program
                default:
                    System.out.println("\nInvalid choice. Please try again.");
            }
        }
    }

    // Method to add a new classroom
    private static void addClassroom() {
        System.out.print("\nEnter room number: ");
        String roomNumber = scanner.nextLine(); // Read room number
        System.out.print("Enter capacity: ");
        int capacity = scanner.nextInt(); // Read capacity
        System.out.print("Does it have a projector? (true/false): ");
        boolean hasProjector = scanner.nextBoolean(); // Read projector availability
        scanner.nextLine(); // Consume newline
        classrooms.add(new Classroom(roomNumber, capacity, hasProjector)); // Add new classroom to the list
        System.out.println("\nClassroom added successfully!\n");
        System.out.println("_______________________________"+
                           "\n\nWhat would you like to do next?");
    }

    // Method to show all classrooms
    private static void showClassrooms() {
        if (classrooms.isEmpty()) {
            System.out.println("\nNo classrooms available.");
        } else {
            System.out.println("\nAvailable Classrooms:");
            classrooms.forEach(System.out::println); // Print each classroom
        }
        System.out.println("_______________________________"+
                           "\n\nWhat would you like to do next?");
    }

    // Method to make a new booking
    private static void makeBooking() {
        System.out.print("\nEnter room number: ");
        String roomNumber = scanner.nextLine(); // Read room number
        System.out.print("Enter date (YYYY-MM-DD): ");
        String date = scanner.nextLine(); // Read date
        System.out.print("Enter time (HH:MM): ");
        String time = scanner.nextLine(); // Read time
        System.out.print("Enter your name: ");
        String bookedBy = scanner.nextLine(); // Read name of the person booking

        // Check if the room is already booked for the specified date and time
        for (Booking booking : bookings) {
            if (booking.getRoomNumber().equals(roomNumber) && booking.getDate().equals(date) && booking.getTime().equals(time)) {
                System.out.println("\nError: Room is already booked for the specified date and time.\n");
                return; // Exit the method if the room is already booked
            }
        }

        bookings.add(new Booking(roomNumber, date, time, bookedBy)); // Add new booking to the list
        System.out.println("\nBooking made successfully!\n");
        System.out.println("_______________________________"+
                           "\n\nWhat would you like to do next?");
    }

    // Method to cancel a booking
    private static void cancelBooking() {
        System.out.print("\nEnter room number: ");
        String roomNumber = scanner.nextLine(); // Read room number
        System.out.print("Enter date: ");
        String date = scanner.nextLine(); // Read date
        System.out.print("Enter time: ");
        String time = scanner.nextLine(); // Read time

        // Remove the booking if it matches the specified room number, date, and time
        boolean removed = bookings.removeIf(booking -> booking.getRoomNumber().equals(roomNumber) && booking.getDate().equals(date) && booking.getTime().equals(time));
        if (removed) {
            System.out.println("\nBooking canceled successfully!\n");
        } else {
            System.out.println("\nError: Booking not found.\n");
        }
        System.out.println("_______________________________"+
                           "\n\nWhat would you like to do next?");
    }

    // Method to edit an existing booking
    private static void editBooking() {
        System.out.print("\nEnter room number to edit: ");
        String roomNumber = scanner.nextLine(); // Read room number
        System.out.print("Enter date : ");
        String date = scanner.nextLine(); // Read date
        System.out.print("Enter time : ");
        String time = scanner.nextLine(); // Read time

        // Find the booking to edit
        for (Booking booking : bookings) {
            if (booking.getRoomNumber().equals(roomNumber) && booking.getDate().equals(date) && booking.getTime().equals(time)) {
                System.out.println("Lets start editing the booking: ");
                System.out.print("Enter your name: ");
                String newName = scanner.nextLine(); // Read new name
                System.out.print("\nEnter room numbre: ");
                String newRoomNumber = scanner.nextLine(); // Read new room number
                System.out.print("\nEnter date (YYYY-MM-DD): ");
                String newDate = scanner.nextLine(); // Read new date
                System.out.print("\nEnter time (HH:MM): ");
                String newTime = scanner.nextLine(); // Read new time
                bookings.remove(booking); // Remove the old booking
                bookings.add(new Booking(newRoomNumber, newDate, newTime, newName)); // Add the updated booking
                System.out.println("\nBooking updated successfully!\n");
                return; // Exit the method after updating the booking
            }
        }
        System.out.println("\nError: Booking not found.\n");
        System.out.println("_______________________________"+
                           "\n\nWhat would you like to do next?");
    }

    // Method to show all bookings
    private static void showBookings() {
        if (bookings.isEmpty()) {
            System.out.println("\nNo bookings available.\n");
        } else {
            System.out.println("\nAll Bookings:");
            bookings.forEach(System.out::println); // Print each booking
        }
        System.out.println("_______________________________"+
                           "\n\nWhat would you like to do next?");  
    }

    // Method to show bookings for a specific date
    private static void showBookingsByDate() {
        System.out.print("\nEnter date (YYYY-MM-DD): ");
        String date = scanner.nextLine(); // Read date
        boolean found = false; // Flag to check if any bookings are found
        for (Booking booking : bookings) {
            if (booking.getDate().equals(date)) {
                System.out.println("\nBookings for " + date + ":"); // Print the date header
                System.out.println(booking); // Print the booking
                found = true; // Set flag to true if a booking is found
            }
        }
        if (!found) System.out.println("\nNo bookings for this date.\n"); // Print message if no bookings are found
        System.out.println("_______________________________"+
                           "\n\nWhat would you like to do next?");
    }
}
