package autoflex;

import java.sql.Connection;
import java.util.Scanner;
import java.sql.SQLException;
import java.util.UUID;
import java.sql.PreparedStatement;

public class Employee implements Utility {

    Scanner sc;
    Connection con;
    String id, name, dep;
    int age;

    Employee(Connection con, Scanner sc) {
        this.con = con;
        this.sc = sc;
    }

    @Override
    public void getDetails() {
    
    }

    @Override
    public void setDetails() {
        UUID uid = UUID.randomUUID();
        id = String.valueOf(uid);
        sc.nextLine();
        System.out.print("Enter Name : ");
        name = sc.nextLine();
        System.out.print("Enter Age : ");
        age = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Department : ");
        dep = sc.nextLine();

        try (PreparedStatement ps = con.prepareStatement("insert into employees (id,name,age,department) values(?,?,?,?);")) {
            ps.setString(1, id);
            ps.setString(2, name);
            ps.setInt(3, age);
            ps.setString(4, dep);
            int r = ps.executeUpdate();
            if(r>0){
                System.out.println("Employee added successfull");
            }else{
                System.out.println("Failed to add employee");
            }
            
         
        } catch (SQLException e) {
            
            e.printStackTrace();

        }

    }

}
