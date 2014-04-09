package engineTests;

import objects.GameObject;
import objects.NonPlayer;

import org.junit.Test;

import stage.Game;
import stage.Level;
import stage.Scene;
import controller.DataController;
import engine.GameEngine;
import junit.framework.TestCase;

/*
 * @Author: Justin (Zihao) Zhang
 */

public class GameObjectTests extends TestCase {
	
		protected GameEngine myEngine;
		protected Game myGame;

	    protected void setUp(){
	    	myGame = new Game();
	    	myEngine = new GameEngine();
	    	myEngine.setGame(myGame);
//			Level level = new Level(0);
//			myGame.addLevel(level);
//			Scene scene = new Scene(0);
//			myGame.addScene(0, scene);
	    }
		
		@Test
		public void testCreateObjects(){
			GameObject object = myEngine.createActor(1, "actor_default.png", 0, 0, "Hero", 0, 0, 0);
		}
		
		@Test
		public void testModifyCollision(){
			GameObject object = myEngine.createActor(1, "actor_default.png", 0, 0, "Hero", 0, 0, 0);
			object.setCollisionBehavior(2, "HitterEliminateVictim");
		}
		
		@Test
		public void testModifyMove(){
			GameObject object = myEngine.createActor(1, "actor_default.png", 0, 0, "Hero", 0, 0, 0);
			object.setMoveBehavior("RegularMove", 1, 1);
		}
		
		@Test
		public void testModifyDie(){
			GameObject object = myEngine.createActor(1, "actor_default.png", 0, 0, "Hero", 0, 0, 0);
			object.setDieBehavior("RegularRemove");
		}
		
		@Test
		public void testResources(){
//			assertEquals(, other.get);
//			assertEquals("Chinese", myLangManager.getCurrentLanguage());
//			Throwable caught = null;
//			try {
//				assertEquals(false, myLangManager.setLanguage("Japanese"));
//			} catch (Throwable t) { caught = t; }
//			assertNotNull(caught);
//			assertSame(LanguageNotFoundException.class, caught.getClass());
		}
		
}


