package ip_project.experiments.waves;

import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Rectangle2D;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Slider;
import javafx.scene.shape.Circle;
import javafx.stage.Screen;
import javafx.util.Duration;
import ip_project.main.MIContainer;
import ip_project.main.Resources;

/**
 * Optics Lens Experiment
 */
public class OpticsContainer extends MIContainer implements Resources{
	
	public String getTitle(){
		return OPTIC_TITLE;
	}
	
	LineChart<Number, Number> mGraph1;
	Slider mSlider1, mSlider2;
	Circle object1;
	ScaleTransition anim2;
	TranslateTransition anim1;
	
	public OpticsContainer(){
		//example of slider set up
		mSlider1 = new Slider(1, 200, 3);
		mSlider1.setShowTickMarks(true);
		mSlider1.setShowTickLabels(true);
		mSlider1.setMajorTickUnit(1);
		mSlider1.setMinorTickCount(0);
		mSlider1.setSnapToTicks(true);
		mSlider1.setId("Slider 1");
		this.addInputs(mSlider1);
		
		mSlider2 = new Slider(1, 200, 20);
		mSlider2.setShowTickMarks(true);
		mSlider2.setShowTickLabels(true);
		mSlider2.setMajorTickUnit(0.5);
		mSlider2.setMinorTickCount(0);
		mSlider2.setSnapToTicks(true);
		mSlider2.setId("Slider 2");
		this.addInputs(mSlider2);
		
		String Xstr1 = "Time";
		String Ystr1 = "X Position";
	
		//example of graph set up
		NumberAxis xAxis = new NumberAxis();
		NumberAxis yAxis = new NumberAxis();
		mGraph1 = new LineChart<Number, Number>(xAxis, yAxis);
		
		this.addGraphs(mGraph1);
		
		Screen screen = Screen.getPrimary();
		
		Rectangle2D bounds = screen.getVisualBounds();
		
		//specify object- look and location
		object1 = new Circle(10);
		object1.setTranslateX(bounds.getWidth()*0.294140625);
		object1.setTranslateY((bounds.getHeight()/2));
		
		anim1 = new TranslateTransition(Duration.seconds(5), object1);
		anim1.setInterpolator(new OpticsInterpolator());		//this is where you put in the custom interpolator
		anim1.setFromX(bounds.getWidth()*0.294140625);
		anim1.setCycleCount(1);
//		anim1.setToX(200);
//		anim1.setToX(calculateImageDistance(mSlider1.getValue(), mSlider2.getValue()));

		
		 anim2 = new ScaleTransition(Duration.seconds(5), object1);
	     anim2.setInterpolator(Interpolator.LINEAR);	
	     anim2.setFromY(1);
//	     anim2.setByX(1.5f);
//	     anim2.setByY(calculateImageHeight(mSlider1.getValue(), mSlider2.getValue(), 10));
	     anim2.setCycleCount(1);
//	     anim2.setAutoReverse(true);
        
//        
		//set up the animation
		ParallelTransition comboAnim = new ParallelTransition();
		comboAnim.getChildren().addAll(anim1, anim2);

		
		this.addAnimations(comboAnim);
		this.addAnimationElements(object1);
	}
		
	//@Override
	public double calculateImageDistance(double focal, double object){
		return  1/(1/focal-1/object);
	}
	
	public double calculateImageHeight(double focal, double object, double objectHeight){
		return (calculateImageDistance(focal, object)/object)* objectHeight;		
	}
	
	private class OpticsInterpolator extends Interpolator{

		@Override
		protected double curve(double t) {
			//calculations
			double value = calculateImageDistance(mSlider1.getValue(), mSlider2.getValue());
			double value2 = calculateImageHeight(mSlider1.getValue(), mSlider2.getValue(), 10);
			//get top level series
			XYChart.Series<Number, Number> series = mGraph1.getData().get(mGraph1.getData().size() - 1);
			series.getData().add(new Data<Number, Number>(value2, value));
			
			return t;
		}
		
	}

	@Override
	public void updateValues() {
		
		Screen screen = Screen.getPrimary();
		
		Rectangle2D bounds = screen.getVisualBounds();
		
		anim1.setToX((bounds.getWidth()*0.294140625) + calculateImageDistance(mSlider1.getValue(), mSlider2.getValue()));
		anim2.setByY(calculateImageHeight(mSlider1.getValue(), mSlider2.getValue(), 20));
		
		
		object1.setRadius(10);	
		System.out.println(calculateImageDistance(mSlider1.getValue(), mSlider2.getValue()));
		System.out.println(calculateImageHeight(mSlider1.getValue(), mSlider2.getValue(), 20));

		
		
	}

	@Override
	public String getHelp() {
		// TODO Auto-generated method stub
		return null;
	}
}
