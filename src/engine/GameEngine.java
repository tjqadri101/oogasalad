package engine;

import stage.Game;
import stage.Scene;
import jgame.JGColor;
import jgame.JGFont;
import jgame.JGPoint;
import jgame.platform.StdGame;
import objects.GameObject;
import objects.NonPlayer;
import objects.Player;

import java.awt.Dimension;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
/*
 * @Author: Isaac (Shenghan) Chen, Justin (Zihao) Zhang
 */
public class GameEngine extends StdGame{

    public static final Dimension SIZE = new Dimension(800, 600);
    public static final String TITLE = "Platformer Game Editor";
    public static final int FRAMES_PER_SECOND = 20;
    public static final int MAX_FRAMES_TO_SKIP = 2;
    public static final int JGPOINT_X = 800;
    public static final int JGPOINT_Y = 600;
    
    private int myCurrentLevelID = 1;
    private int myCurrentSceneID = 0;
    private String Mode = "Edit";//String or boolean ?
    private Scene myCurrentScene;//ID or Object ?
    private List<int[]> collsionPair = new ArrayList<int[]>();
    
    protected Game myGame;
    
    public GameEngine(){
//    	initEngine(JGPOINT_X, JGPOINT_Y);//just for testing; will be deleted later
    	initEngineComponent(JGPOINT_X, JGPOINT_Y);
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
        //setBGColor(JGColor.blue);
//        defineImage("mm", "-", 0, "actor_default.png", "-");
//        setBGImage("mm");
        
        defineMedia("example3.tbl");
        setGameState("Edit");
        
		//setBGImage("bg");
//        defineMedia("TestMediaTable.tbl");
//		setBGImage("StartGameBGImage");
    }
    
    public Game getGame(){
    	return myGame;
    }
    
    public void setCurrentLevel(int currentLevelID){
    	myCurrentLevelID = myCurrentSceneID;
    }
    

    public void startEdit(){
    	//setBGImage(currentScene.getBackgroundImage());
    	
//    	defineImage("mm", "-", 0, "actor_default.png", "-");
//        setBGImage("mm");
//    	createPlayer(0, "actor_default.png", 0, 0, null, 0, 0, 0);
    	
    	//setBGColor(JGColor.cyan);
    	removeObjects(null,0);	
    }
    public void doFrameEdit(){
    	moveObjects();
    	for (int[] cp: collsionPair){
    		checkCollision(cp[0], cp[1]);
    	}
    }
    public void paintFrameEdit(){
    	//drawImage("bg", 100, 200);
    	drawImage(100, 200, "bg");
    	drawString("Press space to start",pfWidth()/2,500,0);
    }
    
    public void addCollisionPair(int srccid, String type, int dstcid, int levelID, int sceneID){
    	collsionPair.add(new int[]{srccid,dstcid});
    	List<GameObject> objects = myGame.getObjectsByColid(dstcid);
    	for(GameObject o: objects){
    		o.setCollisionBehavior(type, srccid);
    	}
    }
    
    public void setCurrentScene (int currentSceneID) {
    	for(GameObject go: myCurrentScene.getGameObjects()){
    		go.suspend();
    	}
    	myCurrentScene = myGame.getScene(myCurrentLevelID, currentSceneID);
    	for(GameObject go: myCurrentScene.getGameObjects()){
    		go.resume();
    	}
    	//setGameState(Mode);
    }
    
    public void setGame (Game mygame) {
    	myGame = mygame;
    }
    
    public void createBackground(){
    	//setBGColor(JGColor.cyan);
        //setBGImage();
    }
    
    
    
    /*
     * (non-Javadoc)
     * @see jgame.platform.StdGame#doFrame()
     * For inGame States
     */
    @Override
    public void doFrame(){
    	//createBackground();
    }
    
    /*
     * (non-Javadoc)
     * @see jgame.platform.StdGame#paintFrame()
     * For inGame states
     */
    
    public void doFrameTitle(){
    	//setBGColor(JGColor.blue);
    	//setGameState("Edit");
    }
    
    @Override
    public void paintFrame ()
    {
    	
    }
    
    @Override
    public void doFrameEnterHighscore(){
    	
    }
    
    @Override
    public void paintFrameStartLevel(){
    	
    }
    
    @Override
    public void paintFrameTitle(){
    	
    }
    
    @Override
    public void paintFrameEnterHighscore(){
    	
    }
    
    @Override
    public void paintFrameGameOver(){
    	
    }
    
    @Override
    public void paintFrameHighscores(){
    	
    }
    
    @Override
    public void paintFrameLevelDone(){
    	
    }
    
    @Override
    public void paintFrameLifeLost(){
    	
    }
    
    @Override 
    public void paintFramePaused(){
    	
    }
    
    @Override
    public void paintFrameStartGame(){
    	
    }
    
    /* 
     * Should be called by the GameFactory to createPlayer
     * Return a created GameObject 
     */
    
    public int getCurrentLevelID(){
    	return myCurrentLevelID;
    }
    
    public int getCurrentSceneID(){
    	return myCurrentSceneID;
    }
    
    public GameObject createPlayer(String unique_id, String url, String xpos, String ypos, String name, String colid){
    	File file = new File(url);
    	String filename = file.getName();
        Player object = new Player(Integer.parseInt(unique_id), filename, Double.parseDouble(xpos), Double.parseDouble(ypos), name, Integer.parseInt(colid));
        object.setPos(Double.parseDouble(xpos), Double.parseDouble(ypos));//just to make sure; may be deleted later
        myGame.setPlayer(object);
        return object;
    }
    
    public GameObject createPlayer(int unique_id, String url, double xpos, double ypos, String name, int colid, int levelID, int sceneID){
    	
    	File file = new File(url);
    	String filename = file.getName();
    	defineImage(url, "-", 0, url, "-");
    	
        GameObject object = new Player(unique_id, filename, xpos, ypos, name, colid);
        
        object.setPos(xpos, ypos);//just to make sure; may be deleted later
        //myGame.setPlayer(object);
        System.out.print("here");
        return object;
    }
    
    public GameObject createActor(int unique_id, String url, double xpos, double ypos, String name, int colid){
//    	File file = new File(url);
//    	String filename = file.getName();
    	defineImage(url, "-", 0, url, "-");
    	System.out.print("here");
        GameObject object = new NonPlayer(unique_id, url, xpos, ypos, name, colid);
        object.setPos(xpos, ypos);//just to make sure; may be deleted later
        myGame.getScene(myCurrentLevelID, myCurrentSceneID).addObject(object);
        return object;
    }

    
    public void removeActor(GameObject object){
    	object.remove();
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
