/**
+ * @author Nick Pan and Talal Javed Qadri and Anthony Olawo and Kat Krieger
 */

package controller;

import game_authoring_environment.ActorsPanel;
import game_authoring_environment.AttributesPanel;
import game_authoring_environment.FullView;
import game_authoring_environment.GAE;
import game_authoring_environment.LeftPanel;
import game_authoring_environment.Library;
import game_authoring_environment.MenuBar;

import java.awt.BorderLayout;
import java.awt.Image;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.plaf.LayerUI;


import objects.GameObject;
import objects.NonPlayer;
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
	private Library libraryPanel;
	private int selectedSceneID;
	private int selectedActorID;
	private int playerID;

	private static final boolean DEBUG = false;

	public GAEController(){
		playerID = SaladConstants.PLAYER_ID;
		myDataController = new DataController();


		myGameEngine = myDataController.initGameEngine(true);
		//createGAE(this);
		g = new GAE(this);
		setUpVariables();
		createLevel(1);
		createScene(1,0);
		switchScene(1,0);
		//uploadImage(100,100,)
		double d = 0;
		//modifyGravityMagnitude(d);


	}


	private void setUpVariables(){

		fv = g.getFullView();
		mb = g.getMenuBar();
		panelMap = fv.getMap();
		attributesPanel = fv.getAttributes();
		libraryPanel = fv.getLibrary();
		//test code below	

	}


	public GameEngine getEngine(){
		return myGameEngine;
	}


	public void createPlayer(int ID,String url, int xSize, int ySize, double xPos, double yPos, String name, int colID, int lives){
		String order = SaladConstants.CREATE_PLAYER + SaladConstants.SEPARATOR + SaladConstants.ID + SaladConstants.SEPARATOR+ID+SaladConstants.SEPARATOR +
				SaladConstants.IMAGE + SaladConstants.SEPARATOR+url+ SaladConstants.SEPARATOR + xSize + SaladConstants.SEPARATOR + ySize +
				SaladConstants.SEPARATOR + SaladConstants.POSITION + SaladConstants.SEPARATOR + xPos + SaladConstants.SEPARATOR + yPos + 
				SaladConstants.SEPARATOR + SaladConstants.NAME + SaladConstants.SEPARATOR+name + ",CollisionID," + colID + SaladConstants.SEPARATOR + 
				SaladConstants.LIVES + SaladConstants.SEPARATOR + lives;
		if (!DEBUG) myDataController.receiveOrder(order);
		setColIDStayOnEveryTileID(colID);
		System.out.println(order);
	}


	/**
	 * Modify player's speed without providing player id. The already set player ID is used.
	 */
	public void modifyPlayerSpeed(double xSpeed, double ySpeed){
		String order = SaladConstants.MODIFY_PLAYER + SaladConstants.SEPARATOR + SaladConstants.ID + SaladConstants.SEPARATOR+playerID+SaladConstants.SEPARATOR + 
				SaladConstants.SPEED + SaladConstants.SEPARATOR+xSpeed+ SaladConstants.SEPARATOR + ySpeed;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}

	/**
	 * Modify player's image without providing player id. The already specified player ID is used.
	 */
	public void modifyPlayerImage( String url, int xSize, int ySize){
		String order = SaladConstants.MODIFY_PLAYER_IMAGE + SaladConstants.SEPARATOR + SaladConstants.ID + SaladConstants.SEPARATOR+ playerID +SaladConstants.SEPARATOR + 
				SaladConstants.IMAGE + SaladConstants.SEPARATOR+url + SaladConstants.SEPARATOR + xSize + SaladConstants.SEPARATOR + ySize;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	/**
	 * Modify player's ID without providing old player id. The already specified player ID is used for the old id.
	 */
	public void modifyPlayerID(int newID){
		String order = SaladConstants.MODIFY_PLAYER + SaladConstants.SEPARATOR + SaladConstants.ID + SaladConstants.SEPARATOR+playerID+SaladConstants.SEPARATOR +  
				SaladConstants.CHANGE_TO_ID + SaladConstants.SEPARATOR+newID;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}

	public void modifyPlayerColID(int oldColID,int newColID){
		String order = SaladConstants.MODIFY_PLAYER + SaladConstants.SEPARATOR + SaladConstants.ID + SaladConstants.SEPARATOR+oldColID+SaladConstants.SEPARATOR + 
				SaladConstants.CHANGE_COLLISION_ID + SaladConstants.SEPARATOR +newColID;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}

	/**
	 * Modify player's position without providing player id. The already specified playerID is used.
	 */
	public void modifyPlayerPos(double xPos, double yPos){
		String order = SaladConstants.MODIFY_PLAYER + SaladConstants.SEPARATOR + SaladConstants.ID + SaladConstants.SEPARATOR+playerID+SaladConstants.SEPARATOR + 
				SaladConstants.POSITION + SaladConstants.SEPARATOR+xPos+ SaladConstants.SEPARATOR + yPos;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}

	/**
	 * Modify player by doing a regular remove without providing player id. The already specified playerID is used.
	 */
	public void modifyPlayerRegRemove(){
		String order = SaladConstants.MODIFY_PLAYER + SaladConstants.SEPARATOR + SaladConstants.ID + SaladConstants.SEPARATOR+
				playerID+SaladConstants.SEPARATOR + 
				SaladConstants.REGULAR_REMOVE + SaladConstants.SEPARATOR +SaladConstants.REGULAR_REMOVE;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	/**
	 * Modify player by making it immortal without providing player id. The already specified playerID is used.
	 */
	public void modifyPlayerImmortal(){
		String order = SaladConstants.MODIFY_PLAYER + SaladConstants.SEPARATOR + SaladConstants.ID + SaladConstants.SEPARATOR+playerID+SaladConstants.SEPARATOR + 
				SaladConstants.IMMORTAL + SaladConstants.SEPARATOR+SaladConstants.IMMORTAL;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}

	/**
	 * Modify player by making it immobile without providing player id. The already specified playerID is used.
	 */
	public void modifyPlayerImmobile(){
		String order = SaladConstants.MODIFY_PLAYER + SaladConstants.SEPARATOR + SaladConstants.ID + SaladConstants.SEPARATOR+playerID+SaladConstants.SEPARATOR + 
				SaladConstants.IMMOBILE + SaladConstants.SEPARATOR+SaladConstants.IMMOBILE;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	/**
	 * Modify number of bullets per shoot  without specifying player ID. Global player ID which is ID of current selected player is used
	 */
	public void  modifyPlayerSpreadShoot(String imageFileName, int xSize, int ySize, int collID, double speed, int bulletsPerShot, int maxBulletsAllowed){
		String order = SaladConstants.MODIFY_PLAYER + SaladConstants.SEPARATOR + SaladConstants.ID + SaladConstants.SEPARATOR+ playerID +SaladConstants.SEPARATOR + 
				SaladConstants.SPREAD_SHOOT + SaladConstants.SEPARATOR+SaladConstants.SPREAD_SHOOT + SaladConstants.SEPARATOR + imageFileName + 
				SaladConstants.SEPARATOR + xSize + SaladConstants.SEPARATOR + ySize  + SaladConstants.SEPARATOR +
				collID  + SaladConstants.SEPARATOR + speed  + SaladConstants.SEPARATOR + bulletsPerShot + SaladConstants.SEPARATOR +  maxBulletsAllowed;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}

	/**
	 * Modify player's shoot property by slowing it without providing player id. The already specified playerID is used.
	 */
	public void modifyPlayerSlowShoot(String url, int xSize, int ySize, int colID, double speed, int maxBullets){
		String order = SaladConstants.MODIFY_PLAYER + SaladConstants.SEPARATOR + SaladConstants.ID + SaladConstants.SEPARATOR+playerID+SaladConstants.SEPARATOR + 
				SaladConstants.SLOW_SHOOT + SaladConstants.SEPARATOR+SaladConstants.SLOW_SHOOT + SaladConstants.SEPARATOR + url + SaladConstants.SEPARATOR 
				+  xSize + SaladConstants.SEPARATOR + ySize + SaladConstants.SEPARATOR + colID + SaladConstants.SEPARATOR + speed + SaladConstants.SEPARATOR +
				maxBullets;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}

	/**
	 * Modify player's shoot property by quickening it without providing player id. The already specified playerID is used.
	 */
	public void modifyPlayerQuickShoot(String url, int xSize, int ySize, int colID, double speed, int numBullets, int maxBullets){
		String order = SaladConstants.MODIFY_PLAYER + SaladConstants.SEPARATOR + SaladConstants.ID + SaladConstants.SEPARATOR+playerID+SaladConstants.SEPARATOR + 
				SaladConstants.QUICK_SHOOT + SaladConstants.SEPARATOR+SaladConstants.QUICK_SHOOT + SaladConstants.SEPARATOR + url + 
				SaladConstants.SEPARATOR +  xSize + SaladConstants.SEPARATOR + ySize + SaladConstants.SEPARATOR + colID + SaladConstants.SEPARATOR + speed +
				SaladConstants.SEPARATOR + numBullets + SaladConstants.SEPARATOR + maxBullets;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}
	/**
	 * Modify player by changing its property to move in air
	 */	
	public void modifyPlayerCanMoveInAir(boolean enableAirMove){
		String order = SaladConstants.MODIFY_PLAYER + SaladConstants.SEPARATOR + SaladConstants.ID + SaladConstants.SEPARATOR+playerID+SaladConstants.SEPARATOR + 
				SaladConstants.CAN_MOVE_IN_AIR + SaladConstants.SEPARATOR+ String.valueOf(enableAirMove);
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}

	/**
	 * Modify player's corpse showing property without providing player id. The already specified playerID is used.
	 */
	public void modifyPlayerShowCorpse(String url, int xSize, int ySize, int time){
		String order = SaladConstants.MODIFY_PLAYER + SaladConstants.SEPARATOR + SaladConstants.ID + SaladConstants.SEPARATOR+playerID+SaladConstants.SEPARATOR + 
				SaladConstants.SHOW_CORPSE + SaladConstants.SEPARATOR+SaladConstants.SHOW_CORPSE + SaladConstants.SEPARATOR + url + 
				SaladConstants.SEPARATOR +  xSize + SaladConstants.SEPARATOR + ySize + SaladConstants.SEPARATOR + time;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}


	/**
	 * Modify player's jump behavior without providing player id. The already specified playerID is used.
	 */

	public void modifyPlayerJumpBehavior(double magnitude, int numJumps){
		String order = SaladConstants.MODIFY_PLAYER + SaladConstants.SEPARATOR + SaladConstants.ID + SaladConstants.SEPARATOR+playerID+SaladConstants.SEPARATOR + 
				SaladConstants.JUMP + SaladConstants.SEPARATOR+SaladConstants.JUMP + SaladConstants.SEPARATOR + magnitude + 
				SaladConstants.SEPARATOR +  numJumps;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	

	/**
	 * Modify player so it can't jump
	 */
	public void modifyPlayerCanNotJump(String cannotJump){ 
		String order = SaladConstants.MODIFY_PLAYER + SaladConstants.SEPARATOR + playerID + SaladConstants.SEPARATOR + SaladConstants.CAN_NOT_JUMP + 
				SaladConstants.SEPARATOR + cannotJump; 
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}


	/**
	 * Modify player's moveUp key without providing player id. The already specified playerID is used for the id.
	 */
	public void modifyPlayerKeyUp(int key){
		String order = SaladConstants.MODIFY_PLAYER + SaladConstants.SEPARATOR + SaladConstants.ID + SaladConstants.SEPARATOR+playerID+SaladConstants.SEPARATOR + 
				SaladConstants.SET_KEY + SaladConstants.SEPARATOR+ key + SaladConstants.SEPARATOR + SaladConstants.MOVE_UP;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}

	/**
	 * Modify player's moveDown key without providing player id. The already specified playerID is used for the id.
	 */
	public void modifyPlayerKeyDown(int key){
		String order = SaladConstants.MODIFY_PLAYER + SaladConstants.SEPARATOR + SaladConstants.ID + SaladConstants.SEPARATOR+ playerID +SaladConstants.SEPARATOR + 
				SaladConstants.SET_KEY + SaladConstants.SEPARATOR+ key + SaladConstants.SEPARATOR + SaladConstants.MOVE_DOWN;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}

	/**
	 * Modify player's moveLeft key without providing player id. The already specified playerID is used for the id.
	 */
	public void modifyPlayerKeyLeft(int key){
		String order = SaladConstants.MODIFY_PLAYER + SaladConstants.SEPARATOR + SaladConstants.ID + SaladConstants.SEPARATOR+playerID+SaladConstants.SEPARATOR + 
				SaladConstants.SET_KEY + SaladConstants.SEPARATOR+ key + SaladConstants.SEPARATOR + SaladConstants.MOVE_LEFT;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}

	/**
	 * Modify player's moveRight key without providing player id. The already specified playerID is used for the id.
	 */
	public void modifyPlayerKeyRight(int key){
		String order = SaladConstants.MODIFY_PLAYER + SaladConstants.SEPARATOR + SaladConstants.ID + SaladConstants.SEPARATOR+playerID+SaladConstants.SEPARATOR + 
				SaladConstants.SET_KEY + SaladConstants.SEPARATOR+ key + SaladConstants.SEPARATOR + SaladConstants.MOVE_RIGHT;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}

	/**
	 * Modify player's shoot key without providing player id. The already specified playerID is used for the id.
	 */
	public void modifyPlayerKeyShoot(int key){
		String order = SaladConstants.MODIFY_PLAYER + SaladConstants.SEPARATOR + SaladConstants.ID + SaladConstants.SEPARATOR+playerID+SaladConstants.SEPARATOR + 
				SaladConstants.SET_KEY + SaladConstants.SEPARATOR+ key + SaladConstants.SEPARATOR + SaladConstants.SHOOT_KEY;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}

	/**
	 * Modify player's jump key without providing player id. The already specified playerID is used for the id.
	 */
	public void modifyPlayerKeyJump(int key){
		String order = SaladConstants.MODIFY_PLAYER + SaladConstants.SEPARATOR + SaladConstants.ID + SaladConstants.SEPARATOR+playerID+SaladConstants.SEPARATOR + 
				SaladConstants.SET_KEY + SaladConstants.SEPARATOR+ key + SaladConstants.SEPARATOR + SaladConstants.JUMP_KEY;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}

	/**
	 * Delete player without providing player id. The already specified playerID is used for the id.
	 */
	public void deletePlayer(){
		String order = SaladConstants.DELETE_PLAYER + SaladConstants.SEPARATOR + SaladConstants.ID + SaladConstants.SEPARATOR + playerID;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}



	public void createActor(int ID,String url, int xSize, int ySize, 
			double xPos, double yPos, String name, int colID, int lives){
		String order = SaladConstants.CREATE_ACTOR + SaladConstants.SEPARATOR + SaladConstants.ID + SaladConstants.SEPARATOR+ID+SaladConstants.SEPARATOR + 
				SaladConstants.IMAGE + SaladConstants.SEPARATOR+url+ SaladConstants.SEPARATOR + xSize + SaladConstants.SEPARATOR + ySize +
				SaladConstants.SEPARATOR + SaladConstants.POSITION + SaladConstants.SEPARATOR + xPos + SaladConstants.SEPARATOR + yPos + 
				SaladConstants.SEPARATOR + SaladConstants.NAME + SaladConstants.SEPARATOR+name + ",CollisionID,"+ colID +  SaladConstants.SEPARATOR + 
				SaladConstants.LIVES + SaladConstants.SEPARATOR + lives;
		if (!DEBUG) myDataController.receiveOrder(order);
		setColIDStayOnEveryTileID(colID);
		System.out.println(order);
	}

	public void modifyActorSpeed(int ID, double xSpeed, double ySpeed){
		String order = SaladConstants.MODIFY_ACTOR + SaladConstants.SEPARATOR + SaladConstants.ID + SaladConstants.SEPARATOR+ID+SaladConstants.SEPARATOR + 
				SaladConstants.SPEED + SaladConstants.SEPARATOR+xSpeed + SaladConstants.SEPARATOR + ySpeed;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}

	/**
	 * Modify actor's speed without providing actor id. The selectedActorID is used.
	 */
	public void modifyActorSpeedNoID(double xSpeed, double ySpeed){
		modifyActorSpeed(selectedActorID,xSpeed, ySpeed);
	}

	/**
	 * Modify actor's jump 
	 * 
	 * @param ID
	 * @param jump
	 * @param magnitude
	 * @param numberOfJumpsAllowedInAir
	 */
	public void modifyActorJump(double magnitude, int numberOfJumpsAllowedInAir){
		String order = SaladConstants.MODIFY_ACTOR + SaladConstants.SEPARATOR + selectedActorID + SaladConstants.SEPARATOR + SaladConstants.JUMP + SaladConstants.SEPARATOR +
				magnitude + SaladConstants.SEPARATOR + numberOfJumpsAllowedInAir;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}

	/**
	 * Modify actor to nullify jumping ability
	 * @param ID
	 * @param canNotJump
	 */
	public void modifyActorNotToJump( String canNotJump){ 
		String order = SaladConstants.MODIFY_ACTOR + SaladConstants.SEPARATOR + SaladConstants.ID + SaladConstants.SEPARATOR + selectedActorID + SaladConstants.SEPARATOR + SaladConstants.JUMP + SaladConstants.SEPARATOR + 
				canNotJump; 
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	/**
	 * 
	 * @param ID
	 * @param jumpByTime
	 * @param magnitude
	 * @param numberOfJumpsAllowedInAir
	 * @param latency
	 */
	public void modifyActorToJumpByTime(int ID, String jumpByTime, double magnitude, int numberOfJumpsAllowedInAir, int latency){ 
		String order = SaladConstants.MODIFY_ACTOR + SaladConstants.SEPARATOR + SaladConstants.ID + SaladConstants.SEPARATOR + ID + SaladConstants.SEPARATOR + SaladConstants.JUMP_BY_TIME + SaladConstants.SEPARATOR + 
				jumpByTime + SaladConstants.SEPARATOR + magnitude + SaladConstants.SEPARATOR + numberOfJumpsAllowedInAir + SaladConstants.SEPARATOR + latency; 
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}

	/**
	 * Animate an actor's jump
	 * 
	 * @param ID
	 * @param jump
	 * @param url
	 * @param xSize
	 * @param ySize
	 */
	public void AnimateActorJump(int ID, String jump, String url, int xSize, int ySize ){
		String order = SaladConstants.MODIFY_ACTOR + SaladConstants.SEPARATOR + SaladConstants.ID + SaladConstants.SEPARATOR + ID + SaladConstants.SEPARATOR + SaladConstants.JUMP +
				SaladConstants.SEPARATOR + jump + SaladConstants.SEPARATOR + url + SaladConstants.SEPARATOR + xSize + SaladConstants.SEPARATOR + ySize; 
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}

	/**
	 * Modify actor to shoot slowly
	 * 
	 * @param ID
	 * @param shootByTime
	 * @param imgURL
	 * @param xSize
	 * @param ySize
	 * @param collID
	 * @param speed
	 * @param latency
	 */
	public void modifyActorToSlowShoot(int ID, String shootByTime, String imgURL, int xSize, int ySize, int collID, double speed, int latency ){ 
		String order = SaladConstants.MODIFY_ACTOR + SaladConstants.SEPARATOR + SaladConstants.ID + SaladConstants.SEPARATOR +ID + SaladConstants.SEPARATOR + SaladConstants.SLOW_SHOOT_BY_TIME + SaladConstants.SEPARATOR + 
				shootByTime + SaladConstants.SEPARATOR + imgURL + SaladConstants.SEPARATOR + xSize + SaladConstants.SEPARATOR + ySize + SaladConstants.SEPARATOR +
				collID + SaladConstants.SEPARATOR + speed + SaladConstants.SEPARATOR + latency; 
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	public void modifyActorToSpreadShoot(int ID, String spreadShoot, String imgURL, int xSize, int ySize, int collID, double speed, int bulletsPerShot ){ 
		String order = SaladConstants.MODIFY_ACTOR + SaladConstants.SEPARATOR + SaladConstants.ID + SaladConstants.SEPARATOR +ID + SaladConstants.SEPARATOR + SaladConstants.SPREAD_SHOOT + SaladConstants.SEPARATOR + 
				spreadShoot + SaladConstants.SEPARATOR + imgURL + SaladConstants.SEPARATOR + xSize + SaladConstants.SEPARATOR + ySize + SaladConstants.SEPARATOR +
				collID + SaladConstants.SEPARATOR + speed + SaladConstants.SEPARATOR + bulletsPerShot; 
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}

	/**
	 * Modify actor's vertical movement
	 * 
	 * @param ID
	 * @param backForthMoveWithVerticalSpeed
	 * @param imgURL
	 * @param amplitude
	 * @param lantency
	 * @param ySpeed
	 */
	public void modifyActorMovement(int ID, String backForthMoveWithVerticalSpeed, String imgURL, double amplitude, int lantency, double ySpeed){ 
		String order = SaladConstants.MODIFY_ACTOR + SaladConstants.SEPARATOR + SaladConstants.ID + SaladConstants.SEPARATOR + ID + SaladConstants.SEPARATOR + SaladConstants.BACK_FORTH_MOVE_WITH_VERTICAL_SPEED + SaladConstants.SEPARATOR + 
				backForthMoveWithVerticalSpeed + SaladConstants.SEPARATOR + imgURL + SaladConstants.SEPARATOR + amplitude + SaladConstants.SEPARATOR + lantency + SaladConstants.SEPARATOR +
				ySpeed; 
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	/**
	 * 
	 * @param ID
	 * @param jump
	 * @param imgURL
	 * @param xSize
	 * @param ySize
	 */
	public void modifyActorAnimationJump(int ID, String jump, String imgURL, int xSize, int ySize){ 
		String order = SaladConstants.MODIFY_ACTOR + SaladConstants.SEPARATOR + SaladConstants.ID + SaladConstants.SEPARATOR + ID + SaladConstants.SEPARATOR + SaladConstants.JUMP + SaladConstants.SEPARATOR + 
				jump + SaladConstants.SEPARATOR + imgURL + SaladConstants.SEPARATOR + xSize + SaladConstants.SEPARATOR + ySize ;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	/**
	 * 
	 * @param ID
	 * @param FDMove
	 * @param imgURL
	 * @param xSize
	 * @param ySize
	 */
	public void modifyActorAnimationFDMove(int ID, String FDMove, String imgURL, int xSize, int ySize){ 
		String order = SaladConstants.MODIFY_ACTOR + SaladConstants.SEPARATOR + SaladConstants.ID + SaladConstants.SEPARATOR + ID + SaladConstants.SEPARATOR + SaladConstants.FD_MOVE + SaladConstants.SEPARATOR + 
				FDMove + SaladConstants.SEPARATOR + imgURL + SaladConstants.SEPARATOR + xSize + SaladConstants.SEPARATOR + ySize ;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	/**
	 * 
	 * @param ID
	 * @param BKMove
	 * @param imgURL
	 * @param xSize
	 * @param ySize
	 */
	public void modifyActorAnimationBKMove(int ID, String BKMove, String imgURL, int xSize, int ySize){ 
		String order = SaladConstants.MODIFY_ACTOR + SaladConstants.SEPARATOR + SaladConstants.ID + SaladConstants.SEPARATOR + ID + SaladConstants.SEPARATOR + SaladConstants.BK_MOVE + SaladConstants.SEPARATOR + 
				BKMove + SaladConstants.SEPARATOR + imgURL + SaladConstants.SEPARATOR + xSize + SaladConstants.SEPARATOR + ySize ;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	public void modifyActorImage(int ID, String url, int xSize, int ySize){
		String order = SaladConstants.MODIFY_ACTOR_IMAGE + SaladConstants.SEPARATOR + SaladConstants.ID + SaladConstants.SEPARATOR+ ID +SaladConstants.SEPARATOR + 
				SaladConstants.IMAGE + SaladConstants.SEPARATOR+url + SaladConstants.SEPARATOR + xSize + SaladConstants.SEPARATOR + ySize;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}

	/**
	 * Modify actor's image without providing actor id. The selectedActorID is used.
	 */
	public void modifyActorImageNoID(String url, int xSize, int ySize){
		modifyActorImage(selectedActorID, url, xSize, ySize);
	}

	public void modifyActorID(int oldID,int newID){
		String order = SaladConstants.MODIFY_ACTOR + SaladConstants.SEPARATOR + SaladConstants.ID + SaladConstants.SEPARATOR+oldID+SaladConstants.SEPARATOR +  
				SaladConstants.CHANGE_TO_ID + SaladConstants.SEPARATOR+newID;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}

	/**
	 * Modify actor's ID without providing old actor id. The selectedActorID is used for the old id.
	 */
	public void modifyActorIDNoOldID(int newID){
		modifyActorID(selectedActorID, newID);
	}

	public void modifyActorName(int ID,String name){
		String order = SaladConstants.MODIFY_ACTOR + SaladConstants.SEPARATOR + SaladConstants.ID + SaladConstants.SEPARATOR
				+ ID+SaladConstants.SEPARATOR + SaladConstants.NAME + SaladConstants.SEPARATOR+ name;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}

	/**
	 * Modify actor's Name without providing old actor id. The selectedActorID is used for the old id.
	 */
	public void modifyActorNameoOldID(int newID){
		modifyActorID(selectedActorID, newID);
	}

	public void modifyActorColIDNoID(int newColID){
		int oldColID = myDataController.getGame().getPlayer(selectedActorID).colid;
		String order = SaladConstants.MODIFY_ACTOR + SaladConstants.SEPARATOR + SaladConstants.ID + SaladConstants.SEPARATOR+ oldColID+SaladConstants.SEPARATOR + 
				SaladConstants.CHANGE_COLLISION_ID + SaladConstants.SEPARATOR +newColID;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	public void modifyActorColID(int oldColID,int newColID){
		String order = SaladConstants.MODIFY_ACTOR + SaladConstants.SEPARATOR + SaladConstants.ID + SaladConstants.SEPARATOR+oldColID+SaladConstants.SEPARATOR + 
				SaladConstants.CHANGE_COLLISION_ID + SaladConstants.SEPARATOR +newColID;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}

	public void modifyActorPos(int ID,double xPos, double yPos){
		String order = SaladConstants.MODIFY_ACTOR + SaladConstants.SEPARATOR + SaladConstants.ID + SaladConstants.SEPARATOR+ID+SaladConstants.SEPARATOR + 
				SaladConstants.POSITION + SaladConstants.SEPARATOR+xPos+ SaladConstants.SEPARATOR + yPos;
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
		String order = SaladConstants.MODIFY_ACTOR + SaladConstants.SEPARATOR + SaladConstants.COLLISION_ID + SaladConstants.SEPARATOR+colID+
				SaladConstants.SEPARATOR + SaladConstants.EXPLODE + SaladConstants.SEPARATOR + SaladConstants.EXPLODE+ SaladConstants.SEPARATOR + colIDTarget + 
				SaladConstants.SEPARATOR + url + SaladConstants.SEPARATOR + xSize + SaladConstants.SEPARATOR + ySize;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}


	public void modifyActorRegMove(int ID, double xSpeed, double ySpeed){
		String order = SaladConstants.MODIFY_ACTOR + SaladConstants.SEPARATOR + SaladConstants.ID + SaladConstants.SEPARATOR+ID+SaladConstants.SEPARATOR + 
				SaladConstants.REGULAR_MOVE + SaladConstants.SEPARATOR + SaladConstants.REGULAR_MOVE+ SaladConstants.SEPARATOR +xSpeed + 
				SaladConstants.SEPARATOR + ySpeed;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}

	/**
	 * Modify actor's regular move property without providing actor id. The selectedActorID is used.
	 */
	public void modifyActorRegMoveNoID(double xSpeed, double ySpeed){
		modifyActorRegMove(selectedActorID, xSpeed, ySpeed);
	}

	public void modifyActorBackForthMove(int ID, double amplitude, int latency){
		String order = SaladConstants.MODIFY_ACTOR + SaladConstants.SEPARATOR + SaladConstants.ID + SaladConstants.SEPARATOR+ID+SaladConstants.SEPARATOR + 
				SaladConstants.BACK_FORTH_MOVE + SaladConstants.SEPARATOR + SaladConstants.BACK_FORTH_MOVE+ SaladConstants.SEPARATOR 
				+ amplitude + SaladConstants.SEPARATOR + latency;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}

	/**
	 * Modify actor's regular move property without providing actor id. The selectedActorID is used.
	 */
	public void modifyActorBackForthMoveNoID(double amplitude, int latency){
		modifyActorBackForthMove(selectedActorID, amplitude, latency);
	}

	public void modifyActorRegRemove(int ID){
		String order = SaladConstants.MODIFY_ACTOR + SaladConstants.SEPARATOR + SaladConstants.ID + SaladConstants.SEPARATOR+ID+SaladConstants.SEPARATOR + 
				SaladConstants.REGULAR_REMOVE + SaladConstants.SEPARATOR +SaladConstants.REGULAR_REMOVE;
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
		String order = SaladConstants.MODIFY_ACTOR + SaladConstants.SEPARATOR + SaladConstants.ID + SaladConstants.SEPARATOR+ID+SaladConstants.SEPARATOR + 
				SaladConstants.IMMORTAL + SaladConstants.SEPARATOR+SaladConstants.IMMORTAL;
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
		String order = SaladConstants.MODIFY_ACTOR + SaladConstants.SEPARATOR + SaladConstants.ID + SaladConstants.SEPARATOR+ID+SaladConstants.SEPARATOR + 
				SaladConstants.IMMOBILE + SaladConstants.SEPARATOR+SaladConstants.IMMOBILE;
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
		String order = SaladConstants.MODIFY_ACTOR + SaladConstants.SEPARATOR + SaladConstants.ID + SaladConstants.SEPARATOR+ID+SaladConstants.SEPARATOR + 
				SaladConstants.SLOW_SHOOT + SaladConstants.SEPARATOR+SaladConstants.SLOW_SHOOT + SaladConstants.SEPARATOR + url + SaladConstants.SEPARATOR 
				+  xSize + SaladConstants.SEPARATOR + ySize + SaladConstants.SEPARATOR + colID + SaladConstants.SEPARATOR + speed;
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
		String order = SaladConstants.MODIFY_ACTOR + SaladConstants.SEPARATOR + SaladConstants.ID + SaladConstants.SEPARATOR+ID+SaladConstants.SEPARATOR + 
				SaladConstants.QUICK_SHOOT + SaladConstants.SEPARATOR+SaladConstants.QUICK_SHOOT + SaladConstants.SEPARATOR + url + 
				SaladConstants.SEPARATOR +  xSize + SaladConstants.SEPARATOR + ySize + SaladConstants.SEPARATOR + colID + SaladConstants.SEPARATOR + speed +
				SaladConstants.SEPARATOR + numBullets;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}

	/**
	 * Modify actor's shoot property by quickening it without providing actor id. The selectedActorID is used.
	 */
	public void modifyActorQuickShootNoID(String url, int xSize, int ySize, int colID, double speed, int numBullets){
		modifyActorQuickShoot(selectedActorID, url, xSize, ySize, colID, speed, numBullets);
	}

	/**
	 * 
	 * 
	 * @param victimColId
	 * @param stayOnTile
	 * @param tileColId
	 * @param direction
	 */
	public void modifyTileCollisionBehaviour(int victimColId,String stayOnTile, char tileColId, String direction){ 
		String order = SaladConstants.MODIFY_TILE_COLLISION_BEHAVIOR + SaladConstants.SEPARATOR + SaladConstants.COLLISION_ID + victimColId + SaladConstants.SEPARATOR 
				+ SaladConstants.STAY_ON_TILE + SaladConstants.SEPARATOR + stayOnTile + SaladConstants.SEPARATOR + tileColId + SaladConstants.SEPARATOR + direction; 
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}

	public void setDragTile(char colID, String url){
		String order = SaladConstants.SET_DRAG_TILE + SaladConstants.SEPARATOR + SaladConstants.TILE_COLID + SaladConstants.SEPARATOR + colID +
				SaladConstants.SEPARATOR + SaladConstants.DRAG_IMAGE + SaladConstants.SEPARATOR + url;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}


	public void deleteActor(int ID){
		String order = SaladConstants.DELETE_ACTOR + SaladConstants.SEPARATOR + SaladConstants.ID + SaladConstants.SEPARATOR + ID;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}

	/**
	 * Delete actor without providing actor id. The selectedActorID is used.
	 */
	public void deleteActorNoID(){
		deleteActor(selectedActorID);
	}

	public void modifyCollisBehavHitElimVic(int victimColID,int hitterColID, String moveDirection){
		String order = SaladConstants.MODIFY_COLLISION_BEHAVIOR + SaladConstants.SEPARATOR + SaladConstants.COLLISION_ID + SaladConstants.SEPARATOR
				+ victimColID + SaladConstants.SEPARATOR + SaladConstants.HITTER_ELIMINATE_VICTIM + SaladConstants.SEPARATOR 
				+ SaladConstants.HITTER_ELIMINATE_VICTIM + SaladConstants.SEPARATOR + hitterColID + SaladConstants.SEPARATOR +
				moveDirection;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}

	public void modifyCollisBehavShootHitObject(int victimCollID, String shootHitObject, int hitterCollID, String Direction){
		String order = SaladConstants.MODIFY_COLLISION_BEHAVIOR + SaladConstants.SEPARATOR + SaladConstants.COLLISION_ID + SaladConstants.SEPARATOR
				+ victimCollID + SaladConstants.SEPARATOR + SaladConstants.SHOOT_HIT_OBJECT + SaladConstants.SEPARATOR 
				+ SaladConstants.HITTER_ELIMINATE_VICTIM + SaladConstants.SEPARATOR + shootHitObject + SaladConstants.SEPARATOR +
				hitterCollID;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}

	public void modifyCollisBehavPerishTog(int victimColID,int hitterColID, String moveDirection){
		String order = SaladConstants.MODIFY_COLLISION_BEHAVIOR + SaladConstants.SEPARATOR + SaladConstants.COLLISION_ID + SaladConstants.SEPARATOR
				+ victimColID + SaladConstants.SEPARATOR + SaladConstants.PERISH_TOGETHER + SaladConstants.SEPARATOR + 
				SaladConstants.PERISH_TOGETHER+ SaladConstants.SEPARATOR + hitterColID+ SaladConstants.SEPARATOR +
				moveDirection;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}


	public void modifyCollisBehavStayOnTile(int victimColID,char tileColID, String moveDirection){
		String order = SaladConstants.MODIFY_TILE_COLLISION_BEHAVIOR + SaladConstants.SEPARATOR + SaladConstants.COLLISION_ID + SaladConstants.SEPARATOR
				+ victimColID + SaladConstants.SEPARATOR + SaladConstants.STAY_ON_TILE + SaladConstants.SEPARATOR + SaladConstants.STAY_ON_TILE
				+ SaladConstants.SEPARATOR + tileColID+ SaladConstants.SEPARATOR + moveDirection;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}

	/**
	 * Modify an object to enable it stand another. 
	 */

	public void modifyCollisionBehaviorToStayOnObject(int victimCollisionID, int hitterCollID, String direction){
		String order = SaladConstants.MODIFY_COLLISION_BEHAVIOUR + SaladConstants.SEPARATOR + SaladConstants.COLLISION_ID + SaladConstants.SEPARATOR+victimCollisionID+SaladConstants.SEPARATOR + 
				SaladConstants.STAY_ON_OBJECT + SaladConstants.SEPARATOR + SaladConstants.STAY_ON_OBJECT  + SaladConstants.SEPARATOR + hitterCollID + SaladConstants.SEPARATOR + 
				direction;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}

	/**
	 * Modify and object to enable it rebound off of another.  
	 */
	public void modifyCollisionBehaviorRebounce(int victimCollisionID, int hitterCollID, String direction){
		String order = SaladConstants.MODIFY_COLLISION_BEHAVIOUR + SaladConstants.SEPARATOR + SaladConstants.COLLISION_ID + SaladConstants.SEPARATOR + victimCollisionID+SaladConstants.SEPARATOR + 
				SaladConstants.REBOUND + SaladConstants.SEPARATOR + SaladConstants.REBOUND + SaladConstants.SEPARATOR + hitterCollID + SaladConstants.SEPARATOR + 
				direction;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	/**
	 * Modify an object such that it dies on colliding with a tile. 
	 */
	public void modifyCollisionBehaviorToDieByTile(int victimCollisionID, String killedByTile, char collID, String direction){
		String order = SaladConstants.MODIFY_COLLISION_BEHAVIOUR + SaladConstants.SEPARATOR + SaladConstants.COLLISION_ID + SaladConstants.SEPARATOR + victimCollisionID+SaladConstants.SEPARATOR + 
				SaladConstants.KILL_BY_TILE + SaladConstants.SEPARATOR + SaladConstants.KILL_BY_TILE + SaladConstants.SEPARATOR + collID + SaladConstants.SEPARATOR + 
				direction;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	

	public void deleteLevel(int levelID){
		String order = SaladConstants.DELETE_LEVEL + SaladConstants.SEPARATOR + SaladConstants.ID + SaladConstants.SEPARATOR+levelID;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}

	public void resetLevelID(int oldLevelID, int newLevelID){
		String order = SaladConstants.RESET_LEVEL_ID + SaladConstants.SEPARATOR + SaladConstants.ID + SaladConstants.SEPARATOR+oldLevelID + SaladConstants.SEPARATOR
				+ SaladConstants.ID + SaladConstants.SEPARATOR+newLevelID;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}


	public void createLevel(int levelID){
		String order = SaladConstants.CREATE_LEVEL + SaladConstants.SEPARATOR + SaladConstants.ID + SaladConstants.SEPARATOR+levelID;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}

	/**
	 * Sets a level's initial scene
	 * 
	 * @param levelID
	 * @param sceneID
	 */
	public void setInitialScene(int levelID, int sceneID){
		String order = SaladConstants.MODIFY_LEVEL + SaladConstants.SEPARATOR + SaladConstants.ID + SaladConstants.SEPARATOR + 
				SaladConstants.SET_INITIAL_SCENE_ID + SaladConstants.SEPARATOR + levelID + SaladConstants.SEPARATOR + sceneID;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	public void switchScene(int levelID, int sceneID){
		String order = SaladConstants.SWITCH_SCENE + SaladConstants.SEPARATOR + SaladConstants.ID + SaladConstants.SEPARATOR+levelID+SaladConstants.SEPARATOR + 
				SaladConstants.ID + SaladConstants.SEPARATOR+sceneID;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}

	public void deleteScene(int levelID, int sceneID){
		String order = SaladConstants.DELETE_SCENE + SaladConstants.SEPARATOR + SaladConstants.ID + SaladConstants.SEPARATOR+levelID+SaladConstants.SEPARATOR + 
				SaladConstants.ID + SaladConstants.SEPARATOR+sceneID;
		if (!DEBUG) myDataController.receiveOrder(order);	
		System.out.println(order);
	}

	public void createScene(int levelID, int sceneID){
		String order = SaladConstants.CREATE_SCENE + SaladConstants.SEPARATOR + SaladConstants.ID + SaladConstants.SEPARATOR+levelID+SaladConstants.SEPARATOR + 
				SaladConstants.ID + SaladConstants.SEPARATOR+sceneID;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}

	/**
	 * 
	 * @param levelID
	 * @param sceneID
	 */
	public void modifyInitScene(int levelID, int sceneID ){ 
		String order = SaladConstants.MODIFY_INIT_SCENE + SaladConstants.SEPARATOR + levelID + SaladConstants.SEPARATOR + 
				SaladConstants.SET_INIT_SCENE + sceneID;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}

	public void modifySceneBackground(String path, boolean wrapHorizontal, boolean wrapVertical, int playFieldXSize, int playFieldYSize){
		String order = SaladConstants.MODIFY_SCENE_VIEW + SaladConstants.SEPARATOR + SaladConstants.BACKGROUND + 
				SaladConstants.SEPARATOR + path + SaladConstants.SEPARATOR + String.valueOf(wrapHorizontal) + 
				SaladConstants.SEPARATOR + String.valueOf(wrapVertical) + SaladConstants.SEPARATOR + playFieldXSize + 
				SaladConstants.SEPARATOR + playFieldYSize;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}


	public void modifyScenePlayerInitPos(double xPos, double yPos){
		String order = SaladConstants.MODIFY_SCENE + SaladConstants.SEPARATOR + SaladConstants.PLAYER_INIT_POS + SaladConstants.SEPARATOR + xPos + 
				SaladConstants.SEPARATOR + yPos;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}

	/**
	 * 
	 * @param key
	 * @param keyFunction
	 */
	public void modifyInputManager(int key, String keyFunction){ 
		String order = SaladConstants.MODIFY_INPUTMANAGER + SaladConstants.SEPARATOR + SaladConstants.SET_KEY + SaladConstants.SEPARATOR + key + 
				SaladConstants.SEPARATOR + keyFunction; 
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}

	public void modifyGravityMagnitude(double magnitude){
		String order = SaladConstants.MODIFY_GRAVITY + SaladConstants.SEPARATOR +  SaladConstants.GRAVITY_MAGNITUDE+ SaladConstants.SEPARATOR + magnitude;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}

	public void modifyScoreManagerInitScore(int score){
		String order = SaladConstants.MODIFY_SCORE_MANAGER + SaladConstants.SEPARATOR +  SaladConstants.INITIAL_SCORE+ 
				SaladConstants.SEPARATOR + score;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}



	public void modifyScoreManagerCollisionScore(int score, int victimColID, int hitterColID){
		String order = SaladConstants.MODIFY_SCORE_MANAGER + SaladConstants.SEPARATOR +  SaladConstants.SET_COLLISION_SCORE + 
				SaladConstants.SEPARATOR + score + SaladConstants.SEPARATOR + SaladConstants.COLLISION + SaladConstants.SEPARATOR 
				+ victimColID + SaladConstants.SEPARATOR + hitterColID;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}

	/**
	 * Sets the tile collision score 
	 * 
	 * @param scoreChange
	 * @param tileCollision
	 * @param victimCollID
	 * @param tileCollID
	 */
	public void modifyScoreManagerTileCollisionScore(int scoreChange, String tileCollision, int victimCollID, char tileCollID){ 
		String order = SaladConstants.MODIFY_SCORE_MANAGER + SaladConstants.SEPARATOR + SaladConstants.SET_TILE_COLLISION_SCORE + 
				SaladConstants.SEPARATOR + scoreChange + SaladConstants.SEPARATOR + tileCollision + SaladConstants.SEPARATOR +
				victimCollID + SaladConstants.SEPARATOR + tileCollID; 
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}

	/**
	 * Sets a Level's transition score 
	 * 
	 * @param scoreChange
	 * @param levelDone
	 * @param oldLevel
	 */
	public void setLevelTransitionScore(int scoreChange, String levelDone, int oldLevel){ 
		String order = SaladConstants.MODIFY_SCORE_MANAGER + SaladConstants.SEPARATOR + SaladConstants.SET_TRANSITION_SCORE + 
				SaladConstants.SEPARATOR + scoreChange + SaladConstants.SEPARATOR + levelDone + SaladConstants.SEPARATOR +
				oldLevel; 
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}

	/**
	 * Set's a scene's transition score 
	 * 
	 * @param scoreChange
	 * @param sceneDone
	 * @param oldScene
	 */	
	public void setSceneTransitionScore(int scoreChange, String sceneDone, int oldScene){ 
		String order = SaladConstants.MODIFY_SCORE_MANAGER + SaladConstants.SEPARATOR + SaladConstants.SET_TRANSITION_SCORE + 
				SaladConstants.SEPARATOR + scoreChange + SaladConstants.SEPARATOR + sceneDone + SaladConstants.SEPARATOR +
				oldScene; 
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}

	/**
	 * Set Level's transition blood 
	 * 
	 * @param changeBlood
	 * @param levelDone
	 * @param oldLevel
	 */
	public void SetLevelTransitionBlood(int changeBlood, String levelDone, int oldLevel){ 
		String order = SaladConstants.MODIFY_BLOOD_MANAGER + SaladConstants.SEPARATOR + SaladConstants.SET_TILE_COLLISION_BLOOD + SaladConstants.SEPARATOR 
				+ changeBlood + SaladConstants.SEPARATOR + levelDone + SaladConstants.SEPARATOR + oldLevel; 
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}

	/**
	 * Set scene's transition blood 
	 * 
	 * @param changeBlood
	 * @param sceneDone
	 * @param oldScene
	 */
	public void SetSceneTransitionBlood(int changeBlood, String sceneDone, int oldScene){ 
		String order = SaladConstants.MODIFY_BLOOD_MANAGER + SaladConstants.SEPARATOR + SaladConstants.SET_TILE_COLLISION_BLOOD + SaladConstants.SEPARATOR 
				+ changeBlood + SaladConstants.SEPARATOR + sceneDone + SaladConstants.SEPARATOR + oldScene; 
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}

	// prevStage e.g. Level1, Scene1.
	// newStage e.g. Level2, Scene2
	public void modifyScoreManagerTransitionScore(int score, String prevStage, String newStage){
		String order = SaladConstants.MODIFY_SCORE_MANAGER + SaladConstants.SEPARATOR +  SaladConstants.SET_TRANSITION_SCORE + 
				SaladConstants.SEPARATOR + score + SaladConstants.SEPARATOR + prevStage + SaladConstants.SEPARATOR 
				+ newStage;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}

	/**
	 * 
	 * @param changeBlood
	 * @param tileCollision
	 * @param victimCollID
	 * @param tileCollID
	 */
	public void modifyBloodManager(int changeBlood, String tileCollision, int victimCollID, char tileCollID){ 
		String order = SaladConstants.MODIFY_BLOOD_MANAGER + SaladConstants.SEPARATOR + SaladConstants.SET_TILE_COLLISION_BLOOD + SaladConstants.SEPARATOR 
				+ changeBlood + SaladConstants.SEPARATOR + tileCollision + SaladConstants.SEPARATOR + victimCollID + SaladConstants.SEPARATOR + tileCollID; 
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}



	public void modifyBloodManagerHitter(int score, int changeBlood, int victimColID, int hitterColID){
		String order = SaladConstants.MODIFY_BLOOD_MANAGER + SaladConstants.SEPARATOR +  SaladConstants.SET_COLLISION_BLOOD + 
				SaladConstants.SEPARATOR + changeBlood + SaladConstants.SEPARATOR + SaladConstants.COLLISION  + SaladConstants.SEPARATOR 
				+ victimColID + SaladConstants.SEPARATOR + hitterColID;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}

	public void modifyBloodManagerTile(int score, int changeBlood, int victimColID, int tileColID){
		String order = SaladConstants.MODIFY_BLOOD_MANAGER + SaladConstants.SEPARATOR +  SaladConstants.SET_TILE_COLLISION_BLOOD + 
				SaladConstants.SEPARATOR + changeBlood + SaladConstants.SEPARATOR + SaladConstants.TILE_COLLISION  + SaladConstants.SEPARATOR 
				+ victimColID + SaladConstants.SEPARATOR + tileColID;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}

	/**
	 * Trigger event manager using time
	 * 
	 * @param eventTriggerPairID
	 * @param triggerByTime
	 * @param time
	 */
	public void modifyTriggerEventManagerTime(int eventTriggerPairID, String triggerByTime, int time){
		String order = SaladConstants.MODIFY_TRIGGER_EVENT_MANAGER + SaladConstants.SEPARATOR + SaladConstants.ID + SaladConstants.SEPARATOR 
				+ eventTriggerPairID + SaladConstants.SEPARATOR + SaladConstants.TRIGGER_BY_TIME + SaladConstants.SEPARATOR 
				+ triggerByTime  + SaladConstants.SEPARATOR + time;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}

	/**
	 * 
	 * @param eventTriggerPairID
	 * @param triggerByRemove
	 * @param ID
	 */
	public void modifyTriggerEventManagerRemove(int eventTriggerPairID, String triggerByRemove, int ID){
		String order = SaladConstants.MODIFY_TRIGGER_EVENT_MANAGER + SaladConstants.SEPARATOR  + SaladConstants.ID  + SaladConstants.SEPARATOR +
				eventTriggerPairID + SaladConstants.SEPARATOR + SaladConstants.TRIGGER_BY_REMOVE + SaladConstants.SEPARATOR + triggerByRemove + 
				SaladConstants.SEPARATOR + ID;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}

	/**
	 * 
	 * @param eventTriggerPairID
	 * @param collision
	 * @param playerOrActorID
	 * @param xPos
	 * @param yPos
	 * @param xSize
	 * @param ySize
	 */
	public void modifyTriggerEventManagerTileCollision(int eventTriggerPairID, int collision, int playerOrActorID, int xPos, int yPos, int xSize, int ySize){
		String order = SaladConstants.MODIFY_TRIGGER_EVENT_MANAGER + SaladConstants.SEPARATOR + SaladConstants.ID + SaladConstants.SEPARATOR 
				+ eventTriggerPairID + SaladConstants.SEPARATOR + SaladConstants.TRIGGER_BY_TILE_COLLISION + SaladConstants.SEPARATOR 
			    + collision  + SaladConstants.SEPARATOR + playerOrActorID
				+ SaladConstants.SEPARATOR + xPos  + SaladConstants.SEPARATOR + yPos +  SaladConstants.SEPARATOR + xSize +  SaladConstants.SEPARATOR + ySize;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}

	/**
	 * 
	 * @param eventTriggerPairID
	 * @param eventLevelDone
	 */
	public void modifyTriggerEventManagerLevelDone(int eventTriggerPairID, String eventLevelDone){
		String order = SaladConstants.MODIFY_TRIGGER_EVENT_MANAGER + SaladConstants.SEPARATOR + SaladConstants.ID + SaladConstants.SEPARATOR 
				+ eventTriggerPairID + SaladConstants.SEPARATOR + SaladConstants.EVENT_LEVEL_DONE + SaladConstants.SEPARATOR 
				+ eventLevelDone;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}

	/**
	 * 
	 * @param eventTriggerPairID
	 * @param eventEnemyShower
	 * @param maxEnemy
	 * @param gfx
	 */
	public void modifyTriggerEventManagerEnemyShower(int eventTriggerPairID, String eventEnemyShower, int maxEnemy, String gfx){
		String order = SaladConstants.MODIFY_TRIGGER_EVENT_MANAGER + SaladConstants.SEPARATOR + SaladConstants.ID + SaladConstants.SEPARATOR 
				+ eventTriggerPairID + SaladConstants.SEPARATOR + SaladConstants.EVENT_ENEMY_SHOWER + SaladConstants.SEPARATOR 
				+ eventEnemyShower + SaladConstants.SEPARATOR + maxEnemy + SaladConstants.SEPARATOR + gfx;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}


	public void modifyLifeManagerInitLives(int lives, int playerID){
		String order = SaladConstants.MODIFY_LIFE_MANAGER + SaladConstants.SEPARATOR + SaladConstants.SET_INIT_LIVES + SaladConstants.SEPARATOR 
				+ lives + SaladConstants.SEPARATOR + playerID; 
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	/**
	 * 
	 * 
	 * @param changeOfLife
	 * @param collision
	 * @param victimCollidID
	 * @param hitterCollidID
	 */
	public void modifyLifeManagerSetCollisionLife(int changeOfLife, String collision, int victimCollidID, int hitterCollidID){
		String order = SaladConstants.MODIFY_LIFE_MANAGER + SaladConstants.SEPARATOR + SaladConstants.SET_COLLISION_LIFE + SaladConstants.SEPARATOR 
				+ changeOfLife + SaladConstants.SEPARATOR + collision + SaladConstants.SEPARATOR + victimCollidID + SaladConstants.SEPARATOR + hitterCollidID; 
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}
	
	/**
	 * 
	 * @param changeOfLife
	 * @param tileCollision
	 * @param victimCollidID
	 * @param tileCollID
	 */
	public void modifyLifeManagerSetTileCollisionLife(int changeOfLife, String tileCollision, int victimCollidID, char tileCollID){
		String order = SaladConstants.MODIFY_LIFE_MANAGER + SaladConstants.SEPARATOR + SaladConstants.SET_TILE_COLLISION_LIFE + SaladConstants.SEPARATOR 
				+ changeOfLife + SaladConstants.SEPARATOR + tileCollision + SaladConstants.SEPARATOR + victimCollidID + SaladConstants.SEPARATOR + tileCollID; 
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}

	public void modifyLifeManagerRestoreLife(boolean restore){
		String order = SaladConstants.MODIFY_LIFE_MANAGER + SaladConstants.SEPARATOR + SaladConstants.RESTORE_LIFE_BY_LEVEL + SaladConstants.SEPARATOR 
				+ String.valueOf(restore); 
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}

	public void modifyTransitionStateBackground(String gameState, String url){
		String order = SaladConstants.MODIFY_TRANSITION_STATE + SaladConstants.SEPARATOR + SaladConstants.GAME_STATE + 
				SaladConstants.SEPARATOR + gameState + SaladConstants.SEPARATOR + SaladConstants.TSBACKGROUND + 
				SaladConstants.SEPARATOR + url;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}

	public void modifyTransitionStateFrame(String gameState, int timeLength){
		String order = SaladConstants.MODIFY_TRANSITION_STATE + SaladConstants.SEPARATOR + SaladConstants.GAME_STATE + 
				SaladConstants.SEPARATOR + gameState + SaladConstants.SEPARATOR + SaladConstants.FRAME + 
				SaladConstants.SEPARATOR + timeLength;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}

	public void modifyTransitionStateMessage(String gameState, String message, int xPos, int yPos){
		String order = SaladConstants.MODIFY_TRANSITION_STATE + SaladConstants.SEPARATOR + SaladConstants.GAME_STATE + 
				SaladConstants.SEPARATOR + gameState + SaladConstants.SEPARATOR + SaladConstants.DISPLAY_MESSAGE + 
				SaladConstants.SEPARATOR + message + SaladConstants.SEPARATOR + xPos + SaladConstants.SEPARATOR + yPos;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}

	public void modifyTransitionStateImage(String gameState, String url, int xPos, int yPos){
		String order = SaladConstants.MODIFY_TRANSITION_STATE + SaladConstants.SEPARATOR + SaladConstants.GAME_STATE + 
				SaladConstants.SEPARATOR + gameState + SaladConstants.SEPARATOR + SaladConstants.DISPLAY_IMAGE + 
				SaladConstants.SEPARATOR + url + SaladConstants.SEPARATOR + xPos + SaladConstants.SEPARATOR + yPos;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}

	public void modifyGameName(String name){
		String order = SaladConstants.MODIFY_GAME + SaladConstants.SEPARATOR + SaladConstants.SET_NAME + SaladConstants.SEPARATOR +  name ;
		if (!DEBUG) myDataController.receiveOrder(order);
		System.out.println(order);
	}


	// GAE funtions below. NOT orders sending to DataController

	/**
	 * Called by Game Authorizing Environment to retrieve the current level ID
	 * @return the current level ID
	 */
	public int getCurrentLevelID(){
		return myDataController.getCurrentLevelID();
	}

	/**
	 * Called by Game Authorizing Environment to retrieve the current scene ID
	 * @return the current scene ID
	 */
	public int getCurrentSceneID(){
		return myDataController.getCurrentSceneID();
	}

	public List<String> getAttributes(){
		List<String> s = myDataController.getNonPlayer(selectedActorID).getAttributes();
		return s;
	}

	public void updateAttributesActorInfo(){
		attributesPanel.updateActorInfo(selectedActorID);
	}

	public void switchActiveAttributesTab(int index){
		attributesPanel.setTab(index);
	}

	public void switchLibraryTab(int index){
		libraryPanel.setTab(index);
	}

	public int getActorID(){
		return selectedActorID;
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


	public void setActorPanelSelection(int actorID){
		switchLibraryTab(1);
		ActorsPanel ap= (ActorsPanel) panelMap.get(SaladConstants.ACTOR_PANEL);
		ap.update(actorID);
	}


	/**Modify the thumbnail in Actor panel*/
	public void updateActorImage(String imageURL, String name){
		ActorsPanel ap= (ActorsPanel) panelMap.get(SaladConstants.ACTOR_PANEL);
		ap.setActorImage(selectedActorID, imageURL, name);
	}

	/**Called to get the url image into our engine package*/
	public void uploadImage(int xSize,int ySize, String url){
		try {
			if (!DEBUG) myDataController.uploadImage(xSize, ySize, url);
			return;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getActorCollisionID(){
		//myDataController.getGame().getNonPlayerColid(int  int sceneidint id)
		return 0;
	}

	public int getSelectedIDFromDataController(){
		return myDataController.getSelectedID();
	}

	public Map<String, Integer> getKeyMap(int playerId2){
		System.out.println("map");
		if(myDataController.getGame()!=null){
			if(myDataController.getGame().getPlayer(playerId2)!=null){
				if(myDataController.getGame().getPlayer(playerId2).getKeyMap()!=null){
					Map<Integer, String> map = myDataController.getGame().getPlayer(playerId2).getKeyMap();
					System.out.println("map");

					HashMap<String, Integer> map2 = new HashMap<String, Integer>();
					System.out.println("map");

					for(Integer K : map.keySet()){
						String str = map.get(K);
						System.out.println(str);
						map2.put(str, K);
					}
					System.out.println("map2");

					return map2;}}}
		Map<String, Integer> k = new HashMap<String, Integer>();
		return k;
	}

	private void setColIDStayOnEveryTileID(int actorColID){
		System.out.println(myDataController.getGame().getOccupiedTileColids());
		for(char tileID :myDataController.getGame().getOccupiedTileColids()){
			this.modifyCollisBehavStayOnTile(actorColID, tileID, "All");
		}
	}
	
	public Map<Integer, NonPlayer> getMapOfPlayers(){
		Map<Integer, NonPlayer> map = myDataController.getMapOfNonPlayers(selectedSceneID);
		return map;
	}

	public List<GameObject> getObjectsByColid(int scene, int colID){ 
		int levelID = getCurrentLevelID();
		List<GameObject> objectColID = myDataController.getGame().getLevel(levelID).getScene(scene).getObjectsByColid(colID);
		return objectColID;
	}
	
	public NonPlayer getNonPlayer(){
		return myDataController.getNonPlayer(selectedSceneID);
	}


	public int getPlayerID() {
		return playerID;
	}
	
}
