

package ip_project.main;

import javafx.scene.layout.Pane;

/**
 *
 */
public abstract class MIContainer extends Pane{
	
	public MIContainer(){
		//TODO not sure what but I'll bet there's something
	}
	
	public void help(){
		//TODO add dialog box (whatever predefined one there is in javafx) and output a help message (writen in Res)
	}
	
	abstract public void start();
	
	abstract public void done();
	
	abstract public void unpause();	//Note: Could not use "Continue" because clash with reserved syntax
	
	abstract public void pause();
	
	abstract public void restart();

}