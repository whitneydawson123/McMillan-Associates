package com;

import java.sql.Connection;

public class Training {

    int trainId;
    int emplId;

    public String getTrainingType() {
        return trainingType;
    }

    public void setTrainingType(String trainingType) {
        this.trainingType = trainingType;
    }

    String trainingType;
    String dateCompleted;

    public int getEmplId() {
        return emplId;
    }

    public void setEmplId(int emplId) {
        this.emplId = emplId;
    }



    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    // for pulling existing Training records from MySQL
    public Training(int emplid, Connection conn){
        // use the connection to pull the emplid with it's associated record from the database
        // once the record the is pulled, we then set all the fiels equal to their values from the database

        // try the id first to determine validity

        // try catch statement here

        // assuming the id exists

        // conn. whatever method to make an SQL statement then use that value for the setter
        // setTrainId(conn.SQLmethod("sql select statement"));
    }

    // for creating new Training records in MySQL
    public Training(Connection conn){

    }

    public void updateTrainingType(String newName, Connection conn){
        // some connection method to ALTER the training table with the training type value
        setTrainingType(newName);
    }
}
