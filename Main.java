import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Phonebook phonebook = new Phonebook();

    public static void main(String[] args) {
        while (true) {
            System.out.println("<-----Menu----->");
            System.out.println("[1] Store to ASEAN Phonebook");
            System.out.println("[2] Edit entry in ASEAN Phonebook");
            System.out.println("[3] Delete entry from ASEAN Phonebook");
            System.out.println("[4] View/Search ASEAN Phonebook");
            System.out.println("[5] Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> storeContact();
                case 2 -> editContact();
                case 3 -> deleteContact();
                case 4 -> searchMenu();
                case 5 -> {
                    System.out.println("Exiting program...");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void storeContact() {
        while (true) {
            System.out.print("Enter student number: ");
            String studentNumber = scanner.nextLine();

            System.out.print("Enter surname: ");
            String surname = scanner.nextLine();

            System.out.print("Enter first name: ");
            String firstName = scanner.nextLine();

            System.out.print("Enter occupation: ");
            String occupation = scanner.nextLine();

            System.out.print("Enter gender (M/F): ");
            String gender = scanner.nextLine();

            System.out.print("Enter country code: ");
            String countryCode = scanner.nextLine();

            System.out.print("Enter area code: ");
            String areaCode = scanner.nextLine();

            System.out.print("Enter number: ");
            String phoneNumber = scanner.nextLine();

            Person person = new Person(studentNumber, surname, firstName, occupation, gender, countryCode, areaCode, phoneNumber);
            phonebook.addContact(person);

            System.out.println("Contact successfully added...");
            System.out.print("Do you want to add a new one (Y/N)? ");
            if (!scanner.nextLine().equalsIgnoreCase("Y")) break;
        }
    }

    private static void editContact() {
        System.out.print("Enter student number to edit: ");
        String studentNumber = scanner.nextLine();
        phonebook.editContact(studentNumber, scanner);
    }

    private static void deleteContact() {
        System.out.print("Enter student number to delete: ");
        String studentNumber = scanner.nextLine();
        System.out.print("Are you sure you want to delete this contact (Y/N)? ");
        if (scanner.nextLine().equalsIgnoreCase("Y")) {
            phonebook.deleteContact(studentNumber);
        }
    }

    private static void searchMenu() {
        while (true) {
            System.out.println("<-----Search Menu----->");
            System.out.println("[1] Search by country");
            System.out.println("[2] Search by surname");
            System.out.println("[3] View All");
            System.out.println("[4] Go back to main menu");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> searchByCountry();
                case 2 -> searchBySurname();
                case 3 -> phonebook.viewAll();
                case 4 -> {
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void searchByCountry() {
        System.out.println("Enter countries to search (0 to stop):");
        Set<String> countryCodes = new HashSet<>();
        while (true) {
            System.out.println("[1] Burma [2] Cambodia [3] Thailand [4] Vietnam [5] Malaysia");
            System.out.println("[6] Philippines [7] Indonesia [8] Timor Leste [9] Laos");
            System.out.println("[10] Brunei [11] Singapore [12] All [0] No More");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 0) break;

            switch (choice) {
                case 6 -> countryCodes.add("63");
                case 7 -> countryCodes.add("62");
                case 12 -> {
                    phonebook.viewAll();
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }

        phonebook.searchByCountry(countryCodes);
    }

    private static void searchBySurname() {
        System.out.print("Enter surname: ");
        String surname = scanner.nextLine();
        phonebook.searchBySurname(surname);
    }
}