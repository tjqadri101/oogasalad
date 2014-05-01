package engine;

import objects.NonPlayer;
import objects.Player;
import saladConstants.SaladConstants;
import stage.Game;
import engine.GameEngine;

public class FlappyBird {
	
	public static final int LEVELS = 8;
	
	public static final int ENEMY_COLID = 1;
	public static final int MUSHROOM_COLID = 5;
	public static final int PLAYER_COLID = 4;
	public static final int BULLET_COLID = 2;
	public static final int BOMB_COLID = 6;
	public static final char TILE_COLID = '3';
	
	
	public void flappyBird(GameEngine engine){
		
		engine.setTileEditing(true);
		engine.setGameSpeed(1);
		Game game = new Game();
		engine.setGame(game);
		for (int i=0;i<LEVELS;i++){
			game.addLevel(i+1);
			game.addScene(i+1, i);
			game.getLevel(i+1).setInitialScene(i);
			game.getScene(i+1, i).setPlayerInitPosition(600, 23800);
			engine.setCurrentScene(i+1, i);
			
			engine.setSceneView(null,false,false,50,1200);
			engine.loadTileImage(TILE_COLID, "brick.png");
			engine.createTiles(TILE_COLID,0,0,1,1200);
			engine.createTiles(TILE_COLID,0,48,1,1200);
			
			NonPlayer actor = engine.createActor(123+i, "poke-mon/015.gif", 200, 200, 300, 100, SaladConstants.NULL, ENEMY_COLID, 10);
			actor.setBehavior("RegularRemove");
			actor.setBehavior("BackForthMove", 8.0, 5);
			actor.setBehavior("SlowShootByTime", "ball20-red.gif", 20, 20, BOMB_COLID, 5.0, 100, 4);
		}
		
		game.getGravity().setMagnitude(0);
		
		game.getTransitionState("Title").setBackground("floorImage.jpg");
		game.getTransitionState("Title").addImage("splash.gif", 20, 30);
		game.getTransitionState("Title").addInstruction("NEW GAME! LET'S GO", 400, 300);
		
		Player player = engine.createPlayer(0, "poke-mon/105.gif", 100, 100, 300, 300, "Nick", PLAYER_COLID, 30);
		engine.setObjectImage(0, "BKMove", "poke-mon/103.gif", 100, 100);
		engine.setObjectImage(0, "FDMove", "poke-mon/102.gif", 100, 100);
		engine.setObjectImage(0, "Jump", "poke-mon/100.gif", 100, 100);

		player.setBehavior("RegularRemove");
		player.setBehavior("Jump", 5.0, 1);
		player.setBehavior("SpreadShoot", "ball20-red.gif", 20, 20, BULLET_COLID, 5.0, 4, 8);
		player.setKey('L', "die");
		player.setKey('A', "moveLeft");
		player.setKey('D', "moveRight");
		player.setKey('W', "moveUp");
		player.setKey('S', "moveDown");
		player.setKey('J', "jump");
		player.setKey('B', "shoot");
		player.setSpeed(0, -10);
		
		
		game.getCollisionManager().setDirectionalCollisionBehavior(PLAYER_COLID, "ShootHitObject", ENEMY_COLID,"All");
		game.getCollisionManager().setDirectionalTileCollisionBehavior(PLAYER_COLID, "StayOnTile", TILE_COLID,"All");
		
		game.getBloodManager().setValue(-1, "Collision",PLAYER_COLID,BOMB_COLID);
		game.getBloodManager().setValue(-1, "Collision",ENEMY_COLID,BULLET_COLID);
		game.getBloodManager().setValue(-1, "Collision",MUSHROOM_COLID,BULLET_COLID);
		game.getScoreManager().setValue(5, SaladConstants.COLLISION, ENEMY_COLID, PLAYER_COLID);
		game.getScoreManager().setValue(5, SaladConstants.COLLISION, MUSHROOM_COLID, PLAYER_COLID);
		game.getScoreManager().setValue(1, "Time");
		game.getScoreManager().setValue(50, "LevelDone", 1);
		game.getGravity().setMagnitude(0.1);
		game.getLiveManager().setInitLives(3, 0);
		
		game.getInputManager().setKey('G', "LifeIncrease");
		game.getInputManager().setKey('F', "GameOver");
		game.getInputManager().setKey('K', "LevelDone");
		
		
		game.getTriggerManager().setEventOrTriggerBehavior(1, "TriggerByTime", 200);
		game.getTriggerManager().setEventOrTriggerBehavior(1, "EventEnemyShower", 5, "actor_default.png");
		engine.loadingDone();
	}
}
