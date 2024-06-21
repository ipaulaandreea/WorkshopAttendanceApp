package WorkshopAttendanceApp;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class Main {
    private static void showCommands() {
        System.out.println("help         - Afiseaza aceasta lista de comenzi");
        System.out.println("add          - Adauga o noua persoana (inscriere)");
        System.out.println("check        - Verifica daca o persoana este inscrisa la eveniment");
        System.out.println("remove       - Sterge o persoana existenta din lista");
        System.out.println("update       - Actualizeaza detaliile unei persoane");
        System.out.println("guests       - Lista de persoane care participa la eveniment");
        System.out.println("waitlist     - Persoanele din lista de asteptare");
        System.out.println("available    - Numarul de locuri libere");
        System.out.println("guests_no    - Numarul de persoane care participa la eveniment");
        System.out.println("waitlist_no  - Numarul de persoane din lista de asteptare");
        System.out.println("subscribe_no - Numarul total de persoane inscrise");
        System.out.println("search       - Cauta toti invitatii conform sirului de caractere introdus");
        System.out.println("save         - Salveaza lista cu invitati");
        System.out.println("restore      - Completeaza lista cu informatii salvate anterior");
        System.out.println("reset        - Sterge informatiile salvate despre invitati");
        System.out.println("quit         - Inchide aplicatia");
    }

    private static boolean isValidEmail (String email){
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private static boolean isValidPhoneNumber (String phoneNumber){
        String phoneRegex = "^\\+40\\d{9}$";
        Pattern pattern = Pattern.compile(phoneRegex);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    private static void addNewGuest(Scanner sc, GuestsList list) {
        String email;
        String phoneNumber;
        System.out.println("Se adauga o noua persoana...");
        System.out.println("Introduceti numele de familie:");
        String lastName = sc.nextLine();
        System.out.println("Introduceti prenumele:");
        String firstName = sc.nextLine();

        do {
            System.out.println("Introduceti email:");
            email = sc.nextLine();
            if (!isValidEmail(email)){
                System.out.println("Adresa email invalida");
            }
        } while (!isValidEmail(email));

        do {
            System.out.println("Introduceti numar de telefon (format „+40733386463“):");
            phoneNumber = sc.nextLine();
            if (!isValidPhoneNumber(phoneNumber)){
                System.out.println("Numar de telefon invalid");
            }
        } while (!isValidPhoneNumber(phoneNumber));


        list.add(new Guest(lastName, firstName, email, phoneNumber));
    }

    private static void checkGuest(Scanner sc, GuestsList list) {
        int choice;

        do {
            System.out.println("Alege modul de verificare, tastand:");
            System.out.println("\"1\" - Nume si prenume");
            System.out.println("\"2\" - Email");
            System.out.println("\"3\" - Numar de telefon (format \"+40733386463\")");
            choice = sc.nextInt();
            sc.nextLine();
            if (choice < 1 || choice > 3) {
                System.out.println("Optiune invalida");
            }
        } while (choice < 1 || choice > 3);

        Guest found = null;

        if (choice == 1) {
            System.out.println("Introduceti prenumele:");
            String firstName = sc.nextLine();
            System.out.println("Introduceti numele de familie:");
            String lastName = sc.nextLine();
            found = list.search(firstName, lastName);
        } else if (choice == 2){
            System.out.println("Introduceti email:");
            String email = sc.nextLine();
            found = list.search(choice, email);
        } else {
            System.out.println("Introduceti numarul de telefon:");
            String phoneNumber = sc.nextLine();
            found = list.search(choice, phoneNumber);
        }

        if (found != null) {
            System.out.println(found.toString());
        } else {
            System.out.println("No match found");
        }
    }

    private static void removeGuest(Scanner sc, GuestsList list) {
        System.out.println("Se sterge o persoana existenta din lista...");
        System.out.println("Alege modul de autentificare, tastand:");
        System.out.println("\"1\" - Nume si prenume");
        System.out.println("\"2\" - Email");
        System.out.println("\"3\" - Numar de telefon (format \"+40733386463\")");
        int choice = sc.nextInt();
        sc.nextLine();
        boolean removed = false;
        if (choice < 1 || choice > 3) {
                System.out.println("Optiune invalida");
                return;
            }

        if (choice == 1) {
            System.out.println("Introduceti prenumele si numele de familie:");

            String firstName = sc.nextLine();
            String lastName = sc.nextLine();
            removed = list.remove(firstName, lastName);


        } if (choice == 2){
            System.out.println("Introduceti email:");
            String email = sc.nextLine();
            removed = list.remove(2, email);


        } if (choice == 3) {
            System.out.println("Introduceti numarul de telefon:");
            String phoneNumber = sc.nextLine();
            removed = list.remove(3, phoneNumber);

        }

        if (!removed) {
            System.out.println("Eroare: Persoana nu este inscrisa la eveniment.");
        }

    }

    private static void updateGuest(Scanner sc, GuestsList list) {
        System.out.println("Se actualizeaza detaliile unei persoane...");
        System.out.println("Alege modul de autentificare, tastand:");
        System.out.println("\"1\" - Nume si prenume");
        System.out.println("\"2\" - Email");
        System.out.println("\"3\" - Numar de telefon (format \"+40733386463\")");
        int choice = sc.nextInt();
        sc.nextLine();
        Guest found = null;
        if (choice < 1 || choice > 3) {
            System.out.println("Optiune invalida");
            }

        if (choice == 1) {
            System.out.println("Introduceti numele de familie:");
            String lastName = sc.nextLine();
            System.out.println("Introduceti prenumele:");
            String firstName = sc.nextLine();
            found = list.search(firstName, lastName);


        } if (choice == 2){
            System.out.println("Introduceti adresa de email:");
            String email = sc.nextLine();
            found = list.search(choice, email);


        } if (choice == 3) {
            System.out.println("Introduceti numarul de telefon (format „+40733386463“):");
            String phoneNumber = sc.nextLine();
            found = list.search(3, phoneNumber);


        }
        if (found != null) {
            System.out.println("Alege campul de actualizat, tastand:");
            System.out.println("\"1\" - Nume");
            System.out.println("\"2\" - Prenume");
            System.out.println("\"3\" - Email");
            System.out.println("\"4\" - Numar de telefon (format \"+40733386463\")");

            int updateChoice = sc.nextInt();
            sc.nextLine();
            if (updateChoice < 1 || updateChoice > 4) {
                System.out.println("Optiune invalida");
            }
            if (updateChoice == 1) {
                System.out.println("Introduceti numele de familie:");
                String newLastName = sc.nextLine();

                found.setLastName(newLastName);
            }

            if (updateChoice == 2) {
                System.out.println("Introduceti prenumele:");
                String newFirstName = sc.nextLine();

                found.setFirstName(newFirstName);
            }
            if (updateChoice == 3) {
                System.out.println("Introduceti adresa de email:");
                String newEmail = sc.nextLine();
                found.setEmail(newEmail);
            }

            if (updateChoice == 4) {
                System.out.println("Introduceti numarul de telefon:");
                String newPhone = sc.nextLine();
                found.setPhoneNumber(newPhone);
            }
        } else {
            System.out.println("No match found");
        }

    }

    private static void searchList(Scanner sc, GuestsList list) {
        System.out.println("Tasteaza termenul de cautare:");
        String word = sc.nextLine();
        List<Guest> matches = list.partialSearch(word);
        if (matches.isEmpty()){
                System.out.println("Nothing found");
        } else {
            for (Guest guest : matches){
                System.out.println(guest.toString());
            }
        }

    }

    public static void main(String[] args) {
        System.out.println("Bun venit! Introduceti numarul de locuri disponibile:");
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        scanner.nextLine();

        GuestsList list = new GuestsList(size);

        boolean running = true;
        while (running) {
            System.out.println("Asteapta comanda: (help - Afiseaza lista de comenzi)");
            String command = scanner.nextLine();

            switch (command) {
                case "help":
                    showCommands();
                    break;
                case "add":
                    addNewGuest(scanner, list);
                    break;
                case "check":
                    checkGuest(scanner, list);
                    break;
                case "remove":
                    removeGuest(scanner, list);
                    break;
                case "update":
                    updateGuest(scanner, list);
                    break;
                case "guests":
                    list.showGuestsList();
                    break;
                case "waitlist":
                    list.showWaitingList();
                    break;
                case "available":
                    System.out.println("Numarul de locuri ramase: " + list.numberOfAvailableSpots());
                    break;
                case "guests_no":
                    System.out.println("Numarul de participanti: " + list.numberOfGuests());
                    break;
                case "waitlist_no":
                    System.out.println("Dimensiunea listei de asteptare: " + list.numberOfPeopleWaiting());
                    break;
                case "subscribe_no":
                    System.out.println("Numarul total de persoane: " + list.numberOfPeopleTotal());
                    break;
                case "search":
                    searchList(scanner, list);
                    break;
                case "quit":
                    System.out.println("Aplicatia se inchide...");
                    scanner.close();
                    running = false;
                    break;
                default:
                    System.out.println("Comanda introdusa nu este valida.");
                    System.out.println("Incercati inca o data.");

            }
        }
    }
}