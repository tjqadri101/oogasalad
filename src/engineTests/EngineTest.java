package engineTests;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import jgame.JGObject;

import objects.GameObject;
import objects.NonPlayer;
import objects.Player;

import org.jbox2d.dynamics.Body;

import stage.Game;
import engine.GameEngine;

public class EngineTest {
	public static void main(String[] arg){
		JFrame mainFrame = new JFrame("test");
		GameEngine engine = new GameEngine(true);
		Game game = new Game();
		engine.setGame(game);
		JPanel panel = new JPanel();
		panel.add(engine);
		mainFrame.add(panel, BorderLayout.CENTER);
		mainFrame.pack();
		mainFrame.setVisible(true);
		game.addLevel(1);
		game.addScene(1, 0);
		engine.setCurrentScene(1, 0);
		NonPlayer actor = engine.createActor(0, "actor_default.png", 300.0, 500.0, null, 2, 1);
		//actor.setCollisionBehavior("HitterEliminateVictim", 0);
		//engine.setTiles();
		//actor.setMoveBehavior("RegularMove", -5, 5);
		Player player = engine.createPlayer(0, "actor_default.png", 300, 400, null, 1, 1);
		engine.addCollisionPair(1, "HitterEliminateVictim", 2);
		engine.addTileCollisionPair(1, "StickToTile", 1);
		player.setDieBehavior("RegularDie");
		actor.setDieBehavior("RegularDie");
		player.setKey('A', "moveLeft");
		player.setKey('D', "moveRight");
		player.setKey('W', "moveUp");
		player.setKey('S', "moveDown");
		//player.setSpeed(3.0);
		//engine.createBackground("actor_default.png");
    }
}
