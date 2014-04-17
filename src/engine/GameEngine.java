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
        setTiles(0, 0, 0, 0, 0, "null");//why?
        
        setPFSize(120,36);
//		setPFWrap(true,false,0,0);
        
        if(isEditingMode){
        	setGameState("Edit");
        }
    }
    
    
    
    
    
    public void startEdit(){
    	removeObjects(null,0);//remove?
    }
    public void doFrameEdit(){
    	if (myGame == null) return;
    	
    	moveObjects();
    	
    	if (myGame.getPlayer() != null){
    		
    		myGame.getGravity().applyGravity(myGame.getPlayer());
    	}
    	
    	
    	
    	if (myGame != null && myGame.getPlayer() != null){
    		setViewOffset((int) myGame.getPlayer().x,(int) myGame.getPlayer().y,true);
    	}
    	
    	for (int[] pair: myGame.getCollisionPair()){
    		checkCollision(pair[0], pair[1]);
    	}
    	for (int[] pair: myGame.getTileCollisionPair()){
    		checkBGCollision(pair[0], pair[1]);
    	}
    	
    }
    public void paintFrameEdit(){
		drawString("You are in Editing Mode right now. This is a test message. ",
			pfWidth()/2,pfHeight()/2,0,true);
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
    
    
    //unfinished
    public void setTiles(int top, int left, int width, int height, int cid, String imgfile){
    	
    	defineImage("",((Integer) cid).toString(),cid,imgfile,"-");
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
    
    
    
    //unfinished
    public void loadImage(String path){
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
