package genericListener;

import java.awt.event.MouseListener;

public class GenericMouseListener {
    
    
    
    

    private MouseListener initializeMouseListener () {
        // TODO Auto-generated method stub
        MouseListener mouseListener = new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                        if (e.getClickCount() == 1) {
                                int index = list.locationToIndex(e.getPoint());
                                lastCommand = listModel.getElementAt(index).toString();
                                updateBackEndandDraw(lastCommand);
                                System.out.println("Double clicked on Item " + index);
                        }
                        if (e.getClickCount() == 2) {
                            int index = list.locationToIndex(e.getPoint());
                            lastCommand = listModel.getElementAt(index).toString();
                            updateBackEndandDraw(lastCommand);
                            System.out.println("Double clicked on Item " + index);
                        }
                }
        };
        return mouseListener;
    
}
