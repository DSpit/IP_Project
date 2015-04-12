

package ip_project.experiments.mechanics;

import ip_project.main.MIContainer;
import ip_project.main.Resources;
import javafx.scene.control.Label;

/**
 * Projectile Motion Experiment
 */
public class ProjContainer extends MIContainer implements Resources{

	public ProjContainer(){
		super();
	}
	
	public String getTitle(){
		return PROJ_TITLE;
	}
}
