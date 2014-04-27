package behaviors;

import java.util.List;
import java.util.Random;
import engine.GameEngine;

public class EventEnemyShower extends Eventable{
    protected GameEngine myEngine;
    protected Random rg;
    protected int maxEnemy;
    protected String gfx;

    protected EventEnemyShower (GameEngine engine) {
        super(engine);
        myEngine = engine;
        rg = new Random();
        gfx = "mushroom1.png";
    }

    @Override
    public void doEvent (List<Object> params) {
        int enemyCounter = 0;
        while(true){
            int size = rg.nextInt(10)+40;
            myEngine.createActor(rg.nextInt(50), gfx, size, size, rg.nextInt(600), rg.nextInt(600), "enemyShower", 1, rg.nextInt(5));
            if(enemyCounter == maxEnemy){
                break;
            }
            enemyCounter++;
        }
    }
}
