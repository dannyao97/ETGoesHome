package etgoeshome;

import java.sql.*;
import javax.swing.table.DefaultTableModel;

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

   public DefaultTableModel selectTab1(String dbState) {

      /* Column headers */
      String colNames[] =
      {
         "State", "City"
      };
      DefaultTableModel table = new DefaultTableModel(colNames, 0);

      try
      {
         Statement statement = conn.createStatement();
         ResultSet result = statement.executeQuery("SELECT *\n"
                 + "FROM Location\n"
                 + "WHERE State = '" + dbState.toLowerCase() + "'\n"
                 + "ORDER BY City;");
         boolean f = result.next();
         while (f)
         {
            String s1 = result.getString(1); //State
            String s2 = result.getString(2); //City name

            Object[] objs =
            {
               s1, s2
            };
            table.addRow(objs);

            System.out.println(s1 + "   :   " + s2);

            f = result.next();
         }

      } catch (Exception ee)
      {
         System.out.println(ee);
      }

      return table;
   }
   
   public DefaultTableModel shootingsPerCity(String dbState) {

      /* Column headers */
      String colNames[] =
      {
         "State", "City", "Shootings"
      };
      DefaultTableModel table = new DefaultTableModel(colNames, 0);

      try
      {
         Statement statement = conn.createStatement();
         ResultSet result = statement.executeQuery("SELECT State, City, COUNT(*)\n"
                 + "FROM Shootings\n"
                 + "WHERE State = '" + dbState.toLowerCase() + "'\n"
                 + "GROUP BY City"
                 + "ORDER BY COUNT(*) DESC;");
         boolean f = result.next();
         while (f)
         {
            String s1 = result.getString(1); //State
            String s2 = result.getString(2); //City name
            String s3 = result.getString(3); //Number of Shootings

            Object[] objs =
            {
               s1, s2, s3
            };
            table.addRow(objs);

            System.out.println(s1 + "   :   " + s2 + "   :   " + s3);

            f = result.next();
         }

      } catch (Exception ee)
      {
         System.out.println(ee);
      }

      return table;
   }
   
   public DefaultTableModel sightingsPerCity(String dbState) {

      /* Column headers */
      String colNames[] =
      {
         "State", "City", "UFO_Sightings"
      };
      DefaultTableModel table = new DefaultTableModel(colNames, 0);

      try
      {
         Statement statement = conn.createStatement();
         ResultSet result = statement.executeQuery("SELECT State, City, COUNT(*)\n"
                 + "FROM UFOSightings\n"
                 + "WHERE State = '" + dbState.toLowerCase() + "'\n"
                 + "GROUP BY City"
                 + "ORDER BY COUNT(*) DESC;");
         boolean f = result.next();
         while (f)
         {
            String s1 = result.getString(1); //State
            String s2 = result.getString(2); //City name
            String s3 = result.getString(3); //Number of UFO sightings

            Object[] objs =
            {
               s1, s2, s3
            };
            table.addRow(objs);

            System.out.println(s1 + "   :   " + s2 + "   :   " + s3);

            f = result.next();
         }

      } catch (Exception ee)
      {
         System.out.println(ee);
      }

      return table;
   }
   
   public DefaultTableModel shootingsPerState() {

      /* Column headers */
      String colNames[] =
      {
         "State", "Shootings"
      };
      DefaultTableModel table = new DefaultTableModel(colNames, 0);

      try
      {
         Statement statement = conn.createStatement();
         ResultSet result = statement.executeQuery("SELECT State, COUNT(*)\n"
                 + "FROM Shootings\n"
                 + "GROUP BY State\n"
                 + "ORDER BY State;");
         boolean f = result.next();
         while (f)
         {
            String s1 = result.getString(1); //State
            String s2 = result.getString(2); //Number of shootings

            Object[] objs =
            {
               s1, s2
            };
            table.addRow(objs);

            System.out.println(s1 + "   :   " + s2);

            f = result.next();
         }

      } catch (Exception ee)
      {
         System.out.println(ee);
      }

      return table;
   }
   
   public DefaultTableModel sightingsPerState() {

      /* Column headers */
      String colNames[] =
      {
         "State", "UFO_Sightings"
      };
      DefaultTableModel table = new DefaultTableModel(colNames, 0);

      try
      {
         Statement statement = conn.createStatement();
         ResultSet result = statement.executeQuery("SELECT State, COUNT(*)\n"
                 + "FROM UFOSightings\n"
                 + "GROUP BY State\n"
                 + "ORDER BY State;");
         boolean f = result.next();
         while (f)
         {
            String s1 = result.getString(1); //State
            String s2 = result.getString(2); //Number of sightings

            Object[] objs =
            {
               s1, s2
            };
            table.addRow(objs);

            System.out.println(s1 + "   :   " + s2);

            f = result.next();
         }

      } catch (Exception ee)
      {
         System.out.println(ee);
      }

      return table;
   }

   public boolean addShooting(String name, String date, String death, String weapon,
           int age, String gender, String race, String city, String state, String mental,
           String threat, String flee, String camera) {

      try
      {
         Statement statement = conn.createStatement();
         statement.executeUpdate("INSERT INTO Shootings(Name, Day, \n"
                 + "Death, Weapon, Age, Gender, Race, City, \n"
                 + "State, Mental, Threat, Flee, BodyCamera) \n"
                 + "VALUES('" + name + "', '" + date + "', '" + death + "', '"
                 + weapon + "', " + age + ", '" + gender +  "', '" + race + "', '" 
                 + city + "', '" + state + "', '" + mental + "', '" + threat + "', '"
                 + flee + "', '" + camera + "');");
         return true;
      } catch (Exception ee)
      {
         System.out.println(ee);
         return false;
      }
   }
   
   public DefaultTableModel mostSightingsByYear() {

      /* Column headers */
      String colNames[] =
      {
         "Year", "UFO_Sightings"
      };
      DefaultTableModel table = new DefaultTableModel(colNames, 0);

      try
      {
         Statement statement = conn.createStatement();
         ResultSet result = statement.executeQuery(
                   "SELECT DISTINCT one.Y, one.C\n"
                 + "FROM (SELECT YEAR(Occurence) AS Y, COUNT(*) AS C\n"
                 + "      FROM UFOSightings\n"
                 + "      GROUP BY YEAR(Occurence)) one,\n"
                 + "     (SELECT MAX(two.C) AS M\n"
                 + "      FROM (SELECT YEAR(Occurence), COUNT(*) AS C\n"
                 + "            FROM UFOSightings\n"
                 + "            GROUP BY YEAR(Occurence)) two) max\n"
                 + "WHERE one.C = max.M\n"
                 + "ORDER BY one.Y;");
         boolean f = result.next();
         while (f)
         {
            String s1 = result.getString(1); //Year
            String s2 = result.getString(2); //Max number of UFO sightings

            Object[] objs =
            {
               s1, s2
            };
            table.addRow(objs);

            System.out.println(s1 + "   :   " + s2);

            f = result.next();
         }

      } catch (Exception ee)
      {
         System.out.println(ee);
      }

      return table;
   }
   
   public DefaultTableModel mostSightingsByCity() {

      /* Column headers */
      String colNames[] =
      {
         "State", "City", "UFO_Sightings"
      };
      DefaultTableModel table = new DefaultTableModel(colNames, 0);

      try
      {
         Statement statement = conn.createStatement();
         ResultSet result = statement.executeQuery(
                   "SELECT DISTINCT u.State, one.City, one.C\n"
                 + "FROM (SELECT City, COUNT(*) AS C\n"
                 + "      FROM UFOSightings\n"
                 + "      GROUP BY City) one,\n"
                 + "     (SELECT MAX(two.C) AS M\n"
                 + "      FROM (SELECT City, COUNT(*) AS C\n"
                 + "            FROM UFOSightings\n"
                 + "            GROUP BY City) two) max,\n"
                 + "     UFOSightings u\n"
                 + "WHERE u.City = one.City AND one.C = max.M\n"
                 + "ORDER BY u.State, one.City;");
         boolean f = result.next();
         while (f)
         {
            String s1 = result.getString(1); //State
            String s2 = result.getString(2); //City name
            String s3 = result.getString(3); //Max number of UFO sightings

            Object[] objs =
            {
               s1, s2, s3
            };
            table.addRow(objs);

            System.out.println(s1 + "   :   " + s2 + "   :   " + s3);

            f = result.next();
         }

      } catch (Exception ee)
      {
         System.out.println(ee);
      }

      return table;
   }
   
   public DefaultTableModel sawBrightLight() {

      /* Column headers */
      String colNames[] =
      {
         "State", "City", "Description"
      };
      DefaultTableModel table = new DefaultTableModel(colNames, 0);

      try
      {
         Statement statement = conn.createStatement();
         ResultSet result = statement.executeQuery("SELECT State, City, Description\n"
                 + "FROM UFOSightings\n"
                 + "WHERE Description LIKE \"%bright light%\"\n"
                 + "ORDER BY State;");
         boolean f = result.next();
         while (f)
         {
            String s1 = result.getString(1); //State
            String s2 = result.getString(2); //City
            String s3 = result.getString(3); //Description

            Object[] objs =
            {
               s1, s2, s3
            };
            table.addRow(objs);

            System.out.println(s1 + "   :   " + s2 + "   :   " + s3);

            f = result.next();
         }

      } catch (Exception ee)
      {
         System.out.println(ee);
      }

      return table;
   }
}
