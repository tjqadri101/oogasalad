package engineTests;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import objects.NonPlayer;
import objects.Player;

import stage.Game;
import engine.GameEngine;

public class EngineTest {
	
		
	public static void main(String[] arg){
		
		GameEngine engine = new GameEngine(true);
		EngineTest et = new EngineTest();
		et.test(engine);
		
		JFrame mainFrame = new JFrame("EngineTest");
		JPanel panel = new JPanel();
		panel.add(engine);
		mainFrame.add(panel, BorderLayout.CENTER);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.pack();
		mainFrame.setVisible(true);
    }
	
	public void test(GameEngine engine){
		
		Game game = new Game();
		engine.setGame(game);
		game.addLevel(1);
		game.addScene(1, 0);
		engine.setCurrentScene(1, 0);
		game.getGravity().setMagnitude(0.1);
		
		engine.setSceneSize(1200,40);
		engine.createTiles(2,"brick.png",0,30,1180,1);
		engine.createTiles(2,"brick.png",20,15,10,1);
		engine.createTiles(0,"null",30,30,5,1);
		engine.setDefaultTiles(2, "brick.png");
//		engine.createTiles(0,0,40,1,1,"brick.png");
		engine.setBackground("bg.png");
		
		NonPlayer actor = engine.createActor(123, "Mario.png", 200, 200, 600.0, 450.0, null, 2, 1);
		actor.setDieBehavior("RegularRemove");
//		actor.setMoveBehavior("RegularMove", -2.0, 0.0);
		actor.setMoveBehavior("BackForthMove", 8.0, 5);
		
		NonPlayer goomba = engine.createActor(300, "goomba.png", 100, 100, 500.0, 100.0, null, 2, 1);
		goomba.setDieBehavior("RegularRemove");
		goomba.setMoveBehavior("BackForthMove",5.0, 10);

		NonPlayer mushroom = engine.createActor(200, "mushroom1.png", 80, 80, 300.0, 100.0, null, 3, 1);
		mushroom.setDieBehavior("RegularRemove");
		mushroom.setMoveBehavior("BackForthMove",6.0, 20);
		
		Player player = engine.createPlayer(0, "actor_default.png", 100, 100, 100.0, 200.0, null, 1, 1);
//		player.setBBox(0, 0, 100, 1);
		player.setDieBehavior("RegularRemove");
		player.setJumpBehavior("Jump", 5.0, 1);
		player.setShootBehavior("QuickShoot", "ball20-red.gif", 20, 20, 3, 5.0, 5);
		player.setKey('L', "die");
		player.setKey('A', "moveLeft");
		player.setKey('D', "moveRight");
		player.setKey('W', "moveUp");
		player.setKey('S', "moveDown");
		player.setKey('J', "jump");
		player.setKey('B', "shoot");
//		player.suspend();
		
		game.getCollisionManager().setSideCollisionDetecter(player, "bottom", 5);
		game.getCollisionManager().setSideCollisionDetecter(player, "right", 6);
		game.getCollisionManager().setSideCollisionDetecter(player, "left", 6);
//		game.getCollisionManager().addCollisionPair(2, "HitterEliminateVictim", 1);
		game.getCollisionManager().addCollisionPair(5, "HitterEliminateVictim", 2);
		game.getCollisionManager().addCollisionPair(2, "HitterEliminateVictim", 6);
		game.getCollisionManager().addTileCollisionPair(1, "StayOnTile", 2);
		game.getCollisionManager().addTileCollisionPair(2, "StayOnTile", 2);
		
//	      game.getLevel(1).setWinBehavior("WinByTime", 400);
//        game.getLevel(1).setWinBehavior("WinByCollision", 123);
        game.getLevel(1).setWinBehavior("WinByTileCollision", 0, 700, 450, 50, 50);
	}
}
