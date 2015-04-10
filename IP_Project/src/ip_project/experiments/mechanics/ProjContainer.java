

package ip_project.experiments.mechanics;

import ip_project.main.MIContainer;
import javafx.scene.control.Label;

/**
 * Projectile Motion Experiment
 */
public class ProjContainer extends MIContainer{

	public ProjContainer(){
		super();
		
		this.getChildren().add(new Label("Projectile"));
	}
}
