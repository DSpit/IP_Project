package ip_project.experiments.calculus;

import ip_project.main.MIContainer;
import ip_project.main.Resources;
import javafx.scene.control.Label;

/**
 * New Sports Bike Calculus Experiment
 */
public class NewSportsBikeContainer extends MIContainer implements Resources{
	
	public NewSportsBikeContainer(){
		super();
	}
	
	@Override
	public void updateValues(){
	}
	
	

	public String getTitle(){
		return BIKE_TITLE;
	}
	
	public String getHelp(){
		return BIKE_HELP;
	}
}
