package com;

import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.*;
import java.util.Scanner;

// A helper class for creating SQL statements as strings
public final class StatementCreator {

    // attempts to parse input as an Integer, and returns an empty string if it cannot
    static String integerValidator(){

        Scanner keyboard = new Scanner(System.in);

        try {
            int parsedInput = Integer.parseInt(keyboard.nextLine());

            return parsedInput + "";

        } catch(NumberFormatException e) {
            System.out.println("Invalid integer input. \n");

            return "";
        }

    }

    // attempts to parse input as a Double rounding to two decimal places before returning,
    // and returns an empty string if it cannot
    static String doubleValidator(){
        Scanner keyboard = new Scanner(System.in);

        try {
            double parsedInput = Double.parseDouble(keyboard.nextLine());

            BigDecimal big = new BigDecimal(parsedInput).setScale(2, RoundingMode.HALF_UP);
            double roundedDouble = big.doubleValue();

            return roundedDouble + "";

        } catch(NumberFormatException e) {
            System.out.println("Invalid Double input. \n");

            return "";
        }
    }

    // attempts to parse input as a Date in the yyyy-[m]m-[d]d format, and returns an empty string if it cannot
    static String dateValidator(){
        Scanner keyboard = new Scanner(System.in);

        try {
            Date parsedInput = Date.valueOf(keyboard.nextLine());

            return parsedInput.toString();

        } catch(NumberFormatException e) {
            System.out.println("Invalid Date input. \n");

            return "";
        }
    }

    // attempts to parse input as a boolean, and returns an empty string if it cannot
    static String boolValidator(){
        Scanner keyboard = new Scanner(System.in);

        try {
            Boolean parsedInput = Boolean.parseBoolean(keyboard.nextLine());

            return parsedInput.toString();

        } catch(NumberFormatException e) {
            System.out.println("Invalid Boolean input. \n");

            return "";
        }
    }

    // deletes records from the database and returns a string message with the number of rows deleted
    static String recordDeleter(String id, String table,  String pkColumnName, Connection conn){

        String sql = "DELETE FROM " + table + " WHERE " + pkColumnName + " = " + id;

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

    // prints the record in the format "column name: column value"
    static void recordPrinter(String[] lines){

        System.out.println("\n");

        // check if the primary key exists first
        if (lines[0] == null){
            System.out.println("No record found with that ID");
        }
        else{
            for (int i = 0; i < lines.length; i++) {
                System.out.println(lines[i]);
            }
        }

        System.out.println("\n");
    }


    // adds each read column as a string in the array in the format "column name: column value"
    static String[] createReadableColumns(String id, String table, String pkColumnName, Connection conn){

        String sql = "SELECT * FROM " + table + " WHERE " + pkColumnName + " = " + id;

        try (Statement stmt = conn.createStatement()) {

            ResultSet resultSet = stmt.executeQuery(sql);

            ResultSetMetaData metaData = resultSet.getMetaData();

            int numberOfColumns = metaData.getColumnCount();

            String[] record = new String[numberOfColumns];

            while (resultSet.next()) {
                for (int i = 1; i <= numberOfColumns; i++) {

                    String columnValue = resultSet.getString(i);

                    record[i-1] = metaData.getColumnName(i) + ": " + columnValue;
                }
            }

            return record;

        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            String[] emptyRecord = new String[1];
            emptyRecord[0] = "";
            return emptyRecord;
        }
    }


    // returns a valid String to be added to the UPDATE statement
    static String validateUpdateLine(int columnNumber, ResultSet resultSet){
        try{

            // get the maximum number of characters that can be input for this column
            int precision = resultSet.getMetaData().getPrecision(columnNumber);

            if (resultSet.getMetaData().getColumnTypeName(columnNumber).equals("INT")){
                System.out.println("It must be an integer value less than or equal to " + precision + " characters long");

                String input = integerValidator();

                if (input.equals("")) return "";

                if (input.length() < precision) {
                    return resultSet.getMetaData().getColumnName(columnNumber) + " = " + input + ", ";
                }

            }

            else if (resultSet.getMetaData().getColumnTypeName(columnNumber).equals("DOUBLE")){
                System.out.println("It must be a double value less than " + precision + " characters long");

                String input = doubleValidator();

                if (input.equals("")) return "";

                if (input.length() < precision) {
                    return resultSet.getMetaData().getColumnName(columnNumber) + " = " + input + ", ";
                }

            }

            else if (resultSet.getMetaData().getColumnTypeName(columnNumber).equals("BOOLEAN")){
                System.out.println("It must be 'true' or 'false'");

                String input = boolValidator();

                if (input.equals("")) return "";

                if (input.length() < precision) {
                    return resultSet.getMetaData().getColumnName(columnNumber) + " = " + input + ", ";
                }

            }

            else if (resultSet.getMetaData().getColumnTypeName(columnNumber).equals("DATE")){
                System.out.println("It must be a date in the format YYYY-MM-DD" + precision + " characters long");

                String input = dateValidator();

                if (input.equals("")) return "";

                if (input.length() < precision) {
                    return resultSet.getMetaData().getColumnName(columnNumber) + " = '" + input + "', ";
                }

            }

            else if (resultSet.getMetaData().getColumnTypeName(columnNumber).equals("VARCHAR")){
                System.out.println("It must be less than " + precision + " characters long");

                Scanner keyboard = new Scanner(System.in);

                String input = keyboard.nextLine();

                if (input.equals("")) return "";

                // cut off excess characters if input is too long
                if (input.length() >= precision) {
                    input = input.substring(0, precision - 1);
                }
                return resultSet.getMetaData().getColumnName(columnNumber) + " = '" + input + "', ";
            }

            else return ""; // return an empty string to keep the value the same


        }catch (SQLException e) {
            e.printStackTrace();
        }

        return "";
    }

    // creates an UPDATE sql statement from user input
    static String createUpdateStatement(String id, String table, String pkColumnName, ResultSet resultSet){

        StringBuilder update = new StringBuilder("UPDATE " + table + " SET ");
        String where = " WHERE " + pkColumnName + " = " + id;

        try {

            // first, make a select statement to obtain the necessary column metadata
            // maybe take the metadata as a parameter instead of the resultset
            ResultSetMetaData metaData = resultSet.getMetaData();

            // get the number of columns to loop through them while assigning new values
            int numberOfColumns = metaData.getColumnCount();

            // records the number of valid values entered by the user
            int valuesEntered = 0;


            // start at 2, because the primary key cannot be changed
            while (resultSet.next()) {
                for (int i = 2; i <= numberOfColumns; i++) {

                    String columnValue = resultSet.getString(i);

                    System.out.println("Current: " + metaData.getColumnName(i) + ": " + columnValue);
                    System.out.println("Enter a new value for " + metaData.getColumnName(i) +
                            ", or enter nothing to keep it the same");

                    String input = validateUpdateLine(i, resultSet);

                    if (input != "") valuesEntered++;

                    update.append(input);
                }

                if (valuesEntered == 0) return "";

                // remove trailing comma and append the WHERE
                if (update.charAt(update.length() - 2) == ',') update.deleteCharAt(update.length() - 2);
                update.append(where);

                return update.toString();
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // updates a record with new values input for each column
    static void recordUpdater(String id, String table, String pkColumnName, Connection conn){

        String select = "SELECT * FROM " + table + " WHERE " + pkColumnName + " = " + id;

        try (Statement stmt = conn.createStatement()) {

            ResultSet resultSet = stmt.executeQuery(select);

            String update = createUpdateStatement(id, table, pkColumnName, resultSet);

            if (update != "" && stmt.executeUpdate(update) > 0) System.out.println("\nRecord altered");
            else System.out.println("\nNo alterations made");


        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // returns a valid String to be added to the INSERT statement
    static String validateInsertLine(int columnNumber, ResultSet resultSet){
        try{

            int precision = resultSet.getMetaData().getPrecision(columnNumber);

            if (resultSet.getMetaData().getColumnTypeName(columnNumber).equals("INT")){
                System.out.println("It must be an integer value less than " + precision + " characters long");

                String input = integerValidator();

                if (input.equals("")) return "";

                if (input.length() < precision) {
                    return input + ", ";
                }

            }

            else if (resultSet.getMetaData().getColumnTypeName(columnNumber).equals("DOUBLE")){
                System.out.println("It must be a double value less than " + precision + " characters long");

                String input = doubleValidator();

                if (input.equals("")) return "";

                if (input.length() < precision) {
                    return input + ", ";
                }

            }

            else if (resultSet.getMetaData().getColumnTypeName(columnNumber).equals("BOOLEAN")){
                System.out.println("It must be 'true' or 'false'");

                String input = boolValidator();

                if (input.equals("")) return "";

                if (input.length() < precision) {
                    return input + ", ";
                }

            }

            else if (resultSet.getMetaData().getColumnTypeName(columnNumber).equals("DATE")){
                System.out.println("It must be a date in the format YYYY-MM-DD" + precision + " characters long");

                String input = dateValidator();

                if (input.equals("")) return "";

                if (input.length() < precision) {
                    return "'" + input + "', ";
                }

            }

            else if (resultSet.getMetaData().getColumnTypeName(columnNumber).equals("VARCHAR")){
                System.out.println("It must be less than " + precision + " characters long");

                Scanner keyboard = new Scanner(System.in);

                String input = keyboard.nextLine();

                if (input.equals("")) return "";

                // cut off excess characters if input is too long
                if (input.length() >= precision) {
                    input = input.substring(0, precision - 1);
                }
                return "'" + input + "', ";
            }

            else return ""; // return an empty string to keep the value the same


        }catch (SQLException e) {
            e.printStackTrace();
        }

        return "";
    }

    // creates an INSERT sql statement from user input
    static String createInsertStatement(String table, ResultSet resultSet){

        StringBuilder insert = new StringBuilder("INSERT INTO " + table + " (");
        StringBuilder secondLine = new StringBuilder("VALUES (");

        try {

            // first, make a select statement to obtain the necessary column metadata
            ResultSetMetaData metaData = resultSet.getMetaData();

            // get the number of columns to loop through them while assigning new values
            int numberOfColumns = metaData.getColumnCount();

            // records the number of valid values entered by the user
            int valuesEntered = 0;


            // start at 2, because the primary key cannot be assigned
            while (resultSet.next()) {
                for (int i = 2; i <= numberOfColumns; i++) {

                    insert.append(metaData.getColumnName(i) + ", ");
                    System.out.println("Enter a value for " + metaData.getColumnName(i));

                    String input = validateInsertLine(i, resultSet);

                    if (input != "") valuesEntered++;

                    secondLine.append(input);
                }

                if (valuesEntered == 0) return "";

                // remove trailing comma and append the WHERE
                if (insert.charAt(insert.length() - 2) == ',') insert.deleteCharAt(insert.length() - 2);
                insert.append(") ");
                if (secondLine.charAt(secondLine.length() - 2) == ',') secondLine.deleteCharAt(secondLine.length() - 2);
                secondLine.append(");");

                // connect the two lines to complete the statement
                insert.append(secondLine);

                return insert.toString();
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // inserts a new record into a table
    static void recordInserter(String table, Connection conn){

        String select = "SELECT * FROM " + table;

        try (Statement stmt = conn.createStatement()) {

            ResultSet resultSet = stmt.executeQuery(select);

            String insert = createInsertStatement(table, resultSet);

            if (insert != "" && stmt.executeUpdate(insert) > 0) System.out.println("\nRecord created");
            else System.out.println("\nInvalid input, no new record created");


        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // runs scripts to create a fresh test database connection
    static Connection createTestDatabaseConnection() {

        String schemaFilePath = "C:/Scripts/mcmillanhristest_schema.sql";
        String dataFilePath = "C:/Scripts/mcmillanhristest_data.sql";

        String dbURL = "jdbc:mysql://localhost:3306";
        String username = "root";
        String password = "password";
        try {
            Connection conn = DriverManager.getConnection(dbURL, username, password);

            if (conn != null) {
                ScriptRunner runner = new ScriptRunner(conn);
                runner.runScript(new BufferedReader(new FileReader(schemaFilePath)));
                runner.runScript(new BufferedReader(new FileReader(dataFilePath)));
                System.out.println("Test database successfully created.");
                return conn;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
