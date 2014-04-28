package engineTests;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import objects.NonPlayer;
import objects.Player;
import saladConstants.SaladConstants;
import stage.Game;
import engine.GameEngine;

public class EngineTest {
	
	public static final int ENEMY_COLID = 1;
	public static final int MUSHROOM_COLID = 5;
	public static final int PLAYER_COLID = 4;
	public static final int BULLET_COLID = 2;
	public static final char TILE_COLID = '3';
	
	public static void main(String[] arg){
		
		EngineTest et = new EngineTest();
		JFrame mainFrame = new JFrame("EngineTest");
		JPanel panel = new JPanel();
		panel.add(et.testEngine());
		mainFrame.add(panel, BorderLayout.CENTER);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.pack();
		mainFrame.setVisible(true);
    }
	
	public GameEngine testEngine(){
		
		GameEngine engine = new GameEngine(false);
		engine.setGameSpeed(1);
		Game game = new Game();
		engine.setGame(game);
		for (int i=0;i<10;i++){
			game.addLevel(i+1);
			game.addScene(i+1, i);
			game.getLevel(i+1).setInitialSceneID(i);
			game.getScene(i+1, i).setPlayerInitPosition((i+1)*100, 200);
			engine.setCurrentScene(i+1, i);
			
			engine.setSceneView(null,false,false,1200-100*i,40-i);
			engine.loadTileImage(TILE_COLID, "brick.png");
			engine.createTiles(TILE_COLID,0,15+i,1180,1);
			engine.createTiles(TILE_COLID,20,15,10,1);
			engine.createTiles('0',30,25+i,5,1);
			
//			NonPlayer actor = engine.createActor(123+i, "poke-mon/0"+(24+i)+".gif", 200, 200, 800, 450, SaladConstants.NULL, ENEMY_COLID, 1);
//			actor.setDieBehavior("RegularRemove");
//			actor.setMoveBehavior("BackForthMove", 8.0, 5);
//			actor.setShootBehavior("SlowShootByTime", "ball20-red.gif", 20, 20, BULLET_COLID, 5.0, 100);
//
//			NonPlayer goomba = engine.createActor(300+i, "poke-mon/0"+(42+i)+".gif", 100, 100, 500.0, 100, SaladConstants.NULL, ENEMY_COLID, 1);
//			goomba.setDieBehavior("RegularRemove");
//			goomba.setMoveBehavior("BackForthMove",5.0, 10);
			
			NonPlayer mushroom = engine.createActor(200, "poke-mon/"+(104+i)+".gif", 80, 80, 400, 100, "Mushroom", MUSHROOM_COLID, 1);
			mushroom.setDieBehavior("RegularRemove");
			mushroom.setMoveBehavior("BackForthMove",6.0, 20);
		}
		
		game.getGravity().setMagnitude(0.1);
		
		game.getTransitionState("Title").setBackground("floorImage.jpg");
		game.getTransitionState("Title").addImage(20, 30, "splash.gif");
		game.getTransitionState("Title").addInstruction(400, 3360, "NEW LEVEL! LET'S GO");
		
		
		
		Player player = engine.createPlayer(0, "actor_default.png", 100, 100, 300, 300, "Player", PLAYER_COLID, 6);
		player.setDieBehavior("RegularRemove");
		player.setJumpBehavior("Jump", 5.0, 1);
		player.setShootBehavior("QuickShoot", "ball20-red.gif", 20, 20, BULLET_COLID, 5.0, 4);
		player.setKey('L', "die");
		player.setKey('A', "moveLeft");
		player.setKey('D', "moveRight");
		player.setKey('W', "moveUp");
		player.setKey('S', "moveDown");
		player.setKey('J', "jump");
		player.setKey('B', "shoot");
		
		game.getCollisionManager().setDirectionalCollisionBehavior(BULLET_COLID, "PerishTogether", MUSHROOM_COLID,"All");
		game.getCollisionManager().setDirectionalCollisionBehavior(MUSHROOM_COLID, "HitterEliminateVictim", PLAYER_COLID,"Top");
		game.getCollisionManager().setDirectionalCollisionBehavior(PLAYER_COLID, "HitterEliminateVictim", MUSHROOM_COLID,"Left");
		game.getCollisionManager().setDirectionalCollisionBehavior(PLAYER_COLID, "HitterEliminateVictim", MUSHROOM_COLID,"Right");
		game.getCollisionManager().setDirectionalTileCollisionBehavior(ENEMY_COLID, "StayOnTile", TILE_COLID,"All");
		game.getCollisionManager().setDirectionalTileCollisionBehavior(PLAYER_COLID, "StayOnTile", TILE_COLID,"All");
//		game.getCollisionManager().setDirectionalTileCollisionBehavior(PLAYER_COLID, "StayOnTile", TILE_COLID,"Bottom");
		game.getCollisionManager().setDirectionalTileCollisionBehavior(MUSHROOM_COLID, "StayOnTile", TILE_COLID,"All");
		
		game.getBloodManager().setValue(-10, "Collision",MUSHROOM_COLID,PLAYER_COLID);
		game.getScoreManager().setValue(5, SaladConstants.COLLISION, ENEMY_COLID, PLAYER_COLID);
		game.getScoreManager().setValue(5, SaladConstants.COLLISION, MUSHROOM_COLID, PLAYER_COLID);
		game.getScoreManager().setValue(1, "Time");
		game.getScoreManager().setValue(50, "LevelDone", 1);
		
		player.getAnimationManager().modifyImage("Jump", "engine/actor_blank");
		engine.loadingDone();
        return engine;
	}
}
