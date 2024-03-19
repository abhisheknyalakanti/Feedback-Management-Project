
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class jdbc1 {
    public static void main(String[] args) throws Exception {

        Class.forName("oracle.jdbc.driver.OracleDriver");

        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","pat","pat");

        Statement stmt= con.createStatement();

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your Firtsname:");
        String firstname = sc.next();
        System.out.println("Enter your Lastname:");
        String lastname = sc.next();
        System.out.println("Enter your Email:");
        String email= sc.next();
        System.out.println("Enter password:");
        String pass = sc.next();
        System.out.println("Enter mobile:");
        long mobile =sc.nextLong();
        sc.nextLine();
        System.out.println("Enter Address:");
        String address=sc.next();

        //driver class connect to oracle db


        //pk generation

        int regid=0;
        ResultSet rs= stmt.executeQuery("select max(regid) from register");
        if(rs.next()) 
        {
            regid=rs.getInt(1);

        }
        regid++;

        PreparedStatement pstmt= con.prepareStatement("INSERT INTO register VALUES(?,?,?,?,?,?,?)");
        pstmt.setInt(1,regid);
        pstmt.setString(2,firstname);
        pstmt.setString(3,lastname);
        pstmt.setString(4,email);
        pstmt.setString(5,pass);
        pstmt.setLong(6,mobile);
        pstmt.setString(7,address);
        int i=pstmt.executeUpdate();
        if(i==1)
        {
            System.out.println("Record Inserted");
        }
        pstmt.close();

    }
}

