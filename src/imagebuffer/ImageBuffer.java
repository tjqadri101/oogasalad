package imagebuffer;

import game_authoring_environment.ViewFactory;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
 * This class is meant to allow users to upload photos into the main package so that they
 * do not have to be reloaded again. This also makes it easier to aggregate photos.
 */
public class ImageBuffer {

	private static final String FINAL_DESTINATION = " ";
	private BufferedImage bufferedImage;
	private File chosenFile;
	private JComponent container;
	
	public ImageBuffer(JComponent component) {
		container = component;
	}
	
	/*
	 * Allows for image choosing, physical image creation, and image storing into the proper file
	 */
	public void loadAndSave() throws IOException {
		chooseImage();
		createImage();
		writeImage();
	}
	
	/*
	 * Create a dialog box to ask the user for a file URL
	 */
	public void chooseImage() {
		FileNameExtensionFilter filter = new FileNameExtensionFilter("jpg", "png", "gif", "jpeg");
		JFileChooser chooser = ViewFactory.createJFileChooser();
		chooser.setApproveButtonText("Open");
		chooser.setFileFilter(filter);
		int actionDialog = chooser.showOpenDialog(container);
		if (actionDialog != JFileChooser.APPROVE_OPTION) {
			return;
		}
		chosenFile = chooser.getSelectedFile();
	}
	
	/*
	 * Turn the image URl into an actual BufferedImage
	 */
	public void createImage( ) {
		
		try {
			BufferedImage in = ImageIO.read(chosenFile);
			bufferedImage = new BufferedImage(in.getWidth(), in.getHeight(), BufferedImage.TYPE_INT_ARGB);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Write the BufferedImage into the correct folder.
	 */
	public void writeImage() throws IOException {
		JFileChooser chooser = ViewFactory.createJFileChooser();
		chooser.setApproveButtonText("Save");
		int actionDialog = chooser.showOpenDialog(container);
		if (actionDialog != JFileChooser.APPROVE_OPTION) {
			return;
		}
		File outputFile = chooser.getSelectedFile();
		ImageIO.write(bufferedImage, "jpg", outputFile);
	}
	
}
