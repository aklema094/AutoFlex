package autoflex;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.ResultSet;

public class Car implements Utility {

    Connection con;
    Scanner sc;
    String id, brand, model, vin, fuel;
    int year;
    Car(Connection con, Scanner sc) {
        this.con = con;
        this.sc = sc;

    }
    @Override
    public void getDetails() {

    }
    @Override
    public void setDetails() {
        sc.nextLine();
        System.out.print("Enter Brand  : ");
        brand = sc.nextLine();
        System.out.print("Enter Model : ");
        model = sc.nextLine();
        System.out.print("Enter Manufacturing year : ");
        year = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Car Number : ");
        vin = sc.nextLine();
        System.out.print("Enter Fuel type : ");
        fuel = sc.nextLine();
        
        try (PreparedStatement ps = con.prepareStatement("insert into Car_Information (brand,model,year,vin,fueltype,status) values(?,?,?,?,?,?);")) {
            ps.setString(1, brand);
            ps.setString(2, model);
            ps.setInt(3, year);
            ps.setString(4, vin);
            ps.setString(5, fuel);
            ps.setBoolean(6, true);
            int r = ps.executeUpdate();
            if (r > 0) {
                System.out.println("New Car added Successfull");
            } else {
                System.out.println("Failed to add Car");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
