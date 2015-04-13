package ip_project.experiments;

import ip_project.main.MIContainer;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Slider;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Test extends MIContainer{
	
	public Test(){
		//example of slider set up
		Slider slider1 = new Slider(0, 10, 0);
		slider1.setShowTickMarks(true);
		slider1.setShowTickLabels(true);
		slider1.setMajorTickUnit(1);
		slider1.setMinorTickCount(0);
		slider1.setSnapToTicks(true);
		slider1.setId("Slider 1");
		this.addInputs(slider1);
		
		//example of graph set up
		NumberAxis xAxis = new NumberAxis();
		NumberAxis yAxis = new NumberAxis();
		LineChart<Number, Number> graph1 = new LineChart<Number, Number>(xAxis, yAxis);
		this.addGraphs(graph1);
		
		Circle object1 = new Circle(10);
		TranslateTransition anim1 = new TranslateTransition(Duration.INDEFINITE, object1);
		anim1.setInterpolator(new TestInterpolator());
		anim1.setFromX(0);
		anim1.setToX(100);
		
		this.addAnimations(anim1);
		this.addAnimationElements(object1);
	}
	
	@Override
	public String getTitle(){
		return "Test";
	}
	
	private class TestInterpolator extends Interpolator{

		@Override
		protected double curve(double t) {
			return t;
		}
		
	}

}
