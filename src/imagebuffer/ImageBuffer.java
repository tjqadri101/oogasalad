package imagebuffer;

import engine.GameEngine;
import game_authoring_environment.ViewFactory;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.FileChannel;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
*
* @author David Chou
*
*/

/*
* This class is meant to allow users to upload photos into the main package so
* that they do not have to be reloaded again. This also makes it easier to
* aggregate photos.
*/
public class ImageBuffer {

private static final String DEFAULT_FOLDER_PATH = "src/engine/"+GameEngine.BUFFER_IMAGE_FOLDER;
private String finalPath;
private File chosenFile;
private String fileName;
private String source;
private JPanel container;

public ImageBuffer( ) {

}

/*
* Allows for image choosing, physical image creation, and image storing
* into the proper file
*/
public void upload(String source) throws IOException {
makeFile(source);
setPath();
if (chosenFile != null) {
copyFile();
}
}

/*
* Same as the above method, except it allows for resizing of the image
*/
public void resizedUpload(int x, int y, String source) throws IOException {
makeFile(source);
setPath();
if (chosenFile != null) {
copyFile();
resizeImage(x, y);
}
}

public void resizedUpload(int x, int y, String source, String destination) throws IOException {
makeFile(source);
setPath(destination);
if (chosenFile != null) {
copyFile();
resizeImage(x, y);
}
}

/*
* Create the file from the String URL given from the user
*/
public void makeFile(String source) throws MalformedURLException {
File f = new File (source);
chosenFile = f;
fileName = chosenFile.getName();
}

/*
* Sets the destination for the copied file
*/
public void setPath() {
finalPath = DEFAULT_FOLDER_PATH + fileName;
System.out.println(finalPath);
}

/*
* Allows the user to choose a new place to copy the file
*/
public void setPath(String finalDestination) {
finalPath = finalDestination + fileName;
}

/*
* This method will transfer the chosenFile to the final destination
*/
public void copyFile( ) throws IOException {
if (fileExists()) {
return;
}
FileInputStream fis = new FileInputStream(chosenFile);
FileOutputStream fos = new FileOutputStream(finalPath);
FileChannel srcChannel = fis.getChannel();
FileChannel destChannel = fos.getChannel();
srcChannel.transferTo(0, chosenFile.length(), destChannel);
srcChannel.close();
destChannel.close();
fis.close();
fos.close();
}

public boolean fileExists() {
File f = new File(finalPath);
boolean preexistingFile = (f.exists() || ! f.exists() ) ? true : false;
return preexistingFile;
}

public String getFinalPath() {
return finalPath;
}

/*
* Allows the image to be resized according to user specifications
*/
public void resizeImage(int x, int y) throws IOException {
//read file
File output = new File (finalPath);
BufferedImage img = ImageIO.read(output);

//resize the image
int w = img.getWidth();
int h = img.getHeight();
BufferedImage resized = new BufferedImage(x, y, img.getType());
Graphics2D g = resized.createGraphics();
g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
RenderingHints.VALUE_INTERPOLATION_BILINEAR);
g.drawImage(img, 0, 0, x, y, 0, 0, w, h, null);
g.dispose();

//write image to file
ImageIO.write(resized, "jpg", output);
}
}