package com.prog2.main.employees;

import java.io.Serializable;
import java.util.Comparator;

/**
 * The abstract Person class implements Serializable so that its subclasses can
 * be saved inside a text file in the main method. Moreover, it implements the computePayRoll method
 * as an abstract method because all of its subclasses must implement it
 */
abstract class Person implements Serializable, PayRoll {
    protected String name, address, phone, email;
    protected int id;

    /**
     * @param name     name of the person
     * @param address  address of the person
     * @param phone    phone number of the person
     * @param email    email address of the person
     * @param birthday birthday of the person
     */

    protected Person(String name, String address, String phone, String email, int birthday) {
        this.name = name;
        this.address = address;
        this.id = birthday;
        this.phone = phone;
        this.email = email;
    }

    protected Person() {

    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    /**
     * @return the salary of a person depending on their role (Teacher, Staff, FullTime, etc)
     */
    @Override
    public abstract double computePayRoll();

    protected boolean equals(Person p) {
        return name.equals(p.name) &&
                address.equals(p.address) &&
                id == p.id &&
                phone.equals(p.phone) &&
                email.equals(p.email);
    }

    public final static class SalaryComparator implements Comparator<Person> {
        @Override
        public int compare(Person o1, Person o2) {
            return (int) (o1.computePayRoll() - o2.computePayRoll());
        }
    }

    /**
     * Used to sort a list of persons depending on their Id
     *
     * @see java.util.Comparator
     */

    public final static class IdComparator implements Comparator<Person> {
        /**
         * Compares its two arguments for order.  Returns a negative integer,
         * zero, or a positive integer as the first argument is less than, equal
         * to, or greater than the second.<p>
         * <p>
         * The implementor must ensure that {@link Integer#signum
         * signum}{@code (compare(x, y)) == -signum(compare(y, x))} for
         * all {@code x} and {@code y}.  (This implies that {@code
         * compare(x, y)} must throw an exception if and only if {@code
         * compare(y, x)} throws an exception.)<p>
         * <p>
         * The implementor must also ensure that the relation is transitive:
         * {@code ((compare(x, y)>0) && (compare(y, z)>0))} implies
         * {@code compare(x, z)>0}.<p>
         * <p>
         * Finally, the implementor must ensure that {@code compare(x,
         * y)==0} implies that {@code signum(compare(x,
         * z))==signum(compare(y, z))} for all {@code z}.
         *
         * @param o1 the first object to be compared.
         * @param o2 the second object to be compared.
         * @return a negative integer, zero, or a positive integer as the
         * first argument is less than, equal to, or greater than the
         * second.
         * @throws NullPointerException if an argument is null and this
         *                              comparator does not permit null arguments
         * @throws ClassCastException   if the arguments' types prevent them from
         *                              being compared by this comparator.
         * @apiNote It is generally the case, but <i>not</i> strictly required that
         * {@code (compare(x, y)==0) == (x.equals(y))}.  Generally speaking,
         * any comparator that violates this condition should clearly indicate
         * this fact.  The recommended language is "Note: this comparator
         * imposes orderings that are inconsistent with equals."
         */
        @Override
        public int compare(Person o1, Person o2) {
            return o1.id - o2.id;
        }
    }

    /**
     * Used to sort a list of persons depending on their names
     *
     * @see java.util.Comparator
     */
    public final static class NameComparator implements Comparator<Person> {
        @Override
        public int compare(Person t1, Person t2) {
            return t1.name.compareTo(t2.name);
        }
    }

    /**
     * Used to sort a list of persons depending on their address
     *
     * @see java.util.Comparator
     */
    public final static class AddressComparator implements Comparator<Person> {
        @Override
        public int compare(Person o1, Person o2) {
            return o1.address.compareTo(o2.address);
        }
    }
}
