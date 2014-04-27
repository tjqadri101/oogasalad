package objects;

public interface Subject {

  //methods to register and unregister observers
    public void register (engineManagers.Observer obj);
    public void unregister (engineManagers.Observer obj);
    
  //method to notify observers of change
    public void notifyObservers ();
  //method to get updates from subject
    public Object getUpdate (engineManagers.Observer obj);
    

}


