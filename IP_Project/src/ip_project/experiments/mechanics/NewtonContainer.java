package ip_project.experiments.mechanics;

//import ip_project.experiments.Test.TestInterpolator;
import java.io.File;
import java.net.URL;
import java.nio.file.Paths;

import javax.swing.JOptionPane;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import ip_project.main.MIContainer;
import ip_project.main.Resources;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

/**
 * Newton's Second Law Experiment
 */
public class NewtonContainer extends MIContainer implements Resources {

	public String getTitle() {
		return NEWTON_TITLE;
	}

	public String getHelp() {
		return NEWTON_HELP;
	}

	// private ObservableList<XYChart.Series> XYlist;
	XYChart.Series<Number, Number> series1, series2;
	LineChart<Number, Number> mGraph1, mGraph2;
	Slider mSlider1, mSlider2;
	Animation gAnim;
	TranslateTransition anim1;
	
	double mForce, mMass, mAcceleration;
	ImageView image, imageflag, imagetext;
	FadeTransition fadetransition1, fadetransition2;

	public NewtonContainer() {

		// example of slider set up
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

		String Xstr1 = "Time";
		String Ystr1 = "Position";

		String Xstr2 = "Time";
		String Ystr2 = "Velocity";

		// example of graph set up
		NumberAxis xAxis1 = new NumberAxis(Xstr1, 0, calculate(
				calculateAcceleration(), 1), (double) 0.1);

		NumberAxis yAxis1 = new NumberAxis(Ystr1, 0, calculate(
				calculateAcceleration(), 1), (double) 0.1);

		NumberAxis xAxis2 = new NumberAxis(Xstr2, 0, calculate(
				calculateAcceleration(), 1), (double) 0.1);

		NumberAxis yAxis2 = new NumberAxis(Ystr2, 0, calculate((double) 1,
				(double) 1), (double) 0.1);

		Image image1 = new Image("ip_project/icons/tank.png");
		image = new ImageView();
		image.setImage(image1);
		image.setTranslateX(10);
		image.setTranslateY(400);
		image.setVisible(false);

		Image image2 = new Image("ip_project/icons/soviet-flag.png");
		imageflag = new ImageView();
		imageflag.setImage(image2);
		imageflag.setTranslateX(700);
		imageflag.setTranslateY(15);
		imageflag.setVisible(false);
		
		Image image3 = new Image("ip_project/icons/meanwhile.png");
		imagetext = new ImageView();
		imagetext.setImage(image3);
		imagetext.setTranslateX(350);
		imagetext.setTranslateY(700);
		imagetext.setVisible(false);



		yAxis1.setAutoRanging(false);
		xAxis1.setAutoRanging(false);
		yAxis2.setAutoRanging(false);
		xAxis2.setAutoRanging(false);

		mGraph1 = new LineChart<Number, Number>(xAxis1, yAxis1);
		mGraph2 = new LineChart<Number, Number>(xAxis2, yAxis2);

		this.addGraphs(mGraph1);
		this.addGraphs(mGraph2);

		anim1 = new TranslateTransition(
				Duration.seconds(10), image);
		anim1.setInterpolator(new NewtonInterpolator());
		anim1.setFromX(10);
		anim1.setCycleCount(1);
		anim1.setToX(600);

		TranslateTransition anim2 = new TranslateTransition(
				Duration.seconds(10), image);
		anim2.setInterpolator(Interpolator.LINEAR); // this is where you put in
													// the custom interpolator
		anim2.setFromY(400);
		anim2.setCycleCount(1);
		anim2.setToY(400);
		
		fadetransition1 = new FadeTransition(Duration.seconds(3), imageflag);
		fadetransition1.setInterpolator(Interpolator.LINEAR);
		fadetransition1.setCycleCount(1);
		fadetransition1.setFromValue(0);
		fadetransition1.setToValue(1);
		
		fadetransition2 = new FadeTransition(Duration.seconds(3), imagetext);
		fadetransition2.setInterpolator(Interpolator.LINEAR);
		fadetransition2.setCycleCount(1);
		fadetransition2.setFromValue(0);
		fadetransition2.setToValue(1);
		
        TranslateTransition bobbing = new TranslateTransition(Duration.millis(DurationBobbing1), image);
        TranslateTransition bobbing2 = new TranslateTransition(Duration.millis(DurationBobbing2), image);

        bobbing.setFromX(BobbingFromX);
        bobbing.setToX(BobbingToX);
        bobbing.setInterpolator(Interpolator.LINEAR);
        bobbing.setAutoReverse(true);
        bobbing2.setByY(BobbingByX);
        bobbing2.setInterpolator(Interpolator.LINEAR);

        bobbing2.setAutoReverse(true);
	

		// set up the animation
		ParallelTransition comboAnim = new ParallelTransition();
		comboAnim.getChildren().addAll(anim1, anim2, bobbing, bobbing2);

		this.addAnimations(comboAnim);
		this.addAnimationElements(image);
		
		mCanvas.getChildren().addAll(imageflag, imagetext);

		mGraph1.setAnimated(false);

		this.getStyleClass().add("newtons-second-law-canvas");

	}

	@Override
	public void updateValues() {
		
		String kalinkaS = new File("src/ip_project/sounds/kalinka.mp3").toURI().toString(); 

        Media pick = new Media(kalinkaS);
        MediaPlayer player = new MediaPlayer(pick);

        
        player.play();
		
		fadetransition1.play();
		fadetransition2.play();
		image.setVisible(true);
		imagetext.setVisible(true);
		imageflag.setVisible(true);
		anim1.setOnFinished(new EventHandler<ActionEvent>() {
			
			 @Override
			 public void handle(ActionEvent event) {
				 
				 player.stop();

			 }
		 });
	}
		

	private double calculateAcceleration() {
		return (mSlider1.getValue() / mSlider2.getValue());
	}

	// @Override
	public double calculate(double value, double time) {
		return value * Math.pow(time, 2);
	}

	private class NewtonInterpolator extends Interpolator {

		@Override
		protected double curve(double t) {

			double value = calculate(calculateAcceleration(), t);

			// get top level series
			XYChart.Series<Number, Number> series1 = mGraph1.getData().get(
					mGraph1.getData().size() - 1);
			XYChart.Series<Number, Number> series2 = mGraph2.getData().get(
					mGraph2.getData().size() - 1);

			if (value < 1) {
				series1.getData().add(new Data<Number, Number>(t, value));
				series2.getData()
						.add(new Data<Number, Number>(t,
								calculateAcceleration() * t));

				return value;
			} else
				// series.getData().add(new Data<Number, Number>(t, 1));
				return 1;
		}

	}
}
