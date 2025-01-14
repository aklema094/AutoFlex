package autoflex;

import java.sql.Connection;
import java.util.Scanner;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class SignUp {
    
    private Connection con;
    private Scanner sc;
    
    SignUp(Connection con, Scanner sc) {
        this.con = con;
        this.sc = sc;
    }
    // new User registration 
    public void userRegistration() throws SQLException {
        sc.nextLine();
        System.out.print("Enter your name : ");
        String name = sc.nextLine();
        System.out.print("Enter your email : ");
        String email = sc.nextLine();
        while (!email.contains("@")) {
            System.out.print("Enter a valid email : ");
            email = sc.nextLine();
        }
        if (isUserExist(email)) {
            System.out.println("Already an account exist with this email!!! ");
        } else {
            System.out.print("Enter your password(6) : ");
            String password = sc.nextLine();
            while (password.length() < 6) {
                System.out.print("Password should be more then 6 : ");
                password = sc.nextLine();
            }
            String query = "INSERT INTO users(name,email,password,role) VALUES(?,?,?,'user');";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);
            int r = ps.executeUpdate();
            if (r > 0) {
                System.out.println("User registration successfully");
            } else {
                System.out.println("Failed to registration!!! try again.");
            }
        }

    }
    // check user exist or not in database
    public boolean isUserExist(String email) throws SQLException {
        PreparedStatement p = con.prepareStatement("Select * from users WHERE email = ?;");
        p.setString(1, email);
        ResultSet rs = p.executeQuery();
        if (rs.next()) {
            return true;
        }
        return false;
    }
}
