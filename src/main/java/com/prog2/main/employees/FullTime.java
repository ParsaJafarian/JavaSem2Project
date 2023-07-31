package com.prog2.main.employees;

public class FullTime extends Teacher {
    public FullTime(String name, String address, String phone, String email, int id, String specialty, String degree_name, boolean isDean) {
        super(name, address, phone, email, id, specialty, degree_name, isDean);
    }

    public FullTime() {

    }

    @Override
    public double computePayRoll() {
        return Double.parseDouble(String.format("%.2f", 32 * getDegree().getRate() * 2 * 0.85));
    }

    @Override
    public String toString() {
        return "FullTime Teacher{" + super.toString() +
                "Salary=" + this.computePayRoll() +
                '}';
    }
}
