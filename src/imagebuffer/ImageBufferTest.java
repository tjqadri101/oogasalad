package imagebuffer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ImageBufferTest extends JFrame {

	private ImageBuffer myImageBuffer;
	private JPanel myPanel;
	
	public ImageBufferTest() {
		myPanel = createPanel();
		myImageBuffer = new ImageBuffer();
	}
	
	public JPanel createPanel() {
		JPanel panel = new JPanel();
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(600,800));
		setVisible(true);
		return panel;
	}
	
	public static void main (String args[]) throws IOException {
		ImageBufferTest ibt = new ImageBufferTest();
//		ibt.myImageBuffer.upload();
		ibt.myImageBuffer.resizedUpload(50, 50, "src/images/4096.png");
	}
}
