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

<<<<<<< HEAD
    @Override
    public void initGame () {
        setFrameRate(FRAMES_PER_SECOND, MAX_FRAMES_TO_SKIP);
        
        //setTileSettings("#",2,0);
                //setPFWrap(false,true,0,0);
        
        if(isEditingMode){
                setGameState("InGame");
        }
    }
    
      
    public boolean checkGoal(){
        if(myCurrentScene == null) return false;
        String winBehavior = myGame.getLevel(myCurrentLevelID).getWinBehavior();
        if(winBehavior == null) return false;
        List<Object> winParameters = myGame.getLevel(myCurrentLevelID).getWinParameters();
        ResourceBundle behaviors = ResourceBundle.getBundle(SaladConstants.DEFAULT_ENGINE_RESOURCE_PACKAGE + SaladConstants.OBJECT_BEHAVIOR);
        Object answer = SaladUtil.behaviorReflection(behaviors, winBehavior, winParameters, "checkGoal", this);
        return (Boolean) answer;
    }
    
    public boolean checkTrigger(){
//      consider combineing the checkTrigger to checkGoal()
        return true;
    }   
    
    
    public void startEdit(){
        removeObjects(null,0);//remove?
    }
    
    //drag;move->gravity->collision->setViewOffset
    public void doFrameInGame(){
        timer++;//
        if (myCurrentScene == null) return;
        boolean viewOffset = false;
        if(drag()) myViewOffsetPlayer = false;
        else{
                moveObjects();
                Gravity g = myGame.getGravity();
                g.applyGravity(myPlayer);
                for(GameObject go: myCurrentScene.getGameObjects()){
                        g.applyGravity(go);
                }
                for (int[] pair: myGame.getCollisionManager().getCollisionPair()){
                        checkCollision(pair[0], pair[1]);
                }
                for (int[] pair: myGame.getCollisionManager().getTileCollisionPair()){
                        checkBGCollision(pair[0], pair[1]);
                }
                viewOffset = setViewOffsetEdit();
                if(!viewOffset) setViewOffsetPlayer();
                else myViewOffsetPlayer = false;
        }
        if(!viewOffset) setViewOffsetEdit();
//        myTriggerManager.checkTrigger();
        if(checkGoal()){
                if(level>=3){
                        gameOver();
                        System.out.println("lol");
                }
                else
                        levelDone();
        }
    }
=======
	protected Game myGame;
	protected int myCurrentLevelID;
	protected int myCurrentSceneID;
	protected Scene myCurrentScene;
	// protected Scene myEmptyScene = new Scene(0);
	protected Player myPlayer;
	protected TriggerEventManager myTriggerManager;
>>>>>>> branch 'master' of https://github.com/duke-compsci308-spring2014/oogasalad_iTeam.git

	protected int myMouseX;
	protected int myMouseY;
	protected int myMouseButton;
	protected int myClickedID;
	protected boolean myViewOffsetPlayer = true;
	protected int myViewOffsetRate = 1;

	protected int myTileX;
	protected int myTileY;
	protected int myTileCid;
	protected String myTileImgFile;

	protected boolean isEditingMode;

	public GameEngine(boolean editing) {
		initEngineComponent(JGPOINT_X, JGPOINT_Y);
		isEditingMode = editing;
	}

	@Override
	public void initCanvas() {
		setCanvasSettings(CANVAS_WIDTH, CANVAS_HEIGHT, TILE_WIDTH, TILE_HEIGHT,
				null, null, null);
	}

	@Override
	public void initGame() {
		setFrameRate(FRAMES_PER_SECOND, MAX_FRAMES_TO_SKIP);

		// setTileSettings("#",2,0);
		// setPFWrap(false,true,0,0);

		if (isEditingMode) {
			setGameState("InGame");
		}
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
		removeObjects(null, 0);// remove?
	}

	// drag;move->gravity->collision->setViewOffset
	public void doFrameInGame() {
		timer++;//
		if (myCurrentScene == null)
			return;
		boolean viewOffset = false;
		if (drag())
			myViewOffsetPlayer = false;
		else {
			moveObjects();
			Gravity g = myGame.getGravity();
			g.applyGravity(myPlayer);
			for (GameObject go : myCurrentScene.getGameObjects()) {
				g.applyGravity(go);
			}
			for (int[] pair : myGame.getCollisionManager().getCollisionPair()) {
				checkCollision(pair[0], pair[1]);
			}
			for (int[] pair : myGame.getCollisionManager()
					.getTileCollisionPair()) {
				checkBGCollision(pair[0], pair[1]);
			}
			viewOffset = setViewOffsetEdit();
			if (!viewOffset)
				setViewOffsetPlayer();
			else
				myViewOffsetPlayer = false;
		}
		if (!viewOffset)
			setViewOffsetEdit();
		myTriggerManager.checkTrigger();
		if (checkGoal()) {
			if (level >= 3) {
				gameOver();
				System.out.println("lol");
			} else
				levelDone();
		}
	}

	private void setViewOffsetPlayer() {
		if (myPlayer != null) {
			if (myViewOffsetRate > 1)
				myViewOffsetRate -= 1;
			if (isEditingMode && !myViewOffsetPlayer)
				myViewOffsetRate = 35;
			myViewOffsetPlayer = true;
			int desired_viewXOfs = (int) myPlayer.x + myPlayer.getXSize() / 2
					- viewWidth() / 2;
			int desired_viewYOfs = (int) myPlayer.y + myPlayer.getYSize() / 2
					- viewHeight() / 2;
			setViewOffset((desired_viewXOfs - viewXOfs()) / myViewOffsetRate
					+ viewXOfs(), (desired_viewYOfs - viewYOfs())
					/ myViewOffsetRate + viewYOfs(), false);
		}
	}

	private boolean setViewOffsetEdit() {
		int speed = 10;
		double margin = 0.1;
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

	public void paintFrameInGame() {
		drawString(
				"You are in Editing Mode right now. This is a test message.",
				viewWidth() / 2, viewHeight() / 2, 0, false);
		if (myPlayer != null) {
			drawRect(myPlayer.x + myPlayer.getXSize() / 2, myPlayer.y
					- myPlayer.getYSize() / 13.5, myPlayer.getXSize() / 2, 10,
					false, true);
			drawRect(
					myPlayer.x
							+ (0.5 + 0.5 * myPlayer.getBlood()
									/ myPlayer.getInitBlood())
							* myPlayer.getXSize() / 2,
					myPlayer.y - myPlayer.getYSize() / 13.5,
					(1.0 * myPlayer.getBlood() / myPlayer.getInitBlood())
							* myPlayer.getXSize() / 2, 10, true, true);
			drawString("lol help!", myPlayer.x + myPlayer.getXSize() / 2,
					myPlayer.y - myPlayer.getYSize() / 3, 0, true);
		}

		// drawRect(getMouseX()+viewXOfs(),getMouseY()+viewYOfs(),20,20,false,true,true);

		if (checkGoal()) {
			drawString("Win!!!!!!!!!!!!!!!!", viewWidth() / 2,
					viewHeight() / 2 + 100, 0, false);
		}
		if (checkGoal()) {
			// call the event module
		}
		if (myMouseButton != 0 && myClickedID == -1) {
			int tileX = myMouseX / 20;
			int tileY = myMouseY / 20;
			if (myMouseButton == 3)
				setColor(JGColor.red);// should only be applied to the last line
			drawRect((double) Math.min(myTileX, tileX) * 20,
					(double) Math.min(myTileY, tileY) * 20,
					(double) (Math.abs(myTileX - tileX) + 1) * 20,
					(double) (Math.abs(myTileY - tileY) + 1) * 20, false, false);
		}
	}

	public void defineLevel() {
		setCurrentScene(1, 0);
		myPlayer.resume();
		// restore the player ? depending on playing mode
	}

	public void initNewLife() {
		// restore the player
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
		// else
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
		// else
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

	// unfinished
	public void createTiles(int cid, String imgfile, int left, int top,
			int width, int height) {
		if (cid > 9)
			return;
		defineImage(((Integer) cid).toString(), ((Integer) cid).toString(),
				cid, imgfile, "-");
		String temp = "";
		for (int i = 0; i < width; i++) {
			temp += cid;
		}
		String[] array = new String[height];
		for (int j = 0; j < height; j++) {
			array[j] = temp;
		}
		setTiles(left, top, array);
		myCurrentScene.defineTileImage(cid, imgfile);
		myCurrentScene.updateTiles(cid, left, top, width, height);
	}

	public int getClickedID() {
		List<GameObject> list = new ArrayList<GameObject>();
		if (getMouseButton(1)) {
			int MouseX = getMouseX() + viewXOfs();
			int MouseY = getMouseY() + viewYOfs();
			if (myPlayer != null && !myPlayer.is_suspended
					&& myPlayer.x < MouseX
					&& MouseX < myPlayer.x + myPlayer.getXSize()
					&& myPlayer.y < MouseY
					&& MouseY < myPlayer.y + myPlayer.getYSize()) {
				list.add(myPlayer);
			}
			for (GameObject go : myCurrentScene.getGameObjects()) {
				// suspended?
				if (!go.is_suspended && go.isAlive() && go.x < MouseX
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
				createTiles(myTileCid, myTileImgFile, Math.min(myTileX, tileX),
						Math.min(myTileY, tileY),
						Math.abs(myTileX - tileX) + 1,
						Math.abs(myTileY - tileY) + 1);
			}
			myClickedID = -1;
		}
		if (myMouseButton == 1 && currentMouse1) {
			if (myClickedID > 0) {
				NonPlayer actor = myCurrentScene.getNonPlayer(myClickedID);
				actor.x += MouseX - myMouseX;
				actor.y += MouseY - myMouseY;
			}
			if (myClickedID == 0) {
				myPlayer.x += MouseX - myMouseX;
				myPlayer.y += MouseY - myMouseY;
			}
			drag = myClickedID > -1;
		}

		if (myMouseButton != 3 && currentMouse3) {
			myTileX = tileX;
			myTileY = tileY;
		}
		if (myMouseButton == 3 && !currentMouse3) {
			createTiles(0, "null", Math.min(myTileX, tileX),
					Math.min(myTileY, tileY), Math.abs(myTileX - tileX) + 1,
					Math.abs(myTileY - tileY) + 1);
		}
		myMouseButton = 0;
		if (currentMouse1)
			myMouseButton = 1;
		if (currentMouse3)
			myMouseButton = 3;
		myMouseX = MouseX;
		myMouseY = MouseY;
		return drag;
	}

	public void setDefaultTiles(int cid, String imgfile) {
		myTileCid = cid;
		myTileImgFile = imgfile;
	}

	// public void createTiles(){
	// boolean currentMouseClicked = getMouseButton(1);
	//
	// if (!myMouseClicked && currentMouseClicked){
	// myTileX = getMouseX()/20;
	// myTileY = getMouseY()/20;
	// }
	// if (myMouseClicked && !currentMouseClicked){
	// int tileX = getMouseX()/20;
	// int tileY = getMouseY()/20;
	// createTiles(Math.min(myTileX,tileX), Math.min(myTileY,tileY),
	// Math.abs(myTileX-tileX)+1, Math.abs(myTileY-tileY)+1, myTileCid,
	// myTileImgFile);
	// }
	// if (myMouseClicked && currentMouseClicked){
	//
	// }
	// myMouseClicked = currentMouseClicked;
	// }

	// unfinished
	private void loadImage(String path) {
		if (path == null)
			return;
		defineImage(path, "-", 0, path, "-");
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
				// go.remove();
				go.suspend();
			}
		}
		myCurrentLevelID = currentLevelID;
		myCurrentSceneID = currentSceneID;
		myCurrentScene = myGame.getScene(myCurrentLevelID, myCurrentSceneID);
		updateCurrentScene();
	}

	/**
         * 
         */
	private void updateCurrentScene() {
		setPFSize(myCurrentScene.getXSize(), myCurrentScene.getYSize());
		for (Entry<Integer, String> entry : myCurrentScene.getTileImageMap()) {
			Integer cid = entry.getKey();
			String imgfile = entry.getValue();
			defineImage(cid.toString(), cid.toString(), cid, imgfile, "-");
		}
		setTiles(0, 0, myCurrentScene.getTiles());
		String url = myCurrentScene.getBackgroundImage();
		loadImage(url);
		setBGImage(url);
		for (GameObject go : myCurrentScene.getGameObjects()) {
			// this.markAddObject(go);
			// go.is_alive = true;
			go.resume();
		}
	}

	public void setBackground(String fileName) {
		myCurrentScene.setBackgroundImage(fileName, false, false,
				myCurrentScene.getXSize(), myCurrentScene.getYSize());
		loadImage(fileName);
		setBGImage(fileName);
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

	public void setSceneSize(int xsize, int ysize) {
		myCurrentScene.resizeTiles(xsize, ysize);
		myCurrentScene.setSize(xsize, ysize);
		setPFSize(xsize, ysize);
	}

	private void modifyImage(GameObject object, String imgfile, int xsize,
			int ysize) {
		loadImage(imgfile);
		object.setImage(imgfile);// animation
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

	public Player createPlayer(int unique_id, String url, int xsize, int ysize,
			double xpos, double ypos, String name, int colid, int lives) {
		loadImage(url);
		Player object = new Player(unique_id, url, xsize, ysize, xpos, ypos,
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

	public NonPlayer createActor(int unique_id, String url, int xsize,
			int ysize, double xpos, double ypos, String name, int colid,
			int lives) {
		loadImage(url);
		NonPlayer object = new NonPlayer(unique_id, url, xsize, ysize, xpos,
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
