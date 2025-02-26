
package autoflex;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class RentCar {
    
     Connection con;
     Scanner sc;
    
      RentCar(Connection c, Scanner sc) {
        this.con = c;
        this.sc = sc;
     }
      // rent a car
      public void rentACar() throws SQLException {
       sc.nextLine();
       System.out.print("Enter your Id : ");
       int userId = sc.nextInt();
       String q = "Select * from users WHERE id = ?;" ;
        while(!isExist(userId,q)){
           System.out.println("Enter valid user Id");
           userId = sc.nextInt();
       }
       sc.nextLine();
       System.out.print("Enter your name : ");
       String name = sc.nextLine();
       System.out.print("\nEnter the Car Id you want to Get : ");
       int carId = sc.nextInt();
       q = "select id from car_information WHERE id = ? ;";
       while(!isExist(carId,q)){
           System.out.println("Enter valid Car Id");
           carId = sc.nextInt();
       }
       System.out.print("Enter the number of rental days : ");
       int totalRentalDays = sc.nextInt();
       
       PreparedStatement pre = con.prepareStatement("insert into rentinformation(carId,customarId,customarName,totalDays) values(?,?,?,?)");
       pre.setInt(1, carId);
        pre.setInt(2, userId);
        pre.setString(3, name);
        pre.setInt(4,totalRentalDays);
        
        int r = pre.executeUpdate();
        if(r>0){
            PreparedStatement ps = con.prepareStatement("UPDATE car_information SET status = ? WHERE id = ?");
           ps.setBoolean(1, false); 
           ps.setInt(2, carId); 
            int rs = ps.executeUpdate();
            if(rs>0){
                System.out.println("Rent a car successfully");
            }
            else{
                System.out.println("Failed to rent a car!! Try again");
                
            }
        }else{
            System.out.println("Failed to rent a car!! Try again");
        }
       
    }
   // check information is exist or not.
    private boolean isExist(int id, String query) throws SQLException {
        
         PreparedStatement p = con.prepareStatement(query);
         p.setInt(1, id);
         ResultSet rs = p.executeQuery();
         if (rs.next()) {
            return true;
         }
        return false;
    }
 // view all retal car
    public void viewAllRentalCar(){
         try (PreparedStatement ps = con.prepareStatement("select * from  rentinformation ;")) {

            ResultSet rs = ps.executeQuery();
            System.out.println("+----+--------------+---------------+-----------------+-----------+");
            System.out.println("| id |    carId     |  customarId   |  customarName   |  totalDays|");
            System.out.println("+----+--------------+---------------+-----------------+-----------+");
            while (rs.next()) {
                int id = rs.getInt("id");
                int cId = rs.getInt("carId");
                int cusId = rs.getInt("customarId");
                String cName = rs.getString("customarName");
                int td = rs.getInt("totalDays");
                
                System.out.printf("|  %-2s| %-13s| %-14s| %-16s| %-10s|\n",id,cId,cusId,cName,td);
                
            }
             System.out.println("+----+--------------+---------------+-----------------+-----------+");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

      
}
