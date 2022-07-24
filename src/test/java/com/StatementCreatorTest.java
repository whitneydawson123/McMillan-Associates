package com;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.sql.*;

class StatementCreatorTest {

    // runs scripts to create a fresh test database
    void sqlRunner() throws ClassNotFoundException, SQLException, FileNotFoundException {

        String schemaFilePath = "C:/Scripts/mcmillanhris_schema.sql";
        String dataFilePath = "C:/Scripts/mcmillanhris_data.sql";

        String dbURL = "jdbc:mysql://localhost:3306/McMillanHRIS";
        String username = "root";
        String password = "password";
        try{
            Connection conn = DriverManager.getConnection(dbURL, username, password);

            if (conn != null) {
                ScriptRunner runner = new ScriptRunner(conn);
                runner.runScript(new BufferedReader(new FileReader(schemaFilePath)));
                runner.runScript(new BufferedReader(new FileReader(dataFilePath)));
                System.out.println("Test database successfully created.");
            }
            conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


}