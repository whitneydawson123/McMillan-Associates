package com;

import java.sql.*;
import java.util.Scanner;

public class MainMenu {

    static void mainDisplay(){
        System.out.println("""
                Welcome to the McMillanHRIS employee management system. Enter the corresponding number key to select an option.

                1. Employee information
                2. Applicant information
                3. Evaluations
                4. Employee self service
                5. Exit system
                """);
    }

    static void startMainMenu(Connection conn){
        String input;
        Scanner keyboard = new Scanner(System.in);
        boolean running = true;


        while (running){
            mainDisplay();

            input = keyboard.nextLine();

            switch (input) {
                case "1" -> startEmployeeMenu(conn);
                case "2" -> startApplicantInformationMenu(conn);
                case "3" ->  startEvaluationsMenu(conn);
                case "4" -> {
                    // need a condition here to enter a valid employee id before self-service can begin
                    System.out.println("Welcome to the employee self service menu. Please enter the employee ID: ");
                    startSelfServiceMenu(conn);
                }
                case "5" -> {
                    System.out.println("Goodbye.");
                    running = false;
                }
                default -> System.out.println("Invalid input. \n");
            }
        }
    }

    static void employeeDisplay() {
        System.out.println("""
                This is the employee information menu. Enter the corresponding number to select an option.

                1. Basic information
                2. Jobs and positions
                3. Benefits
                4. Payroll
                5. Certifications\s
                6. Qualifications\s
                7. Training
                8. Tax information menu
                9. Edit employee basic information
                10. Create new employee
                11. Delete employee
                12. Return to main menu
                """);
    }

    static void startEmployeeMenu(Connection conn){
        String input;
        Scanner keyboard = new Scanner(System.in);
        boolean running = true;

        employeeDisplay();
        while (running){
            System.out.println("Enter 0 if you wish to see the employee information menu options again.");

            input = keyboard.nextLine();

            switch (input) {
                case "0" -> employeeDisplay();
                case "1" -> {
                    System.out.print("Please enter the ID of the employee: ");

                    String id = StatementCreator.integerValidator();

                    if (!id.equals("")) {
                        String[] lines = StatementCreator.createReadableColumns(
                                id, "employee", "employee_id", conn);
                        StatementCreator.recordPrinter(lines);
                    }
                }
                case "2" -> startJobsMenu(conn);
                case "3" -> startBenefitsMenu(conn);
                case "4" -> startPayrollMenu(conn);
                case "5" -> startCertificationsMenu(conn);
                case "6" -> startQualificationsMenu(conn);
                case "7" -> startTrainingMenu(conn);
                case "8" -> startTaxInformationMenu(conn);
                case "9" -> {
                    System.out.print("Please enter the ID of the employee to be updated: ");

                    String id = StatementCreator.integerValidator();

                    if (!id.equals("")) StatementCreator.recordUpdater(
                            id, "employee", "employee_id", conn);
                }
                case "10" -> StatementCreator.recordInserter("employee", conn);
                case "11" -> {
                    System.out.print("Please enter the ID of the employee to be deleted: ");

                    String id = StatementCreator.integerValidator();

                    if (!id.equals("")) {
                        System.out.println(StatementCreator.recordDeleter(
                                id, "employee", "employee_id", conn));
                    }

                }
                case "12" -> running = false;
                default -> System.out.println("Invalid input. \n");
            }

        }
    }

    static void jobsDisplay() {
        System.out.println("""
                Welcome to the jobs and positions menu. Enter the corresponding number to select an option

                1. View jobs by employee id
                2. View jobs by department id
                3. Filter by managers
                4. Edit job
                5. Create job
                6. Delete job
                7. Return to previous menu
                """);
    }

    static void startJobsMenu(Connection conn){
        String input;
        Scanner keyboard = new Scanner(System.in);
        boolean running = true;

        jobsDisplay();
        while (running){
            System.out.println("Enter 0 if you wish to see the jobs and positions menu options again.");

            input = keyboard.nextLine();

            switch (input) {
                case "0" -> jobsDisplay();
                case "1" -> {
                    System.out.print("Please enter the ID of the employee: ");

                    String id = StatementCreator.integerValidator();

                    if (!id.equals("")) {
                        String[] lines = StatementCreator.createReadableColumns(
                                id, "job", "employee_id", conn);
                        StatementCreator.recordPrinter(lines);
                    }
                }
                case "2" -> {
                    System.out.print("Please enter the ID of the department: ");

                    String id = StatementCreator.integerValidator();

                    if (!id.equals("")) {
                        String[] lines = StatementCreator.createReadableColumns(
                                id, "job", "departments_id", conn);
                        StatementCreator.recordPrinter(lines);
                    }
                }
                case "3" -> System.out.println("placeholder");
                case "4" -> System.out.println("placeholder");
                case "5" -> System.out.println("placeholder");
                case "6" -> {
                    System.out.print("Please enter the ID of the employee to be deleted: ");

                    String id = StatementCreator.integerValidator();

                    if (!id.equals("")) {
                        System.out.println(StatementCreator.recordDeleter(
                                id, "job", "job_id", conn));
                    }

                }
                case "7" -> running = false;
                default -> System.out.println("Invalid input. \n");
            }

        }
    }

    static void taxInformationDisplay() {
        System.out.println("""
                Welcome to the tax information menu. Enter the corresponding number to select an option

                1. View federal tax record of an employee
                2. Edit federal tax record of an employee
                3. Create federal tax record of an employee
                4. Delete federal tax record of an employee
                5. View state tax record of an employee
                6. Edit state tax record of an employee
                7. Create state tax record of an employee
                8. Delete state tax record of an employee
                9. Return to previous menu
                """);
    }

    static void startTaxInformationMenu(Connection conn){
        String input;
        Scanner keyboard = new Scanner(System.in);
        boolean running = true;

        taxInformationDisplay();
        while (running){
            System.out.println("Enter 0 if you wish to see the tax information menu options again.");

            input = keyboard.nextLine();

            switch (input) {
                case "0" -> taxInformationDisplay();
                case "1" -> System.out.println("placeholder");
                case "2" -> System.out.println("placeholder");
                case "3" -> System.out.println("placeholder");
                case "4" -> System.out.println("placeholder");
                case "5" -> System.out.println("placeholder");
                case "6" -> System.out.println("placeholder");
                case "7" -> System.out.println("placeholder");
                case "8" -> System.out.println("placeholder");
                case "9" -> running = false;
                default -> System.out.println("Invalid input. \n");
            }

        }
    }

    static void applicantInformationDisplay() {
        System.out.println("""
                This is the applicant information menu. Enter the corresponding number to select an option

                1. View basic applicant info
                2. Edit applicant info
                3. Delete applicant
                4. Create applicant
                5. Applicant stage menu
                6. Return to previous menu
                """);
    }

    static void startApplicantInformationMenu(Connection conn){
        String input;
        Scanner keyboard = new Scanner(System.in);
        boolean running = true;

        applicantInformationDisplay();
        while (running){
            System.out.println("Enter 0 if you wish to see the applicant information menu options again.");

            input = keyboard.nextLine();

            switch (input) {
                case "0" -> applicantInformationDisplay();
                case "1" -> System.out.println("placeholder");
                case "2" -> System.out.println("placeholder");
                case "3" -> System.out.println("placeholder");
                case "4" -> System.out.println("placeholder");
                case "5" -> System.out.println("placeholder");
                case "6" -> running = false;
                default -> System.out.println("Invalid input. \n");
            }

        }
    }

    static void qualificationsDisplay() {
        System.out.println("""
                This is the qualifications menu. Enter the corresponding number to select an option
                
                1. View qualifications
                2. Edit qualification
                3. Add qualification
                4. Delete qualification
                5. Return to previous menu
                """);
    }

    static void startQualificationsMenu(Connection conn){
        String input;
        Scanner keyboard = new Scanner(System.in);
        boolean running = true;

        qualificationsDisplay();
        while (running){
            System.out.println("Enter 0 if you wish to see the qualifications menu options again.");

            input = keyboard.nextLine();

            switch (input) {
                case "0" -> qualificationsDisplay();
                case "1" -> System.out.println("placeholder");
                case "2" -> System.out.println("placeholder");
                case "3" -> System.out.println("placeholder");
                case "4" -> System.out.println("placeholder");
                case "5" -> running = false;
                default -> System.out.println("Invalid input. \n");
            }

        }
    }

    static void certificationsDisplay() {
        System.out.println("""
                This is the certifications menu. Enter the corresponding number to select an option
                
                1. View certifications
                2. Edit certification
                3. Add certification
                4. Delete certification
                5. Return to previous menu
                """);
    }

    static void startCertificationsMenu(Connection conn){
        String input;
        Scanner keyboard = new Scanner(System.in);
        boolean running = true;

        certificationsDisplay();
        while (running){
            System.out.println("Enter 0 if you wish to see the certifications menu options again.");

            input = keyboard.nextLine();

            switch (input) {
                case "0" -> certificationsDisplay();
                case "1" -> System.out.println("placeholder");
                case "2" -> System.out.println("placeholder");
                case "3" -> System.out.println("placeholder");
                case "4" -> System.out.println("placeholder");
                case "5" -> running = false;
                default -> System.out.println("Invalid input. \n");
            }

        }
    }

    static void trainingDisplay() {
        System.out.println("""
                This is the training menu. Enter the corresponding number to select an option
                1. View training record
                2. Edit training record\s
                3. Add training record
                4. Delete training record
                5. Return to previous menu
                """);
    }

    static void startTrainingMenu(Connection conn){
        String input;
        Scanner keyboard = new Scanner(System.in);
        boolean running = true;

        trainingDisplay();
        while (running){
            System.out.println("Enter 0 if you wish to see the training menu options again.");

            input = keyboard.nextLine();

            switch (input) {
                case "0" -> trainingDisplay();
                case "1" -> System.out.println("placeholder");
                case "2" -> System.out.println("placeholder");
                case "3" -> System.out.println("placeholder");
                case "4" -> System.out.println("placeholder");
                case "5" -> running = false;
                default -> System.out.println("Invalid input. \n");
            }

        }
    }

    static void payrollDisplay() {
        System.out.println("""
                This is the payroll information menu. Enter the corresponding number to select an option.

                1. Filter by pay id
                2. Filter by employee id
                3. Filter by payroll period date(mm/dd/yy)
                4. Edit payroll by id
                5. Create new payroll
                6. Delete payroll
                7. Return to main menu
                """);
    }

    static void startPayrollMenu(Connection conn){
        String input;
        Scanner keyboard = new Scanner(System.in);
        boolean running = true;

        payrollDisplay();
        while (running){
            System.out.println("Enter 0 if you wish to see the payroll information menu options again.");

            input = keyboard.nextLine();

            switch (input) {
                case "0" -> payrollDisplay();
                case "1" -> System.out.println("placeholder");
                case "2" -> System.out.println("placeholder");
                case "3" -> System.out.println("placeholder");
                case "4" -> System.out.println("placeholder");
                case "7" -> running = false;
                default -> System.out.println("Invalid input. \n");
            }

        }
    }

    static void benefitsDisplay() {
        System.out.println("""
                This is the benefits menu. Enter the corresponding number to select an option.

                1. View benefits of an employee
                2. Edit benefits of an employee
                3. Create benefits record for an employee
                4. Delete benefits record of employee
                5. Return to main menu
                """);
    }

    static void startBenefitsMenu(Connection conn){
        String input;
        Scanner keyboard = new Scanner(System.in);
        boolean running = true;

        benefitsDisplay();
        while (running){
            System.out.println("Enter 0 if you wish to see the benefits menu options again.");

            input = keyboard.nextLine();

            switch (input) {
                case "0" -> benefitsDisplay();
                case "1" -> System.out.println("placeholder");
                case "2" -> System.out.println("placeholder");
                case "3" -> System.out.println("placeholder");
                case "4" -> System.out.println("placeholder");
                case "5" -> running = false;
                default -> System.out.println("Invalid input. \n");
            }

        }
    }

    static void evaluationsDisplay() {
        System.out.println("""
                This is the evaluations menu. Enter the corresponding number to select an option.

                1. Filter by employee id
                2. Filter by evaluator name
                3. Filter by the date of report(mm/dd/yy)
                4. Edit evaluation
                5. Create evaluation
                6. Delete evaluation
                7. Return to main menu
                """);
    }

    static void startEvaluationsMenu(Connection conn){
        String input;
        Scanner keyboard = new Scanner(System.in);
        boolean running = true;

        evaluationsDisplay();
        while (running){
            System.out.println("Enter 0 if you wish to see the evaluations menu options again.");

            input = keyboard.nextLine();

            switch (input) {
                case "0" -> evaluationsDisplay();
                case "1" -> System.out.println("placeholder");
                case "2" -> System.out.println("placeholder");
                case "3" -> System.out.println("placeholder");
                case "4" -> System.out.println("placeholder");
                case "5" -> running = false;
                default -> System.out.println("Invalid input. \n");
            }

        }
    }

    static void selfServiceDisplay() {
        System.out.println("""
                This is the employee self service menu. Enter the corresponding number to select an option.

                1. Update address
                2. Update city
                3. Update state
                4. Update zip
                5. Update email
                6. Update phone
                7. View benefits
                8. View payroll
                9. Qualifications
                10. Certifications
                11. Return to previous menu
                """);
    }

    static void startSelfServiceMenu(Connection conn){
        String input;
        Scanner keyboard = new Scanner(System.in);
        boolean running = true;

        selfServiceDisplay();
        while (running){
            System.out.println("Enter 0 if you wish to see the self service menu options again.");

            input = keyboard.nextLine();

            switch (input) {
                case "0" -> selfServiceDisplay();
                case "1" -> System.out.println("placeholder");
                case "2" -> System.out.println("placeholder");
                case "3" -> System.out.println("placeholder");
                case "4" -> System.out.println("placeholder");
                case "9" -> System.out.println("placeholder"); // should be an overload that takes the already entered ID
                case "10" -> System.out.println("placeholder"); // should be an overload that takes the already entered ID
                case "11" -> running = false;
                default -> System.out.println("Invalid input. \n");
            }

        }
    }
    public static void main(String[] args) {

        // establish a database connection and assign it to a variable to be used for all future methods
        String dbURL = "jdbc:mysql://localhost:3306/McMillanHRIS";
        String username = "root";
        String password = "password";
        try{
            Connection conn = DriverManager.getConnection(dbURL, username, password);

            if (conn != null) {
                System.out.println("Connected to McMillanHRIS");
                startMainMenu(conn);
                conn.close();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }


    }












}
