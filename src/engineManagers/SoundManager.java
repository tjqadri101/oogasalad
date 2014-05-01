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
import util.Music;

public class SoundManager extends Music {
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
    
    /**
     * 
     * @param filename String of the file path to the music file
     */
    public SoundManager(String filename) {
            super(filename);
            mySoundMap = new HashMap<String,String>();
            mySoundMap.put(CREEPY, SRC_ENGINE_SOUNDS_PLAY_CREEPY_WAV);
            mySoundMap.put(HAPPY, SRC_ENGINE_SOUNDS_PLAY_JOYFUL_WAV);
    }
    
    public void chooseInitSound(){
        System.out.print("Pick the game mode: Creepy or Happy??? ");
        String input = System.console().readLine();
        myBackgroud = mySoundMap.get(input);
    }
    


    public List<String> getAttributes() {
        return null;
        
        
    }
    
    

}
