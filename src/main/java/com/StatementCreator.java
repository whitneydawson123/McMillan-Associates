// McMillanHRIS Java console application
// SDET Group 3
// Programmer and designer: Andrew Hodson

package com;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.*;
import java.util.Scanner;

// A helper class for creating and executing SQL statements
@SuppressWarnings("SqlDialectInspection")
public final class StatementCreator {

    // attempts to parse input as an Integer, and returns an empty string if it cannot
    static String integerValidator(){

        Scanner keyboard = new Scanner(System.in);

        try {
            String input = keyboard.nextLine();
            if (input.isBlank()) return input;
            int parsedInput = Integer.parseInt(input);

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
            String input = keyboard.nextLine();
            if (input.isBlank()) return input;
            double parsedInput = Double.parseDouble(input);

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
            String input = keyboard.nextLine();
            if (input.isBlank()) return input;
            Date parsedInput = Date.valueOf(input);

            return parsedInput.toString();

        } catch(IllegalArgumentException e) {
            System.out.println("Invalid Date input. \n");

            return "";
        }
    }

    // attempts to parse input as a boolean, and returns false for any invalid inputs
    static String boolValidator(){
        Scanner keyboard = new Scanner(System.in);

        try {
            String input = keyboard.nextLine();
            if (input.isBlank()) return input;
            Boolean parsedInput = Boolean.parseBoolean(input);

            return parsedInput.toString();

        } catch(NumberFormatException e) {
            System.out.println("Invalid Boolean input. \n");

            return "false";
        }
    }

    // returns the number of records from a given ResultSet
    static int getRowCount(ResultSet resultSet){
        int rowCount = 0;
        try {
            while (resultSet.next()){
                rowCount++;
            }
            resultSet.close();
        } catch (SQLException e) {
        System.out.println(e.getMessage());
        }
        return rowCount;
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
        if (lines[0].isBlank()){
            System.out.println("No record found with that ID");
        }
        else{
            for (int i = 0; i < lines.length; i++) {
                System.out.println(lines[i]);
            }
        }

        System.out.println("\n");
    }


    // adds each queried column as a string in the array in the format "column name: column value"
    static String[] createReadableColumns(String id, String table, String identifyingColumn, Connection conn){

        String sql = "SELECT * FROM " + table + " WHERE " + identifyingColumn + " = " + id;

        // empty record to return if query fails
        String[] emptyRecord = new String[1];
        emptyRecord[0] = "";

        try (Statement stmt = conn.createStatement()) {

            ResultSet copy = stmt.executeQuery(sql);

            if (!copy.isBeforeFirst()) return emptyRecord;

            int numberOfRows = getRowCount(copy);

            ResultSet resultSet = stmt.executeQuery(sql);

            ResultSetMetaData metaData = resultSet.getMetaData();


            int numberOfColumns = metaData.getColumnCount();

            String[] record = new String[numberOfColumns * numberOfRows];

            int currentRow = 0;

            while (resultSet.next()) {
                for (int i = 1; i <= numberOfColumns; i++) {

                    String columnValue = resultSet.getString(i);

                    record[i-1 + (currentRow * numberOfColumns)] = metaData.getColumnName(i) + ": " + columnValue;

                    // insert a line between records for readability
                    if (i == 1) {
                        record[(currentRow * numberOfColumns)] =
                                "--------------\n" + record[(currentRow * numberOfColumns)];
                    }
                }
                currentRow++;
            }

            // an empty record is returned if there were no results
            if (record[0] == null || record[0].isBlank()) return emptyRecord;
            return record;
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return emptyRecord;
        }
    }

    // an alternative method to createReadableColumns where LIKE is used instead of = in the SELECT statement
    static String[] createReadableColumnsLike(String comparison, String table, String identifyingColumn, Connection conn){

        String sql = "SELECT * FROM " + table + " WHERE " + identifyingColumn + " LIKE \"" + comparison + "%\"";

        // empty record to return if query fails
        String[] emptyRecord = new String[1];
        emptyRecord[0] = "";

        try (Statement stmt = conn.createStatement()) {

            ResultSet copy = stmt.executeQuery(sql);

            if (!copy.isBeforeFirst()) return emptyRecord;

            int numberOfRows = getRowCount(copy);

            ResultSet resultSet = stmt.executeQuery(sql);

            ResultSetMetaData metaData = resultSet.getMetaData();


            int numberOfColumns = metaData.getColumnCount();

            String[] record = new String[numberOfColumns * numberOfRows];

            int currentRow = 0;

            while (resultSet.next()) {
                for (int i = 1; i <= numberOfColumns; i++) {

                    String columnValue = resultSet.getString(i);

                    record[i-1 + (currentRow * numberOfColumns)] = metaData.getColumnName(i) + ": " + columnValue;

                    // insert a line between records for readability
                    if (i == 1) {
                        record[(currentRow * numberOfColumns)] =
                                "--------------\n" + record[(currentRow * numberOfColumns)];
                    }
                }
                currentRow++;
            }

            // an empty record is returned if there were no results
            if (record[0] == null || record[0].isBlank()) return emptyRecord;
            return record;
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return emptyRecord;
        }
    }


    // returns a valid String to be added to the UPDATE statement
    static String validateUpdateLine(int columnNumber, ResultSetMetaData resultSet){
        try{

            // get the maximum number of characters that can be input for this column
            int precision = resultSet.getPrecision(columnNumber);
            String columnTypeName = resultSet.getColumnTypeName(columnNumber);

            switch (columnTypeName) {
                case "INT": case "INT UNSIGNED": {
                    System.out.println("It must be an integer value less than " + precision + " characters long");

                    String input = integerValidator();

                    if (input.equals("")) return "";

                    if (input.length() < precision) {
                        return resultSet.getColumnName(columnNumber) + " = " + input + ", ";
                    }

                    return "";
                }
                case "DOUBLE": {
                    System.out.println("It must be a double value less than " + precision + " characters long");

                    String input = doubleValidator();

                    if (input.equals("")) return "";

                    if (input.length() < precision) {
                        return resultSet.getColumnName(columnNumber) + " = " + input + ", ";
                    }

                    return "";
                }
                case "BIT": {
                    System.out.println("It must be 'true' or 'false'");

                    String input = boolValidator();
                    return resultSet.getColumnName(columnNumber) + " = " + input + ", ";

                }
                case "DATE": {
                    System.out.println("It must be a date in the format YYYY-MM-DD");

                    String input = dateValidator();

                    if (input.isBlank()) return "";

                    return resultSet.getColumnName(columnNumber) + " = " + "'" + input + "', ";
                }
                case "VARCHAR": {
                    System.out.println("It must be less than " + precision + " characters long");

                    Scanner keyboard = new Scanner(System.in);

                    String input = keyboard.nextLine();

                    if (input.equals("")) return "";

                    // cut off excess characters if input is too long
                    if (input.length() >= precision) {
                        input = input.substring(0, precision - 1);
                    }
                    return resultSet.getColumnName(columnNumber) + " = " +  "'" + input + "', ";
                }
                default:
                    return ""; // return an empty string to keep the value the same
            }


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

                    String input = validateUpdateLine(i, metaData);

                    if (!input.isBlank()) valuesEntered++;

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

    // updates a record, prompting new values input for each column
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

    // updates a single column of the employee table, returning the number of records altered
    static void employeeColumnUpdater(String id, String columnName, Connection conn){

        String select = "SELECT * FROM  employee WHERE " + columnName + " = " + id;
        StringBuilder update = new StringBuilder("UPDATE employee SET ");
        String where = " WHERE employee_id = " + id;

        try (Statement stmt = conn.createStatement()) {

            ResultSet resultSet = stmt.executeQuery(select);

            // get the column number of the column being updated.

            ResultSetMetaData metaData = resultSet.getMetaData();

            // get the number of columns to loop through them while assigning new values
            int numberOfColumns = metaData.getColumnCount();

            // records the number of valid values entered by the user
            int valuesEntered = 0;

            // start at 2, because the primary key cannot be changed
            for (int i = 2; i <= numberOfColumns; i++) {
                    if (columnName.equals(metaData.getColumnName(i))){
                        update.append(validateUpdateLine(i, metaData));
                        valuesEntered++;
                    }
            }

            if (valuesEntered == 0){
                System.out.println("\nNo alterations made");
                return;
            }

            // remove trailing comma and append the WHERE
            if (update.charAt(update.length() - 2) == ',') update.deleteCharAt(update.length() - 2);
            update.append(where);

            System.out.println("\nRecord altered");
            int output = stmt.executeUpdate(update.toString());
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // returns a valid String to be added to the INSERT statement
    static String validateInsertLine(int columnNumber, ResultSetMetaData resultSet){
        try{

            int precision = resultSet.getPrecision(columnNumber);
            String columnTypeName = resultSet.getColumnTypeName(columnNumber);

            switch (columnTypeName) {
                case "INT": case "INT UNSIGNED": {
                    System.out.println("It must be an integer value less than " + precision + " characters long");
                    if (resultSet.isNullable(columnNumber) == ResultSetMetaData.columnNoNulls) {
                        System.out.println("This field is required.");
                    }

                    String input = integerValidator();

                    if (input.isBlank()) return "";

                    if (input.length() < precision) {
                        return input + ", ";
                    }

                    return "";
                }
                case "DOUBLE": {
                    System.out.println("It must be a double value less than " + precision + " characters long");
                    if (resultSet.isNullable(columnNumber) == ResultSetMetaData.columnNoNulls) {
                        System.out.println("This field is required.");
                    }

                    String input = doubleValidator();

                    if (input.equals("")) return "";

                    if (input.length() < precision) {
                        return input + ", ";
                    }

                    break;
                }
                case "BIT": {
                    System.out.println("It must be 'true' or 'false'");
                    if (resultSet.isNullable(columnNumber) == ResultSetMetaData.columnNoNulls) {
                        System.out.println("This field is required.");
                    }

                    String input = boolValidator();
                    return input + ", ";

                }
                case "DATE": {
                    System.out.println("It must be a date in the format YYYY-MM-DD");
                    if (resultSet.isNullable(columnNumber) == ResultSetMetaData.columnNoNulls) {
                        System.out.println("This field is required.");
                    }

                    String input = dateValidator();

                    if (input.isBlank()) return "";

                    return "'" + input + "', ";
                }
                case "VARCHAR": {
                    System.out.println("It must be less than " + precision + " characters long");
                    if (resultSet.isNullable(columnNumber) == ResultSetMetaData.columnNoNulls) {
                        System.out.println("This field is required.");
                    }

                    Scanner keyboard = new Scanner(System.in);

                    String input = keyboard.nextLine();

                    if (input.equals("")) return "";

                    // cut off excess characters if input is too long
                    if (input.length() >= precision) {
                        input = input.substring(0, precision - 1);
                    }
                    return "'" + input + "', ";
                }
                default:
                    return ""; // return an empty string to keep the value the same
            }


        }catch (SQLException e) {
            e.printStackTrace();
        }

        return "";
    }

    // creates an INSERT sql statement from user input, returning the entire query as a String
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
                    System.out.println("Enter a value for " + metaData.getColumnName(i));

                    String input = validateInsertLine(i, metaData);

                    if (input.isBlank() && metaData.isNullable(i) == ResultSetMetaData.columnNoNulls) return "";
                    if (!input.isBlank()) insert.append(metaData.getColumnName(i) + ", ");

                    valuesEntered++;

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

            if (!insert.isBlank() && stmt.executeUpdate(insert) > 0) System.out.println("\nRecord created");
            else System.out.println("\nInvalid input, no new record created");


        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
