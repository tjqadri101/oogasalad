package engine;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import objects.NonPlayer;
import objects.Player;
import saladConstants.SaladConstants;
import stage.Game;
import engine.GameEngine;

public class EngineTest {
	
	public static final int LEVELS = 1;
	public static final int ENEMY_COLID = 1;
	public static final int MUSHROOM_COLID = 5;
	public static final int PLAYER_COLID = 4;
	public static final int BULLET_COLID = 2;
	public static final int BOMB_COLID = 6;
	public static final char TILE_COLID = '3';
	
	public static void main(String[] arg){

		GameEngine engine = new GameEngine(false);
		JFrame mainFrame = new JFrame("EngineTest");
		JPanel panel = new JPanel();
		panel.add(engine);
		mainFrame.add(panel, BorderLayout.CENTER);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.pack();
		mainFrame.setVisible(true);
    }
	
	public void testEngine(GameEngine engine){
		engine.loadingBegin();
		engine.setTileEditing(false);
		engine.setGameSpeed(1);
		Game game = new Game();
		engine.setGame(game);
		
		for (int i=0;i<LEVELS;i++){
			game.addLevel(i+1);
			
			game.addScene(i+1, i+2);
			engine.setCurrentScene(i+1, i+2);
			engine.setSceneView("floorImage.jpg",false,false,1200,400);
			engine.createTiles(TILE_COLID,0,20+i,1180,1);
			
			game.addScene(i+1, i);
			game.getLevel(i+1).setInitialScene(i);
			game.getScene(i+1, i).setPlayerInitPosition((i+1)*100, 200);
			game.addScene(i+1, i+2);
			engine.setCurrentScene(i+1, i);
			
			engine.setSceneView("floorImage.jpg",false,false,1200,400);
			engine.loadTileImage(TILE_COLID, "brick.png");
			engine.createTiles(TILE_COLID,0,15+i,1180,1);
			engine.createTiles(TILE_COLID,20,15,10,1);
			engine.createTiles('0',30,25+i,5,1);
			
			NonPlayer actor = engine.createActor(123+i, "poke-mon/0"+(13+i)+".gif", 200, 200, 300, 100, SaladConstants.NULL, ENEMY_COLID, 10);
			actor.setBehavior("RegularRemove");
			actor.setBehavior("BackForthMove", 8.0, 5);
			actor.setBehavior("SlowShootByTime", "ball20-red.gif", 20, 20, BOMB_COLID, 5.0, 100, 4);

			NonPlayer goomba = engine.createActor(300+i, "poke-mon/0"+(12+i)+".gif", 100, 100, 500.0, 100, SaladConstants.NULL, ENEMY_COLID, 10);
			goomba.setBehavior("RegularRemove");
			goomba.setBehavior("BackForthMove",5.0, 10);
			goomba.setBehavior("SpreadShootByTime", "ball20-red.gif", 20, 20, BOMB_COLID, 5.0, 4, 100, 4);

			NonPlayer mushroom = engine.createActor(200, "poke-mon/0"+(14+i)+".gif", 80, 80, 400, 100, "Mushroom", MUSHROOM_COLID, 10);
			mushroom.setBehavior("RegularRemove");
			mushroom.setBehavior("BackForthMove",6.0, 20);
			mushroom.setBehavior("SlowShootByTime", "ball20-red.gif", 20, 20, BOMB_COLID, 5.0, 100, 4);
		}
		
		game.getGravity().setMagnitude(0.1);
		
		game.getTransitionState("Title").setBackground("floorImage.jpg");
		game.getTransitionState("Title").addImage("splash.gif", 20, 30);
		game.getTransitionState("Title").addInstruction("NEW GAME! LET'S GO", 400, 300);
		
		Player player = engine.createPlayer(0, "poke-mon/105.gif", 100, 100, 300, 300, "Nick", PLAYER_COLID, 20);
//		engine.setObjectImage(player, "BKMove", "poke-mon/103.gif", 100, 100);
//		engine.setObjectImage(player, "FDMove", "poke-mon/102.gif", 100, 100);
//		engine.setObjectImage(player, "Jump", "poke-mon/100.gif", 100, 100);

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
		player.setCanMoveInAir(false);
		
		player.setViewOffset(600, 400);

		engine.loadingDone();
	}
}
