
package autoflex;

import java.sql.SQLException;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
public class LogIn {
    
    Scanner sc;
    Connection con;
    
    LogIn(Connection con,Scanner sc){ 
        this.con = con;
        this.sc = sc;  
    }
    
    public void userLogin() throws SQLException {
        sc.nextLine();
        System.out.print("Enter your email : ");
        String email = sc.nextLine();
        System.out.print("Enter your password : ");
        String password = sc.nextLine();
        
        PreparedStatement ps = con.prepareStatement("select * from users where email = ? and password = ?;");
        ps.setString(1, email);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        
        if(rs.next()){
            System.out.println("Log in sucessfully "+rs.getString("name"));
            
            if(rs.getString("role").equals("admin")){
                // admin menu 
                
            }else{
                // user menu 
            }
        }else{
            System.out.println("Failed to login!!! Invalid email or password.");
        }
        
        
        
    }
}
