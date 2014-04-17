package engine;

import stage.Game;
import stage.Scene;
import stage.Transition;
import stage.Transition.StateType;
import jgame.JGColor;
import jgame.JGFont;
import jgame.JGObject;
import jgame.JGPoint;
import jgame.platform.StdGame;
import objects.GameObject;
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
    public static final int FRAMES_PER_SECOND = 20;
    public static final int MAX_FRAMES_TO_SKIP = 2;
    public static final int JGPOINT_X = 800;
    public static final int JGPOINT_Y = 600;
    
    protected Game myGame;
    protected List<int[]> myCollsionPair;
    protected List<int[]> myTileCollsionPair;
    protected int myCurrentLevelID;
    protected int myCurrentSceneID;
    protected Scene myCurrentScene;
    protected boolean isEditingMode;
    
    public GameEngine(boolean editing){
    	initEngineComponent(JGPOINT_X, JGPOINT_Y);
    	myCollsionPair = new ArrayList<int[]>();
    	myTileCollsionPair = new ArrayList<int[]>();
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
        setTiles();//why?
        if(isEditingMode){
        	setGameState("Edit");
        }
    }
    
    
    
    
    
    public void startEdit(){
    	removeObjects(null,0);//remove?
    }
    public void doFrameEdit(){
    	moveObjects();
    	
    	for (int[] pair: myCollsionPair){
    		checkCollision(pair[0], pair[1]);
    	}
    	for (int[] pair: myTileCollsionPair){
    		checkBGCollision(pair[0], pair[1]);
    	}
    }
    public void paintFrameEdit(){
		drawString("You are in Editing Mode right now. This is a test message. ",
			pfWidth()/2,pfHeight()/2,0);
    }
    
    
    
    public void defineLevel(){
    	level += 1;
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
    
    
    
    public void setTiles(){
    	defineImage("mytile","#",1,"marble16.gif","-");
		defineImage("emptytile",".",0,"null","-");
        setTiles(0,0,new String[] { "#......................................#","","","","","","","","","","","","","","","","","","","","","","","","","","","","","########################################" });
        setTiles(19,14,new String[] { "##","##" });
		setTileSettings("#",2,0);// what is this ?
    }
    
    
    
    public void loadImage(String path){
    	//scaling might be done here; dimension parameters needed
    	defineImage(path, "-", 0, path, "-");
    }
    
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
    
    
    
    //questionable; should be in the Game; are IDs needed ?
    public void addCollisionPair(int srccid, String type, int dstcid){
    	myCollsionPair.add(new int[]{srccid,dstcid});
    	List<GameObject> objects = myGame.getObjectsByColid(dstcid);
    	for(GameObject o: objects){
    		o.setCollisionBehavior(type, srccid);
    	}
    }
    
     public void addTileCollisionPair(int tilecid, String type, int objectcid){
    	myTileCollsionPair.add(new int[]{tilecid, objectcid});
    	List<GameObject> objects = myGame.getObjectsByColid(objectcid);
    	for(GameObject o: objects){
    		o.setTileCollisionBehavior(type, tilecid);
    	}
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
    
    public void createBackground(String url){
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
    
    /** 
     * Should be called by the GameFactory to createPlayer
     * Return a created GameObject 
     */
    public Player createPlayer(int unique_id, String url, double xpos, double ypos, String name, int colid, int lives){
    	loadImage(url);
    	Player object = new Player(unique_id, url, xpos, ypos, name, colid, lives);
        myGame.setPlayer(object);
        if(!isEditingMode){
        	object.suspend();//not sure how things are created for playing the game
        }
        return object;
    }
    
    public NonPlayer createActor(int unique_id, String url, double xpos, double ypos, String name, int colid, int lives){
    	loadImage(url);
    	NonPlayer object = new NonPlayer(unique_id, url, xpos, ypos, name, colid, lives);
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
