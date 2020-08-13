package nix.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DbConnection extends Configs{

    Connection dbConnection;
    private final String url = "jdbc:postgresql://" + dbHost + ":" + dbPort+ "/" + dbName;
    private final String user = dbUser;
    private final String password = dbPass;
    public Connection getDbConnection(){
        try{
            dbConnection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return dbConnection;
    }


    public ResultSet getLocation(){
        String name = "gdansk";
        ResultSet resSet = null;

        String select = "SELECT * FROM " + Const.LOCATION_TABLE;


        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            resSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resSet;
    }



    public ResultSet getRoute(){
        ResultSet resSet = null;

        String select = "SELECT * FROM " + Const.ROUTE_TABLE;
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);

            resSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resSet;
    }

//    public ResultSet getAmountOfLocations2(){
//        ResultSet resSet = null;
//
//        String select = "SELECT COUNT (*) FROM" + Const.LOCATION_TABLE;
//
//        try {
//            PreparedStatement prSt = getDbConnection().prepareStatement(select);
//            resSet = prSt.executeQuery();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return resSet;
//    }



    public ResultSet getAmountOfLocations(){
        ResultSet resSet = null;

        String select = "SELECT * FROM " + Const.LOCATION_TABLE
                + " WHERE "+Const.LOCATION_ID +" = "
                +"( SELECT MAX ("+Const.LOCATION_ID+") FROM  "+Const.LOCATION_TABLE +")";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            resSet = prSt.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resSet;
    }

    public ResultSet getProblems(){
        ResultSet resSet = null;

        String select = "SELECT * FROM " + Const.PROBLEM_TABLE;
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);

            resSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resSet;
    }

    public void setLocation( List<String> cities){
        for(int i=1; i<cities.size()+1; i++){
            String insert = "INSERT INTO " + Const.LOCATION_TABLE + "(" +Const.LOCATION_ID+","+
                    Const.LOCATION_NAME  + ")" +
                    "VALUES(?,?)";
            try {
                PreparedStatement prSt = getDbConnection().prepareStatement(insert);
                prSt.setInt(1, i);
                prSt.setString(2, cities.get(i-1));
                prSt.executeUpdate();
            } catch (SQLException e ) {
                System.out.println("Город уже существует");
            }

        }
    }

    public void setRoute( int [][] values){
        int count = 1;
        for(int i=0; i<values.length; i++){
            for(int j=0; j<values.length; j++){
                if(values[i][j] == 0) continue;

                    String insert = "INSERT INTO " + Const.ROUTE_TABLE + "("
                            + Const.ROUTE_ID+","
                            + Const.ROUTE_FROM_ID+","
                            + Const.ROUTE_TO_ID+","
                            + Const.ROUTE_COST+")"
                            +"VALUES(?,?,?,?)";
                    try {
                        PreparedStatement prSt = getDbConnection().prepareStatement(insert);
                        prSt.setInt(1, count);
                        prSt.setInt(2, i+1);
                        prSt.setInt(3, j+1);
                        prSt.setInt(4, values[i][j]);
                        prSt.executeUpdate();
                    } catch (SQLException e) {
                        System.out.println("Путь уже существует");
                    }
                    count++;

            }
        }
    }

    public void setProblem( int count, int start, int end){
            String insert = "INSERT INTO " + Const.PROBLEM_TABLE + "("
                    + Const.PROBLEM_ID+","
                    + Const.PROBLEM_FROM_ID+","
                    + Const.PROBLEM_TO_ID  + ")" +
                    "VALUES(?,?,?)";
            try {
                PreparedStatement prSt = getDbConnection().prepareStatement(insert);
                prSt.setInt(1, count);
                prSt.setInt(2, start);
                prSt.setInt(3, end);
                prSt.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Проблема уже существует");
            }
    }

    public void setSolution(Map<Integer, Integer> map){
        for(Map.Entry<Integer, Integer> item: map.entrySet()){
            String insert = "INSERT INTO " + Const.SOLUTION_TABLE + "("
                    + Const.SOLUTION_PROBLEM_ID+","
                    + Const.SOLUTION_COST + ")" +
                    "VALUES(?,?)";
            try {
                PreparedStatement prSt = getDbConnection().prepareStatement(insert);
                prSt.setInt(1, item.getKey());
                prSt.setInt(2, item.getValue());
                prSt.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Решение уже существует");
            }
        }
    }






}
