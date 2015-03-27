

package ip_project.main;

import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 *
 */
public abstract class MIContainer extends HBox{
	
	public MIContainer(){
		VBox bar = new VBox();
		bar.setPrefWidth(500);
		bar.setBorder(new Border(new BorderStroke(Color.BLACK,  BorderStrokeStyle.SOLID,  CornerRadii.EMPTY, new BorderWidths(1))));
		
		Pane canvas = new Pane();
		canvas.setBorder(new Border(new BorderStroke(Color.RED,  BorderStrokeStyle.SOLID,  CornerRadii.EMPTY, new BorderWidths(1))));
		
		HBox.setHgrow(canvas, Priority.ALWAYS);
		
		this.getChildren().addAll(canvas, bar);
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