public class Person {
    //instance variables
    private String name;
    private String surname;
    private String email;

    public Person(String name, String surname, String email) {
        //constructor to assign input values to attributes
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public void setName(String name){
        //set person name
        this.name = name;
    }

    public void setSurname(String surname){
        //set person surname
        this.surname = surname;
    }

    public void setEmail(String email){
        //set person email
        this.email = email;
    }

    public String getName(){
        //give person name
        return name;
    }

    public String getSurname(){
        //get the surname
        return surname;
    }

    public String getEmail(){
        //get the email
        return email;
    }

    public void showPerson() {
        //Method to print all the information of the person
        System.out.println("Name: "+ name +"\nSurname: "+ surname+ "\nEmail: "+ email);
    }
}
