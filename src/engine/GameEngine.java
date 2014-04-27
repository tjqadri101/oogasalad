package engine;

import saladConstants.SaladConstants;
import stage.Game;
import stage.Scene;
import stage.Transition;
import stage.Transition.StateType;
import util.SaladUtil;
import jgame.JGColor;
import jgame.platform.StdGame;
import objects.GameObject;
import objects.Gravity;
import objects.NonPlayer;
import objects.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.ResourceBundle;

import engineManagers.TriggerEventManager;

/**
 * @Author: Isaac (Shenghan) Chen
 * @Contribution: Justin (Zihao) Zhang
 * @Contribution Steve (Siyang) Wang
 */
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
	
	public GameEngine(boolean editing) {
		initEngineComponent(JGPOINT_X, JGPOINT_Y);
		isEditingMode = editing;
	}

	@Override
	public void initCanvas() {
		setCanvasSettings(CANVAS_WIDTH, CANVAS_HEIGHT, TILE_WIDTH, TILE_HEIGHT, null, null, null);
	}

	@Override
	public void initGame() {
		setFrameRate(FRAMES_PER_SECOND, MAX_FRAMES_TO_SKIP);
		// setTileSettings("#",2,0);
	}

	public boolean checkGoal() {
		if (myCurrentScene == null)
			return false;
		String winBehavior = myGame.getLevel(myCurrentLevelID).getWinBehavior();
		if (winBehavior == null)
			return false;
		List<Object> winParameters = myGame.getLevel(myCurrentLevelID)
				.getWinParameters();
		ResourceBundle behaviors = ResourceBundle
				.getBundle(SaladConstants.DEFAULT_ENGINE_RESOURCE_PACKAGE
						+ SaladConstants.OBJECT_BEHAVIOR);
		Object answer = SaladUtil.behaviorReflection(behaviors, winBehavior,
				winParameters, "checkGoal", this);
		return (Boolean) answer;
	}

	public boolean checkTrigger() {
		// consider combineing the checkTrigger to checkGoal()
		return true;
	}

	public void startEdit() {
		removeObjects(null, 0);// ???
	}

	// drag;move->gravity->collision->setViewOffset
	public void doFrameEdit() {
	        TriggerEventManager myTEM = getGame().getTEM();
		timer++;//change later
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
		if (checkGoal()) {
			if (level >= 3) {
				gameOver();
			} else
				levelDone();
		}
	}

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
		for (GameObject go : myCurrentScene.getGameObjects()) {
			g.applyGravity(go);
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
		if (!isEditingMode)
			return false;
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

	public void paintFrameEdit() {
		displayPlayerInfo();
		disPlayDragTile();
		
		if (checkGoal()) {
			drawString("Win!!!!!!!!!!!!!!!!", viewWidth() / 2,
					viewHeight() / 2 + 100, 0, false);
		}
		if (checkGoal()) {
			// call the event module
		}
	}

	private void disPlayDragTile() {
		if (myMouseButton != 0 && myClickedID == -1) {
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
	
	
	
	
	
	public void defineLevel() {
		setCurrentScene(1, 0);
		myPlayer.resume();
	}

	public void initNewLife() {
		
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see jgame.platform.StdGame#doFrame() For inGame States
	 */
	@Override
	public void doFrame() {
		if (!isEditingMode) {
			super.doFrame();
		}
		doFrameEdit();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see jgame.platform.StdGame#paintFrame() For inGame states
	 */
	@Override
	public void paintFrame() {
		if (!isEditingMode) {
			super.paintFrame();
		}
		paintFrameEdit();
	}

	public void StartTitle() {
		setTransition(StateType.Title);
	}

	public void StartStartGame() {
		setTransition(StateType.StartGame);
	}

	public void StartStartLevel() {
		setTransition(StateType.StartLevel);
	}

	public void StartLevelDone() {
		setTransition(StateType.LevelDone);
	}

	public void StartLifeLost() {
		setTransition(StateType.LifeLost);
	}

	public void StartGameOver() {
		setTransition(StateType.GameOver);
	}

	// @Override
	// public void paintFrameTitle(){
	//
	// }
	//
	// @Override
	// public void paintFrameStartGame(){
	//
	// }
	//
	// @Override
	// public void paintFrameStartLevel(){
	//
	// }
	//
	// @Override
	// public void paintFrameLevelDone(){
	//
	// }
	//
	// @Override
	// public void paintFrameLifeLost(){
	//
	// }
	//
	// @Override
	// public void paintFrameGameOver(){
	//
	// }
	//
	// @Override
	// public void paintFrameEnterHighscore(){
	//
	// }
	//
	// @Override
	// public void paintFrameHighscores(){
	//
	// }
	//
	// @Override
	// public void paintFramePaused(){
	//
	// }
	
	
	
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
		setTiles(left, top, array);
		myCurrentScene.updateTiles(cid, left, top, width, height);
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

	public boolean drag() {
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
		if (myMouseButton == 1 && !currentMouse1) {
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
		if (myMouseButton == 3 && !currentMouse3) {
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
	
	public void createTilesFromString(String tiles){
    	String[] array = tiles.split(" ");
    	setTiles(0, 0, array);
    	myCurrentScene.setTiles(array);
    }
    
    public void loadTileImage(char cid, String imgfile){
    	defineImage(cid+"",cid+"",cid,imgfile,"-");
    	myCurrentScene.defineTileImage(cid, imgfile);
    	if(isEditingMode) {myTileCid = cid;}
    }

	private void loadImage(String imgfile) {
		if (imgfile == null) {return;}
		defineImage(imgfile, "-", 0, imgfile, "-");
	}

	
	
	// unfinished
	private void setTransition(StateType type) {
		// setSequences(startgame_ingame, 0, leveldone_ingame, 0,
		// lifelost_ingame, 0, gameover_ingame, 0);
		Transition trans = myGame.getNonLevelScene(type);
		String url = trans.getBackground();
		if (url != null) {
			loadImage(url);
			setBGImage(url);
		}
		// something else to do ?
	}

	
	
	public void setCurrentScene(int currentLevelID, int currentSceneID) {
		if (myCurrentScene != null) {
			for (GameObject go : myCurrentScene.getGameObjects()) {
				go.suspend();
			}
		}
		myCurrentLevelID = currentLevelID;
		myCurrentSceneID = currentSceneID;
		myCurrentScene = myGame.getScene(myCurrentLevelID, myCurrentSceneID);
		updateCurrentScene();
	}
	
	private void updateCurrentScene() {
		setSceneView(myCurrentScene.getBackgroundImage(), myCurrentScene.ifWrapHorizontal(), 
				myCurrentScene.ifWrapVertical(), myCurrentScene.getXSize(), myCurrentScene.getYSize());
		for (Entry<Character, String> entry : myCurrentScene.getTileImageMap()) {
			Character cid = entry.getKey();
			String imgfile = entry.getValue();
			loadTileImage(cid, imgfile);
		}
		setTiles(0, 0, myCurrentScene.getTiles());
		for (GameObject go : myCurrentScene.getGameObjects()) {
			go.resume();
		}
	}

	
	
	public void setSceneView(String fileName,
			boolean wrapx, boolean wrapy, int xsize, int ysize) {
		setBackground(fileName);
		setSceneSize(xsize, ysize);
		setSceneWrap(wrapx, wrapy);
	}
	
	private void setBackground(String fileName) {
		myCurrentScene.setBackgroundImage(fileName);
		loadImage(fileName);
		setBGImage(fileName);
	}
	
	private void setSceneSize(int xsize, int ysize) {
		myCurrentScene.resizeTiles(xsize, ysize);
		myCurrentScene.setSize(xsize, ysize);
		setPFSize(xsize, ysize);
	}
	
	private void setSceneWrap(boolean wrapx, boolean wrapy) {
		myCurrentScene.setWrap(wrapx, wrapy);
		setPFWrap(wrapx, wrapy,0,0);
	}
	
	

	public void setGame(Game mygame) {
		myGame = mygame;
	}

	public Game getGame() {
		return myGame;
	}

	public int getCurrentLevelID() {
		return myCurrentLevelID;
	}

	public int getCurrentSceneID() {
		return myCurrentSceneID;
	}
	
	

	private void modifyImage(GameObject object, String imgfile, int xsize,
			int ysize) {
		loadImage(imgfile);
		object.setImage(imgfile);// animation?
		object.setSize(xsize, ysize);
		object.updateImageURL(imgfile);
	}

	public void modifyActorImage(int unique_id, String imgfile, int xsize,
			int ysize) {
		GameObject object = myGame.getNonPlayer(myCurrentLevelID,
				myCurrentSceneID, unique_id);
		modifyImage(object, imgfile, xsize, ysize);
	}

	public void modifyPlayerImage(int unique_id, String imgfile, int xsize,
			int ysize) {
		GameObject object = myGame.getPlayer(unique_id);
		modifyImage(object, imgfile, xsize, ysize);
	}

	public Player createPlayer(int unique_id, String imgfile, int xsize, int ysize,
			double xpos, double ypos, String name, int colid, int lives) {
	        TriggerEventManager etm = getGame().getTEM();
		loadImage(imgfile);
		Player object = new Player(unique_id, imgfile, xsize, ysize, xpos, ypos,
				name, colid, lives, myGame.getCollisionManager(),
				myGame.getScoreManager(), myGame.getBloodManager());
		myGame.setPlayer(object);
		myPlayer = object;
		object.resume_in_view = false;

		if (!isEditingMode) {
			object.suspend();// not sure how things are created for playing the
								// game
		}
		return object;
	}

	public NonPlayer createActor(int unique_id, String imgfile, int xsize,
			int ysize, double xpos, double ypos, String name, int colid, int lives) {
	        TriggerEventManager etm = getGame().getTEM();
		loadImage(imgfile);
		NonPlayer object = new NonPlayer(unique_id, imgfile, xsize, ysize, xpos,
				ypos, name, colid, lives, myGame.getCollisionManager(),
				myGame.getScoreManager(), myGame.getBloodManager());
		if (unique_id != SaladConstants.NULL_UNIQUE_ID) {
			myGame.addNonPlayer(myCurrentLevelID, myCurrentSceneID, object);
		}
		object.resume_in_view = false;

		if (!isEditingMode) {
			object.suspend();// not sure how things are created for playing the
								// game
		}
		return object;
	}
	
}
