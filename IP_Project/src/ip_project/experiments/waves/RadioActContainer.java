package ip_project.experiments.waves;

import ip_project.main.MIContainer;
import ip_project.main.Resources;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * The Radio Activity Experiment
 */
public class RadioActContainer extends MIContainer implements Resources{
	
	public static final int NUMBER_OF_PARTICLES = 100;
	
	Slider mHalfLifeSlider;
	LineChart<Number, Number> mGraph;
	
	public RadioActContainer(){
		super();
		
		mHalfLifeSlider = new Slider(1, 10, 5);
		mHalfLifeSlider.setShowTickMarks(true);
		mHalfLifeSlider.setShowTickLabels(true);
		mHalfLifeSlider.setMajorTickUnit(1);
		mHalfLifeSlider.setMinorTickCount(0);
		mHalfLifeSlider.setId("Half Life");
		
		this.addInputs(mHalfLifeSlider);
		
		
		//Graph set up
		String xAxisLabel = "Time";
		String yAxisLabel = "Number of Protons";
		
		NumberAxis xAxis = new NumberAxis(xAxisLabel, 0, 1, 0.1);
		NumberAxis yAxis = new NumberAxis(yAxisLabel, 0, 50, 5);
		
		xAxis.setAutoRanging(false);
		yAxis.setAutoRanging(false);
		
		mGraph = new LineChart<Number, Number>(xAxis, yAxis);
		
		this.addGraphs(mGraph);
		
		//set up animations
		Pane  holder = new Pane();
		for(int i = 0; i < NUMBER_OF_PARTICLES; i++){
			Circle circle = new Circle(5, (i%2 == 0)?Color.BLUE : Color.RED);
			circle.setCenterX(value);
			circle.setCenterY();			
			holder.getChildren().add(circle);
		}
		
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
