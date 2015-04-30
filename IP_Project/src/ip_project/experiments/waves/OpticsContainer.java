package ip_project.experiments.waves;

import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.util.Duration;
import ip_project.main.MIContainer;
import ip_project.main.Resources;

/**
 * Optics Lens Experiment
 */
public class OpticsContainer extends MIContainer implements Resources {

	public String getTitle() {
		return OPTIC_TITLE;
	}

	LineChart<Number, Number> mGraph1;
	Slider mSlider1, mSlider2;
	Rectangle object, image, focalpoint1, focalpoint2;
	TranslateTransition anim1, anim2, anim3;
	

	public OpticsContainer(){
		//example of slider set up
		mSlider1 = new Slider(100, 300, 100);
		mSlider1.setShowTickMarks(true);
		mSlider1.setShowTickLabels(true);
		mSlider1.setMajorTickUnit(1);
		mSlider1.setMinorTickCount(0);
		mSlider1.setSnapToTicks(true);
		mSlider1.setId("Slider 1");
		this.addInputs(mSlider1);
		
		mSlider2 = new Slider(100, 300, 150);
		mSlider2.setShowTickMarks(true);
		mSlider2.setShowTickLabels(true);
		mSlider2.setMajorTickUnit(0.5);
		mSlider2.setMinorTickCount(0);
		mSlider2.setSnapToTicks(true);
		mSlider2.setId("Slider 2");
		this.addInputs(mSlider2);
		
		String Xstr1 = "Time";
		String Ystr1 = "Image Height";
	
		//example of graph set up
		//NumberAxis xAxis = new NumberAxis();
		// yAxis = new NumberAxis();
	
		
		NumberAxis xAxis = new NumberAxis(Xstr1, 0, 1, 0.1);
		NumberAxis yAxis =new NumberAxis(Ystr1,-500,	500, 50);
		
		
		
		
		mGraph1 = new LineChart<Number, Number>(xAxis, yAxis);
		
		this.addGraphs(mGraph1);
		
		Screen screen = Screen.getPrimary();
		
		Rectangle2D bounds = screen.getVisualBounds();
		
		image = new Rectangle(OBJECT_WIDTH, OBJECT_HEIGHT);
		image.setFill(Color.BLUE);
		image.setTranslateX(bounds.getWidth()*WIDTH_CONSTANT);
		image.setTranslateY((bounds.getHeight()/2)- OBJECT_HEIGHT);
		image.setVisible(true);
		
		
		//specify object- look and location
		object = new Rectangle(OBJECT_WIDTH, OBJECT_HEIGHT);
		object.setFill(Color.RED);
		object.setTranslateX(bounds.getWidth()*WIDTH_CONSTANT);
		object.setTranslateY((bounds.getHeight()/2) - OBJECT_HEIGHT);
		object.setVisible(false);
		
		Ellipse ellipse = new Ellipse(); 
		ellipse.setTranslateX(bounds.getWidth()*WIDTH_CONSTANT);
		ellipse.setTranslateY(bounds.getHeight()/2);
		ellipse.setRadiusX(15f);
		ellipse.setRadiusY(150f);
		
		focalpoint1 = new Rectangle(FOCAL_WIDTH, FOCAL_HEIGHT);
		focalpoint1.setTranslateX(bounds.getWidth()*WIDTH_CONSTANT);
		focalpoint1.setTranslateY((bounds.getHeight()/2));
		
		mCanvas.getChildren().add(ellipse);
		
		anim1 = new TranslateTransition(Duration.seconds(4), image);
		anim1.setInterpolator(Interpolator.LINEAR);		//this is where you put in the custom interpolator
		anim1.setFromX(bounds.getWidth()*WIDTH_CONSTANT);
//		anim1.setFromY((bounds.getHeight()/2));
		anim1.setCycleCount(1);
		
		anim2 = new TranslateTransition(Duration.seconds(3), focalpoint1);
		anim2.setInterpolator(new OpticsInterpolator());		//this is where you put in the custom interpolator
		anim2.setFromX(bounds.getWidth()*WIDTH_CONSTANT);
		anim2.setCycleCount(1);
		
		anim3 = new TranslateTransition(Duration.seconds(4), object);
		anim3.setInterpolator(Interpolator.LINEAR);		//this is where you put in the custom interpolator
		anim3.setFromX(bounds.getWidth()*WIDTH_CONSTANT);
		anim3.setToX(calculateImageDistance(mSlider1.getValue(), mSlider2.getValue()));
//		anim3.setFromY(bounds.getHeight()/2);
		anim3.setCycleCount(1);


		
		// TODO fix the width of the line 

		Line line1 = new Line(0, bounds.getHeight()/2, 1300, bounds.getHeight()/2);
		line1.setStroke(Color.BLACK);
		mCanvas.getChildren().addAll(line1);
		
//		 anim2 = new ScaleTransition(Duration.seconds(5), object);
//	     anim2.setInterpolator(Interpolator.LINEAR);	
//	     anim2.setFromY(1);
//	     anim2.setByX(1.5f);
//	     anim2.setByY(calculateImageHeight(mSlider1.getValue(), mSlider2.getValue(), 10));
//	     anim2.setCycleCount(1);
//	     anim2.setAutoReverse(true);
        
//        
		//set up the animation
		ParallelTransition comboAnim = new ParallelTransition();
		comboAnim.getChildren().addAll(anim1);

		
		this.addAnimations(comboAnim, anim2, anim3);
		this.addAnimationElements(image, focalpoint1, object);
		
	}

	// @Override
	public double calculateImageDistance(double focal, double object) {
		return 1 / (1 / focal - 1 / object);
	}

	public double calculateImageHeight(double focal, double object,
			double objectHeight) {
		return (calculateImageDistance(focal, object) / object) * objectHeight;
	}

	private class OpticsInterpolator extends Interpolator {

		@Override
		protected double curve(double t) {
			// calculations
			double value = calculateImageDistance(mSlider1.getValue(),
					mSlider2.getValue());
			double value2 = calculateImageHeight(mSlider1.getValue(),
					mSlider2.getValue(), OBJECT_HEIGHT);
			// get top level series
			XYChart.Series<Number, Number> series = mGraph1.getData().get(
					mGraph1.getData().size() - 1);
			series.getData().add(new Data<Number, Number>(t, value2*t));

			return t;
		}

	}

	@Override
	public void updateValues() {

		Screen screen = Screen.getPrimary();

		Rectangle2D bounds = screen.getVisualBounds();
		
//		image.setWidth(OBJECT_WIDTH);
//		image.setHeight(OBJECT_HEIGHT);
//		image.setTranslateX(bounds.getWidth()*0.294140625);
//		image.setTranslateY((bounds.getHeight()/2));

		anim1.setToX((bounds.getWidth() * WIDTH_CONSTANT) - mSlider2.getValue());
		anim2.setToX((bounds.getWidth() * WIDTH_CONSTANT) - mSlider1.getValue());
		object.setVisible(false);

		// object.setHeight(50);
		//

		//
		// double value = object.getHeight() /
		// calculateImageHeight(mSlider1.getValue(), mSlider2.getValue(), 20);
		//
		 anim3.setToX((bounds.getWidth()*WIDTH_CONSTANT) + calculateImageDistance(mSlider1.getValue(), mSlider2.getValue()));
		 
		// // anim2.setToY((bounds.getHeight()/2) +
		// calculateImageHeight(mSlider1.getValue(), mSlider2.getValue(), 20));
		//
		 anim1.setOnFinished(new EventHandler<ActionEvent>() {
		
		 @Override
		 public void handle(ActionEvent event) {
		
		 // object.setScaleY(value);
		 //// object.setTranslateY((bounds.getHeight()/2));
		
		 if(calculateImageHeight(mSlider1.getValue(), mSlider2.getValue(), OBJECT_HEIGHT) < 0)
		 {
			 
		 anim3.setToY((bounds.getHeight()/2) + calculateImageHeight(mSlider1.getValue(), mSlider2.getValue(), OBJECT_HEIGHT));	 
		 object.setHeight(Math.abs(calculateImageHeight(mSlider1.getValue(), mSlider2.getValue(), OBJECT_HEIGHT)));
//		 image.setTranslateX((bounds.getWidth()*WIDTH_CONSTANT) + calculateImageDistance(mSlider1.getValue(), mSlider2.getValue()));	 
 	     object.setTranslateY((bounds.getHeight()/2) + calculateImageHeight(mSlider1.getValue(), mSlider2.getValue(), OBJECT_HEIGHT));
	     
	     double testvalue = (bounds.getHeight()/2) - calculateImageHeight(mSlider1.getValue(), mSlider2.getValue(), OBJECT_HEIGHT);
	     
		 System.out.println("estoy aca ~~ " + testvalue);
	     
		 object.setVisible(true);
		 
		 }
		 
		 else{
		 
//		 image.setTranslateX((bounds.getWidth()*WIDTH_CONSTANT) + calculateImageDistance(mSlider1.getValue(), mSlider2.getValue()));	 
	     object.setHeight(Math.abs(calculateImageHeight(mSlider1.getValue(), mSlider2.getValue(), OBJECT_HEIGHT)));
		 object.setTranslateY((bounds.getHeight()/2));
		 
		 double value = (bounds.getHeight()/2) - calculateImageHeight(mSlider1.getValue(), mSlider2.getValue(), OBJECT_HEIGHT);
		 
		 object.setVisible(true);
		 System.out.println(bounds.getHeight()/2);
		 
		 }
		 
		 }
		 });
		// //
		// //
//		 image.setHeight(Math.abs(calculateImageHeight(mSlider1.getValue(), mSlider2.getValue(), 20)));
		// // object.setY(bounds.getHeight()/2);
		//
		 System.out.println(calculateImageDistance(mSlider1.getValue(),
		 mSlider2.getValue()));
		 System.out.println(calculateImageHeight(mSlider1.getValue(),
		 mSlider2.getValue(), OBJECT_HEIGHT));
//		 System.out.println(value);

	}

	@Override
	public String getHelp() {
		// TODO Auto-generated method stub
		return OPTIC_HELP;
	}
}
