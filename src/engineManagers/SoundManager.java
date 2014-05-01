package engineManagers;


import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import util.GeneralInputBox;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SoundManager {
    
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
        private File soundFile;
        private AudioInputStream myAudioInputStream;
        private AudioFormat myAudioFormat;
        private SourceDataLine mySourceDataLine;
        private Thread myThread;
        private Map<String, String> mySoundMap;
        
        private int BUFFER_SIZE = 12800000;

        /**
         * @param filename String of the file path to the music file
         */
        public SoundManager() {
                setMap();
        }
        
        /**
         * 
         * @param filename String of the file path to the music file
         */
        public SoundManager(String filename) {
                setSound(filename);
                setMap();
        }
        
       /**
         * 
         * @param url URL of the file path to the music file
         */
        public SoundManager(URL url) {
                String fileURL = url.toString().substring(5);
                setSound(fileURL.replaceAll("%20", " "));
                setMap();
        }
        
        public void setMap(){
            mySoundMap = new HashMap<String,String>();
            mySoundMap.put(CREEPY, SRC_ENGINE_SOUNDS_PLAY_CREEPY_WAV);
            mySoundMap.put(HAPPY, SRC_ENGINE_SOUNDS_PLAY_JOYFUL_WAV);
            mySoundMap.put(OHYEAH, SRC_ENGINE_SOUNDS_CLIP_OHYEAH_WAV);
            mySoundMap.put(GAMEOVER, SRC_ENGINE_SOUNDS_GAME_OVER_WAV);
            mySoundMap.put(SUCCEED, SRC_ENGINE_SOUNDS_CLIP_SUCCEED_WAV);
        }
        
        /**
         * 
         * @param filename String of the file path to the music file
         */
        public void playClip(String filename){
            stop();
            setSound(filename);
            start();
        }
        
        /**
         * @param ask user for what music to put in
         */
        public void chooseInitSound(){        
            setSound(mySoundMap.get(GeneralInputBox.showMessageBox()));
        }

        /**
         * 
         * @param filename Reset file path of the music file
         */

        public void setSound(String filename) {
                try {
                        myThread = getLoopingThread();
                        soundFile = new File(filename);
                }
                catch (Exception e) {
                        e.printStackTrace();
                        System.exit(1);
                }
        }

        /**
         * Starts sound
         */
        
        public void start() {
                myThread.start();
        }

        /**
         * Stops sound
         */
        
        @SuppressWarnings("deprecation")
        public void stop() {
                myThread.stop();
        }

        private Thread getLoopingThread() {
                return new Thread() {
                        @Override
                        public void run() {
                                while(true)
                                        playSound();
                        }
                };
        }

        private void playSound () {
                try {
                        myAudioInputStream = AudioSystem.getAudioInputStream(soundFile);
                }
                catch (Exception e) {
                        e.printStackTrace();
                        System.exit(1);
                }

                myAudioFormat = myAudioInputStream.getFormat();

                DataLine.Info info = new DataLine.Info(SourceDataLine.class, myAudioFormat);
                try {
                        mySourceDataLine = (SourceDataLine) AudioSystem.getLine(info);
                        mySourceDataLine.open(myAudioFormat);
                }
                catch (LineUnavailableException e) {
                        e.printStackTrace();
                        System.exit(1);
                }
                catch (Exception e) {
                        e.printStackTrace();
                        System.exit(1);
                }

                mySourceDataLine.start();

                int nBytesRead = 0;
                byte[] abData = new byte[BUFFER_SIZE];
                while (nBytesRead != -1) {
                        try {
                                nBytesRead = myAudioInputStream.read(abData, 0, abData.length);
                        }
                        catch (IOException e) {
                                e.printStackTrace();
                        }
                        if (nBytesRead >= 0) {
                                @SuppressWarnings("unused")
                                int nBytesWritten = mySourceDataLine.write(abData, 0, nBytesRead);
                        }
                }

                mySourceDataLine.drain();
                mySourceDataLine.close();
        }
        
        
        /**
         * allows addition from exterior 
         */
        public void addSoundClip(String key, String fileName){
            mySoundMap.put(key, fileName);
        }

        public List<String> getAttributes() {
            return null;
        }

}
