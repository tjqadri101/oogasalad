package game_authoring_environment;

import javax.swing.JButton;
import reflection.Reflection;

public class ViewFactory {

	public ViewFactory(){

	}

	public static JButton createJButton(String str){
		JButton button = new JButton(str);
		return button;
	}

	public static Panel buildPanel(PanelType type){
		try{
			return (Panel) Reflection.createInstance("game_authoring_environment."+type.toString()+"Panel");
		} catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

}