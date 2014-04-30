package behaviors;

import java.util.List;

import objects.Player;
import engine.GameEngine;
/**
 * Check if the Player has entered to the center of a tile
 * This behavior class is different from tile collision 
 * @param int Player's ID
 * @param int tile x position
 * @param int tile y position
 * @param int tile x size
 * @param int tile y size
 * 
 * @author Main Justin (Zihao) Zhang
 */
public class TriggerByEnterTile extends Triggerable{
	
	public TriggerByEnterTile(GameEngine engine){
		super(engine);
	}
	
	/**
	 * Check if the Player has moved to a tile
	 * @param int Player's ID
	 * @param int tile x position
	 * @param int tile y position
	 * @param int tile x size
	 * @param int tile y size
	 */
	@Override
	public boolean checkTrigger(List<Object> params) {
		//Wait until tile order and tile stored info are determined
		int playerID = (Integer) params.get(0);
		int xpos = (Integer) params.get(1);
		int ypos = (Integer) params.get(2);
		int xsize = (Integer) params.get(3);
		int ysize = (Integer) params.get(4);
		
		Player player = myEngine.getGame().getPlayer(playerID);
		if(player.x + player.getXSize()/2 > xpos && player.x + player.getXSize()/2 < xpos + xsize 
				&& player.y + player.getYSize()/2 > ypos && player.y + player.getYSize()/2 < ypos + ysize){
			return true;
		}
		return false;
	}

}
