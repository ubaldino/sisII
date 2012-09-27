package org.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Proyecto{
    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;
    PreparedStatement pst = null;
    
  public void conexion(){
    String url = "jdbc:postgresql://localhost:5432/test";
    String user = "ubaldino";
    String password = "asdf";
    try{
      con = DriverManager.getConnection(url, user, password);
      st = con.createStatement();
      rs = st.executeQuery("SELECT * from usuarios");
      System.out.print(" usuario "+"  |  "+"  monto\n-------------------------\n");
      String cadena="";
      while(rs.next()){
        cadena += "  "+rs.getString(1)+"    |    "+rs.getInt(2)+"\n";
      }
        System.out.println(cadena);
    }catch (SQLException ex) {
      System.out.println("error1: "+ex);
    }
  }
  
  public void cerrarConexion(){
      try {
        if(rs != null) rs.close();
        if(st != null) st.close();
        if(con != null) con.close();
      }catch (SQLException ex) {
        System.out.println("error2: "+ex);
      }
  }
  
  /** Actualizar base de datos de ambos estudiantes segun el
   *  monto que se desea transferir
   */
  public void actBdEsts(String est1, int mon, String est2){
    boolean usr1=false,usr2=false,permit=true;
    int mon1 = 0,mon2 = 0;
    try{
      rs = st.executeQuery("SELECT * from usuarios");
      while(rs.next()){
        if(rs.getString(1).equals(est1)){
          usr1=true;
          mon1= rs.getInt(2);
          if(mon1<mon)
            permit=false;
        }
        if(rs.getString(1).equals(est2)){
          usr2=true;
          mon2 = rs.getInt(2);
        }
      }
      if(usr1 && usr2 && permit){
      // update dat
        String stmEst1 = String.format("UPDATE usuarios SET monto=%d WHERE nombre='%s'",mon1,est1);
        String stmEst2 = String.format("UPDATE usuarios SET monto=%d WHERE nombre='%s'",mon2,est2);
        st = con.createStatement();
        st.executeUpdate(stmEst1);
        st.executeUpdate(stmEst2);
      }
      //System.out.println(est1+":"+mon1+"\n"+est2+":"+mon2);
      /**Mensajes de posibles fallos*/
      if(!usr1)
        System.out.println(est1+": no existe");
      if(!usr2)
        System.out.println(est2+": no existe");
      if(!permit)
        System.out.println(mon+": es mayor al acumulado en cuenta");
    }catch(SQLException ex){ }
   
  }
  
  public static void main(String[] args){
    Proyecto p = new Proyecto();
    p.conexion();
    p.actBdEsts("user1", 700, "user2");
    p.cerrarConexion();
  }
  
 //-------------------------------------------------   
 /** public ingresarDatos(){
    // ingresando datos
   try{
      String stm = "INSERT INTO usuarios (nombre, monto) VALUES (?, ?)";
      pst = con.prepareStatement(stm);
      pst.setString(1,est1);                    
      pst.setInt(2,mon1);
      pst.executeUpdate();
    }catch (SQLException ex) { } 
  }*/
}