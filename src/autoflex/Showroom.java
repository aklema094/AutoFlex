package autoflex;

import java.sql.Connection;
import java.util.Scanner;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.UUID;
import java.sql.PreparedStatement;

public class Showroom implements Utility {

    Scanner sc;
    Connection con;
    String id, sName, sAddress, mName;
    int inStock, tNoEmp;

    Showroom(Connection con, Scanner sc) {
        this.con = con;
        this.sc = sc;
    }

    @Override
    public void getDetails() {
        try (PreparedStatement p = con.prepareStatement("select * from showrooms;")) {
            ResultSet rs = p.executeQuery();
            System.out.println("+---------------------------------------+----------------------+------------------------+---------------------+-------+-----------+");
            System.out.println("|                   id                  |     Showroom Name    |     Showroom Address   |    Manager Name     | Stock |  Employee |");
            System.out.println("+---------------------------------------+----------------------+------------------------+---------------------+-------+-----------+");
            while (rs.next()) {
                String id = rs.getString("id");
                String sname = rs.getString("sName");
                String add = rs.getString("sAddress");
                String man = rs.getString("mName");
                int stock = rs.getInt("stock");
                int emp = rs.getInt("employee");
                System.out.printf("| %-38s| %-21s| %-23s| %-20s| %-6s| %-10s|\n", id, sname,add,man,stock,emp);
            }
            System.out.println("+---------------------------------------+----------------------+------------------------+---------------------+-------+-----------+");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setDetails() {
        UUID uid = UUID.randomUUID();
        id = String.valueOf(uid);
        sc.nextLine();
        System.out.print("Enter Showroom Name : ");
        sName = sc.nextLine();
        System.out.print("Enter Showroom Address : ");
        sAddress = sc.nextLine();
        System.out.print("Enter Manager Name : ");
        mName = sc.nextLine();
        System.out.print("Enter total no of car in Stock : ");
        inStock = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter total no of Employee : ");
        tNoEmp = sc.nextInt();
        sc.nextLine();

        try (PreparedStatement ps = con.prepareStatement("insert into showrooms (id,sName,sAddress,mName,stock,employee) values(?,?,?,?,?,?);")) {
            ps.setString(1, id);
            ps.setString(2, sName);
            ps.setString(3, sAddress);
            ps.setString(4, mName);
            ps.setInt(5, inStock);
            ps.setInt(6, tNoEmp);
            int r = ps.executeUpdate();
            if (r > 0) {
                System.out.println("Showroom added Successfull");
            } else {
                System.out.println("Failed to add Showroom");
            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

}
