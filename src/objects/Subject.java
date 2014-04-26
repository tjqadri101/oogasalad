package objects;


import java.util.Observer;

public interface Subject {

  //methods to register and unregister observers
    public void register (objects.Observer obj);
    public void unregister (objects.Observer obj);
    
  //method to notify observers of change
    public void notifyObservers ();
  //method to get updates from subject
    Object getUpdate (objects.Observer obj);
    

}


