package ip_project.experiments.calculus;

import ip_project.experiments.mechanics.ProjContainer.ProjXInterpolator;
import ip_project.main.MIContainer;
import ip_project.main.Resources;
import javafx.animation.Interpolator;
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
 * Geometric Series Experiment
 */
public class GeomSeriesContainer extends MIContainer implements Resources{

	XYChart.Series<Number, Number> series1;
	TranslateTransition anim1;
	Slider mSlider1, mSlider2;
	LineChart<Number, Number> mGraph1;
	
	public GeomSeriesContainer(){
		
		
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
				String Ystr1 = "Position";

				
				
				NumberAxis xAxis1 = new NumberAxis(Xstr1,0, 1,0.1);
				NumberAxis yAxis1 =new NumberAxis(Ystr1,-5,	5, 1);
				         
	
				
				
				yAxis1.setAutoRanging(false);
				xAxis1.setAutoRanging(false);
	
				mGraph1 = new LineChart<Number, Number>(xAxis1, yAxis1);
				
				this.addGraphs(mGraph1);
		
				
				
				Circle object1 = new Circle(10);
				object1.setFill(Color.BLUE);
				object1.setTranslateX(10);
				object1.setTranslateY(700);
				
				anim1 = new TranslateTransition(Duration.seconds(5), object1);
				anim1.setInterpolator(new GeomSeriesInterpolator());		
				anim1.setFromX(10);
				anim1.setCycleCount(1);
				anim1.setToX(500);
				
		
	}
	
	
	
	public double calculatePosition(double maxBounces, double angle, double time){
		return ((velocity) * Math.cos(Math.toRadians(angle)) * time );
	}	
	
	
	private class GeomSeriesInterpolator extends Interpolator{

		@Override
		protected double curve(double t) {
			
			double value = calculatePosition(mSlider1.getValue() ,
					mSlider2.getValue(), t);
			
			//get top level series
			XYChart.Series<Number, Number> series1 = mGraph1.getData().get(mGraph1.getData().size() - 1);
			//XYChart.Series<Number, Number> series2 = mGraph2.getData().get(mGraph2.getData().size() - 1);
	
			series1.getData().add(new Data<Number, Number>(t, value));
			//series2.getData().add(new Data<Number, Number>(t, value));

				return t;
			}		
		}
		
	
	
	
	public String getTitle(){
		return GEOM_SER_TITLE;
	}
}
