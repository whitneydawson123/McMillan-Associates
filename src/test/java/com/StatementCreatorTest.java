package com;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.sql.*;
import java.util.concurrent.locks.StampedLock;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StatementCreatorTest {

    // tests whether the test database can be created and connected to
    @Test
    void canConnectToTestDatabaseAfterItsCreated() {
        try {
            Connection conn = StatementCreator.createTestDatabaseConnection();

            // test passes if connection isn't null
            if (conn != null) {
                System.out.println("Successfully connected to test database");
                conn.close();
                assert(true);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            System.out.println("Failed connecting to database");
            assert(false);
        }
    }

    @Test
    void doesIntegerValidatorReturnEmptyStringWithNonIntegerInput() {

        String notAnInt = "Not an integer";

        InputStream sysInBackup = System.in; // backup of System.in
        ByteArrayInputStream in = new ByteArrayInputStream(notAnInt.getBytes()); // create the test input
        System.setIn(in); // set the test input

        assertEquals("", StatementCreator.integerValidator());

        System.setIn(sysInBackup); // restore System.in using the backup
    }

    @Test
    void doesIntegerValidatorReturnTheIntegerGivenStringWithOnlyIntegerFive() {

        String stringInt = "5";

        InputStream sysInBackup = System.in; // backup of System.in
        ByteArrayInputStream in = new ByteArrayInputStream(stringInt.getBytes()); // create the test input
        System.setIn(in); // set the test input

        assertEquals("5", StatementCreator.integerValidator());

        System.setIn(sysInBackup); // restore System.in using the backup
    }

    @Test
    void doesDoubleValidatorRoundDownToTwoDecimalPlacesAccurately(){
        String stringDouble = "3.155";

        InputStream sysInBackup = System.in; // backup of System.in
        ByteArrayInputStream in = new ByteArrayInputStream(stringDouble.getBytes()); // create the test input
        System.setIn(in); // set the test input

        assertEquals("3.15",
                StatementCreator.doubleValidator(),
                "Passes if the parsed double input 3.155 rounds to 3.15");

        System.setIn(sysInBackup); // restore System.in using the backup
    }

    @Test
    void doesDoubleValidatorRoundUpToTwoDecimalPlacesAccurately(){
        String stringDouble = "3.156";

        InputStream sysInBackup = System.in; // backup of System.in
        ByteArrayInputStream in = new ByteArrayInputStream(stringDouble.getBytes()); // create the test input
        System.setIn(in); // set the test input

        assertEquals("3.16",
                StatementCreator.doubleValidator(),
                "Passes if the parsed double input 3.156 rounds to 3.16");

        System.setIn(sysInBackup); // restore System.in using the backup
    }

    @Test
    void doesGetRowCountReturnAnAccurateNumberOfRows(){
        try(Connection conn = StatementCreator.createTestDatabaseConnection()){

            Statement stmt = conn.createStatement();

            String sql = "SELECT * FROM job WHERE title LIKE \"%Manager\"";

            ResultSet resultSet = stmt.executeQuery(sql);

            assertEquals(3, StatementCreator.getRowCount(resultSet));

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }

    @Test
    void doesValidateUpdateLineReturnRoundedDecimalInProperFormat(){
        try(Connection conn = StatementCreator.createTestDatabaseConnection()){

            Statement stmt = conn.createStatement();

            String select = "SELECT * FROM payroll  WHERE  payid = 1";

            ResultSet resultSet = stmt.executeQuery(select);

            String simulation = "22.213";

            InputStream sysInBackup = System.in; // backup of System.in
            ByteArrayInputStream in = new ByteArrayInputStream(simulation.getBytes()); // create the test input
            System.setIn(in); // set the test input

            String line = StatementCreator.validateUpdateLine(3, resultSet);

            assertEquals("rates = 22.21", line);

            System.setIn(sysInBackup); // restore System.in using the backup

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }

    @Test
    void doesValidateUpdateLineReturnWhenInputIsInvalid(){
        try(Connection conn = StatementCreator.createTestDatabaseConnection()){

            Statement stmt = conn.createStatement();

            String select = "SELECT * FROM payroll  WHERE  payid = 1";

            ResultSet resultSet = stmt.executeQuery(select);

            String simulation = "asdcfghjkl09876543";

            InputStream sysInBackup = System.in; // backup of System.in
            ByteArrayInputStream in = new ByteArrayInputStream(simulation.getBytes()); // create the test input
            System.setIn(in); // set the test input

            String line = StatementCreator.validateUpdateLine(3, resultSet);

            assertEquals("", line);

            System.setIn(sysInBackup); // restore System.in using the backup

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
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
    void canCreateReadableColumnsLikeReturnMultipleRecords(){

        try(Connection conn = StatementCreator.createTestDatabaseConnection()){

            Statement stmt = conn.createStatement();

            String[] lines = StatementCreator.createReadableColumnsLike(
                    "Manager", "job", "title", conn);

            assertEquals("job_id: 4", lines[0]);
            assertEquals("job_id: 17", lines[6]);
            assertEquals("job_id: 18", lines[12]);

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }


    }

    @Test
    void doesCreateUpdateStatementWork(){
        try(Connection conn = StatementCreator.createTestDatabaseConnection()){

            Statement stmt = conn.createStatement();

            String select = "SELECT * FROM employee WHERE  emplid = 1";

            String id = "1";
            String table = "employee";
            String pkColumnName = "emplid";
            ResultSet resultSet = stmt.executeQuery(select);


            String simulation = "asdcfghjkl09876543";

            InputStream sysInBackup = System.in; // backup of System.in
            //ByteArrayInputStream in = new ByteArrayInputStream(simulation.getBytes()); // create the test input
            ByteArrayInputStream in = new ByteArrayInputStream(("1" + System.lineSeparator() + "2").getBytes());
            System.setIn(in); // set the test input

            String line = StatementCreator.createUpdateStatement(id, table, pkColumnName, resultSet);

            assertEquals("", line);

            System.setIn(sysInBackup); // restore System.in using the backup

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }

}