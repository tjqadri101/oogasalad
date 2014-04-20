/**
 * @author Talal Javed Qadri and Nick Pan
 */

package controller;

import game_authoring_environment.ActorsPanel;
import game_authoring_environment.AttributesPanel;
import game_authoring_environment.FullView;
import game_authoring_environment.GAE;
import game_authoring_environment.LeftPanel;
import game_authoring_environment.MenuBar;

import java.awt.BorderLayout;
import java.awt.Image;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;




import engine.GameEngine;
import saladConstants.SaladConstants;
import stage.Game;

public class GAEController {
	
	private DataController myDataController;
	private GAE g;
	public static final String TITLE = "OOGASalad iTeam";
	private static FullView fv;
	private static MenuBar mb; 
	private HashMap<String, Image> availableImages;
	private GameEngine myGameEngine;
	private HashMap<String, JPanel> panelMap;
	private AttributesPanel attributesPanel;
	private ActorsPanel actorsPanel;
	private int selectedSceneID;
	private int selectedActorID;
	
	public GAEController(){
		myDataController = new DataController();
		myGameEngine = myDataController.initGameEngine(true);
		g = new GAE(this);	
		setUpVariables();
	}

	
	private void setUpVariables(){
		
		fv = g.getFullView();
		mb = g.getMenuBar();
		panelMap = fv.getMap();
		attributesPanel = fv.getAttributes();
		
	}
	
	
	public GameEngine getEngine(){
		return myGameEngine;
	}
	
	
	public void createPlayer(int ID,String url, int xSize, int ySize, double xPos, double yPos, String name, int colID, int lives){
		String order = SaladConstants.CREATE_PLAYER + SaladConstants.SEPERATER + SaladConstants.ID + SaladConstants.SEPERATER+ID+SaladConstants.SEPERATER +
						SaladConstants.IMAGE + SaladConstants.SEPERATER+url+ SaladConstants.SEPERATER + xSize + SaladConstants.SEPERATER + ySize +
						SaladConstants.SEPERATER + SaladConstants.POSITION + SaladConstants.SEPERATER + xPos + SaladConstants.SEPERATER + yPos + 
						SaladConstants.SEPERATER + SaladConstants.NAME + SaladConstants.SEPERATER+name + ",CollisionID," + colID + SaladConstants.SEPERATER + 
						SaladConstants.LIVES + SaladConstants.SEPERATER + lives;
		myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	
	public void modifyPlayerSpeed(int ID,double xSpeed, double ySpeed){
		String order = SaladConstants.MODIFY_PLAYER + SaladConstants.SEPERATER + SaladConstants.ID + SaladConstants.SEPERATER+ID+SaladConstants.SEPERATER + 
						SaladConstants.SPEED + SaladConstants.SEPERATER+xSpeed+ SaladConstants.SEPERATER + ySpeed;
		myDataController.receiveOrder(order);
		System.out.println(order);
	}
 	
	/**
     * Modify player's speed without providing player id. The default player id of 0 is used since there can only be 1 player.
     */
	public void modifyPlayerSpeedNoID(double xSpeed, double ySpeed){
		modifyPlayerSpeed(SaladConstants.PLAYER_ID,xSpeed, ySpeed);
	}
	
	
	public void createActor(int ID,String url, int xSize, int ySize, double xPos, double yPos, String name, int colID, int lives){
		String order = SaladConstants.CREATE_ACTOR + SaladConstants.SEPERATER + SaladConstants.ID + SaladConstants.SEPERATER+ID+SaladConstants.SEPERATER + 
						SaladConstants.IMAGE + SaladConstants.SEPERATER+url+ SaladConstants.SEPERATER + xSize + SaladConstants.SEPERATER + ySize +
						SaladConstants.SEPERATER + SaladConstants.POSITION + SaladConstants.SEPERATER + xPos + SaladConstants.SEPERATER + yPos + 
						SaladConstants.SEPERATER + SaladConstants.NAME + SaladConstants.SEPERATER+name + ",CollisionID,"+ colID +  SaladConstants.SEPERATER + 
						SaladConstants.LIVES + SaladConstants.SEPERATER + lives;
		myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	public void modifyActorSpeed(int ID, double xSpeed, double ySpeed){
		String order = SaladConstants.MODIFY_ACTOR + SaladConstants.SEPERATER + SaladConstants.ID + SaladConstants.SEPERATER+ID+SaladConstants.SEPERATER + 
				       SaladConstants.SPEED + SaladConstants.SEPERATER+xSpeed + SaladConstants.SEPERATER + ySpeed;
		myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	/**
     * Modify actor's speed without providing actor id. The selectedActorID is used.
     */
	public void modifyActorSpeedNoID(double xSpeed, double ySpeed){
		modifyActorSpeed(selectedActorID,xSpeed, ySpeed);
	}
	
	public void modifyActorImage(int ID, String url, int xSize, int ySize){
		String order = SaladConstants.MODIFY_ACTOR_IMAGE + SaladConstants.SEPERATER + SaladConstants.ID + SaladConstants.SEPERATER+ ID +SaladConstants.SEPERATER + 
						SaladConstants.IMAGE + SaladConstants.SEPERATER+url + SaladConstants.SEPERATER + xSize + SaladConstants.SEPERATER + ySize;
		myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	/**
     * Modify actor's image without providing actor id. The selectedActorID is used.
     */
	public void modifyActorImageNoID( String url, int xSize, int ySize){
		modifyActorImage(selectedActorID, url, xSize, ySize);
	}
	
	public void modifyActorID(int oldID,int newID){
		String order = SaladConstants.MODIFY_ACTOR + SaladConstants.SEPERATER + SaladConstants.ID + SaladConstants.SEPERATER+oldID+SaladConstants.SEPERATER +  
						SaladConstants.CHANGE_TO_ID + SaladConstants.SEPERATER+newID;
		myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	/**
     * Modify actor's ID without providing old actor id. The selectedActorID is used for the old id.
     */
	public void modifyActorIDNoOldID(int newID){
		modifyActorID(selectedActorID, newID);
	}
 	
	public void modifyActorColID(int oldColID,int newColID){
		String order = SaladConstants.MODIFY_ACTOR + SaladConstants.SEPERATER + SaladConstants.ID + SaladConstants.SEPERATER+oldColID+SaladConstants.SEPERATER + 
						SaladConstants.CHANGE_COLLISION_ID + SaladConstants.SEPERATER +newColID;
		myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	public void modifyActorPos(int ID,double xPos, double yPos){
		String order = SaladConstants.MODIFY_ACTOR + SaladConstants.SEPERATER + SaladConstants.ID + SaladConstants.SEPERATER+ID+SaladConstants.SEPERATER + 
						SaladConstants.POSITION + SaladConstants.SEPERATER+xPos+ SaladConstants.SEPERATER + yPos;
		myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	/**
     * Modify actor's position without providing actor id. The selectedActorID is used.
     */
	public void modifyActorPosNoID(double xPos, double yPos){
		modifyActorPos(selectedActorID, xPos, yPos);
	}
	
	public void modifyActorExplode(int colID, int colIDTarget, String url, int xSize, int ySize){
		String order = SaladConstants.MODIFY_ACTOR + SaladConstants.SEPERATER + SaladConstants.COLLISION_ID + SaladConstants.SEPERATER+colID+
					SaladConstants.SEPERATER + SaladConstants.EXPLODE + SaladConstants.SEPERATER + SaladConstants.EXPLODE+ SaladConstants.SEPERATER + colIDTarget + 
					SaladConstants.SEPERATER + url + SaladConstants.SEPERATER + xSize + SaladConstants.SEPERATER + ySize;
		myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	public void modifyActorHitElimVic(int colID,int colIDTarget){
		String order = SaladConstants.MODIFY_ACTOR + SaladConstants.SEPERATER + SaladConstants.COLLISION_ID + SaladConstants.SEPERATER+colID+
						SaladConstants.SEPERATER + SaladConstants.HITTER_ELIMINATE_VICTIM + SaladConstants.SEPERATER + SaladConstants.HITTER_ELIMINATE_VICTIM + 
						SaladConstants.SEPERATER + colIDTarget;
		myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	
	public void modifyActorPerishTog(int colID, int colIDTarget){
		String order = SaladConstants.MODIFY_ACTOR + SaladConstants.SEPERATER + SaladConstants.COLLISION_ID + SaladConstants.SEPERATER+colID+
						SaladConstants.SEPERATER + SaladConstants.PERISH_TOGETHER + SaladConstants.SEPERATER + SaladConstants.PERISH_TOGETHER+ SaladConstants.SEPERATER + colIDTarget;
		myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	
	public void modifyActorStayOnTile(int colID, int tileColID){
		String order = SaladConstants.MODIFY_ACTOR + SaladConstants.SEPERATER + SaladConstants.COLLISION_ID + SaladConstants.SEPERATER+colID+
						SaladConstants.SEPERATER + SaladConstants.STAY_ON_TILE + SaladConstants.SEPERATER + SaladConstants.STAY_ON_TILE+ SaladConstants.SEPERATER + 
						tileColID;
		myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	
	public void modifyActorRegMove(int ID, double xSpeed, double ySpeed){
		String order = SaladConstants.MODIFY_ACTOR + SaladConstants.SEPERATER + SaladConstants.ID + SaladConstants.SEPERATER+ID+SaladConstants.SEPERATER + 
						SaladConstants.REGULAR_MOVE + SaladConstants.SEPERATER + SaladConstants.REGULAR_MOVE+ SaladConstants.SEPERATER +xSpeed + 
						SaladConstants.SEPERATER + ySpeed;
		myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	/**
     * Modify actor's regular move property without providing actor id. The selectedActorID is used.
     */
	public void modifyActorRegMoveNoID(double xSpeed, double ySpeed){
		modifyActorRegMove(selectedActorID, xSpeed, ySpeed);
	}
	
	public void modifyActorRegRemove(int ID){
		String order = SaladConstants.MODIFY_ACTOR + SaladConstants.SEPERATER + SaladConstants.ID + SaladConstants.SEPERATER+ID+SaladConstants.SEPERATER + 
						SaladConstants.REGULAR_REMOVE + SaladConstants.SEPERATER +SaladConstants.REGULAR_REMOVE;
		myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	/**
     * Modify actor by doing a regular remove without providing actor id. The selectedActorID is used.
     */
	public void modifyActorRegRemoveNoID(){
		modifyActorRegRemove(selectedActorID);
	}
	
	public void modifyActorImmortal(int ID){
		String order = SaladConstants.MODIFY_ACTOR + SaladConstants.SEPERATER + SaladConstants.ID + SaladConstants.SEPERATER+ID+SaladConstants.SEPERATER + 
						SaladConstants.IMMORTAL + SaladConstants.SEPERATER+SaladConstants.IMMORTAL;
		myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	/**
     * Modify actor by making it immortal without providing actor id. The selectedActorID is used.
     */
	public void modifyActorImmortalNoID(){
		modifyActorImmortal(selectedActorID);
	}
	
	public void modifyActorImmobile(int ID){
		String order = SaladConstants.MODIFY_ACTOR + SaladConstants.SEPERATER + SaladConstants.ID + SaladConstants.SEPERATER+ID+SaladConstants.SEPERATER + 
						SaladConstants.IMMOBILE + SaladConstants.SEPERATER+SaladConstants.IMMOBILE;
		myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	/**
     * Modify actor by making it immobile without providing actor id. The selectedActorID is used.
     */
	public void modifyActorImmobileNoID(){
		modifyActorImmobile(selectedActorID);
	}
	
	public void modifyActorSlowShoot(int ID, String url, int xSize, int ySize, int colID, double speed){
		String order = SaladConstants.MODIFY_ACTOR + SaladConstants.SEPERATER + SaladConstants.ID + SaladConstants.SEPERATER+ID+SaladConstants.SEPERATER + 
						SaladConstants.SLOW_SHOOT + SaladConstants.SEPERATER+SaladConstants.SLOW_SHOOT + SaladConstants.SEPERATER + url + SaladConstants.SEPERATER 
						+  xSize + SaladConstants.SEPERATER + ySize + SaladConstants.SEPERATER + colID + SaladConstants.SEPERATER + speed;
		myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	/**
     * Modify actor's shoot property by slowing it without providing actor id. The selectedActorID is used.
     */
	public void modifyActorSlowShootNoID(String url, int xSize, int ySize, int colID, double speed){
		modifyActorSlowShoot(selectedActorID, url, xSize, ySize, colID, speed);
	}
	
	public void modifyActorQuickShoot(int ID, String url, int xSize, int ySize, int colID, double speed, int numBullets){
		String order = SaladConstants.MODIFY_ACTOR + SaladConstants.SEPERATER + SaladConstants.ID + SaladConstants.SEPERATER+ID+SaladConstants.SEPERATER + 
						SaladConstants.QUICK_SHOOT + SaladConstants.SEPERATER+SaladConstants.QUICK_SHOOT + SaladConstants.SEPERATER + url + 
						SaladConstants.SEPERATER +  xSize + SaladConstants.SEPERATER + ySize + SaladConstants.SEPERATER + colID + SaladConstants.SEPERATER + speed +
						SaladConstants.SEPERATER + numBullets;
		myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	/**
     * Modify actor's shoot property by quickening it without providing actor id. The selectedActorID is used.
     */
	public void modifyActorQuickShootNoID(String url, int xSize, int ySize, int colID, double speed, int numBullets){
		modifyActorQuickShoot(selectedActorID, url, xSize, ySize, colID, speed, numBullets);
	}
	
	public void modifyActorShowCorpse(int ID, String url, int xSize, int ySize, int time){
		String order = SaladConstants.MODIFY_ACTOR + SaladConstants.SEPERATER + SaladConstants.ID + SaladConstants.SEPERATER+ID+SaladConstants.SEPERATER + 
						SaladConstants.SHOW_CORPSE + SaladConstants.SEPERATER+SaladConstants.SHOW_CORPSE + SaladConstants.SEPERATER + url + 
						SaladConstants.SEPERATER +  xSize + SaladConstants.SEPERATER + ySize + SaladConstants.SEPERATER + time;
		myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	/**
     * Modify actor's corpse showing property without providing actor id. The selectedActorID is used.
     */
	public void modifyActorShowCorpseNoID(String url, int xSize, int ySize, int time){
		modifyActorShowCorpse(selectedActorID, url, xSize, ySize, time);
	}
	
	
	public void createTile(int colID, String url){
		String order = SaladConstants.CREATE_TILE + SaladConstants.SEPERATER + SaladConstants.COLLISION_ID + SaladConstants.SEPERATER + colID + ",TileImage," + url;
		myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	public void setDragTile(int colID, String url, int leftXPos, int topYPos, int width, int height){
		String order = SaladConstants.SET_DRAG_TILE + SaladConstants.SEPERATER + SaladConstants.COLLISION_ID + SaladConstants.SEPERATER + colID + ",DragImage," +
						url + SaladConstants.SEPERATER + leftXPos + SaladConstants.SEPERATER + topYPos + SaladConstants.SEPERATER + width + SaladConstants.SEPERATER + 
						height;
		myDataController.receiveOrder(order);
		System.out.println(order);
	}

	

	public DataController getDataController(){
		return myDataController;
	}
	
	public void updateSelectedSceneID(int newID){
		selectedSceneID = newID;
	}
	
	public void updateSelectedActorID(int newID){
		selectedActorID = newID;
	}
	

	public void updateActorImage(String imageURL, String name){
		ActorsPanel ap= (ActorsPanel) panelMap.get(SaladConstants.ACTOR_PANEL);
		ap.setActorImage(selectedActorID, imageURL, name);
	}

	public void setActorImageURL(String URL){
		String xval = "100";
		String yval = "100";
		String order = SaladConstants.MODIFY_ACTOR_IMAGE + SaladConstants.SEPERATER + selectedActorID + SaladConstants.SEPERATER + SaladConstants.IMAGE + SaladConstants.SEPERATER + URL + SaladConstants.SEPERATER + xval + SaladConstants.SEPERATER + yval;
		myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	public List<String> getAttributes(){
		List<String> s = myDataController.getActorInfo(selectedActorID);
		return s;
	}
	
	
	/*public void createPlayer(int ID,String url,String name){
		String order = SaladConstants.CREATE_PLAYER + SaladConstants.SEPERATER + SaladConstants.ID + SaladConstants.SEPERATER+ID+SaladConstants.SEPERATER + SaladConstants.IMAGE + SaladConstants.SEPERATER+url+",Position,0,0,Name,"+name;
		//myDataController.receiveOrder(order);
		System.out.println(order);
	}
 	
	public void createActor(int ID,String url,String name){
		String order = SaladConstants.CREATE_ACTOR + SaladConstants.SEPERATER + SaladConstants.ID + SaladConstants.SEPERATER+ID+",ActorImage,"+"actor_default.png"+",100,100,Position,100.0,200.0,Name,"+name+",CollisionID,"+0+",Lives,"+1;
		System.out.println(order);
		myDataController.receiveOrder(order);
		
	}
	
	public void createLevel(int ID){
		String order = SaladConstants.CREATE_LEVEL + SaladConstants.SEPERATER + SaladConstants.ID + SaladConstants.SEPERATER+ID;
		System.out.println(order);
		//myDataController.receiveOrder(order);
		
	}


	public void createScene(int levelID, int sceneID){
		String order = SaladConstants.CREATE_SCENE + SaladConstants.SEPERATER + SaladConstants.ID + SaladConstants.SEPERATER+levelID+SaladConstants.SEPERATER + SaladConstants.ID + SaladConstants.SEPERATER+sceneID;
		System.out.println(order);
		//myDataController.receiveOrder(order);
		
	}


	public void modifyActorPosition(int ID, double xPos, double yPos){
		String order = SaladConstants.MODIFY_ACTOR + SaladConstants.SEPERATER + SaladConstants.ID + SaladConstants.SEPERATER+ID+SaladConstants.SEPERATER + SaladConstants.POSITION + SaladConstants.SEPERATER + xPos + SaladConstants.SEPERATER + yPos;
	//	myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	public void modifyActorPositionOnly(double xPos, double yPos){
		modifyActorPosition(selectedActorID, xPos, yPos);
	}
	
	public void modifyActorSpeed(int ID, double xSpeed, double ySpeed){
		String order = SaladConstants.MODIFY_ACTOR + SaladConstants.SEPERATER + SaladConstants.ID + SaladConstants.SEPERATER+ID+SaladConstants.SEPERATER + SaladConstants.POSITION + SaladConstants.SEPERATER + xSpeed + SaladConstants.SEPERATER + ySpeed;
		//myDataController.receiveOrder(order);
		System.out.println(order);
	}
	public void modifyScene(int ID, String function){
		String order = SaladConstants.MODIFY_SCENE + SaladConstants.SEPERATER + SaladConstants.ID + SaladConstants.SEPERATER+ID+ function;
		//myDataController.receiveOrder(order);
		System.out.println(order);
		
	}


	public void modifyLevel(int ID, String function){
		String order = SaladConstants.MODIFY_LEVEL + SaladConstants.SEPERATER + SaladConstants.ID + SaladConstants.SEPERATER+ID+ function;
		//myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	public void switchLevel(int ID){
		String order = SaladConstants.SWITCH_LEVEL + SaladConstants.SEPERATER + SaladConstants.ID + SaladConstants.SEPERATER+ID;
		//myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	
	public void switchScene(int levelID, int sceneID){
		String order = SaladConstants.SWITCH_SCENE + SaladConstants.SEPERATER + SaladConstants.ID + SaladConstants.SEPERATER+levelID+SaladConstants.SEPERATER + SaladConstants.ID + SaladConstants.SEPERATER+sceneID;
		System.out.println(order);
		//myDataController.receiveOrder(order);
		
	}
	
	public void updateAttributesActorInfo(){
		attributesPanel.updateActorInfo(selectedActorID);
	}
	
	public void switchActiveAttributesTab(int index){
		attributesPanel.setTab(index);
	}


	public void deleteActor(int ID){
		String order = "DeleteActor,ID,"+ID;
		System.out.println(order);
		//myDataController.receiveOrder(order);				
	}
	
	public void deleteScene(int sceneID){
		String order = "ModifyScene,ID,"+sceneID+",DeleteScene";
		System.out.println(order);
		//myDataController.receiveOrder(order);	
	}

	

	public DataController getDataController(){
		return myDataController;
	}
	
	public void updateSelectedSceneID(int newID){
		selectedSceneID = newID;
	}
	
	public void updateSelectedActorID(int newID){
		selectedActorID = newID;
	}
	
	public void updateActorImage(String imageURL,String name){
		ActorsPanel ap= (ActorsPanel) panelMap.get(SaladConstants.ACTOR_PANEL);
		ap.setActorImage(selectedActorID, imageURL, name);
	}
	
	public void setActorImageURL(String URL){
		
	}

	*/
}
