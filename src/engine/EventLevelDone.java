package engine;

import objects.Player;
import stage.Game;
import stage.Scene;
import engineManagers.TriggerManager;

public class EventLevelDone {
    public Game myGame;
    public int myCurrentLevelID;
    public int myCurrentSceneID;
    public Scene myCurrentScene;
    public Player myPlayer;
    public TriggerManager myTriggerManager;
    public boolean triggerFlag;
    public int myMouseX;
    public int myMouseY;
    public int myMouseButton;
    public int myClickedID;
    public boolean myViewOffsetPlayer;
    public int myViewOffsetRate;
    public int myTileX;
    public int myTileY;
    public int myTileCid;
    public String myTileImgFile;
    public boolean isEditingMode;

    public EventLevelDone (TriggerManager myTriggerManager,
                           boolean triggerFlag,
                           boolean myViewOffsetPlayer,
                           int myViewOffsetRate) {
        this.myTriggerManager = myTriggerManager;
        this.triggerFlag = triggerFlag;
        this.myViewOffsetPlayer = myViewOffsetPlayer;
        this.myViewOffsetRate = myViewOffsetRate;
    }
    
    
}