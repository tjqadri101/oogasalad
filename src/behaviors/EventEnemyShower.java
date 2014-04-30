package behaviors;

import java.util.List;
import java.util.Random;
import engine.GameEngine;
/**
 * Showers the enemy of amount specified by user when trigger is triggered 
 * @author Steve (siyang) Wang
 */
public class EventEnemyShower extends Eventable{
    protected GameEngine myEngine;
    protected Random rg;
    protected int maxEnemy;
    protected String gfx;
    protected static final String ENEMY_SHOWER = "EnemyShower"; 

    public EventEnemyShower (GameEngine engine) {
        super(engine);
        myEngine = engine;
        rg = new Random();
        gfx = "mushroom1.png";
    }
    
    /**
     * Called via reflection to invoke the doEvent method
     * params may include: 
     * @param maxEnemy
     * @param gfx
     */
    
    @Override
    public void doEvent (List<Object> params) {
        maxEnemy = (int) params.get(0);
        System.out.println("EventEnemyShower: doEvent: " + maxEnemy);
        gfx = (String) params.get(1);
        int enemyCounter = 0;
        while(true){
            int size = rg.nextInt(10)+30;
            myEngine.createActor(rg.nextInt(50), gfx, size, size, rg.nextInt(800), rg.nextInt(600), ENEMY_SHOWER, 1, rg.nextInt(5));
            if(enemyCounter == maxEnemy){
                break;
            }
            enemyCounter++;
        }
    }
}
