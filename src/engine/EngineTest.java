package engine;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import objects.NonPlayer;
import objects.Player;
import saladConstants.SaladConstants;
import stage.Game;
import util.Music;
import engine.GameEngine;

public class EngineTest {
	
	public static final int LEVELS = 8;
	
	public static final int ENEMY_COLID = 1;
	public static final int MUSHROOM_COLID = 5;
	public static final int PLAYER_COLID = 4;
	public static final int BULLET_COLID = 2;
	public static final int BOMB_COLID = 6;
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
	
	public GameEngine game2(){
		return new FlappyBird().flappyBird();
	}
	
	public GameEngine testEngine(){
		
		GameEngine engine = new GameEngine(true);
		engine.setTileEditing(true);
		engine.setGameSpeed(1);
		Game game = new Game();
		engine.setGame(game);
		for (int i=0;i<LEVELS;i++){
			game.addLevel(i+1);
			game.addScene(i+1, i);
			game.getLevel(i+1).setInitialScene(i);
			game.getScene(i+1, i).setPlayerInitPosition((i+1)*100, 200);
			engine.setCurrentScene(i+1, i);
			
			engine.setSceneView(null,false,false,1200,400);
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
			
//			musicManager = new Music("src/engine/Sounds/PlayJoyful.wav");
//		        System.out.println("loadingDone: initGame is called here");
		}
		
//		engine.setStatusDisplay(engine.status_font, engine.status_color, "poke-mon/025.gif");
		
		game.getGravity().setMagnitude(0.1);
		
		game.getTransitionState("Title").setBackground("floorImage.jpg");
		game.getTransitionState("Title").addImage(20, 30, "splash.gif");
		game.getTransitionState("Title").addInstruction(400, 300, "NEW GAME! LET'S GO");
		
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
		
		player.setViewOffset(400, 300);
		
//		engine.modifyPlayerImage(0, "poke-mon/025.gif", 100, 100);
		
		game.getCollisionManager().setDirectionalCollisionBehavior(PLAYER_COLID, "ShootHitObject", BOMB_COLID,"All");
		game.getCollisionManager().setDirectionalCollisionBehavior(ENEMY_COLID, "ShootHitObject", BULLET_COLID,"All");
		game.getCollisionManager().setDirectionalCollisionBehavior(MUSHROOM_COLID, "ShootHitObject", BULLET_COLID,"All");
		game.getCollisionManager().setDirectionalCollisionBehavior(MUSHROOM_COLID, "HitterEliminateVictim", PLAYER_COLID,"Top");
		game.getCollisionManager().setDirectionalCollisionBehavior(PLAYER_COLID, "HitterEliminateVictim", MUSHROOM_COLID,"Left");
		game.getCollisionManager().setDirectionalCollisionBehavior(PLAYER_COLID, "HitterEliminateVictim", MUSHROOM_COLID,"Right");
		game.getCollisionManager().setDirectionalTileCollisionBehavior(ENEMY_COLID, "StayOnTile", TILE_COLID,"All");
		game.getCollisionManager().setDirectionalTileCollisionBehavior(PLAYER_COLID, "StayOnTile", TILE_COLID,"All");
//		game.getCollisionManager().setDirectionalTileCollisionBehavior(PLAYER_COLID, "StayOnTile", TILE_COLID,"Bottom");
		game.getCollisionManager().setDirectionalTileCollisionBehavior(MUSHROOM_COLID, "StayOnTile", TILE_COLID,"All");
		
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
		
		engine.gotoGameState("Title");
		engine.setCurrentScene(1, 0);
		
		game.getTriggerManager().setEventOrTriggerBehavior(1, "TriggerByTime", 200);
		game.getTriggerManager().setEventOrTriggerBehavior(1, "EventEnemyShower", 5, "actor_default.png");
		System.out.println("EngineTest LoadingDone");
		engine.loadingDone();
        return engine;
	}
}
