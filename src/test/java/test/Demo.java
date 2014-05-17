package test;

import java.io.*;
import java.sql.*;
/**
 * Created with IntelliJ IDEA.
 * User: asanchez
 * Date: 16/05/14
 * Time: 06:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class Demo {

    public static void main(String[] args)
    {
        Connection conn = null;

        try
        {
            com.ibm.db2.jcc.DB2DataSource ds =
                    new com.ibm.db2.jcc.DB2DataSource();

            ds.setDriverType(4);
            ds.setServerName("localhost");
            ds.setPortNumber(60000);
            ds.setDatabaseName("SAPXIPDB");
            ds.setUser("ephafsel");
            ds.setPassword("Palacio14");
            ds.setClientProgramName("My application");

            conn = ds.getConnection();

            System.out.println("Connected. Press a key to continue...");
            BufferedReader stdin =
                    new BufferedReader(new InputStreamReader(System.in));
            int ch = stdin.read();
        }
        catch (Exception e)
        {
            if (e.getMessage() != null)
            {
                System.out.println(e.getMessage());
            }
            e.printStackTrace();
        }
        finally
        {
            if (conn != null)
            {
                try
                {
                    conn.close();
                }
                catch (SQLException e) {}
            }
        }
    }
}
