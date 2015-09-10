

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
public class DptoModelo extends Conexion{
    
    public DefaultTableModel getTablaProducto(){
        DefaultTableModel TablaModelo = new DefaultTableModel();
        
        
          DefaultTableModel tablemodelo = new DefaultTableModel();
          int numregistros = 0;
          String[] NombreColumnas = {"Codigo","Nombre"};
          
         try{
             String sql ="SELECT count(*) as Total FROM Departamentos; ";
             PreparedStatement sentencia = this.getConexion().prepareStatement(sql);
              ResultSet resultado = sentencia.executeQuery();
              resultado.next();
              numregistros = resultado.getInt("Total");
              resultado.close();
              
              
          }catch(SQLException e){
              
              JOptionPane.showMessageDialog(null, e.getMessage());
          }
           Object[][] datos = new String [numregistros][2]; 
        
          
    try 
    {
     String sql = "Select * From Departamentos;";
     PreparedStatement sentencia = this.getConexion().prepareStatement(sql);
     ResultSet resultado = sentencia.executeQuery();
     
     int i = 0;
     
     while (resultado.next()){
         
         datos[i][0] = resultado.getString("Codi_Dpto");
         datos[i][1] = resultado.getString("Nomb_Dpto");
         i++;
     }
     
     resultado.close();
     
   TablaModelo.setDataVector(datos, NombreColumnas);
     
    }catch(SQLException e){
        JOptionPane.showMessageDialog(null, e.getMessage());
     }
        
   return TablaModelo ;
     
    }
     
        
     
    public boolean NuevoDpto(String Codigo,String Nombre){
      boolean bandera = false;
      if(validar_datos(Codigo,Nombre)){
                    
          String sql = "Insert into Departamentos "
                  +"values('"+Codigo+"','"+Nombre+");"; 
          
          try {              
              PreparedStatement sentencia = this.getConexion().prepareStatement(sql);
              sentencia.execute();
              sentencia.close();
              bandera = true;                           
          }catch(SQLException e){
              JOptionPane.showMessageDialog(null, "El Departamento ya Existe!");
          }
      }
      return bandera;    
    }
     
     public boolean EliminarDpto(String id){
         boolean bandera = false;
         
         String sql = "DELETE FROM Departamentos where Codi_Prod = '"+id+"';";
         
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
     
     private boolean validar_datos(String Codigo, String Nombre){
         
         if(Codigo.equals(" - "))
             return false;
         else if(Nombre.length()> 0 && Codigo.length() > 0 && Nombre.length() > 0){
             return true;
             
         }else 
         return false;
     }
}
    
    

