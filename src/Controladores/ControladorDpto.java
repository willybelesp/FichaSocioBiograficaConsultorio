

package Controladores;


import Modelos.DptoModelo;
import Modelos.ProductoModelo;
import Vistas.FrmDptos;
import Vistas.FrmProductos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sala221
 */
public class ControladorDpto implements ActionListener,MouseListener{
    
    FrmDptos vtnDptos ;
    DptoModelo modelo = new DptoModelo ();

    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    
    
    public enum AccionMVC{
        _VER_DPTO,
        _AGREGAR_DPTO,
        _ELIMINAR_DPTO,
        _ADICIONAR_DPTO
    }
    
    public ControladorDpto(FrmDptos vista){
        this.vtnDptos = vista;        
    }
    
    public void iniciar(){
        
//        try{
//            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windowa.windowslookandfeel");
            SwingUtilities.updateComponentTreeUI(vtnDptos);
            vtnDptos.setVisible(true);
            
//        }catch(UnsupportedOperationException ex){}
//         catch(ClassNotFoundException ex){}
//         catch(InstantiationException ex){}
//         catch(IllegalAccessException ex){}
            
        this.vtnDptos.jBtnVer.setActionCommand("_VER_DPTO");
        this.vtnDptos.jBtnVer.addActionListener(this);
        
        this.vtnDptos.jbtnAdicionar.setActionCommand("_ADICIONAR_DPTO");
        this.vtnDptos.jbtnAdicionar.addActionListener(this);
        
        this.vtnDptos.jBtnCrear.setActionCommand("_AGREGAR_DPTO");
        this.vtnDptos.jBtnCrear.addActionListener(this);
        
        this.vtnDptos.jBtnEliminar.setActionCommand("_ELIMINAR_DPTO");
        this.vtnDptos.jBtnEliminar.addActionListener(this);
        
        this.vtnDptos.jTblDptos.addMouseListener(this);
        this.vtnDptos.jTblDptos.setModel(new DefaultTableModel());
        
        }
   
    public void mouseClicked(MouseEvent e){
        if(e.getButton()== 1)
        {
   
          int fila = this.vtnDptos.jTblDptos.rowAtPoint(e.getPoint());
          if(fila > -1){

            this.vtnDptos.jTxtCodigo.setText(String.valueOf(
                    this.vtnDptos.jTblDptos.getValueAt(fila, 0)));
            this.vtnDptos.jTxtNombre.setText(String.valueOf(
                    this.vtnDptos.jTblDptos.getValueAt(fila, 1)));
              this.vtnDptos.jTxtCodigo.setEditable(false);
             this.vtnDptos.jTxtNombre.setEditable(false);

       
       }

     }
       
   }

   
   @Override
    public void actionPerformed(ActionEvent e) {
        switch (AccionMVC.valueOf(e.getActionCommand())){
            
            
          case _VER_DPTO:{
                this.vtnDptos.jTblDptos.setModel(this.modelo.getTablaProducto());
                this.vtnDptos.jTxtCodigo.requestFocus();
                break;  
                
           }
            case _ADICIONAR_DPTO:{
                this.vtnDptos.jTxtCodigo.setEnabled(true);
                this.vtnDptos.jTxtNombre.setEnabled(true);
                this.vtnDptos.jTxtCodigo.requestFocus();
                this.vtnDptos.jTxtCodigo.setText("");
                this.vtnDptos.jTxtNombre.setText("");
                break;
        }
        
             case  _AGREGAR_DPTO: {
                if(this.modelo.NuevoDpto (
                    this.vtnDptos.jTxtCodigo.getText(),
                    this.vtnDptos.jTxtNombre.getText())){
                    this.vtnDptos.jTblDptos.setModel(this.modelo.getTablaProducto());
                    JOptionPane.showMessageDialog(vtnDptos, "Departamento Creado!.");
                    this.vtnDptos.jTxtCodigo.setText("");
                    this.vtnDptos.jTxtNombre.setText("");

                }            
                 else
                        JOptionPane.showMessageDialog(vtnDptos, "Datos Incorrectos!.");
             break;
        }
             
             case  _ELIMINAR_DPTO:{
                if(this.modelo.EliminarDpto(this.vtnDptos.jTxtCodigo.getText())){
                    this.vtnDptos.jTblDptos.setModel(this.modelo.getTablaProducto());
                    JOptionPane.showMessageDialog(vtnDptos, "Departamento Eliminado.");
                    this.vtnDptos.jTxtCodigo.setText("");
                    this.vtnDptos.jTxtNombre.setText("");   
                    
                }
        }              
    }
  }

}