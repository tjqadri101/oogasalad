package engine;

import saladConstants.SaladConstants;
import stage.Game;
import stage.Scene;
import stage.Transition;
import statistics.StatsController;
import util.Music;
import jgame.JGColor;
import jgame.platform.StdGame;
import objects.GameObject;
import objects.Gravity;
import objects.NonPlayer;
import objects.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import engineManagers.SoundManager;

/**
 * @author Main Isaac (Shenghan) Chen
 * @contribution (for the two methods of creating player and actor): Justin (Zihao) Zhang
 * @contribution (for TriggerEventManager handling) Steve (Siyang) Wang
 */

@SuppressWarnings("serial")
public class GameEngine extends StdGame {

	public static final String BUFFER_IMAGE_FOLDER = "ImageBuffer/";
	
	public static final int FRAMES_PER_SECOND = 20;
//	public static final int FRAMES_PER_SECOND = 10;
	public static final int MAX_FRAMES_TO_SKIP = 2;
	public static final int JGPOINT_X = 800;
	public static final int JGPOINT_Y = 600;
	public static final int CANVAS_WIDTH = 40;
	public static final int CANVAS_HEIGHT = 30;
	public static final int TILE_WIDTH = 20;
	public static final int TILE_HEIGHT = 20;

	protected Game myGame;
	protected int myCurrentLevelID;
	protected int myCurrentSceneID;
	protected Scene myCurrentScene;
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
	protected int myTileCounter = 0;
	protected String myTitleBG;
	
	protected String myGameStateString;
	protected int myGameStateFrame = 0;
	protected boolean isEditingMode;
	protected boolean isLoading;
	protected boolean isPlaying;
	protected boolean isTileEditing;
	protected boolean isPaused;
	protected boolean scene_restart = true;
	protected StatsController myStatsController;
//	protected Music musicManager;
//	protected SoundManager mySoundManager;
	
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
		if (isEditingMode) {setGameState("Edit");}
		myTimer = 0;
		lives = 1;

//                musicManager = new Music("src/engine/Sounds/PlayJoyful.wav");
//                System.out.println("loadingDone: initGame is called here");
//		mySoundManager = new SoundManager(); // newly Added for testing music
//		mySoundManager.setSound("src/engine/Sounds/PlayJoyful.wav");
////		getGame().getSoundManager().chooseInitSound();

//		getGame().getSoundManager().chooseInitSound();
//		musicManager.start();
		
	}
	
	public void loadingBegin() {
		if (isEditingMode) {return;}
		setEmptyScene();
		isLoading = true;
		isPlaying = false;
		myTimer = 0;
//		System.out.println("loadingBegin");
	}
	
	public void loadingDone() {
		if (isEditingMode) {return;}
		finishLoading();
		isLoading = false;
		isPlaying = true;
	}
	
	private void finishLoading() {
		//reflection
		int ticks1 = myGame.getTransitionState("StartGame").getFrame();
		if (ticks1 > -1) {leveldone_ticks = ticks1;}
		int ticks2 = myGame.getTransitionState("LevelDone").getFrame();
		if (ticks2 > -1) {leveldone_ticks = ticks2;}
		int ticks3 = myGame.getTransitionState("LifeLost").getFrame();
		if (ticks3 > -1) {leveldone_ticks = ticks3;}
		int ticks4 = myGame.getTransitionState("GameOver").getFrame();
		if (ticks4 > -1) {leveldone_ticks = ticks4;}
	}
	
	
	
	public void defineLevel() {
		setCurrentScene(level+1, myGame.getLevel(level+1).getInitialSceneID());
	}
	
	public void initNewLife() {
		myPlayer.resume();
		if (scene_restart){
			removeObjects(null, 0, false);
			for (GameObject object : myCurrentScene.getGameObjects()) {
				object.restore(true);
			}
		}
		myPlayer.restore(scene_restart);
	}
	
	public void doFrameInGame() {
		doFrameEdit();	
	}
	
	public void paintFrameInGame() {
		paintFrameEdit();
	}
	
	
	
	// drag;move->gravity->collision->setViewOffset
	public void doFrameEdit() {
		if (myGame==null || myCurrentScene == null) {return;}
		boolean viewOffset = false;
		if (drag() || isPaused) {myViewOffsetPlayer = false;}
		else {
//			System.out.println("doFrameEdit");
			moveObjects();
			applyG();
			checkAllCollision();
			viewOffset = setViewOffsetEdit();
			if (!viewOffset) {setViewOffsetPlayer();}
			else {myViewOffsetPlayer = false;}
		}
		if (!viewOffset) {setViewOffsetEdit();}
		doManagers();
	}
	
	private void doManagers() {
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
		if (myGame.getInputManager() != null) {
			myGame.getInputManager().checkKey();
		}
		if (myGame.getTriggerManager() != null) {
			myGame.getTriggerManager().checkTrigger();
		}
	}
	
	public void paintFrameEdit() {
		displayPlayerInfo();
		disPlayDragTile();
		if (myGameStateString != null && myGameStateFrame < FRAMES_PER_SECOND) {
			drawString(myGameStateString,viewWidth()/2,viewHeight()/3,0);
			myGameStateFrame++;
		}
	}
	
	
	
	@Override
	public void doFrame() {
//		System.out.println("\n doFrame");
		myTimer += getGameSpeed();
		if (!isEditingMode && isPlaying) {
			super.doFrame();
		}
	}

	@Override
	public void paintFrame() {
//		System.out.println("\n paintFrame");
		if (!isEditingMode && isPlaying) {
			super.paintFrame();
		}
		if (!isEditingMode && isLoading) {
			String loading = "Loading";
			String dot = ".";
			int cycle = 6;
			int frame = 20;
			int number = myTimer % (((int)getGameSpeed())*cycle*frame);
			for (int i = 0; i < number/frame; i++) {
				loading += dot;
			}
			drawString(loading, viewWidth()/2, viewHeight()/2, 0);
		}
	}
	
	/* start<state> methods */
	
	public void startTitle() {
		setTransition("Title");
	}

	public void startStartGame() {
		setTransition("StartGame");
	}
	
//	public void startStartLevel() {
//		setTransition("StartLevel");
//	}

	public void startLevelDone() {
		myGame.getScoreManager().update("LevelDone", myCurrentLevelID);
		setTransition("LevelDone");
	}

	public void startLifeLost() {
		setTransition("LifeLost");
	}

	public void startGameOver() {
		setEmptyScene();
		setTransition("GameOver");
	}
	
	public void doFrameTitle(){
		if (isEditingMode) {setTransition("GameOver");}
	 }
	
	 /* paintFrame<state> methods */
	
	@Override
	 public void paintFrameTitle(){
		 paintTransition("Title");
		 if (myGame.getTransitionState("Title") != null &&
				 myGame.getTransitionState("Title").getInstructions().isEmpty()) {
			 super.paintFrameTitle();
		 }
	 }
	
	@Override
	 public void paintFrameStartGame(){
		 paintTransition("StartGame");
		 if (myGame.getTransitionState("StartGame") != null &&
				 myGame.getTransitionState("StartGame").getInstructions().isEmpty()) {
			 super.paintFrameStartGame();
		 }
	 }
	
//	@Override
//	 public void paintFrameStartLevel(){
//		 paintTransition("StartLevel");
//		 if (myGame.getTransitionState("StartLevel") != null &&
//				 myGame.getTransitionState("StartLevel").getInstructions().isEmpty()) {
//			 super.paintFrameStartLevel();
//		 }
//	 }
	
	 @Override
	 public void paintFrameLevelDone(){
		 paintTransition("LevelDone");
		 if (myGame.getTransitionState("LevelDone") != null &&
				 myGame.getTransitionState("LevelDone").getInstructions().isEmpty()) {
			 super.paintFrameLevelDone();
		 }
	 }
	
	 @Override
	 public void paintFrameLifeLost(){
		 paintTransition("LifeLost");
		 if (myGame.getTransitionState("LifeLost") != null &&
				 myGame.getTransitionState("LifeLost").getInstructions().isEmpty()) {
			 super.paintFrameLifeLost();
		 }
	 }
	
	 @Override
	 public void paintFrameGameOver(){
		 paintTransition("GameOver");
		 if (myGame.getTransitionState("GameOver") != null &&
				 myGame.getTransitionState("GameOver").getInstructions().isEmpty()) {
			 super.paintFrameGameOver();
		 }
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
	 
	 public void gotoGameState(String gameState){
		 setEmptyScene();
		 setGameState(gameState);
	 }
	 
	 public void gotoTitle(){
		 setGameState("DisplayStats");
	 }
	 
	 
	 
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
			 int xofs = JGPOINT_X/2 - myPlayer.getXofs();
			 int yofs = JGPOINT_Y/2 - myPlayer.getYofs();
			 int desired_viewXOfs = (int) myPlayer.x + myPlayer.getXSize() / 2 - viewWidth() / 2;
			 int desired_viewYOfs = (int) myPlayer.y + myPlayer.getYSize() / 2 - viewHeight() / 2;
			 setViewOffset((desired_viewXOfs - viewXOfs() + xofs) / myViewOffsetRate + viewXOfs(),
					 (desired_viewYOfs - viewYOfs() + yofs) / myViewOffsetRate + viewYOfs(), false);
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
		 if (myMouseButton == 0 && myTileCounter > FRAMES_PER_SECOND) {
			 int tileX = myTileX * TILE_WIDTH + TILE_WIDTH / 2;
			 int tileY = myTileY * TILE_HEIGHT + TILE_HEIGHT / 2;
			 drawString(tileX+" "+tileY, tileX, tileY, 0, true);
		 }
	 }
	 
	 private void displayPlayerInfo() {
		 if (myPlayer != null && myPlayer.isAlive() && !myPlayer.is_suspended) {
			 drawString(myPlayer.getObjectName(), myPlayer.x + myPlayer.getXSize() / 2,
					 myPlayer.y - myPlayer.getYSize() / 3, 0, true);
			 
			 double blood = 1.0 * myPlayer.getBlood() / myPlayer.getInitBlood();
			 if (blood <= 1.0 && blood > 0.5) {setColor(JGColor.green);}
			 if (blood <= 0.5 && blood > 0.25) {setColor(JGColor.yellow);}
			 if (blood <= 0.25) {setColor(JGColor.red);}
			 
			 drawRect(myPlayer.x + 0.5 * (1 + blood) * myPlayer.getXSize() / 2,
					 myPlayer.y - myPlayer.getYSize() / 13.5,
					 blood * myPlayer.getXSize() / 2,10, true, true);
			 drawRect(myPlayer.x + myPlayer.getXSize() / 2,
					 myPlayer.y - myPlayer.getYSize() / 13.5,
					 myPlayer.getXSize() / 2, 10, false, true);
//			 System.out.println(myPlayer.getBlood() +" "+ myPlayer.getInitBlood());
			 setColor(JGColor.white);
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
			 GameObject object = null;
			 GameObject actor = myCurrentScene.getNonPlayer(myClickedID);
			 GameObject player = myGame.getPlayer(myClickedID);
			 if (actor != null) {object = actor;}
			 if (player != null) {object = player;}
			 if (object != null) {
				 object.x += MouseX - myMouseX;
				 object.y += MouseY - myMouseY;
				 drag = true;
				 getParent().firePropertyChange("updatePos", 0, myClickedID);
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
		 
		 if (myMouseButton == 0 && !currentMouse1 && !currentMouse3) {
			 if (myTileX != tileX || myTileY != tileY) {
				 myTileX = tileX;
				 myTileY = tileY;
				 myTileCounter = 0;
			 }
			 else {myTileCounter ++;}
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
		 if(isPlaying) {setTiles(left, top, array);}
		 myCurrentScene.updateTiles(cid, left, top, width, height);
	 }
	
	 /* loading image & tile info methods */

	 public void createTilesFromString(String tiles){
		 String[] array = tiles.split(SaladConstants.SPACE);
		 myCurrentScene.setTiles(array);
	 }

	 public void loadTileImage(char cid, String imgfile){
		 defineImage(cid+"",cid+"",cid,BUFFER_IMAGE_FOLDER+imgfile,"-");
		 myGame.defineTileImage(cid, imgfile);
		 if(isEditingMode) {myTileCid = cid;}
	 }

	 private void loadImage(String imgfile) {
		 if (imgfile == null) {return;}
		 defineImage(imgfile, "-", 0, BUFFER_IMAGE_FOLDER+imgfile, "-");
	 }

	 /* switching scene & transition state methods */

	 private void setTransition(String gameState) {
		 if (myGame == null) {return;}
		 Transition trans = myGame.getTransitionState(gameState);
		 if (trans == null) {return;}
		 String bg = trans.getBackground();
		 if (isEditingMode && gameState.equals("Title")) {
			 if (bg.equals(myTitleBG)) {return;}
			 myTitleBG = bg;
		 }
		 if (bg == null) {return;}
		 loadImage(bg);
		 setBGImage(bg);
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

	 private void setEmptyScene() {
		 hideScene();
		 myCurrentScene = new Scene(0);
		 showScene(true);
	 }
	 
	 //called by defineLevel or order from GAE
	 public void setCurrentScene(int currentLevelID, int currentSceneID) {
		 if (isEditingMode) {setGameState("Edit");}
		 hideScene();
		 myCurrentLevelID = currentLevelID;
		 myCurrentSceneID = currentSceneID;
		 myCurrentScene = myGame.getScene(myCurrentLevelID, myCurrentSceneID);
		 showScene(false);
		 if (!isLoading || myPlayer != null) {
			 myPlayer.setInitPos(myCurrentScene.getPlayerInitPosition()[0],
					 myCurrentScene.getPlayerInitPosition()[1]);
		 }
	 }
	 
	 //called by TEM
	 public void switchScene(int currentSceneID, double xpos, double ypos) {
		 Scene scene = myGame.getScene(myCurrentLevelID, currentSceneID);
		 if (scene == null || scene.getXSize()*TILE_WIDTH < xpos || scene.getYSize()*TILE_WIDTH < ypos) {return;}
		 if(isPlaying) {myGame.getScoreManager().update("SceneDone", myCurrentSceneID);}
		 hideScene();
		 myCurrentSceneID = currentSceneID;
		 myCurrentScene = scene;
		 showScene(false);
		 myPlayer.setInitPos(xpos, ypos);
	 }
	 
	 private void hideScene() {
		 if (myCurrentScene != null) {
			 for (GameObject object : myCurrentScene.getGameObjects()) {
				 object.suspend();
			 }
			 if (myPlayer != null) {myPlayer.suspend();}
			 removeObjects(null, 0, false);
//			 System.out.println("removeObjects");
		 }
	 }
	 
	 private void showScene(boolean empty) {
		 setSceneView(myCurrentScene.getBackgroundImage(), myCurrentScene.ifWrapHorizontal(), 
				 myCurrentScene.ifWrapVertical(), myCurrentScene.getXSize(), myCurrentScene.getYSize());
		 if (isPlaying) {
			 setTiles(0, 0, myCurrentScene.getTiles());
			 if (myPlayer != null && !empty) {
				 myPlayer.resume();
			 }
			 for (GameObject object : myCurrentScene.getGameObjects()) {
				 object.resume();
			 }
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
		 myGame.getInputManager().initCheckKey(this);
		 myGame.getTriggerManager().initTEM(this);
	 }

	 public Game getGame() {
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
		 object.setStaticGfx(imgfile);
		 object.setSize(xsize, ysize);
	 }

	 public void setObjectImage(int objectID, String action, String imgfile, int xsize, int ysize){
		 loadImage(imgfile);
		 GameObject object = myGame.getNonPlayer(myCurrentLevelID, myCurrentSceneID, objectID);
		 object.setSize(object.getXSize(), object.getYSize());
		 object.modifyDynamicImage(action, imgfile, xsize, ysize);
	 }
	 
	 public void setPlayerImage(int objectID, String action, String imgfile, int xsize, int ysize){
		 loadImage(imgfile);
		 GameObject object = myGame.getPlayer(objectID);
		 object.setSize(object.getXSize(), object.getYSize());
		 object.modifyDynamicImage(action, imgfile, xsize, ysize);
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

		 loadImage(imgfile);
		 Player object = new Player(unique_id, imgfile, xsize, ysize, xpos, ypos,
				 name, colid, lives, myGame.getCollisionManager(),
				 myGame.getScoreManager(), myGame.getBloodManager(), 
				 myGame.getRevivalManager(), myGame.getLiveManager(), myGame.getTriggerManager());
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
		 loadImage(imgfile);
		 NonPlayer object = new NonPlayer(unique_id, imgfile, xsize, ysize, xpos,
				 ypos, name, colid, lives, myGame.getCollisionManager(),
				 myGame.getScoreManager(), myGame.getBloodManager(), 
				 myGame.getRevivalManager(), myGame.getLiveManager(),myGame.getTriggerManager());
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
	 
	 public void setPaused(boolean paused) {
		 if (!isEditingMode) {return;}
		 isPaused = paused;
	 }
	 
	 public void levelDone(){
		 super.levelDone();
		 if (isEditingMode) {
			 myGameStateString = "Level Done !";
			 myGameStateFrame = 0;
		 }
	 }
	 
	 public void lifeLost(){
		 super.lifeLost();
		 if (isEditingMode) {
			 myGameStateString = "Life Lost !";
			 myGameStateFrame = 0;
		 }
	 }
	 
	 public void gameOver(){
		 super.gameOver();
		 if (isEditingMode) {
			 myGameStateString = "Game Over !";
			 myGameStateFrame = 0;	 
		 }
	 }
	 
//	 public void createActor(int old_unique_id, int new_unique_id) {
//		 NonPlayer actor = myCurrentScene.getNonPlayer(old_unique_id);
//		 createActor(new_unique_id, actor.getMyGfx(), actor.getXSize(), actor.getYSize(),
//				 actor.x, actor.y, actor.getObjectName(), actor.colid, actor.getInitBlood());
//	 }
	 
}
