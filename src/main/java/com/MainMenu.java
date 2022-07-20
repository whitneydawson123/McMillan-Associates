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
                "3. Payroll information\n" +
                "4. Benefits\n" +
                "5. Evaluations\n" +
                "6. Employee self service\n" +
                "7. Exit system\n");
    }

    static void employeeDisplay() {
        System.out.println("This is the employee information menu. Enter the corresponding number to select an option.\n\n" +
                "1. List all employees\n" +
                "2. Filter employees by name\n" +
                "3. Filter employees by ID\n" +
                "4. Manager information\n" +
                "5. Certifications menu\n" +
                "6. Qualifications menu\n" +
                "7. Training menu\n" +
                "8. Tax information menu\n" +
                "9. Return to main menu\n");
    }

    static void startEmployeeMenu(){
        String input;
        Scanner keyboard = new Scanner(System.in);
        boolean running = true;

        while (running){
            employeeDisplay();

            input = keyboard.nextLine();

            if (input.equals("9")){
                running = false;
            }
            else{
                System.out.println("Invalid input. \n");
            }

        }
    }

    static void applicationInformationDisplay() {
        System.out.println();
    }

    static void startApplicantInformationMenu(){
        String input;
        Scanner keyboard = new Scanner(System.in);
        boolean running = true;

        while (running){
            employeeDisplay();

            input = keyboard.nextLine();

            if (input.equals("9")){
                running = false;
            }
            else{
                System.out.println("Invalid input. \n");
            }

        }
    }

    static void qualificationsDisplay() {
        System.out.println();
    }

    static void startQualificationsMenu(){
        String input;
        Scanner keyboard = new Scanner(System.in);
        boolean running = true;

        while (running){
            employeeDisplay();

            input = keyboard.nextLine();

            if (input.equals("9")){
                running = false;
            }
            else{
                System.out.println("Invalid input. \n");
            }

        }
    }

    static void certificationsDisplay() {
        System.out.println();
    }

    static void startCertificationsMenu(){
        String input;
        Scanner keyboard = new Scanner(System.in);
        boolean running = true;

        while (running){
            employeeDisplay();

            input = keyboard.nextLine();

            if (input.equals("9")){
                running = false;
            }
            else{
                System.out.println("Invalid input. \n");
            }

        }
    }

    static void trainingDisplay() {
        System.out.println();
    }

    static void startTrainingMenu(){
        String input;
        Scanner keyboard = new Scanner(System.in);
        boolean running = true;

        while (running){
            employeeDisplay();

            input = keyboard.nextLine();

            if (input.equals("9")){
                running = false;
            }
            else{
                System.out.println("Invalid input. \n");
            }

        }
    }

    static void payrollDisplay() {
        System.out.println();
    }

    static void startPayrollMenu(){
        String input;
        Scanner keyboard = new Scanner(System.in);
        boolean running = true;

        while (running){
            employeeDisplay();

            input = keyboard.nextLine();

            if (input.equals("9")){
                running = false;
            }
            else{
                System.out.println("Invalid input. \n");
            }

        }
    }

    static void benefitsDisplay() {
        System.out.println();
    }

    static void startBenefitsMenu(){
        String input;
        Scanner keyboard = new Scanner(System.in);
        boolean running = true;

        while (running){
            employeeDisplay();

            input = keyboard.nextLine();

            if (input.equals("9")){
                running = false;
            }
            else{
                System.out.println("Invalid input. \n");
            }

        }
    }

    static void evaluationsDisplay() {
        System.out.println();
    }

    static void startEvaluationsMenu(){
        String input;
        Scanner keyboard = new Scanner(System.in);
        boolean running = true;

        while (running){
            employeeDisplay();

            input = keyboard.nextLine();

            if (input.equals("9")){
                running = false;
            }
            else{
                System.out.println("Invalid input. \n");
            }

        }
    }

    static void selfServiceDisplay() {
        System.out.println();
    }

    static void startSelfServiceMenu(){
        String input;
        Scanner keyboard = new Scanner(System.in);
        boolean running = true;

        while (running){
            employeeDisplay();

            input = keyboard.nextLine();

            if (input.equals("9")){
                running = false;
            }
            else{
                System.out.println("Invalid input. \n");
            }

        }
    }

    static void startMainMenu(Connection conn){
        String input;
        Scanner keyboard = new Scanner(System.in);
        boolean running = true;

        while (running){
            mainDisplay();

            input = keyboard.nextLine();

            if (input.equals("1")){
                startEmployeeMenu();
            }
            else if (input.equals("2")){
                startApplicantInformationMenu();
            }
            else if (input.equals("3")){
                startPayrollMenu();
            }
            else if (input.equals("4")){
                startBenefitsMenu();
            }
            else if (input.equals("5")){
                startEvaluationsMenu();
            }
            else if (input.equals("6")){
                startSelfServiceMenu();
            }
            else if (input.equals("7")){
                System.out.println("Goodbye.");
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
