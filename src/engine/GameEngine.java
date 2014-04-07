package engine;

import parser.*; 
import jgame.Highscore;
import jgame.platform.StdGame;

import java.awt.Dimension;
import objects.GameObject;

import controller.DataController;
import jgame.platform.JGEngine;

public class GameEngine extends StdGame{

    public static final Dimension SIZE = new Dimension(1200, 900);
    public static final String TITLE = "Platformer Game Editor";

    @Override
    public void initCanvas () {
        // TODO Auto-generated method stub
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
        // TODO Auto-generated method stub
        setFrameRate(20, 2);
    }
    
    public void doFrame(){

    }

    @Override
    public void paintFrame ()
    {
    }
    
    /* @Shenghan: create this method in Engine
     * Should be called by the GameFactory to createPlayer
     * Return a  
     */
    public GameObject createPlayer(){
        // NEED IMPLEMENTATION
        return null;
    }
    
    public GameObject createActor(){
        // NEED IMPLEMENTATION
        return null;
    }


}
