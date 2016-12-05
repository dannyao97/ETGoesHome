package etgoeshome;

import java.util.Observable;

/**
 *
 * @author Daniel
 */
public class Logic extends Observable {

   public void notifyGUI() {
      notifyObservers("Called from Logic"); 
      
   }

   public Logic() {
      
   }
}
