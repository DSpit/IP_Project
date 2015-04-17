package ip_project.experiments.mechanics;

//import ip_project.experiments.Test.TestInterpolator;
import javafx.scene.paint.Color;
import ip_project.main.MIContainer;
import ip_project.main.Resources;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

/**
 * Newton's Second Law Experiment
 */
public class NewtonContainer extends MIContainer implements Resources{
	
	
	public String getTitle(){
		return NEWTON_TITLE;
	}
	
	//private ObservableList<XYChart.Series> XYlist;
	XYChart.Series<Number, Number> series;
	LineChart<Number, Number> mGraph1;
	Slider mSlider1, mSlider2;
	Animation gAnim;
	double mForce, mMass, mAcceleration;
	
	public NewtonContainer(){
		
		
		//example of slider set up
				mSlider1 = new Slider(0, 10, 5);
				mSlider1.setShowTickMarks(true);
				mSlider1.setShowTickLabels(true);
				mSlider1.setMajorTickUnit(1);
				mSlider1.setMinorTickCount(0);
				mSlider1.setSnapToTicks(true);
				mSlider1.setId("Slider 1");
				this.addInputs(mSlider1);
				
				
				
				mSlider2 = new Slider(0, 10, 5);
				mSlider2.setShowTickMarks(true);
				mSlider2.setShowTickLabels(true);
				mSlider2.setMajorTickUnit(1);
				mSlider2.setMinorTickCount(0);
				mSlider2.setSnapToTicks(true);
				mSlider2.setId("Slider 2");
				this.addInputs(mSlider2);
				
				
	
				//example of graph set up
				NumberAxis xAxis = new NumberAxis();
				NumberAxis yAxis = new NumberAxis();
				mGraph1 = new LineChart<Number, Number>(xAxis, yAxis);
				this.addGraphs(mGraph1);
				
				//specify object- look and location
				Circle object1 = new Circle(10);
				object1.setFill(Color.RED);
				object1.setTranslateX(10);
				object1.setTranslateY(200);
				
				TranslateTransition anim1 = new TranslateTransition(Duration.seconds(5), object1);
				anim1.setInterpolator(new NewtonInterpolator());		
				anim1.setFromX(10);
				anim1.setCycleCount(1);
				anim1.setToX(500);
		        
				TranslateTransition anim2 = new TranslateTransition(Duration.seconds(5), object1);
				anim2.setInterpolator(Interpolator.LINEAR);		//this is where you put in the custom interpolator
				anim2.setFromY(200);
				anim2.setCycleCount(1);
				anim2.setToY(200);
		        
				//set up the animation
				ParallelTransition comboAnim = new ParallelTransition();
				comboAnim.getChildren().addAll(anim1, anim2);

				
				this.addAnimations(comboAnim);
				this.addAnimationElements(object1);
				
				mGraph1.setAnimated(true);
				
				
				
				
			}
			
			private double calculateAcceleration(){
				return (mSlider1.getValue()/mSlider2.getValue());	
			}
			
			@Override
			public double calculate(double value, double time){
				return value * Math.pow(time, 2);
			}
			
			private class NewtonInterpolator extends Interpolator{

				@Override
				protected double curve(double t) {
					
					double value = calculate( calculateAcceleration(), t);
					
					//get top level series
					XYChart.Series<Number, Number> series = mGraph1.getData().get(mGraph1.getData().size() - 1);
					
					if(value<1)
					{
						series.getData().add(new Data<Number, Number>(t, value));
						return value;
					}
					else 
						series.getData().add(new Data<Number, Number>(t, 1));
						return 1;
				}
				
			}
}
