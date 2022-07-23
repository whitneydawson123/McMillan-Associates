package com;

import java.sql.*;
import java.util.Scanner;

public class MainMenu {

    // helper method to test whether user input can be parsed as an integer
    static Integer integerParser(){

        Scanner keyboard = new Scanner(System.in);

        try {
            int idInput = Integer.parseInt(keyboard.nextLine());

            return idInput;

        } catch(NumberFormatException e) {
            System.out.println("Invalid input. \n");

            return null;
        }

    }

    // deletes records from the database and returns a string message with the number of rows deleted
    static String recordDeleter(int id, String table, Connection conn){

        String sql = "DELETE FROM " + table + " WHERE emplid = " + id;

        try (Statement stmt = conn.createStatement()) {
            int updated = stmt.executeUpdate(sql);

            if (updated >= 1){
                String output = updated + " record(s) deleted.";
                return output;
            }
            else{
                return "No records found matching that ID.";
            }
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    // prints a record to the console display given a table with a primary key
    // maybe it should return the result set instead of being a printer?
    static void recordPrinter(int id, String table, String pkColumnName, Connection conn){

        String sql = "SELECT * FROM " + table + " WHERE " + pkColumnName + " = " + id;

        try (Statement stmt = conn.createStatement()) {

            ResultSet resultSet = stmt.executeQuery(sql);

            ResultSetMetaData metaData = resultSet.getMetaData();

            int columnsNumber = metaData.getColumnCount();

            while (resultSet.next()) {
                for (int i = 1; i <= columnsNumber; i++) {

                    String columnValue = resultSet.getString(i);

                    System.out.println(metaData.getColumnName(i) + ": " + columnValue);
                }
                System.out.println("\n");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // updates a record with new values input for each column
    static void recordUpdater(int id, String table, Connection conn){
        // get the number of columns, loop through them while assigning new values
    }

    // creates a new record on the given table
    static void recordCreator(String table, Connection conn){

    }

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
                startEmployeeMenu(conn);
                mainDisplay();
            }
            else if (input.equals("2")) {
                startApplicantInformationMenu(conn);
                mainDisplay();
            }
            else if (input.equals("3")){
                startEvaluationsMenu(conn);
                mainDisplay();
            }
            else if (input.equals("4")){
                // need a condition here to enter a valid employee id before self service can begin
                System.out.println("Welcome to the employee self service menu. Please enter the employee ID: ");
                startSelfServiceMenu(conn);
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

    static void startEmployeeMenu(Connection conn){
        String input;
        Scanner keyboard = new Scanner(System.in);
        boolean running = true;

        while (running){
            employeeDisplay();

            input = keyboard.nextLine();

            if(input.equals("1")){
                System.out.print("Please enter the ID of the employee: ");

                Integer id = integerParser();

                if (id != null){
                    recordPrinter(id, "employee", "emplid", conn);
                }
            }
            else if (input.equals("2")){
                startJobsMenu(conn);
            }
            else if (input.equals("3")){
                startBenefitsMenu(conn);
            }
            else if (input.equals("4")){
                startPayrollMenu(conn);
            }
            else if (input.equals("5")){
                startCertificationsMenu(conn);
            }
            else if (input.equals("6")){
                startQualificationsMenu(conn);
            }
            else if (input.equals("7")){
                startTrainingMenu(conn);
            }
            else if (input.equals("8")){
                startTaxInformationMenu(conn);
            }
            else if (input.equals("9")){

            }
            else if (input.equals("10")){

            }
            else if (input.equals("11")){
                System.out.print("Please enter the ID of the employee to be deleted: ");

                Integer id = integerParser();

                if (id != null){
                    System.out.println(recordDeleter(id, "employee", conn));
                }
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

    static void startJobsMenu(Connection conn){
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

    static void startTaxInformationMenu(Connection conn){
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

    static void startApplicantInformationMenu(Connection conn){
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

    static void startQualificationsMenu(Connection conn){
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

    static void startCertificationsMenu(Connection conn){
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

    static void startTrainingMenu(Connection conn){
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

    static void startPayrollMenu(Connection conn){
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

    static void startBenefitsMenu(Connection conn){
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

    static void startEvaluationsMenu(Connection conn){
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

    static void startSelfServiceMenu(Connection conn){
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
        catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }


    }












}
