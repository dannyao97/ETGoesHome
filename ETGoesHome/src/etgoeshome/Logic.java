package etgoeshome;

import java.util.Observable;
import java.util.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Daniel
 */
public class Logic extends Observable {

   protected DBConnector db;
   protected DefaultTableModel tableData;
   protected boolean success = false;

   public void notifyGUI(Object obj) {
      setChanged();
      notifyObservers(obj);
   }

   public Logic() {
      db = new DBConnector();
   }

   public boolean login(String user, String pw) {
      return db.loginToDB(user, pw);
   }

   public void select(String state) {
      tableData = db.selectTab1(state);
      notifyGUI(ENotify.AN_STATES);
   }
   
   public void addShooting(String name, String date, String death, String weapon,
           int age, String gender, String race, String city, String state, String mental,
           String threat, String flee, String camera) {
      
      success = db.addShooting(name, date, death, weapon, age, gender, race, city, state, mental, threat, flee, camera);
      notifyGUI(ENotify.ADD_SHOOTING);
   }
}
