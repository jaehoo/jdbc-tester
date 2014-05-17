package test;

/**
 * Created with IntelliJ IDEA.
 * User: asanchez
 * Date: 16/05/14
 * Time: 06:44 PM
 * To change this template use File | Settings | File Templates.
 */
import java.sql.*;

public class EzJava
{
    public static void main(String[] argsx)
    {
        String urlPrefix = "jdbc:db2:";
        String url;
        String user;
        String password;
        String empNo;
        Connection con;
        Statement stmt;
        ResultSet rs;

        System.out.println (" Especificar clase EzJava");

        // Comprobar que el primer argumento tenga el formato correcto para la parte
        // del URL jdbc: db2:,
        // tal como se describe en el tema Conexión a una fuente
        // de datos utilizando la interfaz DriverManager
        // con IBM Data Server Driver para JDBC y SQLJ.
        // Por ejemplo, para IBM Data Server Driver para
        // conectividad de JDBC y SQLJ de tipo 2,
        // args[0] puede ser MVS1DB2M. Para la
        // conectividad de tipo 4, args[0] podría ser
        // stlmvs1:10110/MVS1DB2M.

//        if (args.length!=3)
//        {
//            System.err.println ("Valor no válido. Primer argumento añadido a "+
//                    "jdbc:db2: debe especificar un URL válido.");
//            System.err.println ("El segundo argumento debe ser un ID de usuario válido.");
//            System.err.println ("El tercer argumento debe ser la contraseña del ID de usuario.");
//            System.exit(1);
//        }
        url = "jdbc:db2://10.10.52.234:5978/SAPXIPDB";
        user = "ephafsel";
        password = "Palacio14";
        try
        {
            // Cargar el controlador
            Class.forName("com.ibm.db2.jcc.DB2Driver");
            System.out.println(" Controlador JDBC cargado");

            // Crear conexión utilizando IBM Data Server Driver para JDBC y SQLJ
            con = DriverManager.getConnection (url, user, password);
            // Confirmar los cambios manualmente
            con.setAutoCommit(false);
            System.out.println(" Creada una conexión JDBC con la fuente de datos");

            // Crear el objeto Statement
            stmt = con.createStatement();
            System.out.println(" Creado el objeto Statement de JDBC");

            // Ejecutar una consulta y generar instancia del conjunto de resultados
            rs = stmt.executeQuery("SELECT EMPNO FROM EMPLOYEE");
            System.out.println(" Creado el objeto JDBC ResultSet");

            // Imprimir todos los números de empleado en el dispositivo de salida estándar
            while (rs.next()) {
                empNo = rs.getString(1);
                System.out.println("Número de empleado = " + empNo);
            }
            System.out.println(" Buscadas todas las filas del conjunto resultados JDBC");
            // Cerrar el conjunto de resultados
            rs.close();
            System.out.println(" Cerrado el conjunto de resultados de JDBC");

            // Cerrar el objeto Statement
            stmt.close();
            System.out.println(" Cerrado el objeto Statement de JDBC");

            // La conexión debe estar en un límite de unidad trabajo para permitir cierre
            con.commit();
            System.out.println ( " Transacción confirmada" );

            // Cierre la conexión
            con.close();
            System.out.println(" Desconectado de la fuente de datos");

            System.out.println(" Salida de JDBC de la clase EzJava - sin errores");

        }

        catch (ClassNotFoundException e)
        {
            System.err.println("No se pudo cargar el controlador JDBC");
            System.out.println("Exception: " + e);
            e.printStackTrace();
        }

        catch(SQLException ex)
        {
            System.err.println("Información sobre SQLException");
            while(ex!=null) {
                System.err.println ("Mensaje de error: " + ex.getMessage());
                System.err.println ("SQLSTATE: " + ex.getSQLState());
                System.err.println ("Código de error: " + ex.getErrorCode());
                ex.printStackTrace();
                ex = ex.getNextException(); // Para controladores que soportan
                // excepciones encadenadas
            }
        }
    }  // Fin main
}    // Fin EzJava
