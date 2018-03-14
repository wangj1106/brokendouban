package com.bingo;

import java.sql.*;


public class TestJdbc {
    public static void  main(String[] args){


    try{
        Class.forName("com.mysql.jdbc.Driver");
        String USER = "bingo@bingodatabase";
        String PASS = "Brokendouban123";
        System.out.println("Connecting to database...");

        String url ="jdbc:mysql://bingodatabase.mysql.database.azure.com:3306/bingodatabase?useSSL=false&requireSSL=false";
        //String url ="jdbc:mysql://{host_name}:3306/bingodatabase.mysql.database.azure.com?useSSL=true&requireSSL=false";

        Connection myDbConn = DriverManager.getConnection(url, USER, PASS);
        System.out.println(myDbConn);

    }catch(Exception e){
        e.printStackTrace();
    }



    }
}
