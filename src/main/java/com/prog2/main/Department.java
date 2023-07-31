package com.prog2.main;

import com.prog2.main.employees.Staff;
import com.prog2.main.employees.Teacher;

import java.io.Serializable;
import java.util.Comparator;

/**
 * The department class implements Serializable as it will be serialized
 * Department class stores up teachers and staffs. It can update these lists and access them
 */
public class Department implements Serializable {
    private final String name;
    private final ArrayList<Teacher> teachers;
    private final ArrayList<Staff> staff;
    private boolean hasDean = false;

    public Department(String name) {
        this.name = name;
        teachers = new ArrayList<>();
        staff = new ArrayList<>();
    }

    public ArrayList<Teacher> getTeachers() {
        return this.teachers;
    }

    public ArrayList<Staff> getStaff() {
        return this.staff;
    }

    /**
     * Add a teacher if the new teacher's id is not already used
     * @param teacher the teacher that will be added
     * @throws RuntimeException Exception thrown when a teacher with the same id already exists in Department
     */
    public void addTeacher(Teacher teacher){
        if (teachers.stream().noneMatch(t -> t.getId() == teacher.getId())) {
            if (teacher.isDean() && this.hasDean) {
                teacher.setDean(false);
                System.out.println("There already is a dean in this department, the teacher added is not a dean anymore");
            } else if (teacher.isDean() && !this.hasDean) {
                hasDean = true;
            }
            System.out.println("New teacher added");
            teachers.add(teacher);
        } else throw new RuntimeException("The Department already has this teacher");
    }

    /**
     * Add new staff if the new staff's id is not already used
     * @param staff the new staff that is added
     * @throws RuntimeException Exception thrown when a staff with same id exists
     */
    public void addStaff(Staff staff) {
        if (this.staff.stream().noneMatch(s -> s.getId() == staff.getId())) {
            this.staff.add(staff);
            System.out.println("New Staff added");
        } else throw new RuntimeException("The Department already has this staff");
    }

    public void removeTeacher(int i) {
        if (teachers.get(i).isDean()) hasDean = false;
        teachers.remove(i);
        System.out.println("Teacher removed");
    }

    public boolean hasDean() {
        return hasDean;
    }

    public String getName() {
        return name;
    }

    /**
     * This is used for the SizeComparator
     * @return the total amount of employees inside this department
     */
    private int getTotalSize() {
        return staff.size() + teachers.size();
    }

    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                ", hasDean=" + hasDean +
                ", totalSize=" + getTotalSize() +
                '}';
    }

    public boolean equals(Department d) {
        return name.equals(d.name) &&
                staff.equals(d.staff) &&
                teachers.equals(d.teachers) &&
                hasDean == d.hasDean;
    }

    public final static class SizeComparator implements Comparator<Department> {
        @Override
        public int compare(Department o1, Department o2) {
            return o1.getTotalSize() - o2.getTotalSize();
        }
    }

    public final static class NameComparator implements Comparator<Department> {
        @Override
        public int compare(Department o1, Department o2) {
            return o1.name.compareTo(o2.name);
        }
    }

    public final static class HasDeanComparator implements Comparator<Department> {
        @Override
        public int compare(Department o1, Department o2) {
            return Boolean.compare(o1.hasDean, o2.hasDean);
        }
    }
}
