package main.java;

import java.sql.*;

public final class PostgreSqlConnect {
    private Connection conn;
    private static PostgreSqlConnect postgreSqlConnect;
    private PostgreSqlConnect() {
        String url= "jdbc:postgresql://localhost:5432/";
        String dbName = "testdb";
        String userName = "postgres";
        String password = "1234";
        try {
            Class.forName("org.postgresql.Driver");
            this.conn = DriverManager.getConnection(url+dbName,userName,password);
        }
        catch (Exception sqle) {
            System.out.println("Database Connection Creation Failed : " + sqle.getMessage());
        }
    }

    public static PostgreSqlConnect getDbCon() {
        if ( postgreSqlConnect == null ) {
            synchronized (PostgreSqlConnect.class){
                if ( postgreSqlConnect == null ){
                    postgreSqlConnect = new PostgreSqlConnect();
                }
            }
        }
        return postgreSqlConnect;
    }

    public Connection getConn() {
        return conn;
    }
}
