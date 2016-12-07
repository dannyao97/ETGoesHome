package etgoeshome;

import java.util.Observable;

/**
 *
 * @author Daniel
 */
public class Logic extends Observable {

   protected DBConnector db;
   
   public void notifyGUI(Object obj) {
      setChanged();
      notifyObservers("Called from Logic");
   }

   public Logic() {
      db = new DBConnector();
   }

   public boolean login(String user, String pw) {
      return db.loginToDB(user, pw);
   }
}
