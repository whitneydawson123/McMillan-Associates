package com;

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
    public static void main(String[] args) {


        String input;
        Scanner keyboard = new Scanner(System.in);
        boolean running = true;

        while (running){
            mainDisplay();

            input = keyboard.nextLine();

            if (input.equals("7")){
                System.out.println("Goodbye.");
                running = false;
            }
            else{
                System.out.println("Invalid input. \n");
            }

        }


    }
}
