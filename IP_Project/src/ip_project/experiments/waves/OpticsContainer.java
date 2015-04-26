package ip_project.experiments.waves;

import ip_project.main.MIContainer;
import ip_project.main.Resources;

/**
 * Optics Lens Experiment
 */
public class OpticsContainer extends MIContainer implements Resources{
	
	public OpticsContainer(){
		super();
	}
	
	@Override
	public void updateValues(){	
	}
	
	
	public String getTitle(){
		return OPTIC_TITLE;
	}
	
	public String getHelp(){
		return OPTIC_HELP;
	}
}
