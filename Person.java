public class Person {
    private String studentNumber;
    private String surname;
    private String firstName;
    private String occupation;
    private String gender;
    private String countryCode;
    private String areaCode;
    private String phoneNumber;

    public Person(String studentNumber, String surname, String firstName, String occupation,
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