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
      notifyGUI(ENotify.TAB1);
   }
}
