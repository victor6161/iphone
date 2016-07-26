
package phone.iphone;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DB {
     private  Connection cn;

    public  void DB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try {
                cn = DriverManager.getConnection("jdbc:mysql://localhost/phones", "root", "1234");
            } catch (SQLException ex) {
                System.out.println("Error in create Connection " + ex);
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Error in download Driver " + ex);
        }
    }
    public ArrayList getListIphone(){
       
        ArrayList <Iphone> iphoneList=new ArrayList<>();
         try {
            Statement statement = cn.createStatement();
            ResultSet rs =  statement.executeQuery("Select * from iphone");
            rs.next();
            int id=rs.getInt(1);
            String manufacter=rs.getString(2);
            iphoneList.add(new Iphone(id,manufacter));
         } catch (SQLException ex) {
             Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
         }
        return iphoneList;
    }
}
