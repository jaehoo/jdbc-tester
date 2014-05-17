package test;

import com.ibm.db2.jcc.DB2Connection;

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
public class DB2ConnectionTest {

    private String username;
    private String connectionUrl;
    private String pass;
    private String driverName = "com.ibm.db2.jcc.DB2Driver";

    public static void main(String[] args) throws Exception{



        DB2ConnectionTest tester = new DB2ConnectionTest();
        //tester.connect(args);
        tester.dummyTest();


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
        //jdbc:db2://localhost:60006/asdb

        try {
            // registers the specified driver class into memory
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            // returns a connection objcet by selecting an appropriate driver
            // for specified connection URL
            java.util.Properties props = new java.util.Properties();
            props.put("user", user);
            props.put("password", password);
            props.put("clientProgramName", "My Program");

            con = DriverManager
                    .getConnection(connectionUrl, props);

            DB2Connection db2Conn = (DB2Connection) con;
            db2Conn.setDB2ClientApplicationInformation("My Program");

            System.out.println("Connection Successfully!");
            System.out.println("connection url:"+connectionUrl);

            db2Conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    private void dummyTest() throws Exception{

//        Class.forName("com.ibm.db2.jcc.DB2Driver");
//        java.util.Properties props = new java.util.Properties();
//        props.put("user", "ephafsel");
//        props.put("password", "Palacio14");
//        props.put("clientProgramName", "My Program");
//        Connection conn = DriverManager.getConnection("jdbc:db2://10.10.52.234:5978/SAPXIPDB", props);

        com.ibm.db2.jcc.DB2SimpleDataSource ds= new com.ibm.db2.jcc.DB2SimpleDataSource();

        ds.setDriverType(4);
        ds.setServerName("10.10.52.234");
        ds.setPortNumber(5978);
        ds.setDatabaseName ("SAPXIPDB");
        ds.setUser("ephafsel");
        ds.setPassword("Palacio14");
        ds.setClientProgramName("MyApplication") ;

        Connection con =ds.getConnection();

    }

}