package datamanager;

import datamanager.*;
import engine.*;

public class DMTester {
    private static String playerInfo, enemyInfo, backgroundInfo, levelInfo, stageInfo;
    
    public static void main (String[] args)
    {
        stageInfo = "stageID Level Add";
        levelInfo = "levelID Level Add";
        backgroundInfo = "ID Backgrond Image Path";
        enemyInfo = "ID Actor Image Path";
        playerInfo = "ID Actor Image Path";
        
        Engine myTestEngine = new Engine();
        myTestEngine.readAndWrited(stageInfo);
  

    }

}
