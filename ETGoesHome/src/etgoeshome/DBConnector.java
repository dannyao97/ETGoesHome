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

            //System.out.println(s1 + "   :   " + s2);
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
                 + "GROUP BY City\n"
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

            //System.out.println(s1 + "   :   " + s2 + "   :   " + s3);
            f = result.next();
         }

      } catch (Exception ee)
      {
         System.out.println(ee);
      }

      return table;
   }

   public DefaultTableModel shootingsSightingsPerCity(String dbState) {

      /* Column headers */
      String colNames[] =
      {
         "State", "City", "Shootings", "Sightings"
      };
      DefaultTableModel table = new DefaultTableModel(colNames, 0);

      try
      {
         Statement statement = conn.createStatement();
         ResultSet result = statement.executeQuery(
                 "SELECT one.State, one.City, one.O, two.I\n"
                 + "FROM (SELECT State, City, COUNT(*) AS O\n"
                 + "      FROM Shootings\n"
                 + "      WHERE State = '" + dbState.toLowerCase() + "'\n"
                 + "      GROUP BY City) one,\n"
                 + "     (SELECT State, City, COUNT(*) AS I\n"
                 + "      FROM UFOSightings\n"
                 + "      WHERE State = '" + dbState.toLowerCase() + "'\n"
                 + "      GROUP BY City) two\n"
                 + "WHERE one.State = two.State AND one.City = two.City\n"
                 + "ORDER BY one.O DESC, two.I DESC;");
         boolean f = result.next();
         while (f)
         {
            String s1 = result.getString(1); //State
            String s2 = result.getString(2); //City name
            String s3 = result.getString(3); //Number of Shootings
            String s4 = result.getString(4); //Number of Sightings 

            Object[] objs =
            {
               s1, s2, s3, s4
            };
            table.addRow(objs);

            //System.out.println(s1 + "   :   " + s2 + "   :   " + s3 + "   :   " + s4);
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
                 + "GROUP BY City\n"
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

            //System.out.println(s1 + "   :   " + s2 + "   :   " + s3);
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

            //System.out.println(s1 + "   :   " + s2);
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

            //System.out.println(s1 + "   :   " + s2);
            f = result.next();
         }

      } catch (Exception ee)
      {
         System.out.println(ee);
      }

      return table;
   }

   public DefaultTableModel sightings_HealthyShootersPerCity(String dbState) {

      /* Column headers */
      String colNames[] =
      {
         "City", "Shootings with a Sighting"
      };
      DefaultTableModel table = new DefaultTableModel(colNames, 0);

      try
      {
         Statement statement = conn.createStatement();
         ResultSet result = statement.executeQuery("SELECT L1.City, COUNT(S1.Id)\n"
                 + "FROM Shootings S1, UFOSightings S2, Location L1\n"
                 + "WHERE L1.City = S1.City AND L1.State = '" + dbState + "' AND\n"
                 + "L1.City = S2.City AND L1.State = '" + dbState + "' AND S1.Mental = 'F'\n"
                 + "GROUP BY L1.City;");
         boolean f = result.next();
         while (f)
         {
            String s1 = result.getString(1); //State
            String s2 = result.getString(2); //Number of sightings/sightings

            Object[] objs =
            {
               s1, s2
            };
            table.addRow(objs);

            //System.out.println(s1 + "   :   " + s2);
            f = result.next();
         }

      } catch (Exception ee)
      {
         System.out.println(ee);
      }

      return table;
   }

   public DefaultTableModel sightings_MenAndWomenShootingsPerState() {

      /* Column headers */
      String colNames[] =
      {
         "State", "MenShot", "WomenShot"
      };
      DefaultTableModel table = new DefaultTableModel(colNames, 0);

      try
      {
         Statement statement = conn.createStatement();
         ResultSet result = statement.executeQuery("SELECT S1.State, S2.Id, S3.Id\n"
                 + "FROM UFOSightings S1, \n"
                 + "     (SELECT COUNT(S.Id) AS Id, S.State\n"
                 + "      FROM Shootings S\n"
                 + "      WHERE S.Gender = 'M'\n"
                 + "      GROUP BY S.State) S2,\n"
                 + "     (SELECT COUNT(S.Id) AS Id, S.State\n"
                 + "      FROM Shootings S\n"
                 + "      WHERE S.Gender = 'F'\n"
                 + "      GROUP BY S.State) S3\n"
                 + "WHERE S1.State = S2.State AND S1.State = S3.State\n"
                 + "GROUP BY S1.State;");
         boolean f = result.next();
         while (f)
         {
            String s1 = result.getString(1); //State
            String s2 = result.getString(2); //Number of MenShot
            String s3 = result.getString(3); //Number of WomenShot

            Object[] objs =
            {
               s1, s2, s3
            };
            table.addRow(objs);

            //System.out.println(s1 + "   :   " + s2 + "   :   " + s3);
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
                 + weapon + "', " + age + ", '" + gender + "', '" + race + "', '"
                 + city + "', '" + state + "', '" + mental + "', '" + threat + "', '"
                 + flee + "', '" + camera + "');");
         return true;
      } catch (Exception ee)
      {
         System.out.println(ee);
         return false;
      }
   }

   public boolean addSighting(String occur, String city, String state, String shape,
           int duration, String descript) {
      try
      {
         Statement statement = conn.createStatement();
         statement.executeUpdate("INSERT INTO UFOSightings(Occurence, State, \n"
                 + "City, Shape, Duration_Seconds, Description) "
                 + "VALUES('" + occur + "', '" + state + "', '" + city + "', '"
                 + shape + "', " + duration + ", '" + descript + "');");
         return true;
      } catch (Exception ee)
      {
         System.out.println(ee);
         return false;
      }
   }

   public DefaultTableModel shootingsByRace() {

      /* Column headers */
      String colNames[] =
      {
         "Race", "Shootings"
      };
      DefaultTableModel table = new DefaultTableModel(colNames, 0);

      try
      {
         Statement statement = conn.createStatement();
         ResultSet result = statement.executeQuery(
                 "SELECT Race, COUNT(*)\n"
                 + "FROM Shootings\n"
                 + "GROUP BY Race\n"
                 + "ORDER BY COUNT(*) DESC;");
         boolean f = result.next();
         while (f)
         {
            String s1 = result.getString(1); //Race
            String s2 = result.getString(2); //Number of shootings

            Object[] objs =
            {
               s1, s2
            };
            table.addRow(objs);

            //System.out.println(s1 + "   :   " + s2);
            f = result.next();
         }

      } catch (Exception ee)
      {
         System.out.println(ee);
      }

      return table;
   }

   public DefaultTableModel shootingsByGender() {

      /* Column headers */
      String colNames[] =
      {
         "Gender", "Shootings"
      };
      DefaultTableModel table = new DefaultTableModel(colNames, 0);

      try
      {
         Statement statement = conn.createStatement();
         ResultSet result = statement.executeQuery(
                 "SELECT Gender, COUNT(*)\n"
                 + "FROM Shootings\n"
                 + "GROUP BY Gender;");
         boolean f = result.next();
         while (f)
         {
            String s1 = result.getString(1); //Sex
            String s2 = result.getString(2); //Number of shootings

            Object[] objs =
            {
               s1, s2
            };
            table.addRow(objs);

            //System.out.println(s1 + "   :   " + s2);
            f = result.next();
         }

      } catch (Exception ee)
      {
         System.out.println(ee);
      }

      return table;
   }

   public DefaultTableModel shootingsByDay() {

      /* Column headers */
      String colNames[] =
      {
         "Day", "Shootings"
      };
      DefaultTableModel table = new DefaultTableModel(colNames, 0);

      try
      {
         Statement statement = conn.createStatement();
         ResultSet result = statement.executeQuery(
                 "SELECT DAYNAME(Day), COUNT(*)\n"
                 + "FROM Shootings\n"
                 + "GROUP BY DAYNAME(Day)\n"
                 + "ORDER BY DAYOFWEEK(Day);");
         boolean f = result.next();
         while (f)
         {
            String s1 = result.getString(1); //Day of week
            String s2 = result.getString(2); //Number of shootings

            Object[] objs =
            {
               s1, s2
            };
            table.addRow(objs);

            //System.out.println(s1 + "   :   " + s2);
            f = result.next();
         }

      } catch (Exception ee)
      {
         System.out.println(ee);
      }

      return table;
   }

   public DefaultTableModel shootingsByWeapon() {

      /* Column headers */
      String colNames[] =
      {
         "Weapon", "Shootings"
      };
      DefaultTableModel table = new DefaultTableModel(colNames, 0);

      try
      {
         Statement statement = conn.createStatement();
         ResultSet result = statement.executeQuery(
                 "SELECT Weapon, COUNT(*)\n"
                 + "FROM Shootings\n"
                 + "GROUP BY Weapon\n"
                 + "ORDER BY COUNT(*) DESC;");
         boolean f = result.next();
         while (f)
         {
            String s1 = result.getString(1); //Weapon
            String s2 = result.getString(2); //Number of shootings

            Object[] objs =
            {
               s1, s2
            };
            table.addRow(objs);

            //System.out.println(s1 + "   :   " + s2);
            f = result.next();
         }

      } catch (Exception ee)
      {
         System.out.println(ee);
      }

      return table;
   }

   public DefaultTableModel shootingsByCamera() {

      /* Column headers */
      String colNames[] =
      {
         "With_Body_Camera", "Without_Body_Camera"
      };
      DefaultTableModel table = new DefaultTableModel(colNames, 0);

      try
      {
         Statement statement = conn.createStatement();
         ResultSet result = statement.executeQuery(
                 "SELECT w.T, n.F\n"
                 + "FROM (SELECT COUNT(*) AS T\n"
                 + "      FROM Shootings\n"
                 + "      WHERE BodyCamera = 'T') w,\n"
                 + "     (SELECT COUNT(*) AS F\n"
                 + "      FROM Shootings\n"
                 + "      WHERE BodyCamera = 'F') n;");
         boolean f = result.next();
         while (f)
         {
            String s1 = result.getString(1); //Number of shootings with camera
            String s2 = result.getString(2); //Number of shootings without camera

            Object[] objs =
            {
               s1, s2
            };
            table.addRow(objs);

            //System.out.println(s1 + "   :   " + s2);
            f = result.next();
         }

      } catch (Exception ee)
      {
         System.out.println(ee);
      }

      return table;
   }

   public DefaultTableModel sightingsByShape() {

      /* Column headers */
      String colNames[] =
      {
         "UFO_Shape", "UFO_Sightings"
      };
      DefaultTableModel table = new DefaultTableModel(colNames, 0);

      try
      {
         Statement statement = conn.createStatement();
         ResultSet result = statement.executeQuery(
                 "SELECT Shape, COUNT(*)\n"
                 + "FROM UFOSightings\n"
                 + "GROUP BY Shape\n"
                 + "ORDER BY COUNT(*) DESC;");
         boolean f = result.next();
         while (f)
         {
            String s1 = result.getString(1); //Shape
            String s2 = result.getString(2); //Number of sightings

            Object[] objs =
            {
               s1, s2
            };
            table.addRow(objs);

            //System.out.println(s1 + "   :   " + s2);
            f = result.next();
         }

      } catch (Exception ee)
      {
         System.out.println(ee);
      }

      return table;
   }

   public DefaultTableModel longestSighting() {

      /* Column headers */
      String colNames[] =
      {
         "State", "City", "Date_and_Time", "Seconds"
      };
      DefaultTableModel table = new DefaultTableModel(colNames, 0);

      try
      {
         Statement statement = conn.createStatement();
         ResultSet result = statement.executeQuery(
                 "SELECT State, City, Occurence, Duration_Seconds\n"
                 + "FROM UFOSightings\n"
                 + "WHERE Duration_Seconds = (SELECT MAX(Duration_Seconds) AS M\n"
                 + "                          FROM UFOSightings)\n"
                 + "ORDER BY State;");
         boolean f = result.next();
         while (f)
         {
            String s1 = result.getString(1); //State
            String s2 = result.getString(2); //City name
            String s3 = result.getString(3); //Datatime
            String s4 = result.getString(4); //Seconds

            Object[] objs =
            {
               s1, s2, s3, s4
            };
            table.addRow(objs);

            //System.out.println(s1 + "   :   " + s2 + "   :   " + s3 + "   :   " + s4);
            f = result.next();
         }

      } catch (Exception ee)
      {
         System.out.println(ee);
      }

      return table;
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

            //System.out.println(s1 + "   :   " + s2);
            f = result.next();
         }

      } catch (Exception ee)
      {
         System.out.println(ee);
      }

      return table;
   }

   public DefaultTableModel sightingsByYear() {

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
                 "SELECT YEAR(Occurence), COUNT(*) AS NumSightings\n"
                 + "FROM UFOSightings\n"
                 + "GROUP BY YEAR(Occurence);");
         boolean f = result.next();
         while (f)
         {
            String s1 = result.getString(1); //Year
            String s2 = result.getString(2); //Number of UFO sightings

            Object[] objs =
            {
               s1, s2
            };
            table.addRow(objs);

            //System.out.println(s1 + "   :   " + s2);
            f = result.next();
         }

      } catch (Exception ee)
      {
         System.out.println(ee);
      }

      return table;
   }

   public DefaultTableModel sightingsByState() {

      /* Column headers */
      String colNames[] =
      {
         "State", "UFO_Sightings"
      };
      DefaultTableModel table = new DefaultTableModel(colNames, 0);

      try
      {
         Statement statement = conn.createStatement();
         ResultSet result = statement.executeQuery(
                 "SELECT State, COUNT(*) AS NumSightings\n"
                 + "FROM UFOSightings\n"
                 + "GROUP BY State;");
         boolean f = result.next();
         while (f)
         {
            String s1 = result.getString(1); //State
            String s2 = result.getString(2); //Number of UFO sightings

            Object[] objs =
            {
               s1, s2
            };
            table.addRow(objs);

            //System.out.println(s1 + "   :   " + s2);
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

            //System.out.println(s1 + "   :   " + s2 + "   :   " + s3);
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

            //System.out.println(s1 + "   :   " + s2 + "   :   " + s3);
            f = result.next();
         }

      } catch (Exception ee)
      {
         System.out.println(ee);
      }

      return table;
   }

   public DefaultTableModel searchVictim(String victim) {

      /* Column headers */
      String colNames[] =
      {
         "Name", "Day", "Age", "Gender", "Weapon", "City", "State"
      };
      DefaultTableModel table = new DefaultTableModel(colNames, 0);

      try
      {
         Statement statement = conn.createStatement();
         ResultSet result = statement.executeQuery(
                 "SELECT Name, Day, Age, Gender, Weapon, City, State\n"
                 + "FROM Shootings\n"
                 + "WHERE Name LIKE '%" + victim + "%'"
                 + "ORDER BY Name");
         boolean f = result.next();
         while (f)
         {
            String s1 = result.getString(1);
            String s2 = result.getString(2);
            String s3 = result.getString(3);
            String s4 = result.getString(4);
            String s5 = result.getString(5);
            String s6 = result.getString(6);
            String s7 = result.getString(7);

            Object[] objs =
            {
               s1, s2, s3, s4, s5, s6, s7
            };
            table.addRow(objs);

            //System.out.println(s1 + "   :   " + s2);
            f = result.next();
         }

      } catch (Exception ee)
      {
         System.out.println(ee);
      }

      return table;
   }
}
