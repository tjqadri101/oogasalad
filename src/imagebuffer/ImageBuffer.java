package imagebuffer;

import game_authoring_environment.ViewFactory;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
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

	private static final String FOLDER_PATH = "src/images/";
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
	public void loadAndSave() throws IOException {
		chooseImage();
		copyFile();
	}

	/*
	 * Create a dialog box to ask the user for a file URL
	 */
	public void chooseImage() {
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
		finalPath = FOLDER_PATH + fileName;
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

}
