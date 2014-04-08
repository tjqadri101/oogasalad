package play;
import generic_feature_gui.*;
import java.util.*;


/**
 * 
 * @author Robert Duvall
 * @author David Chou
 */
// To use this generic GUI, just create a new class that extends Feature for each feature you want to implement (see the comments
// in the Feature and included example HelpPageFeature for more details), then add all of them before opening the view as seen
// below.

public class MainPlayer {
	public static void main(String[] args){
		Set myFeatures = new HashSet<Feature>();
		// add features here! HelpPageFeature included as an example.
		myFeatures.add(new EngineFeature());
		PlayView view = new PlayView(myFeatures);
	}
}