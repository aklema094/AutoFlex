package autoflex;

import java.sql.Connection;
import java.util.Scanner;
import java.sql.SQLException;
import java.sql.ResultSet;
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
        try (PreparedStatement p = con.prepareStatement("select * from employees;")) {
            ResultSet rs = p.executeQuery();
            System.out.println("+---------------------------------------+----------------+-----+------------------------+");
            System.out.println("|                   id                  |      name      | age |       department       |");
            System.out.println("+---------------------------------------+----------------+-----+------------------------+");
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String dep = rs.getString("department");
                System.out.printf("| %-38s| %-15s| %-4s| %-23s|\n", id, name, age, dep);
            }
            System.out.println("+---------------------------------------+----------------+-----+------------------------+");
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
            if (r > 0) {
                System.out.println("Employee added successfull");
            } else {
                System.out.println("Failed to add employee");
            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

}
