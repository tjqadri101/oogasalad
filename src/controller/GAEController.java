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
	public static final String comma = ",";
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
		String order = SaladConstants.CREATE_PLAYER + comma + SaladConstants.ID + comma+ID+comma + SaladConstants.IMAGE + comma+url+ comma + xSize + comma + 
						ySize +comma + SaladConstants.POSITION + comma + xPos + comma + yPos + comma + SaladConstants.NAME + comma+name + ",CollisionID," 
						+ colID + comma + SaladConstants.LIVES + comma + lives;
		myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	public void modifyPlayerSpeed(int ID,double xSpeed, double ySpeed){
		String order = SaladConstants.MODIFY_PLAYER + comma + SaladConstants.ID + comma+ID+comma + SaladConstants.SPEED + comma+xSpeed+ comma + ySpeed;
		myDataController.receiveOrder(order);
		System.out.println(order);
	}
 	
	
	public void createActor(int ID,String url, int xSize, int ySize, double xPos, double yPos, String name, int colID, int lives){
		String order = SaladConstants.CREATE_ACTOR + comma + SaladConstants.ID + comma+ID+comma + SaladConstants.IMAGE + comma+url+ comma + xSize + comma 
						+ ySize +comma + SaladConstants.POSITION + comma + xPos + comma + yPos + comma + SaladConstants.NAME + comma+name + ",CollisionID,"
						+ colID +  comma + SaladConstants.LIVES + comma + lives;
		myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	public void modifyActorSpeed(int ID, double xSpeed, double ySpeed){
		String order = SaladConstants.MODIFY_ACTOR + comma + SaladConstants.ID + comma+ID+comma + SaladConstants.SPEED + comma+xSpeed + comma + ySpeed;
		myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	public void modifyActorImage(String url, int xSize, int ySize){
		String order = SaladConstants.MODIFY_ACTOR_IMAGE + comma + SaladConstants.ID + comma+selectedActorID+comma + SaladConstants.IMAGE + comma+url + comma + xSize +
							comma + ySize;
		myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	public void modifyActorID(int oldID,int newID){
		String order = SaladConstants.MODIFY_ACTOR + comma + SaladConstants.ID + comma+oldID+comma + SaladConstants.CHANGE_TO_ID + comma+newID;
		myDataController.receiveOrder(order);
		System.out.println(order);
	}
 	
	public void modifyActorColID(int oldColID,int newColID){
		String order = SaladConstants.MODIFY_ACTOR + comma + SaladConstants.ID + comma+oldColID+comma + SaladConstants.CHANGE_COLLISION_ID + comma +newColID;
		myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	public void modifyActorPos(int ID,double xPos, double yPos){
		String order = SaladConstants.MODIFY_ACTOR + comma + SaladConstants.ID + comma+ID+comma + SaladConstants.POSITION + comma+xPos+ comma + yPos;
		myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	public void modifyActorExplode(int colID, int colIDTarget, String url, int xSize, int ySize){
		String order = SaladConstants.MODIFY_ACTOR + comma + SaladConstants.COLLISION_ID + comma+colID+comma + SaladConstants.EXPLODE + comma + 
						SaladConstants.EXPLODE+ comma + colIDTarget + comma + url + comma + xSize + comma + ySize;
		myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	public void modifyActorHitElimVic(int colID,int colIDTarget){
		String order = SaladConstants.MODIFY_ACTOR + comma + SaladConstants.COLLISION_ID + comma+colID+comma + SaladConstants.HITTER_ELIMINATE_VICTIM + comma + 
						SaladConstants.HITTER_ELIMINATE_VICTIM + comma + colIDTarget;
		myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	
	public void modifyActorPerishTog(int colID, int colIDTarget){
		String order = SaladConstants.MODIFY_ACTOR + comma + SaladConstants.COLLISION_ID + comma+colID+comma + SaladConstants.PERISH_TOGETHER + comma +
						SaladConstants.PERISH_TOGETHER+ comma + colIDTarget;
		myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	
	public void modifyActorStayOnTile(int colID, int tileColID){
		String order = SaladConstants.MODIFY_ACTOR + comma + SaladConstants.COLLISION_ID + comma+colID+comma + SaladConstants.STAY_ON_TILE + comma +
						SaladConstants.STAY_ON_TILE+ comma + tileColID;
		myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	
	public void modifyActorRegMove(int ID, double xSpeed, double ySpeed){
		String order = SaladConstants.MODIFY_ACTOR + comma + SaladConstants.ID + comma+ID+comma + SaladConstants.REGULAR_MOVE + comma +
						SaladConstants.REGULAR_MOVE+ comma +xSpeed + comma + ySpeed;
		myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	
	public void modifyActorRegRemove(int ID){
		String order = SaladConstants.MODIFY_ACTOR + comma + SaladConstants.ID + comma+ID+comma + SaladConstants.REGULAR_REMOVE + comma
						+SaladConstants.REGULAR_REMOVE;
		myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	
	public void modifyActorImmortal(int ID){
		String order = SaladConstants.MODIFY_ACTOR + comma + SaladConstants.ID + comma+ID+comma + SaladConstants.IMMORTAL + comma+SaladConstants.IMMORTAL;
		myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	
	public void modifyActorImmobile(int ID){
		String order = SaladConstants.MODIFY_ACTOR + comma + SaladConstants.ID + comma+ID+comma + SaladConstants.IMMOBILE + comma+SaladConstants.IMMOBILE;
		myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	
	public void modifyActorSlowShoot(int ID, String url, int xSize, int ySize, int colID, double speed){
		String order = SaladConstants.MODIFY_ACTOR + comma + SaladConstants.ID + comma+ID+comma + SaladConstants.SLOW_SHOOT + 
						comma+SaladConstants.SLOW_SHOOT + comma + url + comma +  xSize + comma + ySize + comma + colID + comma + speed;
		myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	public void modifyActorQuickShoot(int ID, String url, int xSize, int ySize, int colID, double speed, int numBullets){
		String order = SaladConstants.MODIFY_ACTOR + comma + SaladConstants.ID + comma+ID+comma + SaladConstants.QUICK_SHOOT + 
						comma+SaladConstants.QUICK_SHOOT + comma + url + comma +  xSize + comma + ySize + comma + colID + comma + speed + comma + numBullets;
		myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	public void modifyActorShowCorpse(int ID, String url, int xSize, int ySize, int time){
		String order = SaladConstants.MODIFY_ACTOR + comma + SaladConstants.ID + comma+ID+comma + SaladConstants.SHOW_CORPSE + 
						comma+SaladConstants.SHOW_CORPSE + comma + url + comma +  xSize + comma + ySize + comma + time;
		myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	public void createTile(int colID, String url){
		String order = SaladConstants.CREATE_TILE + comma + SaladConstants.COLLISION_ID + comma + colID + ",TileImage," + url;
		myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	public void setDragTile(int colID, String url, int leftXPos, int topYPos, int width, int height){
		String order = SaladConstants.SET_DRAG_TILE + comma + SaladConstants.COLLISION_ID + comma + colID + ",DragImage," + url + comma + leftXPos + comma + topYPos + comma + width + comma + height;
		myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	public void createLevel(int ID){
		String order = SaladConstants.CREATE_LEVEL + comma + SaladConstants.ID + comma+ID;
		System.out.println(order);
		myDataController.receiveOrder(order);
		
	}


	public void createScene(int levelID, int sceneID){
		String order = SaladConstants.CREATE_SCENE + comma + SaladConstants.ID + comma+levelID+comma + SaladConstants.ID + comma+sceneID;
		System.out.println(order);
		myDataController.receiveOrder(order);
		
	}


	public void modifyActorPosition(int ID, double xPos, double yPos){
		String order = SaladConstants.MODIFY_ACTOR + comma + SaladConstants.ID + comma+ID+comma + SaladConstants.POSITION + comma + xPos + comma + yPos;
	//	myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	public void modifyActorPositionOnly(double xPos, double yPos){
		modifyActorPosition(selectedActorID, xPos, yPos);
	}
	
	public void modifyScene(int ID, String function){
		String order = SaladConstants.MODIFY_SCENE + comma + SaladConstants.ID + comma+ID+ function;
		//myDataController.receiveOrder(order);
		System.out.println(order);
		
	}


	public void modifyLevel(int ID, String function){
		String order = SaladConstants.MODIFY_LEVEL + comma + SaladConstants.ID + comma+ID+ function;
		//myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	public void switchLevel(int ID){
		String order = SaladConstants.SWITCH_LEVEL + comma + SaladConstants.ID + comma+ID;
		//myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	
	public void switchScene(int levelID, int sceneID){
		String order = SaladConstants.SWITCH_SCENE + comma + SaladConstants.ID + comma+levelID+comma + SaladConstants.ID + comma+sceneID;
		System.out.println(order);
		myDataController.receiveOrder(order);
		
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

	
	
	/*public void createPlayer(int ID,String url,String name){
		String order = SaladConstants.CREATE_PLAYER + comma + SaladConstants.ID + comma+ID+comma + SaladConstants.IMAGE + comma+url+",Position,0,0,Name,"+name;
		//myDataController.receiveOrder(order);
		System.out.println(order);
	}
 	
	public void createActor(int ID,String url,String name){
		String order = SaladConstants.CREATE_ACTOR + comma + SaladConstants.ID + comma+ID+",ActorImage,"+"actor_default.png"+",100,100,Position,100.0,200.0,Name,"+name+",CollisionID,"+0+",Lives,"+1;
		System.out.println(order);
		myDataController.receiveOrder(order);
		
	}
	
	public void createLevel(int ID){
		String order = SaladConstants.CREATE_LEVEL + comma + SaladConstants.ID + comma+ID;
		System.out.println(order);
		//myDataController.receiveOrder(order);
		
	}


	public void createScene(int levelID, int sceneID){
		String order = SaladConstants.CREATE_SCENE + comma + SaladConstants.ID + comma+levelID+comma + SaladConstants.ID + comma+sceneID;
		System.out.println(order);
		//myDataController.receiveOrder(order);
		
	}


	public void modifyActorPosition(int ID, double xPos, double yPos){
		String order = SaladConstants.MODIFY_ACTOR + comma + SaladConstants.ID + comma+ID+comma + SaladConstants.POSITION + comma + xPos + comma + yPos;
	//	myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	public void modifyActorPositionOnly(double xPos, double yPos){
		modifyActorPosition(selectedActorID, xPos, yPos);
	}
	
	public void modifyActorSpeed(int ID, double xSpeed, double ySpeed){
		String order = SaladConstants.MODIFY_ACTOR + comma + SaladConstants.ID + comma+ID+comma + SaladConstants.POSITION + comma + xSpeed + comma + ySpeed;
		//myDataController.receiveOrder(order);
		System.out.println(order);
	}
	public void modifyScene(int ID, String function){
		String order = SaladConstants.MODIFY_SCENE + comma + SaladConstants.ID + comma+ID+ function;
		//myDataController.receiveOrder(order);
		System.out.println(order);
		
	}


	public void modifyLevel(int ID, String function){
		String order = SaladConstants.MODIFY_LEVEL + comma + SaladConstants.ID + comma+ID+ function;
		//myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	public void switchLevel(int ID){
		String order = SaladConstants.SWITCH_LEVEL + comma + SaladConstants.ID + comma+ID;
		//myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	
	public void switchScene(int levelID, int sceneID){
		String order = SaladConstants.SWITCH_SCENE + comma + SaladConstants.ID + comma+levelID+comma + SaladConstants.ID + comma+sceneID;
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
