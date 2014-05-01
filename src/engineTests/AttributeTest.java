package engineTests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import stage.Game;
import util.SaladUtil;
import controller.DataController;
import engine.GameEngine;

public class AttributeTest {
	
	public static final String CREATE_ACTOR_ORDER = "CreateActor,ID,1,Image,actor_default.png,20,20,Position,0.0,0.0,Name,Hero,Colid,0,Lives,1";
	public static final String MODIFY_ACTOR_SHOOT = "ModifyActor,ID,1,SlowShoot,SlowShoot,actor_default.png,10,10,2,5.0,4";
	public static final String CREATE_LEVEL_1 = "CreateLevel,ID,1";
	public static final String CREATE_SCENE_1 = "CreateScene,ID,1,ID,0";
	public static final String SWITCH_SCENE_1 = "SwitchScene,ID,1,ID,0";
	public static final String DEFAULT_GRAVITY = "ModifyGravity,Magnitude,0.0";
	public static final String MODIFY_PERISHTOGETHER = "ModifyCollisionBehavior,Colid,0,PerishTogether,PerishTogether,1,Top";
	public static final String LIVEMANAGER_RESTORE = "ModifyLiveManager,RestoreLifeByLevel,true";
	public static final String SCOREMANAGER_INITIAL = "ModifyScoreManager,InitialScore,0";
	public static final String MODIFY_GAME_NAME = "ModifyGame,SetName,Game";
	public static final String BLOOD_COLLISION = "ModifyBloodManager,SetCollisionBlood,1,Collision,2,3";
	public static final String CREATE_PLAYER_ORDER = "CreatePlayer,ID,1,Image,actor_default.png,20,20,Position,0.0,0.0,Name,Hero,Colid,0,Lives,1";
	public static final String PLAYER_SPEED = "ModifyPlayer,ID,1,Speed,5.0,5.0";
	public static final String PLAYER_AIR = "ModifyPlayer,ID,1,CanMoveInAir,true";
	public static final String SCORE_INITIAL = "ModifyScoreManager,InitialScore,0";
	public static final String SCORE_COLLISION = "ModifyScoreManager,SetCollisionScore,5,Collision,1,2";
	public static final String SCORE_TIME = "ModifyScoreManager,SetScoreCondition,5,Time";
	public static final String INITIAL_SCENE = "ModifyLevel,ID,1,SetInitialScene,0";
	public static final String ACTOR_ANIMATION = "ModifyActorAnimation,ID,1,JumpAnimation,Jump,actor_default.png,10,10";
	public static final String SCENE_PLAYER_POSITION = "ModifyScene,ID,0,PlayerInitialPosition,0.0,0.0";
	public static final String MODIFY_PLAYER_SHOOT = "ModifyPlayer,ID,1,SlowShoot,SlowShoot,actor_default.png,10,10,2,5.0,4";
	public static final String EVENT_MANAGER = "ModifyTriggerManager,ID,0,EventEnemyShower,EventEnemyShower,3,actor_default.png";
//	public static final String RESET_COLLISION_ID = "";
	
	
	protected DataController myController;
	protected GameEngine myEngine;
	protected Game myGame;

	protected void setUp(){
    	myController = new DataController();
    	myEngine = myController.initGameEngine(true);
    	myGame = myEngine.getGame();
	}
	
	@Test
	public void testActor(){
    	myController = new DataController();
    	myEngine = myController.initGameEngine(true);
    	myGame = myEngine.getGame();
    	
    	myController.receiveOrder(CREATE_LEVEL_1);
    	myController.receiveOrder(CREATE_SCENE_1);
    	myController.receiveOrder(SWITCH_SCENE_1);
    	myController.receiveOrder(INITIAL_SCENE);
    	myController.receiveOrder(CREATE_ACTOR_ORDER);
    	myController.receiveOrder(MODIFY_ACTOR_SHOOT);
    	myController.receiveOrder(MODIFY_PERISHTOGETHER);
    	myController.receiveOrder(BLOOD_COLLISION);
    	myController.receiveOrder("ModifyTileCollisionBehavior,Colid,0,KilledByTile,KilledByTile,A,All");
    	
    	List<String> att = myGame.getAttributes();
    	assertEquals(att.get(0), MODIFY_PERISHTOGETHER);
    	assertEquals(att.get(1), CREATE_LEVEL_1);
    	assertEquals(att.get(2), INITIAL_SCENE);
    	assertEquals(att.get(3), CREATE_SCENE_1);
    	assertEquals(att.get(4), SWITCH_SCENE_1);
    	assertEquals(att.get(5), SCENE_PLAYER_POSITION);
    	assertEquals(att.get(6), CREATE_ACTOR_ORDER);
    	assertEquals(att.get(7), MODIFY_ACTOR_SHOOT);
    	System.out.println(att.get(8));
    	assertEquals(att.get(9), LIVEMANAGER_RESTORE);
    	assertEquals(att.get(10), BLOOD_COLLISION);
    	assertEquals(att.get(11), SCOREMANAGER_INITIAL);
    	assertEquals(att.get(12), MODIFY_GAME_NAME);
    	assertEquals(att.get(13), DEFAULT_GRAVITY);
	}
	
//	@Test
//	public void testPlayer(){
//    	myController = new DataController();
//    	myEngine = myController.initGameEngine(true);
//    	myGame = myEngine.getGame();
//    	
//    	myController.receiveOrder(CREATE_LEVEL_1);
//    	myController.receiveOrder(CREATE_SCENE_1);
//    	myController.receiveOrder(SWITCH_SCENE_1);
//    	myController.receiveOrder(CREATE_PLAYER_ORDER);
//    	myController.receiveOrder(MODIFY_PLAYER_SHOOT);
////    	myController.receiveOrder(ACTOR_ANIMATION);
//    	myController.receiveOrder(MODIFY_PERISHTOGETHER);
//    	myController.receiveOrder(BLOOD_COLLISION);
//    	
//    	List<String> att = myGame.getAttributes();
//    	assertEquals(att.get(0), DEFAULT_GRAVITY);
//    	assertEquals(att.get(1), CREATE_PLAYER_ORDER);
//    	assertEquals(att.get(2), MODIFY_PLAYER_SHOOT);
//    	assertEquals(att.get(3), PLAYER_SPEED);
//    	assertEquals(att.get(4), PLAYER_AIR);
//    	assertEquals(att.get(5), CREATE_LEVEL_1);
//	}
//	
//	@Test
//	public void testScoreManager(){
//    	myController = new DataController();
//    	myEngine = myController.initGameEngine(true);
//    	myGame = myEngine.getGame();
//    	myController.receiveOrder(SCORE_COLLISION);
//    	myController.receiveOrder(SCORE_TIME);
//    	
//    	List<String> att = myGame.getAttributes();
//    	assertEquals(att.get(0), DEFAULT_GRAVITY);
//    	assertEquals(att.get(1), LIVEMANAGER_RESTORE);
//    	assertEquals(att.get(2), SCORE_INITIAL);
//    	assertEquals(att.get(3), SCORE_TIME);
//    	assertEquals(att.get(4), SCORE_COLLISION);
//	}
//	
//	@Test
//	public void testTriggerManager(){
//    	myController = new DataController();
//    	myEngine = myController.initGameEngine(true);
//    	myGame = myEngine.getGame();
//    	myController.receiveOrder(CREATE_LEVEL_1);
//    	myController.receiveOrder(CREATE_SCENE_1);
//    	myController.receiveOrder(SWITCH_SCENE_1);
//    	myController.receiveOrder(INITIAL_SCENE);
//    	myController.receiveOrder(EVENT_MANAGER);
//    	
//        List<String> att = myGame.getAttributes();
//    	assertEquals(att.get(0), DEFAULT_GRAVITY);
//    	assertEquals(att.get(1), CREATE_LEVEL_1);
//    	assertEquals(att.get(2), INITIAL_SCENE);
//    	assertEquals(att.get(3), CREATE_SCENE_1);
//    	assertEquals(att.get(4), SWITCH_SCENE_1);
//    	assertEquals(att.get(5), SCENE_PLAYER_POSITION);
//
//	}
	
//	@Test
//	public void testAnimation(){
//    	myController = new DataController();
//    	myEngine = myController.initGameEngine(true);
//    	myGame = myEngine.getGame();
//    	myController.receiveOrder(CREATE_ACTOR_ORDER);
//    	myController.receiveOrder(ACTOR_ANIMATION);
//    	
//        List<String> att = myGame.getAttributes();
//    	myController.receiveOrder(CREATE_LEVEL_1);
//    	myController.receiveOrder(CREATE_SCENE_1);
//    	myController.receiveOrder(SWITCH_SCENE_1);
//    	myController.receiveOrder(INITIAL_SCENE);
//    	myController.receiveOrder(CREATE_ACTOR_ORDER);
//    	myController.receiveOrder(ACTOR_ANIMATION);
//    	
//    	assertEquals(att.get(0), DEFAULT_GRAVITY);
//    	assertEquals(att.get(1), LIVEMANAGER_RESTORE);
//    	assertEquals(att.get(2), SCORE_INITIAL);
//    	assertEquals(att.get(3), "ModifyGame,SetName,Game");
//    	assertEquals(att.get(4), CREATE_LEVEL_1);
//    	assertEquals(att.get(4), INITIAL_SCENE);
//    	assertEquals(att.get(5), CREATE_SCENE_1);
//    	assertEquals(att.get(6), SWITCH_SCENE_1);
//    	assertEquals(att.get(7), SCENE_PLAYER_POSITION);
//    	assertEquals(att.get(8), CREATE_ACTOR_ORDER);
//    	assertEquals(att.get(9), ACTOR_ANIMATION);
//    	
//	}
	
	
}
