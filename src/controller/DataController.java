package controller;

import gameFactory.GameFactory;

import java.util.ResourceBundle;

import objects.GameObject;
import stage.Game;
import stage.Scene;

public class DataController {
	public static final String DEFAULT_RESOURCE_PACKAGE = "engineResources/";
	public static final String DEFAULT_CREATEORMODIFY = "CreationOrModify";
	public static final String IS_CREATION = "Creation";
	
	//Exporter myExporter;
	protected GameFactory myFactory;
	//Importer myImporter;
	//GameEngine myGameEngine;
	protected Game myGame;
	protected ResourceBundle myCreateModifyTeller;
	protected Scene myCurrentScene;
	
	public DataController(){
		//myExporter = new Exporter();
		myFactory = new GameFactory();
		//myImporter = new Importer();
		myGame = new Game();
		myCreateModifyTeller = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + DEFAULT_CREATEORMODIFY);
	}
	
	public void receiveOrder(String order){
		String[] orders = order.split(",");
		if(myCreateModifyTeller.getString(orders[0]) == IS_CREATION){
			GameObject o = myFactory.processOrder(order);
			myCurrentScene.addObject(o);
		}
		else{
			
		}
		//Object o = myFactory.processOrder(order);
		//myGame.add(o);
		//myGameEngine.receiveObject(o);
	}
	
	public void receiveXML(String url){
		//Game game = myImporter.parser(url);
	}

}
