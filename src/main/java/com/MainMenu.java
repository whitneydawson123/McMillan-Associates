package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class MainMenu {

    static void mainDisplay(){
        System.out.println("Welcome to the McMillanHRIS employee management system. " +
                "Enter the corresponding number key to select an option.\n\n" +
                "1. Employee information\n" +
                "2. Applicant information\n" +
                "3. Evaluations\n" +
                "4. Employee self service\n" +
                "5. Exit system\n");
    }

    static void startMainMenu(Connection conn){
        String input;
        Scanner keyboard = new Scanner(System.in);
        boolean running = true;

        mainDisplay();
        while (running){


            input = keyboard.nextLine();

            if (input.equals("1")){
                startEmployeeMenu();
                mainDisplay();
            }
            else if (input.equals("2")) {
                startApplicantInformationMenu();
                mainDisplay();
            }
            else if (input.equals("3")){
                startEvaluationsMenu();
                mainDisplay();
            }
            else if (input.equals("4")){
                // need a condition here to enter a valid employee id before self service can begin
                System.out.println("Welcome to the employee self service menu. Please enter the employee ID: ");
                startSelfServiceMenu();
                mainDisplay();
            }
            else if (input.equals("5")){
                System.out.println("Goodbye.");
                running = false;
            }
            else{
                System.out.println("Invalid input. \n");
            }

        }
    }

    static void employeeDisplay() {
        System.out.println("This is the employee information menu. Enter the corresponding number to select an option.\n\n" +
                "1. Basic information\n" +
                "2. Jobs and positions\n" +
                "3. Benefits\n" +
                "4. Payroll\n" +
                "5. Certifications \n" +
                "6. Qualifications \n" +
                "7. Training\n" +
                "8. Tax information menu\n" +
                "9. Edit employee basic information\n" +
                "10. Create new employee\n" +
                "11. Delete employee\n" +
                "12. Return to main menu\n");
    }

    static void startEmployeeMenu(){
        String input;
        Scanner keyboard = new Scanner(System.in);
        boolean running = true;

        employeeDisplay();
        while (running){

            input = keyboard.nextLine();

            if(input.equals("1")){
                // display all info on the employee table
            }
            else if (input.equals("2")){
                startJobsMenu();
                employeeDisplay();
            }
            else if (input.equals("3")){
                startBenefitsMenu();
                employeeDisplay();
            }
            else if (input.equals("4")){
                startPayrollMenu();
                employeeDisplay();
            }
            else if (input.equals("5")){
                startCertificationsMenu();
                employeeDisplay();
            }
            else if (input.equals("6")){
                startQualificationsMenu();
                employeeDisplay();
            }
            else if (input.equals("7")){
                startTrainingMenu();
                employeeDisplay();
            }
            else if (input.equals("8")){
                startTaxInformationMenu();
                employeeDisplay();
            }
            else if (input.equals("9")){

            }
            else if (input.equals("10")){

            }
            else if (input.equals("11")){

            }
            else if (input.equals("12")){
                running = false;
            }
            else{
                System.out.println("Invalid input. \n");
            }

        }
    }

    static void jobsDisplay() {
        System.out.println("Welcome to the jobs and positions menu. Enter the corresponding number to select an option\n\n" +
                "1. View jobs by employee id\n" +
                "2. View jobs by department id\n" +
                "3. Filter by managers\n" +
                "4. Edit job\n" +
                "5. Create job\n" +
                "6. Delete job\n" +
                "7. Return to previous menu\n");
    }

    static void startJobsMenu(){
        String input;
        Scanner keyboard = new Scanner(System.in);
        boolean running = true;

        jobsDisplay();
        while (running){

            input = keyboard.nextLine();

            if(input.equals("1")){
                // group job records by employee id
            }
            else if (input.equals("2")){
                // group job records by department id
            }
            else if (input.equals("3")){
                // only jobs that start with 'Manager'
            }
            else if (input.equals("4")){
                // edit a job record
            }
            else if (input.equals("5")){
                // create a job record
            }
            else if (input.equals("6")){
                // delete a job record
            }
            else if (input.equals("7")){
                running = false;
            }
            else{
                System.out.println("Invalid input. \n");
            }

        }
    }

    static void taxInformationDisplay() {
        System.out.println("Welcome to the tax information menu. Enter the corresponding number to select an option\n\n" +
                "1. View federal tax record of an employee\n" +
                "2. Edit federal tax record of an employee\n" +
                "3. Create federal tax record of an employee\n" +
                "4. Delete federal tax record of an employee\n" +
                "5. View state tax record of an employee\n" +
                "6. Edit state tax record of an employee\n" +
                "7. Create state tax record of an employee\n" +
                "8. Delete state tax record of an employee\n" +
                "9. Return to previous menu\n");
    }

    static void startTaxInformationMenu(){
        String input;
        Scanner keyboard = new Scanner(System.in);
        boolean running = true;

        taxInformationDisplay();
        while (running){

            input = keyboard.nextLine();

            if(input.equals("1")){
                // display fed tax record associated with employee id
            }
            else if (input.equals("2")){
                // edit a fed tax record
            }
            else if (input.equals("3")){
                // create new fed tax record associated with an employee id that doesn't already have one
            }
            else if (input.equals("4")){
                // delete fed tax record associated with employee id
            }
            else if (input.equals("5")){
                // display state tax record associated with employee id
            }
            else if (input.equals("6")){
                // edit a state tax record
            }
            else if (input.equals("7")){
                // create new state tax record associated with an employee id
            }
            else if (input.equals("8")){
                // delete a state tax record
            }
            else if (input.equals("9")){
                running = false;
            }
            else{
                System.out.println("Invalid input. \n");
            }

        }
    }

    static void applicantInformationDisplay() {
        System.out.println("This is the applicant information menu. Enter the corresponding number to select an option\n\n" +
                "1. View basic applicant info\n" +
                "2. Edit applicant info\n" +
                "3. Delete applicant\n" +
                "4. Create applicant\n" +
                "5. Applicant stage menu\n" +
                "6. Return to previous menu\n");
    }

    static void startApplicantInformationMenu(){
        String input;
        Scanner keyboard = new Scanner(System.in);
        boolean running = true;

        applicantInformationDisplay();
        while (running){

            input = keyboard.nextLine();

            if(input.equals("1")){

            }
            else if (input.equals("2")){

            }
            else if (input.equals("3")){

            }
            else if (input.equals("4")){

            }
            else if (input.equals("5")){

            }
            else if (input.equals("6")){
                running = false;
            }
            else{
                System.out.println("Invalid input. \n");
            }

        }
    }

    static void qualificationsDisplay() {
        System.out.println("This is the qualifications menu. Enter the corresponding number to select an option\n" +
                "1. View qualifications\n" +
                "2. Edit qualification\n" +
                "3. Add qualification\n" +
                "4. Delete qualification\n" +
                "5. Return to previous menu\n");
    }

    static void startQualificationsMenu(){
        String input;
        Scanner keyboard = new Scanner(System.in);
        boolean running = true;

        qualificationsDisplay();
        while (running){

            input = keyboard.nextLine();

            if(input.equals("1")){

            }
            else if (input.equals("2")){

            }
            else if (input.equals("3")){

            }
            else if (input.equals("4")){

            }
            else if (input.equals("5")){
                running = false;
            }
            else{
                System.out.println("Invalid input. \n");
            }

        }
    }

    static void certificationsDisplay() {
        System.out.println("This is the certifications menu. Enter the corresponding number to select an option\n" +
                "1. View certifications\n" +
                "2. Edit certification\n" +
                "3. Add certification\n" +
                "4. Delete certification\n" +
                "5. Return to previous menu\n");
    }

    static void startCertificationsMenu(){
        String input;
        Scanner keyboard = new Scanner(System.in);
        boolean running = true;

        certificationsDisplay();
        while (running){

            input = keyboard.nextLine();

            if(input.equals("1")){

            }
            else if (input.equals("2")){

            }
            else if (input.equals("3")){

            }
            else if (input.equals("4")){

            }
            else if (input.equals("5")){
                running = false;
            }
            else{
                System.out.println("Invalid input. \n");
            }

        }
    }

    static void trainingDisplay() {
        System.out.println("This is the training menu. Enter the corresponding number to select an option\n" +
                "1. View training record\n" +
                "2. Edit training record \n" +
                "3. Add training record\n" +
                "4. Delete training record\n" +
                "5. Return to previous menu\n");
    }

    static void startTrainingMenu(){
        String input;
        Scanner keyboard = new Scanner(System.in);
        boolean running = true;

        trainingDisplay();
        while (running){

            input = keyboard.nextLine();

            if(input.equals("1")){

            }
            else if (input.equals("2")){

            }
            else if (input.equals("3")){

            }
            else if (input.equals("4")){

            }
            else if (input.equals("5")){
                running = false;
            }
            else{
                System.out.println("Invalid input. \n");
            }

        }
    }

    static void payrollDisplay() {
        System.out.println("This is the payroll information menu. Enter the corresponding number to select an option.\n\n" +
                "1. Filter by pay id\n" +
                "2. Filter by employee id\n" +
                "3. Filter by payroll period date(mm/dd/yy)\n" +
                "4. Edit payroll by id\n" +
                "5. Create new payroll\n" +
                "6. Delete payroll\n" +
                "7. Return to main menu\n");
    }

    static void startPayrollMenu(){
        String input;
        Scanner keyboard = new Scanner(System.in);
        boolean running = true;

        payrollDisplay();
        while (running){

            input = keyboard.nextLine();

            if(input.equals("1")){

            }
            else if (input.equals("2")){

            }
            else if (input.equals("3")){

            }
            else if (input.equals("4")){

            }
            else if (input.equals("5")){

            }
            else if (input.equals("6")){

            }
            else if (input.equals("7")){
                running = false;
            }
            else{
                System.out.println("Invalid input. \n");
            }

        }
    }

    static void benefitsDisplay() {
        System.out.println("This is the benefits menu. Enter the corresponding number to select an option.\n\n" +
                "1. View benefits of an employee\n" +
                "2. Edit benefits of an employee\n" +
                "3. Create benefits record for an employee\n" +
                "4. Delete benefits record of employee\n" +
                "5. Return to main menu\n");
    }

    static void startBenefitsMenu(){
        String input;
        Scanner keyboard = new Scanner(System.in);
        boolean running = true;

        benefitsDisplay();
        while (running){

            input = keyboard.nextLine();

            if(input.equals("1")){

            }
            else if (input.equals("2")){

            }
            else if (input.equals("3")){

            }
            else if (input.equals("4")){

            }
            else if (input.equals("5")){
                running = false;
            }
            else{
                System.out.println("Invalid input. \n");
            }

        }
    }

    static void evaluationsDisplay() {
        System.out.println("This is the evaluations menu. Enter the corresponding number to select an option.\n\n" +
                "1. Filter by employee id\n" +
                "2. Filter by evaluator name\n" +
                "3. Filter by the date of report(mm/dd/yy)\n" +
                "4. Edit evaluation\n" +
                "5. Create evaluation\n" +
                "6. Delete evaluation\n" +
                "7. Return to main menu\n");
    }

    static void startEvaluationsMenu(){
        String input;
        Scanner keyboard = new Scanner(System.in);
        boolean running = true;

        evaluationsDisplay();
        while (running){

            input = keyboard.nextLine();

            if(input.equals("1")){

            }
            else if (input.equals("2")){

            }
            else if (input.equals("3")){

            }
            else if (input.equals("4")){

            }
            else if (input.equals("5")){

            }
            else if (input.equals("6")){

            }
            else if (input.equals("7")){
                running = false;
            }
            else{
                System.out.println("Invalid input. \n");
            }

        }
    }

    static void selfServiceDisplay() {
        System.out.println("This is the employee self service menu. Enter the corresponding number to select an option.\n\n" +
                "1. Update address\n" +
                "2. Update city\n" +
                "3. Update state\n" +
                "4. Update zip\n" +
                "5. Update email\n" +
                "6. Update phone\n" +
                "7. View benefits\n" +
                "8. View payroll\n" +
                "9. Qualifications\n" +
                "10. Certifications\n" +
                "11. Return to previous menu\n");
    }

    static void startSelfServiceMenu(){
        String input;
        Scanner keyboard = new Scanner(System.in);
        boolean running = true;

        selfServiceDisplay();
        while (running){

            input = keyboard.nextLine();

            if(input.equals("1")){

            }
            else if (input.equals("2")){

            }
            else if (input.equals("3")){

            }
            else if (input.equals("4")){

            }
            else if (input.equals("5")){

            }
            else if (input.equals("6")){

            }
            else if (input.equals("7")){

            }
            else if (input.equals("8")){

            }
            else if (input.equals("9")){
                // probably an overload that takes the provided employee ID as an argument
            }
            else if (input.equals("10")){
                // probably an overload that takes the provided employee ID as an argument
            }
            else if (input.equals("11")){
                running = false;
            }
            else{
                System.out.println("Invalid input. \n");
            }

        }
    }
    public static void main(String[] args) {

        // establish a database connection and assign it to a variable to be used for all future classes
        String dbURL = "jdbc:mysql://localhost:3306/McMillanHRIS";
        String username = "root";
        String password = "password";
        try{
            Connection conn = DriverManager.getConnection(dbURL, username, password);

            if (conn != null) {
                System.out.println("Connected to McMillanHRIS");
                startMainMenu(conn);
            }
            conn.close();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }


    }












}
