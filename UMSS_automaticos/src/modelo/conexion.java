
package modelo;

import java.sql.*;

/**
 *
 * @author Jimena Salazar Soto
 */

public class conexion {
    Connection conn;
    boolean estado;
    public conexion()
    {
        conn= null;
        estado=conectar();
    }
    
    public boolean conectar()
    {
        String driver ="org.postgresql.Driver";
        String connectString="jdbc:postgresql://localhost:5432/test";
        String user="ubaldino";
        String password="asdf";
        boolean res=conectar(driver,connectString,user,password);
        return res;
    }
    public boolean conectar(String driver,String conect,String user,String pass)
    {
        try
        {
            Class.forName(driver);
            
            conn=DriverManager.getConnection(conect, user, pass);
            System.out.println("conexion establecida");
            estado=true;
        }
        catch(Exception e)
        {
            System.out.println(e);
            System.exit(0);
            estado =false;
        }
        return estado;
    }
}
