package autoflex;

import java.sql.SQLException;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class LogIn {
    Scanner sc;
    Connection con;

    LogIn(Connection con, Scanner sc) {
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
        if (rs.next()) {
            System.out.println("Log in sucessfully " + rs.getString("name"));

            if (rs.getString("role").equals("admin")) {
                // admin menu 
                adminMenu();
            } else {
                // user menu 
            }
        } else {
            System.out.println("Failed to login!!! Invalid email or password.");
        }

    }

    // admin menu 
    public void adminMenu() {
        Employee emp = new Employee(con,sc);
        Car car = new Car(con,sc);
        Showroom sh = new Showroom(con,sc);
        sc.nextLine();
        while (true) {
            System.out.println("=================Admin Menu==================");
            System.out.println("============================================");
            System.out.println("1. Add new Showroom");//done
            System.out.println("2. Add new Employee");//done
            System.out.println("3. Add new car");
            System.out.println("4. View all Showroom");//dpne
            System.out.println("5. view all Employee");//done
            System.out.println("6. view all available Car");
            System.out.println("7. view all Rental Car");
            System.out.println("0. Exit");
            System.out.println("============================================");
            System.out.print("Enter youe choice : ");
            int ch = sc.nextInt();
            if (ch == 0) {
                return;
            }
            while (ch != 9) {
                switch (ch) {
                    case 1:
                        sh.setDetails();
                        System.out.println();
                        System.out.println("1].ADD NEW SHOWROOM");
                        System.out.println("9].GO BACK TO MAIN MENU");
                        ch = sc.nextInt();
                        break;
                    case 2:
                        emp.setDetails();
                        System.out.println();
                        System.out.println("2].ADD NEW EMPLOYEE");
                        System.out.println("9].GO BACK TO MAIN MENU");
                        ch = sc.nextInt();
                        break;
                    case 3:
                        car.setDetails();
                        System.out.println();
                        System.out.println("3].ADD NEW CAR");
                        System.out.println("9].GO BACK TO MAIN MENU");
                        ch = sc.nextInt();
                        break;
                    case 4:
                        sh.getDetails();
                        System.out.println();
                        System.out.println("9].GO BACK TO MAIN MENU");
                        System.out.println("0].EXIT");
                        ch = sc.nextInt();
                        break;
                    case 5:
                        emp.getDetails();
                        System.out.println();
                        System.out.println("9].GO BACK TO MAIN MENU");
                        System.out.println("0].EXIT");
                        ch = sc.nextInt();
                        break;
                    case 6:
                        System.out.println();
                        System.out.println("9].GO BACK TO MAIN MENU");
                        System.out.println("0].EXIT");
                        ch = sc.nextInt();
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Inavlid Choice!!!");
                        break;
                }
            }
        }
    }

}
