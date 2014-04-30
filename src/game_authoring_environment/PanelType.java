package game_authoring_environment;

public enum PanelType {
	SCENE, ACTORS, MEDIA, BEHAVIORS, SUB, ACTOREDITOR, SCENEEDITOR, GAMEEDITOR, PLAYEREDITOR, COLLISIONEDITOR, TILE;

	
	public String toString(){
		return super.toString().substring(0, 1)+super.toString().substring(1).toLowerCase();
	}
}
