
package engineTests;

import objects.GameObject;

import org.junit.Test;

import stage.Game;
import engine.GameEngine;
import junit.framework.TestCase;

/**
 * @Author: Justin (Zihao) Zhang
 */

public class GameObjectTests extends TestCase {
	
		protected GameEngine myEngine;
		protected Game myGame;

	    protected void setUp(){
	    	myGame = new Game();
	    	myEngine = new GameEngine(true);
	    	myEngine.setGame(myGame);
			myGame.addLevel(1);
			myGame.addScene(1, 0);
			myEngine.setCurrentScene(1, 0);
	    }
		
		@Test
		public void testCreateObjects(){
			GameObject object = myEngine.createActor(123, "Mario.png", 200, 200, 600.0, 450.0, null, 2, 1);
			assertEquals(object, myGame.getNonPlayer(1, 0, 0));
		}
		
		@Test
		public void testGetAttributes(){
			GameObject object = myEngine.createActor(123, "Mario.png", 200, 200, 600.0, 450.0, null, 2, 1);
//			object.setCollisionBehavior("HitterEliminateVictim", 2);
			object.setBehavior("RegularMove", 1.0, 1.0);
			object.setBehavior("RegularRemove");
			System.out.println(object.getAttributes());
		}
		
		@Test
		public void testModifyCollision(){
			GameObject object = myEngine.createActor(123, "Mario.png", 200, 200, 600.0, 450.0, null, 2, 1);
//			object.setCollisionBehavior("HitterEliminateVictim", 2);
//			assertEquals("HitterEliminateVictim", object.myCollisionMap.get(2));
		}
		
		@Test
		public void testModifyMove(){
			GameObject object = myEngine.createActor(123, "Mario.png", 200, 200, 600.0, 450.0, null, 2, 1);
			object.setBehavior("RegularMove", 1.0, 1.0);
//			assertEquals("RegularMove", object.myMoveBehavior);
		}
		
		@Test
		public void testModifyDie(){
			GameObject object = myEngine.createActor(123, "Mario.png", 200, 200, 600.0, 450.0, null, 2, 1);
			object.setBehavior("RegularRemove");
//			assertEquals("RegularRemove", object.myDieBehavior);
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


