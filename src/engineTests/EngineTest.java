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
		game.addLevel(1);
		game.addScene(1, 0);
		engine.setCurrentScene(1, 0);
		game.getGravity().setMagnitude(0.1);
		
		game.getTransitionState("LevelDone").setBackground("floorImage.jpg");
		game.getTransitionState("LevelDone").addImage(20, 30, "poke-mon/111.gif");
		
		engine.setSceneView(null,false,false,1200,40);
		engine.loadTileImage(TILE_COLID, "brick.png");
		engine.createTiles(TILE_COLID,0,30,1180,1);
		engine.createTiles(TILE_COLID,20,15,10,1);
		engine.createTiles('0',30,30,5,1);
		
		NonPlayer actor = engine.createActor(123, "poke-mon/024.gif", 200, 200, 800, 450, null, ENEMY_COLID, 1);
		actor.setDieBehavior("RegularRemove");
		actor.setMoveBehavior("BackForthMove", 8.0, 5);
		actor.suspend();
		
		NonPlayer goomba = engine.createActor(300, "poke-mon/042.gif", 100, 100, 500.0, 100, null, ENEMY_COLID, 1);
		goomba.setDieBehavior("RegularRemove");
		goomba.setMoveBehavior("BackForthMove",5.0, 10);
		goomba.suspend();

		NonPlayer mushroom = engine.createActor(200, "mushroom1.png", 80, 80, 400, 100, null, MUSHROOM_COLID, 1);
		mushroom.setDieBehavior("RegularRemove");
		mushroom.setMoveBehavior("BackForthMove",6.0, 20);
		mushroom.suspend();
		
		Player player = engine.createPlayer(0, "actor_default.png", 100, 100, 100, 200, null, PLAYER_COLID, 6);
		player.setDieBehavior("RegularRemove");
		player.setJumpBehavior("Jump", 5.0, 1);
		player.setShootBehavior("SlowShoot", "ball20-red.gif", 20, 20, BULLET_COLID, 5.0);
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
		
		game.getScoreManager().setScore(5, SaladConstants.COLLISION, ENEMY_COLID, PLAYER_COLID);
		game.getScoreManager().setScore(5, SaladConstants.COLLISION, MUSHROOM_COLID, PLAYER_COLID);
		
//	      game.getLevel(1).setWinBehavior("WinByTime", 400);
//        game.getLevel(1).setWinBehavior("WinByCollision", 123);
        game.getLevel(1).setWinBehavior("WinByTileCollision", 0, 700, 450, 50, 50);
        
        return engine;
	}
}
