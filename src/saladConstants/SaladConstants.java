package saladConstants;

public final class SaladConstants {
	
	public static final String SEPARATOR = ",";
	
	public static final String CREATE_LEVEL = "CreateLevel";
	public static final String SWITCH_LEVEL = "SwitchLevel";
	public static final String MODIFY_LEVEL = "ModifyLevel";
	public static final String CREATE_SCENE = "CreateScene";
	public static final String SWITCH_SCENE = "SwitchScene";
	public static final String MODIFY_SCENE = "ModifyScene";
	public static final String DELETE_LEVEL = "DeleteLevel";
	public static final String DELETE_SCENE = "DeleteScene";
	public static final String RESET_LEVEL_ID = "ResetLevelID";
	
	public static final String MODIFY_INPUTMANAGER = "ModifyInputManager";
	public static final String MODIFY_SCOREMANAGER = "ModifyScoreManager";
	public static final String MODIFY_TIMERMANAGER = "ModifyTimerManager";
//	public static final String MODIFY_BACKGROUND = "ModifyBackground";
	public static final String MODIFY_SCENE_VIEW = "ModifySceneView";
	
	public static final String GAME_EDITOR_PANEL = "GameEditor";
	public static final String ACTOR_EDITOR_PANEL = "ActorEditor";
	public static final String SCENE_EDITOR_PANEL = "SceneEditor";
	public static final String SCENE_PANEL = "Scene";
	public static final String ACTOR_PANEL = "Actor";
	public static final String MEDIA_PANEL = "Media";
	public static final String BEHAVIOR_PANEL = "Behavior";
	public static final String ELEMENT_LABEL = "Element";
	public static final String ROOT_ELEMENT_LABEL = "Root";
	
	
	public static final String STATE_GAMEOVER = "GameOver";
	public static final String STATE_STARTTITLE = "StartTitle";
	public static final String STATE_STARTLEVEL = "StartLevel";
	public static final String STATE_LEVELDONE = "LevelDone";
	public static final String STATE_LIFELOST = "LifeLost";
	public static final String INITIAL_SCORE = "InitialScore";
	
	
	public static final String CREATE_PLAYER = "CreatePlayer";
	public static final String MODIFY_PLAYER = "ModifyPlayer";
	public static final String DELETE_PLAYER = "DeletePlayer";
	public static final String CREATE_ACTOR = "CreateActor";
	public static final String MODIFY_ACTOR = "ModifyActor";
	public static final String DELETE_ACTOR = "DeleteActor";
	
	
	public static final String TEST_XMLFILE_URL = "test.xml";
	
	public static final String BACKGROUND = "Background";
	public static final String LEVEL = "level";
	public static final String SCENE = "scene";
	public static final String ID = "ID";
	public static final String IMAGE = "Image";
	public static final String PLAYER_INITIAL_POSITION = "PlayerInitialPosition";
	public static final String COLLISION_ID = "Colid";
	public static final String COLLISION = "Collision";
	
	//General Behaviors
	public static final String DIE = "die";
	public static final String MOVE = "move";
	public static final String SHOOT = "shoot";
	public static final String JUMP = "jump";
	public static final String COLLIDE = "collide";
	public static final String REMOVE = "remove";
	
	//Specific Behaviors
	public static final String EXPLODE = "Explode";
	public static final String PERISH_TOGETHER = "PerishTogether";
    public static final String HITTER_ELIMINATE_VICTIM  = "HitterEliminateVictim";
    public static final String STAY_ON_TILE = "StayOnTile";
	public static final String REGULAR_MOVE = "RegularMove";
	public static final String REGULAR_REMOVE = "RegularRemove";
	public static final String BACK_FORTH_MOVE = "BackForthMove";
	public static final String IMMOBILE = "Immobile";
	public static final String SLOW_SHOOT = "SlowShoot";
	public static final String QUICK_SHOOT = "QuickShoot";
	public static final String SHOW_CORPSE = "ShowCorpse";
	public static final String IMMORTAL = "Immortal";
	public static final String CAN_NOT_COLLIDE = "CanNotCollide";
    
    
	public static final String POSITION = "Position";
	public static final String NAME = "Name";
	public static final String CHANGE_TO_ID = "ChangeToID";
	public static final String CHANGE_COLLISION_ID = "ChangeCollisionID";
//	public static final String COLLISION_TILE = "CollisionTile"; // may not be needed
	public static final String SET_KEY = "SetKey";
	public static final int NULL_UNIQUE_ID = -1;
	public static final int SHOOT_LIVES = 1;
	public static final String SHOOT_NAME = "ShootObjects";
	public static final String LIVES = "Lives";
	public static final String SPEED = "Speed";
	public static final String MODIFY_GRAVITY = "ModifyGravity";
	public static final String GRAVITY_MAGNITUDE = "Magnitude";
    public static final String CREATE_GOAL = "CreateGoal";
    public static final String CREATE_TILE = "CreateTile";
    public static final String SET_DRAG_TILE = "SetDragTile";
    public static final String TILE_IMAGE = "TileImage";
    public static final String DRAG_IMAGE = "DragImage";
    public static final String MODIFY_ACTOR_IMAGE = "ModifyActorImage";
    public static final String MODIFY_PLAYER_IMAGE = "ModifyPlayerImage";
    public static final String WIN_BY_TIME = "WinByTime";
    public static final String WIN_BY_COLLISION = "WinByCollsion";
    public static final String SET_SCORE = "SetScore";
    public static final String WIN_BY_TILE_COLLISION = "WinByTileCollision";
    public static final String MODIFY_COLLISION_BEHAVIOR = "ModifyCollisionBehavior";
    public static final String MODIFY_TILE_COLLISION_BEHAVIOR = "ModifyTileCollisionBehavior";
    
	public static final String NULL_TOKEN = "NullToken";
	public static final String RESET_LEVEL_EXCEPTION = "Level can not be reset!";
	
	
    public static final String DEFAULT_ENGINE_RESOURCE_PACKAGE = "engineResources/";
    public static final String OBJECT_BEHAVIOR = "ObjectBehaviors";
    public static final String DATA_FORMAT_REFLECTION = "DataFormatReflection";
    public static final String TYPE_FORMAT = "TypeFormat";
    public static final String NONCLEAR_KEYS_FILE = "PlayerKeys";
    
    public static final String NON_CLEAR_KEYS = "NonClearKeys";

    public static final double DEFAULT_GRAVITY_MAGNITUDE = 0.1;
    public static final double DEFAULT_ACTOR_SPEED = 5;
    public static final int DEFAULT_INIT_LIVES = 3;
    
    public static final int POSITIVE_DIRECTION = 1;
    public static final int NEGATIVE_DIRECTION = -1;
    public static final int NO_DIRECTION = 0;

    public static final int PLAYER_ID = 0;
    
	public static final String MOVE_UP = "moveUp";
	public static final String MOVE_DOWN = "moveDown";
	public static final String MOVE_LEFT = "moveLeft";
	public static final String MOVE_RIGHT = "moveRight";
	public static final String SHOOT_KEY = "shoot";
	public static final String JUMP_KEY = "jump";
	
	public static final String Top = "Top";
	public static final String BOTTOM = "Bottom";
	public static final String LEFT = "Left";
	public static final String RIGHT = "Right";
	public static final String ALL = "All";


	public static final String PLAYER_INIT_POS = "PlayerInitialPosition";

	public static final String MODIFY_SCORE_MANAGER = "ModifyScoreManager";
	public static final String SET_COLLISION_SCORE = "SetCollisionScore";
	public static final String SET_TRANSITION_SCORE = "SetTransitionScore";
	public static final String MODIFY_BLOOD_MANAGER ="ModifyBloodManager";
	public static final String SET_COLLISION_BLOOD = "SetCollisionBlood";

	public static final String MODIFY_TRIGGER_EVENT_MANAGER = "ModifyTriggerEventManager";
	public static final String MODIFY_EVENT_MANAGER = "ModifyEventManager";
	public static final String MODIFY_LIFE_MANAGER = "ModifyLifeManager";

	public static final String SET_TRIGGER_BY_TIME = "SetTriggerByTime";
	public static final String SET_TRIGGER_BY_REMOVE = "SetTriggerByRemove";
	public static final String SET_TRIGGER_BY_COLLISION = "SetTriggerByCollision";
	public static final String SET_TRIGGER_BY_TILE_COLLISION = "SetTriggerByTileCollision";
	public static final String SET_EVENT_LEVEL_DONE = "SetEventLevelDone";
	public static final String SET_EVENT_ENEMY_SHOWER = "SetEventEnemyShower";
	public static final String SET_TRIGGER = "SetTrigger";
	public static final String SET_EVENT = "SetEvent";
	public static final String TRIGGER_TYPE = "TriggerType";
	public static final String EVENT_TYPE = "EventType";
	public static final String SET_INIT_LIVES = "SetInitLives";
	public static final String RESTORE_LIFE_BY_LEVEL ="RestoreLifeByLevel";
	


	


	


	
}
