/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Estudiante;
import modelo.conexion;
import vista.inicio;
import vista.opciones_estudiante;
/**
 *
 * @author Jimena
 */
public class UMSS_automaticos implements ActionListener{
  conexion conect;
  Estudiante est;
  inicio ini;
  opciones_estudiante opEs;
  
  public UMSS_automaticos(){
    conect=new conexion();
    est = new Estudiante();
    ini = new inicio();
    opEs = new opciones_estudiante();
    ini.setVisible(true);
  }
  
  // metodo para configurar las vistas y agregar eventos
  public void iniciarVista(){
    /*  Agregando botones   */
    opEs.btn_trans.addActionListener(this);
  }
  
  
  //metodo para realizar eventos de las vistas 
  @Override
  public void actionPerformed(ActionEvent ae) {
    throw new UnsupportedOperationException("Not supported yet.");
  }
  
    public static void main(String[] args) {
      UMSS_automaticos umss = new UMSS_automaticos();
    }

}
