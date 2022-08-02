// McMillanHRIS Java console application
// SDET Group 3
// Programmer and designer: Andrew Hodson

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


    // set up input and output streams for testing
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

    @Test
    void doesIntegerValidatorReturnEmptyStringWithNonIntegerInput() {

        setUpStreams("Not an integer");

        assertEquals("", StatementCreator.integerValidator());

        restoreStreams();
    }

    @Test
    void doesIntegerValidatorReturnTheIntegerGivenStringWithOnlyIntegerFive() {

        setUpStreams("5");

        assertEquals("5", StatementCreator.integerValidator());

        restoreStreams();
    }

    @Test
    void doesDoubleValidatorRoundDownToTwoDecimalPlacesAccurately(){

        setUpStreams("3.155");

        assertEquals("3.15",
                StatementCreator.doubleValidator(),
                "Passes if the parsed double input 3.155 rounds to 3.15");

        restoreStreams();
    }

    @Test
    void doesDoubleValidatorRoundUpToTwoDecimalPlacesAccurately(){

        setUpStreams("3.156");

        assertEquals("3.16",
                StatementCreator.doubleValidator(),
                "Passes if the parsed double input 3.156 rounds to 3.16");

        restoreStreams();
    }

    @Test
    void doesBoolValidatorReturnFalseInput(){

        setUpStreams("False");

        assertEquals("false",
                StatementCreator.boolValidator(),
                "Passes if 'False' string input returns false");

        restoreStreams();

        setUpStreams("false");

        assertEquals("false",
                StatementCreator.boolValidator(),
                "Passes if 'false' string input returns false");

        restoreStreams();
    }

    @Test
    void doesBoolValidatorReturnTrueInput(){

        setUpStreams("True");

        assertEquals("true",
                StatementCreator.boolValidator(),
                "Passes if 'True' string input returns true");

        restoreStreams();

        setUpStreams("true");

        assertEquals("true",
                StatementCreator.boolValidator(),
                "Passes if 'true' string input returns true");

        restoreStreams();
    }

    @Test
    void doesBoolValidatorReturnFalseForBadInput(){

        setUpStreams("1");

        assertEquals("false",
                StatementCreator.boolValidator(),
                "Passes if '1' string input returns false");

        restoreStreams();

        setUpStreams("nonsense");

        assertEquals("false",
                StatementCreator.boolValidator(),
                "Passes if nonsense string input returns false");

        restoreStreams();
    }

    @Test
    void doesDateValidatorReturnProperlyInputDateInProperFormat(){
        setUpStreams("1994-03-11");

        assertEquals("1994-03-11",
                StatementCreator.dateValidator(), "Passes if the date is returned");

        restoreStreams(); // restore System.in using the backup

        setUpStreams("1994-3-11");

        assertEquals("1994-03-11",
                StatementCreator.dateValidator(), "Passes if the date is returned");

        restoreStreams(); // restore System.in using the backup
    }

    @Test
    void doesDateValidatorReturnBlankForBadInput(){
        setUpStreams("nonsense");

        assertEquals("",
                StatementCreator.dateValidator(),
                "Passes an empty string is returned");

        restoreStreams();
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
        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/McMillanHRIS",
                "root", "password");){

            Statement stmt = conn.createStatement();

            String sql = "SELECT * FROM job WHERE title LIKE \"%Manager\"";

            ResultSet resultSet = stmt.executeQuery(sql);

            assertEquals(3, StatementCreator.getRowCount(resultSet),
                    "Accurate number of rows returned to pass");

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }

    @Test
    void doesValidateUpdateLineReturnRoundedDecimalInProperFormat(){
        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/McMillanHRIS",
                "root", "password");){

            Statement stmt = conn.createStatement();

            String select = "SELECT * FROM payroll  WHERE  payroll_id = 1";

            ResultSet resultSet = stmt.executeQuery(select);

            setUpStreams("22.213");

            String line = StatementCreator.validateUpdateLine(3, resultSet.getMetaData());

            assertEquals("rates = 22.21, ", line,
                    "Returns the rounded double in the proper update format to pass");

            restoreStreams();

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }

    @Test
    void doesValidateUpdateLineReturnWhenInputIsInvalid(){
        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/McMillanHRIS",
                "root", "password");){

            Statement stmt = conn.createStatement();

            String select = "SELECT * FROM payroll  WHERE  payroll_id = 1";

            ResultSet resultSet = stmt.executeQuery(select);

            setUpStreams("asdcfghjkl09876543");

            String line = StatementCreator.validateInsertLine(3, resultSet.getMetaData());

            assertEquals("", line, "returns nothing to pass");

            restoreStreams();

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }

    @Test
    void doesValidateInsertLineReturnRoundedDecimalInProperFormat(){
        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/McMillanHRIS",
                "root", "password");){

            Statement stmt = conn.createStatement();

            String select = "SELECT * FROM payroll  WHERE  payroll_id = 1";

            ResultSet resultSet = stmt.executeQuery(select);

            setUpStreams("22.213");

            String line = StatementCreator.validateInsertLine(3, resultSet.getMetaData());

            assertEquals("22.21, ", line,
                    "Returns the rounded double in the proper insert format to pass");

            restoreStreams();

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }

    @Test
    void doesValidateInsertLineReturnWhenInputIsInvalid(){
        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/McMillanHRIS",
                "root", "password");){

            Statement stmt = conn.createStatement();

            String select = "SELECT * FROM payroll  WHERE  payroll_id = 1";

            ResultSet resultSet = stmt.executeQuery(select);

            setUpStreams("asdcfghjkl09876543");

            String line = StatementCreator.validateUpdateLine(3, resultSet.getMetaData());

            assertEquals("", line);

            restoreStreams();

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }

    @Test
    void canCreateReadableColumnsLikeReturnMultipleRecords(){

        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/McMillanHRIS",
                "root", "password");){

            String[] lines = StatementCreator.createReadableColumnsLike(
                    "Manager", "job", "title", conn);

            assertEquals("department_id: 3", lines[1]);
            assertEquals("department_id: 1", lines[7]);
            assertEquals("department_id: 2", lines[13]);

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }

}