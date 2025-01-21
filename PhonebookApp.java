import java.util.Scanner;

public class PhonebookApp {
    private static final String[] MENUS_MAIN = {
        "Store to ASEAN Phonebook",
        "Edit entry in ASEAN Phonebook",
        "Delete entry from ASEAN Phonebook",
        "View/Search ASEAN Phonebook",
        "Exit"
    };

    private static final String[] MENUS_VIEWS = {
        "Search by country",
        "Search by surname",
        "View all",
        "Go back to main menu"
    };

    private static final String[] MENUS_EDIT = {
        "Student Number",
        "Surname",
        "Gender",
        "Occupation",
        "Country Code",
        "Area Code",
        "Phone Number",
        "None - Go back to main menu"
    };

    private static final String[] MENUS_CC = {
        "Burma", // 856
        "Cambodia", // 855
        "Thailand", // 66
        "Vietnam", // 84
        "Malaysia", // 60
        "Philippines", // 63
        "Indonesia", // 62
        "Timor Leste", // 670
        "Laos", // 95
        "Brunei", // 673
        "Singapore", // 65
        "All",
        "No More"
    };

    private static Scanner scanner = new Scanner(System.in);

    public static void showMenu(String target, int inline) {
        System.out.println("\n<-----Menu----->");
        int i = 1;
        String[] menuArray = switch (target) {
            case "main" -> MENUS_MAIN;
            case "views" -> MENUS_VIEWS;
            case "edit" -> MENUS_EDIT;
            case "cc" -> MENUS_CC;
            default -> new String[0];
        };
        
        for (String menu : menuArray) {
            String out = "[" + i + "] " + menu;
            if (inline != -1 && i == inline) {
                out = "\n[" + i + "] " + menu;
            }
            System.out.print(out + "\t");
            i++;
        }
    }

    public static Contact receiveContactInfo() {
        String studentNumber = prompt("Enter student number: ");
        String surname = prompt("Enter surname: ");
        String firstName = prompt("Enter first name: ");
        String occupation = prompt("Enter occupation: ");
        String gender = prompt("Enter gender (M for male, F for female): ");
        int countryCode = Integer.parseInt(prompt("Enter country code: "));
        int areaCode = Integer.parseInt(prompt("Enter area code: "));
        int number = Integer.parseInt(prompt("Enter number: "));
        
        return new Contact(studentNumber, firstName, surname, occupation, gender, countryCode, areaCode, number);
    }

    public static String prompt(String phrase) {
        System.out.print(phrase);
        return scanner.nextLine();
    }

    public static int[] convertChoices(int[] choices) {
        for (int i = 0; i < choices.length; i++) {
            switch (choices[i]) {
                case 1 -> choices[i] = 856;
                case 2 -> choices[i] = 855;
                case 3 -> choices[i] = 66;
                case 4 -> choices[i] = 84;
                case 5 -> choices[i] = 60;
                case 6 -> choices[i] = 63;
                case 7 -> choices[i] = 62;
                case 8 -> choices[i] = 670;
                case 9 -> choices[i] = 95;
                case 10 -> choices[i] = 673;
                case 11 -> choices[i] = 65;
            }
        }
        return choices;
    }

    public static void main(String[] args) {
        ContactArray pb = new ContactArray(10);  // Example size, adjust as needed
        while (true) {
            showMenu("main", -1);
            int opt = Integer.parseInt(prompt("Select Operation: "));
            
            if (opt == 1) { // Store to Phonebook
                while (true) {
                    try {
                        Contact contactInput = receiveContactInfo();
                        Contact existStudentNum = pb.getContact(contactInput.getStudentNumber());
                        Contact existContactNum = null;
                        for (int i = 0; i < pb.getSize(); i++) {
                            if (pb.getContactAtIndex(i).getFullContactNumber().equals(contactInput.getFullContactNumber())) {
                                existContactNum = pb.getContactAtIndex(i);
                                break;
                            }
                        }
                        if (existStudentNum != null) {
                            System.out.println("Student number is already taken!");
                        } else if (existContactNum != null) {
                            System.out.println("Contact number is already taken!");
                        } else {
                            pb.insert(contactInput);
                            System.out.println("Contact successfully added...");
                        }
                    } catch (Exception e) {
                        System.out.println("Country Codes, Area Codes, and Contact Numbers must use integers! Please use integers next time.");
                    }

                    String choice = prompt("Do you want to add another entry? (Y/N): ");
                    if (choice.equalsIgnoreCase("N")) {
                        break;
                    }
                }

            } else if (opt == 2) { // Edit entry
                String studentNum = prompt("Enter student number: ");
                Contact contactEdit = pb.getContact(studentNum);
                if (contactEdit == null) {
                    System.out.println("No contact found with student number " + studentNum);
                    continue;
                } else {
                    System.out.println("Here is the existing information about " + studentNum + ":");
                    System.out.println(contactEdit);
                }

                while (true) {
                    showMenu("edit", 5);
                    int optEdit = Integer.parseInt(prompt("Select Operation: "));
                    boolean sortLname = false;

                    switch (optEdit) {
                        case 1 -> contactEdit.setStudentNumber(prompt("Enter new student number: "));
                        case 2 -> {
                            contactEdit.setLName(prompt("Enter new surname: "));
                            sortLname = true;
                        }
                        case 3 -> {
                            String changeGender = prompt("Enter new gender (M/F): ");
                            if (contactEdit.setGender(changeGender) == -1) {
                                continue;
                            }
                        }
                        case 4 -> contactEdit.setOccupation(prompt("Enter new occupation: "));
                        case 5 -> {
                            try {
                                contactEdit.setCountryCode(Integer.parseInt(prompt("Enter new country code: ")));
                            } catch (Exception e) {
                                System.out.println("Enter a numeric country code!");
                                continue;
                            }
                        }
                        case 6 -> {
                            try {
                                contactEdit.setAreaCode(Integer.parseInt(prompt("Enter new area code: ")));
                            } catch (Exception e) {
                                System.out.println("Enter a numeric area code!");
                                continue;
                            }
                        }
                        case 7 -> {
                            try {
                                contactEdit.setContactNumber(Integer.parseInt(prompt("Enter new phone number: ")));
                            } catch (Exception e) {
                                System.out.println("Enter a numeric phone number!");
                                continue;
                            }
                        }
                        case 8 -> {
                            break;
                        }
                        default -> {
                            System.out.println("Invalid Option!");
                            continue;
                        }
                    }

                    if (sortLname) {
                        pb.deleteContact(contactEdit.getStudentNumber());
                        pb.insert(contactEdit);  // Re-insert the updated contact
                        
                    }

                    System.out.println("\nUpdated Contact Information:");
                    System.out.println(contactEdit);
                }

            } else if (opt == 3) { // Delete
                String deleteInput = prompt("Enter student number: ");
                if (pb.getContact(deleteInput) == null) {
                    System.out.println("No contact was found with that Student Number.");
                } else {
                    String confirmationCheck = prompt("Are you sure you want to delete Contact " + deleteInput + "? [Y/N]: ");
                    if (confirmationCheck.equalsIgnoreCase("Y")) {
                        pb.deleteContact(deleteInput);
                        System.out.println("Contact " + deleteInput + " has been successfully deleted.");
                    } else {
                        System.out.println("Contact " + deleteInput + " was not deleted.");
                    }
                }

            } else if (opt == 4) { // View/Search
                while (true) {
                    showMenu("views", -1);
                    int optView = Integer.parseInt(prompt("Select Operation: "));
                    if (optView == 1) { // Search By Country
                        int[] countryCodesList = new int[12];
                        int index = 0;
                        int count = 1;
                        showMenu("cc", 6);

                        while (true) {
                            int countryCode = Integer.parseInt(prompt("Enter choice " + count + ": "));
                            if (countryCode == 0) break;
                            if (countryCode >= 1 && countryCode <= 11) {
                                boolean exists = false;
                                for (int i = 0; i < index; i++) {
                                    if (countryCodesList[i] == countryCode) {
                                        System.out.println("Country code already inputted!");
                                        exists = true;
                                        break;
                                    }
                                }
                                if (!exists) {
                                    countryCodesList[index] = countryCode;
                                    index++;
                                    count++;
                                }
                            } else if (countryCode == 12) {
                                System.out.println("Here are the students from all countries:");
                                System.out.println(pb);
                                break;
                            } else {
                                System.out.println("Invalid choice!");
                            }
                        }

                        if (index > 0) {
                            for (int i = 0; i < index; i++) {
                                System.out.println(MENUS_CC[countryCodesList[i]]);
                            }
                        }
                    } else if (optView == 2) {
                        String lastName = prompt("Enter surname: ");
                        pb.displayLastName(lastName);
                    } else if (optView == 3) {
                        pb.display();
                    } else if (optView == 4) {
                        break;
                    }
                }
            } else if (opt == 5) {
                break;
            }
        }
    }
}

class Contact {
    private String studentNumber;
    private String firstName;
    private String surname;
    private String occupation;
    private String gender;
    private int countryCode;
    private int areaCode;
    private int number;

    // Constructor to initialize the contact details
    public Contact(String studentNumber, String firstName, String surname, String occupation, String gender, int countryCode, int areaCode, int number) {
        this.studentNumber = studentNumber;
        this.firstName = firstName;
        this.surname = surname;
        this.occupation = occupation;
        this.gender = gender;
        this.countryCode = countryCode;
        this.areaCode = areaCode;
        this.number = number;
    }

    // Getter methods
    public String getStudentNumber() {
        return studentNumber;
    }

    public String getFullContactNumber() {
        return String.format("+%d-%d-%d-%d", countryCode, areaCode, number);
    }

    // Setter methods for editing contact details
    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLName(String surname) {
        this.surname = surname;
    }

    public int setGender(String gender) {
        if (gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("F")) {
            this.gender = gender;
            return 0; // success
        }
        return -1; // error
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public void setCountryCode(int countryCode) {
        this.countryCode = countryCode;
    }

    public void setAreaCode(int areaCode) {
        this.areaCode = areaCode;
    }

    public void setContactNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Student Number: " + studentNumber + "\nName: " + firstName + " " + surname + "\nOccupation: " + occupation + "\nGender: " + gender + "\nCountry Code: " + countryCode + "\nArea Code: " + areaCode + "\nPhone Number: " + number;
    }
}


class ContactArray {
    private Contact[] contacts;
    private int size;

    public ContactArray(int capacity) {
        contacts = new Contact[capacity];
        size = 0;
    }

    public Contact getContact(String studentNumber) {
        for (int i = 0; i < size; i++) {
            if (contacts[i].getStudentNumber().equals(studentNumber)) {
                return contacts[i];
            }
        }
        return null;
    }

    public void insert(Contact contact) {
        if (size < contacts.length) {
            contacts[size] = contact;
            size++;
        }
    }

    public void deleteContact(String studentNumber) {
        for (int i = 0; i < size; i++) {
            if (contacts[i].getStudentNumber().equals(studentNumber)) {
                for (int j = i; j < size - 1; j++) {
                    contacts[j] = contacts[j + 1];
                }
                contacts[size - 1] = null;
                size--;
                break;
            }
        }
    }

    public void display() {
        for (int i = 0; i < size; i++) {
            System.out.println(contacts[i]);
        }
    }

    public Contact getContactAtIndex(int index) {
        return contacts[index];
    }

    public int getSize() {
        return size;
    }

    public void displayLastName(String lastName) {
        for (int i = 0; i < size; i++) {
            if (contacts[i].toString().contains(lastName)) {
                System.out.println(contacts[i]);
            }
        }
    }
}
