package hu.hazazs;

import java.util.Objects;

final class Person implements Cloneable {

    private final String firstName;
    private final String lastName;
    private Date dateOfBirth;

    public Person(String firstName, String lastName, Date dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public Person(Person original) {
        this.firstName = original.getFirstName();
        this.lastName = original.getLastName();
        Date originalDate = original.getDateOfBirth();
        this.dateOfBirth = new Date(originalDate.getYear(), originalDate.getMonth(), originalDate.getDay());
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    @Override
    protected Person clone() {
        try {
            Person clone = (Person) super.clone();
            clone.dateOfBirth = this.dateOfBirth.clone();
            return clone;
        } catch (CloneNotSupportedException x) {
            return null;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, dateOfBirth);
    }

    @Override
    public String toString() {
        return "Person [" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ']';
    }

}