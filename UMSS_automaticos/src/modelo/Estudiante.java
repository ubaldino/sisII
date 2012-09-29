/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author ubaldino
 */
public class Estudiante {
  private Connection con = null;
  private Statement st = null;
  private ResultSet rs = null;
  PreparedStatement pst = null;
  
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
    }catch(SQLException ex){}
  }
  public static void main(String[]args){
    System.out.println("hola mundo");
  }
}
