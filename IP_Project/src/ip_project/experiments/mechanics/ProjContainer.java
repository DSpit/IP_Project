

package ip_project.experiments.mechanics;

import ip_project.main.MIContainer;
import ip_project.main.Resources;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

/**
 * Projectile Motion Experiment
 */
public class ProjContainer extends MIContainer implements Resources{

	XYChart.Series<Number, Number> series1, series2;
	LineChart<Number, Number> mGraph1, mGraph2;
	Slider mSlider1, mSlider2;
	TranslateTransition anim1, anim2;

	
	
	
	public ProjContainer(){
		
		//example of slider set up
		mSlider1 = new Slider(1, 5, 5);
		mSlider1.setShowTickMarks(true);
		mSlider1.setShowTickLabels(true);
		mSlider1.setMajorTickUnit(1);
		mSlider1.setMinorTickCount(0);
		mSlider1.setSnapToTicks(true);
		mSlider1.setId("Slider 1");
		this.addInputs(mSlider1);
		
	
		mSlider2 = new Slider(0, 90, 45);
		mSlider2.setShowTickMarks(true);
		mSlider2.setShowTickLabels(true);
		mSlider2.setMajorTickUnit(1);
		mSlider2.setMinorTickCount(0);
		mSlider2.setSnapToTicks(true);
		mSlider2.setId("Slider 2");
		this.addInputs(mSlider2);
		
		String Xstr1 = "Time";
		String Ystr1 = "X Position";

		
		String Xstr2 = "Time";
		String Ystr2 = "Y Position";				
		
			
		
		//example of graph set up
		NumberAxis xAxis1 = new NumberAxis(Xstr1, 0, 1, 0.1);
		NumberAxis yAxis1 =new NumberAxis(Ystr1,-5,	5, 1);
		         
		NumberAxis xAxis2 = new NumberAxis(Xstr2, 0, 1, 0.1);	
		NumberAxis yAxis2 =new NumberAxis(Ystr2, -1, 1, 1);
		
		
		yAxis1.setAutoRanging(false);
		xAxis1.setAutoRanging(false);
		yAxis2.setAutoRanging(false);
		xAxis2.setAutoRanging(false);

		mGraph1 = new LineChart<Number, Number>(xAxis1, yAxis1);
		mGraph2 = new LineChart<Number, Number>(xAxis2, yAxis2);
		
		this.addGraphs(mGraph1);
		this.addGraphs(mGraph2);
		
		
		
		//specify object- look and location
		Circle object1 = new Circle(10);
		object1.setFill(Color.BLUE);
		object1.setTranslateX(10);
		object1.setTranslateY(700);
		
		anim1 = new TranslateTransition(Duration.seconds(5), object1);
		anim1.setInterpolator(new ProjXInterpolator());		
		anim1.setFromX(10);
		anim1.setCycleCount(1);
		anim1.setToX(500);
		
		
		anim2 = new TranslateTransition(Duration.seconds(5), object1);
		anim2.setInterpolator(new ProjYInterpolator());		//this is where you put in the custom interpolator
		anim2.setFromY(this.mCanvas.getHeight()/2); //useless line, values is updated before playing animation
		anim2.setCycleCount(1);
		anim2.setToY(700);
        
		//set up the animation
		ParallelTransition comboAnim = new ParallelTransition();
		comboAnim.getChildren().addAll(anim1, anim2);

		
		this.addAnimations(comboAnim);
		this.addAnimationElements(object1);
		
		this.getStyleClass().add("newton-canvas");

		//mGraph1.setAnimated(false);
		
	
	}

	
	private class ProjYInterpolator extends Interpolator{

		@Override
		protected double curve(double t) {
			
			double value = calculateYPosition(mSlider1.getValue() ,
					mSlider2.getValue(), t);
			
			//get top level series
		//	XYChart.Series<Number, Number> series1 = mGraph1.getData().get(mGraph1.getData().size() - 1);
			XYChart.Series<Number, Number> series2 = mGraph2.getData().get(mGraph2.getData().size() - 1);
	
				series2.getData().add(new Data<Number, Number>(t, value));
			//	XYChart.Series<Number, Number> series2 = mGraph2.getData().get(mGraph2.getData().size() - 1);

				return value;
		}	
	}
	

	private class ProjXInterpolator extends Interpolator{

		@Override
		protected double curve(double t) {
			
			double value = calculateXPosition(mSlider1.getValue() ,
					mSlider2.getValue(), t);
			
			//get top level series
			XYChart.Series<Number, Number> series1 = mGraph1.getData().get(mGraph1.getData().size() - 1);
			//XYChart.Series<Number, Number> series2 = mGraph2.getData().get(mGraph2.getData().size() - 1);
	
			series1.getData().add(new Data<Number, Number>(t, value));
			//series2.getData().add(new Data<Number, Number>(t, value));

				return t;
			}		
		}
		
	public double calculateXPosition(double velocity, double angle, double time){
		return ((velocity) * Math.cos(Math.toRadians(angle)) * time );
	}	
	
	
	public double calculateYPosition(double velocity, double angle, double time){
		
		return ((time * velocity * Math.sin(Math.toRadians(angle))) -
				( 0.5 * Math.pow(time, 2)* GravityConstant))/1.27551;
	}	
	
	
	public double maxHeight(){
		double time = ((mSlider1.getValue()*Math.sin(Math.toRadians(mSlider2.getValue())))
				/ GravityConstant);
		
		return (calculateYPosition(mSlider1.getValue(), mSlider2.getValue(), time));
	}
	
	
	public void updateSetToX(){
		anim1.setToX((this.mCanvas.getWidth()/5)*calculateXPosition(mSlider1.getValue(), mSlider2.getValue(),1));
	}
	
	public void updateSetToY(){
		anim2.setFromY(this.mCanvas.getHeight()/2);

		anim2.setToY((this.mCanvas.getHeight()/2) -	(this.mCanvas.getHeight()/(2))
				* maxHeight());
	}
	
	
	public String getTitle(){
		return PROJ_TITLE;
	}
}
