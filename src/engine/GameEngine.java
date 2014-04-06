package engine;

import jgame.Highscore;
import jgame.platform.StdGame;
import java.awt.Dimension;
import jgame.platform.JGEngine;
import datamanager.*;

public class GameEngine extends StdGame{

    DataManager myDataManager = new DataManager();
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

    public void readAndWrite(String order){
        myDataManager.creatOrModify(order);
    }

    public void doFrame(){

    }

    @Override
    public void paintFrame ()
    {
    }


}
