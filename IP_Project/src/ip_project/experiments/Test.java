package ip_project.experiments;

import ip_project.main.MIContainer;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Slider;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Test extends MIContainer{
	
	LineChart<Number, Number> mGraph1;
	Slider mSlider1;
	
	public Test(){
		//example of slider set up
		mSlider1 = new Slider(0, 10, 0);
		mSlider1.setShowTickMarks(true);
		mSlider1.setShowTickLabels(true);
		mSlider1.setMajorTickUnit(1);
		mSlider1.setMinorTickCount(0);
		mSlider1.setSnapToTicks(true);
		mSlider1.setId("Slider 1");
		this.addInputs(mSlider1);
		
		//example of graph set up
		NumberAxis xAxis = new NumberAxis();
		NumberAxis yAxis = new NumberAxis();
		mGraph1 = new LineChart<Number, Number>(xAxis, yAxis);
		this.addGraphs(mGraph1);
		
		//specify object- look and location
		Circle object1 = new Circle(10);
		object1.setTranslateX(10);
		object1.setTranslateY(10);
		
		TranslateTransition anim1 = new TranslateTransition(Duration.seconds(5), object1);
		anim1.setInterpolator(Interpolator.LINEAR);		//this is where you put in the custom interpolator
		anim1.setFromX(10);
		anim1.setCycleCount(1);
		anim1.setToX(300);
        
		TranslateTransition anim2 = new TranslateTransition(Duration.seconds(5), object1);
		anim2.setInterpolator(new TestInterpolator());		//this is where you put in the custom interpolator
		anim2.setFromY(10);
		anim2.setCycleCount(1);
		anim2.setToY(600);
        
		//set up the animation
		ParallelTransition comboAnim = new ParallelTransition();
		comboAnim.getChildren().addAll(anim1, anim2);

		
		this.addAnimations(comboAnim);
		this.addAnimationElements(object1);
	}
	
	@Override
	public String getTitle(){
		return "Test";
	}
	
	//@Override
	public double calculate(double dynamicValue, double time){
		return  dynamicValue * Math.pow(time, 0.5);
	}
	
	private class TestInterpolator extends Interpolator{

		@Override
		protected double curve(double t) {
			//calculations
			double value = calculate(mSlider1.getValue(), t);
			//get top level series
			XYChart.Series<Number, Number> series = mGraph1.getData().get(mGraph1.getData().size() - 1);
			series.getData().add(new Data<Number, Number>(t, value));
			
			return value;
		}
		
	}

}
