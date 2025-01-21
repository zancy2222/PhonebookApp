import java.util.HashMap;
import java.util.Map;

public class Contact {
    /**
     * A contact object used by Phonebook. Each contact has their own
     * name, student number, contact number, and occupation.
     * 
     * The full contact number of a contact is a combination of its country code, area code,
     * and contact number, IN ORDER.
     */
    
    private static final Map<Integer, String> COUNTRY_CODES = new HashMap<>();
    
    static {
        COUNTRY_CODES.put(60, "Federation of Malaysia"); // Malaysia
        COUNTRY_CODES.put(62, "Republic of Indonesia"); // Indonesia
        COUNTRY_CODES.put(63, "Republic of the Philippines"); // Philippines
        COUNTRY_CODES.put(65, "Republic of Singapore"); // Singapore
        COUNTRY_CODES.put(66, "Kingdom of Thailand"); // Thailand
        COUNTRY_CODES.put(84, "Socialist Republic of Vietnam"); // Vietnam
        COUNTRY_CODES.put(673, "Brunei Darussalam"); // Brunei
        COUNTRY_CODES.put(855, "Kingdom of Cambodia"); // Cambodia
        COUNTRY_CODES.put(856, "Lao People's Democratic Republic"); // Burma
        COUNTRY_CODES.put(95, "Republic of the Union of Myanmar"); // Myanmar
        COUNTRY_CODES.put(670, "Democratic Republic of Timor Leste"); // Timor Leste
    }
    
    private String studentNum;
    private String fname;
    private String lname;
    private String occupation;
    private String gender;
    private int cc;
    private int area;
    private int number;
    
    public Contact(String stdn, String fname, String sname, String occupation, String gender,
                   int cc, int area, int number) {
        this.studentNum = stdn;
        this.fname = fname;
        this.lname = sname;
        this.occupation = occupation;
        this.gender = gender;
        this.cc = cc;
        this.area = area;
        this.number = number;
    }

    public String getStudentNumber() {
        return this.studentNum;
    }

    public String getFName() {
        return this.fname;
    }

    public String getLName() {
        return this.lname;
    }

    public String getFullName() {
        return this.fname + " " + this.lname;
    }

    public String getOccupation() {
        return this.occupation;
    }

    public String getGender() {
        return this.gender;
    }

    public String getPronoun() {
        return this.getGender().equals("M") ? "His" : "Her";
    }

    public String getCountryCode() {
        return COUNTRY_CODES.get(this.getNumericCountryCode());
    }

    public int getNumericCountryCode() {
        return this.cc;
    }

    public int getAreaCode() {
        return this.area;
    }

    public int getContactNumber() {
        return this.number;
    }

    public String getFullContactNumber() {
        return String.format("%d-%d-%d", this.getNumericCountryCode(),
                             this.getAreaCode(), this.getContactNumber());
    }

    public Object get(String attr) {
        switch (attr.toLowerCase()) {
            case "studentnum":
                return this.getStudentNumber();
            case "fname":
                return this.getFName();
            case "lname":
                return this.getLName();
            case "occupation":
                return this.getOccupation();
            case "gender":
                return this.getGender();
            case "country":
                return this.getCountryCode();
            case "countrynum":
                return this.getNumericCountryCode();
            case "area":
                return this.getAreaCode();
            case "contactnum":
                return this.getContactNumber();
            default:
                return -1;
        }
    }

    public void setStudentNumber(String newStdn) {
        this.studentNum = newStdn;
    }

    public void setFName(String newFname) {
        this.fname = newFname;
    }

    public void setLName(String newSname) {
        this.lname = newSname;
    }

    public void setGender(String newGender) {
        if (!newGender.equalsIgnoreCase("M") && !newGender.equalsIgnoreCase("F")) {
            System.out.println("Sorry, that is an invalid value for gender.");
        } else {
            this.gender = newGender;
        }
    }

    public void setOccupation(String newOccupation) {
        this.occupation = newOccupation;
    }

    public void setCountryCode(int newCountryCode) {
        if (!COUNTRY_CODES.containsKey(newCountryCode)) {
            System.out.println("Sorry, this country code does not exist. Try again.");
        } else {
            this.cc = newCountryCode;
        }
    }

    public void setAreaCode(int newArea) {
        this.area = newArea;
    }

    public void setContactNumber(int newNumber) {
        this.number = newNumber;
    }

    public static int compareNames(Contact c1, Contact c2, int comparisonType) {
        String n1, n2;
        if (comparisonType == 0) {
            n1 = c1.getLName();
            n2 = c2.getLName();
        } else {
            n1 = c1.getFName();
            n2 = c2.getFName();
        }
        
        return n1.compareTo(n2);
    }

    public static int compare(Contact c1, Contact c2, String modifier) {
        Object attr1 = c1.get(modifier);
        Object attr2 = c2.get(modifier);

        if (attr1 instanceof Comparable && attr2 instanceof Comparable) {
            Comparable<Object> comp1 = (Comparable<Object>) attr1;
            Comparable<Object> comp2 = (Comparable<Object>) attr2;
            return comp1.compareTo(comp2);
        }
        return 0;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, with student number %s, is a/an %s. %s phone number is %s.",
                             this.getLName(), this.getFName(), this.getStudentNumber(),
                             this.getOccupation(), this.getPronoun(), this.getFullContactNumber());
    }
}
