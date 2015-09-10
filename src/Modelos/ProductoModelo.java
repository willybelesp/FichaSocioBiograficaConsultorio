

package Modelos;

//import com.mysql.jdbc.PreparedStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import sun.rmi.log.ReliableLog;
/**
 *
 * @author Sala221
 */
public class ProductoModelo extends Conexion{
    
    public DefaultTableModel getTablaProducto(){
        DefaultTableModel TablaModelo = new DefaultTableModel();
        
        
          DefaultTableModel tablemodelo = new DefaultTableModel();
          int numregistros = 0;
          String[] NombreColumnas = {"Codigo","Nombre","Precio","Cantidad"};
          
         try{
             String sql ="SELECT count(*) as Total FROM Productos; ";
             PreparedStatement sentencia = this.getConexion().prepareStatement(sql);
              ResultSet resultado = sentencia.executeQuery();
              resultado.next();
              numregistros = resultado.getInt("Total");
              resultado.close();
              
              
          }catch(SQLException e){
              
              JOptionPane.showMessageDialog(null, e.getMessage());
          }
           Object[][] datos = new String [numregistros][4]; 
        
          
    try 
    {
     String sql = "Select * From Productos;";
     PreparedStatement sentencia = this.getConexion().prepareStatement(sql);
     ResultSet resultado = sentencia.executeQuery();
     
     int i = 0;
     
     while (resultado.next()){
         
         datos[i][0] = resultado.getString("Codi_Prod");
         datos[i][1] = resultado.getString("Nomb_prod");
         datos[i][2] = resultado.getString("Precio");
         datos[i][3] = resultado.getString("Cantidad");
         i++;
     }
     
     resultado.close();
     
   TablaModelo.setDataVector(datos, NombreColumnas);
     
    }catch(SQLException e){
        JOptionPane.showMessageDialog(null, e.getMessage());
     }
        
   return TablaModelo ;
     
    }
     
        
     
    public boolean NuevoProducto(String Codigo,String Nombre,String Precio,String Cantidad){
      boolean bandera = false;
      if(validar_datos(Codigo,Nombre,Precio,Cantidad)){
          Precio = Precio.replace(",",".");
          
          String sql = "Insert into Productos "
                  +"values('"+Codigo+"','"+Nombre+"','"+Precio+"',"+Cantidad+");"; 
          
          try {              
              PreparedStatement sentencia = this.getConexion().prepareStatement(sql);
              sentencia.execute();
              sentencia.close();
              bandera = true;                           
          }catch(SQLException e){
              JOptionPane.showMessageDialog(null, "El Producto ya Existe!");
          }
      }
      return bandera;    
    }
     
     public boolean EliminarProducto(String id){
         boolean bandera = false;
         
         String sql = "DELETE FROM Productos where Codi_Prod = '"+id+"';";
         
         try{
             PreparedStatement sentencia = this.getConexion().prepareStatement(sql);
              sentencia.execute();
              sentencia.close();
              bandera = true;
         }catch(SQLException e){
             JOptionPane.showMessageDialog(null, e.getMessage());
             
         }
         return bandera;
     }
     
     private boolean validar_datos(String Codigo, String Nombre,String Precio,String Cantidad){
         
         if(Codigo.equals(" - "))
             return false;
         else if(Nombre.length()> 0 && Precio.length() > 0 && Cantidad.length() > 0){
             return true;
             
         }else 
         return false;
     }
}
    
    

