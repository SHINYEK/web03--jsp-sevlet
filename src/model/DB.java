package model;
import java.sql.*;

public class DB {
	//상수라서 CON대문자 - public의 static은 대문자로 써준다
	public static Connection CON;
	   static {
	      try {
	         Class.forName("com.mysql.jdbc.Driver");
	         CON = DriverManager.getConnection(
	        		 "jdbc:mysql://localhost:3306/webdb", "web", "pass");
	         System.out.println("접속성공");
	      } catch (Exception e) {
	         System.out.println("접속실패:" + e.toString());
	      }
	   }
}
