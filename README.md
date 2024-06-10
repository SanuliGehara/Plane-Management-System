# Plane Management System

The Plane Management System, a Java-based application developed for managing and tracking seat reservations on a private plane. The system allows users to reserve and cancel seats, find available seats, and view ticket information and sales details.

## Features :sparkles:
- **Seat Management**: Track available and sold seats using arrays.
- **User Interaction**: Menu-driven interface for easy navigation.
- **Reservation System**: Buy and cancel seat reservations.
- **Ticket Information**: Store and display detailed ticket and passenger information.
- **Save Tickets to Files**: Save ticket information to text files for each reservation.
- **Sales Reporting**: Display total sales and detailed ticket information.

## Usage
1. Run the program to display the main menu:
    ```plaintext
    Welcome to the Plane Management application
    ```
2. Choose from the following options:
    - `1`: Buy a seat
    - `2`: Cancel a seat
    - `3`: Find the first available seat
    - `4`: Show seating plan
    - `5`: Print tickets information and total sales
    - `6`: Search for a ticket
    - `0`: Exit the program

## Classes and Methods :page_facing_up:

### PlaneManagement  :airplane:
The `PlaneManagement` class contains the main logic for managing the plane's seating and handling user interactions.

- **Main Method**: Entry point of the program. Displays the welcome message and main menu.
  
- **buy_seat**: Prompts user for seat details, validates, and records the seat as sold.
- **cancel_seat**: Prompts user for seat details, validates, and records the seat as available.
- **find_first_available**: Finds the first available seat starting from row A to D.
- **show_seating_plan**: Displays the current seating plan.
- **print_ticket_info**: Prints all sold tickets and calculates total sales.
- **search_ticket**: Searches for a specific ticket and displays its information.

### Ticket :ticket:
The `Ticket` class represents a ticket for a seat on the plane.

- **Attributes**: row, seat, price, isBooked, person.
- **Methods**:
  - `Ticket(char row, int seat, int price, Person person)`: Constructor to create a ticket.
  - `getRow()`, `getSeat()`, `getPrice()`, `getPerson()`, `getIsBooked()`: Getters for ticket attributes.
  - `setIsBooked(boolean isBooked)`, `setRow(char row)`, `setSeat(int seat)`, `setPrice(int price)`, `setPerson(Person person)`: Setters for ticket attributes.
  - `get_ticketInfo()`: Displays ticket information.
  - `save()`: Writes ticket and passenger information to a text file.

### Person :blond_haired_person:
The `Person` class represents a passenger.

- **Attributes**: name, surname, email.
- **Methods**:
  - `Person(String name, String surname, String email)`: Constructor to create a person.
  - `getName()`, `getSurname()`, `getEmail()`: Getters for person attributes.
  - `setName(String name)`, `setSurname(String surname)`, `setEmail(String email)`: Setters for person attributes.


