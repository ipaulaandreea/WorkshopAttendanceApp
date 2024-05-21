package WorkshopAttendanceApp;
import java.util.Iterator;
import java.util.List;


import java.util.ArrayList;

 class GuestsList {
    private final int guestsCapacity;
    private List<Guest> guests = new ArrayList<>();
    private List<Guest> waitingList = new ArrayList<>();

    // TO DO: add fields

    public GuestsList(int guestsCapacity) {
        this.guestsCapacity = guestsCapacity;
        // TO DO:
    }

    /**
     * Add a new, unique guest to the list.
     * <p>
     * //     * @param g the guest to be added
     * //     * @return '-1' if the guest is already present, '0' if is a guest, or the
     * number on the waiting list
     */
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

        // TO DO:
    }

    /**
     * Check if someone is already registered ( as a guest, or on the waiting
     * list).
     * <p>
     * //     * @param g the guest we are searching for
     * //     * @return true if present, false if not
     */


    private boolean isOnTheListAlready(Guest g) {
        return guests.contains(g) || waitingList.contains(g);
        // TO DO:
    }

    /**
     * Search for a guest based on first and last name. Return the first result.
     *
     * @param firstName first name of the guest
     * @param lastName  last name of the guest
     * @return the guest if found, null if not
     */
    public Guest search(String firstName, String lastName) {
        for (Guest guest : guests) {
            if (guest.getFirstName().equals(firstName) && guest.getLastName().equals(lastName)) {
                return guest;
            }
        }
        return null;
        // TO DO:
    }

    ;

    /**
     * Search for a guest based on email or phone number. Return the first result.
     *
     * @param opt   option to use for searching: 2 for email, 3 for phone number
     * @param match what is searched for
     * @return the guest if found, null if not
     */
    public Guest search(int opt, String match) {
        for (Guest guest : guests) {
            if (opt == 3) {
                if (guest.getPhoneNumber().equals(match)) {
                    return guest;
                }
            } else if (opt == 2) {
                if (guest.getEmail().equals(match)) {
                    return guest;
                }
            }
        }
        return null;
        // TO DO:
    }

    /**
     * Remove a guest based on first and last name. Remove the first result.
     *
     * @param firstName first name of the guest
     * @param lastName  last name of the guest
     * @return true if removed, false if not
     */
    public boolean remove(String firstName, String lastName) {
        Iterator<Guest> iterator = guests.iterator();
        while (iterator.hasNext()) {
            Guest guest = iterator.next();
            if (guest.getFirstName().equals(firstName) && guest.getLastName().equals(lastName)) {
                iterator.remove();
                if (guests.size() < guestsCapacity && !waitingList.isEmpty()) {
                    Guest g = waitingList.remove(0);
                    add(g);
                }

                return true;
            }
        }
            return false;
        }



        // TO DO:

    /**
     * Remove a guest based on email or phone number. Remove the first result.
     * <p>
     * //     * @param opt   option to use for searching: 2 for email, 3 for phone number
     * //     * @param match the match we are searching for
     * //     * @return true if removed, false if not
     * //
     */
    public boolean remove(int opt, String match) {
        Iterator<Guest> iterator = guests.iterator();
        while (iterator.hasNext()) {
            Guest guest = iterator.next();
            if (opt == 3 && guest.getPhoneNumber().equals(match) ||
            opt == 2 && guest.getEmail().equals(match)) {
                iterator.remove();
                if (guests.size() < guestsCapacity && !waitingList.isEmpty()) {
                    Guest g = waitingList.remove(0);
                    guests.add(g);
                }


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
    // Show the people on the waiting list.
    public void showWaitingList() {
        if (waitingList.isEmpty()){
            System.out.println("Lista de asteptare este goala…");
        } else {
            for (Guest guest : waitingList) {
                System.out.println((waitingList.indexOf(guest) + 1) + "." + " " + guest);
            }
        }
    }

    /**
     * Show how many free spots are left.
     *
     * @return the number of spots left for guests
     */
    public int numberOfAvailableSpots() {
        return guestsCapacity - guests.size();
    }

    /**
     * Show how many guests there are.
     *
     * @return the number of guests
     */
    public int numberOfGuests() {
        return guests.size();
    }

    /**
     * Show how many people are on the waiting list.
     *
     * @return number of people on the waiting list
     */
    public int numberOfPeopleWaiting() {

        return waitingList.size();

    }

    /**
     * Show how many people there are in total, including guests.
     *
     * @return how many people there are in total
     */
    public int numberOfPeopleTotal() {
        return guests.size() + waitingList.size();
    }

    /**
     * Find all people based on a partial value search.
     *
     * @param match the match we are looking for
     * @return a list of people matching the criteria
     */
    public List<Guest> partialSearch(String match) {
        List<Guest> matches = new ArrayList<>();
        String lowerCaseMatch = match.toLowerCase();
        System.out.println("Inside partial search");
        for (Guest guest : guests) {
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
