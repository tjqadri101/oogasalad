package behaviors;

import java.util.List;

import objects.Player;
import engine.GameEngine;

public class KeyLiveIncrease extends Keyable{

	public KeyLiveIncrease(GameEngine engine) {
		super(engine);
	}

	@Override
	public void checkKey(List<Object> params) {
		for(Player p: myEngine.getGame().getAllPlayers()){
			myEngine.getGame().getLiveManager().changeLive(p.getID(), 1);	
		}
	}

}
