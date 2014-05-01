package engineManagers;

import java.io.File;
import java.util.List;
import javax.media.j3d.Sound;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.SourceDataLine;
import objects.GameObject;
import util.Music;

public class SoundManager extends Music {
    private File soundFile;
    private AudioInputStream myAudioInputStream;
    private AudioFormat myAudioFormat;
    private SourceDataLine mySourceDataLine;
    private Thread myThread;
    
    private int BUFFER_SIZE = 12800000;
    
    /**
     * 
     * @param filename String of the file path to the music file
     */
    public SoundManager(String filename) {
            super(filename);
    }
    
    public void chooseSound(){
        
    }
    


    public List<String> getAttributes () {
        
        
    }
    
    

}
