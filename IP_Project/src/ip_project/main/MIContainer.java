

package ip_project.main;

import javafx.geometry.Pos;
import javafx.scene.chart.Chart;
import javafx.scene.control.Slider;
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
	
	private VBox mGContainer = new VBox();
	private HBox mIContainer = new HBox();
	
	public MIContainer(){
		
		//create graph and input bar
		VBox bar = new VBox();
		bar.setPrefWidth(500);
		
		//create graph container
		mGContainer.setBorder(new Border(new BorderStroke(Color.BROWN,  BorderStrokeStyle.SOLID,  CornerRadii.EMPTY, new BorderWidths(2))));
		mGContainer.setAlignment(Pos.CENTER);
		VBox.setVgrow(mGContainer, Priority.ALWAYS);
		
		//create input container
		mIContainer.setPrefHeight(150);
		mIContainer.setAlignment(Pos.CENTER);
		mIContainer.setBorder(new Border(new BorderStroke(Color.GREEN,  BorderStrokeStyle.SOLID,  CornerRadii.EMPTY, new BorderWidths(2))));
		
		//add elements to bar
		bar.getChildren().addAll(mGContainer, mIContainer);
		
		//create animation pane
		Pane canvas = new Pane();
		canvas.setBorder(new Border(new BorderStroke(Color.RED,  BorderStrokeStyle.SOLID,  CornerRadii.EMPTY, new BorderWidths(1))));	
		HBox.setHgrow(canvas, Priority.ALWAYS);
		
		//add elements to the container
		this.getChildren().addAll(canvas, bar);
	}
	
	public void addInputs(Slider... inputs){
			mIContainer.getChildren().addAll(inputs);
	}
	
	public void addGraphs(Chart... graphs){
		mGContainer.getChildren().addAll(graphs);
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