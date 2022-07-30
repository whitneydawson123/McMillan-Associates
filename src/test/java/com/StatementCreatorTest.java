package com;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StatementCreatorTest {

    // allows test methods to access output and error streams
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    // backups of the original input and output streams to restore
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    private final InputStream originalIn = System.in;


    // helper method to set up input and output streams for testing
    void setUpStreams(String input) {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));

        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes()); // create the test input
        System.setIn(in); // set the test input
    }


    // restores the original input and output streams after testing
    void restoreStreams() {
        System.out.flush();
        System.setOut(originalOut);
        System.setErr(originalErr);
        System.setIn(originalIn);
    }

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
    void doesBoolValidatorReturnFalseInput(){
        String upperFalse = "False";
        String lowerFalse = "false";

        InputStream sysInBackup = System.in; // backup of System.in
        ByteArrayInputStream in = new ByteArrayInputStream(upperFalse.getBytes()); // create the test input
        System.setIn(in); // set the test input

        assertEquals("false",
                StatementCreator.boolValidator(),
                "Passes if 'False' string input returns false");

        System.setIn(sysInBackup); // restore System.in using the backup

        in = new ByteArrayInputStream(lowerFalse.getBytes()); // create the test input
        System.setIn(in); // set the test input

        assertEquals("false",
                StatementCreator.boolValidator(),
                "Passes if 'false' string input returns false");

        System.setIn(sysInBackup); // restore System.in using the backup
    }

    @Test
    void doesBoolValidatorReturnTrueInput(){
        String upperTrue = "True";
        String lowerTrue = "true";

        InputStream sysInBackup = System.in; // backup of System.in
        ByteArrayInputStream in = new ByteArrayInputStream(upperTrue.getBytes()); // create the test input
        System.setIn(in); // set the test input

        assertEquals("true",
                StatementCreator.boolValidator(),
                "Passes if 'True' string input returns true");

        System.setIn(sysInBackup); // restore System.in using the backup

        in = new ByteArrayInputStream(lowerTrue.getBytes()); // create the test input
        System.setIn(in); // set the test input

        assertEquals("true",
                StatementCreator.boolValidator(),
                "Passes if 'true' string input returns true");

        System.setIn(sysInBackup); // restore System.in using the backup
    }

    @Test
    void doesBoolValidatorReturnFalseForBadInput(){
        String integer = "1";
        String nonsense = "nonsense";

        InputStream sysInBackup = System.in; // backup of System.in
        ByteArrayInputStream in = new ByteArrayInputStream(integer.getBytes()); // create the test input
        System.setIn(in); // set the test input

        assertEquals("false",
                StatementCreator.boolValidator(),
                "Passes if '1' string input returns false");

        System.setIn(sysInBackup); // restore System.in using the backup

        in = new ByteArrayInputStream(nonsense.getBytes()); // create the test input
        System.setIn(in); // set the test input

        assertEquals("false",
                StatementCreator.boolValidator(),
                "Passes if nonsense string input returns false");

        System.setIn(sysInBackup); // restore System.in using the backup
    }

    @Test
    void doesDateValidatorReturnProperlyInputDateInProperFormat(){
        String validDate = "1994-03-11";
        String validDateOmission = "1994-3-11";

        InputStream sysInBackup = System.in; // backup of System.in
        ByteArrayInputStream in = new ByteArrayInputStream(validDate.getBytes()); // create the test input
        System.setIn(in); // set the test input

        assertEquals("1994-03-11",
                StatementCreator.dateValidator(), "Passes if the date is returned");

        System.setIn(sysInBackup); // restore System.in using the backup

        in = new ByteArrayInputStream(validDateOmission.getBytes()); // create the test input
        System.setIn(in); // set the test input

        assertEquals("1994-03-11",
                StatementCreator.dateValidator(), "Passes if the date is returned");

        System.setIn(sysInBackup); // restore System.in using the backup
    }

    @Test
    void doesDateValidatorReturnBlankForBadInput(){
        String nonsense = "nonsense";

        InputStream sysInBackup = System.in; // backup of System.in
        ByteArrayInputStream in = new ByteArrayInputStream(nonsense.getBytes()); // create the test input
        System.setIn(in); // set the test input


        assertEquals("",
                StatementCreator.dateValidator(),
                "Passes an empty string is returned");

        System.setIn(sysInBackup); // restore System.in using the backup
    }

    @Test
    void doesDateValidatorReturnBlankForBadInputPlus(){
        setUpStreams("nonsense");
        assertEquals("",
                StatementCreator.dateValidator(),
                "Passes an empty string is returned");


        assertEquals("Invalid Date input.",
                outContent.toString().trim().replace("\r",""),
                "Passes if the printed output is accurate");
        restoreStreams();
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

            String line = StatementCreator.validateUpdateLine(3, resultSet.getMetaData());

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

            String line = StatementCreator.validateUpdateLine(3, resultSet.getMetaData());

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