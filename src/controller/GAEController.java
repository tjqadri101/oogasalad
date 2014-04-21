/**
 * @author Talal Javed Qadri and Nick Pan and Kat Krieger
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
import java.io.IOException;
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
	private int playerID;
	
	private static final boolean DEBUG = true;
	
	public GAEController(){
		playerID = SaladConstants.PLAYER_ID;
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
		//test code below
		//setDragTile(2,"brick.png");		
		
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
		if (!DEBUG) if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	
	public void modifyPlayerSpeed(int ID,double xSpeed, double ySpeed){
		String order = SaladConstants.MODIFY_PLAYER + SaladConstants.SEPERATER + SaladConstants.ID + SaladConstants.SEPERATER+ID+SaladConstants.SEPERATER + 
						SaladConstants.SPEED + SaladConstants.SEPERATER+xSpeed+ SaladConstants.SEPERATER + ySpeed;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}
 	
	/**
     * Modify player's speed without providing player id. The already set player ID is used.
     */
	public void modifyPlayerSpeedNoID(double xSpeed, double ySpeed){
		modifyPlayerSpeed(playerID,xSpeed, ySpeed);
	}
	
	public void modifyPlayerImage(int ID, String url, int xSize, int ySize){
		String order = SaladConstants.MODIFY_PLAYER_IMAGE + SaladConstants.SEPERATER + SaladConstants.ID + SaladConstants.SEPERATER+ ID +SaladConstants.SEPERATER + 
						SaladConstants.IMAGE + SaladConstants.SEPERATER+url + SaladConstants.SEPERATER + xSize + SaladConstants.SEPERATER + ySize;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	/**
     * Modify player's image without providing player id. The already specified player ID is used.
     */
	public void modifyPlayerImageNoID( String url, int xSize, int ySize){
		modifyPlayerImage(playerID, url, xSize, ySize);
	}
	
	public void modifyPlayerID(int oldID,int newID){
		String order = SaladConstants.MODIFY_PLAYER + SaladConstants.SEPERATER + SaladConstants.ID + SaladConstants.SEPERATER+oldID+SaladConstants.SEPERATER +  
						SaladConstants.CHANGE_TO_ID + SaladConstants.SEPERATER+newID;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	/**
     * Modify player's ID without providing old player id. The already specified player ID is used for the old id.
     */
	public void modifyPlayerIDNoOldID(int newID){
		modifyPlayerID(playerID, newID);
	}
	
	public void modifyPlayerColID(int oldColID,int newColID){
		String order = SaladConstants.MODIFY_PLAYER + SaladConstants.SEPERATER + SaladConstants.ID + SaladConstants.SEPERATER+oldColID+SaladConstants.SEPERATER + 
						SaladConstants.CHANGE_COLLISION_ID + SaladConstants.SEPERATER +newColID;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	public void modifyPlayerPos(int ID,double xPos, double yPos){
		String order = SaladConstants.MODIFY_PLAYER + SaladConstants.SEPERATER + SaladConstants.ID + SaladConstants.SEPERATER+ID+SaladConstants.SEPERATER + 
						SaladConstants.POSITION + SaladConstants.SEPERATER+xPos+ SaladConstants.SEPERATER + yPos;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	/**
     * Modify player's position without providing player id. The already specified playerID is used.
     */
	public void modifyPlayerPosNoID(double xPos, double yPos){
		modifyPlayerPos(playerID, xPos, yPos);
	}
	
	public void modifyPlayerExplode(int colID, int colIDTarget, String url, int xSize, int ySize){
		String order = SaladConstants.MODIFY_PLAYER + SaladConstants.SEPERATER + SaladConstants.COLLISION_ID + SaladConstants.SEPERATER+colID+
					SaladConstants.SEPERATER + SaladConstants.EXPLODE + SaladConstants.SEPERATER + SaladConstants.EXPLODE+ SaladConstants.SEPERATER + colIDTarget + 
					SaladConstants.SEPERATER + url + SaladConstants.SEPERATER + xSize + SaladConstants.SEPERATER + ySize;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	public void modifyPlayerHitElimVic(int colID,int colIDTarget){
		String order = SaladConstants.MODIFY_PLAYER + SaladConstants.SEPERATER + SaladConstants.COLLISION_ID + SaladConstants.SEPERATER+colID+
						SaladConstants.SEPERATER + SaladConstants.HITTER_ELIMINATE_VICTIM + SaladConstants.SEPERATER + SaladConstants.HITTER_ELIMINATE_VICTIM + 
						SaladConstants.SEPERATER + colIDTarget;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	
	public void modifyPlayerPerishTog(int colID, int colIDTarget){
		String order = SaladConstants.MODIFY_PLAYER + SaladConstants.SEPERATER + SaladConstants.COLLISION_ID + SaladConstants.SEPERATER+colID+
						SaladConstants.SEPERATER + SaladConstants.PERISH_TOGETHER + SaladConstants.SEPERATER + SaladConstants.PERISH_TOGETHER+ SaladConstants.SEPERATER + colIDTarget;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	public void modifyPlayerCannotCollide(int colID, int tileColID){
		String order = SaladConstants.MODIFY_PLAYER + SaladConstants.SEPERATER + SaladConstants.COLLISION_ID + SaladConstants.SEPERATER+colID+
						SaladConstants.SEPERATER + SaladConstants.CAN_NOT_COLLIDE + SaladConstants.SEPERATER + SaladConstants.CAN_NOT_COLLIDE;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	public void modifyPlayerStayOnTile(int colID, int tileColID){
		String order = SaladConstants.MODIFY_PLAYER + SaladConstants.SEPERATER + SaladConstants.COLLISION_ID + SaladConstants.SEPERATER+colID+
						SaladConstants.SEPERATER + SaladConstants.STAY_ON_TILE + SaladConstants.SEPERATER + SaladConstants.STAY_ON_TILE+ SaladConstants.SEPERATER + 
						tileColID;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
/*	
	public void modifyPlayerRegMove(int ID, double xSpeed, double ySpeed){
		String order = SaladConstants.MODIFY_PLAYER + SaladConstants.SEPERATER + SaladConstants.ID + SaladConstants.SEPERATER+ID+SaladConstants.SEPERATER + 
						SaladConstants.REGULAR_MOVE + SaladConstants.SEPERATER + SaladConstants.REGULAR_MOVE+ SaladConstants.SEPERATER +xSpeed + 
						SaladConstants.SEPERATER + ySpeed;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	*//**
     * Modify player's regular move property without providing player id. The already specified playerID is used.
     *//*
	public void modifyPlayerRegMoveNoID(double xSpeed, double ySpeed){
		modifyPlayerRegMove(playerID, xSpeed, ySpeed);
	}
	*/
	public void modifyPlayerRegRemove(int ID){
		String order = SaladConstants.MODIFY_PLAYER + SaladConstants.SEPERATER + SaladConstants.ID + SaladConstants.SEPERATER+ID+SaladConstants.SEPERATER + 
						SaladConstants.REGULAR_REMOVE + SaladConstants.SEPERATER +SaladConstants.REGULAR_REMOVE;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	/**
     * Modify player by doing a regular remove without providing player id. The already specified playerID is used.
     */
	public void modifyPlayerRegRemoveNoID(){
		modifyPlayerRegRemove(playerID);
	}
	
	public void modifyPlayerImmortal(int ID){
		String order = SaladConstants.MODIFY_PLAYER + SaladConstants.SEPERATER + SaladConstants.ID + SaladConstants.SEPERATER+ID+SaladConstants.SEPERATER + 
						SaladConstants.IMMORTAL + SaladConstants.SEPERATER+SaladConstants.IMMORTAL;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	/**
     * Modify player by making it immortal without providing player id. The already specified playerID is used.
     */
	public void modifyPlayerImmortalNoID(){
		modifyPlayerImmortal(playerID);
	}
	
/*	public void modifyPlayerImmobile(int ID){
		String order = SaladConstants.MODIFY_PLAYER + SaladConstants.SEPERATER + SaladConstants.ID + SaladConstants.SEPERATER+ID+SaladConstants.SEPERATER + 
						SaladConstants.IMMOBILE + SaladConstants.SEPERATER+SaladConstants.IMMOBILE;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	*//**
     * Modify player by making it immobile without providing player id. The already specified playerID is used.
     *//*
	public void modifyPlayerImmobileNoID(){
		modifyPlayerImmobile(playerID);
	}*/
	
	public void modifyPlayerSlowShoot(int ID, String url, int xSize, int ySize, int colID, double speed){
		String order = SaladConstants.MODIFY_PLAYER + SaladConstants.SEPERATER + SaladConstants.ID + SaladConstants.SEPERATER+ID+SaladConstants.SEPERATER + 
						SaladConstants.SLOW_SHOOT + SaladConstants.SEPERATER+SaladConstants.SLOW_SHOOT + SaladConstants.SEPERATER + url + SaladConstants.SEPERATER 
						+  xSize + SaladConstants.SEPERATER + ySize + SaladConstants.SEPERATER + colID + SaladConstants.SEPERATER + speed;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	/**
     * Modify player's shoot property by slowing it without providing player id. The already specified playerID is used.
     */
	public void modifyPlayerSlowShootNoID(String url, int xSize, int ySize, int colID, double speed){
		modifyPlayerSlowShoot(playerID, url, xSize, ySize, colID, speed);
	}
	
	public void modifyPlayerQuickShoot(int ID, String url, int xSize, int ySize, int colID, double speed, int numBullets){
		String order = SaladConstants.MODIFY_PLAYER + SaladConstants.SEPERATER + SaladConstants.ID + SaladConstants.SEPERATER+ID+SaladConstants.SEPERATER + 
						SaladConstants.QUICK_SHOOT + SaladConstants.SEPERATER+SaladConstants.QUICK_SHOOT + SaladConstants.SEPERATER + url + 
						SaladConstants.SEPERATER +  xSize + SaladConstants.SEPERATER + ySize + SaladConstants.SEPERATER + colID + SaladConstants.SEPERATER + speed +
						SaladConstants.SEPERATER + numBullets;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	/**
     * Modify player's shoot property by quickening it without providing player id. The already specified playerID is used.
     */
	public void modifyPlayerQuickShootNoID(String url, int xSize, int ySize, int colID, double speed, int numBullets){
		modifyPlayerQuickShoot(playerID, url, xSize, ySize, colID, speed, numBullets);
	}
	
	public void modifyPlayerShowCorpse(int ID, String url, int xSize, int ySize, int time){
		String order = SaladConstants.MODIFY_PLAYER + SaladConstants.SEPERATER + SaladConstants.ID + SaladConstants.SEPERATER+ID+SaladConstants.SEPERATER + 
						SaladConstants.SHOW_CORPSE + SaladConstants.SEPERATER+SaladConstants.SHOW_CORPSE + SaladConstants.SEPERATER + url + 
						SaladConstants.SEPERATER +  xSize + SaladConstants.SEPERATER + ySize + SaladConstants.SEPERATER + time;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	/**
     * Modify player's corpse showing property without providing player id. The already specified playerID is used.
     */
	public void modifyPlayerShowCorpseNoID(String url, int xSize, int ySize, int time){
		modifyPlayerShowCorpse(playerID, url, xSize, ySize, time);
	}
	
	public void modifyPlayerJumpBehavior(int ID, double magnitude, double numJumps){
		String order = SaladConstants.MODIFY_PLAYER + SaladConstants.SEPERATER + SaladConstants.ID + SaladConstants.SEPERATER+ID+SaladConstants.SEPERATER + 
				SaladConstants.JUMP + SaladConstants.SEPERATER+SaladConstants.JUMP + SaladConstants.SEPERATER + magnitude + 
				SaladConstants.SEPERATER +  numJumps;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	/**
     * Modify player's jump behavior without providing player id. The already specified playerID is used.
     */
	public void modifyPlayerJumpBehaviorNoID( double magnitude, double numJumps){
		modifyPlayerJumpBehavior(playerID, magnitude, numJumps);
	}
	public void modifyPlayerKeyUp(int ID, int key){
		String order = SaladConstants.MODIFY_PLAYER + SaladConstants.SEPERATER + SaladConstants.ID + SaladConstants.SEPERATER+ID+SaladConstants.SEPERATER + 
				SaladConstants.SET_KEY + SaladConstants.SEPERATER+ key + SaladConstants.SEPERATER + SaladConstants.MOVE_UP;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	
	/**
     * Modify player's moveUp key without providing player id. The already specified playerID is used for the id.
     */
	public void modifyPlayerKeyUpNoID(int key){
		modifyPlayerKeyUp(playerID, key);
	}
	
	public void modifyPlayerKeyDown(int ID, int key){
		String order = SaladConstants.MODIFY_PLAYER + SaladConstants.SEPERATER + SaladConstants.ID + SaladConstants.SEPERATER+ID+SaladConstants.SEPERATER + 
				SaladConstants.SET_KEY + SaladConstants.SEPERATER+ key + SaladConstants.SEPERATER + SaladConstants.MOVE_DOWN;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	
	/**
     * Modify player's moveDown key without providing player id. The already specified playerID is used for the id.
     */
	public void modifyPlayerKeyDownNoID(int key){
		modifyPlayerKeyDown(playerID, key);
	}
	
	public void modifyPlayerKeyLeft(int ID, int key){
		String order = SaladConstants.MODIFY_PLAYER + SaladConstants.SEPERATER + SaladConstants.ID + SaladConstants.SEPERATER+ID+SaladConstants.SEPERATER + 
				SaladConstants.SET_KEY + SaladConstants.SEPERATER+ key + SaladConstants.SEPERATER + SaladConstants.MOVE_LEFT;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	
	/**
     * Modify player's moveLeft key without providing player id. The already specified playerID is used for the id.
     */
	public void modifyPlayerKeyLeftNoID(int key){
		modifyPlayerKeyLeft(playerID, key);
	}
	
	public void modifyPlayerKeyRight(int ID, int key){
		String order = SaladConstants.MODIFY_PLAYER + SaladConstants.SEPERATER + SaladConstants.ID + SaladConstants.SEPERATER+ID+SaladConstants.SEPERATER + 
				SaladConstants.SET_KEY + SaladConstants.SEPERATER+ key + SaladConstants.SEPERATER + SaladConstants.MOVE_RIGHT;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	
	/**
     * Modify player's moveRight key without providing player id. The already specified playerID is used for the id.
     */
	public void modifyPlayerKeyRighttNoID(int key){
		modifyPlayerKeyRight(playerID, key);
	}
	
	public void modifyPlayerKeyShoot(int ID, int key){
		String order = SaladConstants.MODIFY_PLAYER + SaladConstants.SEPERATER + SaladConstants.ID + SaladConstants.SEPERATER+ID+SaladConstants.SEPERATER + 
				SaladConstants.SET_KEY + SaladConstants.SEPERATER+ key + SaladConstants.SEPERATER + SaladConstants.SHOOT_KEY;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	
	/**
     * Modify player's shoot key without providing player id. The already specified playerID is used for the id.
     */
	public void modifyPlayerKeyShoottNoID(int key){
		modifyPlayerKeyShoot(playerID, key);
	}
	
	public void modifyPlayerKeyJump(int ID, int key){
		String order = SaladConstants.MODIFY_PLAYER + SaladConstants.SEPERATER + SaladConstants.ID + SaladConstants.SEPERATER+ID+SaladConstants.SEPERATER + 
				SaladConstants.SET_KEY + SaladConstants.SEPERATER+ key + SaladConstants.SEPERATER + SaladConstants.JUMP_KEY;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	
	/**
     * Modify player's jump key without providing player id. The already specified playerID is used for the id.
     */
	public void modifyPlayerKeyJumpNoID(int key){
		modifyPlayerKeyJump(playerID, key);
	}
	
	
	public void deletePlayer(int ID){
		String order = SaladConstants.DELETE_PLAYER + SaladConstants.SEPERATER + SaladConstants.ID + SaladConstants.SEPERATER + ID;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	/**
     * Delete player without providing player id. The already specified playerID is used for the id.
     */
	public void deletePlayerNoID(){
		deletePlayer(playerID);
	}
	
	
	
	public void createActor(int ID,String url, int xSize, int ySize, double xPos, double yPos, String name, int colID, int lives){
		String order = SaladConstants.CREATE_ACTOR + SaladConstants.SEPERATER + SaladConstants.ID + SaladConstants.SEPERATER+ID+SaladConstants.SEPERATER + 
						SaladConstants.IMAGE + SaladConstants.SEPERATER+url+ SaladConstants.SEPERATER + xSize + SaladConstants.SEPERATER + ySize +
						SaladConstants.SEPERATER + SaladConstants.POSITION + SaladConstants.SEPERATER + xPos + SaladConstants.SEPERATER + yPos + 
						SaladConstants.SEPERATER + SaladConstants.NAME + SaladConstants.SEPERATER+name + ",CollisionID,"+ colID +  SaladConstants.SEPERATER + 
						SaladConstants.LIVES + SaladConstants.SEPERATER + lives;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	public void modifyActorSpeed(int ID, double xSpeed, double ySpeed){
		String order = SaladConstants.MODIFY_ACTOR + SaladConstants.SEPERATER + SaladConstants.ID + SaladConstants.SEPERATER+ID+SaladConstants.SEPERATER + 
				       SaladConstants.SPEED + SaladConstants.SEPERATER+xSpeed + SaladConstants.SEPERATER + ySpeed;
		if (!DEBUG) myDataController.receiveOrder(order);
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
		if (!DEBUG) myDataController.receiveOrder(order);
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
		if (!DEBUG) myDataController.receiveOrder(order);
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
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	public void modifyActorPos(int ID,double xPos, double yPos){
		String order = SaladConstants.MODIFY_ACTOR + SaladConstants.SEPERATER + SaladConstants.ID + SaladConstants.SEPERATER+ID+SaladConstants.SEPERATER + 
						SaladConstants.POSITION + SaladConstants.SEPERATER+xPos+ SaladConstants.SEPERATER + yPos;
		if (!DEBUG) myDataController.receiveOrder(order);
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
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	public void modifyActorHitElimVic(int colID,int colIDTarget){
		String order = SaladConstants.MODIFY_ACTOR + SaladConstants.SEPERATER + SaladConstants.COLLISION_ID + SaladConstants.SEPERATER+colID+
						SaladConstants.SEPERATER + SaladConstants.HITTER_ELIMINATE_VICTIM + SaladConstants.SEPERATER + SaladConstants.HITTER_ELIMINATE_VICTIM + 
						SaladConstants.SEPERATER + colIDTarget;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	
	public void modifyActorPerishTog(int colID, int colIDTarget){
		String order = SaladConstants.MODIFY_ACTOR + SaladConstants.SEPERATER + SaladConstants.COLLISION_ID + SaladConstants.SEPERATER+colID+
						SaladConstants.SEPERATER + SaladConstants.PERISH_TOGETHER + SaladConstants.SEPERATER + SaladConstants.PERISH_TOGETHER+ SaladConstants.SEPERATER + colIDTarget;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	
	public void modifyActorStayOnTile(int colID, int tileColID){
		String order = SaladConstants.MODIFY_ACTOR + SaladConstants.SEPERATER + SaladConstants.COLLISION_ID + SaladConstants.SEPERATER+colID+
						SaladConstants.SEPERATER + SaladConstants.STAY_ON_TILE + SaladConstants.SEPERATER + SaladConstants.STAY_ON_TILE+ SaladConstants.SEPERATER + 
						tileColID;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	
	public void modifyActorRegMove(int ID, double xSpeed, double ySpeed){
		String order = SaladConstants.MODIFY_ACTOR + SaladConstants.SEPERATER + SaladConstants.ID + SaladConstants.SEPERATER+ID+SaladConstants.SEPERATER + 
						SaladConstants.REGULAR_MOVE + SaladConstants.SEPERATER + SaladConstants.REGULAR_MOVE+ SaladConstants.SEPERATER +xSpeed + 
						SaladConstants.SEPERATER + ySpeed;
		if (!DEBUG) myDataController.receiveOrder(order);
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
		if (!DEBUG) myDataController.receiveOrder(order);
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
		if (!DEBUG) myDataController.receiveOrder(order);
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
		if (!DEBUG) myDataController.receiveOrder(order);
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
		if (!DEBUG) myDataController.receiveOrder(order);
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
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	/**
     * Modify actor's shoot property by quickening it without providing actor id. The selectedActorID is used.
     */
	public void modifyActorQuickShootNoID(String url, int xSize, int ySize, int colID, double speed, int numBullets){
		modifyActorQuickShoot(selectedActorID, url, xSize, ySize, colID, speed, numBullets);
	}
	
	/*public void modifyActorShowCorpse(int ID, String url, int xSize, int ySize, int time){
		String order = SaladConstants.MODIFY_ACTOR + SaladConstants.SEPERATER + SaladConstants.ID + SaladConstants.SEPERATER+ID+SaladConstants.SEPERATER + 
						SaladConstants.SHOW_CORPSE + SaladConstants.SEPERATER+SaladConstants.SHOW_CORPSE + SaladConstants.SEPERATER + url + 
						SaladConstants.SEPERATER +  xSize + SaladConstants.SEPERATER + ySize + SaladConstants.SEPERATER + time;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	*//**
     * Modify actor's corpse showing property without providing actor id. The selectedActorID is used.
     *//*
	public void modifyActorShowCorpseNoID(String url, int xSize, int ySize, int time){
		modifyActorShowCorpse(selectedActorID, url, xSize, ySize, time);
	}
	*/
	
	public void setDragTile(int colID, String url){
		String order = SaladConstants.SET_DRAG_TILE + SaladConstants.SEPERATER + SaladConstants.COLLISION_ID + SaladConstants.SEPERATER + colID +
				SaladConstants.SEPERATER + SaladConstants.DRAG_IMAGE + SaladConstants.SEPERATER + url;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	public void createTile(int colID, String url, int leftXPos, int topYPos, int width, int height){
		String order = SaladConstants.CREATE_TILE + SaladConstants.SEPERATER + SaladConstants.COLLISION_ID + SaladConstants.SEPERATER + colID
				+ SaladConstants.SEPERATER +SaladConstants.TILE_IMAGE + SaladConstants.SEPERATER +url + SaladConstants.SEPERATER + leftXPos 
				+ SaladConstants.SEPERATER + topYPos + SaladConstants.SEPERATER + width + SaladConstants.SEPERATER + height;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}

	public void deleteActor(int ID){
		String order = SaladConstants.DELETE_ACTOR + SaladConstants.SEPERATER + SaladConstants.ID + SaladConstants.SEPERATER + ID;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	/**
     * Delete actor without providing actor id. The selectedActorID is used.
     */
	public void deleteActorNoID(){
		deleteActor(selectedActorID);
	}
	
	public void deleteLevel(int levelID){
		String order = SaladConstants.DELETE_LEVEL + SaladConstants.SEPERATER + SaladConstants.ID + SaladConstants.SEPERATER+levelID;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}

	public void resetLevelID(int oldLevelID, int newLevelID){
		String order = SaladConstants.RESET_LEVEL_ID + SaladConstants.SEPERATER + SaladConstants.ID + SaladConstants.SEPERATER+oldLevelID + SaladConstants.SEPERATER
				+ SaladConstants.ID + SaladConstants.SEPERATER+newLevelID;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}

	
	public void createLevel(int levelID){
		String order = SaladConstants.CREATE_LEVEL + SaladConstants.SEPERATER + SaladConstants.ID + SaladConstants.SEPERATER+levelID;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}

	public void switchScene(int levelID, int sceneID){
		String order = SaladConstants.SWITCH_SCENE + SaladConstants.SEPERATER + SaladConstants.ID + SaladConstants.SEPERATER+levelID+SaladConstants.SEPERATER + 
						SaladConstants.ID + SaladConstants.SEPERATER+sceneID;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	public void deleteScene(int levelID, int sceneID){
		String order = SaladConstants.DELETE_SCENE + SaladConstants.SEPERATER + SaladConstants.ID + SaladConstants.SEPERATER+levelID+SaladConstants.SEPERATER + 
				SaladConstants.ID + SaladConstants.SEPERATER+sceneID;
		if (!DEBUG) myDataController.receiveOrder(order);	
		System.out.println(order);
	}
	
	public void createScene(int levelID, int sceneID){
		String order = SaladConstants.CREATE_SCENE + SaladConstants.SEPERATER + SaladConstants.ID + SaladConstants.SEPERATER+levelID+SaladConstants.SEPERATER + 
				SaladConstants.ID + SaladConstants.SEPERATER+sceneID;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	public void modifySceneBackground(String path){
		String order = SaladConstants.MODIFY_SCENE + SaladConstants.SEPERATER + SaladConstants.BACKGROUND + SaladConstants.SEPERATER + path;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}
		
	
	public void modifyScenePlayerInitPos(double xPos, double yPos){
		String order = SaladConstants.MODIFY_SCENE + SaladConstants.SEPERATER + SaladConstants.PLAYER_INIT_POS + SaladConstants.SEPERATER + xPos + 
						SaladConstants.SEPERATER + yPos;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	
	public void createGoalCollision(int bossID){
		String order = SaladConstants.CREATE_GOAL + SaladConstants.SEPERATER +  SaladConstants.WIN_BY_COLLISION + SaladConstants.SEPERATER + 
				SaladConstants.WIN_BY_COLLISION + SaladConstants.SEPERATER + bossID;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}	
	
	public void createGoalTime(int time){
		String order = SaladConstants.CREATE_GOAL + SaladConstants.SEPERATER +  SaladConstants.WIN_BY_TIME + SaladConstants.SEPERATER + 
				SaladConstants.WIN_BY_TIME + SaladConstants.SEPERATER + time;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	public void createGoalTileCollision(int curPlayerID, int leftXPos, int topYPos, int width, int height){
		String order = SaladConstants.CREATE_GOAL + SaladConstants.SEPERATER +  SaladConstants.WIN_BY_TILE_COLLISION+ SaladConstants.SEPERATER + 
				curPlayerID + SaladConstants.SEPERATER + leftXPos + SaladConstants.SEPERATER + topYPos + SaladConstants.SEPERATER + width + SaladConstants.SEPERATER
				+ height;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	/**
     * Create tile collision winning goal without providing player id. The already specified playerID is used.
     */
	public void createGoalTileCollisionNoID(int leftXPos, int topYPos, int width, int height){
		createGoalTileCollision(playerID,leftXPos, topYPos, width,height);
	}
	
	public void modifyGravityMagnitude(double magnitude){
		String order = SaladConstants.MODIFY_GRAVITY + SaladConstants.SEPERATER +  SaladConstants.GRAVITY_MAGNITUDE+ SaladConstants.SEPERATER + magnitude;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}

	public List<String> getAttributes(){
		List<String> s = myDataController.getActorInfo(selectedActorID);
		return s;
	}

	public void updateAttributesActorInfo(){
		attributesPanel.updateActorInfo(selectedActorID);
	}
	
	public void switchActiveAttributesTab(int index){
		attributesPanel.setTab(index);
	}
		
	
	public DataController getDataController(){
		return myDataController;
	}
	
	public void updateSelectedSceneID(int newID){
		selectedSceneID = newID;
	}
	
	public void updatePlayerID(int newID){
		playerID = newID;
	}
	
	public void updateSelectedActorID(int newID){
		selectedActorID = newID;
	}
	
	//Kat's method for testing
	/**Modify the thumbnail in Actor panel*/
	public void updateActorImage(String imageURL, String name){
		ActorsPanel ap= (ActorsPanel) panelMap.get(SaladConstants.ACTOR_PANEL);
		ap.setActorImage(selectedActorID, imageURL, name);
	}
	
	/**Called to get the url image into our engine package*/
	public void uploadImage(int xSize,int ySize, String url){
		try {
			if (!DEBUG) myDataController.uploadImage(xSize, ySize, url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setActorImageURL(String URL){
		String xval = "100";
		String yval = "100";
		String order = SaladConstants.MODIFY_ACTOR_IMAGE + SaladConstants.SEPERATER + SaladConstants.ID + SaladConstants.SEPERATER + selectedActorID + SaladConstants.SEPERATER + SaladConstants.IMAGE + SaladConstants.SEPERATER + URL + SaladConstants.SEPERATER + xval + SaladConstants.SEPERATER + yval;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	/*public void createPlayer(int ID,String url,String name){
		String order = SaladConstants.CREATE_PLAYER + SaladConstants.SEPERATER + SaladConstants.ID + SaladConstants.SEPERATER+ID+SaladConstants.SEPERATER + SaladConstants.IMAGE + SaladConstants.SEPERATER+url+",Position,0,0,Name,"+name;
		//if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}
 	
	public void createActor(int ID,String url,String name){
		String order = SaladConstants.CREATE_ACTOR + SaladConstants.SEPERATER + SaladConstants.ID + SaladConstants.SEPERATER+ID+",ActorImage,"+"actor_default.png"+",100,100,Position,100.0,200.0,Name,"+name+",CollisionID,"+0+",Lives,"+1;
		System.out.println(order);
		if (!DEBUG) myDataController.receiveOrder(order);
		
	}
	
	public void createLevel(int ID){
		String order = SaladConstants.CREATE_LEVEL + SaladConstants.SEPERATER + SaladConstants.ID + SaladConstants.SEPERATER+ID;
		System.out.println(order);
		//if (!DEBUG) myDataController.receiveOrder(order);
		
	}


	public void createScene(int levelID, int sceneID){
		String order = SaladConstants.CREATE_SCENE + SaladConstants.SEPERATER + SaladConstants.ID + SaladConstants.SEPERATER+levelID+SaladConstants.SEPERATER + SaladConstants.ID + SaladConstants.SEPERATER+sceneID;
		System.out.println(order);
		//if (!DEBUG) myDataController.receiveOrder(order);
		
	}


	public void modifyActorPosition(int ID, double xPos, double yPos){
		String order = SaladConstants.MODIFY_ACTOR + SaladConstants.SEPERATER + SaladConstants.ID + SaladConstants.SEPERATER+ID+SaladConstants.SEPERATER + SaladConstants.POSITION + SaladConstants.SEPERATER + xPos + SaladConstants.SEPERATER + yPos;
	//	if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	public void modifyActorPositionOnly(double xPos, double yPos){
		modifyActorPosition(selectedActorID, xPos, yPos);
	}
	
	public void modifyActorSpeed(int ID, double xSpeed, double ySpeed){
		String order = SaladConstants.MODIFY_ACTOR + SaladConstants.SEPERATER + SaladConstants.ID + SaladConstants.SEPERATER+ID+SaladConstants.SEPERATER + SaladConstants.POSITION + SaladConstants.SEPERATER + xSpeed + SaladConstants.SEPERATER + ySpeed;
		//if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}
	public void modifyScene(int ID, String function){
		String order = SaladConstants.MODIFY_SCENE + SaladConstants.SEPERATER + SaladConstants.ID + SaladConstants.SEPERATER+ID+ function;
		//if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
		
	}


	public void modifyLevel(int ID, String function){
		String order = SaladConstants.MODIFY_LEVEL + SaladConstants.SEPERATER + SaladConstants.ID + SaladConstants.SEPERATER+ID+ function;
		//if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	public void switchLevel(int ID){
		String order = SaladConstants.SWITCH_LEVEL + SaladConstants.SEPERATER + SaladConstants.ID + SaladConstants.SEPERATER+ID;
		//if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	
	public void switchScene(int levelID, int sceneID){
		String order = SaladConstants.SWITCH_SCENE + SaladConstants.SEPERATER + SaladConstants.ID + SaladConstants.SEPERATER+levelID+SaladConstants.SEPERATER + SaladConstants.ID + SaladConstants.SEPERATER+sceneID;
		System.out.println(order);
		//if (!DEBUG) myDataController.receiveOrder(order);
		
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
		//if (!DEBUG) myDataController.receiveOrder(order);				
	}
	
	public void deleteScene(int sceneID){
		String order = "ModifyScene,ID,"+sceneID+",DeleteScene";
		System.out.println(order);
		//if (!DEBUG) myDataController.receiveOrder(order);	
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
	
	
	public void setActorImageURL(String URL){
		
	}

	*/
}
