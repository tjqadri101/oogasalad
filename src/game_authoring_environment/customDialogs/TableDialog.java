package game_authoring_environment.customDialogs;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class TableDialog extends PanelDialog{

	private JTextField textArea;

	public TableDialog(JFrame frame){
		super(frame);
	}

	@Override
	protected JComponent initializePanel() {
		textArea = new JTextField();
		String btnString1 = "Enter";
		String btnString2 = "Cancel";

		//Create an array of the text and components to be displayed.
		String msgString1 = "What was Dr. SEUSS's real last name?";
		String msgString2 = "(The answer is";
		Object[] array = {msgString1, msgString2, textArea};
		//Create an array specifying the number of dialog buttons
		//and their text.
		Object[] options = {btnString1, btnString2, "kat"};
		JOptionPane optionPane = new JOptionPane(array,
				JOptionPane.QUESTION_MESSAGE,
				JOptionPane.YES_NO_OPTION,
				null,
				options,
				options[0]);
		
		return optionPane;
		
	}

}
