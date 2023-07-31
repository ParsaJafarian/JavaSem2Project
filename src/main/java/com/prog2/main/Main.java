package com.prog2.main;

import com.prog2.main.employees.*;

import java.io.*;
import java.util.Scanner;

/**
 * @author adinashby
 * @author Parsa Jafarian
 */

public class Main {

    /**
     * @param departments an ArrayList of departments that will be displayed
     */
    private static void displayDepartments(ArrayList<Department> departments) {
        for (int i = 0; i < departments.size(); i++) {
            System.out.println(i + ". " + departments.get(i));
        }
        System.out.println();
    }

    /**
     * @param department the department of Department class that will be displayed
     */
    private static void displayDepartment(Department department) {
        System.out.println("The " + department.getName() + " Department ");

        if (!department.getStaff().isEmpty()) {
            System.out.println("Staff:");
            for (int i = 0; i < department.getStaff().size(); i++) {
                System.out.println(i + ". " + department.getStaff().get(i));
            }
        }
        if (!department.getTeachers().isEmpty()) {
            System.out.println("Teachers: ");
            for (int i = 0; i < department.getTeachers().size(); i++) {
                System.out.println(i + ". " + department.getTeachers().get(i));
            }
        }
    }

    /**
     * @param departments ArrayList of departments in which a department is added
     * @param scanner     Scanner object that will be passed in order to get console input
     */
    private static void addDepartment(ArrayList<Department> departments, Scanner scanner) {
        scanner.nextLine();
        System.out.println("Please enter the new department's name:");
        String name = scanner.nextLine();
        if (departments.stream().noneMatch(department -> department.getName().equalsIgnoreCase(name))) {
            departments.add(new Department(name));
            System.out.println("New department added");
        }
        System.out.println("This department already exists");
    }

    /**
     * @param department department in which a staff will be added
     * @param scanner    Scanner object that will be passed in order to get console input
     */
    private static void addStaff(Department department, Scanner scanner) {
        scanner.nextLine();
        System.out.println("Enter name: ");
        String name = scanner.nextLine();
        System.out.println("Enter address:");
        String address = scanner.nextLine();
        System.out.println("Enter phone:");
        String phone = scanner.nextLine();
        System.out.println("Enter email address:");
        String email = scanner.nextLine();
        System.out.println("Enter Id");
        int id = acceptInt(scanner);
        scanner.nextLine();
        System.out.println("Enter duty:");
        String duty = scanner.nextLine();
        System.out.println("Enter workload");
        int workload = -1;
        while (workload <= 0 || workload > 40) {
            workload = acceptInt(scanner);
        }
        try{
            department.addStaff(new Staff(name, address, phone, email, id, duty, workload));
        } catch (RuntimeException ignored){
            //No need to stop the program because of exception
        }
    }

    /**
     * @param department department in which a teacher is added
     * @param scanner    Scanner object used for console input to add the teacher
     */
    private static void addTeacher(Department department, Scanner scanner) {
        int input;
        scanner.nextLine();
        System.out.println("Enter name:");
        String name = scanner.nextLine();
        System.out.println("Enter address:");
        String address = scanner.nextLine();
        System.out.println("Enter phone number:");
        String phone = scanner.nextLine();
        System.out.println("Enter email address");
        String email = scanner.nextLine();
        System.out.println("Enter specialty:");
        String specialty = scanner.nextLine();
        //Setting degree
        String degree = null;
        System.out.println("Enter degree name (Bachelor, Master, Phd):");
        while(degree == null){
            String ans = scanner.nextLine();
            if(Teacher.validDegree(ans)) degree = ans;
        }
        //Setting id, dean and part-time/full-time
        System.out.println("Enter Id");
        int id = acceptInt(scanner);

        System.out.println("Enter if dean(1 if yes, 0 if no)");
        boolean isDean;
        do {
            input = acceptInt(scanner);
        } while (input != 1 && input != 0);
        isDean = input == 1;

        System.out.println("Enter if part-time(0) or full-time(1)");
        boolean fullTime;

        do {
            input = acceptInt(scanner);
        } while (input != 1 && input != 0);
        fullTime = input == 1;

        if (fullTime) {
            try{
                department.addTeacher(new FullTime(name, address, phone, email, id, specialty, degree, isDean));
            } catch (RuntimeException ignored){
                //ignored because the teacher is just not added, no need to stop the program and
                //make the user lose progress
            }
        } else {
            System.out.println("Enter the hours worked (1 to 40 hours)");
            int hours = -1;
            while (hours <= 0 || hours > 40) {
                hours = acceptInt(scanner);
            }
            department.addTeacher(new PartTime(name, address, phone, email, id, specialty, degree, hours, isDean));
        }
    }

    /**
     * @param departments ArrayList of department in which a department will be removed based on its index
     * @param scanner     Scanner object passed in for console input
     */
    private static void removeDepartment(ArrayList<Department> departments, Scanner scanner) {
        if (departments.isEmpty()) {
            System.out.println("There are no departments");
        } else {
            System.out.println("Enter the department's id ");
            int i;
            do {
                i = acceptInt(scanner);
            } while (i < 0 || i >= departments.size());

            departments.remove(i);
            System.out.println("Department removed");
        }
    }

    /**
     * @param department Department in which one Staff will be removed based on its index
     * @param scanner    Scanner object passed in for console input
     */
    private static void removeStaff(Department department, Scanner scanner) {
        if (department.getStaff().isEmpty()) {
            System.out.println("There is no staff ");
        } else {
            System.out.println("Enter the index of the staff you want to remove");
            int i;
            do {
                i = acceptInt(scanner);
            } while (i < 0 || i >= department.getStaff().size());

            department.getStaff().remove(i);
        }
    }

    /**
     * @param department department in which a teacher is removed
     * @param scanner    Scanner object passed in for console input
     */
    private static void removeTeacher(Department department, Scanner scanner) {
        if (department.getTeachers().isEmpty()) {
            System.out.println("There are no teachers left");
        } else {
            System.out.println("Enter the index of the teacher you want to remove");
            int i;
            do {
                i = acceptInt(scanner);
            } while (i < 0 || i >= department.getTeachers().size());

            department.removeTeacher(i);
        }
    }

    /**
     * @param departments arraylist of departments
     * @param scanner     Scanner object passed in for console input
     * @return a department from the arraylist of departments
     */
    private static Department getDepartment(ArrayList<Department> departments, Scanner scanner) {
        if (departments.isEmpty()) {
            System.out.println("No departments exist");
            return null;
        }
        System.out.println("Enter the index of the department you want to inspect");
        int i;
        do {
            i = acceptInt(scanner);
        } while (i < 0 || i >= departments.size());
        return departments.get(i);
    }

    /**
     *
     * @param department department where its list of teachers and staff are updated
     * @param scanner used to remove and add employees inside the department
     */
    private static void inspectDepartment(Department department, Scanner scanner) {
        int input;
        do {
            displayDepartment(department);
            System.out.println();
            System.out.println("0(back) 1(add staff) 2(remove staff) 3(add teacher) 4(remove teacher)");
            System.out.println("5 (sort by name) 6(sort by age) 7(sort by address) 8(sort by salary) 9(sort by deans)");

            input = acceptInt(scanner);
            switch (input) {
                case 1 -> addStaff(department, scanner);
                case 2 -> removeStaff(department, scanner);
                case 3 -> addTeacher(department, scanner);
                case 4 -> removeTeacher(department, scanner);
                //Sorting
                case 5 -> {
                    department.getStaff().sort(new Staff.NameComparator());
                    department.getTeachers().sort(new Teacher.NameComparator());
                }
                case 6 -> {
                    department.getStaff().sort(new Staff.IdComparator());
                    department.getTeachers().sort(new Teacher.IdComparator());
                }
                case 7 -> {
                    department.getStaff().sort(new Staff.AddressComparator());
                    department.getTeachers().sort(new Teacher.AddressComparator());
                }
                case 8 -> {
                    department.getStaff().sort(new Staff.SalaryComparator());
                    department.getTeachers().sort(new Teacher.SalaryComparator());
                }
                case 9 -> department.getTeachers().sort(new Teacher.isDeanComparator());
            }
        } while (input != 0);
    }

    /**
     * @param scanner Scanner object used to accept an integer
     * @return a console integer input from the user that is checked with exception handling that is an integer
     */
    private static int acceptInt(Scanner scanner) {
        int input;
        while (true) {
            try {
                input = scanner.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Invalid input");
                scanner.nextLine();
            }
        }
        return input;
    }

    /**
     * @param obj an object of type ArrayList<Department> that will be written into a text file
     * @throws IOException in case of the file not existing when deserializing
     */
    private static void serialize(ArrayList<Department> obj) throws IOException {
        FileOutputStream fos = new FileOutputStream("data.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(obj);
        fos.close();
        oos.close();
    }

    /**
     * @return a deserialized object of type arraylist of departments from a text file
     */
    private static ArrayList<Department> deserialize() {
        ArrayList<Department> obj = null;
        try (FileInputStream fis = new FileInputStream("data.txt")) {
            ObjectInputStream ois = new ObjectInputStream(fis);
            obj = (ArrayList<Department>) ois.readObject();
        } catch (Exception e) {
            System.out.println(e.getClass() + ": " + e.getMessage());
        }
        return obj;
    }

    /**
     * @throws IOException in case of the data file being non-existing in the deserialization process
     */

    public static void main(String[] args) throws IOException {
        File file = new File("data.txt");
        ArrayList<Department> departments;
        if (file.length() != 0) {
            departments = deserialize();
        } else {
            departments = new ArrayList<>();
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Department Manager Application (DMA)!");

        int input;
        loop:
        while (true) {
            System.out.println();
            displayDepartments(departments);
            System.out.println("0(quit) 1(add department) 2(remove department) 3(inspect a department)");
            System.out.println("4(Sort by name) 5(sort by size) 6(sort by deans)");

            input = acceptInt(scanner);

            switch (input) {
                case 0 -> {
                    //Check if a department without a dean exists
                    departments.sort(new Department.HasDeanComparator());
                    if (!departments.isEmpty() && !departments.get(0).hasDean()) {
                        System.out.println("No departments without deans are allowed, please add deans");
                    } else break loop;
                }
                case 1 -> addDepartment(departments, scanner);
                case 2 -> removeDepartment(departments, scanner);
                case 3 -> {
                    Department department = getDepartment(departments, scanner);
                    if (department == null) continue;
                    inspectDepartment(department, scanner);
                }
                //Sorting commands
                case 4 -> departments.sort(new Department.NameComparator());
                case 5 -> departments.sort(new Department.SizeComparator());
                case 6 -> departments.sort(new Department.HasDeanComparator());
            }
        }
        serialize(departments);
        scanner.close();
    }
}
