import java.util.*;

class Contact {
    private String studentNumber;
    private String surname;
    private String firstName;
    private String occupation;
    private String gender;
    private String countryCode;
    private String areaCode;
    private String phoneNumber;

    public Contact(String studentNumber, String surname, String firstName, String occupation,
                   String gender, String countryCode, String areaCode, String phoneNumber) {
        this.studentNumber = studentNumber;
        this.surname = surname;
        this.firstName = firstName;
        this.occupation = occupation;
        this.gender = gender;
        this.countryCode = countryCode;
        this.areaCode = areaCode;
        this.phoneNumber = phoneNumber;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, with student number %s, is a/an %s. %s phone number is %s-%s-%s.",
                surname, firstName, studentNumber, occupation, gender.equalsIgnoreCase("M") ? "His" : "Her",
                countryCode, areaCode, phoneNumber);
    }
}

public class ASEANPhonebook {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Contact> phonebook = new ArrayList<>();

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
            if (phonebook.stream().anyMatch(c -> c.getStudentNumber().equals(studentNumber))) {
                System.out.println("Student number already exists. Try again.");
                continue;
            }

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
            if (phonebook.stream().anyMatch(c -> c.getPhoneNumber().equals(phoneNumber))) {
                System.out.println("Phone number already exists. Try again.");
                continue;
            }

            Contact contact = new Contact(studentNumber, surname, firstName, occupation, gender, countryCode, areaCode, phoneNumber);
            phonebook.add(contact);

            System.out.println("Contact successfully added...");
            System.out.print("Do you want to add a new one (Y/N)? ");
            if (!scanner.nextLine().equalsIgnoreCase("Y")) break;
        }
    }

    private static void editContact() {
        System.out.print("Enter student number to edit: ");
        String studentNumber = scanner.nextLine();
        Optional<Contact> contactOpt = phonebook.stream().filter(c -> c.getStudentNumber().equals(studentNumber)).findFirst();

        if (contactOpt.isEmpty()) {
            System.out.println("No contact found with the given student number.");
            return;
        }

        Contact contact = contactOpt.get();
        while (true) {
            System.out.println("Here is the existing information about " + studentNumber + ":");
            System.out.println(contact);
            System.out.println("<-----Edit Menu----->");
            System.out.println("[1] Student Number [2] Surname [3] Gender");
            System.out.println("[4] Occupation [5] Country Code [6] Area Code");
            System.out.println("[7] Phone Number [8] None – Go back to main menu");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter new student number: ");
                    contact.setStudentNumber(scanner.nextLine());
                }
                case 2 -> {
                    System.out.print("Enter new surname: ");
                    contact.setSurname(scanner.nextLine());
                }
                case 3 -> {
                    System.out.print("Enter new gender: ");
                    contact.setGender(scanner.nextLine());
                }
                case 4 -> {
                    System.out.print("Enter new occupation: ");
                    contact.setOccupation(scanner.nextLine());
                }
                case 5 -> {
                    System.out.print("Enter new country code: ");
                    contact.setCountryCode(scanner.nextLine());
                }
                case 6 -> {
                    System.out.print("Enter new area code: ");
                    contact.setAreaCode(scanner.nextLine());
                }
                case 7 -> {
                    System.out.print("Enter new phone number: ");
                    contact.setPhoneNumber(scanner.nextLine());
                }
                case 8 -> {
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void deleteContact() {
        System.out.print("Enter student number to delete: ");
        String studentNumber = scanner.nextLine();
        Optional<Contact> contactOpt = phonebook.stream().filter(c -> c.getStudentNumber().equals(studentNumber)).findFirst();

        if (contactOpt.isEmpty()) {
            System.out.println("No contact found with the given student number.");
            return;
        }

        System.out.print("Are you sure you want to delete this contact (Y/N)? ");
        if (scanner.nextLine().equalsIgnoreCase("Y")) {
            phonebook.remove(contactOpt.get());
            System.out.println("Contact " + studentNumber + " has been successfully deleted.");
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
                case 3 -> viewAll();
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
                    viewAll();
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }

        phonebook.stream()
                .filter(contact -> countryCodes.contains(contact.getCountryCode()))
                .forEach(System.out::println);
    }

    private static void searchBySurname() {
        System.out.print("Enter surname: ");
        String surname = scanner.nextLine();

        phonebook.stream()
                .filter(contact -> contact.getSurname().equalsIgnoreCase(surname))
                .forEach(System.out::println);
    }

    private static void viewAll() {
        phonebook.forEach(System.out::println);
    }
}
