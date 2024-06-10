/* Student ID - w2053019 / 20220855

 * MY ASSUMPTIONS
 * I am performing soft delete when canceling the ticket.
 * If the user inputs are not in correct format ask for inputs iteratively.
 * Emails are validated by searching for @ and . signs.
 * */

import java.util.*;

public class PlaneManagement {

    //Declare and initialize variables
    static int[][] seatPlan = new int[4][];
    private static final int AVAILABLE = 0;
    private static final int SOLD = 1;
    private static final Ticket[] ticketArray = new Ticket[52];
    public static int ticketCount = -1;

    /* @desc validates the user inputs and find the index in the seatPlan array
     * return index of rowLetter and seatNumber in seatPlan array */
    public static int[] input_validation() {

        //declare and initialize variables
        boolean correct = false;
        Scanner scan = new Scanner(System.in);
        String rowLetter = null;
        byte seatNumber = 0;

        //input validation
        while(!correct) {
            try {
                System.out.print("\nEnter a letter for the row(from A-D): ");
                rowLetter = scan.next();       //Get user inputs

                //validate the row letter as a letter
                if (!rowLetter.matches("[a-dA-D]")) {
                    System.out.println("Invalid input! Please enter a letter from A to D.");
                    continue;
                }

                System.out.print("Enter a seat number: ");
                seatNumber = scan.nextByte();   //Get user inputs

                //validate the seat number range
                if (!(seatNumber > 0 && seatNumber <= 14)) {
                    System.out.println("Please enter a correct seat number!");
                    scan.nextLine();    // Consume newline character
                    continue;
                }

                //check the seat in valid seat range for row B & C
                switch (rowLetter.toUpperCase()) {
                    case "B":
                    case "C":
                        if (seatNumber > 12) {     //contains only 12 seats
                            System.out.println("This row contains only 12 seats. Please try again! ");
                            scan.nextLine();       // Consume newline character
                            continue;
                        }
                        break;
                }
                correct = true;

            } catch (InputMismatchException exception) {     //validate the data type
                System.out.println("Please enter correct information!");
                scan.nextLine();                   // Consume newline character

            } catch (Exception exception) {
                System.out.println(exception);
            }
        }

        int seat = seatNumber-1;          //seat position in the seatPlan array = seatNumber-1
        int row;                          //row number in the seatPlan array[0-3]

        //select the row Number according to the row letter
        row = (rowLetter.equalsIgnoreCase("A")) ? 0 :         //using ternary operator
                (rowLetter.equalsIgnoreCase("B")) ? 1 :
                        (rowLetter.equalsIgnoreCase("C")) ? 2 :
                                3;     // D -> 3

        return new int[] {row,seat};
    }

    public static void buy_seat(){
        //This method is for buy a seat and mark the seat as sold

        //Get row number and seat number
        int[] array = input_validation();      //Get the position of the row and seat in the seatPlan array
        int rowIndex = array[0];
        int seatIndex = array[1];

        //check if the seat is available
        if ( seatPlan[rowIndex][seatIndex] == AVAILABLE){
            System.out.println("\nSeat available");
            seatPlan[rowIndex][seatIndex] = SOLD;       //mark seat as sold
            System.out.println("Seat reserved successfully! \n");

            ticketCount++;

            //Get details of the passenger from user
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your name: ");
            String name = scanner.nextLine();

            System.out.print("Enter your surname: ");
            String surname = scanner.nextLine();

            //Check the email validity
            String email = null;
            boolean valid = false;

            while (!valid) {
                System.out.print("Enter your email address: ");
                email = scanner.nextLine();

                if (email.contains("@") && email.contains(".")){    //Check if email contains @ & . symbols
                    valid = true;
                }
                else {
                    System.out.println("Email is not correct. Please try again...1\n");
                }
            }

            Person person = new Person(name,surname,email);              //Create a new person object
            Ticket ticket = new Ticket(getRow(rowIndex), seatIndex++, getPrice(seatIndex), person); //create a new ticket object

            ticket.save();                             //save ticket information in to a text file
            ticketArray[ticketCount] = ticket;         //add ticket to ticketArray

        } else {
            System.out.println("\nSorry, this seat is no longer available\n");
        }
    }

    /*@desc Calculate the price of a seat
     * @param seat number in Integer format
     * @return price as Integer    */
    private static int getPrice(int seatIndex) {

        if( seatIndex >= 0  && seatIndex < 6 ) {           // seats from 1-5
            return 200;
        } else if ( seatIndex > 5 && seatIndex < 10 ) {      // seats from 6-9
            return 150;
        } else {
            return 180;
        }
    }

    /*@desc convert rowIndex into appropiate row letter
     * @param Index for row number in the seatPlan array
     * @return row letter as char */
    private static char getRow (int rowIndex) {
        return switch (rowIndex) {
            case 0 -> 'A';
            case 1 -> 'B';
            case 2 -> 'C';
            case 3 -> 'D';
            default -> 0;
        };
    }

    public static void cancel_seat() {
        //Makes a seat available by canceling the seat

        int[] array = input_validation();     //Get index of the row and seat in the seatPlan array
        int rowIndex = array[0];
        int seatIndex = array[1];

        char row = getRow(rowIndex);          // Get the row character

        //check if the seat is already sold out
        if (seatPlan[rowIndex][seatIndex] == SOLD) {
            seatPlan[rowIndex][seatIndex] = AVAILABLE;     //change the availability status in seatPlan array

            //find seat in ticketArray to cancel the ticket
            for (int i = 0; i <ticketArray.length; i++) {
                if( ticketArray[i].getRow() == row && ticketArray[i].getSeat() == seatIndex ) {
                    ticketArray[i].setIsBooked(false);      //set the seat availability status as not booked
                    ticketArray[i].save();                  //set status as not booked in text file
                    break;
                }
            }
            System.out.println("Seat canceled successfully!\n");

        } else {
            System.out.println("No booking found! Seat is already available\n");
        }
    }

    /*@desc search for the first available seat
     * @return available first seat row and number as a String */
    public static String find_first_available() {
        String[] rowArray = {"A","B","C","D"};

        for (int row = 0; row<rowArray.length; row++) {
            for (int seat = 0; seat <seatPlan[row].length; seat++) {     //checking row by row
                if (seatPlan[row][seat] == AVAILABLE) {                 //checking the availability of each seat
                    String availableSeat = rowArray[row]+ (seat+1);
                    return availableSeat;
                }
            }
        }
        return "No available seat found!\n";
    }

    public static void show_seating_plan() {
        //this method will display the seating plan
        System.out.println("\n" + "*".repeat(48) + " Show seating Plan " + "*".repeat(48));
        System.out.println("Available seats - O \tSold seats - X\n");

        for (int row = 0; row <seatPlan.length; row++) {
            if (row == 2){                    //Print a new line before the row C to match the plane seat plan
                System.out.println();
            }
            char rowLetter = getRow(row);     //Get the row letter
            System.out.print(rowLetter + "\t");

            int rowCount = 0;
            for (int seat: seatPlan[row]){
                rowCount++;
                if (rowCount == 8) {
                    System.out.print("\t");    //To match the plane seat plan
                }

                if (seat==AVAILABLE){
                    System.out.print("O  ");    //seat available
                }
                else {
                    System.out.print("X  ");    //seat not available
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    static void print_ticket_info() {
        //show ticket information and total price

        int totalPrice = 0;

        for ( int i = 0; i <ticketArray.length; i++) {
            //skip cancelled tickets in the ticketArray
            if ( ticketArray[i] == null || !ticketArray[i].getIsBooked() ) {
                continue;
            }

            //Display ticket information by calling get_ticketInfo method
            ticketArray[i].get_ticketInfo();

            System.out.println("-".repeat(60));
            totalPrice = totalPrice + ticketArray[i].getPrice();
        }
        //Display total price
        System.out.println("Total sales: £" + totalPrice + "\n");
    }

    /*@desc search seat availability of a specific seat and
     * display the passenger details of that seat   */
    static void search_ticket() {

        int[] inputArray = input_validation();        //Get user inputs (row & seat)
        int rowNumber = inputArray[0];
        int seat = inputArray[1];

        char row = getRow(rowNumber);                 //Get the row character

        if (seatPlan[rowNumber][seat] == SOLD) {      //check seat availability
            System.out.println("\nSeat already reserved!");

            for ( int ticket = 0; ticket <ticketArray.length; ticket++) {
                //skip null or cancelled tickets in the ticketArray
                if (ticketArray[ticket] == null || !ticketArray[ticket].getIsBooked()) {
                    continue;

                } else if (ticketArray[ticket].getRow() == row && ticketArray[ticket].getSeat() == seat) {   //find the ticket of that seat

                    //Display ticket information
                    ticketArray[ticket].get_ticketInfo();
                }
            }
        } else {
            System.out.println("\nThis seat is available\n");
        }
    }

    //Main programme starts
    public static void main(String[] args){
        //Declare and initialize variables
        seatPlan[0] = new int[14];    //A = 0
        seatPlan[1] = new int[12];    //B = 1
        seatPlan[2] = new int[12];    //C = 2
        seatPlan[3] = new int[14];    //D = 3

        //set all the seats Available
        for (int index = 0; index< seatPlan.length; index++) {
            for (int indexTwo = 0; indexTwo<seatPlan[index].length; indexTwo++) {
                seatPlan[index][indexTwo] = AVAILABLE;
            }
        }

        Scanner scan = new Scanner(System.in);
        boolean choice = true;
        byte option = 0;

        //creating the star border
        String starLine = "*".repeat(114);
        System.out.println("\nWelcome to the Plane Management application");

        //Display the menu
        do {
            System.out.println(starLine);
            System.out.println("*" + " ".repeat(50) +"MENU OPTIONS" +  " ".repeat(50)+"*");
            System.out.println(starLine);
            System.out.println("\n" + " ".repeat(20) + "1) Buy a seat");
            System.out.println(" ".repeat(20) + "2) Cancel a seat");
            System.out.println(" ".repeat(20) + "3) Find first available seat");
            System.out.println(" ".repeat(20) + "4) Show seating plan");
            System.out.println(" ".repeat(20) + "5) Print tickets information and total sales");
            System.out.println(" ".repeat(20) + "6) Search ticket");
            System.out.println(" ".repeat(20) + "0) Quit");
            System.out.println("\n" + starLine + "\n");

            try {
                //Get input for the option
                System.out.print("Please select an option: ");

                if (scan.hasNextByte()) {         //validate the data type for option
                    option = scan.nextByte();
                }
                else{
                    System.out.println("Invalid option! Only numbers from 0 to 6 can be entered");
                    String input = scan.next();   //capture the wrong input data
                    continue;
                }
            }
            catch (InputMismatchException exception){
                System.out.println("please enter a number from 0 to 6");
                scan.nextLine();
                continue;
            }
            catch (Exception exception) {
                System.out.println(exception);
            }

            //Using a switch statement to handle user input options
            switch (option) {
                case 1:
                    buy_seat();                 //Make a reservation
                    break;
                case 2:
                    cancel_seat();              //Make a seat cancellation
                    break;
                case 3:
                    System.out.println("First seat available: "+find_first_available());     //Find the first seat available
                    break;
                case 4:
                    show_seating_plan();         //Show seating plan
                    break;
                case 5:
                    print_ticket_info();        //Display all tickets and total sales
                    break;
                case 6:
                    search_ticket();            // Search for a ticket and display information
                    break;
                case 0:
                    System.out.println("Thank you! \nExiting the program...");
                    choice = false;            //terminates the loop
                    break;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
        while (choice!= false);
    }
}

