

package Controladores;


import Modelos.ProductoModelo;
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
public class ControladorProducto implements ActionListener,MouseListener{
    
    FrmProductos vtnProductos ;
    ProductoModelo modelo = new ProductoModelo ();

    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    
    
    public enum AccionMVC{
        _VER_PRODUCTO,
        _AGREGAR_PRODUCTO,
        _ELIMINAR_PRODUCTO,
        _ADICIONAR_PRODUCTO
    }
    
    public ControladorProducto(FrmProductos vista){
        this.vtnProductos = vista;        
    }
    
    public void iniciar(){
        
//        try{
//            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windowa.windowslookandfeel");
            SwingUtilities.updateComponentTreeUI(vtnProductos);
            vtnProductos.setVisible(true);
            
//        }catch(UnsupportedOperationException ex){}
//         catch(ClassNotFoundException ex){}
//         catch(InstantiationException ex){}
//         catch(IllegalAccessException ex){}
            
        this.vtnProductos.jBtnVerProduct.setActionCommand("_VER_PRODUCTO");
        this.vtnProductos.jBtnVerProduct.addActionListener(this);
        
        this.vtnProductos.jbtnAdicionarP.setActionCommand("_ADICIONAR_PRODUCTO");
        this.vtnProductos.jbtnAdicionarP.addActionListener(this);
        
        this.vtnProductos.jBtnCrearProduc.setActionCommand("_AGREGAR_PRODUCTO");
        this.vtnProductos.jBtnCrearProduc.addActionListener(this);
        
        this.vtnProductos.jBtnEliminarProduc.setActionCommand("_ELIMINAR_PRODUCTO");
        this.vtnProductos.jBtnEliminarProduc.addActionListener(this);
        
        this.vtnProductos.jtblProductos.addMouseListener(this);
        this.vtnProductos.jtblProductos.setModel(new DefaultTableModel());
        
        }
   
    public void mouseClicked(MouseEvent e){
        if(e.getButton()== 1)
        {
   
          int fila = this.vtnProductos.jtblProductos.rowAtPoint(e.getPoint());
          if(fila > -1){

            this.vtnProductos.jftCodigo.setText(String.valueOf(
                    this.vtnProductos.jtblProductos.getValueAt(fila, 0)));
            this.vtnProductos.jftProducto.setText(String.valueOf(
                    this.vtnProductos.jtblProductos.getValueAt(fila, 1)));
            this.vtnProductos.jftPrecio.setText(String.valueOf(
                    this.vtnProductos.jtblProductos.getValueAt(fila, 2)));
            this.vtnProductos.jftCantidad.setText(String.valueOf(
                    this.vtnProductos.jtblProductos.getValueAt(fila, 3)));
             this.vtnProductos.jftCodigo.setEditable(false);
             this.vtnProductos.jftProducto.setEditable(false);
             this.vtnProductos.jftPrecio.setEditable(false);
             this.vtnProductos.jftCantidad.setEditable(false);
       
       }

     }
       
   }

   
   @Override
    public void actionPerformed(ActionEvent e) {
        switch (AccionMVC.valueOf(e.getActionCommand())){
            
            
          case _VER_PRODUCTO:{
                this.vtnProductos.jtblProductos.setModel(this.modelo.getTablaProducto());
                this.vtnProductos.jftCodigo.requestFocus();
                break;  
                
           }
            case _ADICIONAR_PRODUCTO:{
                this.vtnProductos.jftCodigo.setEnabled(true);
                this.vtnProductos.jftProducto.setEnabled(true);
                this.vtnProductos.jftPrecio.setEnabled(true);
                this.vtnProductos.jftCantidad.setEnabled(true);
                this.vtnProductos.jftCodigo.requestFocus();
                this.vtnProductos.jftCodigo.setText("");
                this.vtnProductos.jftProducto.setText("");
                this.vtnProductos.jftPrecio.setText("");
                this.vtnProductos.jftCantidad.setText("");
                break;
        }
        
             case  _AGREGAR_PRODUCTO: {
                if(this.modelo.NuevoProducto (
                    this.vtnProductos.jftCodigo.getText(),
                    this.vtnProductos.jftProducto.getText(),
                    this.vtnProductos.jftPrecio.getText(),
                    this.vtnProductos.jftCantidad.getText())) {
                    this.vtnProductos.jtblProductos.setModel(this.modelo.getTablaProducto());
                    JOptionPane.showMessageDialog(vtnProductos, "Producto Creado!.");
                    this.vtnProductos.jftCodigo.setText("");
                    this.vtnProductos.jftProducto.setText("");
                    this.vtnProductos.jftPrecio.setText("");
                    this.vtnProductos.jftCantidad.setText("");
                }            
                 else
                        JOptionPane.showMessageDialog(vtnProductos, "Datos Incorrectos!.");
             break;
        }
             
             case  _ELIMINAR_PRODUCTO:{
                if(this.modelo.EliminarProducto(this.vtnProductos.jftCodigo.getText())){
                    this.vtnProductos.jtblProductos.setModel(this.modelo.getTablaProducto());
                    JOptionPane.showMessageDialog(vtnProductos, "Producto Eliminado.");
                    this.vtnProductos.jftCodigo.setText("");
                    this.vtnProductos.jftProducto.setText("");
                    this.vtnProductos.jftPrecio.setText("0");
                    this.vtnProductos.jftCantidad.setText("0");   
                    
                }
        }              
    }
  }

}