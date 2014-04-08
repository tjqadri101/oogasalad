package engineTests;

import objects.GameObject;
import objects.NonPlayer;

import org.junit.Test;

import stage.Game;
import controller.DataController;
import engine.GameEngine;
import junit.framework.TestCase;

/*
 * @Author: Justin (Zihao) Zhang
 */

public class GameObjectTests extends TestCase {
	
		protected DataController myDataController;

	    protected void setUp(){
			myDataController = new DataController();
	    }
	    
		@Test
		public void testCreateScene(){
//			myDataController.initGameEngine(new Game());
//			String order = "CreatePlayer,ID,1,Image,game_authoring_environment.resources/actor_default.png,Position,0,0,Name,Hero";
//			myDataController.receiveOrder(order);
			GameEngine engine = new GameEngine(new Game());
			GameObject other = engine.createActor(1, "actor_default.png", 0, 0, "Hero");
		}
		
		@Test
		public void testCreateObjects(){
//			assertEquals(, other.get);
//			assertEquals("Chinese", myLangManager.getCurrentLanguage());
//			Throwable caught = null;
//			try {
//				assertEquals(false, myLangManager.setLanguage("Japanese"));
//			} catch (Throwable t) { caught = t; }
//			assertNotNull(caught);
//			assertSame(LanguageNotFoundException.class, caught.getClass());
		}
		
		@Test
		public void testModifyCollision(){
			
		}
		
		@Test
		public void testModifyMove(){
			
		}
		
}


