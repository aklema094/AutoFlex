package autoflex;

import java.sql.SQLException;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;

public class AutoFlex {

    public static void main(String[] args) throws SQLException, ClassNotFoundException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        final String url = "jdbc:mysql://localhost:3306/autoflex";
        final String userName = "root";
        final String password = "29344";
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection con = DriverManager.getConnection(url, userName, password);
        SignUp signUp = new SignUp(con, sc);
        LogIn log = new LogIn(con,sc);

        while (true) {
            System.out.println("           WELCOME TO AUTOFLEX");
            System.out.println("=========================================");
            System.out.println("1. Log In");
            System.out.println("2. Sign Up");
            System.out.println("3. Exit");
            System.out.print("Enter your choice : ");
            int ch = sc.nextInt();
            switch (ch) {
                case 1:
                    log.userLogin();
                    System.out.println("");
                    break;
                case 2:
                    signUp.userRegistration();
                    System.out.println("");
                    break;
                case 3:
                    exit("Existing System");
                    return;
                default:
                    System.out.println("Invalid Choice!!!!\n");
            }

        }
    }

    // existing from system
    public static void exit(String s) throws InterruptedException {
        System.out.print(s);
        for (int i = 0; i < 7; i++) {
            System.out.print(". ");
            Thread.sleep(250);
        }
        System.out.println("");
    }

}
