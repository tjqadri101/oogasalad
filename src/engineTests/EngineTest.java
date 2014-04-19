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
		mainFrame.pack();
		mainFrame.setVisible(true);
    }
	
	public void test(GameEngine engine){
		
		Game game = new Game();
		engine.setGame(game);
		game.addLevel(1);
		game.addScene(1, 0);
		engine.setCurrentScene(1, 0);
		game.setGravity(0.1);
		
		engine.setTiles(0,30,980,1,2,"brick.png");
		engine.setTiles(20,15,10,1,2,"brick.png");
		engine.setTiles(30,30,5,1,0,"null");
		//engine.setTiles(0,0,40,1,1,"brick.png");
		engine.setBackground("bg.png");
		
		NonPlayer actor = engine.createActor(123, "Mario.png", 200, 200, 600.0, 450.0, null, 2, 1);
		actor.setDieBehavior("RegularDie");
		//actor.setMoveBehavior("RegularMove", -2, 1);
		
		Player player = engine.createPlayer(456, "actor_default.png", 100, 100, 100, 200, null, 1, 1);
		player.setDieBehavior("RegularDie");
		player.setJumpBehavior("Jump", 5.0);
		player.setShootBehavior("SlowShoot", "ball20-red.gif", 20, 20, 3, 5.0);
		player.setKey('L', "die");
		player.setKey('A', "moveLeft");
		player.setKey('D', "moveRight");
		player.setKey('W', "moveUp");
		player.setKey('S', "moveDown");
		player.setKey('J', "jump");
		player.setKey('B', "shoot");
		
		game.addCollisionPair(1, "HitterEliminateVictim", 2);
		game.addTileCollisionPair(2, "StayOnTile", 1);
		game.addTileCollisionPair(2, "StayOnTile", 2);
		game.addCollisionPair(3, "PerishTogether", 2);
	}
}
