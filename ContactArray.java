import java.util.ArrayList;
import java.util.List;

public class ContactArray {
    private Contact[] phonebook;
    private int size;

    public ContactArray(int size) {
        this.phonebook = new Contact[size];
        this.size = 0;
    }

    public int getSize() {
        return this.size;
    }

    public Contact first() {
        return isEmpty() ? null : this.phonebook[0];
    }

    public Contact getLast() {
        return isEmpty() ? null : this.phonebook[this.size - 1];
    }

    public Contact getContactAtIndex(int index) {
        if (index >= 0 && index < this.size) {
            return this.phonebook[index];
        }
        return null;
    }

    public Contact getContact(String studentNum) {
        for (int i = 0; i < this.size; i++) {
            if (this.phonebook[i].getStudentNumber().equals(studentNum)) {
                return this.phonebook[i];
            }
        }
        return null;
    }

    public Contact getContactBySurname(String surname) {
        for (int i = 0; i < this.size; i++) {
            if (this.phonebook[i].getLName().equals(surname)) {
                return this.phonebook[i];
            }
        }
        return null;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void incrSize() {
        this.size++;
    }

    public void decrSize() {
        this.size--;
    }

    private void increasePhonebookSize() {
        Contact[] newPhonebook = new Contact[this.phonebook.length * 2];
        System.arraycopy(this.phonebook, 0, newPhonebook, 0, this.phonebook.length);
        this.phonebook = newPhonebook;
    }

    public void insert(Contact c) {
        if (this.size >= this.phonebook.length) {
            increasePhonebookSize();
        }
        int indexPoint = findIndexInsertion(c);
        adjustPhonebook(indexPoint, this.size, "b");
        this.phonebook[indexPoint] = c;
        incrSize();
    }

    private int findIndexInsertion(Contact c) {
        for (int i = 0; i < this.size; i++) {
            if (Contact.compareNames(c, this.phonebook[i], 0) == -1 ||
                (Contact.compareNames(c, this.phonebook[i], 0) == 0 &&
                 Contact.compareNames(c, this.phonebook[i], 1) == -1)) {
                return i;
            }
        }
        return this.size;
    }

    private void adjustPhonebook(int start, int end, String dir) {
        if (dir.equals("f")) {
            for (int i = start; i < end - 1; i++) {
                this.phonebook[i] = this.phonebook[i + 1];
            }
        } else if (dir.equals("b")) {
            for (int i = end; i > start; i--) {
                this.phonebook[i] = this.phonebook[i - 1];
            }
            this.phonebook[start] = null;
        }
    }

    public Contact deleteContact(String stdn) {
        for (int i = 0; i < this.size; i++) {
            Contact contact = this.phonebook[i];
            if (contact.getStudentNumber().equals(stdn)) {
                adjustPhonebook(i, this.size, "f");
                decrSize();
                return contact;
            }
        }
        return null;
    }

    private List<Contact> sort(String by) {
        List<Contact> sortedList = new ArrayList<>();
        sortedList.add(this.phonebook[0]);
        int index = 0;

        for (Contact entry : this.phonebook) {
            if (entry == null) continue;
            String modifier = by;
            if (Contact.compare(entry, sortedList.get(index), modifier) >= 0) {
                int replacementIndex = index;
                if (Contact.compare(entry, sortedList.get(index), modifier) == 0) {
                    modifier = "fname";
                }
                for (Contact object : sortedList) {
                    if (Contact.compare(entry, object, modifier) == -1) {
                        sortedList.set(replacementIndex, entry);
                    }
                    replacementIndex++;
                }
            } else {
                adjustPhonebook(index, sortedList.size(), "f");
                sortedList.add(index, entry);
                index++;
            }
        }

        return sortedList;
    }

    @Override
    public String toString() {
        return toString(null, "lname");
    }

    public String toString(List<String> f, String by) {
        StringBuilder s = new StringBuilder("<----Phonebook---->");
        if (!isEmpty()) {
            for (int i = 0; i < this.size; i++) {
                Contact contact = this.phonebook[i];
                if (f == null || f.contains(contact.getNumericCountryCode())) {
                    s.append("\n").append(contact);
                }
            }
        } else {
            s.append("\nThis phonebook is currently empty...");
        }
        s.append("\n<----End---->");
        return s.toString();
    }
}