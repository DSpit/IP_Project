package ip_project.experiments.waves;

import ip_project.main.MIContainer;
import ip_project.main.Resources;

/**
 * The Radio Activity Experiment
 */
public class RadioActContainer extends MIContainer implements Resources{
	
	public RadioActContainer(){
		super();
	}
	
	
	@Override
	public void updateValues(){
	}
	
	
	public String getTitle(){
		return RAD_ACT_TITLE;
	}
	
	
	public String getHelp(){
		return RAD_ACT_HELP;
	}
}
