//package engine;
//
//import saladConstants.SaladConstants;
//import stage.Game;
//import stage.Transition;
//import stage.Transition.StateType;
//import util.SaladUtil;
//import jgame.JGColor;
//import jgame.platform.StdGame;
//import objects.GameObject;
//import objects.Gravity;
//import objects.NonPlayer;
//import objects.Player;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map.Entry;
//import java.util.ResourceBundle;
//import engineManagers.TriggerManager;
//
//
///**
// * @Author: Isaac (Shenghan) Chen
// * @Contribution: Justin (Zihao) Zhang
// */
//public class GameEngine extends StdGame{
//
//    public static final int FRAMES_PER_SECOND = 70;
//    public static final int MAX_FRAMES_TO_SKIP = 2;
//    public static final int JGPOINT_X = 800;//
//    public static final int JGPOINT_Y = 600;//
//    public static final int CANVAS_WIDTH = 40;
//    public static final int CANVAS_HEIGHT = 30;
//    public static final int TILE_WIDTH = 20;
//    public static final int TILE_HEIGHT = 20;
//    
//    protected EventLevelDone data = new EventLevelDone(new TriggerManager(this), false, true, 1);
//
//    public GameEngine(boolean editing){
//        initEngineComponent(JGPOINT_X, JGPOINT_Y);
//        isEditingMode = editing;
//    }
//    
//    @Override
//    public void initCanvas () {
//        setCanvasSettings(CANVAS_WIDTH,CANVAS_HEIGHT,TILE_WIDTH,TILE_HEIGHT,null,null,null);
//    }
//
//    @Override
//    public void initGame () {
//        setFrameRate(FRAMES_PER_SECOND, MAX_FRAMES_TO_SKIP);
//        
//        //setTileSettings("#",2,0);
//                //setPFWrap(false,true,0,0);
//        
//        if(isEditingMode){
//                setGameState("InGame");
//        }
//    }
//    
//      
//    public boolean checkGoal(){
//        if(data.myCurrentScene == null) return false;
//        String winBehavior = data.myGame.getLevel(data.myCurrentLevelID).getWinBehavior();
//        if(winBehavior == null) return false;
//        List<Object> winParameters = data.myGame.getLevel(data.myCurrentLevelID).getWinParameters();
//        ResourceBundle behaviors = ResourceBundle.getBundle(SaladConstants.DEFAULT_ENGINE_RESOURCE_PACKAGE + SaladConstants.OBJECT_BEHAVIOR);
//        Object answer = SaladUtil.behaviorReflection(behaviors, winBehavior, winParameters, "checkGoal", this);
//        data.triggerFlag = (boolean) answer;
//        return (Boolean) answer;
//    }
//    
//    public boolean checkTrigger(){
//        if(data.myCurrentScene == null) return false;
//        String eventBehavior = data.myGame.getLevel(data.myCurrentLevelID).getEventBehavior();
//        if(eventBehavior == null) return false;
//        
////      consider combineing the checkTrigger to checkGoal()
//        return true;
//    }   
//    
//    
//    
//    
///*    if(checkGoal()){
//        if(level>=3){
//                gameOver();
//                System.out.println("printedFrom Engine, gameOver");
//        }
//        else
//                levelDone();
//}*/
//    
//    public void startEdit(){
//        removeObjects(null,0);//remove?
//    }
//    
//    //drag;move->gravity->collision->setViewOffset
//    public void doFrameInGame(){
//        timer++;//
//        if (data.myCurrentScene == null) return;
//        boolean viewOffset = false;
//        if(drag()) data.myViewOffsetPlayer = false;
//        else{
//                moveObjects();
//                Gravity g = data.myGame.getGravity();
//                g.applyGravity(data.myPlayer);
//                for(GameObject go: data.myCurrentScene.getGameObjects()){
//                        g.applyGravity(go);
//                }
//                for (int[] pair: data.myGame.getCollisionManager().getCollisionPair()){
//                        checkCollision(pair[0], pair[1]);
//                }
//                for (int[] pair: data.myGame.getCollisionManager().getTileCollisionPair()){
//                        checkBGCollision(pair[0], pair[1]);
//                }
//                viewOffset = setViewOffsetEdit();
//                if(!viewOffset) setViewOffsetPlayer();
//                else data.myViewOffsetPlayer = false;
//        }
//        if(!viewOffset) setViewOffsetEdit();
//        data.myTriggerManager.checkTrigger();
//        if(data.triggerFlag){
//                if(level>=3){
//                        gameOver();
//                        System.out.println("printedFrom Engine, gameOver");
//                }
//                else
//                        levelDone();
//        }
//    }
//
//        private void setViewOffsetPlayer() {
//                if (data.myPlayer != null){
//                        if(data.myViewOffsetRate > 1) data.myViewOffsetRate -= 1;
//                        if(data.isEditingMode && !data.myViewOffsetPlayer) data.myViewOffsetRate = 35;
//                        data.myViewOffsetPlayer = true;
//                        int desired_viewXOfs = (int) data.myPlayer.x+data.myPlayer.getXSize()/2-viewWidth()/2;
//                        int desired_viewYOfs = (int) data.myPlayer.y+data.myPlayer.getYSize()/2-viewHeight()/2;
//                        setViewOffset((desired_viewXOfs-viewXOfs())/data.myViewOffsetRate+viewXOfs(),(desired_viewYOfs-viewYOfs())/data.myViewOffsetRate+viewYOfs(),false);                     
//                }
//        }
//
//    private boolean setViewOffsetEdit() {
//        int speed = 10;
//        double margin = 0.1;
//        if (!data.isEditingMode) return false;
//        int XOfs = 0;
//        int YOfs = 0;
//        double x_ratio = 1.0*getMouseX()/viewWidth();
//        double y_ratio = 1.0*getMouseY()/viewHeight();
//        if (0 < x_ratio && x_ratio < margin){
//                XOfs -= speed*(1-x_ratio/margin);
//        }
//        if ((1-margin) < x_ratio && x_ratio < 1){
//                XOfs += speed*(1-(1-x_ratio)/margin);
//        }
//        if (0 < y_ratio && y_ratio < margin){
//                YOfs -= speed*(1-y_ratio/margin);
//        }
//        if ((1-margin) < y_ratio && y_ratio < 1){
//                YOfs += speed*(1-(1-y_ratio)/margin);
//        }
//        setViewOffset(viewXOfs()+XOfs,viewYOfs()+YOfs,false);
//        return XOfs != 0 || YOfs != 0;
//    }
//    
//    public void paintFrameInGame(){
//                drawString("You are in Editing Mode right now. This is a test message.",viewWidth()/2,viewHeight()/2,0,false);
//                if (data.myPlayer != null){
//                        drawRect(data.myPlayer.x+data.myPlayer.getXSize()/2,data.myPlayer.y-data.myPlayer.getYSize()/13.5,data.myPlayer.getXSize()/2,10,false,true);
//                        drawRect(data.myPlayer.x+(0.5+0.5*data.myPlayer.getBlood()/data.myPlayer.getInitBlood())*data.myPlayer.getXSize()/2,data.myPlayer.y-data.myPlayer.getYSize()/13.5,(1.0*data.myPlayer.getBlood()/data.myPlayer.getInitBlood())*data.myPlayer.getXSize()/2,10,true,true);
//                        drawString("lol help!",data.myPlayer.x+data.myPlayer.getXSize()/2,data.myPlayer.y-data.myPlayer.getYSize()/3,0,true);
//                }
//                
////              drawRect(getMouseX()+viewXOfs(),getMouseY()+viewYOfs(),20,20,false,true,true);
//                
//        if(data.triggerFlag){
//                drawString("You have win the game",viewWidth()/2,viewHeight()/2+100,0,false);
//                data.triggerFlag = false;
//        }
//        if(data.triggerFlag){
//                // call the event module
//                drawString("You have triggered the event",viewWidth()/2,viewHeight()/2+100,0,false);
//                data.triggerFlag = false;
//        }
//        if(data.myMouseButton!=0 && data.myClickedID == -1){
//                        int tileX = data.myMouseX/20;
//                int tileY = data.myMouseY/20;
//                if(data.myMouseButton==3) setColor(JGColor.red);//should only be applied to the last line
//                drawRect((double)Math.min(data.myTileX,tileX)*20,(double)Math.min(data.myTileY,tileY)*20,(double)(Math.abs(data.myTileX-tileX)+1)*20,(double)(Math.abs(data.myTileY-tileY)+1)*20,false,false);
//                }
//    }
//    
//    
//    
//    public void defineLevel(){
//        setCurrentScene(1, 0);
//        data.myPlayer.resume();
//        //restore the player ? depending on playing mode
//    }
//    
//    public void initNewLife(){
//        //restore the player
//    }
//    
//    
//    
//    /**
//     * (non-Javadoc)
//     * @see jgame.platform.StdGame#doFrame()
//     * For inGame States
//     */
//    @Override
//    public void doFrame(){
//        if(!data.isEditingMode){
//                super.doFrame();
//        }
//        //else
//    }
//    
//    /**
//     * (non-Javadoc)
//     * @see jgame.platform.StdGame#paintFrame()
//     * For inGame states
//     */
//    @Override
//    public void paintFrame(){
//        if(!data.isEditingMode){
//                super.paintFrame();
//        }
//        //else
//    }
//    
//    
//    
//    public void StartTitle(){
//        setTransition(StateType.Title);
//    }
//    
//    public void StartStartGame(){
//        setTransition(StateType.StartGame);
//    }
//    
//    public void StartStartLevel(){
//        setTransition(StateType.StartLevel);
//    }
//
//    public void StartLevelDone(){
//        setTransition(StateType.LevelDone);
//    }
//    
//    public void StartLifeLost(){
//        setTransition(StateType.LifeLost);
//    }
//    
//    public void StartGameOver(){
//        setTransition(StateType.GameOver);
//    }
//    
//    
//    
////    @Override
////    public void paintFrameTitle(){
////      
////    }
////    
////    @Override
////    public void paintFrameStartGame(){
////      
////    }
////    
////    @Override
////    public void paintFrameStartLevel(){
////      
////    }
////
////    @Override
////    public void paintFrameLevelDone(){
////      
////    }
////    
////    @Override
////    public void paintFrameLifeLost(){
////      
////    }
////    
////    @Override
////    public void paintFrameGameOver(){
////      
////    }
////    
////    @Override
////    public void paintFrameEnterHighscore(){
////      
////    }
////    
////    @Override
////    public void paintFrameHighscores(){
////      
////    }
////    
////    @Override 
////    public void paintFramePaused(){
////      
////    }
//    
//    
//    
//    //unfinished
//    public void createTiles(int cid, String imgfile, int left, int top, int width, int height){
//        if (cid > 9) return;
//        defineImage(((Integer) cid).toString(),((Integer) cid).toString(),cid,imgfile,"-");
//        String temp = "";
//        for(int i=0;i<width;i++){
//                temp += cid;
//        }
//        String[] array = new String[height];
//        for(int j=0;j<height;j++){
//                array[j] = temp;
//        }
//        setTiles(left,top,array);
//        data.myCurrentScene.defineTileImage(cid, imgfile);
//        data.myCurrentScene.updateTiles(cid, left, top, width, height);
//    }
//    
//    public int getClickedID(){
//        List<GameObject> list = new ArrayList<GameObject>();
//        if (getMouseButton(1)){
//                int MouseX = getMouseX()+viewXOfs();
//                int MouseY = getMouseY()+viewYOfs();
//                if (data.myPlayer != null && !data.myPlayer.is_suspended && data.myPlayer.x < MouseX && MouseX < data.myPlayer.x + data.myPlayer.getXSize() 
//                                && data.myPlayer.y < MouseY && MouseY < data.myPlayer.y + data.myPlayer.getYSize()){
//                        list.add(data.myPlayer);
//                }
//                for(GameObject go: data.myCurrentScene.getGameObjects()){
//                        //suspended?
//                        if (!go.is_suspended && go.isAlive() && go.x < MouseX && MouseX < go.x + go.getXSize() 
//                                        && go.y < MouseY && MouseY < go.y + go.getYSize()){
//                                list.add(go);
//                        }
//                }
//        }
//        if (list.isEmpty()){
//                return -1;
//        }
//        int id = list.get(0).getID();
//        getParent().firePropertyChange("clickedID", 0, id);  
//        return id;
//    }
//    
//    public boolean drag(){
//        boolean drag = false;
//        boolean currentMouse1 = getMouseButton(1);
//        boolean currentMouse3 = getMouseButton(3);
//        int MouseX = getMouseX()+viewXOfs();
//        int MouseY = getMouseY()+viewYOfs();
//        int tileX = Math.max(MouseX/20,0);
//                int tileY = Math.max(MouseY/20,0);
//        
//        if (data.myMouseButton!=1 && currentMouse1){
//                data.myClickedID = getClickedID();
//                data.myTileX = tileX;
//                data.myTileY = tileY;
//        }
//        if (data.myMouseButton==1 && !currentMouse1){
//                if (data.myClickedID == -1){
//                        createTiles(data.myTileCid, data.myTileImgFile, Math.min(data.myTileX,tileX), Math.min(data.myTileY,tileY), Math.abs(data.myTileX-tileX)+1, Math.abs(data.myTileY-tileY)+1);
//                }
//                data.myClickedID = -1;
//        }
//        if (data.myMouseButton==1 && currentMouse1){
//                if (data.myClickedID > 0){
//                        NonPlayer actor = data.myCurrentScene.getNonPlayer(data.myClickedID);
//                        actor.x+=MouseX-data.myMouseX;
//                        actor.y+=MouseY-data.myMouseY;
//                }
//                if (data.myClickedID == 0){
//                        data.myPlayer.x+=MouseX-data.myMouseX;
//                        data.myPlayer.y+=MouseY-data.myMouseY;
//                }
//                drag = data.myClickedID > -1;
//        }
//        
//        if (data.myMouseButton!=3 && currentMouse3){
//                data.myTileX = tileX;
//                data.myTileY = tileY;
//        }
//        if (data.myMouseButton==3 && !currentMouse3){
//                createTiles(0, "null", Math.min(data.myTileX,tileX), Math.min(data.myTileY,tileY), Math.abs(data.myTileX-tileX)+1, Math.abs(data.myTileY-tileY)+1);
//        }
//        data.myMouseButton = 0;
//        if(currentMouse1) data.myMouseButton = 1;
//        if(currentMouse3) data.myMouseButton = 3;
//        data.myMouseX = MouseX;
//        data.myMouseY = MouseY;
//        return drag;
//    }
//    
//    public void setDefaultTiles(int cid, String imgfile){
//        data.myTileCid = cid;
//        data.myTileImgFile = imgfile;
//    }
//    
////    public void createTiles(){
////      boolean currentMouseClicked = getMouseButton(1);
////      
////      if (!myMouseClicked && currentMouseClicked){
////              myTileX = getMouseX()/20;
////              myTileY = getMouseY()/20;
////      }
////      if (myMouseClicked && !currentMouseClicked){
////              int tileX = getMouseX()/20;
////              int tileY = getMouseY()/20;
////              createTiles(Math.min(myTileX,tileX), Math.min(myTileY,tileY), Math.abs(myTileX-tileX)+1, Math.abs(myTileY-tileY)+1, myTileCid, myTileImgFile);
////      }
////      if (myMouseClicked && currentMouseClicked){
////              
////      }
////      myMouseClicked = currentMouseClicked;
////    }
//    
//    //unfinished
//    private void loadImage(String path){
//        if (path == null) return;
//        defineImage(path, "-", 0, path, "-");
//        System.out.println("printedFrom loadImage: ");
//    }
//    
//    //unfinished
//    private void setTransition(StateType type){
//        //setSequences(startgame_ingame, 0, leveldone_ingame, 0, lifelost_ingame, 0, gameover_ingame, 0);
//        Transition trans = data.myGame.getNonLevelScene(type);
//        String url = trans.getBackground();
//        if(url != null){
//                loadImage(url);
//                setBGImage(url);
//        }
//        //something else to do ?
//    }
//    
//    
//    public void setCurrentScene (int currentLevelID, int currentSceneID) {
//        if(data.myCurrentScene != null){
//                for(GameObject go: data.myCurrentScene.getGameObjects()){
////                      go.remove();
//                        go.suspend();
//                }
//        }
//        data.myCurrentLevelID = currentLevelID;
//        data.myCurrentSceneID = currentSceneID;
//        data.myCurrentScene = data.myGame.getScene(data.myCurrentLevelID, data.myCurrentSceneID);
//        updateCurrentScene();
//    }
//
//        /**
//         * 
//         */
//        private void updateCurrentScene() {
//                setPFSize(data.myCurrentScene.getXSize(), data.myCurrentScene.getYSize());
//        for (Entry<Integer, String> entry: data.myCurrentScene.getTileImageMap()){
//                Integer cid = entry.getKey();
//                String imgfile = entry.getValue();
//                defineImage(cid.toString(),cid.toString(),cid,imgfile,"-");
//        }
//        setTiles(0, 0, data.myCurrentScene.getTiles());
//        String url = data.myCurrentScene.getBackgroundImage();
//        loadImage(url);
//        setBGImage(url);
//        for(GameObject go: data.myCurrentScene.getGameObjects()){
////              this.markAddObject(go);
////              go.is_alive = true;
//                go.resume();
//        }
//        }
//    
//    public void setBackground(String fileName){
//        data.myCurrentScene.setBackgroundImage(fileName, false, false, data.myCurrentScene.getXSize(), data.myCurrentScene.getYSize());
//        loadImage(fileName);
//        setBGImage(fileName);
//    }
//    
//    public void setGame (Game mygame) {
//        data.myGame = mygame;
//    }
//    
//    public Game getGame(){
//        return data.myGame;
//    }
//    
//    public int getCurrentLevelID(){
//        return data.myCurrentLevelID;
//    }
//    
//    public int getCurrentSceneID(){
//        return data.myCurrentSceneID;
//    }
//    
//    public void setSceneSize(int xsize, int ysize){
//        data.myCurrentScene.resizeTiles(xsize, ysize);
//        data.myCurrentScene.setSize(xsize, ysize);
//        setPFSize(xsize, ysize);
//    }
//    
//    private void modifyImage(GameObject object, String imgfile, int xsize, int ysize){
//        loadImage(imgfile);
//        object.setImage(imgfile);//animation
//        object.setSize(xsize, ysize);
//        object.updateImageURL(imgfile);
//    }
//    
//    public void modifyActorImage(int unique_id, String imgfile, int xsize, int ysize){
//        GameObject object = data.myGame.getNonPlayer(data.myCurrentLevelID, data.myCurrentSceneID, unique_id);
//        modifyImage(object, imgfile, xsize, ysize);
//    }
//    
//    public void modifyPlayerImage(int unique_id, String imgfile, int xsize, int ysize){
//        GameObject object = data.myGame.getPlayer(unique_id);
//        modifyImage(object, imgfile, xsize, ysize);
//    }
//    
//    public Player createPlayer(int unique_id, String url, int xsize, int ysize, double xpos, double ypos, String name, int colid, int lives){
//        loadImage(url);
//        Player object = new Player(unique_id, url, xsize, ysize, xpos, ypos, name, colid, lives, 
//                        data.myGame.getCollisionManager(), data.myGame.getScoreManager(), data.myGame.getBloodManager());
//        data.myGame.setPlayer(object);
//        data.myPlayer = object;
//        object.resume_in_view = false;
//        if(!data.isEditingMode){
//                object.suspend();//not sure how things are created for playing the game
//        }
//        return object;
//    }
//    
//    public NonPlayer createActor(int unique_id, String url, int xsize, int ysize, double xpos, double ypos, String name, int colid, int lives){
//        loadImage(url);
//        NonPlayer object = new NonPlayer(unique_id, url, xsize, ysize, xpos, ypos, name, colid, lives, 
//                        data.myGame.getCollisionManager(), data.myGame.getScoreManager(), data.myGame.getBloodManager());
//        if(unique_id != SaladConstants.NULL_UNIQUE_ID){
//                data.myGame.addNonPlayer(data.myCurrentLevelID, data.myCurrentSceneID, object);
//        }
//        object.resume_in_view = false;
//        if(!data.isEditingMode){
//                object.suspend();//not sure how things are created for playing the game
//        }
//        return object;
//    }
//    
//}
