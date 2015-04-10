package ip_project.experiments.waves;

import javafx.scene.control.Label;
import ip_project.main.MIContainer;

/**
 * The Radio Activity Experiment
 */
public class RadioActContainer extends MIContainer {
	
	public RadioActContainer(){
		super();
		
		this.getChildren().add(new Label("Radioactive Decay"));
	}
}
