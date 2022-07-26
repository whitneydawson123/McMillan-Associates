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
                    System.out.println("Welcome to the employee self service menu. Please enter the employee ID: ");
                    String id = StatementCreator.integerValidator(); // validates that the input is an integer

                    // validates that the ID is both an integer and exists on the database
                    if (!id.equals("")
                            && !StatementCreator.createReadableColumns(
                                    id, "employee", "employee_id", conn)[0].isBlank()) {
                        startSelfServiceMenu(id, conn);
                    }
                    else System.out.println("Invalid ID");
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
                case "3" -> System.out.println("placeholder for manager filter");
                case "4" -> {
                    System.out.print("Please enter the ID of the job to be updated: ");

                    String id = StatementCreator.integerValidator();

                    if (!id.equals("")) StatementCreator.recordUpdater(
                            id, "job", "job_id", conn);
                }
                case "5" -> StatementCreator.recordInserter("job", conn);
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
                case "1" -> {
                    System.out.print("Please enter the ID of the employee: ");

                    String id = StatementCreator.integerValidator();

                    if (!id.equals("")) {
                        String[] lines = StatementCreator.createReadableColumns(
                                id, "federal_tax", "employee_id", conn);
                        StatementCreator.recordPrinter(lines);
                    }
                }
                case "2" -> {
                    System.out.print("Please enter the ID of the federal tax record to be updated: ");

                    String id = StatementCreator.integerValidator();

                    if (!id.equals("")) StatementCreator.recordUpdater(
                            id, "federal_tax", "federal_tax_id", conn);
                }
                case "3" -> StatementCreator.recordInserter("federal_tax", conn);
                case "4" -> {
                    System.out.print("Please enter the ID of the federal tax record to be deleted: ");

                    String id = StatementCreator.integerValidator();

                    if (!id.equals("")) {
                        System.out.println(StatementCreator.recordDeleter(
                                id, "federal_tax", "federal_tax_id", conn));
                    }

                }
                case "5" -> {
                    System.out.print("Please enter the ID of the employee: ");

                    String id = StatementCreator.integerValidator();

                    if (!id.equals("")) {
                        String[] lines = StatementCreator.createReadableColumns(
                                id, "state_tax", "employee_id", conn);
                        StatementCreator.recordPrinter(lines);
                    }
                }
                case "6" -> {
                    System.out.print("Please enter the ID of the state tax record to be updated: ");

                    String id = StatementCreator.integerValidator();

                    if (!id.equals("")) StatementCreator.recordUpdater(
                            id, "state_tax", "state_tax_id", conn);
                }
                case "7" -> StatementCreator.recordInserter("state_tax", conn);
                case "8" -> {
                    System.out.print("Please enter the ID of the state tax record to be deleted: ");

                    String id = StatementCreator.integerValidator();

                    if (!id.equals("")) {
                        System.out.println(StatementCreator.recordDeleter(
                                id, "state_tax", "state_tax_id", conn));
                    }

                }
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
                case "1" -> {
                    System.out.print("Please enter the ID of the employee: ");

                    String id = StatementCreator.integerValidator();

                    if (!id.equals("")) {
                        String[] lines = StatementCreator.createReadableColumns(
                                id, "applicant_tracking", "employee_id", conn);
                        StatementCreator.recordPrinter(lines);
                    }
                }
                case "2" -> {
                    System.out.print("Please enter the ID of the applicant to be updated: ");

                    String id = StatementCreator.integerValidator();

                    if (!id.equals("")) StatementCreator.recordUpdater(
                            id, "applicant_tracking", "applicant_id", conn);
                }
                case "3" -> StatementCreator.recordInserter("applicant_tracking", conn);
                case "4" -> {
                    System.out.print("Please enter the ID of the applicant to be deleted: ");

                    String id = StatementCreator.integerValidator();

                    if (!id.equals("")) {
                        System.out.println(StatementCreator.recordDeleter(
                                id, "applicant_tracking", "applicant_id", conn));
                    }

                }
                case "5" -> startApplicantStageMenu(conn);
                case "6" -> running = false;
                default -> System.out.println("Invalid input. \n");
            }

        }
    }

    static void applicantStageDisplay() {
        System.out.println("""
                This is the applicant stage menu. Enter the corresponding number to select an option
                                
                1. Filter applicant stages by id
                2. Filter applicant stages by applicant id
                3. Filter applicant stages by stage
                4. Filter applicant stages by start date
                5. Filter applicant stages by end date
                6. Edit application stage record
                7. Create application stage record
                8. Delete application stage record
                9. Previous menu
                """);
    }

    static void startApplicantStageMenu(Connection conn){
        String input;
        Scanner keyboard = new Scanner(System.in);
        boolean running = true;

        applicantStageDisplay();
        while (running){
            System.out.println("Enter 0 if you wish to see the applicant information menu options again.");

            input = keyboard.nextLine();

            switch (input) {
                case "0" -> applicantStageDisplay();
                case "1" -> {
                    System.out.print("Please enter the ID of the application stage record: ");

                    String id = StatementCreator.integerValidator();

                    if (!id.equals("")) {
                        String[] lines = StatementCreator.createReadableColumns(
                                id, "application_stage", "application_stage_id", conn);
                        StatementCreator.recordPrinter(lines);
                    }
                }
                case "2" -> {
                    System.out.print("Please enter the employee ID of the applicant: ");

                    String id = StatementCreator.integerValidator();

                    if (!id.equals("")) {
                        String[] lines = StatementCreator.createReadableColumns(
                                id, "applicant_tracking", "employee_id", conn);
                        StatementCreator.recordPrinter(lines);
                    }
                }
                case "3" -> {
                    System.out.print("Please enter the stage of the application stage record: ");

                    String id = StatementCreator.integerValidator();

                    if (!id.equals("")) {
                        String[] lines = StatementCreator.createReadableColumns(
                                id, "applicant_tracking", "application_stage", conn);
                        StatementCreator.recordPrinter(lines);
                    }
                }
                case "4" -> {
                    System.out.print("Please enter the start date of the application stage record: ");

                    String id = StatementCreator.integerValidator();

                    if (!id.equals("")) {
                        String[] lines = StatementCreator.createReadableColumns(
                                id, "applicant_tracking", "started", conn);
                        StatementCreator.recordPrinter(lines);
                    }
                }
                case "5" -> {
                    System.out.print("Please enter the end date of the application stage record: ");

                    String id = StatementCreator.integerValidator();

                    if (!id.equals("")) {
                        String[] lines = StatementCreator.createReadableColumns(
                                id, "applicant_tracking", "ended", conn);
                        StatementCreator.recordPrinter(lines);
                    }
                }
                case "6" ->
                {
                    System.out.print("Please enter the ID of the application stage to be updated: ");

                    String id = StatementCreator.integerValidator();

                    if (!id.equals("")) StatementCreator.recordUpdater(
                            id, "application_stage", "application_stage_id", conn);
                }
                case "7" -> StatementCreator.recordInserter("application_stage", conn);
                case "8" -> {
                    System.out.print("Please enter the ID of the application stage to be deleted: ");

                    String id = StatementCreator.integerValidator();

                    if (!id.equals("")) {
                        System.out.println(StatementCreator.recordDeleter(
                                id, "application_stage", "application_stage_id", conn));
                    }

                }
                case "9" -> running = false;
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
                case "1" -> {
                    System.out.print("Please enter the ID of the employee: ");

                    String id = StatementCreator.integerValidator();

                    if (!id.equals("")) {
                        String[] lines = StatementCreator.createReadableColumns(
                                id, "qualification", "employee_id", conn);
                        StatementCreator.recordPrinter(lines);
                    }
                }
                case "2" -> {
                    System.out.print("Please enter the ID of the qualification to be updated: ");

                    String id = StatementCreator.integerValidator();

                    if (!id.equals("")) StatementCreator.recordUpdater(
                            id, "qualification", "qualification_id", conn);
                }
                case "3" -> StatementCreator.recordInserter("qualification", conn);
                case "4" -> {
                    System.out.print("Please enter the ID of the qualification to be deleted: ");

                    String id = StatementCreator.integerValidator();

                    if (!id.equals("")) {
                        System.out.println(StatementCreator.recordDeleter(
                                id, "qualification", "qualification_id", conn));
                    }

                }
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
                case "1" -> {
                    System.out.print("Please enter the ID of the employee: ");

                    String id = StatementCreator.integerValidator();

                    if (!id.equals("")) {
                        String[] lines = StatementCreator.createReadableColumns(
                                id, "certification", "employee_id", conn);
                        StatementCreator.recordPrinter(lines);
                    }
                }
                case "2" -> {
                    System.out.print("Please enter the ID of the certification to be updated: ");

                    String id = StatementCreator.integerValidator();

                    if (!id.equals("")) StatementCreator.recordUpdater(
                            id, "certification", "certification_id", conn);
                }
                case "3" -> StatementCreator.recordInserter("certification", conn);
                case "4" -> {
                    System.out.print("Please enter the ID of the certification to be deleted: ");

                    String id = StatementCreator.integerValidator();

                    if (!id.equals("")) {
                        System.out.println(StatementCreator.recordDeleter(
                                id, "certification", "certification_id", conn));
                    }

                }
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
                case "1" -> {
                    System.out.print("Please enter the ID of the employee: ");

                    String id = StatementCreator.integerValidator();

                    if (!id.equals("")) {
                        String[] lines = StatementCreator.createReadableColumns(
                                id, "training", "employee_id", conn);
                        StatementCreator.recordPrinter(lines);
                    }
                }
                case "2" -> {
                    System.out.print("Please enter the ID of the training record to be updated: ");

                    String id = StatementCreator.integerValidator();

                    if (!id.equals("")) StatementCreator.recordUpdater(
                            id, "training", "training_id", conn);
                }
                case "3" -> StatementCreator.recordInserter("training", conn);
                case "4" -> {
                    System.out.print("Please enter the ID of the training record to be deleted: ");

                    String id = StatementCreator.integerValidator();

                    if (!id.equals("")) {
                        System.out.println(StatementCreator.recordDeleter(
                                id, "training", "training_id", conn));
                    }

                }
                case "5" -> running = false;
                default -> System.out.println("Invalid input. \n");
            }

        }
    }

    static void payrollDisplay() {
        System.out.println("""
                This is the payroll information menu. Enter the corresponding number to select an option.

                1. Filter by employee ID
                2. Filter by payroll ID
                3. Filter by payroll period date(YYYY-MM-DD)
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
                case "1" -> {
                    System.out.print("Please enter the ID of the employee: ");

                    String id = StatementCreator.integerValidator();

                    if (!id.equals("")) {
                        String[] lines = StatementCreator.createReadableColumns(
                                id, "payroll", "employee_id", conn);
                        StatementCreator.recordPrinter(lines);
                    }
                }
                case "2" -> {
                    System.out.print("Please enter the payroll ID: ");

                    String id = StatementCreator.integerValidator();

                    if (!id.equals("")) {
                        String[] lines = StatementCreator.createReadableColumns(
                                id, "payroll", "payroll_id", conn);
                        StatementCreator.recordPrinter(lines);
                    }
                }
                case "3" -> {
                    System.out.print("Please enter the payroll period date(YYYY-MM-DD): ");

                    String id = StatementCreator.integerValidator();

                    if (!id.equals("")) {
                        String[] lines = StatementCreator.createReadableColumns(
                                id, "payroll", "period", conn);
                        StatementCreator.recordPrinter(lines);
                    }
                }
                case "4" -> {
                    System.out.print("Please enter the ID of the training record to be updated: ");

                    String id = StatementCreator.integerValidator();

                    if (!id.equals("")) StatementCreator.recordUpdater(
                            id, "payroll", "payroll_id", conn);
                }
                case "5" -> StatementCreator.recordInserter("payroll", conn);
                case "6" -> {
                    System.out.print("Please enter the ID of the training record to be deleted: ");

                    String id = StatementCreator.integerValidator();

                    if (!id.equals("")) {
                        System.out.println(StatementCreator.recordDeleter(
                                id, "payroll", "payroll_id", conn));
                    }

                }
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
                case "1" -> {
                    System.out.print("Please enter the ID of the employee: ");

                    String id = StatementCreator.integerValidator();

                    if (!id.equals("")) {
                        String[] lines = StatementCreator.createReadableColumns(
                                id, "benefit", "employee_id", conn);
                        StatementCreator.recordPrinter(lines);
                    }
                }
                case "2" -> {
                    System.out.print("Please enter the ID of the benefits record to be updated: ");

                    String id = StatementCreator.integerValidator();

                    if (!id.equals("")) StatementCreator.recordUpdater(
                            id, "benefit", "benefit_id", conn);
                }
                case "3" -> StatementCreator.recordInserter("benefit", conn);
                case "4" -> {
                    System.out.print("Please enter the ID of the benefits record to be deleted: ");

                    String id = StatementCreator.integerValidator();

                    if (!id.equals("")) {
                        System.out.println(StatementCreator.recordDeleter(
                                id, "benefit", "benefit_id", conn));
                    }

                }
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
                3. Filter by the date of report(YYYY-MM-DD)
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
                case "1" -> {
                    System.out.print("Please enter the ID of the employee: ");

                    String id = StatementCreator.integerValidator();

                    if (!id.equals("")) {
                        String[] lines = StatementCreator.createReadableColumns(
                                id, "evaluation", "evaluation_id", conn);
                        StatementCreator.recordPrinter(lines);
                    }
                }
                case "2" -> {
                    System.out.print("Please enter the evaluator name to search: ");

                    String id = StatementCreator.integerValidator();

                    if (!id.equals("")) {
                        String[] lines = StatementCreator.createReadableColumns(
                                id, "evaluation", "evaluator", conn);
                        StatementCreator.recordPrinter(lines);
                    }
                }
                case "3" -> {
                    System.out.print("Please enter the date of report(YYYY-MM-DD): ");

                    String id = StatementCreator.integerValidator();

                    if (!id.equals("")) {
                        String[] lines = StatementCreator.createReadableColumns(
                                id, "evaluation", "date_written", conn);
                        StatementCreator.recordPrinter(lines);
                    }
                }
                case "4" -> {
                    System.out.print("Please enter the ID of the evaluation record to be updated: ");

                    String id = StatementCreator.integerValidator();

                    if (!id.equals("")) StatementCreator.recordUpdater(
                            id, "evaluation", "evaluation_id", conn);
                }
                case "5" -> StatementCreator.recordInserter("evaluation", conn);
                case "6" -> {
                    System.out.print("Please enter the ID of the evaluation record to be deleted: ");

                    String id = StatementCreator.integerValidator();

                    if (!id.equals("")) {
                        System.out.println(StatementCreator.recordDeleter(
                                id, "evaluation", "evaluation_id", conn));
                    }

                }
                case "7" -> running = false;
                default -> System.out.println("Invalid input. \n");
            }

        }
    }

    static void selfServiceDisplay() {
        System.out.println("""
                This is the employee self service menu. Enter the corresponding number to select an option.

                1. View basic information
                2. Update address
                3. Update city
                4. Update state
                5. Update zip
                6. Update email
                7. Update phone
                8. View benefits
                9. View payroll
                10. View qualifications
                11. View certifications
                12. Return to previous menu
                """);
    }

    static void startSelfServiceMenu(String id, Connection conn){
        String input;
        Scanner keyboard = new Scanner(System.in);
        boolean running = true;

        selfServiceDisplay();
        while (running){
            System.out.println("Enter 0 if you wish to see the self service menu options again.");

            input = keyboard.nextLine();

            switch (input) {
                case "0" -> selfServiceDisplay();
                case "1" -> {
                    String[] lines = StatementCreator.createReadableColumns(
                            id, "employee", "employee_id", conn);
                    StatementCreator.recordPrinter(lines);
                }
                case "2" -> StatementCreator.employeeColumnUpdater(id, "address", conn);
                case "3" -> StatementCreator.employeeColumnUpdater(id, "city", conn);
                case "4" -> StatementCreator.employeeColumnUpdater(id, "state", conn);
                case "5" -> StatementCreator.employeeColumnUpdater(id, "zip", conn);
                case "6" -> StatementCreator.employeeColumnUpdater(id, "email", conn);
                case "7" -> StatementCreator.employeeColumnUpdater(id, "phone", conn);
                case "8" -> {
                    String[] lines = StatementCreator.createReadableColumns(
                            id, "benefit", "employee_id", conn);
                    StatementCreator.recordPrinter(lines);
                }
                case "9" -> {
                    String[] lines = StatementCreator.createReadableColumns(
                            id, "payroll", "employee_id", conn);
                    StatementCreator.recordPrinter(lines);
                }
                case "10" -> {
                    String[] lines = StatementCreator.createReadableColumns(
                            id, "qualification", "employee_id", conn);
                    StatementCreator.recordPrinter(lines);
                }
                case "11" -> {
                    String[] lines = StatementCreator.createReadableColumns(
                            id, "certification", "employee_id", conn);
                    StatementCreator.recordPrinter(lines);
                }
                case "12" -> running = false;
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
