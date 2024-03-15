package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
    //private   String url="jdbc:mysql://localhost:3306/ArtSpace";
    private   String url="jdbc:mysql://localhost:8111/ArtSpace";

    private String login="root";
    private String pwd="";
    private static DataSource data;

    private Connection con;
    private DataSource(){

        try {
            con= DriverManager.getConnection(url,login,pwd);
            System.out.println("Connexion établie");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public Connection getCon(){
        return con;
    }


    public static DataSource getInstance()
    {
        if (data==null)
            data=new DataSource();
        return data;
    }
    public static Connection getConnection() {
        return null;
    }
}
