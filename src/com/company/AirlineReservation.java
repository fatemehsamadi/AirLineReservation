package com.company;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class Flight {
    private String flightNumber;
    private String origin;
    private String destination;
    private String date;
    private String time;
    private int seats;
    private int price;

    public Flight(String flightNumber, String origin, String destination, String date, String time,int seats,int price) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.date = date;
        this.time = time;
        this.seats = seats;
        this.price = price;

    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String  flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {

        return String.format("%-10s %-10s %-12s %-10s %-6s %-6s %-10s%n",flightNumber,origin,destination,date,time,seats,price);
    }
}
class User{
    private static int nextId = 1;


    private int id;
    private String username;
    private String password;
    private boolean isAdmin;
    private int charge;
    private ArrayList<Flight> reserved = new ArrayList<>();
    private ArrayList<String> ticketID = new ArrayList<>();
    private ArrayList<Flight> flights;

    public User(String username, String password,boolean isAdmin,ArrayList<Flight> flights) {
        this.id = nextId++;
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
        this.charge = 0;
        this.flights = flights;


    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public ArrayList<String> getTicketID() {
        return ticketID;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setCharge(int charge){
        this.charge += charge;
    }
    public int getCharge(){
        return charge;
    }
    public boolean isFlightNumberUnique(String flightNumber) {
        for (Flight flight : this.flights) {
            if (flight.getFlightNumber().equals(flightNumber)) {
                return false;
            }
        }
        return true;
    }
    public int getFlightIndexByNumber(String flightNumber) {
        for (int i = 0; i < flights.size(); i++) {
            if (flights.get(i).getFlightNumber().equals(flightNumber)) {
                return i;
            }
        }
        return -1;
    }
    public int getTicketIndexByNumber(String ticket) {
        for (int i = 0; i < this.ticketID.size(); i++) {
            if (ticketID.get(i).equals(ticket)) {
                return i;
            }
        }
        return -1;
    }
    public void addFlight() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter flight number:");
        String flightNumber = scanner.next();
        System.out.println("Enter origin:");
        String origin = scanner.next();
        System.out.println("Enter destination:");
        String destination = scanner.next();
        System.out.println("Enter date (YYYY/MM/DD):");
        String date = scanner.next();
        System.out.println("Enter time (hh:mm):");
        String time = scanner.next();
        System.out.println("Enter seats available");
        int seats = scanner.nextInt();
        System.out.println("Enter ticket's Price");
        int price = scanner.nextInt();

        if (isFlightNumberUnique(flightNumber)) {
            this.flights.add(new Flight(flightNumber, origin, destination, date, time,seats,price));
            System.out.println("Flight added successfully.");
        } else {
            System.out.println("Flight number already exists. Cannot add.");
        }
    }
    public void updateFlight() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter flight number to update:");
        String flightNumber = scanner.nextLine();

        int flightIndex = getFlightIndexByNumber(flightNumber);

        if (flightIndex >= 0) {
            System.out.println("Enter new origin (existing value " + flights.get(flightIndex).getOrigin() + " value):");
            String origin = scanner.nextLine();
            if (!origin.isEmpty()) {
                flights.get(flightIndex).setOrigin(origin);
            }

            System.out.println("Enter new destination (existing value " + flights.get(flightIndex).getDestination() + " value):");
            String destination = scanner.nextLine();
            if (!destination.isEmpty()) {
                flights.get(flightIndex).setDestination(destination);
            }

            System.out.println("Enter new Date (YYYY/MM/DD) (existing value " + flights.get(flightIndex).getDate() + " value):");
            String date = scanner.nextLine();
            if (!date.isEmpty()) {
                flights.get(flightIndex).setDate(date);
            }

            System.out.println("Enter new time (hh:mm) (existing value " + flights.get(flightIndex).getTime() + " value):");
            String time = scanner.nextLine();
            if (!time.isEmpty()) {
                flights.get(flightIndex).setTime(time);
            }

            System.out.println("Enter new available seats (existing value " + flights.get(flightIndex).getSeats() + " value):");
            int seats = scanner.nextInt();

            flights.get(flightIndex).setSeats(seats);

            System.out.println("Enter new Price (existing value " + flights.get(flightIndex).getPrice() + " value):");
            int price = scanner.nextInt();
            flights.get(flightIndex).setPrice(price);


            System.out.println("Flight updated successfully.");
        } else {
            System.out.println("Flight number does not exist. Cannot update.");
        }
    }
    public void deleteFlight() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter flight number to delete:");
        String flightNumber = scanner.next();

        int flightIndex = getFlightIndexByNumber(flightNumber);

        if (flightIndex >= 0) {
            flights.remove(flightIndex);
            System.out.println("Flight deleted successfully.");
        } else {
            System.out.println("Flight number does not exist. Cannot delete.");
        }
    }
    public void printFlights(){
        System.out.println("FlightId   Origin     Destination  Date       time   Seats  Price");
        for (Flight flight : flights) {
            System.out.print(flight.toString());
        }

    }
    public void changePassword(){
        System.out.println("Enter Your new Password:");
        Scanner scanner = new Scanner(System.in);
        String newPassword = scanner.next();
        setPassword(newPassword);
        System.out.println("Your password successfully changed.");
    }
    public void searchFlightTickets(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("1.Search based on flight ID");
        System.out.println("2.Search based on origin");
        System.out.println("3.Search based on destination");
        System.out.println("4.Search based on date");
        System.out.println("5.Search based on time");
        System.out.println("6.Search based on price");

        int choice = 0;
        choice = scanner.nextInt();

        switch(choice){
            case 1 ->{
                System.out.println("Enter your flight ID");
                String flightID = scanner.next();
                System.out.println("FlightId   Origin     Destination  Date       time   Seats  Price");
                for (Flight flight : flights) {
                    if(flight.getFlightNumber().contains(flightID))
                        System.out.print(flight.toString());
                }
            }

            case 2 ->{
                System.out.println("Enter your origin");
                String origin = scanner.next();
                System.out.println("FlightId   Origin     Destination  Date       time   Seats  Price");
                for (Flight flight : flights) {
                    if(flight.getOrigin().contains(origin))
                        System.out.print(flight.toString());
                }
            }
            case 3 ->{
                System.out.println("Enter your destination");
                String destination = scanner.next();
                System.out.println("FlightId   Origin     Destination  Date       time   Seats  Price");
                for (Flight flight : flights) {
                    if(flight.getDestination().contains(destination))
                        System.out.print(flight.toString());
                }
            }
            case 4 ->{
                System.out.println("Enter your date(YYYY/MM/DD)");
                String date = scanner.next();
                System.out.println("FlightId   Origin     Destination  Date       time   Seats  Price");
                for (Flight flight : flights) {
                    if(flight.getDate().equals(date))
                        System.out.print(flight.toString());
                }
            }
            case 5 ->{
                System.out.println("Enter your time(HH:MM)");
                String time = scanner.next();
                System.out.println("FlightId   Origin     Destination  Date       time   Seats  Price");
                for (Flight flight : flights) {
                    if(flight.getTime().equals(time))
                        System.out.print(flight.toString());
                }
            }
            case 6 ->{
                System.out.println("Enter your min Price");
                int min = scanner.nextInt();
                System.out.println("Enter your max Price");
                int max = scanner.nextInt();
                System.out.println("FlightId   Origin     Destination  Date       time   Seats  Price");
                for (Flight flight : flights) {
                    if(flight.getPrice() >= min && flight.getPrice() <= max)
                        System.out.print(flight.toString());
                }
            }
        }
    }
    public void printReserved(){
        System.out.println("FlightId   Origin     Destination  Date       time   Seats  Price");
        for (Flight flight : reserved) {
            System.out.print(flight.toString());
        }

    }
    public StringBuilder generateTicket(){
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        int stringLength = 6;
        StringBuilder randomString = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < stringLength; i++) {
            int index = random.nextInt(alphabet.length());
            randomString.append(alphabet.charAt(index));
        }
        return randomString;
    }
    public void bookTicket(){
        System.out.println("Enter Flight ID you want to book:");
        Scanner scanner = new Scanner(System.in);
        String flightID = scanner.next();
        Flight reserving = null;
        for (Flight flight : flights) {
            reserving = flight;
            break;
        }
        if(reserving == null){
            System.out.println("No Flight with this id is available");
        }
        else{
            if(reserving.getSeats()==0){
                System.out.println("No available seat");
            }
            else{
                if(this.charge < reserving.getPrice()){
                    System.out.println("You don't have enough charge");
                }
                else {
                    this.charge -= reserving.getPrice();
                    reserving.setSeats(reserving.getSeats() - 1);
                    reserved.add(reserving);
                    StringBuilder ticket = generateTicket();
                    ticketID.add(ticket.toString());
                    System.out.println("Your ticket ID is " + ticket);
                }
            }
        }
    }
    public void cancelTicket(){
        System.out.println("Enter Your Ticket ID:");
        Scanner scanner = new Scanner(System.in);
        String ticketid = scanner.next();
        int index  = getTicketIndexByNumber(ticketid);
        if(index >= 0){
            ticketID.remove(index);
            Flight canceled = flights.get(index);
            canceled.setSeats(canceled.getSeats()+1);
            charge += canceled.getPrice();
            reserved.remove(index);
            System.out.println("Your ticket canceled successfully");
        }
        else{
            System.out.println("No ticket found");

        }

    }
    public void addCharge(){
        System.out.println("How much do you want to charge?");
        Scanner scanner = new Scanner(System.in);
        int charge = scanner.nextInt();
        setCharge(charge);
    }
    public int printMenu(){
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        if(isAdmin){
            System.out.println("1.Add Flight");
            System.out.println("2.Update Flight");
            System.out.println("3.Delete Flight");
            System.out.println("4.Schedule Flights");
            System.out.println("0.Sign out");
            choice = scanner.nextInt();
            switch (choice) {
                case 1 -> {
                    addFlight();
                    return 1;
                }
                case 2 -> {
                    updateFlight();
                    return 1;
                }
                case 3 -> {
                    deleteFlight();
                    return 1;
                }
                case 4 -> {
                    printFlights();
                    return 1;
                }
                case 0 -> {
                    return -1;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
        else{
            System.out.println("1.Change Password");
            System.out.println("2.Search Flight tickets");
            System.out.println("3.Booking ticket");
            System.out.println("4.Canceling ticket");
            System.out.println("5.Booked tickets");
            System.out.println("6.Add charge");
            System.out.println("0.Sign out");
            choice = scanner.nextInt();
            switch (choice) {
                case 1 -> {
                    changePassword();
                    return 1;
                }
                case 2 -> {
                    searchFlightTickets();
                    return 1;
                }
                case 3 -> {
                    bookTicket();
                    return 1;
                }
                case 4 -> {
                    cancelTicket();
                    return 1;
                }
                case 5 -> {
                    printReserved();
                    return 1;
                }
                case 6 -> {
                    addCharge();
                    return 1;
                }
                case 0 -> {
                    return -1;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
        return -1;
    }
}
public class AirlineReservation {
    private static ArrayList<Flight> flights = new ArrayList<>();
    private static ArrayList<User> users = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static boolean LoggedIn = false;

    public static void main(String[] args) {
        User currentUser = null;
        while(true)
        {
            while (currentUser == null) {
                System.out.println("1.Sign in");
                System.out.println("2.Sign up");
                System.out.println("3.Exit");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        currentUser = login();
                        break;
                    case 2:
                        SignUp();
                        break;
                    case 3:
                        System.exit(0);
                    default:
                        System.out.println("Invalid option. Try again.");
                }
            }
            while(true){
                if(currentUser.printMenu() == -1){
                    currentUser = null;
                    break;
                }

            }

        }

    }

    private static User login() {
        System.out.println("Enter your username :");
        String inputUsername = scanner.next();
        System.out.println("Enter your password :");
        String inputPassword = scanner.next();
        for (User user : users) {
            if (user.getUsername().equals(inputUsername) && user.getPassword().equals(inputPassword)) {
                return user;
            }
        }
        if(inputPassword.equals("admin") && inputUsername.equals("admin")){
            User admin = new User("admin","admin",true,flights);
            users.add(admin);
            return admin;
        }
        System.out.println("Invalid credentials. Please try again.");
        return null;

    }
    private static User SignUp() {
        System.out.println("Enter your username:");
        String inputUsername = scanner.next();
        System.out.println("Enter your password :");
        String inputPassword = scanner.next();
        User newUser = new User(inputUsername, inputPassword,false,flights);
        users.add(newUser);
        System.out.println("User Created.");
        return null;
    }

}
