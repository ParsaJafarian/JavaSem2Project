package com.prog2.main.employees;

public class PartTime extends Teacher {
    private int hoursWorked;

    public PartTime(String name, String address, String phone, String email, int id, String specialty, String degree_name, int hoursWorked, boolean isDean) {
        super(name, address, phone, email, id, specialty, degree_name, isDean);
        setHoursWorked(hoursWorked);
    }

    public PartTime() {

    }

    private boolean validHoursWorked(int hoursWorked) {
        return hoursWorked < 40 && hoursWorked >= 0;
    }

    public void setHoursWorked(int hoursWorked) {
        if (validHoursWorked(hoursWorked)) {
            this.hoursWorked = hoursWorked;
        }
    }

    @Override
    public double computePayRoll() {
        return Double.parseDouble(String.format("%.2f", hoursWorked * getDegree().getRate() * 2 * 0.76));
    }

    @Override
    public String toString() {
        return "PartTime Teacher{" + super.toString() +
                "hoursWorked=" + hoursWorked +
                "Salary=" + computePayRoll() +
                '}';
    }
}
