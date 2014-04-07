package gameFactory;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;
import objects.GameObject;
import reflection.Reflection;
import stage.Game;
import jgame.JGObject;
import jgame.platform.JGEngine;
import util.reflection.*;
/*
 * @Author: Steve (Siyang) Wang
 */
public class GameFactory {
    private JGEngine myEngine;
    private String myOrder;
    private int  myLevelID, mySceneID;
    private Game myGame;
    private GameObject myObject;
    public static final String RESOURCE_PACKAGE = "engineResources/";
    public static final String DEFAULT_FORMAT= "DataFormat";
    public static final String DEFAULT_NULL = "null";
    
    private static final 

        protected ResourceBundle myFormat;
        
        protected GameFactory(JGEngine engine){
            myEngine = engine;
            myFormat = ResourceBundle.getBundle(RESOURCE_PACKAGE + DEFAULT_FORMAT);
        }
        
        /**
         * Only couple things as argument, use reflection to creat or modify object instance.
         */
        public GameObject processOrder(Game game, int levelID, int sceneID, String order){
//            myLevelID = levelID;
//            mySceneID = sceneID;
//            myGame = game;
//            myOrder = order;
//            myObject = object;
            
            List<GameObject> results = new ArrayList<GameObject>();
            Enumeration<String> iter = myFormat.getKeys();
            parseOrder(order);
            
            
            try{
                    Object myObject = Reflection.createInstance(myFormat.getString(myMoveMethod), this);
                    Reflection.callMethod(behavior, "move", mySetXSpeed, mySetYSpeed);      
            } catch (Exception e){
                    e.printStackTrace(); //should never reach here
            }


            return null;
        }

        private void parseOrder (String order) {
            // TODO Auto-generated method stub

        }

        
        
        
        
        
        
        /**
         * Takes Object instance and String order as argument, for modification.
         */
        public void processOrder(GameObject object, String order){
            parseOrder(order);
            
            try{
//                http://docs.oracle.com/javase/tutorial/reflect/member/ctorInstance.html
//                http://java.sun.com/docs/books/tutorial/reflect/member/methodInvocation.html
//                http://docs.oracle.com/javase/tutorial/reflect/member/fieldValues.html
//                Object instance = Class.forName("Foobar").newInstance();
                Object behavior = Reflection.createInstance(myFormat.getString(myMoveMethod), this);
                Reflection.callMethod(behavior, "move", mySetXSpeed, mySetYSpeed);      
            } catch (Exception e){
                e.printStackTrace(); //should never reach here
            }

        }



}
