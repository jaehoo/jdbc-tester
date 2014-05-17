package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: asanchez
 * Date: 26/03/14
 * Time: 09:25 AM
 *
 * @author <a href="jaehoo@gmail.com">Alberto Sánchez</a>
 *         Contact me by:
 *         <ul><li>Twitter: @jaehoox</li><ul>
 */
public class MSSQLConnectionTest {

    private String username;
    private String connectionUrl;
    private String pass;
    private String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    public static void main(String[] args) {

        MSSQLConnectionTest tester = new MSSQLConnectionTest();
        tester.connect(args);

    }

    private void connect(String... args){

        Scanner keyboard = new Scanner(System.in);

        //get url username
        if (args != null && args.length >0){

            connectionUrl = args[0];


        }else{
            System.out.print("connection url:");
            connectionUrl = keyboard.next();

        }

        //get user/pass

        System.out.print("User:");
        username = keyboard.next();

        System.out.print("Password:");
        pass = keyboard.next();


        pingConnection(connectionUrl,username,pass);
    }


    public void pingConnection(String connectionUrl, String user, String password){

        Connection con = null;

        try {
            // registers the specified driver class into memory
            Class.forName(driverName);
            System.out.println("Driver Loaded Successfully!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            // returns a connection objcet by selecting an appropriate driver
            // for specified connection URL
            con = DriverManager
                    .getConnection(connectionUrl, username, password);

            System.out.println("Connection Successfully!");
            System.out.println("connection url:"+connectionUrl);

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }



}