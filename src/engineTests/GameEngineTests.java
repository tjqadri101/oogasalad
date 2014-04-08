package engineTests;

import static org.junit.Assert.*;
import objects.GameObject;

import org.junit.Test;

import stage.Game;
import engine.GameEngine;

public class GameEngineTests {
	
	protected GameEngine myEngine;

    protected void setUp(){
    	myEngine = new GameEngine(new Game());
    }
	
	@Test
	public void testCreateObjects(){
		GameObject object = myEngine.createActor(1, "actor_default.png", 0, 0, "Hero", 0, 0, 0);
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
