package com;

import java.sql.*;
import java.util.Scanner;

// A helper class for creating SQL statements as strings
public final class StatementCreator {

    // attempts to parse input as an integer, and returns null if it cannot
    static Integer integerParser(){

        Scanner keyboard = new Scanner(System.in);

        try {
            int idInput = Integer.parseInt(keyboard.nextLine());

            return idInput;

        } catch(NumberFormatException e) {
            System.out.println("Invalid integer input. \n");

            return null;
        }

    }

    // deletes records from the database and returns a string message with the number of rows deleted
    static String recordDeleter(int id, String table,  String pkColumnName, Connection conn){

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


    // adds each read column as a string in the array
    static String[] columnsToStrings(int id, String table, String pkColumnName, Connection conn){

        String sql = "SELECT * FROM " + table + " WHERE " + pkColumnName + " = " + id;

        try (Statement stmt = conn.createStatement()) {

            ResultSet resultSet = stmt.executeQuery(sql);

            ResultSetMetaData metaData = resultSet.getMetaData();

            int numberOfColumns = metaData.getColumnCount();

            String[] record = new String[numberOfColumns];

            while (resultSet.next()) {
                for (int i = 1; i <= numberOfColumns; i++) {

                    String columnValue = resultSet.getString(i);

                    //System.out.println(metaData.getColumnName(i) + ": " + columnValue);
                    record[i-1] = metaData.getColumnName(i) + ": " + columnValue;
                }
                //System.out.println("\n");
            }

            return record;

        }catch (SQLException e) {
            e.printStackTrace();
            e.getMessage();
            return null;
        }
    }

    // updates a record with new values input for each column
    static void recordUpdater(int id, String table, String pkColumnName, Connection conn){
        // get the number of columns, loop through them while assigning new values

        String select = "SELECT * FROM " + table + " WHERE " + pkColumnName + " = " + id;

        StringBuilder update = new StringBuilder("UPDATE " + table + " SET ");
        String where = " WHERE " + pkColumnName + " = " + id;


        try (Statement stmt = conn.createStatement()) {

            String input;
            Scanner keyboard = new Scanner(System.in);

            // first, make a select statement to obtain the necessary column metadata
            ResultSet resultSet = stmt.executeQuery(select);
            ResultSetMetaData metaData = resultSet.getMetaData();

            int numberOfColumns = metaData.getColumnCount();


            // start at 2, because the primary key cannot be changed
            while (resultSet.next()) {
                for (int i = 2; i <= numberOfColumns; i++) {

                    String columnValue = resultSet.getString(i);

                    System.out.println("Current: " + metaData.getColumnName(i) + ": " + columnValue);
                    System.out.println("Enter a new value for " + metaData.getColumnName(i) +
                            ", or enter nothing to keep it the same");

                    input = keyboard.nextLine();
                    if (!input.equals("")){
                        // if the input isn't blank, append it to the update statement
                        String newValue = metaData.getColumnName(i) + " = " + input + ", ";
                        update.append(newValue);
                    }
                }

                // remove trailing comma and append the WHERE
                update.deleteCharAt(update.length() - 1);
                update.append(where);

                // execute the constructed update statement
                stmt.executeUpdate(update.toString());
                System.out.println("\n");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // creates a new record on the given table
    static void recordCreator(String table, Connection conn){
        String sql = "INSERT INTO " + table;
    }


}
