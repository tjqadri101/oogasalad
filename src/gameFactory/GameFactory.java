package gameFactory;

import java.util.ResourceBundle;
import objects.GameObject;
import jgame.JGObject;
import util.reflection.*;
/*
 * @Author: Steve (Siyang) Wang
 */
public class GameFactory {
    private String myOrder;
    private GameObject myObject;
    public static final String RESOURCE_PACKAGE = "engineResources/";
    public static final String DEFAULT_FORMAT= "DataFormat";
    public static final String DEFAULT_NULL = "null";
        
        protected ResourceBundle myFormat;
        
        protected GameFactory(String order, GameObject object){
            myOrder = order;
            myObject = object;
            myFormat = ResourceBundle.getBundle(RESOURCE_PACKAGE + DEFAULT_FORMAT);
        }
        
        /**
         * Only takes String order as argument, for creation.
         */
        public GameObject processOrder(String order){
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
         * Takes Object and String order as argument, for modification.
         */
        public void processOrder(JGObject object, String order){
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
