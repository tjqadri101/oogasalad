package engine;

import stage.Game;
import stage.Scene;
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
/*
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
    
    private List<int[]> myCollsionPair = new ArrayList<int[]>();
    private int myCurrentLevelID = 1;
    private int myCurrentSceneID = 0;
    //private Scene myCurrentScene;
    private String Mode = "Edit";//String or boolean ?
    
    
    
    public GameEngine(){
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
        WorldManager.initWorld(this);
        setGameState(Mode);
    }
    
    public Game getGame(){
    	return myGame;
    }
    
    public void setCurrentLevel(int currentLevelID){
    	myCurrentLevelID = currentLevelID;
    }
    

    public void startEdit(){
//    	removeObjects(null,0);
    	defineImage("image", "-", 0, "actor_default.png", "-");
    	PhysicalObject c = new PhysicalObject("name", 0, "image"){

			@Override
			protected void paintShape() {
				// TODO Auto-generated method stub
				
			}
//    		public void move(){this.x+=1;}
    	};
    	c.setSpeed(1, 1);
    }
    public void doFrameEdit(){
    	moveObjects();
    	for (int[] cp: myCollsionPair){
    		checkCollision(cp[0], cp[1]);
    	}
    }
    public void paintFrameEdit(){
    	
    }
    
    
    
    //questionable
    public void addCollisionPair(int srccid, String type, int dstcid, int levelID, int sceneID){
    	myCollsionPair.add(new int[]{srccid,dstcid});
    	List<GameObject> objects = myGame.getObjectsByColid(dstcid);
    	for(GameObject o: objects){
    		o.setCollisionBehavior(type, srccid);
    	}
    }
    
    public void setCurrentScene (int currentSceneID) {
    	for(GameObject go: myGame.getScene(myCurrentLevelID, myCurrentSceneID).getGameObjects()){
    		go.suspend();
    	}
    	myCurrentSceneID = currentSceneID;
    	for(GameObject go: myGame.getScene(myCurrentLevelID, myCurrentSceneID).getGameObjects()){
    		go.resume();
    	}
    	//setGameState(Mode);
    }
    
    public void setGame (Game mygame) {
    	myGame = mygame;
    }
    
    public void createBackground(String url){
    	defineImage(url, "-", 0, url, "-");
    	setBGImage(url);
    }
    
    public void doFrameTitle(){
    	
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
    public void paintFrame (){
    	
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
    
    
    
    public int getCurrentLevelID(){
    	return myCurrentLevelID;
    }
    
    public int getCurrentSceneID(){
    	return myCurrentSceneID;
    }
    
//    public GameObject createPlayer(String unique_id, String url, String xpos, String ypos, String name, String colid){
//    	File file = new File(url);
//    	String filename = file.getName();
//        Player object = new Player(Integer.parseInt(unique_id), filename, Double.parseDouble(xpos), Double.parseDouble(ypos), name, Integer.parseInt(colid));
//        object.setPos(Double.parseDouble(xpos), Double.parseDouble(ypos));//just to make sure; may be deleted later
//        myGame.setPlayer(object);
//        return object;
//    }
    
    /** 
     * Should be called by the GameFactory to createPlayer
     * Return a created GameObject 
     */
    public GameObject createPlayer(int unique_id, String url, double xpos, double ypos, String name, int colid){
    	defineImage(url, "-", 0, url, "-");
    	Player object = new Player(unique_id, url, xpos, ypos, name, colid);
        //object.setPos(xpos, ypos);//just to make sure; may be deleted later
        myGame.setPlayer(object);
        return object;
    }
    
    public GameObject createActor(int unique_id, String url, double xpos, double ypos, String name, int colid){
    	defineImage(url, "-", 0, url, "-");
        GameObject object = new NonPlayer(unique_id, url, xpos, ypos, name, colid);
        object.setPos(xpos, ypos);//just to make sure; may be deleted later
        myGame.getScene(myCurrentLevelID, myCurrentSceneID).addObject(object);
        return object;
    }

    //what does this do?
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
