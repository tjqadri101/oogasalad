package util;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import reflection.Reflection;
import reflection.ReflectionException;


public class MethodChangeSpinner implements ChangeListener{

	
	 private Object myReceiver;
	 private Method myMethod;
	 private Object[] myArgs;
	 private Object myFieldObject;

	    /**
	     * Create action for given no-argument method of the target object.
	     */
	    public MethodChangeSpinner (Object fieldObject, Object target, String methodName)
	    {
	        this(fieldObject, target, methodName, new Object[0]);
	    }


	    /**
	     * Create action for given method of the target object that takes 
	     * arguments.
	     */
	    public MethodChangeSpinner (Object fieldObject, Object target, String methodName, Object ... args)
	    {
	        try
	        {
	        	myFieldObject = fieldObject;
	            myReceiver = target;
	            myArgs = args;
	            myMethod = target.getClass().getDeclaredMethod(methodName,
	                                                           Reflection.toClasses(args));
	        }
	        catch (Exception e)
	        {
	            throw new ReflectionException(e.getMessage());
	        }
	    }


	    /**
	     * Call the method when this action is performed.
	     */
	    public void stateChanged (ChangeEvent event)
	    {
	    	JSpinner curSpinner = (JSpinner)(event.getSource());
	        try
	        {
	        	Class<?> c1 = myFieldObject.getClass();
				Field field1 = c1.getField(curSpinner.getName());
				field1.set(myFieldObject,curSpinner.getValue());
	            myMethod.invoke(myReceiver, myArgs);
	        }	
	        catch (Exception e)
	        {
	            throw new ReflectionException(e.getMessage());
	        }

	    }   
	
}
