package com.prog2.main.employees;

public class Staff extends Person {
    private String duty;
    private int workload;

    public Staff(String name, String address, String phone, String email, int date, String duty, int workload) {
        super(name, address, phone, email, date);
        this.duty = duty;
        setWorkload(workload);
    }

    public Staff() {

    }

    public void setWorkload(int workload) {
        if (validWorkload(workload)) this.workload = workload;
    }

    private boolean validWorkload(int workload) {
        return workload < 40 && workload >= 0;
    }

    @Override
    public double computePayRoll() {
        return Double.parseDouble(String.format("%.2f", workload * 32 * 2 * 0.75));
    }

    @Override
    public String toString() {
        return "Staff{" +
                "duty='" + duty + '\'' +
                ", workload=" + workload +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", Id=" + id +
                ", salary=" + this.computePayRoll() +
                '}';
    }

    public boolean equals(Staff s) {
        return super.equals(s) &&
                duty.equals(s.duty) &&
                workload == s.workload;
    }

}
