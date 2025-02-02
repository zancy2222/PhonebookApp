import java.util.*;

public class Phonebook {
    private final List<Person> contacts;

    public Phonebook() {
        this.contacts = new ArrayList<>();
    }

    public void addContact(Person person) {
        contacts.add(person);
    }

    public void editContact(String studentNumber, Scanner scanner) {
        Optional<Person> personOpt = findContactByStudentNumber(studentNumber);
        if (personOpt.isEmpty()) {
            System.out.println("No contact found with the given student number.");
            return;
        }

        Person person = personOpt.get();
        while (true) {
            System.out.println("Here is the existing information about " + studentNumber + ":");
            System.out.println(person);
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
                    person.setStudentNumber(scanner.nextLine());
                }
                case 2 -> {
                    System.out.print("Enter new surname: ");
                    person.setSurname(scanner.nextLine());
                }
                case 3 -> {
                    System.out.print("Enter new gender: ");
                    person.setGender(scanner.nextLine());
                }
                case 4 -> {
                    System.out.print("Enter new occupation: ");
                    person.setOccupation(scanner.nextLine());
                }
                case 5 -> {
                    System.out.print("Enter new country code: ");
                    person.setCountryCode(scanner.nextLine());
                }
                case 6 -> {
                    System.out.print("Enter new area code: ");
                    person.setAreaCode(scanner.nextLine());
                }
                case 7 -> {
                    System.out.print("Enter new phone number: ");
                    person.setPhoneNumber(scanner.nextLine());
                }
                case 8 -> {
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void deleteContact(String studentNumber) {
        Optional<Person> personOpt = findContactByStudentNumber(studentNumber);
        if (personOpt.isEmpty()) {
            System.out.println("No contact found with the given student number.");
            return;
        }

        contacts.remove(personOpt.get());
        System.out.println("Contact " + studentNumber + " has been successfully deleted.");
    }

    public void searchByCountry(Set<String> countryCodes) {
        contacts.stream()
                .filter(contact -> countryCodes.contains(contact.getCountryCode()))
                .forEach(System.out::println);
    }

    public void searchBySurname(String surname) {
        contacts.stream()
                .filter(contact -> contact.getSurname().equalsIgnoreCase(surname))
                .forEach(System.out::println);
    }

    public void viewAll() {
        contacts.forEach(System.out::println);
    }

    private Optional<Person> findContactByStudentNumber(String studentNumber) {
        return contacts.stream()
                .filter(contact -> contact.getStudentNumber().equals(studentNumber))
                .findFirst();
    }
}