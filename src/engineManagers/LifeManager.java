package engineManagers;

import saladConstants.SaladConstants;
/**
 * Manage the overall lives of the Player throughout the whole Game
 * @author Main Justin (Zihao) Zhang
 *
 */
public class LifeManager {
	
	protected int myInitLives;
	protected int myLives;
	protected boolean myRestoreLevel;
	
	public LifeManager(){
		myInitLives = SaladConstants.DEFAULT_INIT_LIVES;
		myLives = myInitLives;
	}
	
	public void setInitLives(int lives){
		myInitLives = lives;
	}
	
	public void restoreLifeByLevel(boolean ifRestore){
		myRestoreLevel = ifRestore;
	}
	
	public int getCurrentLives(){
		return myLives;
	}

	public void updateLevelDoneLives(){
		if(myRestoreLevel){
			myLives = myInitLives;
		}
	}
}
