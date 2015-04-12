package ip_project.experiments.mechanics;

import ip_project.main.MIContainer;
import ip_project.main.Resources;
import javafx.scene.control.Label;

/**
 * Newton's Second Law Experiment
 */
public class NewtonContainer extends MIContainer implements Resources{

	public NewtonContainer(){
		super();
	}
	
	public String getTitle(){
		return NEWTON_TITLE;
	}
}
