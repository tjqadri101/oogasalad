package engine;

import stage.Game;
import jgame.JGColor;
import jgame.JGPoint;
import jgame.platform.StdGame;
import objects.GameObject;
import objects.NonPlayer;
import objects.Player;

import java.awt.Dimension;
/*
 * @Author: Justin (Zihao) Zhang
 */
public class GameEngine extends StdGame{

    public static final Dimension SIZE = new Dimension(1200, 900);
    public static final String TITLE = "Platformer Game Editor";
    public static final int FRAMES_PER_SECOND = 20;
    public static final int MAX_FRAMES_TO_SKIP = 2;
    public static final int JGPOINT_X = 960;
    public static final int JGPOINT_Y = 640;
    
    protected Game myGame;
    
    public GameEngine(JGPoint size){
    	initEngine(size.x,size.y); 
    }
    
    public GameEngine(){
		new GameEngine(new JGPoint(JGPOINT_X, JGPOINT_Y));
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
    public GameObject createPlayer(int colid, String gfxname, double xpos, double ypos, String name){
        GameObject object = new Player(name, xpos, ypos, colid, gfxname);
        object.setPos(xpos, ypos);//just to make sure; may be deleted later
        return object;
    }
    
    public GameObject createActor(int colid, String gfxname, double xpos, double ypos, String name){
        GameObject object = new NonPlayer(name, xpos, ypos, colid, gfxname);
        object.setPos(xpos, ypos);//just to make sure; may be deleted later
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
