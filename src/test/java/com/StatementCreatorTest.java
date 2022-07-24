package com;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StatementCreatorTest {


    @Test
    void doesIntegerParserReturnNullWithNonIntegerInput() {

        String notAnInt = "Not an integer";

        InputStream sysInBackup = System.in; // backup of System.in
        ByteArrayInputStream in = new ByteArrayInputStream(notAnInt.getBytes()); // create the test input
        System.setIn(in); // set the test input

        assertEquals(null, StatementCreator.integerParser());

        System.setIn(sysInBackup); // restore System.in using the backup
    }

    @Test
    void doesIntegerParserReturnTheIntegerGivenStringWithOnlyIntegerFive() {

        String stringInt = "5";

        InputStream sysInBackup = System.in; // backup of System.in
        ByteArrayInputStream in = new ByteArrayInputStream(stringInt.getBytes()); // create the test input
        System.setIn(in); // set the test input

        assertEquals(5, StatementCreator.integerParser());

        System.setIn(sysInBackup); // restore System.in using the backup
    }


    @Test
    void recordDeleter() {
    }

    @Test
    void recordUpdater() {
    }

    @Test
    void recordCreator() {
    }

    @Test
    void canConnectToTestDatabaseAfterItsCreated() {
        String dbURL = "jdbc:mysql://localhost:3306/McMillanHRIStest";
        String username = "root";
        String password = "password";
        try {
            StatementCreator.sqlRunner();

            Connection conn = DriverManager.getConnection(dbURL, username, password);

            // test passes if connection isn't null
            if (conn != null) {
                System.out.println("Successfully connected to test database");
                assert(true);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            System.out.println("Failed connecting to database");
            assert(false);
        }
    }
}