package WorkshopAttendanceApp;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;


 class GuestsList {
    private final int guestsCapacity;
    private List<Guest> guests = new ArrayList<>();
    private List<Guest> waitingList = new ArrayList<>();


    public GuestsList(int guestsCapacity) {
        this.guestsCapacity = guestsCapacity;
    }

    public int add(Guest g) {
        if (guests.contains(g)) {
            return -1;
        } else if (guests.size() < guestsCapacity) {
            guests.add(g);


            System.out.println("[" + g.fullName() + "] Felicitari! Locul tau la eveniment este confirmat. Te asteptam!");
            return 0;

        } else {
            waitingList.add(g);

            System.out.println("[" + g.fullName() + "] Te-ai inscris cu succes in lista de asteptare si ai primit numarul de ordine " + (waitingList.indexOf(g) + 1)  + ". Te vom notifica daca un loc devine disponibil.");
            return waitingList.indexOf(g) + 1;

        }


    }



    private boolean isOnTheListAlready(Guest g) {
        return guests.contains(g) || waitingList.contains(g);

    }


    public Guest search(String firstName, String lastName) {
        for (Guest guest : guests) {
            if (guest.getFirstName().equalsIgnoreCase(firstName) && guest.getLastName().equalsIgnoreCase(lastName)) {
                return guest;
            }
        }

        for (Guest guest : waitingList) {
            if (guest.getFirstName().equalsIgnoreCase(firstName) && guest.getLastName().equalsIgnoreCase(lastName)) {
                return guest;
            }
        }

        return null;
    }


    public Guest search(int opt, String match) {

        for (Guest guest : guests) {
            if ((opt == 3 && guest.getPhoneNumber().equals(match)) ||
                    (opt == 2 && guest.getEmail().equals(match))) {
                return guest;
            }
        }

        for (Guest guest : waitingList) {
            if ((opt == 3 && guest.getPhoneNumber().equals(match)) ||
                    (opt == 2 && guest.getEmail().equals(match))) {
                return guest;
            }
        }

        return null;
    }

    public boolean remove(String firstName, String lastName) {
        Iterator<Guest> iterator = guests.iterator();
        while (iterator.hasNext()) {
            Guest guest = iterator.next();
            if (guest.getFirstName().equals(firstName) && guest.getLastName().equals(lastName)) {
                    iterator.remove();
                System.out.println("Stergerea persoanei s-a realizat cu succes.");

                if (guests.size() < guestsCapacity && !waitingList.isEmpty()) {
                    Guest g = waitingList.remove(0);
                    this.add(g);
                }

                return true;
            }
        }
        iterator = waitingList.iterator();
        while(iterator.hasNext()) {
            Guest guest = iterator.next();
            if (guest.getFirstName().equals(firstName) && guest.getLastName().equals(lastName)){
                iterator.remove();
                System.out.println("Stergerea persoanei s-a realizat cu succes.");
                return true;
            }
        }
            return false;
        }




    public boolean remove(int opt, String match) {
        Iterator<Guest> iterator = guests.iterator();
        while (iterator.hasNext()) {
            Guest guest = iterator.next();
            if (opt == 3 && guest.getPhoneNumber().equals(match) ||
            opt == 2 && guest.getEmail().equals(match)) {
                iterator.remove();
                System.out.println("Stergerea persoanei s-a realizat cu succes.");
                if (guests.size() < guestsCapacity && !waitingList.isEmpty()) {
                    Guest g = waitingList.remove(0);
                    this.add(g);
                }


                return true;
            }
            }
            iterator = waitingList.iterator();
            while (iterator.hasNext()) {
                Guest guest = iterator.next();
                if (opt == 3 && guest.getPhoneNumber().equals(match) ||
                opt == 2 && guest.getEmail().equals(match)) {
                    iterator.remove();
                    System.out.println("Stergerea persoanei s-a realizat cu succes.");
                    return true;
                }
            }

        return false;
    }

    // Show the list of guests.
    public void showGuestsList() {
        if (guests.isEmpty()) {
            System.out.println("Niciun participant inscris...");

        } else {
            for (Guest guest : guests) {
                System.out.println((guests.indexOf(guest) + 1) + "." + " " + guest);
            }
        }
    }

    public void showWaitingList() {
        if (waitingList.isEmpty()){
            System.out.println("Lista de asteptare este goala...");
        } else {
            for (Guest guest : waitingList) {
                System.out.println((waitingList.indexOf(guest) + 1) + "." + " " + guest);
            }
        }
    }


    public int numberOfAvailableSpots() {
        return guestsCapacity - guests.size();
    }


    public int numberOfGuests() {
        return guests.size();
    }


    public int numberOfPeopleWaiting() {

        return waitingList.size();

    }


    public int numberOfPeopleTotal() {
        return guests.size() + waitingList.size();
    }


     public List<Guest> partialSearch(String match) {
         List<Guest> matches = new ArrayList<>();
         String lowerCaseMatch = match.toLowerCase();

         // Search in guests list
         for (Guest guest : guests) {
             if (guest.getFirstName().toLowerCase().contains(lowerCaseMatch) ||
                     guest.getLastName().toLowerCase().contains(lowerCaseMatch) ||
                     guest.getEmail().toLowerCase().contains(lowerCaseMatch) ||
                     guest.getPhoneNumber().toLowerCase().contains(lowerCaseMatch)) {
                 matches.add(guest);
             }
         }

         // Search in waiting list
         for (Guest guest : waitingList) {
             if (guest.getFirstName().toLowerCase().contains(lowerCaseMatch) ||
                     guest.getLastName().toLowerCase().contains(lowerCaseMatch) ||
                     guest.getEmail().toLowerCase().contains(lowerCaseMatch) ||
                     guest.getPhoneNumber().toLowerCase().contains(lowerCaseMatch)) {
                 matches.add(guest);
             }
         }

         return matches;
     }


    @Override
    public String toString() {
        return "GuestsList{" +
                "guestsCapacity=" + guestsCapacity +
                ", guests=" + guests +
                ", waitingList=" + waitingList +
                '}';
    }
}
