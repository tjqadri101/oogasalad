package engine;

import saladConstants.SaladConstants;
import stage.Game;
import stage.Scene;
import stage.Transition;
import statistics.StatsController;
import jgame.JGColor;
import jgame.platform.StdGame;
import objects.GameObject;
import objects.Gravity;
import objects.NonPlayer;
import objects.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import engineManagers.TriggerEventManager;

/**
 * @Author: Isaac (Shenghan) Chen
 * @Contribution: Justin (Zihao) Zhang
 * @Contribution Steve (Siyang) Wang
 */

@SuppressWarnings("serial")
public class GameEngine extends StdGame {

	public static final int FRAMES_PER_SECOND = 70;
	public static final int MAX_FRAMES_TO_SKIP = 2;
	public static final int JGPOINT_X = 800;//
	public static final int JGPOINT_Y = 600;//
	public static final int CANVAS_WIDTH = 40;
	public static final int CANVAS_HEIGHT = 30;
	public static final int TILE_WIDTH = 20;
	public static final int TILE_HEIGHT = 20;

	protected Game myGame;
	protected int myCurrentLevelID;
	protected int myCurrentSceneID;
	protected Scene myCurrentScene;
//	protected Scene myEmptyScene = new Scene(0);
	protected Player myPlayer;
	protected int myTimer;
	
	protected int myMouseX;
	protected int myMouseY;
	protected int myMouseButton;
	protected int myClickedID;
	protected boolean myViewOffsetPlayer = true;
	protected int myViewOffsetRate = 1;

	protected int myTileX;
	protected int myTileY;
	protected char myTileCid;

	protected boolean isEditingMode;
	protected boolean isLoading;
	protected boolean isPlaying;
	protected boolean isTileEditing;
	protected boolean scene_restart = true;//
	protected StatsController myStatsController;
	
	public GameEngine(boolean editing) {
		initEngineComponent(JGPOINT_X, JGPOINT_Y);
		isEditingMode = editing;
		isLoading = true;
		isPlaying = editing;
	}

	@Override
	public void initCanvas() {
		setCanvasSettings(CANVAS_WIDTH, CANVAS_HEIGHT, TILE_WIDTH, TILE_HEIGHT, null, null, null);
	}

	@Override
	public void initGame() {
		setFrameRate(FRAMES_PER_SECOND, MAX_FRAMES_TO_SKIP);
//		setTileSettings("#",2,0);
		defineImage("null","0",0,"null","-");
		setGameState("Edit");
		myTimer = 0;
		lives = 1;
	}
	
//	public boolean checkGoal() {
//		if (myCurrentScene == null)
//			return false;
//		String winBehavior = myGame.getLevel(myCurrentLevelID).getWinBehavior();
//		if (winBehavior == null)
//			return false;
//		List<Object> winParameters = myGame.getLevel(myCurrentLevelID)
//				.getWinParameters();
//		ResourceBundle behaviors = ResourceBundle
//				.getBundle(SaladConstants.DEFAULT_ENGINE_RESOURCE_PACKAGE
//						+ SaladConstants.OBJECT_BEHAVIOR);
//		Object answer = SaladUtil.behaviorReflection(behaviors, winBehavior,
//				winParameters, "checkGoal", this);
//		return (Boolean) answer;
//	}

	public boolean checkTrigger() {
		// consider combineing the checkTrigger to checkGoal()
		return true;
	}
	
	public void loadingBegin() {
		if (isEditingMode) {return;}
		isLoading = true;
		isPlaying = false;
		hideScene();
		myCurrentScene = new Scene(0);
		showScene();
	}
	
	public void loadingDone() {
		if (isEditingMode) {return;}
		isLoading = false;
		isPlaying = true;
		finishLoading();
	}
	
	private void finishLoading() {
		int ticks1 = myGame.getTransitionState("StartGame").getFrame();
		if (ticks1 > -1) {leveldone_ticks = ticks1;}
		int ticks2 = myGame.getTransitionState("LevelDone").getFrame();
		if (ticks2 > -1) {leveldone_ticks = ticks2;}
		int ticks3 = myGame.getTransitionState("LifeLost").getFrame();
		if (ticks3 > -1) {leveldone_ticks = ticks3;}
		int ticks4 = myGame.getTransitionState("GameOver").getFrame();
		if (ticks4 > -1) {leveldone_ticks = ticks4;}
		//reflection
		if (inGameState("Title")) {setTransition("Title");System.out.println("???");}
	}
	
	
	
	public void defineLevel() {
		setCurrentScene(level+1, myGame.getLevel(level+1).getInitialSceneID());
	}
	
	public void initNewLife() {
		myPlayer.resume();
		myPlayer.restore(scene_restart);
		if (scene_restart){
			for (GameObject object : myCurrentScene.getGameObjects()) {
				object.restore(true);
			}
		}
	}
	
	
	
	public void doFrameInGame() {
		doFrameEdit();
		
	}
	
	public void paintFrameInGame() {
		paintFrameEdit();
	}
	
	
	
	// drag;move->gravity->collision->setViewOffset
	public void doFrameEdit() {
		myTimer += this.getGameSpeed();
	        if (myGame==null){return;}
	        TriggerEventManager myTEM = getGame().getTEManager();
		if (myCurrentScene == null) {return;}
		boolean viewOffset = false;
		if (drag()) {myViewOffsetPlayer = false;}
		else {
			moveObjects();
			applyG();
			checkAllCollision();
			viewOffset = setViewOffsetEdit();
			if (!viewOffset) {setViewOffsetPlayer();}
			else {myViewOffsetPlayer = false;}
		}
		if (!viewOffset) {setViewOffsetEdit();}
		myTEM.checkTrigger(this);
//		if (checkGoal()) {
//			if (level >= 3) {
//				gameOver();
//			} else
//				levelDone();
//		}
		if (myGame != null) {
			if (myGame.getScoreManager() != null) {
				myGame.getScoreManager().update("Time");
				score = myGame.getScoreManager().getCurrentValue();
			}
			if (myGame.getLiveManager() != null && myPlayer != null) {
				int current_lives = myGame.getLiveManager().getCurrentLife(myPlayer.getID());
				if (current_lives < lives) {
					lives = current_lives+1;
					lifeLost();
				}
				else {lives = current_lives;}
			}
		}
		timer ++; //add by Justin
	}
	
	public void paintFrameEdit() {
		displayPlayerInfo();
		disPlayDragTile();
		
//		if (checkGoal()) {
//			drawString("Win!!!!!!!!!!!!!!!!", viewWidth() / 2,
//					viewHeight() / 2 + 100, 0, false);
//		}
//		if (checkGoal()) {
//			// call the event module
//		}
	}
	
	
	
	@Override
	public void doFrame() {
		if (getKey('L')) {levelDone();clearKey('L');} //cheat key for testing
		if (getKey('K')) {lifeLost();clearKey('K');} //cheat key for testing
		if (!isEditingMode && isPlaying) {
			super.doFrame();
		}
	}

	@Override
	public void paintFrame() {
		if (!isEditingMode && isPlaying) {
			super.paintFrame();
		}
	}
	
	/* start<state> methods */
	
	public void startTitle() {
		setTransition("Title");
	}

	public void startStartGame() {
		setTransition("StartGame");
	}
	
	public void startStartLevel() {
		setTransition("StartLevel");//
	}

	public void startLevelDone() {
		myGame.getScoreManager().update("LevelDone", myCurrentLevelID);
		setTransition("LevelDone");
	}

	public void startLifeLost() {
		setTransition("LifeLost");
	}

	public void startGameOver() {
		hideScene();
		myCurrentScene = new Scene(0);
		showScene();
		setTransition("GameOver");
	}
	
	 /* paintFrame<state> methods */
	
	@Override
	 public void paintFrameTitle(){
		 super.paintFrameTitle();//
		 paintTransition("Title");
	 }
	
	 @Override
	 public void paintFrameLevelDone(){
		 super.paintFrameLevelDone();//
		 paintTransition("LevelDone");
	 }
	
	 @Override
	 public void paintFrameLifeLost(){
		 super.paintFrameLifeLost();//
		 paintTransition("LifeLost");
	 }
	
	 @Override
	 public void paintFrameGameOver(){
		 super.paintFrameGameOver();//
		 paintTransition("GameOver");
		 setGameState("DisplayStats"); // added by Justin
	 }
	 
	 @Override
	 public void paintFrameStartGame(){
		 super.paintFrameStartGame();//
		 paintTransition("StartGame");
	 }
	 
	 //
	 @Override
	 public void paintFrameStartLevel(){
		 super.paintFrameStartLevel();//
		 paintTransition("StartLevel");
	 }
	
//	 @Override
//	 public void paintFrameEnterHighscore(){
//		 super.paintFrameEnterHighscore();//
//		 paintTransition("EnterHighscore");
//	 }
//	
//	 @Override
//	 public void paintFrameHighscores(){
//		 super.paintFrameHighscores();//
//		 paintTransition("Highscores");
//	 }
//	
//	 @Override
//	 public void paintFramePaused(){
//		 super.paintFramePaused();//
//		 paintTransition("Paused");
//	 }
	 
	 
	 
	 /* doFrame methods */
	 
	 private void checkAllCollision() {
		 for (int[] pair : myGame.getCollisionManager().getCollisionPair()) {
			 checkCollision(pair[1], pair[0]);
		 }
		 for (int[] pair : myGame.getCollisionManager().getTileCollisionPair()) {
			 checkBGCollision(pair[1], pair[0]);
		 }
	 }

	 private void applyG() {
		 Gravity g = myGame.getGravity();
		 g.applyGravity(myPlayer);
		 for (GameObject object : myCurrentScene.getGameObjects()) {
			 g.applyGravity(object);
		 }
	 }

	 private void setViewOffsetPlayer() {
		 if (myPlayer != null && myPlayer.isAlive() && !myPlayer.is_suspended) {
			 if (myViewOffsetRate > 1) {
				 myViewOffsetRate -= 1;
			 }
			 if (isEditingMode && !myViewOffsetPlayer) {
				 myViewOffsetRate = 35; //make it constant later
			 }
			 myViewOffsetPlayer = true;
			 int desired_viewXOfs = (int) myPlayer.x + myPlayer.getXSize() / 2 - viewWidth() / 2;
			 int desired_viewYOfs = (int) myPlayer.y + myPlayer.getYSize() / 2 - viewHeight() / 2;
			 setViewOffset((desired_viewXOfs - viewXOfs()) / myViewOffsetRate + viewXOfs(),
					 (desired_viewYOfs - viewYOfs()) / myViewOffsetRate + viewYOfs(), false);
		 }
	 }

	 private boolean setViewOffsetEdit() {
		 int speed = 10; //make it constant later
		 double margin = 0.1; //make it constant later
		 if (!isEditingMode) {return false;}
		 int XOfs = 0;
		 int YOfs = 0;
		 double x_ratio = 1.0 * getMouseX() / viewWidth();
		 double y_ratio = 1.0 * getMouseY() / viewHeight();
		 if (0 < x_ratio && x_ratio < margin) {
			 XOfs -= speed * (1 - x_ratio / margin);
		 }
		 if ((1 - margin) < x_ratio && x_ratio < 1) {
			 XOfs += speed * (1 - (1 - x_ratio) / margin);
		 }
		 if (0 < y_ratio && y_ratio < margin) {
			 YOfs -= speed * (1 - y_ratio / margin);
		 }
		 if ((1 - margin) < y_ratio && y_ratio < 1) {
			 YOfs += speed * (1 - (1 - y_ratio) / margin);
		 }
		 setViewOffset(viewXOfs() + XOfs, viewYOfs() + YOfs, false);
		 return XOfs != 0 || YOfs != 0;
	 }

	 /* display methods */

	 private void disPlayDragTile() {
		 if (myMouseButton != 0 && myClickedID == -1 && isTileEditing) {
			 int tileX = myMouseX / 20;
			 int tileY = myMouseY / 20;
			 if (myMouseButton == 3) {
				 setColor(JGColor.red);// should only be applied to the last line
			 }
			 drawRect((double) Math.min(myTileX, tileX) * tileWidth(),
					 (double) Math.min(myTileY, tileY) * tileHeight(),
					 (double) (Math.abs(myTileX - tileX) + 1) * tileWidth(),
					 (double) (Math.abs(myTileY - tileY) + 1) * tileHeight(), false, false);
		 }
	 }
	 
	 private void displayPlayerInfo() {
		 if (myPlayer != null && myPlayer.isAlive() && !myPlayer.is_suspended) {
			 drawRect(myPlayer.x + myPlayer.getXSize() / 2,
					 myPlayer.y - myPlayer.getYSize() / 13.5,
					 myPlayer.getXSize() / 2, 10, false, true);
			 drawRect(myPlayer.x + 0.5 * (1 + myPlayer.getBlood() / myPlayer.getInitBlood()) * myPlayer.getXSize() / 2,
					 myPlayer.y - myPlayer.getYSize() / 13.5,
					 (1.0 * myPlayer.getBlood() / myPlayer.getInitBlood()) * myPlayer.getXSize() / 2,
					 10, true, true);
			 drawString("lol help!", myPlayer.x + myPlayer.getXSize() / 2,
					 myPlayer.y - myPlayer.getYSize() / 3, 0, true);
		 }
	 }

	 /* dragging object & tile methods */

	 private boolean drag() {
		 if (!isEditingMode) {return false;}
		 boolean drag = false;
		 boolean currentMouse1 = getMouseButton(1);
		 boolean currentMouse3 = getMouseButton(3);
		 int MouseX = getMouseX() + viewXOfs();
		 int MouseY = getMouseY() + viewYOfs();
		 int tileX = Math.max(MouseX / 20, 0);
		 int tileY = Math.max(MouseY / 20, 0);

		 if (myMouseButton != 1 && currentMouse1) {
			 myClickedID = getClickedID();
			 myTileX = tileX;
			 myTileY = tileY;
		 }
		 if (myMouseButton == 1 && !currentMouse1 && isTileEditing) {
			 if (myClickedID == -1) {
				 createTiles(myTileCid, Math.min(myTileX, tileX),
						 Math.min(myTileY, tileY),
						 Math.abs(myTileX - tileX) + 1,
						 Math.abs(myTileY - tileY) + 1);
			 }
			 myClickedID = -1;
		 }
		 if (myMouseButton == 1 && currentMouse1) {
			 GameObject object = myCurrentScene.getNonPlayer(myClickedID);
			 if (myPlayer != null && myClickedID == myPlayer.getID()) {
				 object = myPlayer;
			 }
			 if (object != null) {
				 object.x += MouseX - myMouseX;
				 object.y += MouseY - myMouseY;
				 drag = true;
			 }
		 }

		 if (myMouseButton != 3 && currentMouse3) {
			 myTileX = tileX;
			 myTileY = tileY;
		 }
		 if (myMouseButton == 3 && !currentMouse3 && isTileEditing) {
			 createTiles('0', Math.min(myTileX, tileX),
					 Math.min(myTileY, tileY), Math.abs(myTileX - tileX) + 1,
					 Math.abs(myTileY - tileY) + 1);
		 }
		 myMouseButton = 0;
		 if (currentMouse1) {myMouseButton = 1;}
		 if (currentMouse3) {myMouseButton = 3;}
		 myMouseX = MouseX;
		 myMouseY = MouseY;
		 return drag;
	 }

	 public int getClickedID() {
		 List<GameObject> list = new ArrayList<GameObject>();
		 if (getMouseButton(1)) {
			 int MouseX = getMouseX() + viewXOfs();
			 int MouseY = getMouseY() + viewYOfs();
			 if (myPlayer != null && myPlayer.isAlive() && !myPlayer.is_suspended
					 && myPlayer.x < MouseX && MouseX < myPlayer.x + myPlayer.getXSize()
					 && myPlayer.y < MouseY
					 && MouseY < myPlayer.y + myPlayer.getYSize()) {
				 list.add(myPlayer);
			 }
			 for (GameObject go : myCurrentScene.getGameObjects()) {
				 if (go.isAlive() && !go.is_suspended && go.x < MouseX
						 && MouseX < go.x + go.getXSize() && go.y < MouseY
						 && MouseY < go.y + go.getYSize()) {
					 list.add(go);
				 }
			 }
		 }
		 if (list.isEmpty()) {
			 return -1;
		 }
		 int id = list.get(0).getID();
		 getParent().firePropertyChange("clickedID", 0, id);
		 return id;
	 }
	
	 //private
	 public void createTiles(char cid, int left, int top,
			 int width, int height) {
		 String temp = "";
		 for (int i = 0; i < width; i++) {
			 temp += cid;
		 }
		 String[] array = new String[height];
		 for (int j = 0; j < height; j++) {
			 array[j] = temp;
		 }
		 if(isPlaying) setTiles(left, top, array);
		 myCurrentScene.updateTiles(cid, left, top, width, height);
	 }
	
	 /* loading image & tile info methods */

	 public void createTilesFromString(String tiles){
		 String[] array = tiles.split(SaladConstants.SPACE);
		 myCurrentScene.setTiles(array);
	 }

	 public void loadTileImage(char cid, String imgfile){
		 defineImage(cid+"",cid+"",cid,imgfile,"-");
		 myGame.defineTileImage(cid, imgfile);
		 if(isEditingMode) {myTileCid = cid;}
	 }

	 private void loadImage(String imgfile) {
		 if (imgfile == null) {return;}
		 defineImage(imgfile, "-", 0, imgfile, "-");
	 }

	 /* switching scene & transition state methods */

	 private void setTransition(String gameState) {
		 if (myGame == null) {return;}
		 Transition trans = myGame.getTransitionState(gameState);
		 if (trans == null) {return;}
		 loadImage(trans.getBackground());
		 setBGImage(trans.getBackground());
	 }

	 private void paintTransition(String gameState) {
		 if (myGame == null) {return;}
		 Transition trans = myGame.getTransitionState(gameState);
		 if (trans == null) {return;}
		 for (Entry<double[], String> entry: trans.getInstructions()) {
			 double x = entry.getKey()[0];
			 double y = entry.getKey()[1];
			 String instruction = entry.getValue();
			 drawString(instruction, x, y, 0);
		 }
		 for (Entry<double[], String> entry: trans.getImages()) {
			 double x = entry.getKey()[0];
			 double y = entry.getKey()[1];
			 String imgfile = entry.getValue();
			 loadImage(imgfile);
			 drawImage(imgfile, x, y, false);
		 }
	 }

	 public void setCurrentScene(int currentLevelID, int currentSceneID) {
		 hideScene();
		 myCurrentLevelID = currentLevelID;
		 myCurrentSceneID = currentSceneID;
		 myCurrentScene = myGame.getScene(myCurrentLevelID, myCurrentSceneID);
		 showScene();
	 }

	private void hideScene() {
		if (myCurrentScene != null) {
			 for (GameObject object : myCurrentScene.getGameObjects()) {
				 object.suspend();
			 }
			 if (myPlayer != null) {myPlayer.suspend();}
			 removeObjects(null, 0, false);
		 }
		 if(isPlaying) {myGame.getScoreManager().update("SceneDone", myCurrentSceneID);}
	}

	private void showScene() {
		setSceneView(myCurrentScene.getBackgroundImage(), myCurrentScene.ifWrapHorizontal(), 
				 myCurrentScene.ifWrapVertical(), myCurrentScene.getXSize(), myCurrentScene.getYSize());
		 setTiles(0, 0, myCurrentScene.getTiles());
		 if (myPlayer != null) {
			 myPlayer.setInitPos(myCurrentScene.getPlayerInitPosition()[0],
					 myCurrentScene.getPlayerInitPosition()[1]);
			 myPlayer.resume();
		 }
		 for (GameObject object : myCurrentScene.getGameObjects()) {
			 object.resume();
		 }
	}
	
	 /* scene view modification methods */

	 public void setSceneView(String fileName,
			 boolean wrapx, boolean wrapy, int xsize, int ysize) {
		 setBackground(fileName);
		 setSceneSize(xsize, ysize);
		 setSceneWrap(wrapx, wrapy);
	 }

	 private void setBackground(String fileName) {
		 if (isLoading) {
			 myCurrentScene.setBackgroundImage(fileName);
			 loadImage(fileName);
		 }
		 if (isPlaying) {setBGImage(fileName);}
	 }

	 private void setSceneSize(int xsize, int ysize) {
		 if (isLoading) {
			 myCurrentScene.resizeTiles(xsize, ysize);
			 myCurrentScene.setSize(xsize, ysize);
		 }
		 if (isPlaying) {setPFSize(xsize, ysize);}
	 }

	 private void setSceneWrap(boolean wrapx, boolean wrapy) {
		 if (isLoading) {myCurrentScene.setWrap(wrapx, wrapy);}
		 if (isPlaying) {setPFWrap(wrapx, wrapy,0,0);}
	 }

	 /* game, level, scene setter & getter methods */

	 public void setGame(Game mygame) {
		 myGame = mygame;
		 myStatsController = new StatsController(this, myGame.getName());
	 }

	 public Game getGame() {
//	     if (myGame == null) {return null;}
		 return myGame;
	 }
	 
	 public int getSaladTimer() {
		 return myTimer;
	 }

	 public int getCurrentLevelID() {
		 return myCurrentLevelID;
	 }

	 public int getCurrentSceneID() {
		 return myCurrentSceneID;
	 }

	 /* object creation & image modification methods */

	 private void modifyImage(GameObject object, String imgfile, int xsize, int ysize) {
		 loadImage(imgfile);
		 object.setImage(imgfile);
		 object.setStaticGfx(imgfile);//same thing
		 object.setSize(xsize, ysize);
		 object.updateImageURL(imgfile);
	 }

	 

	 public void setObjectImage(GameObject object, String action, String imgfile, int xsize, int ysize){
		 loadImage(imgfile);
		 object.setSize(xsize, ysize);
//		 object.modifyDynamicImage(action, imgfile, xsize, ysize);
	 }

	 public void modifyActorImage(int unique_id, String imgfile, int xsize, int ysize) {
		 GameObject object = myGame.getNonPlayer(myCurrentLevelID,
				 myCurrentSceneID, unique_id);
		 modifyImage(object, imgfile, xsize, ysize);
	 }

	 public void modifyPlayerImage(int unique_id, String imgfile, int xsize, int ysize) {
		 GameObject object = myGame.getPlayer(unique_id);
		 modifyImage(object, imgfile, xsize, ysize);
	 }

	 public Player createPlayer(int unique_id, String imgfile, int xsize, int ysize,
			 double xpos, double ypos, String name, int colid, int lives) {
		 //	        TriggerEventManager etm = getGame().getTEM();
		 loadImage(imgfile);
		 Player object = new Player(unique_id, imgfile, xsize, ysize, xpos, ypos,
				 name, colid, lives, myGame.getCollisionManager(),
				 myGame.getScoreManager(), myGame.getBloodManager(), 
				 myGame.getRevivalManager(), myGame.getLiveManager(), myGame.getTEManager());
		 myGame.setPlayer(object);
		 myPlayer = object;
		 if (myGame.getLiveManager() != null) {
			 myGame.getLiveManager().addPlayer(object);
		 }
		 if (isPlaying) {object.resume();}
		 return object;
	 }

	 public NonPlayer createActor(int unique_id, String imgfile, int xsize,
			 int ysize, double xpos, double ypos, String name, int colid, int lives) {
		 //	        TriggerEventManager etm = getGame().getTEM();
		 loadImage(imgfile);
		 NonPlayer object = new NonPlayer(unique_id, imgfile, xsize, ysize, xpos,
				 ypos, name, colid, lives, myGame.getCollisionManager(),
				 myGame.getScoreManager(), myGame.getBloodManager(), 
				 myGame.getRevivalManager(), myGame.getLiveManager(),myGame.getTEManager());
		 if (unique_id != SaladConstants.NULL_UNIQUE_ID) {myCurrentScene.addNonPlayer(object);}
		 if (isPlaying) {object.resume();}
		 return object;
	 }
	 
	 public void startDisplayStats() {
		 myStatsController.saveGameStats(); 
	 }

	 public void paintFrameDisplayStats() {
		 myStatsController.displayStats();
	 }

	 public void reviveObject() {
		 myGame.getRevivalManager().undo();
	 }
	 
	 public void setTileEditing(boolean tile_editing) {
		 isTileEditing = tile_editing;
	 }

}
