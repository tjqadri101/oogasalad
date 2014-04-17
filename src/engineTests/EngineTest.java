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
		
		//engine.setTiles();
		engine.createBackground("bg.png");
		
		NonPlayer actor = engine.createActor(0, "actor_default.png", 300.0, 500.0, null, 2, 1);
		actor.setDieBehavior("RegularDie");
		//actor.setMoveBehavior("RegularMove", -5, 5);
		
		Player player = engine.createPlayer(0, "actor_default.png", 100, 400, null, 1, 1);
		player.setDieBehavior("RegularDie");
		player.setJumpBehavior("Jump", 5.0);
		player.setDieBehavior("RegularDie");
		player.setKey('L', "die");
		player.setKey('A', "moveLeft");
		player.setKey('D', "moveRight");
		player.setKey('W', "moveUp");
		player.setKey('S', "moveDown");
		player.setKey('J', "jump");
		player.setSpeed(3.0);
		
		engine.addCollisionPair(1, "HitterEliminateVictim", 2);
		engine.addTileCollisionPair(1, "StickToTile", 1);
	}
}
