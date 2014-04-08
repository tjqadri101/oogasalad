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
    
    private String Mode = "Edit";//String or boolean ?
    private Scene currentScene;//ID or Object ?
    private List<int[]> collsionPair = new ArrayList<int[]>();
    
    protected Game myGame;
    
    public GameEngine(Game mygame){
    	initEngineComponent(JGPOINT_X, JGPOINT_Y);
    	myGame = mygame;
    }
    
    @Override
    public void initCanvas () {
        setCanvasSettings(1, // width of the canvas in tiles
                          1, // height of the canvas in tiles
                          displayWidth(), // width of one tile
                          displayHeight(), // height of one tile
                          null,// foreground colour -> use default colour white
                          null,// background colour -> use default colour black
                          null); // standard font -> use default font
    }

    @Override
    public void initGame () {
        setFrameRate(FRAMES_PER_SECOND, MAX_FRAMES_TO_SKIP);
        //setGameState(Mode);
        defineMedia("TestMediaTable.tbl");
		setBGImage("StartGameBGImage");
    }

    public void startEdit(){
    	//setBGImage(currentScene.getBackgroundImage());
    }
    public void doFrameEdit(){
    	moveObjects();
    	for (int[] cp: collsionPair){
    		checkCollision(cp[0], cp[1]);
    	}
    }
    public void paintFrameEdit(){

    }
    
    public void addCollisionPair(int srccid, String type, int dstcid, int levelID, int sceneID){
    	collsionPair.add(new int[]{srccid,dstcid});
    	List<GameObject> objects = myGame.getObjectsByColid(dstcid);
    	for(GameObject o: objects){
    		o.setCollisionBehavior(srccid, type);
    	}
    }
    
    public void setCurrentScene (int currentLevelID, int currentSceneID) {
    	for(GameObject go: currentScene.getGameObjects().values()){
    		go.suspend();
    	}
    	currentScene = myGame.getScene(currentLevelID, currentSceneID);
    	for(GameObject go: currentScene.getGameObjects().values()){
    		go.resume();
    	}
    	//setGameState(Mode);
    }
    
    
    /*
     * (non-Javadoc)
     * @see jgame.platform.StdGame#doFrame()
     * For inGame States
     */
    @Override
    public void doFrame(){

    }
    
    /*
     * (non-Javadoc)
     * @see jgame.platform.StdGame#paintFrame()
     * For inGame states
     */
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
    public GameObject createPlayer(int unique_id, String url, double xpos, double ypos, String name, int colid, int levelID, int sceneID){
    	File file = new File(url);
    	String filename = file.getName();
        GameObject object = new Player(name, xpos, ypos, colid, filename);
        object.setPos(xpos, ypos);//just to make sure; may be deleted later
        myGame.addObject(levelID, sceneID, object);
        return object;
    }
    
    public GameObject createActor(int unique_id, String url, double xpos, double ypos, String name, int colid, int levelID, int sceneID){
    	File file = new File(url);
    	String filename = file.getName();
        GameObject object = new NonPlayer(name, xpos, ypos, colid, filename);
        object.setPos(xpos, ypos);//just to make sure; may be deleted later
        myGame.addObject(levelID, sceneID, object);
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
