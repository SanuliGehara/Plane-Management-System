import java.io.FileWriter;
import java.io.IOException;

public class Ticket {
    //Create attributes
    private char row;
    private int seat;
    private int price;
    private boolean isBooked;
    private Person person;

    //A constructor that takes user inputs to create a ticket object
    public Ticket(char row, int seat, int price, Person person) {
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;
        this.isBooked = true;
    }

    //Constructor with empty parameters
    public Ticket() {}

    //Create getters for all the attributes
    public char getRow() {
        return row;
    }

    public int getSeat() {
        return seat;
    }

    public int getPrice() {
        return price;
    }
    public Person getPerson() {
        return person;
    }

    //Method to display ticket information
    public void get_ticketInfo() {
        System.out.println("\n" +"*".repeat(20) +" Ticket information "+ "*".repeat(20) );
        System.out.println("Seat row: " + row);
        System.out.println("Seat number: " + (seat + 1));   //seat index in array = seat-1
        System.out.println("Ticket price: £" + price);
        System.out.println("\n" +"*".repeat(20) +" Passenger details "+ "*".repeat(20) );
        System.out.println("Passenger name: " + person.getName() + " " + person.getSurname());
        System.out.println("Email address: " + person.getEmail());
    }

    /**
     * @return booking status of the seat (true or false)
     */
    public boolean getIsBooked() {
        return isBooked;
    }

    //Create all the setters
    public void setIsBooked(boolean isBooked) {
        this.isBooked = isBooked; }

    public void setRow(char row) {
        this.row = row; }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    /* write ticket and person information to a text file */
    public void save() {
        String fileName = (this.row + String.valueOf(this.seat + 1) + ".txt");     //Assign the seat row and number a fileName

        try {
            FileWriter file = new FileWriter(fileName);
            file.write("Ticket information\n");
            file.write("Seat row letter: " + this.row + "\n");
            file.write("Seat number: " + (this.seat + 1) + "\n");
            file.write("Seat price: " + this.price + "\n");
            file.write("Seat is Booked (True/ False): " + this.isBooked+ "\n");

            file.write("\nPassenger details \nName: " + this.person.getName()+ "\n");
            file.write("Surname: " + this.person.getSurname()+ "\n");
            file.write("Email: " + this.person.getEmail()+ "\n");

            file.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
