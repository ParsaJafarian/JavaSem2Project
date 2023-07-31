package com.prog2.main.employees;

import java.util.Comparator;

public abstract class Teacher extends Person {

    protected String specialty;
    protected Education degree;
    protected boolean isDean;

    /**
     * @param specialty  specialty of the teacher
     * @param degreeName name of the degree of the teacher which affects the teacher's payroll
     * @param isDean     boolean value depicting if the teacher is a dean
     */
    protected Teacher(String name, String address, String phone, String email, int date, String specialty, String degreeName, boolean isDean) {
        super(name, address, phone, email, date);
        setDegree(degreeName);
        this.specialty = specialty;
        this.isDean = isDean;
    }

    protected Teacher() {

    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public Education getDegree() {
        return degree;
    }

    /**
     * Static method used in main to make sure that the degree is valid
     * @param name The name of the degree which is checked to see if it's valid or not
     * @return true if the name contains one of the Education Enum names , else false
     */
    public static boolean validDegree(String name){
        for(Education education : Education.values()){
            if(name.toLowerCase().contains(education.name.toLowerCase())) return true;
        }
        System.out.println("The degree name is not valid (it has to contain Bachelor, Master or Phd)");
        return false;
    }

    public void setDegree(String name) {
        for (Education education : Education.values()) {
            if (name.toLowerCase().contains(education.name.toLowerCase())) {
                degree = education;
                break;
            }
        }
    }

    public boolean isDean() {
        return isDean;
    }

    public void setDean(boolean isDean) {
        this.isDean = isDean;
    }

    @Override
    public String toString() {
        return "specialty='" + specialty + '\'' +
                ", degree=" + degree +
                ", isDean=" + isDean +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", Id=" + id +
                '}';
    }

    public boolean equals(Teacher t) {
        return super.equals(t) &&
                specialty.equals(t.specialty) &&
                degree.equals(t.degree) &&
                isDean == t.isDean;
    }

    @Override
    public abstract double computePayRoll();

    /**
     * This Enum is used to assign degree rates to each degree name
     *
     * @see java.lang.Enum
     */
    protected enum Education {
        BACHELOR("Bachelor", 42),
        MASTER("Master", 82),
        PHD("Phd", 112);

        private final String name;
        private final int rate;

        Education(String name, int rate) {
            this.name = name;
            this.rate = rate;
        }

        boolean equals(Education e) {
            return name.equals(e.name);
        }

        int getRate() {
            return rate;
        }
    }

    /**
     * Used to sort teachers based on if they're deans
     *
     * @see Comparator<Teacher>
     */
    public final static class isDeanComparator implements Comparator<Teacher> {
        @Override
        public int compare(Teacher o1, Teacher o2) {
            return Boolean.compare(o1.isDean, o2.isDean);
        }
    }
}
