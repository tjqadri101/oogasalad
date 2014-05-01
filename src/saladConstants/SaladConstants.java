package saladConstants;

public final class SaladConstants {
	
	public static final String SEPARATOR = ",";
	
	public static final String CREATE_LEVEL = "CreateLevel";
	public static final String SWITCH_LEVEL = "SwitchLevel";
	public static final String MODIFY_LEVEL = "ModifyLevel";
	public static final String CREATE_SCENE = "CreateScene";
	public static final String SWITCH_SCENE = "SwitchScene";
	public static final String SWITCH_SCENE_TO_NEW_LEVEL_ID = "SwitchSceneToNewLevelID";
	public static final String MODIFY_SCENE = "ModifyScene";
	public static final String MODIFY_INIT_SCENE = "ModifyInitScene";
	public static final String SET_INIT_SCENE = "SetInitialScene";
	public static final String DELETE_LEVEL = "DeleteLevel";
	public static final String DELETE_SCENE = "DeleteScene";
	public static final String RESET_LEVEL_ID = "ResetLevelID";
	
	public static final String MODIFY_INPUTMANAGER = "ModifyInputManager";
	public static final String MODIFY_SCOREMANAGER = "ModifyScoreManager";
	public static final String MODIFY_TIMERMANAGER = "ModifyTimerManager";
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
	public static final String TILE_PANEL = "Tile";
	public static final String PLAYER_SPEED = "PlayerSpeed";
	
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
	public static final String MODIFY_ACTOR_ANIMATION = "ModifyActorAnimation";
	public static final String MODIFY_PLAYER_ANIMATION = "ModifyPlayerAnimation";
	
	public static final String VIEW_OFFSET = "ViewOffset";
	
	public static final String TEST_XMLFILE_URL = "test.xml";
	
	public static final String BACKGROUND = "Background";
	public static final String TSBACKGROUND = "TSBackground";
	public static final String LEVEL = "level";
	public static final String SCENE = "scene";
	public static final String ID = "ID";
	public static final String IMAGE = "Image";
	public static final String PLAYER_INITIAL_POSITION = "PlayerInitialPosition";
	public static final String COLLISION_ID = "Colid";
	public static final String COLLISION = "Collision";
	public static final String TILE_COLID = "TileID";
	
	//General Behaviors
	public static final String DIE = "die";
	public static final String MOVE = "move";
	public static final String SHOOT = "shoot";
	public static final String JUMP = "Jump";
	public static final String COLLIDE = "collide";
	public static final String REMOVE = "remove";
	public static final String UPDATE_JUMP = "JumpAnimation";
	
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
	public static final String CAN_MOVE_IN_AIR = "CanMoveInAir";
	public static final String CAN_NOT_COLLIDE = "CanNotCollide";
	public static final String CAN_NOT_JUMP = "CanNotJump";
    public static final String BACK_FORTH_MOVE_WITH_VERTICAL_SPEED = "BackForthMoveWithVerticalSpeed";
    public static final String STAY_ON_OBJECT = "StayOnObject";
    
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
	public static final String PLAYERSPEED = "PlayerSpeed";
	public static final String MODIFY_GRAVITY = "ModifyGravity";
	public static final String GRAVITY_MAGNITUDE = "Magnitude";
    public static final String CREATE_GOAL = "CreateGoal";
    public static final String CREATE_TILE = "CreateTile";
    public static final String SET_DRAG_TILE = "SetDragTile";
    public static final String TILE_MAP = "TileMap";
    public static final String TILE_IMAGE = "TileImage";
    public static final String DRAG_IMAGE = "DragImage";
    public static final String MODIFY_ACTOR_IMAGE = "ModifyActorImage";
    public static final String MODIFY_PLAYER_IMAGE = "ModifyPlayerImage";
    public static final String SET_SCORE = "SetScore";
    public static final String MODIFY_COLLISION_BEHAVIOR = "ModifyCollisionBehavior";
    public static final String MODIFY_TILE_COLLISION_BEHAVIOR = "ModifyTileCollisionBehavior";
    
	public static final String NULL_TOKEN = "NullToken";
	public static final String RESET_LEVEL_EXCEPTION = "Level can not be reset!";
	
	
    public static final String DEFAULT_ENGINE_RESOURCE_PACKAGE = "engineResources/";
    public static final String DEFAULT_ENGINE_PACKAGE = "engine/";
    public static final String OBJECT_BEHAVIOR = "ObjectBehaviors";
    public static final String DATA_FORMAT_REFLECTION = "DataFormatReflection";
    public static final String TYPE_FORMAT = "TypeFormat";
    public static final String NONCLEAR_KEYS_FILE = "PlayerKeys";
    public static final String OBJECT_IMAGES = "ObjectImages";
    
    public static final String NON_CLEAR_KEYS = "NonClearKeys";

    public static final double DEFAULT_GRAVITY_MAGNITUDE = 0.0;
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
	public static final String TIME = "Time";
	public static final String SET_SCORE_CONDITION = "SetScoreCondition";
	public static final String MODIFY_BLOOD_MANAGER ="ModifyBloodManager";
	public static final String SET_COLLISION_BLOOD = "SetCollisionBlood";
	public static final String SET_TRANSITION_BLOOD = "SetTransitionBlood";

	public static final String MODIFY_TRIGGER_EVENT_MANAGER = "ModifyTriggerEventManager";
	public static final String MODIFY_EVENT_MANAGER = "ModifyEventManager";
	public static final String MODIFY_LIFE_MANAGER = "ModifyLiveManager";

	public static final String TRIGGER_BY_TIME = "TriggerByTime";
	public static final String SET_TRIGGER_BY_REMOVE = "SetTriggerByRemove";
	public static final String SET_TRIGGER_BY_COLLISION = "SetTriggerByCollision";
	public static final String TRIGGER_BY_TILE_COLLISION = "TriggerByTileCollision";
	public static final String SET_EVENT_LEVEL_DONE = "SetEventLevelDone";
	public static final String SET_EVENT_ENEMY_SHOWER = "SetEventEnemyShower";
	public static final String SET_TRIGGER = "SetTrigger";
	public static final String SET_EVENT = "SetEvent";
	public static final String EVENT_LOSE_GAME = "EventLoseGame";

	public static final String TRIGGER_TYPE = "TriggerType";
	public static final String EVENT_TYPE = "EventType";
	public static final String SET_INIT_LIVES = "SetInitLives";
	public static final String RESTORE_LIFE_BY_LEVEL ="RestoreLifeByLevel";
	
	public static final String TILE_COLLISION = "TileCollision";
	public static final String SET_TILE_COLLISION_BLOOD = "SetTileCollisionBlood";
	public static final String SET_TILE_COLLISION_SCORE = "SetTileCollisionScore";
	

	public static final String MODIFY_TRANSITION_STATE = "ModifyTransitionState";
	public static final String GAME_STATE = "GameState";
	public static final String FRAME = "Frame";
	public static final String DISPLAY_MESSAGE = "DisplayMessage";
	public static final String DISPLAY_IMAGE = "DisplayImage";
	public static final String SET_NAME = "SetName";


	
	public static final String HELP_HTML_URL = "./src/game_authoring_environment/resources/help.html";
	public static final String ABOUT_HTML_URL = "./src/game_authoring_environment/resources/about.html";
	
	public static final String SPACE = " ";
	public static final String NULL = "null";
	public static final int NUM_SIDE_DETECTORS = 4;

	public static final int NEUTRAL_DIRECTION = 0;

	public static final String LEVEL_DONE = "LevelDone";
	public static final String SCENE_DONE = "SceneDone";
	public static final String SET_COLLISION_LIVE = "SetCollisionLive";
	public static final String SET_TILE_COLLISION_LIVE = "SetTileCollisionLive";


	public static final String MODIFY_GAME = "ModifyGame";


	public static final String BLOOD = "Blood";
	public static final String LIVE = "Live";
	public static final String SCORE = "Score";
	public static final String ENEMY_KILLED = "Enemies Killed";
	public static final String SLOW_SHOOT_BY_TIME = "SlowShootByTime";

	public static final String MODIFY_COLLISION_BEHAVIOUR = "ModifyCollisionBehavior";
	public static final String REBOUND = "Rebounce";

	public static final String KILL_BY_TILE = "KilledByTile";
	public static final String SET_COLLISION_LIFE = "SetCollisionLife";
	public static final String SET_TILE_COLLISION_LIFE = "SetTileCollisionLife";

	
	public static final String SPREAD_SHOOT_BY_TIME = "SpreadShootByTime";
	public static final String JUMP_BY_TIME = "JumpByTime";
	public static final String SPREAD_SHOOT = "SpreadShoot";
	public static final String FD_MOVE = "FDMove";
	public static final String BK_MOVE = "BKMove";
	public static final String UP_MOVE = "UPMove";
	public static final String DW_MOVE = "DWMOVE";
	public static final String TRIGGER_BY_REMOVE = "TriggerByRemove";
	public static final String TRIGGER_BY_COLLISION = "TriggerByCollision";
	public static final String EVENT_LEVEL_DONE = "EventLevelDone";
	public static final String EVENT_ENEMY_SHOWER = "EventEnemyShower";
	public static final String SHOOT_HIT_OBJECT = "ShootHitObject";

	public static final String PATH = "src/statistics/PersistentStats.txt";
	public static final String BEHAVIOR_METHOD = "BehaviorMethod";

	public static final String MANAGER_UPDATE = "update";
	public static final String LIVE_MANAGER = "LiveManager";
	public static final String TRIGGER_EVENT_MANAGER = "TriggerEventManager";
	public static final String OBJECT_DO_ACTION = "doAction";
	public static final String CHECK_KEY = "checkKey";

	public static final String BLOOD_FULL = "BloodFull";
	public static final String LIFE_INCREASE = "LifeIncrease";
	public static final String GAME_OVER = "GameOver";
	public static final String JUMP_ANIMATION = "JumpAnimation";
	public static final String EVENT_CHANGE_BLOOD = "EventChangeBlood";
	public static final String EVENT_CHANGE_LIVE = "EventChangeLive";
	public static final String EVENT_SWITCH_SCENE = "EventSwitchScene";

	
	
	public static final String BLOOD_MANAGER_PATH = "engineManagers.BloodManager";
	public static final String SCORE_MANAGER_PATH = "engineManagers.ScoreManager";
	public static final String LIVE_MANAGER_PATH = "engineManagers.LiveManager";
	public static final String EVENT_MANAGER_PATH = "engineManagers.TriggerEventManager";

	public static final String SET_INIT_BLOOD = "SetInitBlood";

	

	

	
	

	


}
