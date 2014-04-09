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
			myGame.addLevel(0);
			myGame.addScene(0, 0);
	    }
		
		@Test
		public void testCreateObjects(){
			GameObject object = myEngine.createActor(1, "actor_default.png", 0, 0, "Hero", 0, 0, 0);
			assertEquals(object, myGame.getGameObject(0, 0, 0));
		}
		
		@Test
		public void testModifyCollision(){
			GameObject object = myEngine.createActor(1, "actor_default.png", 0, 0, "Hero", 0, 0, 0);
			object.setCollisionBehavior(2, "HitterEliminateVictim");
			assertEquals("HitterEliminateVictim", object.myCollisionMap.get(2));
		}
		
		@Test
		public void testModifyMove(){
			GameObject object = myEngine.createActor(1, "actor_default.png", 0, 0, "Hero", 0, 0, 0);
			object.setMoveBehavior("RegularMove", 1, 1);
			assertEquals("RegularMove", object.myMoveBehavior);
		}
		
		@Test
		public void testModifyDie(){
			GameObject object = myEngine.createActor(1, "actor_default.png", 0, 0, "Hero", 0, 0, 0);
			object.setDieBehavior("RegularRemove");
			assertEquals("RegularRemove", object.myDieBehavior);
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


