package etgoeshome;

import java.util.Observable;
import javax.swing.DefaultListModel;

/**
 *
 * @author Daniel
 */
public class Logic extends Observable {

   protected DBConnector db;
   protected DefaultListModel listModel;

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
      listModel = db.select(state);
      notifyGUI(ENotify.TAB1);
   }
}
