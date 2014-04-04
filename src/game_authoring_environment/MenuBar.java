package game_authoring_environment;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar{

	public MenuBar(){
		super();
		JMenu fileMenu = createFileMenu();
		this.add(fileMenu);
	}
	
	private JMenu createFileMenu(){
		JMenu menu = new JMenu("File");
		//Add save button
		JMenuItem saveButton = new JMenuItem("Save");
		saveButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed (ActionEvent e){
				try{
				}
				catch (Exception k) {
					
				}
			}
		});
		menu.add(saveButton);
		//Add load button
		JMenuItem loadButton = new JMenuItem("Load");
		loadButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed (ActionEvent e){
				try{
				}
				catch (Exception k) {
					
				}
			}
		});
		menu.add(loadButton);

		return menu;
	}
	

}
