package etgoeshome;

import java.io.IOException;
import java.sql.*;

/**
 *
 * @author Daniel
 */
public class DBConnector {

   private static Connection conn;
   private static int target = 1;

   /**
    * Try to connect to the database
    */
   public DBConnector() {

   }

   public boolean loginToDB(String user, String pw) {
      try
      {
         Class.forName("com.mysql.jdbc.Driver").newInstance();
      } catch (Exception ex)
      {
         System.out.println("Driver not found");
         System.out.println(ex);
      };

      String url = "jdbc:mysql://cslvm74.csc.calpoly.edu/";

      conn = null;
      try
      {
         // conn = DriverManager.getConnection(url, "LOGIN_ID", "PASSWORD");
         conn = DriverManager.getConnection(url + user + "?user=" + user + "&password=" + pw + "&");
         System.out.println("Connected");

      } catch (Exception ex)
      {
         System.out.println("Could not open connection");
         System.out.println(ex);
         return false;
      }
      return true;
   }
}
