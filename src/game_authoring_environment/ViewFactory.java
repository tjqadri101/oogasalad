package game_authoring_environment;

import javax.swing.JButton;

import controller.GAEController;
import reflection.Reflection;

public class ViewFactory {

	public ViewFactory(){

	}

	public static JButton createJButton(String str){
		JButton button = new JButton(str);
		return button;
	}

	public static Panel buildPanel(PanelType type,GAEController gController){
		try{
			return (Panel) Reflection.createInstance("game_authoring_environment."+type.toString()+"Panel",gController);
		} catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

}