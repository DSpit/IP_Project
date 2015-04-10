package ip_project.experiments.waves;

import javafx.scene.control.Label;
import ip_project.main.MIContainer;

/**
 * Optics Lens Experiment
 */
public class OpticsContainer extends MIContainer {
	
	public OpticsContainer(){
		super();
		
		this.getChildren().add(new Label("Optics"));
	}
}
