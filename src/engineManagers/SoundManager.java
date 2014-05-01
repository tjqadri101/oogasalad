package engineManagers;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.media.j3d.Sound;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.SourceDataLine;
import objects.GameObject;
import util.GeneralInputBox;
import util.Music;

public class SoundManager extends Music {
    private static final String SRC_ENGINE_SOUNDS_CLIP_SUCCEED_WAV = "src/engine/Sounds/ClipSucceed.wav";
    private static final String SRC_ENGINE_SOUNDS_GAME_OVER_WAV = "src/engine/Sounds/GameOver.wav";
    private static final String SRC_ENGINE_SOUNDS_CLIP_OHYEAH_WAV = "src/engine/Sounds/ClipOhyeah.wav";
    private static final String SUCCEED = "Succeed";
    private static final String GAMEOVER = "Gameover";
    private static final String OHYEAH = "Ohyeah";
    private static final String SRC_ENGINE_SOUNDS_PLAY_JOYFUL_WAV = "src/engine/Sounds/PlayJoyful.wav";
    private static final String SRC_ENGINE_SOUNDS_PLAY_CREEPY_WAV = "src/engine/Sounds/PlayCreepy.wav";
    private static final String HAPPY = "Happy";
    private static final String CREEPY = "Creepy";
    private String myBackgroud;
    private File soundFile;
    private AudioInputStream myAudioInputStream;
    private AudioFormat myAudioFormat;
    private SourceDataLine mySourceDataLine;
    private Thread myThread;
    private Map<String,String> mySoundMap;
    
    
    private int BUFFER_SIZE = 12800000;
    
    public SoundManager(){
        mySoundMap = new HashMap<String,String>();
        mySoundMap.put(CREEPY, SRC_ENGINE_SOUNDS_PLAY_CREEPY_WAV);
        mySoundMap.put(HAPPY, SRC_ENGINE_SOUNDS_PLAY_JOYFUL_WAV);
        mySoundMap.put(OHYEAH, SRC_ENGINE_SOUNDS_CLIP_OHYEAH_WAV);
        mySoundMap.put(GAMEOVER, SRC_ENGINE_SOUNDS_GAME_OVER_WAV);
        mySoundMap.put(SUCCEED, SRC_ENGINE_SOUNDS_CLIP_SUCCEED_WAV);
        chooseInitSound();
    }
    
    /**
     * @param filename String of the file path to the music file
     */
    public SoundManager(String filename) {
            super(filename);
    }

    /**
     * @param ask user for what music to put in
     */
    public void chooseInitSound(){
        System.out.print("Pick the game mode: Creepy or Happy??? ");
        String input = System.console().readLine();
        myBackgroud = mySoundMap.get(GeneralInputBox.showMessageBox());
    }
    
    public void addSoundClip(String key, String fileName){
        mySoundMap.put(key, fileName);
    }

    public List<String> getAttributes() {
        return null;
        
        
    }
    
    

}
