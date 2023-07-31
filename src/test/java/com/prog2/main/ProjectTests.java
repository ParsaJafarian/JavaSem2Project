package com.prog2.main;

import com.prog2.main.employees.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectTests {

    @Test
    void computePartTimePayRoll1() {
        PartTime pt = new PartTime();
        pt.setDegree("Bachelor");
        pt.setHoursWorked(20);

        assertEquals(1276.8, pt.computePayRoll());
    }

    @Test
    void computePartTimePayRoll2() {
        PartTime pt = new PartTime();
        pt.setDegree("Master");
        pt.setHoursWorked(20);

        assertEquals(2492.8, pt.computePayRoll());
    }

    @Test
    void computePartTimePayRoll3() {
        PartTime pt = new PartTime();
        pt.setDegree("Phd");
        pt.setHoursWorked(20);

        assertEquals(3404.8, pt.computePayRoll());
    }

    @Test
    void computeFullTimePayRoll1() {
        FullTime ft = new FullTime();
        ft.setDegree("Bachelor");

        assertEquals(2284.8, ft.computePayRoll());
    }

    @Test
    void computeFullTimePayRoll2() {
        FullTime ft = new FullTime();
        ft.setDegree("Master");

        assertEquals(4460.8, ft.computePayRoll());
    }

    @Test
    void computeFullTimePayRoll3() {
        FullTime ft = new FullTime();
        ft.setDegree("Phd");

        assertEquals(6092.8, ft.computePayRoll());
    }

    @Test
    void computeStaffPayRoll() {
        Staff s = new Staff();
        s.setWorkload(20);

        assertEquals(960, s.computePayRoll());
    }

    @Test
    void sortDepartmentsNameTest() {
        final ArrayList<Department> actual = new ArrayList<>();
        final ArrayList<Department> expected = new ArrayList<>();

        Department d1 = new Department("Math");
        Department d2 = new Department("Chemistry");
        Department d3 = new Department("Art");
        Department d4 = new Department("Social Science");

        actual.add(d1);
        actual.add(d2);
        actual.add(d3);
        actual.add(d4);

        actual.sort(new Department.NameComparator());

        expected.add(d3);
        expected.add(d2);
        expected.add(d1);
        expected.add(d4);

        assertEquals(expected, actual);
    }

    @Test
    void addTeacherTest() throws RuntimeException {
        Department dep = new Department("Department");

        FullTime ft = new FullTime();
        ft.setName("John");
        ft.setAddress("Boulevard");
        ft.setEmail("gmail.com");
        ft.setPhone("111-111-1111");
        ft.setId(234134);
        ft.setSpecialty("Chemistry");
        ft.setDegree("Bachelor's");
        ft.setDean(true);

        FullTime other = new FullTime();
        //Same id as the other one
        other.setId(234134);

        dep.addTeacher(ft);

        //Assert that it throws a runtime exception when adding the same teacher
        assertThrows(RuntimeException.class, () -> dep.addTeacher(other));
    }

    @Test
    void addStaffTest(){
        Department dep = new Department("Department");

        Staff first = new Staff();
        first.setId(1000);

        Staff other = new Staff();
        other.setId(1000);

        dep.addStaff(first);

        assertThrows(RuntimeException.class, () -> dep.addStaff(other));
    }

    @Test
    void setDegreeTest(){
        FullTime ft = new FullTime();
        ft.setDegree("asldfj;asldkfj");
        assertNull(ft.getDegree());
    }

    @Test
    void sortTeachersSalaryTest(){
        ArrayList<Teacher> actual = new ArrayList<>();
        ArrayList<Teacher> expected = new ArrayList<>();

        FullTime t1 = new FullTime();
        t1.setDegree("Phd");

        FullTime t2 = new FullTime();
        t2.setDegree("Masters");

        PartTime t3 = new PartTime();
        t3.setDegree("Phd");
        t3.setHoursWorked(20);

        PartTime t4 = new PartTime();
        t4.setDegree("Bachelors");
        t4.setHoursWorked(10);

        actual.add(t1);
        actual.add(t2);
        actual.add(t3);
        actual.add(t4);
        actual.sort(new Teacher.SalaryComparator());

        expected.add(t4);
        expected.add(t3);
        expected.add(t2);
        expected.add(t1);

        assertEquals(expected,actual);
    }



}
