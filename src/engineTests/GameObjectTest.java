package engineTests;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import objects.NonPlayer;
import objects.Player;
import saladConstants.SaladConstants;
import stage.Game;
import engine.GameEngine;

public class GameObjectTest {
	
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
		
		GameEngine engine = new GameEngine(true);
		engine.setGameSpeed(1);
		Game game = new Game();
		engine.setGame(game);
		game.addLevel(1);
		game.addScene(1, 0);
		game.getLevel(1).setInitialScene(0);
		engine.setCurrentScene(1, 0);
		game.getScene(1, 0).setPlayerInitPosition(100, 200);
		game.getGravity().setMagnitude(0.1);
		
		game.getTransitionState("Title").setBackground("floorImage.jpg");
		
		
		engine.setSceneView(null,false,false,1200,40);
		engine.loadTileImage(TILE_COLID, "brick.png");
		engine.createTiles(TILE_COLID,0,30,1180,1);
		engine.createTiles(TILE_COLID,20,15,10,1);
		engine.createTiles('0',30,30,5,1);
		
		NonPlayer actor = engine.createActor(123, "poke-mon/024.gif", 200, 200, 800, 450, SaladConstants.NULL, ENEMY_COLID, 1);
		actor.setBehavior("RegularRemove");
		actor.setBehavior("BackForthMove", 8.0, 5);
		actor.setBehavior("SlowShootByTime", "ball20-red.gif", 20, 20, BULLET_COLID, 5.0, 100);
		
		NonPlayer goomba = engine.createActor(300, "poke-mon/042.gif", 100, 100, 500.0, 100, SaladConstants.NULL, ENEMY_COLID, 1);
		goomba.setBehavior("RegularRemove");
		goomba.setBehavior("BackForthMove",5.0, 10);

		NonPlayer mushroom = engine.createActor(200, "poke-mon/104.gif", 80, 80, 400, 100, SaladConstants.NULL, MUSHROOM_COLID, 1);
		mushroom.setBehavior("RegularRemove");
		mushroom.setBehavior("BackForthMove",6.0, 20);
		
		Player player = engine.createPlayer(0, "actor_default.png", 100, 100, 300, 300, SaladConstants.NULL, PLAYER_COLID, 6);
		player.setBehavior("RegularRemove");
		player.setBehavior("Jump", 5.0, 1);
		player.setBehavior("SlowShoot", "ball20-red.gif", 20, 20, BULLET_COLID, 5.0, 4);
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
		
		game.getScoreManager().setValue(5, SaladConstants.COLLISION, ENEMY_COLID, PLAYER_COLID);
		game.getScoreManager().setValue(5, SaladConstants.COLLISION, MUSHROOM_COLID, PLAYER_COLID);
		engine.loadingDone();
        return engine;
	}
}