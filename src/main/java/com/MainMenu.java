package com;

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
    public static void main(String[] args) {
        mainDisplay();
    }
}
