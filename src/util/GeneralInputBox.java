package util;
import javax.swing.*;
import java.awt.event.*;

/** 
 * @author Steve (Siyang) Wang
 * General static method for adding message dialog box 
 */
public class GeneralInputBox{
    
    public static String showMessageBox(){
        return JOptionPane.showInputDialog(null, "Background music : Creepy or Happy?", "Happy");
    }   
}