package ip_project.experiments.mechanics;

//import ip_project.experiments.Test.TestInterpolator;
import ip_project.main.MIContainer;
import ip_project.main.Resources;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
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
				object1.setTranslateX(10);
				object1.setTranslateY(10);
				
				TranslateTransition anim1 = new TranslateTransition(Duration.seconds(5), object1);
				anim1.setInterpolator(new NewtonInterpolator());		
				anim1.setFromX(10);
				anim1.setCycleCount(1);
				anim1.setToX(500);
				
				
		       // calculateForce();
		      //  calculateMass();
		       // calculateAcceleration();
		        populateGraph();
		        
				TranslateTransition anim2 = new TranslateTransition(Duration.seconds(5), object1);
				anim2.setInterpolator(new NewtonInterpolator());		//this is where you put in the custom interpolator
				anim2.setFromY(10);
				anim2.setCycleCount(1);
				anim2.setToY(10);
		        
				//set up the animation
				ParallelTransition comboAnim = new ParallelTransition();
				comboAnim.getChildren().addAll(anim1, anim2);

				
				this.addAnimations(comboAnim);
				this.addAnimationElements(object1);
			}
			
	
			private void populateGraph(){
				
				series = new Series<Number, Number>();
		        series.setName("Newton's Second Law");

		        for(int i = 0; i<100; ++i){
		        	series.getData().add(new Data<Number, Number>(i, (calculateAcceleration() * 2* Math.pow(i, 2))));

		        }
		        mGraph1.getData().add(series);
				
			}
	
			private double calculateAcceleration(){
					
				calculateForce();
				calculateMass();
				
				return (mForce/mMass);
		
			}
			
			private  void calculateForce(){
				if(mSlider1.getValue()!=0)
					mForce = (mSlider1.getValue()*MAX_FORCE);
				else
					mForce = MIN_FORCE;
				
			}

			private void calculateMass(){
				if(mSlider2.getValue()!=0)
					mMass = (mSlider2.getValue()*MAX_WEIGHT);
				else
					mMass = MIN_WEIGHT;
			}
			
			private class NewtonInterpolator extends Interpolator{

				@Override
				protected double curve(double t) {
					
					//mGraph1.getData();
					return  calculateAcceleration() * 0.5* Math.pow(t, 2);
				}
				
			}

		
		
		
		
	
	

}
