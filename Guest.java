package WorkshopAttendanceApp;


import java.util.Objects;

public class Guest {

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;




    public Guest(String lastName, String firstName, String email, String phoneNumber) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.phoneNumber = phoneNumber;

    }



    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Guest guest = (Guest) obj;
        return Objects.equals(firstName, guest.firstName) && Objects.equals(lastName, guest.lastName) && Objects.equals(email, guest.email) && Objects.equals(phoneNumber, guest.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email, phoneNumber);
    }

    @Override
    public String toString() {
        return "Nume: " + lastName + " " + firstName + ", Email: " + email + ", Telefon: " + phoneNumber;

    }

    public String fullName() {
        return this.lastName + " " + this.firstName;
    }
}