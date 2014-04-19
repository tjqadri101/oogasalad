package imagebuffer;

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
import java.nio.channels.FileChannel;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
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

	private static final String DEFAULT_FOLDER_PATH = "src/images/";
	private String finalPath;
	private File chosenFile;
	private JComponent container;
	private String fileName;

	public ImageBuffer(JComponent component) {
		container = component;
	}

	/*
	 * Allows for image choosing, physical image creation, and image storing
	 * into the proper file
	 */
	public void upload() throws IOException {
		setPath();
		if (chosenFile != null) copyFile();
	}

	public void resizedUpload(int x, int y) throws IOException {
		setPath();
		if (chosenFile != null) {
			copyFile();
			resizeImage(x, y);
		}
	}
	/*
	 * Create a dialog box to ask the user for a file URL
	 */
	public void setPath() {
		chooseImage();
		finalPath = DEFAULT_FOLDER_PATH + fileName;
	}
	
	public void setPath(String finalDestination) {
		chooseImage();
		finalPath = finalDestination + fileName;
	}

	private void chooseImage() {
		FileNameExtensionFilter filter = new FileNameExtensionFilter("jpg",
				"png", "gif", "jpeg");
		JFileChooser chooser = ViewFactory.createJFileChooser();
		chooser.setApproveButtonText("Open");
		chooser.setFileFilter(filter);
		int actionDialog = chooser.showOpenDialog(container);
		if (actionDialog != JFileChooser.APPROVE_OPTION) {
			return;
		}
		chosenFile = chooser.getSelectedFile();
		fileName = chosenFile.getName();
	}
	
	public void copyFile( ) throws IOException {
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
	
	public String getFinalPath() {
		return finalPath;
	}

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
