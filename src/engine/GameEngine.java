package engine;

import stage.Game;
import stage.ResetLevelException;
import stage.Scene;
import stage.Transition;
import stage.Transition.StateType;
import jgame.JGColor;
import jgame.JGFont;
import jgame.JGObject;
import jgame.JGPoint;
import jgame.platform.StdGame;
import objects.GameObject;
import objects.Gravity;
import objects.NonPlayer;
import objects.Player;

import java.awt.Dimension;//
import java.util.ArrayList;
import java.util.List;

import jboxGlue.PhysicalObject;
import jboxGlue.WorldManager;
/**
 * @Author: Isaac (Shenghan) Chen, Justin (Zihao) Zhang
 */
public class GameEngine extends StdGame{

//    public static final Dimension SIZE = new Dimension(800, 600);
//    public static final String TITLE = "Platformer Game Editor";
    public static final int FRAMES_PER_SECOND = 70;
    public static final int MAX_FRAMES_TO_SKIP = 2;
    public static final int JGPOINT_X = 800;
    public static final int JGPOINT_Y = 600;
    
    protected Game myGame;
    protected int myCurrentLevelID;
    protected int myCurrentSceneID;
    protected Scene myCurrentScene;
    
    protected int myMouseX;
    protected int myMouseY;
    protected boolean myMouseClicked;
    protected int myClickedID;
    
    protected int myTileX;
    protected int myTileY;
    protected int myTileCid;
    protected String myTileImgFile;
    
    protected boolean isEditingMode;
    
    public GameEngine(boolean editing){
    	initEngineComponent(JGPOINT_X, JGPOINT_Y);
    	isEditingMode = editing;
    }
    
    @Override
    public void initCanvas () {
        setCanvasSettings(40, // width of the canvas in tiles
                          30, // height of the canvas in tiles
                          20, // width of one tile
                          20, // height of one tile
                          null,// foreground colour -> use default colour white
                          null,// background colour -> use default colour black
                          null); // standard font -> use default font
    }

    @Override
    public void initGame () {
        setFrameRate(FRAMES_PER_SECOND, MAX_FRAMES_TO_SKIP);
        createTiles(0, 0, 0, 0, 0, "null");//why?
        
        setPFSize(1200,36);
		//setPFWrap(false,true,0,0);
        
        if(isEditingMode){
        	setGameState("Edit");
        }
//        this.addGameState("InGame");
    }
    
    
    public void checkGoal(){
    	
    }
    
    
    public void startEdit(){
    	removeObjects(null,0);//remove?
    }
    //drag;move->gravity->collision->setViewOffset
    public void doFrameEdit(){
    	timer++;
    	if (myGame == null) return;
    	if(!drag()){
    		createTiles();
    		moveObjects();
    		myGame.getGravity().applyGravity(myGame.getPlayer(Game.NONUSE_ID, Game.NONUSE_ID, Game.NONUSE_ID));
    		for (int[] pair: myGame.getCollisionPair()){
        		checkCollision(pair[0], pair[1]);
        	}
        	for (int[] pair: myGame.getTileCollisionPair()){
        		checkBGCollision(pair[0], pair[1]);
        	}
        	Player player = myGame.getPlayer(Game.NONUSE_ID, Game.NONUSE_ID, Game.NONUSE_ID);
        	if (player != null){
        		setViewOffset((int) player.x+player.getXSize()/2,(int) player.y+player.getYSize()/2,true);
        	}
    	}
    	setViewOffsetEdit();
    }

    private void setViewOffsetEdit() {
    	int XOfs = 0;
    	int YOfs = 0;
    	if (0 < getMouseX() && getMouseX() < 0.1*viewWidth()){
    		XOfs -= 10;
    	}
    	if (0.9*viewWidth() < getMouseX() && getMouseX() < viewWidth()){
    		XOfs += 10;
    	}
    	if (0 < getMouseY() && getMouseY() < 0.1*viewHeight()){
    		YOfs -= 10;	
    	}
    	if (0.9*viewHeight() < getMouseY() && getMouseY() < viewHeight()){
    		YOfs += 10;
    	}
    	setViewOffset(viewXOfs()+XOfs,viewYOfs()+YOfs,false);
    }
    public void paintFrameEdit(){
		drawString("You are in Editing Mode right now. This is a test message. ",
			pfWidth()/2,pfHeight()/2,0,true);
		drawRect(getMouseX()+viewXOfs(),getMouseY()+viewYOfs(),20,20,false,true,true);
		if(myMouseClicked && myClickedID == -1){
			int tileX = myMouseX/20;
    		int tileY = myMouseY/20;
    		drawRect((double)Math.min(myTileX,tileX)*20,(double)Math.min(myTileY,tileY)*20,(double)(Math.abs(myTileX-tileX)+1)*20,(double)(Math.abs(myTileY-tileY)+1)*20,false,false);
		}
    }
    
    
    
    public void defineLevel(){
    	level += 1;//shouldn't be here
    	setCurrentScene(level, 0);
    	//restore the player ? depending on playing mode
    }
    
    public void initNewLife(){
    	//restore the player
    }
    
    
    
    /**
     * (non-Javadoc)
     * @see jgame.platform.StdGame#doFrame()
     * For inGame States
     */
    @Override
    public void doFrame(){
    	if(!isEditingMode){
    		super.doFrame();
    	}
    	//else
    }
    
    /**
     * (non-Javadoc)
     * @see jgame.platform.StdGame#paintFrame()
     * For inGame states
     */
    @Override
    public void paintFrame(){
    	if(!isEditingMode){
    		super.paintFrame();
    	}
    	//else
    }
    
    
    
    public void StartTitle(){
    	setTransition(StateType.Title);
    }
    
    public void StartStartGame(){
    	setTransition(StateType.StartGame);
    }
    
    public void StartStartLevel(){
    	setTransition(StateType.StartLevel);
    }

    public void StartLevelDone(){
    	setTransition(StateType.LevelDone);
    }
    
    public void StartLifeLost(){
    	setTransition(StateType.LifeLost);
    }
    
    public void StartGameOver(){
    	setTransition(StateType.GameOver);
    }
    
    
    
//    @Override
//    public void paintFrameTitle(){
//    	
//    }
//    
//    @Override
//    public void paintFrameStartGame(){
//    	
//    }
//    
//    @Override
//    public void paintFrameStartLevel(){
//    	
//    }
//
//    @Override
//    public void paintFrameLevelDone(){
//    	
//    }
//    
//    @Override
//    public void paintFrameLifeLost(){
//    	
//    }
//    
//    @Override
//    public void paintFrameGameOver(){
//    	
//    }
//    
//    @Override
//    public void paintFrameEnterHighscore(){
//    	
//    }
//    
//    @Override
//    public void paintFrameHighscores(){
//    	
//    }
//    
//    @Override 
//    public void paintFramePaused(){
//    	
//    }
    
    
    
    //unfinished
    public void createTiles(int top, int left, int width, int height, int cid, String imgfile){
    	if (cid > 9) return;
    	defineImage(((Integer) cid).toString(),((Integer) cid).toString(),cid,imgfile,"-");
    	String temp = "";
    	for(int i=0;i<width;i++){
    		temp += cid;
    	}
    	String[] array = new String[height];
    	for(int j=0;j<height;j++){
    		array[j] = temp;
    	}
    	setTiles(top,left,array);
//		setTileSettings("#",2,0);// what is this ?
    }
    
    public int getClickedID(){
    	List<GameObject> list = new ArrayList<GameObject>();
    	if (getMouseButton(1)){
    		int MouseX = getMouseX()+viewXOfs();
        	int MouseY = getMouseY()+viewYOfs();
    		Player player = myGame.getPlayer(Game.NONUSE_ID, Game.NONUSE_ID, Game.NONUSE_ID);
    		if (player != null && player.x < MouseX && MouseX < player.x + player.getXSize() 
    				&& player.y < MouseY && MouseY < player.y + player.getYSize()){
    			list.add(player);
    		}
    		for(GameObject go: myCurrentScene.getGameObjects()){
    			if (go.isAlive() && go.x < MouseX && MouseX < go.x + go.getXSize() 
    					&& go.y < MouseY && MouseY < go.y + go.getYSize()){
    				list.add(go);
    			}
    		}
    	}
    	if (list.isEmpty()){
    		return -1;
    	}
    	System.out.println("ID "+list.get(0).getID());
    	return list.get(0).getID();
    }
    
    public boolean drag(){
    	boolean drag = false;
    	boolean currentMouseClicked = getMouseButton(1);
    	int MouseX = getMouseX()+viewXOfs();
    	int MouseY = getMouseY()+viewYOfs();
    	
    	if (!myMouseClicked && currentMouseClicked){
    		myClickedID = getClickedID();
    		myTileX = MouseX/20;
    		myTileY = MouseY/20;
    	}
    	if (myMouseClicked && !currentMouseClicked){
    		if (myClickedID == -1){
    			int tileX = MouseX/20;
    			int tileY = MouseY/20;
    			createTiles(Math.min(myTileX,tileX), Math.min(myTileY,tileY), Math.abs(myTileX-tileX)+1, Math.abs(myTileY-tileY)+1, myTileCid, myTileImgFile);
    		}
    		myClickedID = -1;
    	}
    	if (myMouseClicked && currentMouseClicked){
    		if (myClickedID > 0){
    			NonPlayer actor = myCurrentScene.getNonPlayer(myClickedID);
    			actor.x+=MouseX-myMouseX;
    			actor.y+=MouseY-myMouseY;
    		}
    		if (myClickedID == 0){
    			Player player = myGame.getPlayer(Game.NONUSE_ID, Game.NONUSE_ID, Game.NONUSE_ID);
    			player.x+=MouseX-myMouseX;
    			player.y+=MouseY-myMouseY;
    		}
    		drag = myClickedID > -1;
    	}
    	myMouseClicked = currentMouseClicked;
    	myMouseX = MouseX;
    	myMouseY = MouseY;
    	return drag;
    }
    
    public void setDefaultTiles(int cid, String imgfile){
    	myTileCid = cid;
    	myTileImgFile = imgfile;
    }
    
    public void createTiles(){
    	boolean currentMouseClicked = getMouseButton(1);
    	
    	if (!myMouseClicked && currentMouseClicked){
    		myTileX = getMouseX()/20;
    		myTileY = getMouseY()/20;
    	}
    	if (myMouseClicked && !currentMouseClicked){
    		int tileX = getMouseX()/20;
    		int tileY = getMouseY()/20;
    		createTiles(Math.min(myTileX,tileX), Math.min(myTileY,tileY), Math.abs(myTileX-tileX)+1, Math.abs(myTileY-tileY)+1, myTileCid, myTileImgFile);
    	}
    	if (myMouseClicked && currentMouseClicked){
    		
    	}
    	myMouseClicked = currentMouseClicked;
    }
    
    //unfinished
    private void loadImage(String path){
    	//scaling might be done here; dimension parameters needed
    	defineImage(path, "-", 0, path, "-");
    }
    
    //unfinished
    private void setTransition(StateType type){
    	//setSequences(startgame_ingame, 0, leveldone_ingame, 0, lifelost_ingame, 0, gameover_ingame, 0);
    	Transition trans = myGame.getNonLevelScene(type);
    	String url = trans.getBackground();
    	if(url != null){
    		loadImage(url);
        	setBGImage(url);
    	}
    	//something else to do ?
    }
    
    
    
    public void setCurrentScene (int currentLevelID, int currentSceneID) {
    	if(myCurrentScene != null){
    		for(GameObject go: myCurrentScene.getGameObjects()){
        		go.suspend();
        	}
    	}
    	myCurrentLevelID = currentLevelID;
    	myCurrentSceneID = currentSceneID;
    	myCurrentScene = myGame.getScene(myCurrentLevelID, myCurrentSceneID);
    	String url = myCurrentScene.getBackgroundImage();
    	if(url != null){
    		loadImage(url);
        	setBGImage(url);
    	}
    	for(GameObject go: myCurrentScene.getGameObjects()){
    		go.resume();
    	}
    }
    
    public void setBackground(String url){
    	myCurrentScene.setBackgroundImage(url);
    	loadImage(url);
    	setBGImage(url);
    }
    
    public void setGame (Game mygame) {
    	myGame = mygame;
    }
    
    public Game getGame(){
    	return myGame;
    }
    
    public int getCurrentLevelID(){
    	return myCurrentLevelID;
    }
    
    public int getCurrentSceneID(){
    	return myCurrentSceneID;
    }
    
    public void createLevel(int levelID){
    	myGame.addLevel(levelID);
    }
    
    public void deleteLevel(int levelID){
    	myGame.removeLevel(levelID);
    }
    
    public void resetLevelID(int oldID, int newID){
    	try {
			myGame.resetLevelID(oldID, newID);
		} catch (ResetLevelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void createScene(int levelID, int sceneID){
    	myGame.addScene(levelID, sceneID);
    }
    
    public void deleteScene(int levelID, int sceneID){
    	myGame.removeScene(levelID, sceneID);
    }
    
    public void setInitPos(double xpos, double ypos){
    	myCurrentScene.setPlayerInitPosition(xpos, ypos);
    }
    
    public void deleteActor(int unique_id){
    	myCurrentScene.deleteNonPlayer(unique_id);
    }
    
    /** 
     * Should be called by the GameFactory to createPlayer
     * Return a created GameObject 
     */
    public Player createPlayer(int unique_id, String url, int xsize, int ysize, double xpos, double ypos, String name, int colid, int lives){
    	loadImage(url);
    	Player object = new Player(unique_id, url, xsize, ysize, xpos, ypos, name, colid, lives);
        myGame.setPlayer(object);
        if(!isEditingMode){
        	object.suspend();//not sure how things are created for playing the game
        }
        return object;
    }
    
    public NonPlayer createActor(int unique_id, String url, int xsize, int ysize, double xpos, double ypos, String name, int colid, int lives){
    	loadImage(url);
    	NonPlayer object = new NonPlayer(unique_id, url, xsize, ysize, xpos, ypos, name, colid, lives);
        myCurrentScene.addNonPlayer(object);
        if(!isEditingMode){
        	object.suspend();//not sure how things are created for playing the game
        }
        return object;
    }
    
    /*
     * For reference only
     */
//	private Mass getMassById(String id){
//    	Object thisObject = this.getObject(id);
//    	Mass returnObject = (Mass) thisObject;
//    	return returnObject;
//    }
    
}
